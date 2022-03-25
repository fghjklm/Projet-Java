package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

public class Boite extends Box{
	
	public int hauteur, largeur, longueur;
	public static Color couleur;
	
	public Boite(int longu, int haut,int larg, Group group) {
		super(longu, haut, larg);
		this.hauteur = haut;
		this.largeur = larg;
		this.longueur  =longu;
		group.getChildren().add(this);
		
		
		
		this.setOnMousePressed(event -> {
		    {
		    	if (event.isPrimaryButtonDown()) {
		    		Boite b1 = new Boite(this.longueur, this.hauteur, this.largeur, group);
		    		System.out.println(this.getTranslateY());
		    		b1.translateYProperty().set(this.getTranslateY() - this.hauteur);
		    		b1.translateXProperty().set(this.getTranslateX());
		    		b1.translateZProperty().set(this.getTranslateZ());
		    	}
		    	              
		    	}
		    	 if (event.isSecondaryButtonDown()) {
		    	              
		    	}

    	});
		
		
		
		
		
	}
	public Color getCouleur() {
		return Boite.couleur;
	}
	
	public void setCouleur(Color couleurE) {
		Boite.couleur = couleurE;

	}

}
