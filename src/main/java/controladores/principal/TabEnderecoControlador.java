package controladores.principal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import controladores.fiscalizacao.TabVistoriaControlador;
import dao.EnderecoDao;
import entidades.Demanda;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.RA;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import mapas.GoogleMap;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;

public class TabEnderecoControlador implements Initializable {
	
	// para transmitir o endereco para  outras tabs //
	TabInterferenciaControlador tabIntCon = new TabInterferenciaControlador();
	TabUsuarioControlador tabUsCon = new TabUsuarioControlador();
	TabVistoriaControlador tabVisCon = new TabVistoriaControlador();
	
	// para trazer a demanda cadastrada e relacionado com o endereco //
	Demanda demanda = new Demanda ();
	
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
	
	Pane pMap;

	
	Label lblDataAtualizacao = new Label();
	
	//-- TableView endereco --//
	private TableView <Endereco> tvLista = new TableView<>();

	TableColumn<Endereco, String> tcDesEnd = new TableColumn<>("Endereço");
	TableColumn<Endereco, String> tcEndRA = new TableColumn<>("Região Administrativa");
	TableColumn<Endereco, String> tcEndCid = new TableColumn<>("CEP");

	int intRA = 1;
	String strRA = "Plano Piloto";
	
	final int [] listaRA = new int [] {
			
			20	,4	,19	,9	,11	,31	,2	,10	,28	,27	,18	,16	,8	,7	,24	,6	,
			1	,15	,17	,21	,12	,13	,14	,25	,29	,5	,26	,22	,3	,23	,30	,

	};
	
	
	ObservableList<String> olEndRA = FXCollections
	.observableArrayList(
			
			"Águas Claras"	,
			"Brazlândia"	,
			"Candangolândia"	,
			"Ceilândia"	,
			"Cruzeiro"	,
			"Fercal"	,
			"Gama"	,
			"Guará"	,
			"Itapoã"	,
			"Jardim Botânico"	,
			"Lago Norte"	,
			"Lago Sul"	,
			"Núcleo Bandeirante"	,
			"Paranoá"	,
			"Park Way"	,
			"Planaltina"	,
			"Plano Piloto"	,
			"Recanto das Emas"	,
			"Riacho Fundo"	,
			"Riacho Fundo II"	,
			"Samambaia"	,
			"Santa Maria"	,
			"São Sebastião"	,
			"SCIA/Estrutural"	,
			"SIA"	,
			"Sobradinho"	,
			"Sobradinho II"	,
			"Sudoeste/Octogonal"	,
			"Taguatinga"	,
			"Varjão"	,
			"Vicente Pires"	
			); 	
		
		//-- combobox - unidade federal --//

	
		ObservableList<String> olEndUF = FXCollections
				.observableArrayList("DF" , "GO", "Outro");

		
		Label lblEndereco = new Label();
		
		//-- string para chamar as coordenadas corretas do mapa --//
		String strHTMLMap;
		
		
		//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
		ObservableList<Endereco> obsList = FXCollections.observableArrayList();
		
	public void btnNovoHab () {
		
		tfLogradouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		tfCidade.setText("Brasília");
		
		cbUF.setValue("DF");
		
		tfLatitude.setText("");
		tfLongitude.setText("");
		
		
		tfLogradouro.setDisable(false);
		cbRA.setDisable(false);
		
		
		tfCEP.setDisable(false);
		tfCidade.setDisable(false);
		cbUF.setDisable(false);
		tfLatitude.setDisable(false);
		tfLongitude.setDisable(false);
	
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
	}
	
	public void btnSalvarHab () {
		
		if (tfLatitude.getText().isEmpty() || 
				tfLongitude.getText().isEmpty()) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
		} 
		
			else if (demanda == null) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Não há demanda selecionada!!!", ButtonType.OK));
				
			} 
		
				else {
				
				
					if (tfLogradouro.getText().isEmpty()) {
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o logradouro do empreendimento!!!", ButtonType.OK));
						
					} else {
					
							RA ra = new RA ();
								ra.setRaID(intRA);
								ra.setRaNome(strRA);
						
						Endereco end = new Endereco();
							
								end.setEndLogradouro(tfLogradouro.getText());
								end.setEndRAFK(ra);
									
								end.setEndCEP(tfCEP.getText());
								end.setEndCidade(tfCidade.getText());
								end.setEndUF(cbUF.getValue());
							
							try {
								
								end.setEndDDLatitude(Double.parseDouble(tfLatitude.getText()));
								end.setEndDDLongitude(Double.parseDouble(tfLongitude.getText()));
								
								GeometryFactory geoFac = new GeometryFactory();
								
								Point p = geoFac.createPoint(new Coordinate(
										Double.parseDouble(tfLongitude.getText()),
										Double.parseDouble(tfLatitude.getText()
										)));
								
								p.setSRID(4674);
									
								end.setEndGeom(p);
								
								end.setEndAtualizacao(
										Timestamp.valueOf((LocalDateTime.now())));
										
										Demanda dem = new Demanda ();
										
											dem = demanda;
											dem.setDemEnderecoFK(end);
											end.getDemandas().add(dem);
										
										EnderecoDao endDao = new EnderecoDao();
										
											endDao.salvarEndereco(end); //solução para recuperar o id do endereço
											endDao.mergeEndereco(end); // assim adiciona o id end na demanda dem
											
										// levar o endereco salvo para a tabinterferencia //	
										tabIntCon.setEndereco(end);
										tabUsCon.setEndereco(end);
										tabVisCon.setEndereco(end);
										
										
										//-- modular botoes--//
										modularBotoesInicial ();
										
										obsList.remove(end);
										obsList.add(end);
										
										modularBotoesInicial();
										
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", ButtonType.OK));
										
								} 
							
								catch (Exception e) {
									
									Alerta a = new Alerta ();
									a.alertar(new Alert(Alert.AlertType.ERROR, "erro ao salvar!!!", ButtonType.OK));
									
									e.printStackTrace();
								}
				}
			
		}
			
	}
	
	public void btnEditarHab () {
	
	
	if (tfLogradouro.isDisable()) {
		
		tfLogradouro.setDisable(false);
		cbRA.setDisable(false);
		tfCEP.setDisable(false);
		tfCidade.setDisable(false);
		cbUF.setDisable(false);
		tfLatitude.setDisable(false);
		tfLongitude.setDisable(false);
	
			
	} else {
		
		if (tfLatitude.getText().isEmpty()|| tfLongitude.getText().isEmpty() ) {
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			// colocar para não aceitar texto e somente número
			} 
			
			else if (demanda == null) {
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Não foi selecionado uma demanda!!!", ButtonType.OK));
			}
		
			else {
				
				RA ra = new RA ();
				ra.setRaID(intRA);
				ra.setRaNome(strRA);

				Endereco end = new Endereco ();
				
				end = tvLista.getSelectionModel().getSelectedItem();
					
				end.setEndLogradouro(tfLogradouro.getText());
				end.setEndRAFK(ra);
				end.setEndCEP(tfCEP.getText());
				end.setEndCidade(tfCidade.getText());
				end.setEndUF(cbUF.getValue());
				
				end.setEndDDLatitude(Double.parseDouble(tfLatitude.getText()));
				end.setEndDDLongitude(Double.parseDouble(tfLongitude.getText()));
				
				GeometryFactory geoFac = new GeometryFactory();
				
				Point p = geoFac.createPoint(new Coordinate(
						Double.parseDouble(tfLongitude.getText()),
						Double.parseDouble(tfLatitude.getText()
						)));
				
				p.setSRID(4674);
					
				end.setEndGeom(p);
				
				end.setEndAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
				
				Demanda dem = new Demanda();
				
				dem = demanda;
				dem.setDemEnderecoFK(end);
				
				// para não dar repeticao de objetos //
				for (int i = 0 ; i < end.getDemandas().size(); i++) {
					if (end.getDemandas().get(i).getDemID() == (dem.getDemID())) {
						end.getDemandas().remove(end.getDemandas().get(i));
					}
				}
				
				// adicionar a demanda editada //
				end.getDemandas().add(dem);
				
				// dao //
				EnderecoDao enderecoDao = new EnderecoDao();
			
				enderecoDao.mergeEndereco(end);
				
				tabIntCon.setEndereco(end);
				tabUsCon.setEndereco(end);
				tabVisCon.setEndereco(end);
				
				// atualizar a tableview //
				obsList.remove(end);
				obsList.add(end);
				
				modularBotoesInicial (); 
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!!!", ButtonType.OK));
					
			}
	
	}	
	
}

	public void btnExcluirHab () {
	
		Endereco end = tvLista.getSelectionModel().getSelectedItem();
		
		int id = end.getEndID();
		
		EnderecoDao endDao = new EnderecoDao();
		
			try {
				
				endDao.removerEndereco(id);
				
				tabIntCon.setEndereco(null);
				tabUsCon.setEndereco(null);
				tabVisCon.setEndereco(null);
				
				obsList.remove(end);
				
				modularBotoesInicial();
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro deletado com sucesso!!!", ButtonType.OK));
				
		
					}
		
					catch (Exception e) {
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Há denúncia associada a este endereço!", ButtonType.OK));
						
					}
		
}

	public void btnCancelarHab () {
	
		modularBotoesInicial ();
		
		tfLogradouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		
		cbUF.setValue(null);
		
		tfLatitude.setText("");
		tfLongitude.setText("");
	
}

	public void btnPesquisarHab () {
	
	
		strPesquisa = tfPesquisar.getText();
		
		listarEnderecos (strPesquisa);
	
		modularBotoesInicial (); 
	
}

	/*
	 * capturar as coordenadas clicadas no mapa e trazer para o cadastro do endereco
	 */
	public void capturarLatitudeLongitude () {
		
		tfLatitude.setText( ControladorPrincipal.capturarGoogleMaps().getLat() );
		tfLongitude.setText( ControladorPrincipal.capturarGoogleMaps().getLon());
		
	}


	// pane para o mapa
	Pane pEnderecoMapa = new Pane();
	
	Pane pDemanda;
	Label lblDemanda;
		Button btnDemanda;

			ArrayList<Node> listNodesDemanda = new ArrayList<Node>();
			
			Pane pDadosEndereco;
			TextField tfLogradouro;
				ComboBox<String> cbRA;
					TextField tfCEP;
						TextField tfCidade;
							ComboBox<String> cbUF;
								TextField tfLatitude;
									TextField tfLongitude;
										Button btnLatLon;
									
										ArrayList<Node> listNodeDadosEndereco = new ArrayList<Node>();
								
										 Pane pPersistencia;
										  	Button btnNovo;
										  		Button btnSalvar;
										  			Button btnEditar;
										  				Button btnExcluir;
										  					Button btnCancelar;
										  						Button btnPesquisar;
										  						
										  							TextField tfPesquisar;
										  							
										  								ArrayList<Node> listNodesPersistencia= new ArrayList<Node>();
										  								
			
	
			
	
	public static TabEnderecoControlador tabEndCon;
	
	@FXML Pane pEndereco;
	  
	  Pane p1 = new Pane();
	  BorderPane bp1 = new BorderPane();
	  BorderPane bp2 = new BorderPane();
	  ScrollPane sp = new ScrollPane();
	  Pane pMapa = new Pane();
	  
	  /* array de posicoes prefWidth prefHeight Layout Y e X */
		Double prefSizeWHeLayXY [][];
		
	  Componentes com;
	  
	public void initialize(URL url, ResourceBundle rb) {
		
		tabEndCon = this;
		
		 bp1.minWidthProperty().bind(pEndereco.widthProperty());
		    bp1.maxHeightProperty().bind(pEndereco.heightProperty().subtract(60));
		    
		    bp1.getStyleClass().add("border-pane");
		    
		    bp2.setPrefHeight(800.0D);
		    bp2.minWidthProperty().bind(pEndereco.widthProperty());
		    
		    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		    
		    sp.setContent(bp2);
		    
		    bp1.setCenter(sp);
		    
		    pEndereco.getChildren().add(bp1);
		    
		    p1.setMaxSize(980.0, 1000.0);
		    p1.setMinSize(980.0, 1000.0);
		    
		    bp2.setTop(p1);
		    BorderPane.setAlignment(p1, Pos.CENTER);
		    
		    listNodesDemanda.add(pDemanda = new Pane());
			listNodesDemanda.add(new Label("DEMANDA:"));
				listNodesDemanda.add(lblDemanda = new Label());
					listNodesDemanda.add(btnDemanda = new Button("<<<"));
	    
	    prefSizeWHeLayXY = new Double [][] { 
    		{950.0,60.0,15.0,10.0}, 
    			{85.0,30.0,18.0,15.0}, 
    				{740.0,30.0,105.0,15.0}, 
    					{75.0,25.0,854.0,17.0}
    				};
	    	
	    com = new Componentes();
	    com.popularTela(listNodesDemanda, prefSizeWHeLayXY, p1);
	    
	    
	    listNodeDadosEndereco.add(pDadosEndereco = new Pane());
	    	listNodeDadosEndereco.add(new Label("ENDEREÇO DO EMPREENDIMENTO:"));
	    	listNodeDadosEndereco.add(tfLogradouro = new TextField());
	    		listNodeDadosEndereco.add(new Label("REGIÃO ADMINISTRATIVA:"));
	    		listNodeDadosEndereco.add(cbRA = new  ComboBox<>());
	    			listNodeDadosEndereco.add(new Label("CEP:"));
	    			listNodeDadosEndereco.add(tfCEP = new TextField());
					    listNodeDadosEndereco.add(new Label("CIDADE:"));
					    listNodeDadosEndereco.add(tfCidade = new TextField());
						    listNodeDadosEndereco.add(new Label("UF:"));
							listNodeDadosEndereco.add(cbUF = new  ComboBox<>());
								listNodeDadosEndereco.add(new Label("LATITUDE (Y):"));
							    listNodeDadosEndereco.add(tfLatitude = new TextField());
							    	listNodeDadosEndereco.add(new Label("LONGITUDE(X):"));
								    listNodeDadosEndereco.add(tfLongitude = new TextField());
								    	listNodeDadosEndereco.add(btnLatLon = new Button());
	    
	
	    prefSizeWHeLayXY = new Double [][] { 
			{930.0,130.0,25.0,83.0}, 
			
				{380.0,30.0,16.0,0.0}, 
				{380.0,30.0,16.0,30.0}, // tfLogradouro
					{150.0,30.0,407.0,0.0}, 
					{150.0,30.0,407.0,30.0}, // comboBox RA
						{100.0,30.0,570.0,0.0}, 
						{100.0,30.0,570.0,30.0}, // tfCEP
						
							{90.0,30.0,682.0,0.0}, 
							{90.0,30.0,682.0,30.0}, // tfCidadeade
							
								{100.0,30.0,784.0,0.0}, 
								{100.0,30.0,784.0,30.0}, // comobobx UF
						
									{100.0,30.0,185.0,78.0}, 
									{140.0,30.0,284.0,78.0},
									
										{100.0,30.0,435.0,78.0}, 
										{140.0,30.0,538.0,78.0},
				
											{25.0,25.0,689.0,81.0},
	    };
	    	
	    com = new Componentes();
	    com.popularTela(listNodeDadosEndereco, prefSizeWHeLayXY, p1);
	    
	    cbRA.setItems(olEndRA);
	    cbUF.setItems(olEndUF);
	    
	    
	    listNodesPersistencia.add(pPersistencia = new Pane());
	    listNodesPersistencia.add(btnNovo = new Button("NOVO"));
	    listNodesPersistencia.add(btnSalvar = new Button("SALVAR"));
	    listNodesPersistencia.add(btnEditar = new Button("EDITAR"));
	    listNodesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
	    listNodesPersistencia.add(btnCancelar = new Button("CANCELAR"));
	    listNodesPersistencia.add(tfPesquisar = new TextField());
	    listNodesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	{930.0,60.0,25.0,225.0}, 
	    		{95.0,25.0,18.0,18.0}, 
	    			{95.0,25.0,123.0,18.0}, 
	    				{95.0,25.0,228.0,18.0}, 
					    	{95.0,25.0,333.0,18.0}, 
					    		{95.0,25.0,438.0,18.0}, 
					    			{265.0,25.0,543.0,18.0}, 
					    				{95.0,25.0,818.0,18.0} }; 
					    				
					    				 com = new Componentes();
					    				    com.popularTela(listNodesPersistencia, prefSizeWHeLayXY, p1);    
		    
	    
	    // para trazer o valor da entidade principal, no caso Endereco
	    tcDesEnd.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endLogradouro"));
		// para trazer valor de outra entidade, no caso RA
	    tcEndRA.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Endereco, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Endereco, String> e) {
		    	return new SimpleStringProperty(e.getValue().getEndRAFK().getRaNome());
		       
		    }
		});
		
		tcDesEnd.setPrefWidth(440);
		tcEndRA.setPrefWidth(232);
		tcEndCid.setPrefWidth(232);
			
	    tvLista.getColumns().add(tcDesEnd);
	    	tvLista.getColumns().add(tcEndRA); 
	    		tvLista.getColumns().add(tcEndCid); 
	    			tvLista.setItems(obsList);
	    
					    tvLista.setPrefSize(930, 185);
							tvLista.setLayoutX(25);
								tvLista.setLayoutY(300);
								
	    
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(707);
	    lblDataAtualizacao.setLayoutY(500);
	  
	    
		// para trazer o valor da entidade principal, no caso Endereco
		//tcEndCid.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endCEP")); // endCEP
	    
	    cbRA.setItems(olEndRA);
	    cbRA.setValue("Plano Piloto");
	    cbUF.setItems(olEndUF);
	    
	    
	    pEnderecoMapa.setPrefSize(930, 400);
	    pEnderecoMapa.setLayoutX(25);
	    pEnderecoMapa.setLayoutY(530);
	    pEnderecoMapa.getChildren().add(googleMaps);
	    
	    googleMaps.setWidth(930);
	    googleMaps.setHeight(400);
	    googleMaps.switchHybrid();
	    
	    p1.getChildren().addAll(tvLista,lblDataAtualizacao, pEnderecoMapa);
	    
	    cbRA.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		intRA = listaRA[(int) new_value];
	    		
            }
	    });
	    
	    cbRA.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	strRA = (String) newValue 
	    );
	    
	    // ao abrir fechar os campos para edicao //
	    modularBotoesInicial();
	    // ativar na tableview a possibilidade e selecionar uma opcao //
	    selecionarEndereco();
	   
	    btnNovo.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnNovoHab();
	        }
	    });
		    
	    btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnSalvarHab();
	        }
	    });
	    
	    btnEditar.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnEditarHab();
	        }
	    });
	    
	    btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnCancelarHab();
	        }
	    });
	    
	    btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnPesquisarHab();
	        }
	    });
	    
	    btnLatLon.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	capturarLatitudeLongitude ();
	        }
	    });
	    
	    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnExcluirHab();
	        }
	    });
	    
	   
	    	
	}
	
	
	//-- Modular os botoes na inicializacao do programa --//
	private void modularBotoesInicial () {
		
		tfLogradouro.setDisable(true);
		cbRA.setDisable(true);
		tfCEP.setDisable(true);
		tfCidade.setDisable(true);
		cbUF.setDisable(true);
		tfLatitude.setDisable(true);
		tfLongitude.setDisable(true);
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		
	}
	
	// --- metodo para listar endereco --- //
	 	public void listarEnderecos (String strPesquisa) {
	 		
	 	// --- conexao - listar enderecos --- //
		EnderecoDao enderecoDao = new EnderecoDao();
		List<Endereco> enderecoList = enderecoDao.listarEndereco(strPesquisa);
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
		
				
		// funcionando
    	List<Endereco> iList = enderecoList;
    	
    	
    	for (Endereco e : iList) {
    		
    		e.getEndID();
    		e.getEndLogradouro();
    		e.getEndRAFK();
    		e.getEndCEP();
    		e.getEndCidade();
    		e.getEndUF();
    		e.getEndDDLatitude();
    		e.getEndDDLongitude();
    		e.getDemandas();
    		
    		obsList.add(e);
    		
		}
		
	}
	
	GoogleMap googleMaps = new GoogleMap();
	
	// método selecionar endereço -- //
	 	public void selecionarEndereco () {
		
			tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Endereco end = (Endereco) newValue;
				
				if (end == null) {
					
					tfLogradouro.setText("");
					
					tfCEP.setText("");
					tfCidade.setText("");
					tfLatitude.setText("");
					tfLongitude.setText("");
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					tfLogradouro.setText(end.getEndLogradouro());
					
					cbRA.setValue(end.getEndRAFK().getRaNome()); 
					
					tfCEP.setText(end.getEndCEP());
					tfCidade.setText(end.getEndCidade());
					
					cbUF.setValue(end.getEndUF());
					
					tfLatitude.setText(end.getEndDDLatitude().toString());
					tfLongitude.setText(end.getEndDDLongitude().toString());
					
					//tabIntCon.setEndereco(end);
					//tabUsCon.setEndereco(end);
					//tabVisCon.setEndereco(end);
					
					// -- habilitar e desabilitar botoes -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					
					/*
					if (end.getDemandas().size() != 0) { // colocar regra de só pode enditar escolhendo uma demanda...
						demanda = end.getDemandas().get(0);
					}
					*/
					
					
					// mostrar data de atualizacao //
					FormatoData d = new FormatoData();
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(end.getEndAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
						
//aquii 	ao excluir um endereco  Index: 0, Size: 0	
					
					try {
					// setar a demanda 0 do endereco selecionado // 
						setDemanda (end.getDemandas().get(0)); 
					
					} catch (IndexOutOfBoundsException ex) {
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Não há demandas cadastradas para este endereço!!!", ButtonType.OK));
					}
					
					// setar na interferencia (tabinterferencia) este endereco selecinado //
					tabIntCon.setEndereco(end);
					tabUsCon.setEndereco(end);
					tabVisCon.setEndereco(end);
					
					// Abrir o mapa com o endereco selecionado e interferencias
					
						// listar as interferencias
						List<Interferencia> iList = end.getInterferencias();
						
							// preparar strings para transmitir para o javascript pelo metodo 'setEnderecoInterferencias()'
							String strInterferencias = "";
					
							String strEndereco = end.getEndDDLatitude() + "," + end.getEndDDLongitude();
							/* string para os detalhes das  interferencias como tipo 
							de interferencia, bacia hid, uh e situação do processo */
							String strDetalhes = "";
					
					for(Interferencia i : iList) {
						
						strInterferencias += "|" + i.getInterDDLatitude() + "," + i.getInterDDLongitude() ;
						
						strDetalhes += "|" + i.getInterTipoInterferenciaFK().getTipoInterDescricao() 
										+ "," + i.getInterBaciaFK().getBaciaNome()
										+ "," + i.getInterUHFK().getUhID()
										+ "," +  i.getInterSituacaoProcessoFK().getSituacaoProcessoDescricao();
						
						
						System.out.println("strInterferencias: " + strInterferencias);
						
						System.out.println("str detalhes: " + strDetalhes);
						
					} // fim loop for
					
					/* chamar os metodo necessarios, primeiro as coordenadas e detalhes, 
						zoom do mapa e deois centralizar o mapa de acordo com o endereco
						*/
					googleMaps.setEnderecoInterferencias(strEndereco, strInterferencias, strDetalhes);
					googleMaps.setZoom (13);
					googleMaps.setMapCenter(end.getEndDDLatitude(), end.getEndDDLongitude());
					
				}
				
				}
				
			});
			
		}
	

}

