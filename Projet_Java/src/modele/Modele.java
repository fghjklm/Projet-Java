package modele;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
public class Modele {
	
	private Color couleur;
	private Image texture;
	public enum Etat {
		COULEUR, TEXTURE
	}
	
	private  Etat etat;
	
	public Modele() {
		
		this.couleur = Color.SILVER;
		this.texture = null;
		this.etat = Etat.COULEUR;
		
	}



	public void setCouleur(Color coul) {
		this.couleur = coul;;
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public Etat getEtat() {
		return this.etat;
	}



	public Image getTexture() {
		return texture;
	}



	public void setTexture(Image texture) {
		this.texture = texture;
	}
	
	
	
	

}
