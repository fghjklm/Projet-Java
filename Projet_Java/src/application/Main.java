package application;
	

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.scene.input.*;
 
public class Main extends Application {
    private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	
	private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	
	
	
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	SmartGroup group = new SmartGroup();
    	
    	for(int i =-50; i< 50; i++) {
    		
    		for(int j = -50; j<50; j++) {
    			Boite box = new Boite(50, 50, 50, group);
    			box.translateXProperty().set(i*50);
    			box.translateZProperty().set(j*50);
    			
    		}
    		
    	}

    	///Sphere sphere = new Sphere(50);

    	///sphere.translateXProperty().set(-50);;

    	///group.getChildren().add(sphere);
    	

    	
    	///group.getChildren().add(prepareSecondBox());
    	
    	Camera camera = new PerspectiveCamera();
    	
    	Scene scene = new Scene(group,WIDTH,HEIGHT, true);
    	scene.setFill(Color.SILVER);
    	scene.setCamera(camera);
    	
    	group.translateXProperty().set(0);;
    	group.translateYProperty().set(0);
    	group.translateZProperty().set(8000);

    	initMouseControl(group,  scene, primaryStage);
    	
    	
    	primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event->{
    		switch(event.getCode())
    		{
    		case Z:
    			group.translateZProperty().set(group.getTranslateZ() + 70);
    			break;
    		case S:
    			group.translateZProperty().set(group.getTranslateZ() - 70);
    			break;
    		case A:
    			group.rotateByX(10);
    			break;
    		case E:
    			group.rotateByX(-10);
    			break;
    		
	    	case Q:
				group.rotateByY(10);
				break;
			
	    	case D:
				group.rotateByY(-10);
				break;
    
		}
    		
    	});
    	
    	primaryStage.setTitle("Genuine Coder");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
    	

    	
    	
    }
    
    private Node prepareSecondBox() {
    	;
    	Box box = new Box(20, 50, 100);
    	box.translateXProperty().set(50);;
    	box.translateYProperty().set(0);
		
		return box;
	}

	private void initMouseControl(SmartGroup group,Scene  scene, Stage stage) {
    	Rotate xRotate;
    	Rotate yRotate;
    	group.getTransforms().addAll(
    			xRotate = new Rotate(0, Rotate.X_AXIS),
    			yRotate = new Rotate(0, Rotate.Y_AXIS));
    	
    	xRotate.angleProperty().bind(angleX);
    	yRotate.angleProperty().bind(angleY);
    	
    	scene.setOnMousePressed(event -> {
    		anchorX = event.getSceneX();
    		anchorY = event.getSceneY();
    		anchorAngleX = angleX.get();
    		anchorAngleY = angleY.get();
    	});
    	
    	scene.setOnMouseDragged(event -> {
    		angleX.set(anchorAngleX-(anchorY- event.getSceneY()));
    		angleY.set(anchorAngleY-(anchorX- event.getSceneX()));
    		System.out.println("l'angle X est :" +angleX.get());
    		System.out.println("l'angle Y est " +angleY.get());
    		
    	});
    	
    	stage.addEventHandler(ScrollEvent.SCROLL, event ->{
    		double delta = event.getDeltaY();
    		group.translateZProperty().set(group.getTranslateZ() - delta);
    		System.out.println("La translation Z est :" + group.getTranslateZ());
    	});
    }
    
    class SmartGroup extends Group{
    	
    	Rotate r;
    	
    	Transform t = new Rotate();
    	
    	void rotateByX(int ang) {
    		
    		r = new Rotate(ang, Rotate.X_AXIS);
    		t = t.createConcatenation(r);
    		this.getTransforms().clear();
    		this.getTransforms().addAll(t);
    	}
    	
		void rotateByY(int ang) {
			
		    		
		    r = new Rotate(ang, Rotate.Y_AXIS);
		    t = t.createConcatenation(r);
		    this.getTransforms().clear();
		    this.getTransforms().addAll(t);
		}

    	
    	
    }
}