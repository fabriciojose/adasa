package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import dao.DocumentoDao;
import entidades.Documento;
import entidades.Endereco;
import entidades.Processo;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mapas.GoogleMap;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;

public class TabDocumentoControlador implements Initializable {
	
	String strPesquisa = "";
	  String strPesquisaProcesso = "";
	  
	  TableView<Documento> tvLista = new TableView<Documento>();
		  TableColumn<Documento, String> tcTipo = new TableColumn<Documento, String>("Tipo");
		  	TableColumn<Documento, String> tcNumeracao = new TableColumn<Documento, String>("Numeração");
		  		TableColumn<Documento, String> tcSEI = new TableColumn<Documento, String>("Número SEI");
		  			TableColumn<Documento, String> tcProcesso = new TableColumn<Documento, String>("Número do Processo");

	  Documento documento = new Documento();
	  Processo processo = new Processo();
	  
	  public void habilitarDocumento () {
	    
		  cbTipo.setValue(null);
		  tfNumeracao.setText("");
		  tfSEI.setText("");
		  tfProcesso.setText("");
	    
		  dpDataDistribuicao.getEditor().clear();
		  dpDataRecebimento.getEditor().clear();
	    
		  dpDataDistribuicao.setDisable(false);
		  dpDataRecebimento.setDisable(false);
	    
		  cbTipo.setDisable(false);
		  tfNumeracao.setDisable(false);
		  tfSEI.setDisable(false);
		  tfProcesso.setDisable(false);
	    
		  btnSalvar.setDisable(false);
	    
		  btnEditar.setDisable(true);
		  btnExcluir.setDisable(true);
		  btnNovo.setDisable(true);
		  
	  }
	  
	  public void salvarDocumento ()	{
	    
		  try {
			  
			      if ((tfSEI.getText().isEmpty()) || 
			        (tfProcesso.getText().isEmpty()))
			      {
			        Alerta a = new Alerta();
			        a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Documento, Processo SEI!!!", new ButtonType[] { ButtonType.OK }));
			      }
			      else
			      {
			        Documento doc = new Documento();
			        
			        doc.setDocTipo(cbTipo.getValue());
			        doc.setDocNumeracao(tfNumeracao.getText());
			        doc.setDocSEI(tfSEI.getText());
			        doc.setDocProcesso(tfProcesso.getText());
			        
			        if (dpDataDistribuicao.getValue() == null) {
			        	doc.setDocDataDistribuicao(null);
			        } else {
			        	doc.setDocDataDistribuicao(Date.valueOf((LocalDate)dpDataDistribuicao.getValue()));
			        }
				        if (dpDataRecebimento.getValue() == null) {
				        	doc.setDocDataRecebimento(null);
				        } else {
				        	doc.setDocDataRecebimento(Date.valueOf((LocalDate)dpDataRecebimento.getValue()));
				        }
				        
				        doc.setDocDataAtualizacao(Timestamp.valueOf(LocalDateTime.now()));
			        
			        DocumentoDao docDao = new DocumentoDao();
			        
			        docDao.salvarDocumento(doc);
			        

				    if (intControlador == 0) {
				    	TabEnderecoControlador.controladorAtendimento.setDocumento(doc);
			
				 	}
				     
				    if (intControlador == 1) {
				 		TabEnderecoControlador.controladorFiscalizacao.setDocumento(doc);
				    }
				    
				    if (intControlador == 2) {
			 			TabEnderecoControlador.controladorOutorga.setDocumento(doc);
				    }
					
			        obsList.add(doc);
			        
			        modularBotoes ();
			        
			        Alerta a = new Alerta();
			        a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", new ButtonType[] { ButtonType.OK }));
			      }
			      
		    	}
		  
			  catch (Exception ex)	{
				  System.out.println("Erro: " + ex);
				  ex.printStackTrace();
	      
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.ERROR, "erro na conexão, tente novamente!", new ButtonType[] { ButtonType.OK }));
	      
	    }
		  
	  }
	  
	  public void editarDocumento ()	{
		  
		    if (cbTipo.isDisable()) {
		    	
		      cbTipo.setDisable(false);
		      tfNumeracao.setDisable(false);
		      tfSEI.setDisable(false);
		      tfProcesso.setDisable(false);
		      
		      dpDataDistribuicao.setDisable(false);
		      dpDataRecebimento.setDisable(false);
		      
		    }
		    
			    else if ((tfSEI.getText().isEmpty()) || (tfProcesso.getText().isEmpty())) {
			    	
			      Alerta a = new Alerta();
			      a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Documento, Processo SEI!!!", new ButtonType[] { ButtonType.OK }));
			      
			    }
		    
				    else
				    {
				    	Documento doc = (Documento)tvLista.getSelectionModel().getSelectedItem();
				      
				    	doc.setDocTipo(cbTipo.getValue());
				    	doc.setDocNumeracao(tfNumeracao.getText());
				    	doc.setDocSEI(tfSEI.getText());
				    	doc.setDocProcesso(tfProcesso.getText());
				        
					      if (dpDataDistribuicao.getValue() == null) {
					    	  doc.setDocDataDistribuicao(null);
					      } else {
					    	  doc.setDocDataDistribuicao(Date.valueOf((LocalDate)dpDataDistribuicao.getValue()));
					      }
						      if (dpDataRecebimento.getValue() == null) {
						    	  doc.setDocDataRecebimento(null);
						      } else {
						    	  doc.setDocDataRecebimento(Date.valueOf((LocalDate)dpDataRecebimento.getValue()));
						      }
				      
						      doc.setDocDataAtualizacao(Timestamp.valueOf(LocalDateTime.now()));
				      
				      DocumentoDao dDao = new DocumentoDao();
				      
				      dDao.mergeDocumento(doc);
				      
				      obsList.remove(doc);
				      obsList.add(doc);
				     
				     /* transmitir demanda para a tab endereco */
				   
				    if (intControlador == 0) {
				    	TabEnderecoControlador.controladorAtendimento.setDocumento(doc);
			
				 	}
				     
				    if (intControlador == 1) {
				 		TabEnderecoControlador.controladorFiscalizacao.setDocumento(doc);
				    }
				    
				    if (intControlador == 2) {
			 			TabEnderecoControlador.controladorOutorga.setDocumento(doc);
				    }
					
				    modularBotoes();
				      
				    Alerta a = new Alerta();
				    a.alertar(new Alert(Alert.AlertType.ERROR, "Cadastro editado com sucesso!!!", new ButtonType[] { ButtonType.OK }));
				    
				    } // fim else
		    
	  }
	  
	  public void excluirDocumento ()	{
	   
		  try
			    {
			  		
		  	Documento doc = (Documento)tvLista.getSelectionModel().getSelectedItem();
		      
		    DocumentoDao docDao = new DocumentoDao();
		      
			docDao.removerDocumento(doc.getDocID());
			      
			obsList.remove(doc);
			      
			modularBotoes();
			      
			      Alerta a = new Alerta();
			      a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluído com sucesso!!!", new ButtonType[] { ButtonType.OK }));
			      
			}
		  
		    catch (Exception e)	{
		    	
			      Alerta a = new Alerta();
			      a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao excluir o cadastro!!!", new ButtonType[] { ButtonType.OK }));
		      
		    	}
		  
	  }
	  
	  public void cancelarDocumento ()	{
	   
		  modularBotoes();
	  }
	  
	  public void pesquisarDocumento ()	{
		  
	    strPesquisa = tfPesquisar.getText();
	    
	    listarDocumentos(strPesquisa);
	    
	    modularBotoes();
	    
	  }
	  
	  Label lblDataAtualizacao = new Label();
	  
	  @FXML Pane pDocumento;
	  
	  Pane p1 = new Pane();
	  BorderPane bp1 = new BorderPane();
	  BorderPane bp2 = new BorderPane();
	  ScrollPane sp = new ScrollPane();
	  Pane pMapa = new Pane();
	  
	  GoogleMap googleMaps = new GoogleMap();

	  Double[][] prefSizeWHeLayXY;

	  Pane pProcesso;
	  Label lblProcessoPrincipal;
	  Button btnTelaProcesso;
	  
	  Componentes com;
	  
	  public static TabDocumentoControlador controladorAtendimento;
	  	public static TabDocumentoControlador controladorFiscalizacao;
	  		public static TabDocumentoControlador controladorOutorga;
	  	
	  		int intControlador;
		
	  public TabDocumentoControlador (int i) {
			
			if (i==0) {
				controladorAtendimento = this;
				intControlador = i;
			}
			if(i==1) {
				controladorFiscalizacao = this;
				intControlador = i;
			}
			if(i==2) {
				controladorOutorga = this;
				intControlador = i;
			}
		
		}

	  public void initialize(URL url, ResourceBundle rb) {
		  
	    bp1.minWidthProperty().bind(pDocumento.widthProperty());
	    bp1.maxHeightProperty().bind(pDocumento.heightProperty().subtract(60));
	    
	    bp1.getStyleClass().add("border-pane");
	    
	    bp2.setPrefHeight(800.0D);
	    bp2.minWidthProperty().bind(pDocumento.widthProperty());
	    
	    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    
	    sp.setContent(bp2);
	    
	    bp1.setCenter(sp);
	    
	    pDocumento.getChildren().add(bp1);
	    
	    p1.setMaxSize(980.0, 1000.0);
	    p1.setMinSize(980.0, 1000.0);
	    
	    bp2.setTop(p1);
	    BorderPane.setAlignment(p1, Pos.CENTER);
	    
	    lblDataAtualizacao.setPrefSize(247.0, 22.0);
	    lblDataAtualizacao.setLayoutX(707.0);
	    lblDataAtualizacao.setLayoutY(510.0);
	    
	    tcTipo.setCellValueFactory(new PropertyValueFactory<Documento, String>("docTipo"));
	    tcNumeracao.setCellValueFactory(new PropertyValueFactory<Documento, String>("docNumeracao"));
	    tcSEI.setCellValueFactory(new PropertyValueFactory<Documento, String>("docSEI"));
	    tcProcesso.setCellValueFactory(new PropertyValueFactory<Documento, String>("docProcesso"));
	    
	    tcTipo.setPrefWidth(255.0);
	    	tcNumeracao.setPrefWidth(210.0);
	    		tcSEI.setPrefWidth(220.0);
	    			tcProcesso.setPrefWidth(220.0);
	    
	    tvLista.setPrefSize(930.0, 185.0);
	    	tvLista.setLayoutX(25.0);
	    		tvLista.setLayoutY(316.0);
	    
	    tvLista.getColumns().add(tcTipo); //, tcDocsSEI, tcProcsSEI });
	    	tvLista.getColumns().add(tcNumeracao);
	    		tvLista.getColumns().add(tcSEI);
	    			tvLista.getColumns().add(tcProcesso);
	    
	    tvLista.setItems(obsList);
	    
	    pMapa.setPrefSize(930.0, 400.0);
	    pMapa.setLayoutX(25.0);
	    pMapa.setLayoutY(543.0);
	    pMapa.getStyleClass().add("panes");
	    
	    pMapa.getChildren().add(googleMaps);
	    googleMaps.setWidth(930.0);
	    googleMaps.setHeight(400.0);
	    googleMaps.switchHybrid();
	    
	    p1.getChildren().addAll(tvLista, lblDataAtualizacao, pMapa);
	    
	    listNodesProcesso.add(pProcesso = new Pane());
	    listNodesProcesso.add(new Label("PROCESSO PRINCIPAL:"));
	    listNodesProcesso.add(lblProcessoPrincipal = new Label());
	    listNodesProcesso.add(btnTelaProcesso = new Button("<<<"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	
	    	{950.0,60.0,15.0,10.0},
	    		{150.0,30.0,32.0,15.0},
	    			{655.0,30.0,182.0,15.0},
	    				{70.0,20.0,848.0,19.0},
		};
	    
	    com = new Componentes();
	    com.popularTela(listNodesProcesso, prefSizeWHeLayXY, p1);
	    
	    
	    componentesDocumento.add(pDadosDocumento = new Pane());
		    
		    componentesDocumento.add(new Label("TIPO:"));
		    componentesDocumento.add(cbTipo =  new ComboBox<String>());
		    componentesDocumento.add(new Label("NUMERAÇÃO"));
		    componentesDocumento.add(tfNumeracao = new TextField());
		    componentesDocumento.add(new Label("SEI:"));
		    componentesDocumento.add(tfSEI = new TextField());
		    componentesDocumento.add(new Label("PROCESSO:"));
		    componentesDocumento.add(tfProcesso = new TextField());
			    	
		    componentesDocumento.add(new Label("DATA DE RECEBIMENTO:"));
		    componentesDocumento.add(dpDataRecebimento = new DatePicker());
		    componentesDocumento.add(new Label("DATA DE DISTRIBUIÇÃO:"));
		    componentesDocumento.add(dpDataDistribuicao = new DatePicker());
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	{530.0,150.0,15.0,80.0},
	    	{200.0,30.0,15.0,13.0},
	    	{200.0,30.0,15.0,43.0},
	    	{130.0,30.0,225.0,13.0},
	    	{130.0,30.0,225.0,43.0},
	    	{150.0,30.0,366.0,13.0},
	    	{150.0,30.0,366.0,43.0},
	    	{160.0,30.0,15.0,75.0},
	    	{160.0,30.0,15.0,108.0},
	    	{160.0,30.0,185.0,78.0},
	    	{160.0,30.0,185.0,108.0},
	    	{160.0,30.0,355.0,78.0},
	    	{160.0,30.0,355.0,108.0},
	    	};
	    	
	    com = new Componentes();
	    com.popularTela(componentesDocumento, prefSizeWHeLayXY, p1);
	    
	    ObservableList<String> obsListTipoDocumento = FXCollections
	    		.observableArrayList(
	    				
	    				"Requerimento de Outorga"	,
	    				"Solicitação",
	    				"Memorando"	,
	    				"Ofício"	,
	    				"Carta",
	    				"Recurso",
	    				"Outros"
	    				
	    				); 	
	    
	    cbTipo.setItems(obsListTipoDocumento);
	    
	    componentesEndereco.add(pEndereco = new Pane());
	    
		    componentesEndereco.add(new Label("ENDERECO:"));
		    componentesEndereco.add(lblLogradouro = new Label());
		    componentesEndereco.add(new Label("RA:"));
		    componentesEndereco.add(lblRegiaoAdministrativa = new Label());
		    componentesEndereco.add(new Label("LAT:"));
		    componentesEndereco.add(lblLatitude = new Label());
		    componentesEndereco.add(new Label("LON:"));
		    componentesEndereco.add(lblLongitude = new Label());
		    componentesEndereco.add(btnTelaEndereco = new Button("<<<"));
	    
	    prefSizeWHeLayXY = new Double[][] { 

	    	{380.0,110.0,580.0,100.0}, 
		    	{80.0,30.0,10.0,10.0}, 
			    {275.0,30.0,96.0,10.0}, 
				    	{40.0,30.0,10.0,39.0}, 
					    {160.0,30.0,50.0,39.0}, 
						    	{40.0,30.0,10.0,70.0}, 
							    {100.0,30.0,49.0,70.0}, 
								    	{40.0,30.0,139.0,70.0}, 
									    {100.0,30.0,190.0,70.0}, 
									    		{70.0,20.0,296.0,74.0} 
		    	
	    };
	    	   
	    Componentes comEndereco = new Componentes();
	    comEndereco.popularTela(componentesEndereco, prefSizeWHeLayXY, p1);
	    
		    componentesPersistencia.add(pPersistencia = new Pane());
		    componentesPersistencia.add(btnNovo = new Button("NOVO"));
		    componentesPersistencia.add(btnSalvar = new Button("SALVAR"));
		    componentesPersistencia.add(btnEditar = new Button("EDITAR"));
		    componentesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
		    componentesPersistencia.add(btnCancelar = new Button("CANCELAR"));
		    
		    componentesPersistencia.add(tfPesquisar = new TextField());
		    
		    componentesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	
	    	{930.0,60.0,25.0,240.0}, 
		    	{95.0,25.0,17.0,18.0}, 
			    	{95.0,25.0,123.0,18.0}, 
				    	{95.0,25.0,228.0,18.0}, 
					    	{95.0,25.0,333.0,18.0}, 
						    	{95.0,25.0,438.0,18.0}, 
							    	{265.0,25.0,543.0,18.0}, 
							    		{95.0,25.0,818.0,18.0} }; 
		    	
		com = new Componentes();
	    com.popularTela(componentesPersistencia, prefSizeWHeLayXY, p1);
	    
	    modularBotoes();
	   
	    acionarBotoes();
	    
	    selecionarDocumento ();
	    
	  }
	  
	  public void acionarBotoes () {
	   
	    btnNovo.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	habilitarDocumento();
	        }
	    });
	    
	    
		    btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent e) {
		        	salvarDocumento();
		        }
		    });
	    
	    
			    btnEditar.setOnAction(new EventHandler<ActionEvent>() {
			        @Override public void handle(ActionEvent e) {
			        	editarDocumento();
			        }
			    });
	   
	    
	    
				    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
				        @Override public void handle(ActionEvent e) {
				        	excluirDocumento();
				        }
				    });
	   
	    
					    btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
					        @Override public void handle(ActionEvent e) {
					        	cancelarDocumento();
					        }
					    });
	   
	    
						    btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {
						        @Override public void handle(ActionEvent e) {
						        	pesquisarDocumento();
						        }
						    });
						   
	    
	    btnTelaEndereco.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	inicializarTelaEndereco();
	        	
	        	TelaEnderecoControlador.tabEndCon.setObjetoDeEdicao(documento);
	        	
	        }
	    });
	    
	    
	    tfPesquisar.setOnKeyReleased(event -> {
			  if (event.getCode() == KeyCode.ENTER){
			     btnPesquisar.fire();
			  }
			});
	   
	    
	    btnTelaProcesso.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	
	        	inicializarTelaProcesso ();
	        
	        	TelaProcessoControlador.telaProCon.setDocumento(documento);
	        	
	        	
	        }
	    });
	    
	  }
	  
	  ArrayList<Node> listNodesProcesso = new ArrayList<Node>();
	  
	  Pane pDadosDocumento;
	  	ComboBox<String> cbTipo;
	  		TextField tfNumeracao;
	  			TextField tfSEI;
	  				TextField tfProcesso;
	  					DatePicker dpDataDistribuicao;
	  						DatePicker dpDataRecebimento;
	  
	  					ArrayList<Node> componentesDocumento = new ArrayList<Node>();
	  
	  						Pane pPersistencia = new Pane();
	  
			  					Button btnNovo;
			  						Button btnSalvar;
									  Button btnEditar;
										  Button btnExcluir;
										  	Button btnCancelar;
										  		Button btnPesquisar;
										  			TextField tfPesquisar;
										  			
										  				ArrayList<Node> componentesPersistencia = new ArrayList<Node>();
			  
								  				Pane pEndereco = new Pane();
												  Label lblLogradouro;
												  	Label lblRegiaoAdministrativa;
												  		Label lblLatitude;
												  			Label lblLongitude;
												  				Button btnTelaEndereco;
												  				
												  					ArrayList<Node> componentesEndereco = new ArrayList<Node>();
	  
												  
	ObservableList<Documento> obsList = FXCollections.observableArrayList();
	
	  	TranslateTransition tProEsquerda;
	  		TranslateTransition tProDireita;
	  			Pane pTelaProcesso;
	  				Double dblTransTelaProcesso;
	  						
	  public void listarDocumentos (String strPesquisa) {
		  
	    DocumentoDao docDao = new DocumentoDao();
	    List<Documento> docList = docDao.listarDocumentos(strPesquisa);
	    
	    if (!obsList.isEmpty()) {
	      obsList.clear();
	      
	    }
	  
	    for (Documento d : docList) {
	      obsList.add(d);
	    }
	    
	    tvLista.setItems(obsList);
	    
	  }
	  
	  TranslateTransition tDireita;
	  TranslateTransition tEsquerda;
	  Pane pTelaEndereco;
	  Double dblTransEndereco;
	  
	  public void inicializarTelaProcesso () {
		  
	    if (pTelaProcesso == null) {
	    	
	    	pTelaProcesso = new Pane();
	    	pTelaProcesso.setPrefSize(500.0, 500.0);
	    	
	    	pTelaProcesso.setStyle("-fx-background-color: red;");
		    
	    	Pane p = new Pane();
	    	
	    	p.setStyle("-fx-background-color: blue;");
	    	
	    	System.out.println("intControlador na tab Demanda " + intControlador);
			//telaProCon = new TelaProcessoControlador();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TelaProcesso.fxml"));
			loader.setRoot(p);
			loader.setController(new TelaProcessoControlador(intControlador));
		
			try {
				loader.load();
			}
				catch (IOException e)	{
					System.out.println("erro leitura do pane");
					e.printStackTrace();
				}
		
			pTelaProcesso.getChildren().add(p);
			
			p1.getChildren().add(pTelaProcesso);
		
			tProEsquerda = new TranslateTransition(new Duration(350.0), pTelaProcesso);
			tProEsquerda.setToX(15.0);
				  
			tProDireita = new TranslateTransition(new Duration(350.0), pTelaProcesso);
			tProDireita.setToX(1300.0);
		  
					pTelaProcesso.setTranslateX(1300.0);
		  
		}
	    
	    movimentarTelaProcesso (15.0);
	    
	  }
	  
	  public void movimentarTelaProcesso (Double dbltransEsquerda)	{
		  
		  /*
	    if (demanda.getDemID() == 0) {
	    	
	      lbl_TP_Demanda.setText("Não há demanda selecionada!!!");
	      lbl_TP_Demanda.setTextFill(Color.RED);
	      
	    }
	    else {
	    	
	      lbl_TP_Demanda.setText(demanda
	        .getDemDocumento() + ", Sei nº" + demanda
	        	.getDemDocumentoSEI() + ", Processo nº " + demanda
	        		.getDemProcessoSEI());
	      
	      	lbl_TP_Demanda.setTextFill(Color.BLACK);
	    }*/
	    
		  dblTransTelaProcesso = Double.valueOf(pTelaProcesso.getTranslateX());
	    
		  if (dblTransTelaProcesso.equals(dbltransEsquerda)) {
			  tProDireita.play();
		    } else {
		    	tProEsquerda.play();
		    }
		    
		  }
		  
	  public void inicializarTelaEndereco ()
	  {
	    if (pTelaEndereco == null)
	    {
	      pTelaEndereco = new Pane();
	      pTelaEndereco.setPrefSize(500.0, 500.0);
	      
	      Pane p = new Pane();
	      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TelaEndereco.fxml"));
	      loader.setRoot(p);
	      loader.setController(new TelaEnderecoControlador(intControlador));
	      try
	      {
	        loader.load();
	      }
	      catch (IOException e)
	      {
	        System.out.println("erro leitura do pane - chamada legisla��o");
	        e.printStackTrace();
	      }
	      pTelaEndereco.getChildren().add(p);
	      
	      p1.getChildren().add(pTelaEndereco);
	      
	      tEsquerda = new TranslateTransition(new Duration(350.0D), pTelaEndereco);
	      tEsquerda.setToX(15.0D);
	      
	      tDireita = new TranslateTransition(new Duration(350.0D), pTelaEndereco);
	      tDireita.setToX(1300.0D);
	      
	      pTelaEndereco.setTranslateX(1300.0D);
	    }
	    movimentarTelaEndereco(15.0);
	  }
	  
	  public void movimentarTelaEndereco(Double dbltransEsquerda){
		  
	    System.out.println("movimentar tela endereco ");
	    
	    dblTransEndereco = Double.valueOf(pTelaEndereco.getTranslateX());
	    if (dblTransEndereco.equals(dbltransEsquerda)) {
	      tDireita.play();
	    } else {
	    	tEsquerda.play();
	    }
	    
	    
	  }
	  
	  public void modularBotoes () {
		  
	    cbTipo.setDisable(true);
	    tfNumeracao.setDisable(true);
	    tfNumeracao.setDisable(true);
	    tfSEI.setDisable(true);
	    tfProcesso.setDisable(true);
	    
	    dpDataDistribuicao.setDisable(true);
	    dpDataRecebimento.setDisable(true);
	    
	    btnSalvar.setDisable(true);
	    btnEditar.setDisable(true);
	    btnExcluir.setDisable(true);
	    
	    btnNovo.setDisable(false);
	  }
	  
	  //-- selecionar demandas -- //
	  public void selecionarDocumento () {
			
		// TableView - selecionar demandas ao clicar //
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			Documento doc = (Documento) newValue;
			
			if (doc == null) {
				
				cbTipo.setValue(null);
				tfNumeracao.setText("");
				tfSEI.setText("");
				tfProcesso.setText("");
				
				dpDataRecebimento.getEditor().clear();
				dpDataDistribuicao.getEditor().clear();
			
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} else {

				documento = doc;
				
				// preencher os campos //
				cbTipo.setValue(doc.getDocTipo());
				tfNumeracao.setText(doc.getDocNumeracao());
				tfSEI.setText(doc.getDocSEI());
				tfProcesso.setText(doc.getDocProcesso());
				
				if (doc.getDocDataDistribuicao() == null) {
					dpDataDistribuicao.setValue(null);
					
	 				} else {
	 					Date dataDis = doc.getDocDataDistribuicao();
	 					dpDataDistribuicao.setValue(dataDis.toLocalDate());
	 				}
				
					if (doc.getDocDataRecebimento() == null) {
						dpDataRecebimento.setValue(null);
		 				} else {
		 					
		 					Date dataRec = doc.getDocDataRecebimento();
		 					dpDataRecebimento.setValue(dataRec.toLocalDate());
		 				}
				
				
				// endereço relacionado //
				if (doc.getDocEnderecoFK() != null) {
					
						lblLogradouro.setText(doc.getDocEnderecoFK().getEndLogradouro());
						lblRegiaoAdministrativa.setText(doc.getDocEnderecoFK().getEndRAFK().getRaNome());
						lblLatitude.setText(doc.getDocEnderecoFK().getEndDDLatitude().toString());
						lblLongitude.setText(doc.getDocEnderecoFK().getEndDDLongitude().toString());
						 		
						lblLogradouro.setStyle("-fx-text-fill: #4A4A4A;"); 
						
					
						// listar as interferencias
						Set<Documento> setDoc = doc.getDocEnderecoFK().getDocumentos();
						Endereco end = doc.getDocEnderecoFK();
							
						// preparar strings para transmitir para o javascript pelo metodo 'setEnderecoInterferencias()'
							
							String strInfoDocumentos = "";
						
								String strEndereco = end.getEndDDLatitude() + "," + end.getEndDDLongitude();
								
							for(Documento d : setDoc) {
								
								strInfoDocumentos += "|" + d.getDocTipo() + "," + d.getDocSEI() + "," + d.getDocProcesso();
								
								
							} // fim loop for
						
						/* chamar os metodo necessarios, primeiro as coordenadas e detalhes, 
							zoom do mapa e deois centralizar o mapa de acordo com o endereco
							*/
						googleMaps.mostrarDemandas(strEndereco, strInfoDocumentos);
						googleMaps.setZoom (11);
						googleMaps.setMapCenter(end.getEndDDLatitude(), end.getEndDDLongitude());
					
					
				} else {
					
						lblLogradouro.setText("Sem endereço cadastrado!");
						lblRegiaoAdministrativa.setText("");
						lblLatitude.setText("");
						lblLongitude.setText("");
					
						lblLogradouro.setStyle("-fx-text-fill: #FF0000;"); // fonte color: vermelho
				}
				
				if (doc.getDocProcessoFK() != null) {
					
					lblProcessoPrincipal.setText(
							
							doc.getDocProcessoFK().getProSEI()
							+ ", Interessado n° " + doc.getDocProcessoFK().getProInteressado()
							
							);
					lblProcessoPrincipal.setStyle("-fx-text-fill: #4A4A4A;"); 
			
					
				} else {
					
					lblProcessoPrincipal.setText("Não está relacionado a nenhum processo principal!");
					lblProcessoPrincipal.setStyle("-fx-text-fill: #FF0000;");
				}
				
				// mostrar data de atualizacao //
				FormatoData d = new FormatoData();
				try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(doc.getDocDataAtualizacao()));  // d.formatarData(demanda.getDemAtualizacao())
						lblDataAtualizacao.setTextFill(Color.BLACK);
				}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
						lblDataAtualizacao.setTextFill(Color.RED);}
				
				//Levar a demanda para cadastrar o endereco //
				//tabEndCon.setDemanda(demanda);
				//enditarEnderecoControlador.setObjetoDeEdicao(demanda);
				
				System.out.println("valor do int controlador " + intControlador);
				
			    if (intControlador == 0) {
			    	TabEnderecoControlador.controladorAtendimento.setDocumento(doc);
		
			 	}
			     
			    if (intControlador == 1) {
			 		TabEnderecoControlador.controladorFiscalizacao.setDocumento(doc);
			    }
			    
			    if (intControlador == 2) {
		 			TabEnderecoControlador.controladorOutorga.setDocumento(doc);
			    }
				
			    
				// copiar número sei da demanda ao selecionar //
				Clipboard clip = Clipboard.getSystemClipboard();
	            ClipboardContent conteudo = new ClipboardContent();
	            conteudo.putString(doc.getDocSEI());
	            clip.setContent(conteudo);
				
				// habilitar e desabilitar botões //
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} // fim do else
			
		} // fim do metodo changed
				
		}); // fim do selection model
			
		}

}
