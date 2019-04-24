package controladores.principal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ControladorNavegacao implements Initializable {
	
	@FXML Pane pNavegador;
	
	String strHTML;
	
	
	public void setHTML (String strHTML) {
		this.strHTML = strHTML;
	}
	
	AnchorPane ap;
	WebView webView;
	WebView webViewPopUp;
	
	Button btnVoltar;
	Button btnIr;
	Button btnAtualizaNavegador;
	Button btnGoogle;
	Button btnSEI;
	
	HBox hbControles;
	
	
	public static ControladorNavegacao conNav;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		conNav = this;
		
		ap = new AnchorPane();
		
		ap.minWidthProperty().bind(pNavegador.widthProperty());
		ap.maxWidthProperty().bind(pNavegador.widthProperty());
    	
		ap.minHeightProperty().bind(pNavegador.heightProperty());
		ap.maxHeightProperty().bind(pNavegador.heightProperty());
		
		hbControles = new HBox();
			hbControles.setPrefHeight(30);
		
			btnVoltar = new Button();
			btnVoltar.setPrefSize(90, 30);
			
			btnIr = new Button();
			btnIr.setPrefSize(90, 30);
			
			btnAtualizaNavegador = new Button();
			btnAtualizaNavegador.setPrefSize(90, 30);
			
			btnGoogle = new Button("GOOGLE");
			btnGoogle.setPrefSize(90, 30);
			
			btnSEI = new Button("SEI");
			btnSEI.setPrefSize(90, 30);
			
			hbControles.setStyle("-fx-background-color: white");
			
		hbControles.getChildren().addAll(btnVoltar, btnIr, btnAtualizaNavegador, btnGoogle, btnSEI);
	
		inicializarNavegadorWeb ();
    	
    	ap.getChildren().add(hbControles);
    	
    	AnchorPane.setTopAnchor(hbControles, 0.0);
 	    AnchorPane.setLeftAnchor(hbControles, 0.0);
 	    AnchorPane.setRightAnchor(hbControles, 0.0);
 	   
    	AnchorPane.setTopAnchor(webView, 30.0);
 	    AnchorPane.setLeftAnchor(webView, 0.0);
 	    AnchorPane.setRightAnchor(webView, 0.0);
 	    AnchorPane.setBottomAnchor(webView, 0.0);
    	
	    pNavegador.getChildren().add(ap);
	    
        btnGoogle.setOnAction((ActionEvent evt)->{
        	
        	link = "https://www.google.com.br/webhp?hl=pt-BR&sa=X&ved=0ahUKEwiQm_2BrN3hAhXKIrkGHRukAZUQPAgH";
        	navegarWeb (link);
        	
        });
        
        btnSEI.setOnAction((ActionEvent evt)->{
              	
          	link = "https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
          	navegarWeb (link);
              	
        });	
        	
	} // FIM INITIALIZE
	
	Button btnHTML;
	
	public void inicializarBotoesPopUp (){
		
		btnHTML = new Button("clique");
		
		//String strHTML = "'<b>Hello World</b>'";
		
		/*
		 * @String strHTML no formato "'<p>Hello World'"
		 * @ var x - captura o iframe de edição, no caso do requer
		 * @ var y - recebe contentDocument de x e muda texto  no iframe
		 */
		
		btnHTML.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
        			//-- imprimir o relatório ou tn no editor do SEI --//
            		webViewPopUp.getEngine().executeScript(
	            				"var x = document.getElementsByTagName('iframe')[2];"
	            			+ 	"var y = x.contentDocument;" 
	            			+ 	"y.body.innerHTML = " + strHTML + ";"
	            			);
					
            }
        });
		
	}
	
	String link;
	
	public void inicializarNavegadorWeb () {
		
		// inicializacao webVew //
		webView = new WebView();
		webView.setPrefSize(500, 500);
    	WebEngine webEnginer = webView.getEngine();
    	
    	webView.minWidthProperty().bind(pNavegador.widthProperty());
    	webView.maxWidthProperty().bind(pNavegador.widthProperty());
    	
    	webView.minHeightProperty().bind(pNavegador.heightProperty());
    	webView.maxHeightProperty().bind(pNavegador.heightProperty());
    	
    	// https://www.google.com.br/webhp?hl=pt-BR&sa=X&ved=0ahUKEwiQm_2BrN3hAhXKIrkGHRukAZUQPAgH
    	
    	//https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI
    	
    	// String link = "https://www.w3schools.com/howto/howto_js_popup.asp";
    	
    	// treinamento sei http://treinamento3.sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI
    	
    	webEnginer.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");
    	
    	ap.getChildren().add(webView);
    	
    	// inicializacao do pop up onde necessitar nas telas do SEI
    	webView.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

		    public WebEngine call(PopupFeatures p) {
		    	
		    	// inicializacao do webViewPopUp, responsavel pela tela de popup
		    	webViewPopUp = new WebView(); 
		    	
			    	AnchorPane apPopUp = new AnchorPane();
			    	
			    	apPopUp.setPrefHeight(645.0);
			    	apPopUp.setPrefWidth(1140.0);
			    	
			    	inicializarBotoesPopUp ();
			    
			    	
			    	HBox hbPopUp = new HBox(btnHTML);
			    	
			    	AnchorPane.setTopAnchor(hbPopUp, 0.0);
			 	    AnchorPane.setLeftAnchor(hbPopUp, 0.0);
			 	    AnchorPane.setRightAnchor(hbPopUp, 0.0);
			 	    
			 	    AnchorPane.setTopAnchor(webViewPopUp, 30.0);
			 	    AnchorPane.setLeftAnchor(webViewPopUp, 0.0);
			 	    AnchorPane.setRightAnchor(webViewPopUp, 0.0);
			 	    AnchorPane.setBottomAnchor(webViewPopUp, 0.0);
		 	    
			 	    apPopUp.getChildren().addAll(hbPopUp, webViewPopUp);
		 	   
		 	   
				Stage stage = new Stage();
				
	            stage.setScene(new Scene(apPopUp));
	            
	            stage.setMaximized(false);
		        stage.setResizable(false);
	            stage.show();
	            
	            return webViewPopUp.getEngine();
	            
		    }
		    
	    });
	
    	      
		
	}

	
	//-- navegar para o site google --//
	public void  navegarWeb (String link) {
			
		webView.getEngine().load(link);
			
	}

}
