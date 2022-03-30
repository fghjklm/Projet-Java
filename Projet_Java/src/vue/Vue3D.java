package vue;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.Camera;
import javafx.scene.Group;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;

import javafx.scene.paint.Color;

import javafx.scene.transform.Rotate;
import modele.Modele;
import javafx.scene.input.*;
 
public class Vue3D {
	

    private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	
	private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	
	
	

	
	public Scene getScene() {
		
		
		Modele mod = new Modele();
    	
    	Group group = mod.getTerrain();
    	
    	Camera camera = new PerspectiveCamera();
    	
    	Scene scene = new Scene(group,WIDTH,HEIGHT, true);
    	scene.setFill(Color.SILVER);
    	scene.setCamera(camera);
    	

    	group.translateZProperty().set(3000);

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
    		
    	});
    	
    	scene.addEventHandler(ScrollEvent.SCROLL, event ->{
    		double delta = event.getDeltaY();
    		group.translateZProperty().set(group.getTranslateZ() - 10*delta);
    	});
		
	}

}
