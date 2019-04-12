package controladores.principal;

import java.io.IOException;

import controladores.modelosHTML.ControladorModelosHTML;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import mapas.GoogleMap;

public class ControladorPrincipal {
	
	@FXML AnchorPane apPrincipal;
	
	// para chamar a parte da fiscalizacao //
	ControladorAtendimento controladorAtendimento;
	ControladorFiscalizacao controladorFiscalizacao;
	ControladorModelosHTML controladorModelosHTML;
	
	Button btnTerrain = new Button();
	Button btnRoadMap = new Button();
	Button btnSattelite = new Button();
	Button btnHybrid = new Button();
	
	Button btnZoomOut = new Button();
	Button btnZoomIn = new Button();
	
	Button btnNightMap = new Button();
	Button btnBlueMap = new Button();
	Button btnGreenMap = new Button();
	Button btnRetroMap = new Button();
	
	CheckBox checkBacia;
	CheckBox checkRiodDF;
	CheckBox checkRiosUniao;
	CheckBox checkFraturado;
	CheckBox checkPoroso;
	CheckBox checkUTM;
	CheckBox checkTrafego;
	
	TabPane tpLateralDireita = new TabPane();
	TabPane tpLateralEsquera = new TabPane();
	
	Tab tabLD1 = new Tab();
	Tab tabLD2 = new Tab();
	
	Tab tabLE1 = new Tab();
	Tab tabLE2 = new Tab();
	
	Pane pLD1 = new Pane();
	Pane pLD2 = new Pane();
	Pane pLE1 = new Pane();
	Pane pLE2 = new Pane();
	
	StackPane spCentroTabPanes = new  StackPane();
	StackPane spConversorCoordenadas = new  StackPane();
	
	Pane paneConversor = new Pane();
	Pane pConvCentral = new Pane();

	Pane pSuperior = new Pane();
	
	Pane pBrowserSEI;
		Pane pFiscalizacao;
			Pane pAtendimento;
				Pane pOutorga;
	
	Pane pBroSEICadastros;
		Pane pFiscCadastros;
			Pane pAtenCadastros;
				Pane pOutCadastros;
	
	Button btnHome = new Button();
	Button btnMapa = new Button();
	Button btnConversor = new Button();
	Button btnAtendimento = new Button();
	Button btnFiscalizacao = new Button();
	Button btnSEI = new Button();
	Button btnEditorHTML = new Button();
	
	static GoogleMap googleMaps;
	
	ObservableList<String> olcbMainConverteCoord;
	
	Double dblSearch;
	Double dblBrowser;
	Double dblFiscal;
	Double dblAtend;
	
	TranslateTransition upFiscal;
	TranslateTransition downFiscal;
	
	TranslateTransition upAtend;
	TranslateTransition downAtend;
	
	TranslateTransition downSearch;
	TranslateTransition upSearch;
	
	TranslateTransition downBrowser;
	TranslateTransition upBrowser;
	
	public static GoogleMap capturarGoogleMaps () {
		return googleMaps;
    }
	
	TextField tfLatDD;
	TextField tfLonDD;
	TextField tfZonaUTM;
	
	public static Label lblCoord1;
	public static Label lblCoord2;
	
	public static Label lblDD;
	public static Label lblDMS;
	public static Label lblUTM;
	
	
	ComboBox<String> cbConverterCoord = new ComboBox<>();
	
	Button btnConverteCoord = new Button("BUSCAR");
	
	ComboBox<String> cbNorteSul;
	ObservableList<String> cbNorteSulOpcoes;
	
	ComboBox<String> cbLesteOeste;
	ObservableList<String> cbLesteOesteOpcoes;
	
	Pane pWebMap = new Pane();
	
	ScrollPane spWebBrowser;
	WebView wBrowser;
	
	
	@FXML 
	private void initialize() {
		
	    Platform.runLater(new Runnable(){

			public void run() {
				
				googleMaps = new GoogleMap();
				
				
				pWebMap.getChildren().add(googleMaps); 
				
				pWebMap.widthProperty().addListener(
			    		(observable, oldValue, newValue) -> {
			    			googleMaps.resizeWidthMap ((Double)newValue);
			    			
				    	    }
			    		);
				
				pWebMap.heightProperty().addListener(
			    		(observable, oldValue, newValue) -> {
			    			googleMaps.resizeHeightMap((Double)newValue) ;
			    			
				    	    }
			    		);
			    	
				AnchorPane.setTopAnchor(pWebMap, 0.0);
				AnchorPane.setLeftAnchor(pWebMap , 0.0);
			    AnchorPane.setRightAnchor(pWebMap , 0.0);
			    AnchorPane.setBottomAnchor(pWebMap, 0.0);
			    
			}
	   
    	});
	  
	    pLE1.setPrefSize(140, 370);
		pLE2.setPrefSize(140, 370);
		
		tabLE1.setContent(pLE1);
		tabLE2.setContent(pLE2);
		
	    pLD1.setPrefSize(140, 370);
	    pLD2.setPrefSize(140, 370);
	    
	   
		tabLD1.setContent(pLD1);
		tabLD2.setContent(pLD2);
		
		tpLateralEsquera.getTabs().addAll(tabLE1, tabLE2);
		tpLateralDireita.getTabs().addAll(tabLD1, tabLD2);
		
		AnchorPane.setTopAnchor(tpLateralEsquera, 200.0);
		AnchorPane.setLeftAnchor(tpLateralEsquera, 10.0);
		
		AnchorPane.setTopAnchor(tpLateralDireita, 200.0);
		AnchorPane.setRightAnchor(tpLateralDireita, 10.0);

	    pSuperior.setPrefSize(600, 25);
	    pSuperior.getStyleClass().add("pane-superior");
	   
	    AnchorPane.setTopAnchor(pSuperior, 0.0);
		AnchorPane.setRightAnchor(pSuperior, 0.0);
		AnchorPane.setLeftAnchor(pSuperior, 0.0);
		
		
		AnchorPane.setTopAnchor(spCentroTabPanes, 100.0);
		AnchorPane.setRightAnchor(spCentroTabPanes, 110.0);
		AnchorPane.setLeftAnchor(spCentroTabPanes, 110.0);
		AnchorPane.setBottomAnchor(spCentroTabPanes, 105.0);
		
		spCentroTabPanes.setDisable(true);
	    StackPane.setAlignment(spCentroTabPanes, Pos.TOP_CENTER);
	    
		paneConversor.setMinSize(1050, 100);
		paneConversor.setMaxSize(1050, 100);
	
		  	cbConverterCoord.setPrefSize(100, 30);
	  		cbConverterCoord.setLayoutX(11);
	  		cbConverterCoord.setLayoutY(53);
	  		cbConverterCoord.getStyleClass().add("classePressedHover");
	  		
		  		pConvCentral.setPrefSize(829, 40);
		  		pConvCentral.setLayoutX(108);
		  		pConvCentral.setLayoutY(48);
			  		
			  		btnConverteCoord.setPrefSize(100, 30);
			  		btnConverteCoord.setLayoutX(937);
			  		btnConverteCoord.setLayoutY(53);
			  		btnConverteCoord.getStyleClass().add("classePressedHover");
			  		
	  		
			  			paneConversor.getChildren().addAll(cbConverterCoord, pConvCentral, btnConverteCoord); 
		  	
						  	olcbMainConverteCoord = FXCollections.observableArrayList(
					    	        " DD ",
					    	        " DMS ",
					    	        " UTM "
					    	    ); 
	
									AnchorPane.setRightAnchor(spConversorCoordenadas, 0.0);
									AnchorPane.setLeftAnchor(spConversorCoordenadas, 0.0);
									AnchorPane.setBottomAnchor(spConversorCoordenadas, 15.0);
		
										spConversorCoordenadas.getChildren().add(paneConversor);
										spConversorCoordenadas.setDisable(false);

											StackPane.setAlignment(paneConversor, Pos.TOP_CENTER);
		    
											
		    inicializarConversorCoordenadas();
			inicializarTabLateralEsquerda();
			inicializarTabLateralDireita();
		    
		apPrincipal.getChildren().addAll( pWebMap, tpLateralEsquera, tpLateralDireita, pSuperior,spCentroTabPanes, spConversorCoordenadas);
		
		apPrincipal.getStylesheets().add("/css/principal_mapa_azul.css");
			
	}
	
	
	private void inicializarConversorCoordenadas () {
		
		olcbMainConverteCoord = FXCollections.observableArrayList(
    	        " DD ",
    	        " DMS ",
    	        " UTM "
    	    ); 
		
		cbConverterCoord.setItems(olcbMainConverteCoord);
		
		cbConverterCoord.setValue(" DD ");
		
		cbConverterCoord.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {  
            	
                if (newValue == " DD ") {
                	
                	tfLatDD = new TextField("-15");
                	tfLatDD.setPrefSize(193.0, 30.0);
                	tfLatDD.setLayoutX(11.0); 
                	tfLatDD.setLayoutY(6.0);
	                	
	                	tfLonDD = new TextField("-47"); 
	                	tfLonDD.setPrefSize(193.0, 30.0);
	                	tfLonDD.setLayoutX(216.0); 
	                	tfLonDD.setLayoutY(6.0);
                	
		                	lblCoord1 = new Label();
		                	lblCoord1.setAlignment(Pos.CENTER); 
		                	lblCoord1.setPrefSize(193.0, 30.0);
		                	lblCoord1.setLayoutX(421.0);
		                	lblCoord1.setLayoutY(6.0);
		                	lblCoord1.setStyle("-fx-background-color: white;");
                	
			                	lblCoord2 = new Label();
			                	lblCoord2.setAlignment(Pos.CENTER);
			                	lblCoord2.setPrefSize(193.0, 30.0);
			                	lblCoord2.setLayoutX(626.0); 
			                	lblCoord2.setLayoutY(6.0);
			                	lblCoord2.setStyle("-fx-background-color: white;");
                	
				                	pConvCentral.getChildren().clear();
				                	pConvCentral.getChildren().addAll(tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
				                	
				                		btnConverteCoord.setOnAction((ActionEvent evt)->{
                    	
				                			String typeCoordinate = cbConverterCoord.getValue();
                		
				                				googleMaps.convDD (typeCoordinate, tfLatDD.getText(), tfLonDD.getText());
            			
							            			copiarCoord (lblCoord1);
							            			copiarCoord (lblCoord2);
                        
                    });
                	
                }
                
                if (newValue == " UTM ") {
                	
                	cbNorteSulOpcoes = FXCollections.observableArrayList(
                	        "N",
                	        "S"
                	    );

                	cbNorteSul = new ComboBox<>(cbNorteSulOpcoes);
                	cbNorteSul.setValue("S");
                	
	                	cbNorteSul.setPrefSize(65.0, 30.0);
	                	cbNorteSul.setLayoutX(11.0);
	                	cbNorteSul.setLayoutY(6.0);
	                	cbNorteSul.getStyleClass().add("classePressedHover");
	                	
		                	tfZonaUTM = new TextField("23"); 
		                	tfZonaUTM.setPrefSize(65.0, 30.0);
		                	tfZonaUTM.setLayoutX(88.0); 
		                	tfZonaUTM.setLayoutY(6.0);
	                	
			                	tfLatDD = new TextField("284947"); 
			                	tfLatDD.setPrefSize(150.0, 30.0);
			                	tfLatDD.setLayoutX(165.0); 
			                	tfLatDD.setLayoutY(6.0);
	                	
				                	tfLonDD = new TextField("8340702"); 
				                	tfLonDD.setPrefSize(150, 30);
				                	tfLonDD.setLayoutX(326.0); 
				                	tfLonDD.setLayoutY(6.0);
	                	
					                	lblCoord1 = new Label(); 
					                	lblCoord1.setAlignment(Pos.CENTER);
					                	lblCoord1.setPrefSize(160, 30);
					                	lblCoord1.setLayoutX(487.0);
					                	lblCoord1.setLayoutY(6.0);
					                	lblCoord1.setStyle("-fx-background-color: white;");
	                	
						                	lblCoord2 = new Label();
						                	lblCoord2.setAlignment(Pos.CENTER);
						                	lblCoord2.setPrefSize(160, 30);
						                	lblCoord2.setLayoutX(658.0); 
						                	lblCoord2.setLayoutY(6.0);
						                	lblCoord2.setStyle("-fx-background-color: white;");
	                	
							                	pConvCentral.getChildren().clear();
							                	pConvCentral.getChildren().addAll(cbNorteSul, tfZonaUTM, tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
	                	
	                	btnConverteCoord.setOnAction((ActionEvent evt)->{
	                		
	                		String typeCoordinate = cbConverterCoord.getValue();
	                		String utmLatLon = tfZonaUTM.getText() + " " + cbNorteSul.getValue() + " " + tfLatDD.getText() + " " + tfLonDD.getText();
	                		
	                		googleMaps.convUTM(typeCoordinate, utmLatLon);
	                		copiarCoord(lblCoord1);
	            			copiarCoord(lblCoord2);
                		
                    });
                }
                
                if (newValue == " DMS ") {
                	
                	cbNorteSulOpcoes = FXCollections.observableArrayList(
                	        "N",
                	        "S"
                	    );
                	

                	cbNorteSul = new ComboBox<>(cbNorteSulOpcoes);
                	cbNorteSul.setValue("S");
                	
                	cbNorteSul.setPrefSize(65.0, 30.0);
                	cbNorteSul.setLayoutX(173.0);
                	cbNorteSul.setLayoutY(6.0);
                	cbNorteSul.getStyleClass().add("classePressedHover");
                	
                	
                	cbLesteOesteOpcoes = FXCollections.observableArrayList(
                	        "E",
                	        "W"
                	    );

                	cbLesteOeste = new ComboBox<>(cbLesteOesteOpcoes);
                	cbLesteOeste.setValue("W");
                	
                	cbLesteOeste.setPrefSize(65.0, 30.0); 
                	cbLesteOeste.setLayoutX(410.0);
                	cbLesteOeste.setLayoutY(6.0);
                	cbLesteOeste.getStyleClass().add("classePressedHover");
                	
                	
                	tfLatDD = new TextField("15 00 00");
                	tfLatDD.setPrefSize(150.0, 30.0);
                	tfLatDD.setLayoutX(12.0); 
                	tfLatDD.setLayoutY(6.0);
                	
                	tfLonDD = new TextField("47 00 00");
                	tfLonDD.setPrefSize(150, 30);
                	tfLonDD.setLayoutX(249.0); 
                	tfLonDD.setLayoutY(6.0);
                	
                	lblCoord1 = new Label(); 
                	lblCoord1.setAlignment(Pos.CENTER);
                	lblCoord1.setPrefSize(160, 30);
                	lblCoord1.setLayoutX(487.0);
                	lblCoord1.setLayoutY(6.0);
                	lblCoord1.setStyle("-fx-background-color: white;");
                	
                	lblCoord2 = new Label();
                	lblCoord2.setAlignment(Pos.CENTER);
                	lblCoord2.setPrefSize(160, 30);
                	lblCoord2.setLayoutX(657.0); 
                	lblCoord2.setLayoutY(6.0);
                	lblCoord2.setStyle("-fx-background-color: white;");
                	
                	pConvCentral.getChildren().clear();
                	pConvCentral.getChildren().addAll(tfLatDD, cbNorteSul,tfLonDD, cbLesteOeste, lblCoord1, lblCoord2 );
                	
                	btnConverteCoord.setOnAction((ActionEvent evt)->{
                		
                		String typeCoordinate = cbConverterCoord.getValue();
                		
                		String lat = tfLatDD.getText() + " " + cbNorteSul.getValue();
                		String lon = tfLonDD.getText() + " " + cbLesteOeste.getValue();
                		
                		googleMaps.convDMS (typeCoordinate, lat, lon);
                		copiarCoord(lblCoord1);
            			copiarCoord(lblCoord2);
                		
                    });
                }
                
             
            }    
        });
		

    	tfLatDD = new TextField("-15");
    	tfLatDD.setPrefSize(193.0, 30.0);
    	tfLatDD.setLayoutX(11.0); 
    	tfLatDD.setLayoutY(6.0);
    	
    	tfLonDD = new TextField("-47");
    	tfLonDD.setPrefSize(193.0, 30.0);
    	tfLonDD.setLayoutX(216.0); 
    	tfLonDD.setLayoutY(6.0);
    	
    	lblCoord1 = new Label();
    	lblCoord1.setAlignment(Pos.CENTER);
    	lblCoord1.setPrefSize(193.0, 30.0);
    	lblCoord1.setLayoutX(421.0);
    	lblCoord1.setLayoutY(6.0);
    	lblCoord1.setStyle("-fx-background-color: white;");
    	
    	lblCoord2 = new Label();
    	lblCoord2.setAlignment(Pos.CENTER);
    	lblCoord2.setPrefSize(193.0, 30.0);
    	lblCoord2.setLayoutX(626.0); 
    	lblCoord2.setLayoutY(6.0);
    	lblCoord2.setStyle("-fx-background-color: white;");
    	
    	pConvCentral.getChildren().clear();
    	pConvCentral.getChildren().addAll(tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
    	
    	lblDD = new Label();
		lblDD.setPrefSize(246.0, 30.0);
		lblDD.setLayoutX(126.0); 
		lblDD.setLayoutY(13.0);
		lblDD.setAlignment(Pos.CENTER); 
		lblDD.setStyle("-fx-background-color: white;");
    	
		lblDMS = new Label();
		lblDMS.setPrefSize(246.0, 30.0);
		lblDMS.setLayoutX(401.0); 
		lblDMS.setLayoutY(13.0);
		lblDMS.setAlignment(Pos.CENTER); 
		lblDMS.setStyle("-fx-background-color: white;");
		
		lblUTM = new Label();
		lblUTM.setPrefSize(246.0, 30.0);
		lblUTM.setLayoutX(676.0); 
		lblUTM.setLayoutY(13.0);
		lblUTM.setAlignment(Pos.CENTER); 
		lblUTM.setStyle("-fx-background-color: white;");
		
		copiarCoord (lblDD);
		copiarCoord (lblDMS);
		copiarCoord (lblUTM);
		
		paneConversor.getChildren().addAll(lblDD, lblDMS, lblUTM);
    	
		btnConverteCoord.setOnAction((ActionEvent evt)->{
        	
    		String typeCoordinate = cbConverterCoord.getValue();
    		
			googleMaps.convDD (typeCoordinate, tfLatDD.getText(), tfLonDD.getText());
			
			copiarCoord (lblCoord1);
			copiarCoord (lblCoord2);
            
        });
	}
	
	
	private void inicializarTabLateralEsquerda () {
		
		  btnHome = GlyphsDude.createIconButton(
	        		FontAwesomeIcon.HOME,
	        		"HOME", 
	        		"20px", 
	                "11px",  
	                ContentDisplay.LEFT);
	        
		  		btnHome.getStyleClass().add("button-lateral");
		        btnHome.setLayoutX(10.0);
		        btnHome.setLayoutY(16.0);
		        
		  btnSEI = GlyphsDude.createIconButton(
        		FontAwesomeIcon.CHROME,
        		"SEI - GDF", 
        		"15px", 
                "11px",  
                ContentDisplay.LEFT);
	        
		        btnSEI.getStyleClass().add("button-lateral");
		        btnSEI.setLayoutX(10.0);
		        btnSEI.setLayoutY(55.0);
	        
		  btnAtendimento = GlyphsDude.createIconButton(
        		FontAwesomeIcon.PHONE_SQUARE,
        		"ATENDIMENTO", 
        		"20px", 
                "11px",   
                ContentDisplay.LEFT);
	        
		       	btnAtendimento.getStyleClass().add("button-lateral");
		        btnAtendimento.setLayoutX(10.0);
		        btnAtendimento.setLayoutY(94.0);
	        
	       btnFiscalizacao = GlyphsDude.createIconButton(
	        		FontAwesomeIcon.TICKET,
	        		"FISCALIZAÇÃO", 
	        		"20px", 
	                "11px",  
	                ContentDisplay.LEFT);
	        
		        btnFiscalizacao.getStyleClass().add("button-lateral");
		        btnFiscalizacao.setLayoutX(10.0);
		        btnFiscalizacao.setLayoutY(133.0);
	        
	        btnEditorHTML = GlyphsDude.createIconButton(
	           		FontAwesomeIcon.HTML5,
	           		"EDITOR HTML", 
	           		"20px", 
	                   "11px",  
	                   ContentDisplay.LEFT);
	           
	           btnEditorHTML.getStyleClass().add("button-lateral");
	           btnEditorHTML.setLayoutX(10.0);
	           btnEditorHTML.setLayoutY(172.0);
	           
	        
	        btnConversor = GlyphsDude.createIconButton(
	        		FontAwesomeIcon.GLOBE,
	        		"CONVERSOR", 
	        		"20px", 
	                "11px",   
	                ContentDisplay.LEFT);
	        
			        btnConversor.getStyleClass().add("button-lateral");
			        btnConversor.setLayoutX(10.0);
			        btnConversor.setLayoutY(211.0);
		        
		        Text iconTabHome = GlyphsDude.createIcon(FontAwesomeIcon.HOME, "20px");
		        //iconTabHome.getStyleClass().add("classeIconTab");
		        
		        
			        tabLE1.setGraphic(iconTabHome);
			        tabLE1.setClosable(false);
		      
		        Text iconTabHome2 = GlyphsDude.createIcon(FontAwesomeIcon.CROP, "20px");
		        //iconTabHome2.getStyleClass().add("classeIconTab");
		        
			        tabLE2.setGraphic(iconTabHome2);
			        tabLE2.setClosable(false);
		       
			        pLE1.getStyleClass().add("pane-lateral");
			        
			        pLE2.getStyleClass().add("pane-lateral");
			        
			        pLE1.getChildren().addAll(btnHome, btnSEI, btnAtendimento, btnFiscalizacao, btnConversor, btnEditorHTML);
		        
		        tabLE1.setContent(pLE1);
		        tabLE2.setContent(pLE2);
		        //aaqq
		        
		    
		        // traslalte transition do conversor de coordenadas, para sumir deslizando para baixo
		        downSearch = new TranslateTransition(new Duration(350), (spConversorCoordenadas));
		        downSearch.setToY(130.0);
		        upSearch = new TranslateTransition(new Duration(350), spConversorCoordenadas);
		        upSearch.setToY(2.0);
		     
		        btnAtendimento.setOnAction((ActionEvent evt)->{
		        	
		        	if (pAtendimento == null) {
		        		
		        		pAtendimento = new Pane();
		        		
			        		downAtend = new TranslateTransition(new Duration(350), pAtendimento);
			        		downAtend.setToY(880.0);
			                upAtend = new TranslateTransition(new Duration(350), pAtendimento);
			                upAtend.setToY(0.0);
		        
			        	    AnchorPane.setTopAnchor(pAtendimento, 150.0);
			        	    AnchorPane.setLeftAnchor(pAtendimento, 160.0);
			        	    AnchorPane.setRightAnchor(pAtendimento, 160.0);
			        	    AnchorPane.setBottomAnchor(pAtendimento, 115.0);
			        	    
			        	    // Para abrir o pane fora do campo de vis�o
			        	    pAtendimento.setTranslateY(880.0);

			        	    apPrincipal.getChildren().add(pAtendimento);
		        	    
		        		
		        	}
		        	
		        	dblAtend =  pAtendimento.getTranslateY();
		        	
			        	if(dblAtend.equals(0.0)){
			            	
			            	downAtend.play(); 
			            	if (pBrowserSEI != null)
			            	pBrowserSEI.setTranslateY(880.0);
			            	if (pFiscalizacao != null)
		            			pFiscalizacao.setTranslateY(880.0);
			        		} 
			            	
			            	else {
			            			
			            		upAtend.play();
			            		if (pBrowserSEI != null)
			            		pBrowserSEI.setTranslateY(880.0);
			            		if (pFiscalizacao != null)
			            			pFiscalizacao.setTranslateY(880.0);
			            		}
			        	
			        			
		        	
						        	if (pAtenCadastros == null) {
						
						        		pAtenCadastros = new Pane();
							        	
							        	controladorAtendimento = new ControladorAtendimento();
							        	
							        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/atendimento/Atendimento.fxml"));
										
										loader.setRoot(pAtenCadastros);
										loader.setController(controladorAtendimento);
										try {
											loader.load();
										} catch (IOException e) {
											System.out.println("erro na abertura do pane atendimento");
											e.printStackTrace();
										}
										
										pAtenCadastros.minWidthProperty().bind(pAtendimento.widthProperty());
										pAtenCadastros.minHeightProperty().bind(pAtendimento.heightProperty());
										
										pAtenCadastros.maxWidthProperty().bind(pAtendimento.widthProperty());
										pAtenCadastros.maxHeightProperty().bind(pAtendimento.heightProperty());
										
										pAtenCadastros.setStyle("-fx-background-color: transparent;");
										
										pAtendimento.getChildren().add(pAtenCadastros);
										
						        		}
		        	});
		        
		        btnFiscalizacao.setOnAction((ActionEvent evt)->{
		        	
		        	if (pFiscalizacao == null) {
		        		
		        		pFiscalizacao = new Pane();
		        		
			        		downFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
			                downFiscal.setToY(880.0);
			                upFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
			                upFiscal.setToY(0.0);
		        
			        	    AnchorPane.setTopAnchor(pFiscalizacao, 150.0);
			        	    AnchorPane.setLeftAnchor(pFiscalizacao, 160.0);
			        	    AnchorPane.setRightAnchor(pFiscalizacao, 160.0);
			        	    AnchorPane.setBottomAnchor(pFiscalizacao, 115.0);
			        	    
			        	    // Para abrir o pane fora do campo de vis�o
			        	    pFiscalizacao.setTranslateY(880.0);

			        	    apPrincipal.getChildren().add(pFiscalizacao);
		        	    
		        		
		        	}
		        	
		        	dblFiscal =  pFiscalizacao.getTranslateY();
		        	
			        	if(dblFiscal.equals(0.0)){
			            	
			            	downFiscal.play(); 
			            	if (pBrowserSEI != null)
			            	pBrowserSEI.setTranslateY(880.0);
			            	if (pAtendimento != null)
			            		pAtendimento.setTranslateY(880.0);
			        		} 
			            	
			            	else {
			            			
			            		upFiscal.play();
			            		if (pBrowserSEI != null)
			            		pBrowserSEI.setTranslateY(880.0);
			            		if (pAtendimento != null)
			            			pAtendimento.setTranslateY(880.0);
				        		
			            		}
		        	
						        	if (pFiscCadastros == null) {
						
						        		pFiscCadastros = new Pane();
							        	
							        	controladorFiscalizacao = new ControladorFiscalizacao();
							        	
							        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/Fiscalizacao.fxml"));
										
										loader.setRoot(pFiscCadastros);
										loader.setController(controladorFiscalizacao);
										try {
											loader.load();
										} catch (IOException e) {
											System.out.println("erro na abertura do pane fiscalizacao");
											e.printStackTrace();
										}
										
										pFiscCadastros.minWidthProperty().bind(pFiscalizacao.widthProperty());
										pFiscCadastros.minHeightProperty().bind(pFiscalizacao.heightProperty());
										
										pFiscCadastros.maxWidthProperty().bind(pFiscalizacao.widthProperty());
										pFiscCadastros.maxHeightProperty().bind(pFiscalizacao.heightProperty());
										
										pFiscCadastros.setStyle("-fx-background-color: transparent;"); 
										
										pFiscalizacao.getChildren().add(pFiscCadastros);
										
						        		}
		        	});
						    
		        btnConversor.setOnAction((ActionEvent evt)->{
		        	
		        	dblSearch =  spConversorCoordenadas.getTranslateY();
		        	
		            if(dblSearch.equals(0.0) || dblSearch.equals(2.0)){
		            	
		                downSearch.play();
			            
		            	} else {
			            	
			            upSearch.play();
			            	  
			            }
		            
		        });
		        
		        btnSEI.setOnAction((ActionEvent evt)->{
		        	
		        	
		        	if (wBrowser == null ) {
		        		
		        		spWebBrowser = new ScrollPane();
		        		pBrowserSEI = new Pane();
		        		
		        		downBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
			            downBrowser.setToY(880.0);
			            upBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
			            upBrowser.setToY(0.0);
			        	
			        	AnchorPane.setTopAnchor(pBrowserSEI, 150.0);
			    	    AnchorPane.setLeftAnchor(pBrowserSEI, 160.0);
			    	    AnchorPane.setRightAnchor(pBrowserSEI, 160.0);
			    	    AnchorPane.setBottomAnchor(pBrowserSEI, 115.0);

			    	    pBrowserSEI.setTranslateY(880.0);
			    	    
			    	    apPrincipal.getChildren().add(pBrowserSEI);

			        	wBrowser = new WebView();
			        	WebEngine weBrowser = wBrowser.getEngine();
			        	weBrowser.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");
			        	
			        	spWebBrowser.setContent(wBrowser);
			        	
			        	spWebBrowser.prefWidthProperty().bind((pBrowserSEI.widthProperty()));
			        	spWebBrowser.prefHeightProperty().bind(pBrowserSEI.heightProperty());
			        	
			        	wBrowser.prefWidthProperty().bind(spWebBrowser.widthProperty().subtract(10.0));
			        	wBrowser.prefHeightProperty().bind(spWebBrowser.heightProperty().subtract(10.0));
			
			        	wBrowser.maxWidthProperty().bind(spWebBrowser.widthProperty());
			        	wBrowser.maxHeightProperty().bind(spWebBrowser.heightProperty());
			        	
			        	spWebBrowser.maxWidthProperty().bind(pBrowserSEI.widthProperty());
			        	spWebBrowser.maxHeightProperty().bind(pBrowserSEI.heightProperty());
			        	
			        	pBrowserSEI.getChildren().add(spWebBrowser);
		        	
		        	}
		        	
		        	
		        	dblBrowser =  pBrowserSEI.getTranslateY();
		        	
		        	
		        	if(dblBrowser.equals(0.0)){
		            	
		            	downBrowser.play();
		            	
		            	if (pFiscalizacao != null)
		            	pFiscalizacao.setTranslateY(880.0);//downFiscal.play();
		            	if (pAtendimento != null)
		            		pAtendimento.setTranslateY(880.0);
		        		
			            } else {
			            
			            	upBrowser.play();
			            	if (pFiscalizacao != null)
			            	pFiscalizacao.setTranslateY(880.0);
			            	if (pAtendimento != null)
			            		pAtendimento.setTranslateY(880.0);
			            
			            }
			            
		        });
		        
		        btnEditorHTML.setOnAction((ActionEvent evt)->{
		        	
		        	Pane pEndereco = new Pane();
		        	controladorModelosHTML = new ControladorModelosHTML();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modelosHTML/ModelosHTML.fxml"));
					loader.setRoot(pEndereco);
					loader.setController(controladorModelosHTML);
					
					try {
						loader.load();
					} catch (IOException e) {
						System.out.println("erro leitura do pane - chamada legislação");
						e.printStackTrace();
					}
					
					Scene scene = new Scene(pEndereco);
					Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
					stage.setWidth(1177);
					stage.setHeight(800);
			        stage.setScene(scene);
			        stage.setMaximized(false);
			        stage.setResizable(false);
			        stage.setAlwaysOnTop(true); 
			        stage.show();
		        	
		        });
		        
	}
	
	
	private void inicializarTabLateralDireita () {
		
		
        // botao de aumentar o zoom //
        btnZoomIn = GlyphsDude.createIconButton(
        		FontAwesomeIcon.PLUS,
        		"", 
        		"10px", 
                "9px",  
                ContentDisplay.TOP);
        	btnZoomIn.getStyleClass().add("button-lateral");
        
	        btnZoomIn.setOnAction((ActionEvent evt)->{
	        	googleMaps.setZoomIn();
	        });
        
	        btnZoomIn.setLayoutX(10.0);
	        btnZoomIn.setLayoutY(290.0);
	        
        // botao de diminuir o zoom //
        btnZoomOut = GlyphsDude.createIconButton(
        		FontAwesomeIcon.MINUS,
        		"", 
        		"10px", 
                "9px",  
                ContentDisplay.TOP);
        	btnZoomOut.getStyleClass().add("button-lateral");
        
	        btnZoomOut.setOnAction((ActionEvent evt)->{
	        	googleMaps.setZoomOut();
	        });
	        
	        btnZoomOut.setLayoutX(10.0);
	        btnZoomOut.setLayoutY(327.0);
        
        Label lblTipoMapa = new Label("Tipo de Mapa");
        	lblTipoMapa.setLayoutX(28.0);
        		lblTipoMapa.setLayoutY(11.0);
        		lblTipoMapa.getStyleClass().add("classeLabel");
        		
        		Label lblEstiloMapa = new Label("Estilo de Mapa");
        			lblEstiloMapa.setLayoutX(26.0);
        				lblEstiloMapa.setLayoutY(189.0);
        				lblEstiloMapa.getStyleClass().add("classeLabel");
    	
       btnTerrain = new Button("TERRENO");
       btnRoadMap = new Button("RODOVIAS");
       btnSattelite = new Button("SATÉLITE");
       btnHybrid = new Button("HÍBRIDO");
    	
   	btnBlueMap = new Button();
		btnNightMap = new Button();
			btnGreenMap = new Button();
			btnRetroMap = new Button();

	btnNightMap.setId("btnNight");
		btnBlueMap.setId("btnBlueMap");
	    	btnGreenMap.setId("btnGreenMap");
	    		btnRetroMap.setId("btnRetroMap");
	    		
	    		btnNightMap.getStyleClass().add("estilo-mapa");
		    	btnBlueMap.getStyleClass().add("estilo-mapa");
			    	btnGreenMap.getStyleClass().add("estilo-mapa");
			    		btnRetroMap.getStyleClass().add("estilo-mapa");
		    		
	
							btnBlueMap.setLayoutX(10.0);
							btnBlueMap.setLayoutY(216.0);
							
								btnNightMap.setLayoutX(41.0);
								btnNightMap.setLayoutY(216.0);
								
							    	btnGreenMap.setLayoutX(72.0);
							    	btnGreenMap.setLayoutY(216.0);
							    	
								    	btnRetroMap.setLayoutX(103.0);
								    	btnRetroMap.setLayoutY(216.0);
							    	
							    	btnNightMap.setOnAction((ActionEvent evt)->{
							        	googleMaps.mudarEstiloMapa(1);
							        	apPrincipal.getStylesheets().clear();
							        	apPrincipal.getStylesheets().add("/css/principal_mapa_escuro.css");
							      
			        	
			        });	
			    	
				    	btnBlueMap.setOnAction((ActionEvent evt)->{
				        	googleMaps.mudarEstiloMapa(2);
				        	apPrincipal.getStylesheets().clear();
				        	apPrincipal.getStylesheets().add("/css/principal_mapa_azul.css");
				        	
				        });	 
			    	
				    		btnGreenMap.setOnAction((ActionEvent evt)->{
					        	googleMaps.mudarEstiloMapa(3);
					        	
					        	apPrincipal.getStylesheets().clear();
					        	apPrincipal.getStylesheets().add("/css/principal_mapa_verde.css");
					        	
					        });	    
				    			btnRetroMap.setOnAction((ActionEvent evt)->{
						        	googleMaps.mudarEstiloMapa(4);
						        	
						        	apPrincipal.getStylesheets().clear();
						        	apPrincipal.getStylesheets().add("/css/principal_mapa_salmao.css");
						        });	    	
				    			 
    	
    	btnTerrain.setLayoutX(10.0);
    		btnTerrain.setLayoutY(40.0);
    	
    			btnTerrain.getStyleClass().add("button-lateral");
        
    	btnRoadMap.setLayoutX(10.0);
    		btnRoadMap.setLayoutY(75.0);
    	
    			btnRoadMap.getStyleClass().add("button-lateral");
        
    	btnSattelite.setLayoutX(10.0);
    		btnSattelite.setLayoutY(110.0);
    	
    			btnSattelite.getStyleClass().add("button-lateral");
        
    	btnHybrid.setLayoutX(10.0);
    		btnHybrid.setLayoutY(145.0);
    	
    			btnHybrid.getStyleClass().add("button-lateral");
    	
    	checkBacia = new CheckBox("Bacias");
    		checkRiodDF  = new CheckBox("Rios do DF");
    			checkRiosUniao  = new CheckBox("Rios da União");
    				checkFraturado  = new CheckBox("Fraturado");
				    	checkPoroso  = new CheckBox("Poroso");
					    	checkUTM  = new CheckBox("UTM");
					    		checkTrafego  = new CheckBox("Tráfego");
    	
    	Pane pCheck = new Pane ();
	    	pCheck.setPrefSize(120, 345);
	    	pCheck.setLayoutX(10.0);
	    	pCheck.setLayoutY(10.0);
    	
		    	pCheck.setStyle("-fx-background-color: white;");
		    	pCheck.getChildren().addAll(checkBacia, checkRiodDF, checkRiosUniao,  checkFraturado, checkPoroso,checkUTM, checkTrafego);
		    	
    	checkBacia.setLayoutX(5.0);
    	checkBacia.setLayoutY(15.0);
    	
	    	checkRiodDF.setLayoutX(5.0);
	    	checkRiodDF.setLayoutY(40.0);
    	
		    	checkRiosUniao.setLayoutX(5.0);
		    	checkRiosUniao.setLayoutY(65.0);
    	
			    	checkFraturado.setLayoutX(5.0);
			    	checkFraturado.setLayoutY(90.0);
			    	
				    	checkPoroso.setLayoutX(5.0);
				    	checkPoroso.setLayoutY(115.0);
				    	
					    	checkUTM.setLayoutX(5.0);
					    	checkUTM.setLayoutY(140.0);
					    	
						    	checkTrafego.setLayoutX(5.0);
						    	checkTrafego.setLayoutY(165.0);

						    	
						    	//fontAwesomeIconView.setStyleClass("thumbs-up-icon");  //.setStyleClass("thumbs-up-icon");
						        
    	FontAwesomeIconView mapIcon = 
    			  new FontAwesomeIconView(FontAwesomeIcon.MAP);	
    	
    					mapIcon.setSize("20px");

    						Text txtMapIcon = mapIcon;
   
    	FontAwesomeIconView shapeIcon = 
  			  new FontAwesomeIconView(FontAwesomeIcon.OBJECT_GROUP);	
    	
    			shapeIcon.setSize("20px");
    	
    				Text txtShapeIcon= shapeIcon;
    	
    	tabLD1.setGraphic(txtMapIcon);
    		tabLD1.setClosable(false);
      
        tabLD2.setGraphic(txtShapeIcon);
        
        	tabLD2.setClosable(false);
       
        	pLD1.getStyleClass().add("pane-lateral");
        
	        pLD1.getChildren().addAll(
        		lblTipoMapa, 
        		btnTerrain, btnRoadMap, btnSattelite, btnHybrid, 
        		lblEstiloMapa, btnNightMap, btnBlueMap, btnGreenMap, btnRetroMap,
        		btnZoomIn, btnZoomOut);
	        
	        pLD2.getStyleClass().add("pane-lateral");
	        pLD2.getChildren().addAll(pCheck);
        
        tabLD1.setContent(pLD1);
        tabLD2.setContent(pLD2);
        
        checkBacia.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(6);
        });
        
        checkRiodDF.setOnAction((ActionEvent evt)->{ 
        	googleMaps.openShape(1);
        });
        
        checkRiosUniao.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(4);
        });
        
        checkFraturado.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(2);
        });
        
        checkPoroso.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(3);
        });
        
        checkUTM.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(5);
        });
        
        checkTrafego.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(7);
        });
        
        
        btnTerrain.setOnAction((ActionEvent evt)->{
        	googleMaps.switchTerrain();
        });
        
        btnRoadMap.setOnAction((ActionEvent evt)->{
        	googleMaps.switchRoadmap();
        });
        
        btnSattelite.setOnAction((ActionEvent evt)->{
        	googleMaps.switchSatellite();
        });
        
        btnHybrid.setOnAction((ActionEvent evt)->{
        	googleMaps.switchHybrid();
        });
        
	}
	
	// ao clicar copiar o valor da coordenada para utilizar em navegadores //
	public void copiarCoord (Label lbl) {
		
		lbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent mouseEvent) {
	            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	                if(mouseEvent.getClickCount() == 1){
	                    
	                	Clipboard clip = Clipboard.getSystemClipboard();
	                    ClipboardContent conteudo = new ClipboardContent();
	                    conteudo.putString(lbl.getText());
	                    clip.setContent(conteudo);
	                }
	            }
	        }
	    });
		
	}

}


