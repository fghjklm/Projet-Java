package modele;

import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Boite extends Box{
	
	public int hauteur, largeur, longueur;
	public Boite fils;
	public Boite pere;
	public Group group;
	private boolean destructible;
	
	public Boite(int longu, int haut,int larg, Group group, Modele mod) {
		super(longu, haut, larg);
		this.hauteur = haut;
		this.largeur = larg;
		this.longueur  =longu;
		this.group = group;
		this.setDestructible(true);
		group.getChildren().add(this);
		PhongMaterial material = new PhongMaterial();
		
		switch(mod.getEtat()) {

		case COULEUR:
			material.setDiffuseColor(mod.getCouleur());
		
		case TEXTURE:
			material.setDiffuseMap(mod.getTexture());
			
		}
		this.setMaterial(material);
		
		
		
		this.setOnMousePressed(event -> {
		    {
		    	if (event.isPrimaryButtonDown()) {
		    		if (this.fils == null) {
		    			Boite b1 = new Boite(this.longueur, this.hauteur, this.largeur, group, mod);
			    		b1.pere = this;
			    		this.fils = b1;
			    		
			    		System.out.println(this.getTranslateY());
			    		
			    			
			    		b1.translateYProperty().set(this.getTranslateY() - this.hauteur);
			    		b1.translateXProperty().set(this.getTranslateX());
			    		b1.translateZProperty().set(this.getTranslateZ());
		    			
		    		}
		    		
		    	}
		    	              
		    	}
		    	 if (event.isSecondaryButtonDown()) {	
		    		if (this.destructible) {
		    			if(this.pere != null) {
				    		this.pere.fils = null;		
		    			}
		    			this.removeFils();
		    		}

		    	              
		    	}

    	});
		
	}

	private void removeFils() {
		group.getChildren().remove(this);
		if (this.fils!= null) {
			this.fils.removeFils();
		}
		
	}
	
	public void remove() {
		group.getChildren().remove(this);
	}

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}
	
		
		
		
		

}
