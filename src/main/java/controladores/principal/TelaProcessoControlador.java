package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.internal.InexactComparisonCriteria;

import dao.ProcessoDao;
import entidades.Demanda;
import entidades.Endereco;
import entidades.Processo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;

public class TelaProcessoControlador implements Initializable {
	
	@FXML Pane pTelaProcesso;
	
	Demanda demanda;
	
	/* recebimento da demanda e preenchimento na parte superior - DOCUMENTO: ... */
	public void setDemanda (Demanda demanda)  {
		
		this.demanda = demanda;
		// preencher o label com a demanda selecionada //
		lblDemanda.setText(
				demanda.getDemTipo() 
				+ ", Sei n° " + demanda.getDemNumeroSEI()
				+ ", Processo n° " + demanda.getDemProcesso()
				);
	}
	
	Pane pDemanda;
		Label lblDemanda;
			Button btnDemanda;
	
	
	ArrayList<Node> listNodesEditorProcesso = new ArrayList<Node>();
	  
	  Pane TelaProcessoDados;
	  	TextField tfProcNumero;
	  		DatePicker dpProcDataCriacao;
	  			TextField tfProcInteressado;
	  				ArrayList<Node> listNodesTelaProcessoDados = new ArrayList<Node>();
	  				
	  Pane pPersistencia;
	  	Button btnNovo;
	  		Button btnSalvar;
	  			Button btnEditar;
	  				Button btnExcluir;
	  					Button btnCancelar;
	  						Button btnPesquisar;
	  						
	  							TextField tfPesquisar;
	  							
	  								ArrayList<Node> listNodesPersistencia= new ArrayList<Node>();
	  								
	  TableView<Processo> tvProcessos = new TableView<Processo>();
	  	TableColumn<Processo, String> tcNumeroProcesso = new TableColumn<Processo, String>("Processo");
	  		TableColumn<Processo, String> tcDataCriacao = new TableColumn<Processo, String>("Data de Criação");
	  			TableColumn<Processo, String> tcInteressado = new TableColumn<Processo, String>("Interessado");
	  				ObservableList<Processo> obsListProcessos = FXCollections.observableArrayList();
	  
	  				Label lblDataAtualizacao;
	  
	  				TableView<Demanda> tvDemandas = new TableView<Demanda>();
	  				 TableColumn<Demanda, String> tcTipoDemanda = new TableColumn<Demanda, String>("Tipo");
	  			  		TableColumn<Demanda, String> tcNumeroDemanda = new TableColumn<Demanda, String>("Número");
	  			  			TableColumn<Demanda, String> tcNumeroDemandaSEI = new TableColumn<Demanda, String>("Número SEI");
	  			  				TableColumn<Demanda, String> tcNumeroProcessoSEI = new TableColumn<Demanda, String>("Número do Processo");
	  		    
	  								ObservableList<Demanda> obsListDemandas = FXCollections.observableArrayList();
	  							
	  
  Double prefSizeWHeLayXY [][];
  String strPesquisa = "";
	
  /* para movimentar dados entre as telas */
  public static TelaProcessoControlador telaProCon;
  
 
  @Override
  public void initialize(URL url, ResourceBundle rb) {
		
		/* inicializar objeto para movimentar dados entre as telas */
		telaProCon = this;
		
	    pTelaProcesso.setStyle("-fx-background-color:#FFFFFF;");
	    
	    listNodesEditorProcesso.add(pDemanda = new Pane());
	    listNodesEditorProcesso.add(new Label("DEMANDA:"));
	    listNodesEditorProcesso.add(lblDemanda = new Label());
	    listNodesEditorProcesso.add(btnDemanda = new Button(">>>"));
	    
	    prefSizeWHeLayXY = new Double [][] { 
	    	{930.0,60.0,10.0,10.0},
	    	{100.0,30.0,25.0,15.0},
	    	{720.0,30.0,115.0,15.0},
	    	{60.0,25.0,845.0,18.0},
    				
	    };
	    	
	    Componentes comEditorProcesso = new Componentes();
	    comEditorProcesso.popularTela(listNodesEditorProcesso, prefSizeWHeLayXY, pTelaProcesso);
	    
	    listNodesTelaProcessoDados.add(TelaProcessoDados = new Pane());
	    	listNodesTelaProcessoDados.add(new Label("PROCESSO:"));
			    listNodesTelaProcessoDados.add(tfProcNumero = new TextField());
				    listNodesTelaProcessoDados.add(new Label("DATA DE CRIAÇÃO:"));
				    	listNodesTelaProcessoDados.add(dpProcDataCriacao = new DatePicker());
				    		listNodesTelaProcessoDados.add(new Label("INTERESSADO:"));
				    			listNodesTelaProcessoDados.add(tfProcInteressado = new TextField());
	    
	    prefSizeWHeLayXY =  new Double [][] { 
	    	
	    	{930.0,60.0,7.0,82.0},
	    	{95.0,30.0,8.0,15.0},
	    	{130.0,30.0,113.0,15.0},
	    	{130.0,30.0,253.0,15.0},
	    	{140.0,30.0,383.0,15.0},
	    	{100.0,30.0,533.0,15.0},
	    	{280.0,30.0,643.0,15.0},
    							
	    };
	   
	    Componentes comDadosProcesso = new Componentes();
	    comDadosProcesso.popularTela(listNodesTelaProcessoDados, prefSizeWHeLayXY, pTelaProcesso);
	    
	    listNodesPersistencia.add(pPersistencia = new Pane());
	    listNodesPersistencia.add(btnNovo = new Button("NOVO"));
	    listNodesPersistencia.add(btnSalvar = new Button("SALVAR"));
	    listNodesPersistencia.add(btnEditar = new Button("EDITAR"));
	    listNodesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
	    listNodesPersistencia.add(btnCancelar = new Button("CANCELAR"));
	    listNodesPersistencia.add(tfPesquisar = new TextField());
	    listNodesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	{930.0,60.0,7.0,153.0},
	    	{95.0,25.0,18.0,18.0},
	    	{95.0,25.0,123.0,18.0},
	    	{95.0,25.0,228.0,18.0},
	    	{95.0,25.0,333.0,18.0},
	    	{95.0,25.0,438.0,18.0},
	    	{265.0,25.0,543.0,18.0},
	    	{95.0,25.0,818.0,18.0},
					    			
	    }; 
					    				
	    Componentes comPersistencia = new Componentes();
	    comPersistencia.popularTela(listNodesPersistencia, prefSizeWHeLayXY, pTelaProcesso);
	    
	    tcNumeroProcesso.setCellValueFactory(new PropertyValueFactory<Processo, String>("proSEI"));
	    tcDataCriacao.setCellValueFactory(new PropertyValueFactory<Processo, String>("proDataCriacao"));
	    tcInteressado.setCellValueFactory(new PropertyValueFactory<Processo, String>("proInteressado"));
	    
	    
	    tcNumeroProcesso.setPrefWidth(200.0);
	    tcDataCriacao.setPrefWidth(200.0);
	    tcInteressado.setPrefWidth(480.0);
	    
	    tvProcessos.setPrefSize(900.0, 185.0);
	    tvProcessos.setLayoutX(25.0D);
	    tvProcessos.setLayoutY(228.0);
	    
	    tvProcessos.getColumns().add(tcNumeroProcesso);
	    tvProcessos.getColumns().add(tcDataCriacao);
	    tvProcessos.getColumns().add(tcInteressado);
	    
	    tvProcessos.setItems(obsListProcessos);
	    
	    pTelaProcesso.getChildren().add(tvProcessos);
	    
	    lblDataAtualizacao = new Label();
	    
	    lblDataAtualizacao.setPrefSize(247.0, 22.0);
	    lblDataAtualizacao.setLayoutX(692.0);
	    lblDataAtualizacao.setLayoutY(425.0);
	    
	    pTelaProcesso.getChildren().add(lblDataAtualizacao);
	    
	    tcTipoDemanda.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demTipo"));
	    tcNumeroDemanda.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demNumero"));
	    tcNumeroDemandaSEI.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demNumeroSEI"));
	    tcNumeroProcessoSEI.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demProcesso"));
	    
	    tcTipoDemanda.setPrefWidth(200.0);
    		tcNumeroDemanda.setPrefWidth(200.0);
    			tcNumeroDemandaSEI.setPrefWidth(200.0);
    				tcNumeroProcessoSEI.setPrefWidth(220.0);
	    
	    tvDemandas.setPrefSize(840.0, 185.0);
	    tvDemandas.setLayoutX(55.0);
	    tvDemandas.setLayoutY(458.0);
	    
	    tvDemandas.getColumns().add(tcTipoDemanda);
	    tvDemandas.getColumns().add(tcNumeroDemanda);
	    tvDemandas.getColumns().add(tcNumeroDemandaSEI);
	    tvDemandas.getColumns().add(tcNumeroProcessoSEI);
	    
	    tvDemandas.setItems(obsListDemandas);
	    
	    pTelaProcesso.getChildren().add(tvDemandas);
	    
	    modularBotoesProcesso();
	    
	    acionarBotoes ();
	    
	    selecionarProcesso ();
	
	} // FIM INITIALIZE

	/*@intControlador - valor referente ao controlador chamado. 0 para atendimento e 1 para fiscalizacao
	 *  Utilizado no método acionarBotoes e assim movimentar a tela a partir do controlador chamado
	 */
	int intControlador;
	
	/* construtor para trazer o intControlador correto. 0 para atendimento e 1 para fiscalizacao */
	public TelaProcessoControlador (int intControlador) {
		  this.intControlador = intControlador;
	}
	
  public void acionarBotoes () {
	  
	    btnDemanda.setOnAction(new EventHandler<ActionEvent>() {
	    	
	        @Override public void handle(ActionEvent e) {
	     
	        	if (intControlador == 0) {
	        		TabDemandaControlador.controladorAtendimento.movimentarTelaProcesso(15.0);
	        	}
	        	if (intControlador == 1) {
	        		TabDemandaControlador.controladorFiscalizacao.movimentarTelaProcesso(15.0);
	        	}
	        	
	        }
	    });
	    

	    btnNovo.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	habilitarProcesso();
	        }
	    });
	    
	    btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	salvarProcesso();
	        }
	    });
	    
	    
	    btnEditar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	editarProcesso();
	        }
	    });
	    
	    
	    
	    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	excluiProcesso();
	        }
	    });
	    
	    
	    btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	cancelarProcesso();
	        }
	    });
	    
	    btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	pesquisarProcesso();
	        }
	    });
	    
	  
  }
  
  public void habilitarProcesso()	{
		  
		    tfProcNumero.setText("");
		    dpProcDataCriacao.getEditor().clear();
		    tfProcInteressado.setText("");
		    
		    tfProcNumero.setDisable(false);
		    dpProcDataCriacao.setDisable(false);
		    tfProcInteressado.setDisable(false);
		    
		    btnSalvar.setDisable(false);
		    
		    btnNovo.setDisable(true);
		    btnEditar.setDisable(true);
		    btnExcluir.setDisable(true);
		    
		  }
		  
  public void salvarProcesso()	{
    try	{
    	
      if (tfProcNumero.getText().isEmpty())
      {
        Alerta a = new Alerta();
        a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Número do processo - SEI!!!", new ButtonType[] { ButtonType.OK }));
      }
      
      else	{
    	  
        Processo pro = new Processo();
        
        pro.setProSEI(tfProcNumero.getText());
        if (dpProcDataCriacao.getValue() == null) {
          pro.setProDataCriacao(null);
        } else {
          pro.setProDataCriacao(Date.valueOf((LocalDate)dpProcDataCriacao.getValue()));
        }
        pro.setProInteressado(tfProcInteressado.getText());
        
        pro.setProAtualizacao(Timestamp.valueOf(LocalDateTime.now()));
        
        Demanda dem = new Demanda();
        dem = demanda;
        dem.setDemProcessoFK(pro);
        
        Set<Demanda> setDemandas = new HashSet<Demanda>();
        
        setDemandas.add(dem);
        
        pro.setDemandas(setDemandas);
        
        ProcessoDao proDao = new ProcessoDao();
        
        proDao.salvarProcesso(pro);
        proDao.mergeProcesso(pro);
        
        obsListProcessos.add(pro);
        
        modularBotoesProcesso();
        
        Alerta a = new Alerta();
        
        a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", new ButtonType[] { ButtonType.OK }));
      }
      
    }
    catch (Exception ex) {
    	
      System.out.println("Erro: " + ex);
      ex.printStackTrace();
      
      Alerta a = new Alerta();
      a.alertar(new Alert(Alert.AlertType.ERROR, "erro na conexão, tente novamente!", new ButtonType[] { ButtonType.OK }));
      
    }
    
  }
  
  public void editarProcesso()	{
	  
    if (tfProcNumero.isDisable()) {
      tfProcNumero.setDisable(false);
      dpProcDataCriacao.setDisable(false);
      tfProcInteressado.setDisable(false);
    }
	    else if ((tfProcNumero.getText().isEmpty()) || (tfProcInteressado.getText().isEmpty())) {
	    	
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Processo SEI e Interessado !!!", new ButtonType[] { ButtonType.OK }));
	      
	    }
		    else {
		    	
		      Processo pro = (Processo) tvProcessos.getSelectionModel().getSelectedItem();
		      
		      pro.setProSEI(tfProcNumero.getText());
		      if (dpProcDataCriacao.getValue() == null) {
		        pro.setProDataCriacao(null);
		      } else {
		        pro.setProDataCriacao(Date.valueOf((LocalDate)dpProcDataCriacao.getValue()));
		      }
		      pro.setProInteressado(tfProcInteressado.getText());
		      
		      pro.setProAtualizacao(Timestamp.valueOf(LocalDateTime.now()));
		      for (Demanda d : pro.getDemandas()) {
		        System.out.println(" ++ antes " + d.getDemNumeroSEI());
		      }
		      
		      Iterator<Demanda> iteratorDemanda;
		      
		      if (demanda.getDemID() != 0)
		      {
		        Demanda dem = new Demanda();
		        dem = demanda;
		        dem.setDemProcessoFK(pro);
		        
		        Set<Demanda> setDemandas = new HashSet<Demanda>();
		        	setDemandas = pro.getDemandas();
		        	
		        for (iteratorDemanda = setDemandas.iterator(); iteratorDemanda.hasNext();)
		        {
		          Demanda d = (Demanda)iteratorDemanda.next();
		          if (d.getDemID() == demanda.getDemID()) {
		            iteratorDemanda.remove();
		          }
		        }
		        
		        setDemandas.add(dem);
		        for (Demanda d : setDemandas) {
		          System.out.println("                 -----------------------                    depois " + d.getDemNumeroSEI());
		        }
		        
		}
		      
      ProcessoDao proDao = new ProcessoDao();
      
      proDao.mergeProcesso(pro);
      
      obsListProcessos.remove(pro);
      obsListProcessos.add(pro);
      
      modularBotoesProcesso();
      
      Alerta a = new Alerta();
      a.alertar(new Alert(Alert.AlertType.ERROR, "Cadastro editado com sucesso!!!", new ButtonType[] { ButtonType.OK }));
      
    }
    
  }
  
  public void excluiProcesso() {
	  
    try {
    	
	      Processo pro = (Processo) tvProcessos.getSelectionModel().getSelectedItem();
	      
	      int id = pro.getProID();
	      
	      ProcessoDao proDao = new ProcessoDao();
	      
	      proDao.removerProcesso(Integer.valueOf(id));
	      
	      obsListProcessos.remove(pro);
	      
	      modularBotoesProcesso();
	      
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluÍdo com sucesso!!!", new ButtonType[] { ButtonType.OK }));
	      
	 }
	  catch (Exception e)	{
		  
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao excluir o cadastro!!!", new ButtonType[] { ButtonType.OK }));
	      
	    }
    
	    System.out.println("btn excluir processo clicado");
  }
		  
  public void cancelarProcesso() {
	  
    modularBotoesProcesso();
    
  }
  
  public void pesquisarProcesso()	{
	  
    strPesquisa = tfPesquisar.getText();
    
    listarProcessos(strPesquisa);
    
    modularBotoesProcesso();
    
  }
  
  private void modularBotoesProcesso() {
	 
    tfProcNumero.setDisable(true);
    dpProcDataCriacao.setDisable(true);
    tfProcInteressado.setDisable(true);
    
    btnSalvar.setDisable(true);
    btnEditar.setDisable(true);
    btnExcluir.setDisable(true);
    
    btnNovo.setDisable(false);
}

  public void listarProcessos(String strPesquisaProcesso) {
	  
	ProcessoDao proDao = new ProcessoDao();
    
    Set<Processo> proList = new HashSet<Processo>(proDao.listarProcessos(strPesquisaProcesso));
    
    if (!obsListProcessos.isEmpty()) {
      obsListProcessos.clear();
    }
    
    for (Processo p : proList) {
    
      obsListProcessos.add(p);
    }
    
    tvProcessos.setItems(obsListProcessos);
 }
	
//-- selecionar demandas -- //
  public void selecionarProcesso () {
		
	// TableView - selecionar demandas ao clicar //
	  tvProcessos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
		
		public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
		
		Processo pro = (Processo) newValue;
		
		if (pro == null) {
			
			tfProcNumero.setText("");
			tfProcInteressado.setText("");
			dpProcDataCriacao.getEditor().clear();
		
			btnNovo.setDisable(true);
			btnSalvar.setDisable(true);
			btnEditar.setDisable(false);
			btnExcluir.setDisable(false);
			btnCancelar.setDisable(false);
			
		} else {

			// preencher os campos //
			
			tfProcNumero.setText(pro.getProSEI());
			tfProcInteressado.setText(pro.getProInteressado());
			dpProcDataCriacao.getEditor().clear();
			
			if (pro.getProDataCriacao() == null) {
				dpProcDataCriacao.setValue(null);
				
 				} else {
 					Date dataDis = pro.getProDataCriacao();
 							dpProcDataCriacao.setValue(dataDis.toLocalDate());
 				}
			
			obsListDemandas.clear();
			obsListDemandas.addAll(pro.getDemandas());
			
			for (Demanda d : pro.getDemandas()) {
				System.out.println(d.getDemNumero());
			}
			
			// mostrar data de atualizacao //
			FormatoData d = new FormatoData();
			try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(pro.getProAtualizacao()));  // d.formatarData(demanda.getDemAtualizacao())
					lblDataAtualizacao.setTextFill(Color.BLACK);
			}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
					lblDataAtualizacao.setTextFill(Color.RED);}
			
			
			// copiar número sei da demanda ao selecionar //
			Clipboard clip = Clipboard.getSystemClipboard();
            ClipboardContent conteudo = new ClipboardContent();
            conteudo.putString(pro.getProSEI());
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
