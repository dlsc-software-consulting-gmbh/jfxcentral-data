# FXyz3D

A JavaFX 3D visualization and component library with many custom shapes (e.g. `SpringMesh`, shown in the screenshots).

## Sample

```java
PerspectiveCamera camera = new PerspectiveCamera(true);
camera.setNearClip(0.1);
camera.setFarClip(10000.0);
camera.setTranslateX(10);
camera.setTranslateZ(-100);
camera.setFieldOfView(20);

CameraTransformer cameraTransform = new CameraTransformer();
cameraTransform.getChildren().add(camera);
cameraTransform.ry.setAngle(-30.0);
cameraTransform.rx.setAngle(-15.0);

SpringMesh spring = new SpringMesh(10, 2, 2, 8 * 2 * Math.PI, 200, 100, 0, 0);
spring.setCullFace(CullFace.NONE);
spring.setTextureModeVertices3D(1530, p -> p.f);

Scene scene = new Scene(new Group(cameraTransform, spring), 600, 400, true, SceneAntialiasing.BALANCED);
scene.setFill(Color.BISQUE);
scene.setCamera(camera);

primaryStage.setScene(scene);
primaryStage.show();
```