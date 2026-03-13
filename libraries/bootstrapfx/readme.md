# BootstrapFX

BootstrapFX is a partial port of [Twitter Bootstrap](https://getbootstrap.com/) for JavaFX, providing a CSS stylesheet
that closely resembles the original while being tailored for JavaFX's CSS flavor.

## Getting Started

Apply the `bootstrapfx.css` stylesheet to a scene:

```java
Panel panel = new Panel("This is the title");       // (1) custom BootstrapFX widget
panel.getStyleClass().add("panel-primary");

BorderPane content = new BorderPane();
content.setPadding(new Insets(20));

Button button = new Button("Hello BootstrapFX");
button.getStyleClass().setAll("btn", "btn-danger"); // (2) apply CSS class
content.setCenter(button);
panel.setBody(content);

Scene scene = new Scene(panel);
scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet()); // (3) apply stylesheet
```

## Supported CSS Classes

| Category | Classes |
|----------|---------|
| **Text** | `b`, `strong`, `i`, `em`, `italic`, `dfn`, `small`, `code`, `kbd`, `pre`, `samp`, `h1`–`h6`, `lead`, `p`, `text-mute`, `text-{primary,success,info,warning,danger}`, `bg-{primary,success,info,warning,danger}` |
| **Buttons** | `btn`, `btn-{default,primary,success,info,warning,danger}`, `btn-{lg,sm,xs}` |
| **SplitMenu Buttons** | `split-menu-btn`, `split-menu-btn-{default,primary,success,info,warning,danger}`, `split-menu-btn-{lg,sm,xs}` |
| **Labels** | `lbl`, `lbl-{default,primary,success,info,warning,danger}` |
| **Panels** | `panel`, `panel-{default,primary,success,info,warning,danger}`, `panel-{heading,title,body,footer}` |
| **Alerts** | `alert`, `alert-{success,info,warning,danger}` |
| **Groups** | `btn-group-horizontal`, `btn-group-vertical` ¹ |
| **Progress Bars** | `progress-bar-{primary,success,info,warning,danger}` |
| **Tooltips** | `tooltip-{primary,success,info,warning,danger}` |
| **Miscellaneous** | `badge` |

> ¹ All elements inside a vertical button group must have the same width.
