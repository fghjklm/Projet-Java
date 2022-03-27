package lego;
	

import javafx.application.Application;
import javafx.stage.Stage;
import vue.Vue;

 
public class Lego extends Application {
	
	public static void main(String[] args) {	
        launch(args);
    }
	
    @Override
    public void start(Stage primaryStage) {
    	Vue vue = new Vue();
    	primaryStage.setTitle("Lego");
    	primaryStage.setScene(vue.getScene());
    	primaryStage.show();
	
    }
 

}
