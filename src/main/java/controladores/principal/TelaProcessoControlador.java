package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import dao.ProcessoDao;
import entidades.Demanda;
import entidades.Processo;
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
import javafx.scene.layout.Pane;
import principal.Alerta;
import principal.Componentes;

public class TelaProcessoControlador implements Initializable {
	
	@FXML Pane pTelaProcesso;
	
	Demanda demanda;
	
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
	  								
	  TableView<Processo> tvListaProcessos = new TableView<Processo>();
	  	TableColumn<Processo, String> tcNumeroProcesso = new TableColumn<Processo, String>("Processo");
	  		TableColumn<Processo, String> tcDataCriacao = new TableColumn<Processo, String>("Data de Criação");
	  			TableColumn<Processo, String> tcInteressado = new TableColumn<Processo, String>("Interessado");
	  				ObservableList<Processo> obsListProcessos = FXCollections.observableArrayList();
	  
	  				Label lblDataAtualizacaoProcesso;
	  
	  				TableView<Demanda> tvDemandas = new TableView<Demanda>();
	  					TableColumn<Demanda, String> tcDemanda = new TableColumn<Demanda, String>("Documento");
	  						TableColumn<Demanda, String> tcDemandaSEI = new TableColumn<Demanda, String>("Número SEI");
	  							TableColumn<Demanda, String> tcDemandaProcessoSEI = new TableColumn<Demanda, String>("Processo");
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
    			{75.0,30.0,14.0,15.0}, 
    				{730.0,30.0,99.0,15.0}, 
    					{75.0,20.0,841.0,17.0}};
	    	
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
	    	{930.0,60.0,10.0,85.0}, 
    			{75.0,20.0,14.0,19.0}, 
    				{160.0,20.0,95.0,16.0}, 
    					{120.0,20.0,270.0,19.0}, 
    						{126.0,20.0,402.0,16.0}, 
    							{100.0,20.0,540.0,19.0}, 
    								{265.0,20.0,638.0,16.0} };
	   
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
	    	{900.0,60.0,25.0,155.0}, 
	    		{85.0,20.0,15.0,15.0}, 
	    			{85.0,20.0,111.0,15.0}, 
	    				{85.0,20.0,207.0,15.0}, 
					    	{85.0,20.0,303.0,15.0}, 
					    		{85.0,20.0,399.0,15.0}, 
					    			{295.0,20.0,495.0,15.0}, 
					    				{85.0,20.0,801.0,15.0} }; 
					    				
	    Componentes comPersistencia = new Componentes();
	    comPersistencia.popularTela(listNodesPersistencia, prefSizeWHeLayXY, pTelaProcesso);
	    
	    tcNumeroProcesso.setCellValueFactory(new PropertyValueFactory<Processo, String>("proSEI"));
	    tcDataCriacao.setCellValueFactory(new PropertyValueFactory<Processo, String>("proDataCriacao"));
	    tcInteressado.setCellValueFactory(new PropertyValueFactory<Processo, String>("proInteressado"));
	    
	    tcNumeroProcesso.setPrefWidth(200.0D);
	    tcDataCriacao.setPrefWidth(200.0D);
	    tcInteressado.setPrefWidth(480.0D);
	    
	    tvListaProcessos.setPrefSize(900.0D, 185.0D);
	    tvListaProcessos.setLayoutX(25.0D);
	    tvListaProcessos.setLayoutY(228.0D);
	    
	    tvListaProcessos.getColumns().add(tcNumeroProcesso);
	    tvListaProcessos.getColumns().add(tcDataCriacao);
	    tvListaProcessos.getColumns().add(tcInteressado);
	    
	    tvListaProcessos.setItems(obsListProcessos);
	    
	    pTelaProcesso.getChildren().add(tvListaProcessos);
	    
	    lblDataAtualizacaoProcesso = new Label();
	    
	    lblDataAtualizacaoProcesso.setPrefSize(247.0D, 22.0D);
	    lblDataAtualizacaoProcesso.setLayoutX(677.0D);
	    lblDataAtualizacaoProcesso.setLayoutY(425.0D);
	    
	    pTelaProcesso.getChildren().add(lblDataAtualizacaoProcesso);
	    
	    tcDemanda.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demDocumento"));
	    tcDemandaSEI.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demDocumentoSEI"));
	    tcDemandaProcessoSEI.setCellValueFactory(new PropertyValueFactory<Demanda, String>("demProcessoSEI"));
	    
	    tcDemanda.setPrefWidth(330.0D);
	    tcDemandaSEI.setPrefWidth(190.0D);
	    tcDemandaProcessoSEI.setPrefWidth(220.0D);
	    
	    tvDemandas.setPrefSize(765.0D, 150.0D);
	    tvDemandas.setLayoutX(93.0D);
	    tvDemandas.setLayoutY(460.0D);
	    
	    tvDemandas.getColumns().add(tcDemanda);
	    tvDemandas.getColumns().add(tcDemandaSEI);
	    tvDemandas.getColumns().add(tcDemandaProcessoSEI);
	    
	    tvDemandas.setItems(obsListDemandas);
	    
	    pTelaProcesso.getChildren().add(tvDemandas);
	    
	    modularBotoesProcesso();
	    
	    acionarBotoes ();
	
	} // FIM INITIALIZE
	
  public void acionarBotoes () {
	  

	    btnDemanda.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	TabDemandaControlador.tabDemCon.movimentarTelaProcesso(15.0);
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
      a.alertar(new Alert(Alert.AlertType.ERROR, "erro na conex�o, tente novamente!", new ButtonType[] { ButtonType.OK }));
      
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
		    	
		      Processo pro = (Processo)tvListaProcessos.getSelectionModel().getSelectedItem();
		      
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
    	
	      Processo pro = (Processo)tvListaProcessos.getSelectionModel().getSelectedItem();
	      
	      int id = pro.getProID();
	      
	      ProcessoDao proDao = new ProcessoDao();
	      
	      proDao.removerProcesso(Integer.valueOf(id));
	      
	      obsListProcessos.remove(pro);
	      
	      modularBotoesProcesso();
	      
	      Alerta a = new Alerta();
	      a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro exclu�do com sucesso!!!", new ButtonType[] { ButtonType.OK }));
	      
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
    	
      p.getProID();
      p.getProSEI();
      
      obsListProcessos.add(p);
    }
    
    tvListaProcessos.setItems(obsListProcessos);
 }
		  

}
