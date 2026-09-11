## Securing a JavaFX desktop application with Spring Security and OAuth 2.0

This tutorial continues where
["JavaFX and Spring Boot"](https://www.jfx-central.com/learn-javafx/spring-boot) stopped. We have a
JavaFX application whose lifecycle is managed by Spring, the stage is published as a Spring event,
and the FXML components are looked up by ID and ready to receive behaviour. Now we want the
**Sign in** button to actually sign somebody in, and the **Call** button to call a protected backend
API on their behalf.

This tutorial is based on the video by
[**Josh Long**](https://www.jfx-central.com/people/j.long).

![alt](youtube:gB7FIbxMEos)

### Why a desktop application cannot use a normal OAuth client

In an OAuth flow there are two levels of authentication. The user authenticates, but the *client*
also has to prove to the authorization server that it is a client the server knows, so that it is
allowed to act on behalf of that user. A web application does that with a client secret stored on
the server.

A desktop application cannot do that. Whatever you ship, the secret ships with it:

> "*Even a native image - if you run the `strings` command line utility on the native binary, you'll
> see the text string values for things like a password, and they're just in plain text too. And
> even more so in the JAR, which is just a ZIP file: you can unzip it and now you can see the client
> secret.*"

So storing a client ID *and* a client secret in the binary we ship to users is a non-starter.

### PKCE

The answer is **PKCE**, Proof Key for Code Exchange (pronounced "pixie"). It replaces the static
secret with a secret that is generated fresh for every single login:

1. The app generates a random, cryptographically secure string, the **verifier**.
2. The app hashes the verifier with SHA-256 to create the **challenge**.
3. The app sends the challenge to the authorization server, and keeps the verifier on the device.
4. When trading the authorization code for an access token, the app sends the original verifier, so
   the server can confirm it matches the challenge it received earlier.

### The authorization server

For the demo, an authorization server is generated on [start.spring.io](https://start.spring.io)
with **OAuth2 Authorization Server**, **Web**, **JDBC**, **PostgreSQL** and **Flyway**, plus a Docker
Compose file for the database. A Flyway migration creates the standard Spring Security users and
authorities tables and inserts a few users. In a real setup you would of course deploy this
somewhere in your organization instead of on localhost.

The interesting part is the registration of our desktop client:

```properties
server.port=9090
spring.threads.virtual.enabled=true

spring.security.oauth2.authorizationserver.client.javafx.registration.client-id=javafx
spring.security.oauth2.authorizationserver.client.javafx.registration.client-authentication-methods=none
spring.security.oauth2.authorizationserver.client.javafx.registration.authorization-grant-types=authorization_code,refresh_token
spring.security.oauth2.authorizationserver.client.javafx.registration.redirect-uris=http://127.0.0.1:8385/login/oauth2/code/javafx
spring.security.oauth2.authorizationserver.client.javafx.registration.scopes=openid,profile,email,user.read
spring.security.oauth2.authorizationserver.client.javafx.require-proof-key=true
```

Two things stand out. There is **no client authentication method** and no secret. And
`require-proof-key=true` switches on the PKCE support we just described.

The redirect URI points at `127.0.0.1`. That looks odd for a desktop client, but the desktop
application starts a tiny single-use web server on port 8385 that receives the callback from the
authorization server.

### Opening the system browser

We do not authenticate inside the application. Instead we open the user's own browser - on macOS,
Linux or Windows - let them sign in at the authorization server, and let the server redirect them
back to our local web server. A small interface keeps that testable and platform-specific.

```java
public interface AuthorizationBrowser {
    void open(String authorizationRequestUri);
}

@Component
class MacOsSystemBrowser implements AuthorizationBrowser {

    @Override
    public void open(String authorizationRequestUri) {
        var command = List.of("open", authorizationRequestUri);
        try {
            new ProcessBuilder(command).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

Using the JavaFX `WebView` for this is possible, but do not do it - use the real browser, so the
user sees a URL bar they can trust, and so existing sessions and password managers keep working.
On Linux and Windows you would provide another implementation, for instance behind a Spring profile.

### Driving the flow

The primitives for this all exist inside Spring Security, but they are aimed at the web tier, so for
a desktop application some assembly is required. The flow is a sandwich with two halves:

* **start** - look up the client registration, build an `OAuth2AuthorizationRequest` (client ID,
  authorization URI, redirect URI, scopes) and let the PKCE customizer add the challenge. Store the
  request in an `AtomicReference` (there is multi-threading involved) and open the browser.
* **finish** - the callback arrives, we take the in-flight request out of the `AtomicReference`
  while replacing it with `null` so it is truly single use, combine it with the returned parameters
  into a response, perform the authorization code exchange and get an access token. Then we publish
  a `UserSignedInEvent` so the rest of the application knows who is signed in.

The event is again a plain Spring application event that exposes the `OAuth2AuthenticationToken`,
the user name (`preferred_username`, falling back to `name`) and the `OAuth2User`.

Once this work is done, Spring Security is back in charge of renewing and refreshing the token, and
all the familiar machinery, like the `RestClient` interceptors, starts working again.

### Plugging into Spring Security

Spring Security authorizes clients through an `OAuth2AuthorizedClientProvider`. There are
implementations for token exchange, refresh tokens, JWT bearer tokens, client credentials and the
authorization code grant - and now we add one for our system browser flow.

The provider starts the login and then blocks on a queue until the callback has been handled, with a
timeout so that a user who wanders off does not block the UI forever.

```java
@Configuration
class SecurityConfiguration {

    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService,
            SystemBrowserOAuth2AuthorizedClientProvider provider) {

        var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientService);

        manager.setAuthorizedClientProvider(OAuth2AuthorizedClientProviderBuilder.builder()
                .provider(provider)
                .build());

        return manager;
    }
}
```

Two more adjustments are needed, because Spring Boot assumes it is configuring a secure *web*
client:

```java
// One user, one session - no thread-bound security context needed.
SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
```

```java
@SpringBootApplication(exclude = {
        SecurityFilterChainAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class,
        OAuth2ClientWebSecurityAutoConfiguration.class
})
public class DesktopApplication extends Application { /* ... */ }
```

The global `SecurityContextHolder` strategy is a nice fit here: there is exactly one tenant, so all
the weirdness around thread management and context propagation simply goes away. The one user *is*
the user.

### Catching the callback

The desktop application also runs an embedded web server, on the port we registered as the redirect
URI:

```properties
server.port=8385
server.address=127.0.0.1
spring.threads.virtual.enabled=true
```

And a very ordinary Spring MVC controller receives the redirect, calls the `finish` half of the
flow, and renders a small page telling the user they can go back to the application. The page is
rendered with Mustache, so the desktop application needs that starter as well:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mustache</artifactId>
</dependency>
```

```java
@Controller
class AuthorizationCodeRedirectController {

    private final SystemBrowserOAuth2Login login;

    AuthorizationCodeRedirectController(SystemBrowserOAuth2Login login) {
        this.login = login;
    }

    @GetMapping("/login/oauth2/code/{registrationId}")
    String signedIn(@PathVariable String registrationId,
                    @RequestParam Map<String, String> parameters,
                    Model model) {
        var event = this.login.finish(registrationId, parameters);
        model.addAttribute("name", event.name());
        return "signed-in";
    }
}
```

Make sure this is not annotated with `@ResponseBody` or `@RestController`, otherwise Spring treats
the return value as a REST response instead of a view name. The view itself lives in
`src/main/resources/templates/signed-in.mustache` and is as plain as it gets:

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Signed in</title>
</head>
<body>
<h1>You're signed in, {{name}}.</h1>
<p>You can close this window and go back to the app.</p>
</body>
</html>
```

### Wiring the button - and the JavaFX thread

Nothing happens until the **Sign in** button is connected to all of this. And here a second
threading rule appears, this time from JavaFX: anything that blocks must not run on the JavaFX
application thread, and anything that updates the UI must run on it. A tiny helper makes that
explicit.

```java
abstract class Threads {

    static void offFxThread(Runnable runnable) {
        Thread.ofVirtual().start(runnable);
    }

    static void onFxThread(Runnable runnable) {
        Platform.runLater(runnable);
    }
}
```

Blocking work goes on a virtual thread, UI work goes through `Platform.runLater`. With that in
place, the wiring in the stage initializer is short:

```java
signIn.setOnAction(e -> Threads.offFxThread(() -> login.start("javafx")));
```

```java
@EventListener
public void onUserSignedIn(UserSignedInEvent event) {
    Threads.onFxThread(() -> greeting.setText("Greetings " + event.name()));
}
```

Click **Sign in**, the system browser opens, the authorization server asks for credentials, the
browser lands on our local page, and the JavaFX window updates to "Greetings Josh".

### Calling a protected API

The last piece is a separate, headless resource server (generated with **OAuth2 Resource Server**
and **Web**) that exposes a `/message` endpoint echoing the authenticated principal, and points at
the same issuer.

On the client side you could dig the token out of the `OAuth2AuthenticationToken` and the
`OAuth2AuthorizedClientService` yourself and set the `Authorization` header by hand, but that is
horrific - and unnecessary. Register the request interceptor on the `RestClient` and let Spring
Security source the token:

```java
@Bean
RestClient restClient(RestClient.Builder builder, OAuth2AuthorizedClientManager manager) {
    var interceptor = new OAuth2ClientHttpRequestInterceptor(manager);
    interceptor.setClientRegistrationIdResolver(request -> "javafx");
    return builder.requestInterceptor(interceptor).build();
}
```

The client then only has to say *which* registration it wants to use. Better still, with the
declarative HTTP service clients from Spring Framework 6 and 7 the whole client collapses into an
interface, and `@ClientRegistrationId` is where it names the registration:

```java
@ClientRegistrationId("javafx")
interface MessageClient {

    @GetExchange("http://localhost:8081/message")
    Message message();
}
```

```java
@Configuration
@ImportHttpServices(group = "message", types = MessageClient.class)
class MessageClientConfiguration {

    @Bean
    OAuth2RestClientHttpServiceGroupConfigurer oauth2GroupConfigurer(OAuth2AuthorizedClientManager manager) {
        return OAuth2RestClientHttpServiceGroupConfigurer.from(manager);
    }
}
```

The group configurer is what installs the OAuth 2.0 interceptor on the `RestClient` behind the
declarative clients - without it the annotation has nothing to talk to and no token is attached.
Register the `RestClient` group once per JVM - it does not matter whether you have one of these
clients or a thousand, you declare it exactly once - and calling the API is a one-liner. Press
**Call** and the text area fills with `Hello Josh`.

### The result

A secure, portable desktop application that combines JavaFX with the Spring component model,
application events, the environment abstraction, resource loading and resource bundles - plus a real
OAuth 2.0 PKCE flow through the system browser.

And it still compiles to a GraalVM native image with no extra effort:

```shell
./mvnw -DskipTests native:compile
```

The resulting binary uses roughly 200 MB of real memory according to Activity Monitor, and starts
instantly. If you prefer, you can ship the same JAR to everybody and run it on the JVM, or bundle a
JRE - it starts plenty fast that way too, and certainly faster than an Electron application.

One last practical tip: turn the console logging off before shipping, because your users are never
going to see it anyway.

```properties
logging.level.root=off
spring.main.banner-mode=off
```
