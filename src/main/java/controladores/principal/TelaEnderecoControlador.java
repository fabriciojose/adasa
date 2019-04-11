package controladores.principal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;

public class TelaEnderecoControlador implements Initializable {
	
		
	Object objetoDeEdicao = new Object();
	
	public void setObjetoDeEdicao (Object objetoDeEdicao) {
		
		EditarEnderecoControlador.objetoDeEdicao = objetoDeEdicao;
		
		if (objetoDeEdicao.getClass().getName() == "entidades.Demanda")
		
		lblDemanda.setText(
				((Demanda) objetoDeEdicao).getDemTipo() 
				+ ", Sei n° " + ((Demanda) objetoDeEdicao).getDemNumeroSEI()
				+ ", Processo n° " + ((Demanda) objetoDeEdicao).getDemProcesso()
				);
		
	}
	
	@FXML Pane pTelaEndereco;
	
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
												  								
												 
	//-- TableView endereco --//
	private TableView <Endereco> tvLista = new TableView<>();

	TableColumn<Endereco, String> tcDesEnd = new TableColumn<>("Endereço");
	TableColumn<Endereco, String> tcEndRA = new TableColumn<>("Região Administrativa");
	TableColumn<Endereco, String> tcEndCid = new TableColumn<>("CEP");
	
	Label lblDataAtualizacao = new Label();
												
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
	
	ObservableList<String> olEndUF = FXCollections
			.observableArrayList("DF" , "GO", "Outro");
	
	public static TelaEnderecoControlador tabEndCon;
	
	/* array de posicoes prefWidth prefHeight Layout Y e X */
	Double prefSizeWHeLayXY [][];
	
	Componentes com;
	
	ObservableList<Endereco> obsList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		tabEndCon = this;
		
		pTelaEndereco.setStyle("-fx-background-color:#FFFFFF;");
	    
		listNodesDemanda.add(pDemanda = new Pane());
			listNodesDemanda.add(new Label("DEMANDA:"));
				listNodesDemanda.add(lblDemanda = new Label());
					listNodesDemanda.add(btnDemanda = new Button(">>>"));
	    
	    prefSizeWHeLayXY = new Double [][] { 
    		{930.0,60.0,10.0,10.0}, 
    			{75.0,30.0,11.0,15.0}, 
    				{750.0,30.0,87.0,15.0}, 
    					{75.0,20.0,844.0,18.0}};
	    	
	    com = new Componentes();
	    com.popularTela(listNodesDemanda, prefSizeWHeLayXY, pTelaEndereco);
	    
	    
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
	    	{900.0,130.0,25.0,83.0},
	    	{380.0,30.0,18.0,0.0},
	    	{370.0,30.0,18.0,30.0},
	    	{160.0,30.0,398.0,0.0},
	    	{160.0,30.0,398.0,30.0},
	    	{100.0,30.0,568.0,0.0},
	    	{100.0,30.0,568.0,30.0},
	    	{90.0,30.0,680.0,0.0},
	    	{90.0,30.0,680.0,30.0},
	    	{100.0,30.0,782.0,0.0},
	    	{100.0,30.0,782.0,30.0},
	    	{100.0,30.0,183.0,79.0},
	    	{140.0,30.0,283.0,79.0},
	    	{100.0,30.0,438.0,79.0},
	    	{140.0,30.0,538.0,79.0},
	    	{25.0,25.0,693.0,82.0},
	    };
	    	
	    com = new Componentes();
	    com.popularTela(listNodeDadosEndereco, prefSizeWHeLayXY, pTelaEndereco);
	    
	    
	    listNodesPersistencia.add(pPersistencia = new Pane());
	    listNodesPersistencia.add(btnNovo = new Button("NOVO"));
	    listNodesPersistencia.add(btnSalvar = new Button("SALVAR"));
	    listNodesPersistencia.add(btnEditar = new Button("EDITAR"));
	    listNodesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
	    listNodesPersistencia.add(btnCancelar = new Button("CANCELAR"));
	    listNodesPersistencia.add(tfPesquisar = new TextField());
	    listNodesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	{900.0,60.0,25.0,225.0}, 
	    		{95.0,25.0,13.0,18.0}, 
	    			{95.0,25.0,118.0,18.0}, 
	    				{95.0,25.0,223.0,18.0}, 
					    	{95.0,25.0,328.0,18.0}, 
					    		{95.0,25.0,433.0,18.0}, 
					    			{245.0,25.0,538.0,18.0}, 
					    				{95.0,25.0,793.0,18.0} }; 
					    				
					    				 com = new Componentes();
					    				    com.popularTela(listNodesPersistencia, prefSizeWHeLayXY, pTelaEndereco);
	    
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
			
	    tvLista.getColumns().add(tcDesEnd);
	    tvLista.getColumns().add(tcEndRA);
	    tvLista.getColumns().add(tcEndCid);
	  
	    tvLista.setItems(obsList);
	    
	    tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(25);
		tvLista.setLayoutY(305);
		
		lblDataAtualizacao.setPrefSize(247, 22);
		lblDataAtualizacao.setLayoutX(677);
		lblDataAtualizacao.setLayoutY(500);
		
		pTelaEndereco.getChildren().addAll(tvLista, lblDataAtualizacao);
		
		
	    acionarBotoes ();
	    
	    selecionarEndereco();
		
	} // FIM INITIALIZE
	
	public void acionarBotoes () {
		  
	    btnDemanda.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	TabDemandaControlador.tabDemCon.movimentarTelaEndereco(15.0);
	        }
	    });
	    
	    btnNovo.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	
	        }
	    });
	    
	    btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	salvarEndereco(objetoDeEdicao);
	        }
	    });
	    
	    btnEditar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	editarEndereco(objetoDeEdicao);
	        }
	    });
	    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	excluirEndereco();
	        }
	    });
	    
	    btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	cancelarEndereco();
	        }
	    });
	    
	    btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	pesquisarEndereco();
	        }
	    });
	  
  }
	
	public void btnNovoHab (ActionEvent event) {
		
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

	public void salvarEndereco (Object obj) {
	
	/*
		
		if (tfLatitude.getText().isEmpty() || 
				tfLongitude.getText().isEmpty()) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
		} 
		
			else if (obj == null) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Não há demanda selecionada!!!", ButtonType.OK));
				
			} 
		
				else {
				
				
					if (tfLogradouro.getText().isEmpty()) {
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o logadouro do empreendimento!!!", ButtonType.OK));
						
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
		
		*/
			
	}
	

	public void editarEndereco (Object objetoDeEdicao) {
		
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
						
					end.setEndLogradouro(tfLogradouro.getText());
					end.setEndRAFK(ra);
					end.setEndCEP(tfCEP.getText());
					end.setEndCidade(tfCidade.getText());
					end.setEndUF(cbUF.getValue());
					end.setEndDDLatitude(Double.parseDouble(tfLatitude.getText()));
					end.setEndDDLongitude(Double.parseDouble(tfLongitude.getText()));
					
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
	
	public void excluirEndereco () {
		
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
	
	public void cancelarEndereco () {
		
		modularBotoesInicial ();
		
		tfLogradouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		
		cbUF.setValue(null);
		
		tfLatitude.setText("");
		tfLongitude.setText("");
		
	}

	public void pesquisarEndereco () {
		
		strPesquisa = tfPesquisar.getText();
		
		listarEnderecos (strPesquisa);
		
		selecionarEndereco () ;
		
		modularBotoesInicial (); 
		
	}
	
	String strPesquisa = "";
	
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
		//obsList = FXCollections.observableArrayList();
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
			
		// funcionando
		List<Endereco> iList = enderecoList;
		
		
		for (Endereco e : iList) {
			
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





