package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.print.Doc;

import dao.DemandaDao;
import dao.DocumentoDao;
import entidades.Demanda;
import entidades.Documento;
import entidades.Endereco;
import entidades.Parecer;
import entidades.Processo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.util.Callback;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;

public class TabParecerControlador implements Initializable {
	
	TableView<Documento> tvLista = new TableView<Documento>();
	  TableColumn<Documento, String> tcDocumento = new TableColumn<Documento, String>("Parecer");
	  	TableColumn<Documento, String> tcSEI = new TableColumn<Documento, String>("SEI");
	  		TableColumn<Documento, String> tcEndereco = new TableColumn<Documento, String>("Endereço");

	  		 ObservableList<Documento> obsList = FXCollections.observableArrayList();
	  		
	  		 Set<Documento> setListParecer;

	Documento documento = new Documento();
		Processo processo = new Processo();
			Endereco endereco = new Endereco();
		
	public void setEndereco (Endereco endereco) {
	
	this.endereco = endereco;

	if (endereco != null ) {
		
		lblEndereco.setText(
				
				endereco.getEndLogradouro()
				+ ", CEP n°: " + endereco.getEndCEP()
				+ ", Cidade: " + endereco.getEndCidade()
				
				);
		lblEndereco.setStyle("-fx-text-fill: #4A4A4A;"); 
	}
	else {
		
		lblEndereco.setText(
				"Não há endereco relacionado! "
				);
		lblEndereco.setStyle("-fx-text-fill: #FF0000;");
	}
	
	}

	Label lblDataAtualizacao = new Label();
  
  	@FXML Pane pParecer;
	  
  	Pane p1 = new Pane();
  	BorderPane bp1 = new BorderPane();
  	BorderPane bp2 = new BorderPane();
  	ScrollPane sp = new ScrollPane();
	  
	public static TabParecerControlador controladorAtendimento;
  		public static TabParecerControlador controladorFiscalizacao;
  			public static TabParecerControlador controladorOutorga;
	 
	int intControlador;
		
	public TabParecerControlador (int i) {
		
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
	  
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		bp1.minWidthProperty().bind(pParecer.widthProperty());
	    bp1.maxHeightProperty().bind(pParecer.heightProperty().subtract(60));
	    
	    bp1.getStyleClass().add("border-pane");
	    
	    bp2.setPrefHeight(800.0D);
	    bp2.minWidthProperty().bind(pParecer.widthProperty());
	    
	    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    
	    sp.setContent(bp2);
	    
	    bp1.setCenter(sp);
	    
	    pParecer.getChildren().add(bp1);
	    
	    p1.setMaxSize(980.0, 1000.0);
	    p1.setMinSize(980.0, 1000.0);
	    
	    bp2.setTop(p1);
	    BorderPane.setAlignment(p1, Pos.CENTER);
    
    
    lblDataAtualizacao.setPrefSize(247.0, 22.0);
    lblDataAtualizacao.setLayoutX(705.0);
    lblDataAtualizacao.setLayoutY(450.0);
    
    tcDocumento.setCellValueFactory(new PropertyValueFactory<Documento, String>("docNumero"));
    tcSEI.setCellValueFactory(new PropertyValueFactory<Documento, String>("docSEI"));
    
    /*
    tcEndereco.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Documento, String>, ObservableValue<String>>() {
	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Documento, String> doc) {
	    	return new SimpleStringProperty(doc.getValue().getDocEnderecoFK().getEndLogradouro());
	       
	    }
	});
	*/

    tcDocumento.setPrefWidth(250.0);
    	tcSEI.setPrefWidth(250.0);
    		tcEndereco.setPrefWidth(410.0);

    
    tvLista.setPrefSize(930.0, 185.0);
    	tvLista.setLayoutX(25.0);
    		tvLista.setLayoutY(255.0);
    
    tvLista.getColumns().add(tcDocumento); //, tcDocsSEI, tcProcsSEI });
    	tvLista.getColumns().add(tcSEI);
    		tvLista.getColumns().add(tcEndereco);
    		
    
    tvLista.setItems(obsList);
  
    p1.getChildren().addAll(tvLista, lblDataAtualizacao);
    
    inicializarComponentes ();
    selecionarDocumento (); 
    modularBotoesDocumento();
    acionarBotoesDocumento();
    
    	
	} // FIM INITIALIZE
	
	Componentes com;
	Double prefSizeWHeLayXY [][];
	
	Pane pEndereco;
		Label lblEndereco;
		Button btnEndereco;
		ArrayList<Node> componentesEndereco = new ArrayList<Node>();
	
	Pane pDadosParecer;
		TextField tfDocumento;
  		TextField tfSEI;
  		TextField tfProcessoSEI;
  		DatePicker dpDataCriacao;
  		DatePicker dpDataDistribuicao;
  		DatePicker dpDataRecebimento;
  		
  		ArrayList<Node> componentesParecer = new ArrayList<Node>();
	
  	Pane  pPersistencia;
  		Button btnNovo;
		Button btnSalvar;
		Button btnEditar;
		Button btnExcluir;
		Button btnCancelar;
		Button btnPesquisar;
		TextField tfPesquisar;
			  			
		ArrayList<Node> componentesPersistencia = new ArrayList<Node>();
	
	public void inicializarComponentes (){
		
		
		componentesEndereco.add(pEndereco = new Pane());
	    componentesEndereco.add(new Label("ENDEREÇO:"));
	    componentesEndereco.add(lblEndereco = new Label());
	    componentesEndereco.add(btnEndereco = new Button("<<<"));
		    
		    prefSizeWHeLayXY = new Double [][] { 
		    	
		    	{950.0,60.0,15.0,10.0},
		    	{85.0,30.0,43.0,15.0},
		    	{710.0,30.0,128.0,15.0},
		    	{70.0,20.0,838.0,19.0},
	    				};
		    	
		    com = new Componentes();
		    com.popularTela(componentesEndereco, prefSizeWHeLayXY, p1);
		    
		    componentesParecer.add(pDadosParecer = new Pane());
		    componentesParecer.add(new Label("NÚMERO:"));
		    componentesParecer.add(tfDocumento = new TextField());
		    componentesParecer.add(new Label("SEI:"));
		    componentesParecer.add(tfSEI = new TextField());
			componentesParecer.add(new Label("PROCESSO:"));
			componentesParecer.add(tfProcessoSEI = new TextField());
			componentesParecer.add(new Label("DATA DE CRIAÇÃO:"));
			componentesParecer.add(dpDataCriacao = new DatePicker());
			componentesParecer.add(new Label("DATA DE RECEBIMENTO:"));
			componentesParecer.add(dpDataRecebimento = new DatePicker());
			componentesParecer.add(new Label("DATA DE DISTRIBUIÇÃO:"));
			componentesParecer.add(dpDataDistribuicao = new DatePicker());
			    
			    prefSizeWHeLayXY = new Double [][] { 
			    	
			    	{950.0,89.0,15.0,80.0},
			    	{70.0,30.0,16.0,15.0},
			    	{130.0,30.0,15.0,45.0},
			    	{35.0,30.0,156.0,15.0},
			    	{130.0,30.0,156.0,45.0},
			    	{85.0,30.0,296.0,15.0},
			    	{160.0,30.0,296.0,45.0},
			    	{150.0,30.0,466.0,15.0},
			    	{150.0,30.0,466.0,45.0},
			    	{150.0,30.0,626.0,15.0},
			    	{150.0,30.0,626.0,45.0},
			    	{150.0,30.0,786.0,15.0},
			    	{150.0,30.0,786.0,45.0},
		    				};
			    	
			    com = new Componentes();
			    com.popularTela(componentesParecer, prefSizeWHeLayXY, p1);
			    
			    
	    componentesPersistencia.add(pPersistencia = new Pane());
	    componentesPersistencia.add(btnNovo = new Button("NOVO"));
	    componentesPersistencia.add(btnSalvar = new Button("SALVAR"));
	    componentesPersistencia.add(btnEditar = new Button("EDITAR"));
	    componentesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
	    componentesPersistencia.add(btnCancelar = new Button("CANCELAR"));
		componentesPersistencia.add(tfPesquisar = new TextField());
		componentesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
		    
		    prefSizeWHeLayXY = new Double[][] { 
		    	
		    	{930.0,60.0,25.0,180.0},
		    	{95.0,25.0,18.0,18.0},
		    	{95.0,25.0,123.0,18.0},
		    	{95.0,25.0,228.0,18.0},
		    	{95.0,25.0,333.0,18.0},
		    	{95.0,25.0,438.0,18.0},
		    	{265.0,25.0,543.0,18.0},
		    	{95.0,25.0,818.0,18.0},
								    	
		    }; 
			    	
			com = new Componentes();
		    com.popularTela(componentesPersistencia, prefSizeWHeLayXY, p1);
			    
		
	}
		  				
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
	
	int contador = 0;
	
	public void selecionarDocumento () {
		
		// TableView - selecionar demandas ao clicar //
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			Documento doc = (Documento) newValue;
			
			if (doc == null) {
				
				tfDocumento.setText("");
				tfSEI.setText("");
				tfProcessoSEI.setText("");
			
				dpDataCriacao.getEditor().clear();
				dpDataRecebimento.getEditor().clear();
				dpDataDistribuicao.getEditor().clear();
			
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} else {
				
				System.out.println(contador ++);

				documento = doc;
				
				setEndereco(doc.getDocEnderecoFK());
				
				// preencher os campos //
				tfDocumento.setText(doc.getDocNumero());
				tfSEI.setText(doc.getDocSEI());
				tfProcessoSEI.setText(doc.getDocProcesso());
				
				if (doc.getDocDataCriacao() == null) {
					dpDataCriacao.setValue(null);
					
	 				} else {
	 					Date d = doc.getDocDataCriacao();
	 					dpDataCriacao.setValue(d.toLocalDate());
	 					System.out.println("data criacao " + d.toLocalDate());
	 				}
				
				if (doc.getDocDataDistribuicao() == null) {
					dpDataDistribuicao.setValue(null);
					
	 				} else {
	 					Date d = doc.getDocDataDistribuicao();
	 					dpDataDistribuicao.setValue(d.toLocalDate());
	 					System.out.println("data distribuicao " + d.toLocalDate());
	 				}
				
				if (doc.getDocDataRecebimento() == null) {
					dpDataRecebimento.setValue(null);
	 				} else {
	 					
	 					Date d = doc.getDocDataRecebimento();
	 					dpDataRecebimento.setValue(d.toLocalDate());
	 					System.out.println("data recebimento " + d.toLocalDate());
	 				}
				
				
				// mostrar data de atualizacao //
				FormatoData d = new FormatoData();
				try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(doc.getDocDataAtualizacao()));  // d.formatarData(demanda.getDemAtualizacao())
						lblDataAtualizacao.setTextFill(Color.BLACK);
				}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
						lblDataAtualizacao.setTextFill(Color.RED);}
				
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
	
	public void modularBotoesDocumento () {
		  
		tfDocumento.setDisable(true);
	    tfSEI.setDisable(true);
	    tfProcessoSEI.setDisable(true);
	    
	    dpDataCriacao.setDisable(true);
	    dpDataDistribuicao.setDisable(true);
	    dpDataRecebimento.setDisable(true);
	    
	    btnSalvar.setDisable(true);
	    btnEditar.setDisable(true);
	    btnExcluir.setDisable(true);
	    
	    btnNovo.setDisable(false);
	}
	
	public void acionarBotoesDocumento () {
		   
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
						   
	    
	    
	    tfPesquisar.setOnKeyReleased(event -> {
			  if (event.getCode() == KeyCode.ENTER){
			     btnPesquisar.fire();
			  }
			});
	   
		    
		  
	 }
	 
	public void habilitarDocumento () {
		  
	  tfDocumento.setText("");
	  tfSEI.setText("");
	  tfProcessoSEI.setText("");
    
	  dpDataCriacao.getEditor().clear();
	  dpDataDistribuicao.getEditor().clear();
	  dpDataRecebimento.getEditor().clear();
    
	  dpDataCriacao.setDisable(false);
	  dpDataDistribuicao.setDisable(false);
	  dpDataRecebimento.setDisable(false);
   
	  tfDocumento.setDisable(false);
	  tfSEI.setDisable(false);
	  tfProcessoSEI.setDisable(false);
    
	  btnSalvar.setDisable(false);
    
	  btnEditar.setDisable(true);
	  btnExcluir.setDisable(true);
	  btnNovo.setDisable(true);
		  
	  }
	 
	public void salvarDocumento ()	{
		    
	  try {
		  
		  if (endereco.getEndLogradouro() == null) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Endereço não selecionado!!!", ButtonType.OK));
				
		  }
		  
		  else if ((tfDocumento.getText().isEmpty()) || (tfProcessoSEI.getText().isEmpty()))
	      {
		        Alerta a = new Alerta();
		        a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Numero e Processo SEI!!!", new ButtonType[] { ButtonType.OK }));
		  }
	      else
	      {
		       Documento doc = new Documento();
		        
		       	doc.setDocNumero(tfDocumento.getText());
		       	doc.setDocSEI(tfSEI.getText());
		       	doc.setDocProcesso(tfProcessoSEI.getText());
			        
		        if (dpDataCriacao.getValue() == null) {
		        		doc.setDocDataCriacao(null);
			        } else {
			        	doc.setDocDataCriacao(Date.valueOf((LocalDate)dpDataCriacao.getValue()));
			        }
			      
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
			    
			    // relacionar a um endereco //
			    doc.setDocEnderecoFK(endereco);
			        
		        DocumentoDao docDao = new DocumentoDao();
		        
		        docDao.salvarDocumento(doc);
		      
		        obsList.add(doc);
		        
		        modularBotoesDocumento();
		        
		        Alerta a = new Alerta();
		        a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", new ButtonType[] { ButtonType.OK }));
			      
	      		} // fim else
			      
		  } // fim try
		  
		  catch (Exception ex)	{
			  System.out.println("Erro: " + ex);
			  ex.printStackTrace();
	      
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.ERROR, "erro na conexão, tente novamente!", new ButtonType[] { ButtonType.OK }));
	      
	    }
		  
	}

	public void editarDocumento()	{
		  
		    if (tfDocumento.isDisable()) {
		
		    tfDocumento.setDisable(false);
		     tfSEI.setDisable(false);
		     tfProcessoSEI.setDisable(false);
		      
		      dpDataCriacao.setDisable(false);
		      dpDataDistribuicao.setDisable(false);
		      dpDataRecebimento.setDisable(false);
		      
		    }
		    
			    else if ((tfDocumento.getText().isEmpty()) || (tfProcessoSEI.getText().isEmpty())) {
			    	
			      Alerta a = new Alerta();
			      a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Documento, Processo SEI!!!", new ButtonType[] { ButtonType.OK }));
			      
			    }
		    
				    else
				    {
				      Documento doc = (Documento)tvLista.getSelectionModel().getSelectedItem();
				      
				      doc.setDocNumero(tfDocumento.getText());
				      doc.setDocSEI(tfSEI.getText());
				      doc.setDocProcesso(tfProcessoSEI.getText());
				      
				      
				      if (dpDataCriacao.getValue() == null) {
			        		doc.setDocDataCriacao(null);
				      } else {
				        	doc.setDocDataCriacao(Date.valueOf((LocalDate)dpDataCriacao.getValue()));
				      }
				        
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
					  
					    
					  // relacionar a um endereco //
					  doc.setDocEnderecoFK(endereco);
				      
				      DocumentoDao docDao = new DocumentoDao();
				      
				      docDao.mergeDocumento(doc);
				      
				      obsList.remove(doc);
				      obsList.add(doc);
				     
				  
				    modularBotoesDocumento();
				      
				    Alerta a = new Alerta();
				    a.alertar(new Alert(Alert.AlertType.ERROR, "Cadastro editado com sucesso!!!", new ButtonType[] { ButtonType.OK }));
				    
				    } // fim else
		    
	  }
  
	public void excluirDocumento ()	{
		   
		  try
			    {
			      Documento doc = (Documento) tvLista.getSelectionModel().getSelectedItem();
			      
			      int id = doc.getDocID();
			      
			      DocumentoDao dDao = new DocumentoDao();
			      
			      dDao.removerDocumento(Integer.valueOf(id));
			      
			      obsList.remove(doc);
			      
			      modularBotoesDocumento();
			      
			      Alerta a = new Alerta();
			      a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluído com sucesso!!!", new ButtonType[] { ButtonType.OK }));
			    }
		  
			    catch (Exception e)	{
			    	
				      Alerta a = new Alerta();
				      a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao excluir o cadastro!!!", new ButtonType[] { ButtonType.OK }));
			      
			    	}
		  
	  }
	  
	public void cancelarDocumento ()	{
	   
		  modularBotoesDocumento();
	}
	  
	String strPesquisa = "";
	  
	public void pesquisarDocumento ()	{
		  
	   strPesquisa = tfPesquisar.getText();
	    
	   listarDocumentos(strPesquisa);
	    
	   modularBotoesDocumento();
	    
	  }
}
