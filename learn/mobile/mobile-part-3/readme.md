In Part 1, we introduced a mobile app game, TiltMaze, written completely in JavaFX, which you can download from either the Apple App Store or Google Play and install it on your mobile device or tablet. In Part 2, we showed you how to work with Gluon and GraalVM to build native images that execute on either Apple or Android mobile devices and tablets.

In this article, we'll discuss how to upload your application to the respective mobile app stores so the world can install your application on their devices.

## Applying to the App Stores

OK, you’ve tested your JavaFX mobile application on both iOS and Android devices. Now it’s time to upload these applications to the respective app stores. This last step is fraught with tricky details and frustrations, especially for first time submitters.

The challenges have to do with each app store’s rules for user safety, application transparency, data privacy, software compliance, and specifying age-appropriate users. So, let’s jump in and examine the process that will hopefully result in acceptance for your application.

## Apple App Store

Here’s a summary of the steps required to submit a mobile app to the App Store. Gluon has very detailed, helpful instructions for each target platform. Read them carefully! Here you can find the instructions for ios targets. https://docs.gluonhq.com/#platforms_ios

* You should have already signed up for the Apple Developer Program. Among other things, this gives you the ability to create a provisioning profile, which you need to sign your app.
* Create a bundle ID for your app through the Apple Developer website under Certificates, Identifiers & Profiles.
* You will configure the provisioning profile and signing identity names in the pom.xml file.
* You’ve already tested the app yourself, but perhaps you’d like some beta testers. The best way to do this is to upload your app to TestFlight, the Apple store beta test application.
* Create and download a signing certificate for your bundle identifier and provisioning profile.
* Specify your own bundle identifiers and provisioning profiles in the pom.xml file. The GluonFX Plugin uses these settings for signing.
```xml
<releaseConfiguration>
    <bundleVersion>1.0.1</bundleVersion>
    <bundleShortVersion>1.0.1</bundleShortVersion>
    <providedSigningIdentity>
        iPhone Distribution: --put your own signing identity here--
    </providedSigningIdentity>
    <providedProvisioningProfile>
        --put your own provisioning profile here--
    </providedProvisioningProfile>
    </releaseConfiguration>
```

If there are issues with either your bundle ID, provisioning profile, or signing certificate, you will need to fix these and rerun the link step (you don’t need to rerun the compile step).

* Create an IPA file to submit to TestFlight.
```bash
$ mvn -Pios gluonfx:package
```

This creates file TiltMaze.ipa.

* Sign in with your Apple ID to App Store Connect:  https://appstoreconnect.apple.com/
* Select MyApps and select the plus sign to add an app to your account.

To create a new app, you’ll need to provide:

* Platforms (one or more of iOS, macOS, tvOS) 
* Name of your app as it will appear in the app store
* Primary language
* Previously created Bundle ID (or create a new one with link provided)
* SKU (an identifier internal to your organization)

There are several ways to upload your app to App Store Connect. I used Transporter, which is an app that can be installed on your Mac. Transporter verifies your application before uploading and makes sure the signing and provisioning information is correct.

If you need to upload a new version, you must change the version number in file **Default-Info.plist** as well as the `pom.xml` file. The `CFBundleVersion` must be increased for every upload. The `CFBundleShortVersionString` must be increased for every public release.

```xml
<key>CFBundleVersion</key>
<string>1.0.1</string>
<key>CFBundleShortVersionString</key>
<string>1.0.1</string>
```

## TestFlight

Once in **TestFlight**, submit your app for review. After it passes review, you can invite testers by email or through a public link.

## App Store

To prepare your app for submission, you provide screenshots, app descriptions, and additional information, including copyrights, license agreements, privacy policies, and company URLs. The various screenshot sizes are described here: https://help.apple.com/app-store-connect/#/devd274dd925

After you provide all the required information, submit your app for the app store review. After it passes, your app will appear in the App Store! Congratulations!

## Android Google Play

Here are the steps to upload your app to the Google Play Store.

* Create and upload a keystore using these directions: https://developer.android.com/studio/publish/app-signing
* Specify release configuration in the pom.xml file, as follows. You provide your own values for the keystore path, keystore password, and android key alias.

```xml
<releaseConfiguration>
    <versionCode>1</versionCode>
    <providedKeyStorePath>
        ${android-keystore-path}
    </providedKeyStorePath>
    <providedKeyStorePassword>
        ${android-keystore-password}
    </providedKeyStorePassword>
    <providedKeyAlias>
        ${android-key-alias}
    </providedKeyAlias>
    <providedKeyAliasPassword>
        ${android-key-password}
    </providedKeyAliasPassword>
</releaseConfiguration>
```

Create an Internal Testing release. Google recommends you use Play App Signing, which means Google signs the app for you, but uses your .keystore file to verify your upload. The Internal Testing lets you supply a list of emails of your testers.

You provide screenshots, descriptions of your application, a privacy policy, and a primary function of your application.

Once your testing is complete, it’s time to release your app into production. In this case, select Move to Production from the dropdown associated with your release. (You don’t create a new release, unless you make changes from one of the Testing Releases.) When you move your app to production, the Google Play Console lets you create and manage production releases to make your app available to all users in your chosen countries.

## Summary and Further Information

Together, JavaFX, Gluon, and GraalVM provide a viable technology stack for creating Mobile Applications targeting both iPhone/iPad iOS and Android phone and tablet devices. Create your UI with JavaFX and use the same JavaFX source code for all targets.

Learn more about Gluon, GraalVM, JavaFX, and Gluon Substrate for creating your own mobile offerings. See our Mobile Applications available in the app stores at Anderson Software Group.