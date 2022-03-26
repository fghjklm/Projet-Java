package vue;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.Camera;
import javafx.scene.Group;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;

import javafx.scene.paint.Color;

import javafx.scene.transform.Rotate;
import modele.Boite;
import modele.Modele;
import javafx.scene.input.*;
 
public class Vue {
	

    private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	
	private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	
	
	

	
	public Scene vueE() {
		
		
		Modele mod = new Modele();
    	
    	Group group = new Group();
    	
    	for(int i =-50; i< 50; i++) {
    		
    		for(int j = -50; j<50; j++) {
    			Boite box = new Boite(50, 50, 50, group, mod);
    			box.translateXProperty().set(i*50);
    			box.translateZProperty().set(j*50);
    			box.setDestructible(false);
    			
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

    	initMouseControl(group,  scene);
    	
    	
    	scene.addEventHandler(KeyEvent.KEY_PRESSED, event->{
    		switch(event.getCode())
    		{
    		case W:
    			mod.setCouleur(Color.WHITE);
    			break;
    		case B:
    			mod.setCouleur(Color.BLUE);
    			break;
    		
	    	case R:
				mod.setCouleur(Color.RED);
				break;
			
	    	case V:
				mod.setCouleur(Color.GREEN);
				break;
			default:
				break;
    
		}
    		
    	});
		
		
		return scene;
		
	}


	private void initMouseControl(Group group,Scene  scene) {
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
    	
    	scene.addEventHandler(ScrollEvent.SCROLL, event ->{
    		double delta = event.getDeltaY();
    		group.translateZProperty().set(group.getTranslateZ() - delta);
    		System.out.println("La translation Z est :" + group.getTranslateZ());
    	});
		
	}

}