package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe Principal
 * @author fabricio.barrozo
 * @version 1.0
 *
 */
public class Principal extends Application{
	
	public Parent rootNode;
	
	/** Método principal - Main
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    /** Método de leitura do arquivo fxml da classe principal
     * 
     */
    public void init() throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/principal/Principal.fxml"));
        rootNode = fxmlLoader.load();
        
    }

    /** Método de inicialização do JavaFX
     * 
     */
	public void start(Stage stage) throws Exception {
		
		Scene scene = new Scene (rootNode);
		
        stage.setScene(scene);
        
        stage.setTitle("Welcome to JavaFX!"); 
        
        // Redimensionamento da do stage de acordo com as dimensões do monitor
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        // teste no notebook
        // stage.setWidth(1266);
        // stage.setHeight(668);
       
        // limites de tamanho do stage
        stage.setMinHeight(768);
        stage.setMinWidth(1366);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        
        //stage.sizeToScene(); 
        stage.show();
	
	}

}
