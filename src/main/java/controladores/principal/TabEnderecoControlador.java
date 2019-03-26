package controladores.principal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import mapas.GoogleMap;
import principal.Alerta;
import principal.FormatoData;

public class TabEnderecoControlador implements Initializable {
	
	// para transmitir o endereco para  outras tabs //
	TabInterferenciaControlador tabIntCon = new TabInterferenciaControlador();
	TabUsuarioControlador tabUsCon = new TabUsuarioControlador();
	TabVistoriaControlador tabVisCon = new TabVistoriaControlador();
	
	// para trazer a demanda cadastrada e relacionado com o endereco //
	static Demanda demanda = new Demanda ();
	
	public void setDemanda (Demanda demanda) {
		
		TabEnderecoControlador.demanda = demanda;
		// preencher o label com a demanda selecionada //
		TabEnderecoControlador.lblDemanda.setText(
				demanda.getDemDocumento() 
				+ ", Sei n° " + demanda.getDemDocumentoSEI()
				+ ", Processo n° " + demanda.getDemProcessoSEI()
				);
	}
	
	public static Demanda getDemanda () {
		
		return demanda;
	}
	
	TextField tfEnd = new TextField();
	
	TextField tfCep = new TextField();
	TextField  tfCid = new TextField();
	
	TextField tfLat = new TextField();
	TextField tfLon = new TextField();
	
	Button btnNovo = new Button("Novo");
	Button btnSalvar = new Button("Salvar");
	Button btnEditar = new Button("Editar");
	Button btnExcluir = new Button("Excluir");
	Button btnCancelar = new Button("Cancelar");
	Button btnPesquisar = new Button("Pesquisar");
	TextField tfPesquisar = new TextField();
	
	
	Button btnCoord = new Button();
	Pane pMap;

	Button btnMaps = new Button();
	
	Label lblDataAtualizacao = new Label();
	
	//-- TableView endereco --//
	private TableView <Endereco> tvLista = new TableView<>();

	TableColumn<Endereco, String> tcDesEnd = new TableColumn<>("Endereço");
	TableColumn<Endereco, String> tcEndRA = new TableColumn<>("Região Administrativa");
	TableColumn<Endereco, String> tcEndCid = new TableColumn<>("CEP");
	
	static Label lblDemanda = new Label();
	
	int intRA = 1;
	String strRA = "Plano Piloto";
	
	final int [] listaRA = new int [] {
			
			20	,4	,19	,9	,11	,31	,2	,10	,28	,27	,18	,16	,8	,7	,24	,6	,
			1	,15	,17	,21	,12	,13	,14	,25	,29	,5	,26	,22	,3	,23	,30	,

	};
	
	@FXML
	ChoiceBox<String> cbEndRA = new ChoiceBox<String>();
	
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

		ChoiceBox<String> cbEndUF = new ChoiceBox<String>();
			ObservableList<String> olEndUF = FXCollections
				.observableArrayList("DF" , "GO", "Outro");

		
		Label lblEndereco = new Label();
		
		//-- string para chamar as coordenadas corretas do mapa --//
		String strHTMLMap;
		
		
		//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
		ObservableList<Endereco> obsList = FXCollections.observableArrayList();
		
	public void btnNovoHab () {
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfCep.setText("");
		tfCid.setText("Brasília");
		
		cbEndUF.setValue("DF");
		
		tfLat.setText("");
		tfLon.setText("");
		
		
		tfEnd.setDisable(false);
		cbEndRA.setDisable(false);
		
		
		tfCep.setDisable(false);
		tfCid.setDisable(false);
		cbEndUF.setDisable(false);
		tfLat.setDisable(false);
		tfLon.setDisable(false);
	
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
	}
	
	public void btnSalvarHab () {
		
		if (tfLat.getText().isEmpty() || 
				tfLon.getText().isEmpty()) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
		} 
		
			else if (demanda == null) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Não há demanda selecionada!!!", ButtonType.OK));
				
			} 
		
				else {
				
				
					if (tfEnd.getText().isEmpty()) {
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o logradouro do empreendimento!!!", ButtonType.OK));
						
					} else {
					
							RA ra = new RA ();
								ra.setRaID(intRA);
								ra.setRaNome(strRA);
						
						Endereco end = new Endereco();
							
								end.setEndLogradouro(tfEnd.getText());
								end.setEndRAFK(ra);
									
								end.setEndCEP(tfCep.getText());
								end.setEndCidade(tfCid.getText());
								end.setEndUF(cbEndUF.getValue());
							
							try {
								
								end.setEndDDLatitude(Double.parseDouble(tfLat.getText()));
								end.setEndDDLongitude(Double.parseDouble(tfLon.getText()));
								
								GeometryFactory geoFac = new GeometryFactory();
								
								Point p = geoFac.createPoint(new Coordinate(
										Double.parseDouble(tfLon.getText()),
										Double.parseDouble(tfLat.getText()
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
	
	
	if (tfEnd.isDisable()) {
		
		tfEnd.setDisable(false);
		cbEndRA.setDisable(false);
		tfCep.setDisable(false);
		tfCid.setDisable(false);
		cbEndUF.setDisable(false);
		tfLat.setDisable(false);
		tfLon.setDisable(false);
	
			
	} else {
		
		if (tfLat.getText().isEmpty()|| tfLon.getText().isEmpty() ) {
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
					
				end.setEndLogradouro(tfEnd.getText());
				end.setEndRAFK(ra);
				end.setEndCEP(tfCep.getText());
				end.setEndCidade(tfCid.getText());
				end.setEndUF(cbEndUF.getValue());
				
				end.setEndDDLatitude(Double.parseDouble(tfLat.getText()));
				end.setEndDDLongitude(Double.parseDouble(tfLon.getText()));
				
				GeometryFactory geoFac = new GeometryFactory();
				
				Point p = geoFac.createPoint(new Coordinate(
						Double.parseDouble(tfLon.getText()),
						Double.parseDouble(tfLat.getText()
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
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfCep.setText("");
		
		cbEndUF.setValue(null);
		
		tfLat.setText("");
		tfLon.setText("");
	
}

	public void btnPesquisarHab () {
	
	
		strPesquisa = tfPesquisar.getText();
		
		listarEnderecos (strPesquisa);
	
		modularBotoesInicial (); 
	
}

	/*
	 * capturar as coordenadas clicadas no mapa e trazer para o cadastro do endereco
	 */
	public void btnMapsHab () {
		
		tfLat.setText( ControladorPrincipal.capturarGoogleMaps().getLat() );
		tfLon.setText( ControladorPrincipal.capturarGoogleMaps().getLon());
		
	}

	
	@FXML Pane pEndereco;
	AnchorPane apPrincipal = new AnchorPane();
	BorderPane bpPrincipal = new BorderPane();
	ScrollPane spPrincipal = new ScrollPane();
	Pane p1 = new Pane ();
	
	// pane para mostrar a demanda envolvida no crud
	Pane p_lblDemanda = new Pane();
	Pane pDadosBasicos = new Pane();
	Pane pPersistencia = new Pane();
	
	// pane para o mapa
	Pane pEnderecoMapa = new Pane();
	
	@SuppressWarnings("unchecked")
	public void initialize(URL url, ResourceBundle rb) {
		
		pEndereco.getChildren().add(apPrincipal);
		
		apPrincipal.minWidthProperty().bind(pEndereco.widthProperty());
		apPrincipal.minHeightProperty().bind(pEndereco.heightProperty());
		
		apPrincipal.getChildren().add(spPrincipal);
		
		spPrincipal.setHbarPolicy(ScrollBarPolicy.NEVER);
		spPrincipal.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
	    AnchorPane.setLeftAnchor(spPrincipal, 0.0);
		AnchorPane.setRightAnchor(spPrincipal, 0.0);
		AnchorPane.setTopAnchor(spPrincipal, 0.0);
		AnchorPane.setBottomAnchor(spPrincipal, 47.0);
		
		spPrincipal.setPrefSize(200, 200);
		
	    bpPrincipal.minWidthProperty().bind(spPrincipal.widthProperty());
	    bpPrincipal.setPrefHeight(1200);

	    spPrincipal.setContent(bpPrincipal);
	    
	    p1.setMaxSize(1140, 680);
	    p1.setMinSize(1140, 680);
	    
		bpPrincipal.setTop(p1);
	    BorderPane.setAlignment(p1, Pos.CENTER);
	    
	    // para trazer o valor da entidade principal, no caso Endereco
	    tcDesEnd.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endLogradouro"));
		// para trazer valor de outra entidade, no caso RA
	    tcEndRA.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Endereco, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Endereco, String> e) {
		    	return new SimpleStringProperty(e.getValue().getEndRAFK().getRaNome());
		       
		    }
		});
		
		tcDesEnd.setPrefWidth(409);
		tcEndRA.setPrefWidth(232);
		tcEndCid.setPrefWidth(232);
			
	    tvLista.getColumns().addAll(tcDesEnd, tcEndRA, tcEndCid);
	    tvLista.setItems(obsList);
	    
	    tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(120);
		tvLista.setLayoutY(298);
	    
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(772);
	    lblDataAtualizacao.setLayoutY(493);
	    
	    obterDemanda ();
	    obterDadosBasicos ();
	    obterPersistencia ();
	    
		// para trazer o valor da entidade principal, no caso Endereco
		//tcEndCid.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endCEP")); // endCEP
	    
	    cbEndRA.setItems(olEndRA);
	    cbEndRA.setValue("Plano Piloto");
	    cbEndUF.setItems(olEndUF);
	    
	    
	    pEnderecoMapa.setPrefSize(900, 400);
	    pEnderecoMapa.setLayoutX(120);
	    pEnderecoMapa.setLayoutY(526);
	    pEnderecoMapa.getChildren().add(googleMaps);
	    googleMaps.setWidth(900);
	    googleMaps.setHeight(400);
	    googleMaps.switchHybrid();
	    
	    p1.getChildren().addAll(p_lblDemanda, pDadosBasicos, pPersistencia, lblDataAtualizacao, tvLista, pEnderecoMapa);
	    
	    cbEndRA.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		intRA = listaRA[(int) new_value];
	    		
            }
	    });
	    
	    cbEndRA.getSelectionModel()
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
	    
	    btnMaps.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	 btnMapsHab ();
	        }
	    });
	    
	    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            btnExcluirHab();
	        }
	    });
	    
	   
	    	
	}
	
	public void obterDemanda () {
		
		p_lblDemanda.setPrefSize(900, 50);
		p_lblDemanda.setLayoutX(120);
		p_lblDemanda.setLayoutY(20);
		p_lblDemanda.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblDem = new Label ("Documento: ");
		lblDem.setLayoutX(20);
		lblDem.setLayoutY(16);
		
		// Label para preencher com a demanda a ser trabalhada //
	    lblDemanda.setStyle("-fx-font-weight: bold;");
	    lblDemanda.setPrefSize(750, 25);	
		lblDemanda.setLayoutX(95);
		lblDemanda.setLayoutY(13);
		
		btnCoord.setPrefSize(25, 25);
		btnCoord.setLayoutX(856);
		btnCoord.setLayoutY(13);
		
		p_lblDemanda.getChildren().addAll(lblDem, lblDemanda, btnCoord);
		
	}
	
	public void obterDadosBasicos () {
		
		pDadosBasicos.setPrefSize(900, 147);
		pDadosBasicos.setLayoutX(120);
		pDadosBasicos.setLayoutY(80);
		pDadosBasicos.setStyle("-fx-background-color: #E9E9E9;");
		
		
		Label lblEnd = new Label ("Endereço do  Empreendimento: ");
		lblEnd.setLayoutX(12);
		lblEnd.setLayoutY(38);
		
			tfEnd.setPrefSize(424, 25);
			tfEnd.setLayoutX(13);
			tfEnd.setLayoutY(61);

		Label lblRA = new Label ("Região Administrativa: ");
		lblRA.setLayoutX(450);
		lblRA.setLayoutY(38);
		
			cbEndRA.setPrefSize(164	, 25);
			cbEndRA.setLayoutX(449);
			cbEndRA.setLayoutY(61);
		
		Label lblCep = new Label ("Cep: ");
		lblCep.setLayoutX(626);
		lblCep.setLayoutY(38);
		
			tfCep.setPrefSize(100, 25);
			tfCep.setLayoutX(626);
			tfCep.setLayoutY(61);
		
		Label lblCid = new Label ("Cidade: ");
		lblCid.setLayoutX(735);
		lblCid.setLayoutY(38);
		
			tfCid.setPrefSize(87, 25);
			tfCid.setLayoutX(735);
			tfCid.setLayoutY(61);
		
		Label lblUF = new Label ("UF: ");
		lblUF.setLayoutX(834);
		lblUF.setLayoutY(38);
		
			cbEndUF.setPrefSize(55	, 25);
			cbEndUF.setLayoutX(833);
			cbEndUF.setLayoutY(61);
		
		Label lblLat = new Label ("Latitude (Y): ");
		lblLat.setLayoutX(203);
		lblLat.setLayoutY(112);
		
			tfLat.setPrefSize(140, 25);
			tfLat.setPromptText("-15.7754084");
			tfLat.setLayoutX(280);
			tfLat.setLayoutY(108);
		
		Label lblLon = new Label ("Longitude (X): ");
		lblLon.setLayoutX(431);
		lblLon.setLayoutY(112);
			
			tfLon.setPrefSize(140, 25);
			tfLon.setPromptText("-47.9411395");
			tfLon.setLayoutX(519);
			tfLon.setLayoutY(108);
			
		btnMaps.setPrefSize(25, 25);	
		btnMaps.setLayoutX(670);
		btnMaps.setLayoutY(108);
		
		
		pDadosBasicos.getChildren().addAll(
				lblEnd, tfEnd, lblRA, cbEndRA, lblCep, tfCep, lblCid, tfCid, lblUF, cbEndUF,
				lblLat, tfLat, lblLon, tfLon,
				btnMaps
				
				);
	}
	
    public void obterPersistencia () {
    	
   	    pPersistencia.setPrefSize(900, 50);
   	    pPersistencia.setLayoutX(120);
   	    pPersistencia.setLayoutY(233);
   
		btnNovo.setPrefSize(76, 25);
		btnNovo.setLayoutX(42);
		btnNovo.setLayoutY(12);
	
	    btnSalvar.setPrefSize(76, 25);
	    btnSalvar.setLayoutX(129);
	    btnSalvar.setLayoutY(12);
	
	    btnEditar.setPrefSize(76, 25);
	    btnEditar.setLayoutX(216);
	    btnEditar.setLayoutY(12);
	
	    btnExcluir.setPrefSize(76, 25);
	    btnExcluir.setLayoutX(303);
	    btnExcluir.setLayoutY(12);
	    
	    btnCancelar.setPrefSize(76, 25);
	    btnCancelar.setLayoutX(390);
	    btnCancelar.setLayoutY(12);
	    
	    btnPesquisar.setPrefSize(76, 25);
	    btnPesquisar.setLayoutX(783);
	    btnPesquisar.setLayoutY(12);
	    
	    tfPesquisar.setPrefSize(295, 25);
	    tfPesquisar.setLayoutX(477);
	    tfPesquisar.setLayoutY(12);
	    
	    pPersistencia.getChildren().addAll( 
	    		btnNovo, btnSalvar, btnEditar, btnExcluir,
	    		btnCancelar, tfPesquisar, btnPesquisar
	    		
	    		);
	    
	    
    }
	
	//-- Modular os botoes na inicializacao do programa --//
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		cbEndRA.setDisable(true);
		tfCep.setDisable(true);
		tfCid.setDisable(true);
		cbEndUF.setDisable(true);
		tfLat.setDisable(true);
		tfLon.setDisable(true);
		
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
					
					tfEnd.setText("");
					
					tfCep.setText("");
					tfCid.setText("");
					tfLat.setText("");
					tfLon.setText("");
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					tfEnd.setText(end.getEndLogradouro());
					
					cbEndRA.setValue(end.getEndRAFK().getRaNome()); 
					
					tfCep.setText(end.getEndCEP());
					tfCid.setText(end.getEndCidade());
					
					cbEndUF.setValue(end.getEndUF());
					
					tfLat.setText(end.getEndDDLatitude().toString());
					tfLon.setText(end.getEndDDLongitude().toString());
					
					tabIntCon.setEndereco(end);
					tabUsCon.setEndereco(end);
					tabVisCon.setEndereco(end);
					
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

