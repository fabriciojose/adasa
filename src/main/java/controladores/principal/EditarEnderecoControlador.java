package controladores.principal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import controladores.principal.ControladorPrincipal;
import dao.EnderecoDao;
import entidades.Demanda;
import entidades.Endereco;
import entidades.RA;
import entidades.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import principal.Alerta;
import principal.FormatoData;


public class EditarEnderecoControlador implements Initializable {
	
	static Object objetoDeEdicao = new Object();
	
	public void setObjetoDeEdicao (Object objetoDeEdicao) {
		
		EditarEnderecoControlador.objetoDeEdicao = objetoDeEdicao;
		
	}
	
	@FXML Pane tabEndereco;
	@FXML Button btnBuscarDoc;
	@FXML TextField tfEnd;
	
	@FXML TextField tfEndCep;
	@FXML TextField  tfEndCid;
	
	@FXML TextField tfLinkEnd;
	@FXML TextField tfEndLat;
	@FXML TextField tfEndLon;
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	@FXML Button btnEndCoord;
	@FXML Pane pEndMap;
	@FXML Pane pEndereco;
	
	@FXML Button btnEndMaps;
	
	
	@FXML Button btnDenAtualizar;
	@FXML TextField tfEndPesq;
	
	@FXML Button btnEndLatLon;
	
	@FXML Pane pEndCoord;
	
	@FXML Label lblDataAtualizacao;
	
	@FXML Pane p_lblDemanda;
	@FXML Label lblDemanda1;
	Label lblDemanda2;
	
	//-- TableView endereco --//
	@FXML private TableView <Endereco> tvLista;
	
	@FXML TableColumn<Endereco, String> tcDesEnd;
	@FXML TableColumn<Endereco, String> tcEndRA;
	@FXML TableColumn<Endereco, String> tcEndCid;
	
	@FXML TextField tfPesquisar;
	
	//-- combobox -Regiao Administrativa --//	
	//List <String> listaRA = new ArrayList<> ();
	
	int intRA = 1;
	String strRA = "Plano Piloto";
	
	final int [] listaRA = new int [] {
			
			20	,
			4	,
			19	,
			9	,
			11	,
			31	,
			2	,
			10	,
			28	,
			27	,
			18	,
			16	,
			8	,
			7	,
			24	,
			6	,
			1	,
			15	,
			17	,
			21	,
			12	,
			13	,
			14	,
			25	,
			29	,
			5	,
			26	,
			22	,
			3	,
			23	,
			30	,

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
		@FXML
		ChoiceBox<String> cbEndUF = new ChoiceBox<String>();
			ObservableList<String> olEndUF = FXCollections
				.observableArrayList("DF" , "GO", "Outro");

		
		@FXML Label lblEndereco = new Label();
		
		//-- string para chamar as coordenadas corretas do mapa --//
		String strHTMLMap;
		
		
		//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
		ObservableList<Endereco> obsList = FXCollections.observableArrayList();
		
	public void btnNovoHab (ActionEvent event) {
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfEndCep.setText("");
		tfEndCid.setText("Brasília");
		
		cbEndUF.setValue("DF");
		
		tfLinkEnd.setText("");
		tfEndLat.setText("");
		tfEndLon.setText("");
		
		
		tfEnd.setDisable(false);
		cbEndRA.setDisable(false);
		
		
		tfEndCep.setDisable(false);
		tfEndCid.setDisable(false);
		cbEndUF.setDisable(false);
		tfEndLat.setDisable(false);
		tfEndLon.setDisable(false);
		tfLinkEnd.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
	}
	/*
	public void btnSalvarHab (ActionEvent event) {
		
		if (tfEndLat.getText().isEmpty() || 
				tfEndLon.getText().isEmpty()) {
			
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
						a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o logadouro do empreendimento!!!", ButtonType.OK));
						
					} else {
					
						RA ra = new RA ();
						ra.setRaID(intRA);
						ra.setRaNome(strRA);
						
						Endereco end = new Endereco();
							
							end.setEndLogadouro(tfEnd.getText());
							end.setEndRAFK(ra);
								
							end.setEndCEP(tfEndCep.getText());
							end.setEndCidade(tfEndCid.getText());
							end.setEndUF(cbEndUF.getValue());
							
							try {
								
								end.setEndDDLatitude(Double.parseDouble(tfEndLat.getText()));
								end.setEndDDLongitude(Double.parseDouble(tfEndLon.getText()));
								
								end.setEndAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
										
										Demanda dem = new Demanda ();
										
											dem = EditarEnderecoControlador.getDemanda();
											dem.setDemEnderecoFK(end);
											end.getDemandas().add(dem);
										
										EnderecoDao endDao = new EnderecoDao();
										
											endDao.salvarEndereco(end); //solução para recuperar o id do endereço
											endDao.mergeEndereco(end); // assim adiciona o id end na demanda dem
										
										//-- modular botoes--//
										modularBotoesInicial ();
										
										obsList.remove(end);
										obsList.add(end);
										
										
										selecionarEndereco();
										
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
	
	*/
	public void editarEndereco (Object objetoDeEdicao) {
		
		if (tfEnd.isDisable()) {
			
			tfEnd.setDisable(false);
			cbEndRA.setDisable(false);
			tfEndCep.setDisable(false);
			tfEndCid.setDisable(false);
			cbEndUF.setDisable(false);
			tfEndLat.setDisable(false);
			tfEndLon.setDisable(false);
			tfLinkEnd.setDisable(false);
				
		} else {
			
			if (tfEndLat.getText().isEmpty()|| tfEndLon.getText().isEmpty() ) {
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
				// colocar para não aceitar texto e somente número
				} 
				
				else if (objetoDeEdicao == null) {
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.ERROR, "não selecionado(a)!!!", ButtonType.OK));
				}
			
				else {
					
					RA ra = new RA ();
					ra.setRaID(intRA);
					ra.setRaNome(strRA);
	
					Endereco end = new Endereco ();
					
					end = tvLista.getSelectionModel().getSelectedItem();
						
					end.setEndLogradouro(tfEnd.getText());
					end.setEndRAFK(ra);
					end.setEndCEP(tfEndCep.getText());
					end.setEndCidade(tfEndCid.getText());
					end.setEndUF(cbEndUF.getValue());
					end.setEndDDLatitude(Double.parseDouble(tfEndLat.getText()));
					end.setEndDDLongitude(Double.parseDouble(tfEndLon.getText()));
					
					end.setEndAtualizacao(
							Timestamp.valueOf((LocalDateTime.now())));
					
					Demanda dem = new Demanda();
					Usuario us = new Usuario();
					
					if (objetoDeEdicao.getClass().getName().equals("entidades.Demanda")) {
						
						dem = (Demanda) objetoDeEdicao;
						dem.setDemEnderecoFK(end);
						
						// para não dar repeticao de objetos //
						for (int i = 0 ; i < end.getDemandas().size(); i++) {
							if (end.getDemandas().get(i).getDemID() == (dem.getDemID())) {
								end.getDemandas().remove(end.getDemandas().get(i));
							}
						}
						
						// adicionar a demanda editada //
						end.getDemandas().add(dem);
						
					}
					
					else if (objetoDeEdicao.getClass().getName().equals("entidades.Usuario")) {
						
						us = (Usuario) objetoDeEdicao;
						//us.setUsDataAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
						// adiciona neste endereco o id usuario selecionado
						end.setEndUsuarioFK(us);
						// adiciona este endereco no setEnderecos do usuario
						//us.getEnderecos().add(end);
						
					}
					
					// dao //
					EnderecoDao enderecoDao = new EnderecoDao();
				
					enderecoDao.mergeEndereco(end);
					
					// atualizar a tableview //
					obsList.remove(end);
					obsList.add(end);
					
					modularBotoesInicial (); 
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!!!", ButtonType.OK));
						
				}
		
		}
		
	}
	
	public void btnExcluirHab (ActionEvent event) {
		
		Endereco end = tvLista.getSelectionModel().getSelectedItem();
		
		int id = end.getEndID();
		
		EnderecoDao endDao = new EnderecoDao();
		
			try {
				
				endDao.removerEndereco(id);
				
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
	
	public void btnCancelarHab (ActionEvent event) {
		
		modularBotoesInicial ();
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfEndCep.setText("");
		
		cbEndUF.setValue(null);
		
		tfLinkEnd.setText("");
		tfEndLat.setText("");
		tfEndLon.setText("");
		
	}

	public void btnPesquisarHab (ActionEvent event) {
		
		
		strPesquisa = tfPesquisar.getText();
		
		listarEnderecos (strPesquisa);
		
		selecionarEndereco () ;
		
		modularBotoesInicial (); 
		
	}
	
	public void btnEndLatLonHab (ActionEvent event) {
		
	}
	
	/*
	 * capturar as coordenadas clicada no mapa e trazer para o cadastro do endereco 
	 */
	public void btnEndMapsHab (ActionEvent event) {
		
		tfEndLat.setText( ControladorPrincipal.capturarGoogleMaps().getLat() );
		tfEndLon.setText( ControladorPrincipal.capturarGoogleMaps().getLon());
		
	}
	
	public void btnEndCoordHab (ActionEvent event) {
		
	}
	
	@FXML AnchorPane apPrincipal = new AnchorPane();
	@FXML AnchorPane apPrin1 = new AnchorPane();
	@FXML AnchorPane apPrin2 = new AnchorPane();
	@FXML BorderPane bpPrincipal = new BorderPane();
	@FXML ScrollBar sbPrincipal = new ScrollBar();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	    // para trazer o valor da entidade principal, no caso Endereco
	    tcDesEnd.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endLogadouro")); // endLogadouro
		// para trazer valor de outra entidade, no caso RA
	    
		tcEndRA.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Endereco, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Endereco, String> e) {
		    	return new SimpleStringProperty(e.getValue().getEndRAFK().getRaNome());
		       
		    }
		});
		
		// para trazer o valor da entidade principal, no caso Endereco
		tcEndCid.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endCEP")); // endCEP
	    
	    cbEndRA.setItems(olEndRA);
	    cbEndRA.setValue("Plano Piloto");
	    cbEndUF.setItems(olEndUF);
	    
	    // capturar o numero da RA (Regiao Administrativa) selecionada
	    cbEndRA.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		intRA = listaRA[(int) new_value];
	    	
            }
	    });
	    
	    // capturar o nome da RA (Regiao Administrativa) selecionada
	    cbEndRA.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    		
	    		strRA = (String) newValue );
	    
	    /*
	    // Se houver endereco cadastrado para a demanda, abrir mostrando o endereco //
	    try {
	    	
	    	listarEnderecos(demanda.getDemEnderecoFK().getEndLogadouro());
	    
	    } catch (Exception e ) {
	    	// Alert a = new Alert(Alert.AlertType.CONFIRMATION, title, ButtonType.OK, ButtonType.CANCEL); //
	    	Alerta a = new Alerta ();
	    	a.alertar(new Alert(Alert.AlertType.CONFIRMATION, "Cadastre um endereço para esta demanda!!!", ButtonType.OK));
	    }
	    */
	    
	    tvLista.setItems(obsList);
	    
	    // ao abrir fechar os campos para edicao //
	    modularBotoesInicial();
	    // ativar na tableview a possibilidade e selecionar uma opcao //
	    selecionarEndereco();
	    
		lblDemanda2 = new Label();
	
		if (objetoDeEdicao.getClass().getName().equals("entidades.Demanda")) {
			
			lblDemanda2.setText(((Demanda) objetoDeEdicao).getDemNumeroSEI()); //
			System.out.println("teste demanda selecionada");
		} 
		
		if (objetoDeEdicao.getClass().getName().equals("entidades.Usuario")){
			
			lblDemanda2.setText(((Usuario) objetoDeEdicao).getUsNome()); // 
			System.out.println("teste usuario selecionada");
		}
		
		
		lblDemanda2.setPrefSize(lblDemanda1.getPrefWidth(), lblDemanda1.getPrefHeight());	
		lblDemanda2.setLayoutX(lblDemanda1.getLayoutX());
		lblDemanda2.setLayoutY(lblDemanda1.getLayoutY());
		lblDemanda2.setStyle("-fx-font-weight: bold;");
		
		p_lblDemanda.getChildren().add(lblDemanda2);
		
		btnEditar.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	editarEndereco (objetoDeEdicao);
	        }
	    });
	 			
	}
	
	//-- Modular os botoes na inicializacao do programa --//
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		cbEndRA.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		cbEndUF.setDisable(true);
		tfEndLat.setDisable(true);
		tfEndLon.setDisable(true);
		tfLinkEnd.setDisable(true);
		tfLinkEnd.setText("");
		
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
		//obsList = FXCollections.observableArrayList();
		
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
    		
    		obsList.add(e);
    		
		}
			
		tvLista.setItems(obsList); 
		
	}
	
	// método selecionar endereço -- //
	 	public void selecionarEndereco () {
		
			tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Endereco end = (Endereco) newValue;
				
				if (end == null) {
					
					tfEnd.setText("");
					
					tfEndCep.setText("");
					tfEndCid.setText("");
					tfEndLat.setText("");
					tfEndLon.setText("");
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					tfEnd.setText(end.getEndLogradouro());
					
					cbEndRA.setValue(end.getEndRAFK().getRaNome()); 
					
					tfEndCep.setText(end.getEndCEP());
					tfEndCid.setText(end.getEndCidade());
					
					cbEndUF.setValue(end.getEndUF());
					
					tfEndLat.setText(end.getEndDDLatitude().toString());
					tfEndLon.setText(end.getEndDDLongitude().toString());
					
					// -- habilitar e desabilitar botoes -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					
					FormatoData d = new FormatoData();
					
					// mostrar data de atualizacao //
					
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(end.getEndAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
					
					// atualizar o valor da demanda //
					/*
					if (end.getDemandas().size() != 0) { // colocar regra de só pode enditar escolhendo uma demanda...
						demanda = end.getDemandas().get(0);
					}
					*/
					
					
					//eGeral = new Endereco(endTab);
					
					//main.pegarEnd(eGeral);
					
					
					//Double lat = Double.parseDouble(tfEndLat.getText());
					//Double  lng = Double.parseDouble(tfEndLon.getText() );
					
					/*
					if (wv1 == null) {
						
						String strMarcador = "" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);";
						
						abrirMapa(strMarcador);
						
					} else
					{
						webEng.executeScript("" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);"
		                    );
				
					}
					*/
					
				}
				
				}
				
			});
			
			
		}
	 	
}
