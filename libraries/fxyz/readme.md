# FXyz3D

A JavaFX 3D Visualization and Component Library

With FXyz3D there are many different 3D custom shapes. See for example `SpringMesh` in the screenshots, to create
a 3D mesh of a spring.

## Sample

Create a JavaFX Application class `Sample` under the `org.fxyz3d` package:

```java
@Override
public void start(Stage primaryStage)throws Exception{
        PerspectiveCamera camera=new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateX(10);
        camera.setTranslateZ(-100);
        camera.setFieldOfView(20);

        CameraTransformer cameraTransform=new CameraTransformer();
        cameraTransform.getChildren().add(camera);
        cameraTransform.ry.setAngle(-30.0);
        cameraTransform.rx.setAngle(-15.0);

        SpringMesh spring=new SpringMesh(10,2,2,8*2*Math.PI,200,100,0,0);
        spring.setCullFace(CullFace.NONE);
        spring.setTextureModeVertices3D(1530,p->p.f);

        Group group=new Group(cameraTransform,spring);

        Scene scene=new Scene(group,600,400,true,SceneAntialiasing.BALANCED);
        scene.setFill(Color.BISQUE);
        scene.setCamera(camera);

        primaryStage.setScene(scene);
        primaryStage.setTitle("FXyz3D Sample");
        primaryStage.show();
        }
```