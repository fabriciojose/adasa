package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import dao.InterferenciaDao;
import entidades.BaciasHidrograficas;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.SituacaoProcesso;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.TipoAto;
import entidades.TipoInterferencia;
import entidades.TipoOutorga;
import entidades.UnidadeHidrografica;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import principal.Alerta;
import principal.FormatoData;


public class TabInterferenciaControlador  implements Initializable{
	
 	TabSubterraneaController tabSubCon;
	TabSuperficialController tabSupCon;
 	
 	static Endereco endereco = new Endereco ();
 	
 	static Label lblEndereco = new Label();

	public void setEndereco (Endereco end) {
		
		TabInterferenciaControlador.endereco = end;
		// preencher o label com a demanda selecionada //
		
		TabInterferenciaControlador.lblEndereco.setText(
				
				endereco.getEndLogradouro()
				+ ", CEP n°: " + endereco.getEndCEP()
				+ ", Cidade: " + endereco.getEndCidade()
				
				);
			
	}
	
	public static Endereco getEndereco () {
		return endereco;
	}
	
	ObservableList<Interferencia> obsList = FXCollections.observableArrayList();
	
	int tipoCaptacao = 3;
	
	Button btnNovo = new Button("Novo");
	Button btnSalvar = new Button("Salvar");
	Button btnEditar = new Button("Editar");
	Button btnExcluir = new Button("Excluir");
	Button btnCancelar = new Button("Cancelar");
	Button btnPesquisar = new Button("Pesquisar");
	TextField tfPesquisar = new TextField();
	
	
	//Button btnEndCoord;
	//Image imgEndCoord = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));

	//Button btnEndCoordMap = new Button();
	//Image imgEndCoordMap = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));
	
	//Image imgGetCoord = new Image(TabEnderecoController.class.getResourceAsStream("/images/getCoord.png")); 
	Button btnBuscarEnd = new Button ();

	//-- TableView Endereco --//
	private TableView <Interferencia> tvLista = new TableView<>();
	
	TableColumn<Interferencia, String> tcTipoInterferencia  = new TableColumn<>("Tipo de Interferência");
	TableColumn<Interferencia, String> tcLogradouro  = new TableColumn<>("Endereço do Empreendimento");
	TableColumn<Interferencia, String> tcSituacao  = new TableColumn<>("Situação");
	
	Label lblDataAtualizacao = new Label();
												
	int tipoInterferenciaID = 1;
	String tipoInterferenciaDescricao = "Superficial";
	final int [] listaTipoInterID = new int [] { 1,2,3,4,5,6,7 };
	
	int baciaID = 1;
	final int [] listaBaciasID = new int [] { 1,2,3,4,5,6,7,8 };
	
	int unidHidID = 1;
	final int [] listaUHID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41};
	
	int tipoOutorgaID = 1;
	final int [] listaTipoOutorgaID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14 };
	
	int tipoAtoID = 1;
	final int [] listaTipoAtoID = new int [] { 1,2,3,4,5,6 };
	
	int situacaoProcessoID = 1;
	final int [] listaSituacaoProcessoID = new int [] { 1,2,3,4,5,6,7,8 };

	public void btnNovoHab () {
					
			cbTipoInterferencia.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			cbTipoOutorga.setDisable(false);
			cbTipoAto.setDisable(false);
			cbSituacaoProcesso.setDisable(false);
			
			dpDataPublicacao.setDisable(false);
			dpDataVencimento.setDisable(false);
			tfNumeroAto.setDisable(false);
			tfProcessoRenovacao.setDisable(false);
			tfDespachoRenovacao.setDisable(false);
			
			tfLat.setDisable(false);
			tfLon.setDisable(false);
			
			tfLat.setText("");
			tfLon.setText("");
			
			btnNovo.setDisable(true);
			btnSalvar.setDisable(false);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			tfPesquisar.setDisable(false);
			
			btnPesquisar.setDisable(false);
			
			//-- choice box --//
			cbTipoInterferencia.setItems(olTipoInterferencia);
			cbBacia.setItems(olBacia);
					
	}

	
	public void btnSalvarHab () {
		
		TipoInterferencia tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterferenciaID);

		BaciasHidrograficas baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UnidadeHidrografica UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		TipoOutorga tipoOutorga = new TipoOutorga();
		tipoOutorga.setTipoOutorgaID(tipoOutorgaID);
		
		TipoAto tipoAto = new TipoAto();
		tipoAto.setTipoAtoID(tipoAtoID);
		
		SituacaoProcesso situacaoProcesso = new  SituacaoProcesso();
		situacaoProcesso.setSituacaoProcessoID(situacaoProcessoID);
		
		
		if (tfLat.getText().isEmpty() || tfLon.getText().isEmpty()
				) { // ver de aceitar somente número 
			
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR,"Coordenadas inválidas!!!", ButtonType.OK));
			
		}
		
			else if (endereco == null) { // colocar na tabela que não pode ser nulo o id do endereco
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR,"Endereço relacionado não selecionado!!!", ButtonType.OK));
				
			
				} else {
				
						if (tipoInterferenciaID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoPocoFK() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null ||
												tabSubCon.obterSubterranea().getSubSubSistemaFK() == null
											) {
										
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.ERROR,"Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!", ButtonType.OK));
										
										} else {
											
										Subterranea sub = new Subterranea ();
										
											sub = tabSubCon.obterSubterranea();
											
											sub.setInterTipoInterferenciaFK(tipoInterferencia);
											sub.setInterBaciaFK(baciaHid);
											sub.setInterUHFK(UniHid);
											sub.setInterTipoOutorgaFK(tipoOutorga);
											sub.setInterTipoAtoFK(tipoAto);
											sub.setInterSituacaoProcessoFK(situacaoProcesso);
											
											sub.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
											sub.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
												
											sub.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
											
											sub.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
											sub.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
											
											sub.setInterNumeroAto(tfNumeroAto.getText());
											
											sub.setInterProcRenovacao(tfProcessoRenovacao.getText());
											sub.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
											
											sub.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												Point p = geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfLon.getText()),
														Double.parseDouble(tfLat.getText()
														)));
												
												p.setSRID(4674);
													
												sub.setInterGeom(p);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											
											interferenciaDao.salvaInterferencia(sub);	
								
											obsList.remove(sub);
											obsList.add(sub);
											
										modularBotoes ();
										
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.INFORMATION, "Interferência salva com sucesso!", ButtonType.OK));
								
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterferenciaID == 1) {
							
								if (
										tabSupCon.obterSuperficial() == null
										
										) {
									
									Alerta a = new Alerta ();
									a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o Local de Captação e se há Caesb!!!", ButtonType.OK));
									
									} else {
									
											Superficial sup = new Superficial ();
											
												sup = tabSupCon.obterSuperficial();
												
												sup.setInterTipoInterferenciaFK(tipoInterferencia);
												sup.setInterBaciaFK(baciaHid);
												sup.setInterUHFK(UniHid);
												sup.setInterTipoOutorgaFK(tipoOutorga);
												sup.setInterTipoAtoFK(tipoAto);
												sup.setInterSituacaoProcessoFK(situacaoProcesso);
												
												sup.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
												sup.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
													
												sup.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
												
												sup.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
												sup.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
												
												sup.setInterNumeroAto(tfNumeroAto.getText());
												
												sup.setInterProcRenovacao(tfProcessoRenovacao.getText());
												sup.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
												
												sup.setInterEnderecoFK(endereco);
													
													GeometryFactory geoFac = new GeometryFactory();
													
													Point p = geoFac.createPoint(new Coordinate(
															Double.parseDouble(tfLon.getText()),
															Double.parseDouble(tfLat.getText()
															)));
													
													p.setSRID(4674);
														
													sup.setInterGeom(p);
												
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											interferenciaDao.salvaInterferencia(sup);
											
											obsList.remove(sup);
											obsList.add(sup);
											
											modularBotoes ();
											
											Alerta a = new Alerta ();
											a.alertar(new Alert(Alert.AlertType.INFORMATION, "Interferência salva com sucesso!", ButtonType.OK));
											
											}
											
												
									} // fim superficial //
						
						else {
							
							Interferencia inter = new Interferencia();
							
							inter.setInterTipoInterferenciaFK(tipoInterferencia);
							inter.setInterBaciaFK(baciaHid);
							inter.setInterUHFK(UniHid);
							inter.setInterTipoOutorgaFK(tipoOutorga);
							inter.setInterTipoAtoFK(tipoAto);
							inter.setInterSituacaoProcessoFK(situacaoProcesso);
							
							inter.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
							inter.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
							
							inter.setInterNumeroAto(tfNumeroAto.getText());
							
							inter.setInterProcRenovacao(tfProcessoRenovacao.getText());
							inter.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
							
							inter.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
						
							inter.setInterEnderecoFK(endereco);
							
							GeometryFactory geoFac = new GeometryFactory();
							
							Point p = geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfLon.getText()),
									Double.parseDouble(tfLat.getText()
									)));
							
							p.setSRID(4674);
								
							inter.setInterGeom(p);
							
							
							InterferenciaDao interferenciaDao = new InterferenciaDao ();
							interferenciaDao.salvaInterferencia(inter);
							
								obsList.remove(inter);
								obsList.add(inter);
								
									
								//tvLista.setItems(obsList); 
							
								modularBotoes ();
								
								//-- Alerta de endereco salvo --//
								Alerta a = new Alerta ();
								a.alertar(new Alert(Alert.AlertType.INFORMATION, "Interferência salva com sucesso!", ButtonType.OK));
							
							
						} // fim outras interferencias
						
				}
		
		}
	
	//-- botao editar --//
	public void btnEditarHab () {
		
		TipoInterferencia tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterferenciaID);

		BaciasHidrograficas baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UnidadeHidrografica UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		TipoOutorga tipoOutorga = new TipoOutorga();
		tipoOutorga.setTipoOutorgaID(tipoOutorgaID);
		
		TipoAto tipoAto = new TipoAto();
		tipoAto.setTipoAtoID(tipoAtoID);
		
		SituacaoProcesso situacaoProcesso = new  SituacaoProcesso();
		situacaoProcesso.setSituacaoProcessoID(situacaoProcessoID);
		
		
		// ver excecao de querer editar sem esconlher o endereco da interferencia...
		
		// habilitar os campos para edição //
		if (cbTipoInterferencia.isDisable()) {
					
			cbTipoInterferencia.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			cbTipoOutorga.setDisable(false);
			cbTipoAto.setDisable(false);
			
			tfLat.setDisable(false);
			tfLon.setDisable(false);
			
			cbSituacaoProcesso.setDisable(false);
			dpDataPublicacao.setDisable(false);
			dpDataVencimento.setDisable(false);
			tfNumeroAto.setDisable(false);
			tfProcessoRenovacao.setDisable(false);
			tfDespachoRenovacao.setDisable(false);		
		}
		
		// verificar se foi preenchido o campo das coordenadas //
		else if (tfLat.getText().isEmpty()|| tfLon.getText().isEmpty()) { // ver de aceitar somente número 
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
			} 
		
					else {
				
						if (tipoInterferenciaID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoPocoFK() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null ||
												tabSubCon.obterSubterranea().getSubSubSistemaFK() == null
											) {
										
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!", ButtonType.OK));
										
										} else {
									
										// obter interferencia selecionada	
										Subterranea sub = tabSubCon.obterSubterranea();
										
										System.out.println("btn editar subterranea id " + tabSubCon.obterSubterranea().getInterID());
											
											sub.setInterTipoInterferenciaFK(tipoInterferencia);
											sub.setInterBaciaFK(baciaHid);
											sub.setInterUHFK(UniHid);
											sub.setInterTipoOutorgaFK(tipoOutorga);
											sub.setInterTipoAtoFK(tipoAto);
											sub.setInterSituacaoProcessoFK(situacaoProcesso);
										
											sub.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
											sub.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
											
											if (dpDataPublicacao.getValue() == null) {
												sub.setInterDataPublicacao(null);;}
												else {
													sub.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
													
													}
															
											if (dpDataVencimento.getValue() == null) {
												sub.setInterDataVencimento(null);}
												else {
													sub.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
												
													}
											
											sub.setInterNumeroAto(tfNumeroAto.getText());
											
											sub.setInterProcRenovacao(tfProcessoRenovacao.getText());
											sub.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
											
											sub.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
											
											sub.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												Point p = geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfLon.getText()),
														Double.parseDouble(tfLat.getText()
														)));
												
												p.setSRID(4674);
													
												sub.setInterGeom(p);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
													
											// merge subterranea //
											interferenciaDao.mergeInterferencia(sub);
								
											obsList.remove(sub);
											obsList.add(sub);
											
										modularBotoes ();
										
										//-- Alerta de endereco salvo --//
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.INFORMATION, "Interferência editada com sucesso!", ButtonType.OK));
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterferenciaID == 1) {
							
								if (tabSupCon.obterSuperficial().getSupLocalCaptacaoFK() == null // || 
									//	tabSupCon.obterSuperficial().getSupArea() == null
										
										) {
									
									Alerta a = new Alerta ();
									a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o Local de Captação e se há Caesb!!!", ButtonType.OK));
									
									} else {
										
												Superficial sup = tabSupCon.obterSuperficial();
												
												System.out.println("btn editar - super valor id " + tabSupCon.obterSuperficial().getInterID());
												
												sup.setInterTipoInterferenciaFK(tipoInterferencia);
												sup.setInterBaciaFK(baciaHid);
												sup.setInterUHFK(UniHid);
												sup.setInterTipoOutorgaFK(tipoOutorga);
												sup.setInterTipoAtoFK(tipoAto);
												sup.setInterSituacaoProcessoFK(situacaoProcesso);
												
												sup.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
												sup.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
												
												if (dpDataPublicacao.getValue() == null) {
													sup.setInterDataPublicacao(null);;}
													else {
														sup.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
														
														}
																
												if (dpDataVencimento.getValue() == null) {
													sup.setInterDataVencimento(null);}
													else {
														sup.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
													
														}
												
												sup.setInterNumeroAto(tfNumeroAto.getText());
												
												sup.setInterProcRenovacao(tfProcessoRenovacao.getText());
												sup.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
												
												sup.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
											
												sup.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												Point p = geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfLon.getText()),
														Double.parseDouble(tfLat.getText()
														)));
												
												p.setSRID(4674);
												
												sup.setInterGeom(p);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											
											// merge superficial e canal //
											interferenciaDao.mergeInterferencia(sup);
											
											//interferencia.setIntSupFK(sup);
												
											obsList.remove(sup);
											obsList.add(sup);
											
											modularBotoes ();
											
											//-- Alerta de endereco salvo --//
											Alert a = new Alert (Alert.AlertType.INFORMATION);
											a.setTitle("Parabéns!");
											a.setContentText("Interferência editada com sucesso!");
											a.setHeaderText(null);
											a.show();
											
											}
											
												
									} // fim superficial //
						
						else {
							
							Interferencia inter= tvLista.getSelectionModel().getSelectedItem();
							
							inter.setInterTipoInterferenciaFK(tipoInterferencia);
							inter.setInterBaciaFK(baciaHid);
							inter.setInterUHFK(UniHid);
							inter.setInterTipoOutorgaFK(tipoOutorga);
							inter.setInterTipoAtoFK(tipoAto);
							inter.setInterSituacaoProcessoFK(situacaoProcesso);
					
							inter.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
							inter.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
							
							if (dpDataPublicacao.getValue() == null) {
								inter.setInterDataPublicacao(null);;}
								else {
									inter.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
									
									}
											
							if (dpDataVencimento.getValue() == null) {
								inter.setInterDataVencimento(null);}
								else {
									inter.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
								
									}
							
							inter.setInterNumeroAto(tfNumeroAto.getText());
							
							inter.setInterProcRenovacao(tfProcessoRenovacao.getText());
							inter.setInterDespachoRenovacao(tfDespachoRenovacao.getText());
								
							inter.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
							
							inter.setInterEnderecoFK(endereco);
								
								GeometryFactory geoFac = new GeometryFactory();
								
								Point p = geoFac.createPoint(new Coordinate(
										Double.parseDouble(tfLon.getText()),
										Double.parseDouble(tfLat.getText()
										)));
								
								p.setSRID(4674);
								
								inter.setInterGeom(p);
								
								/*
								GeometryFactory geoFac = new GeometryFactory();
								
								interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
										Double.parseDouble(tfLon.getText()),
										Double.parseDouble(tfLat.getText())
										)));
										*/
								
								InterferenciaDao interferenciaDao = new InterferenciaDao ();
								
								//merge outras interferencias //
								interferenciaDao.mergeInterferencia(inter);
								
									obsList.remove(inter);
									obsList.add(inter);
									
									modularBotoes ();
									
										//-- Alerta de endereco salvo --//
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.INFORMATION, "Interferência editada com sucesso!", ButtonType.OK));
							
							
						} // fim outras interferencias
						
				}
		
		}
		
	public void btnExcluirHab () {
		
		try {
			
			Interferencia inter = tvLista.getSelectionModel().getSelectedItem();
			
			InterferenciaDao interferenciaDao = new InterferenciaDao ();
			
			interferenciaDao.removeInterferencia(inter.getInterID());
			
			// remover a interferencia da lista //
			obsList.remove(inter);
			
			modularBotoes ();
			
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluído!", ButtonType.OK));
				
		}
		
		catch (Exception e) {
			
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao excluir o cadastro!!!", ButtonType.OK));
		}
		
	}
	
	Pane pInterTipo = new Pane();
	
	@FXML Pane pInterferencia;
	AnchorPane apPrincipal = new AnchorPane();
	BorderPane bpPrincipal = new BorderPane();
	ScrollPane spPrincipal = new ScrollPane();
	Pane p1 = new Pane ();
	
	Pane p_lblEndereco = new Pane();

	Pane pPersistencia = new Pane();
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		pInterferencia.getChildren().add(apPrincipal);
		
		apPrincipal.minWidthProperty().bind(pInterferencia.widthProperty());
		apPrincipal.minHeightProperty().bind(pInterferencia.heightProperty());
		
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
	    
	   
	    tcTipoInterferencia.setPrefWidth(232);
	    tcLogradouro.setPrefWidth(409);
	    tcSituacao.setPrefWidth(232);
		
	    tcTipoInterferencia.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Interferencia, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Interferencia, String> i) {
		    	return new SimpleStringProperty(i.getValue().getInterTipoInterferenciaFK().getTipoInterDescricao());
		       
		    }
		});
	    
	  //  tcLogradouro.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("interLogradouro")); 
	    
	    tcLogradouro.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Interferencia, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Interferencia, String> i) {
		    	return new SimpleStringProperty(i.getValue().getInterEnderecoFK().getEndLogradouro());
		       
		    }
		});
	    
	    tcSituacao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Interferencia, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Interferencia, String> i) {
		    	return new SimpleStringProperty(i.getValue().getInterSituacaoProcessoFK().getSituacaoProcessoDescricao());
		       
		    }
		});
	    
	    tvLista.getColumns().addAll(tcTipoInterferencia, tcLogradouro, tcSituacao);
	    tvLista.setItems(obsList);
	    
	    tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(120);
		tvLista.setLayoutY(352);
	    
		lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(772);
	    lblDataAtualizacao.setLayoutY(547);
	 
	    obterEndereco ();
	    obterDadosBasicos();
	    obterPersistencia();
	    
	    // para trazer os dados do tipo de interferencia especifico //
	    pInterTipo.setPrefSize(960, 410);
	    pInterTipo.setLayoutX(90);
	    pInterTipo.setLayoutY(580);
	    
		p1.getChildren().addAll(p_lblEndereco, pDadosBasicos, pPersistencia, lblDataAtualizacao, tvLista, pInterTipo);
		
		olTipoInterferencia= FXCollections.observableArrayList(
				
				"Superficial",
				"Subterrânea" ,
				"Canal",
				"Caminhão Pipa",
				"Lançamento de Águas Pluviais",
				"Lançamento de Efluentes",
				"Barragem"
				
				);
		
		olBacia = FXCollections
				.observableArrayList(
						
						"Rio Corumbá"	,
						"Rio Descoberto"	,
						"Rio Paranã"	,
						"Rio São Bartolomeu"	,
						"Rio São Marcos"	,
						"Rio Maranhão"	,
						"Rio Paranoá"	,
						"Rio Preto"	

						); 
		
		olUniHid = FXCollections
				.observableArrayList(
						
						"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
						"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41"
						); 
		
		olTipoOutorga = FXCollections
				.observableArrayList(
						
						"Outorga",
						"Renovação de Outorga",
						"Transferência de Outorga",
						"Revogação de Outorga",
						"Suspensão de Outorga",
						"Outorga Prévia",
						"Renovação de Outorga Prévia",
						"Transferência de Outorga Prévia",
						"Revogação de Outorga Prévia",
						"Suspensão de Outorga Prévia",
						"Registro",
						"Transferência de Registro",
						"Revogação de Registro",
						"Suspensão de Registro"	

						); 
		
		olTipoAto = FXCollections
				.observableArrayList(
						
						"Despacho"	,
						"Portaria"	,
						"Registro"	,
						"Resolução",
						"Resolução ANA",
						"Portaria DNAEE"
						
						); 
		
		olSituacaoProcesso = FXCollections
				.observableArrayList(
						
						"Arquivado",
						"Em Análise",
						"Outorgado",
						"Vencida",
						"Arquivado (CNRH 16)",
						"Pendência"	,
						"Indeferido",
						"Revogado"
		); 
		
		cbTipoInterferencia.setItems(olTipoInterferencia);
		cbBacia.setItems(olBacia);
		cbUnidHid.setItems(olUniHid);
		cbTipoOutorga.setItems(olTipoOutorga);
		
		cbTipoOutorga.setCellFactory(
	            new Callback<ListView<String>, ListCell<String>>() {
	               
	            	@Override public ListCell<String> call(ListView<String> param) {
	            		
	                    final ListCell<String> cell = new ListCell<String>() {
	                        {
	                            super.setPrefWidth(100);
	                        }    
                    @Override public void updateItem(String item, 
                        boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);    
                                if (		item.equals("Outorga") 
                                		|| 	item.equals("Renovação de Outorga") 
                                		|| 	item.equals("Transferência de Outorga") 
                                		|| 	item.equals("Revogação de Outorga") 
                                		|| 	item.equals("Suspensão de Outorga")
                                		) {
                                	setTextFill(Color.BLUE);
                                }
                                
                                else if (	item.equals("Outorga Prévia") 
                                		|| 	item.equals("Renovação de Outorga Prévia") 
                                		|| 	item.equals("Transferência de Outorga Prévia") 
                                		|| 	item.equals("Revogação de Outorga Prévia") 
                                		|| 	item.equals("Suspensão de Outorga Prévia")
                                		) {
                                    setTextFill(Color.CHOCOLATE);
                                }
                                
                                else {
                                    setTextFill(Color.DIMGRAY);
                                }
                            }
                        } // fim metodo updateItem
	                };
	                
	                return cell;
	                
	            } // fim metodo call
	        });
		
		cbTipoAto.setItems(olTipoAto);
		cbSituacaoProcesso.setItems(olSituacaoProcesso);
		
		// capturar o id do tipo de interferencia //
		cbTipoInterferencia.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoInterferenciaID = listaTipoInterID [(int) new_value];
	    			
	    		try {
					abrirTabs(tipoInterferenciaID);
				} catch (IOException e) {
					System.out.println("erro ao abrirTabs" + e);
					
				}
	    		
            }
	    });
	    
		cbTipoInterferencia.getSelectionModel().selectedItemProperty().addListener( 
				
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	tipoInterferenciaDescricao = (String) newValue
	     );
		
		cbBacia.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		baciaID = listaBaciasID [(int) new_value];
	    		
	    		System.out.println(" bacia id " + baciaID);
	    		
            }
	    });
		
		cbTipoOutorga.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoOutorgaID = listaTipoOutorgaID [(int) new_value];
	    			System.out.println("tipo outorga " + tipoOutorgaID);
	    			
            }
	    });
		
		cbTipoAto.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoAtoID = listaTipoAtoID [(int) new_value];
	    			System.out.println("situação " + tipoAtoID);
	    			
            }
	    });
		
		cbSituacaoProcesso.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			situacaoProcessoID = listaSituacaoProcessoID [(int) new_value];
	    			System.out.println("situação " + situacaoProcessoID);
	    			
            }
	    });
	    
		/*
		cbBacia.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	baciaNome = (String) newValue
	    	
	    );
	    */
		
		cbUnidHid.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		unidHidID = listaUHID [(int) new_value];
	    		
	    		System.out.println("unidade hidr selecionada " + unidHidID);
	    		
            }
	    });
		
		modularBotoes ();
		selecionarInterferencia();
		
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
		    
		    btnCapturarCoord.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	 btnIntMapsHab(); 
		        }
		    });
		    
		    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	btnExcluirHab();
		        }
		    });
	}
	
	/*
	 
	OUTORGA SUBTERRÂNEA
	OUTORGA SUBTERRÂNEA TRANSFERÊNCIA
	OUTORGA SUBTERRÂNEA INDEFERIMENTO
	OUTORGA SUBTERRÂNEA MODIFICAÇÃO
	OUTORGA SUBTERRÂNEA RENOVAÇÃO
	REGISTRO SUBTERRÂNEA
	REGISTRO SUBTERRÂNEA MODIFICAÇÃO
	REGISTRO SUBTERRÂNEA TRANSFERÊNCIA
	OUTORGA SUBTERRÂNEA PRÉVIA 
	OUTORGA SUBTERRÂNEA PRÉVIA INDEFERIMENTO

	 */
	
	public void obterEndereco () {
		
		p_lblEndereco.setPrefSize(900, 50);
		p_lblEndereco.setLayoutX(120);
		p_lblEndereco.setLayoutY(20);
		p_lblEndereco.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblEnd = new Label ("Endereco: ");
		lblEnd.setLayoutX(25);
		lblEnd.setLayoutY(16);
		
		 // Label para preencher com o endereco a ser trabalhada //
	    lblEndereco.setStyle("-fx-font-weight: bold;");
	    lblEndereco.setPrefSize(750, 25);	
	    lblEndereco.setLayoutX(89);
	    lblEndereco.setLayoutY(13);
	    
	    btnBuscarEnd.setPrefSize(25, 25);
	    btnBuscarEnd.setLayoutX(851);
	    btnBuscarEnd.setLayoutY(13);
		
		p_lblEndereco.getChildren().addAll(lblEnd, lblEndereco, btnBuscarEnd);
	}
	
		Pane pDadosBasicos = new Pane();
		Button btnCapturarCoord = new Button();
		
		//-- capturar latitude e longitude --//
		TextField tfLat = new TextField();
		TextField tfLon = new TextField();
		
		ChoiceBox<String> cbTipoInterferencia  = new ChoiceBox<String>();
		ObservableList<String> olTipoInterferencia; 
		
			ChoiceBox<String> cbBacia  = new ChoiceBox<String>();
			ObservableList<String> olBacia;
			
				ChoiceBox<String> cbUnidHid  = new ChoiceBox<String>();
				ObservableList<String> olUniHid; 
				
					ComboBox<String>  cbTipoOutorga  = new ComboBox<String>();
					ObservableList<String> olTipoOutorga; 
					
						ChoiceBox<String> cbTipoAto  = new ChoiceBox<String>();
						ObservableList<String> olTipoAto; 
						
							ChoiceBox<String> cbSituacaoProcesso  = new ChoiceBox<String>();
							ObservableList<String> olSituacaoProcesso; 
							
							DatePicker dpDataPublicacao = new DatePicker();
							DatePicker dpDataVencimento = new DatePicker();
							TextField tfNumeroAto = new TextField();
							TextField tfProcessoRenovacao = new TextField();
							TextField tfDespachoRenovacao = new TextField();
		
			
	public void obterDadosBasicos () {
		
		pDadosBasicos.setPrefSize(900, 205);
		pDadosBasicos.setLayoutX(120);
		pDadosBasicos.setLayoutY(83);
		pDadosBasicos.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblInter = new Label ("Tipo de Interferência: ");
		lblInter.setLayoutX(15);
		lblInter.setLayoutY(6);
		
			cbTipoInterferencia.setPrefSize(215	, 25);
			cbTipoInterferencia.setLayoutX(14);
			cbTipoInterferencia.setLayoutY(32);

		Label lblBacia = new Label ("Bacia: ");
		lblBacia.setLayoutX(241);
		lblBacia.setLayoutY(6);
		
			cbBacia.setPrefSize(160	, 25);
			cbBacia.setLayoutX(240);
			cbBacia.setLayoutY(32);
		
		Label lblUH = new Label ("UH: ");
		lblUH.setLayoutX(411);
		lblUH.setLayoutY(6);
		
			cbUnidHid.setPrefSize(55, 25);
			cbUnidHid.setLayoutX(411);
			cbUnidHid.setLayoutY(32);
			
				Label lblTipoOutorga = new Label ("Tipo de Outorga: ");
				lblTipoOutorga.setLayoutX(478);
				lblTipoOutorga.setLayoutY(6);
				
					cbTipoOutorga.setPrefSize(238, 25);
					cbTipoOutorga.setLayoutX(477);
					cbTipoOutorga.setLayoutY(32);
					
						Label lblTipoAto = new Label ("Tipo de Ato: ");
						lblTipoAto.setLayoutX(726);
						lblTipoAto.setLayoutY(6);
						
							cbTipoAto.setPrefSize(149, 25);
							cbTipoAto.setLayoutX(725);
							cbTipoAto.setLayoutY(32);
		
		Label lblLat = new Label ("Latitude (Y): ");
		lblLat.setLayoutX(219);
		lblLat.setLayoutY(86);
		
			tfLat.setPrefSize(140, 25);
			tfLat.setPromptText("-15.7754084");
			tfLat.setLayoutX(296);
			tfLat.setLayoutY(82);
		
		Label lblLon = new Label ("Longitude (X): ");
		lblLon.setLayoutX(447);
		lblLon.setLayoutY(86);
			
			tfLon.setPrefSize(140, 25);
			tfLon.setPromptText("-47.9411395");
			tfLon.setLayoutX(535);
			tfLon.setLayoutY(82);
			
			btnCapturarCoord.setPrefSize(25, 25);	
			btnCapturarCoord.setLayoutX(686);
			btnCapturarCoord.setLayoutY(82);
			
				Label lblRenovacao = new Label ("* no caso de renovação de outorga. "); // EBEBEB
				lblRenovacao.setStyle("-fx-background-color: EBEBEB;-fx-font-size: 7.5pt ;");
				lblRenovacao.setLayoutX(589);
				lblRenovacao.setLayoutY(126);
			
			Label lblSituacaoProcesso = new Label ("Situação: ");
			lblSituacaoProcesso.setLayoutX(51);
			lblSituacaoProcesso.setLayoutY(143);
			
				cbSituacaoProcesso.setPrefSize(168, 25);
				cbSituacaoProcesso.setLayoutX(51);
				cbSituacaoProcesso.setLayoutY(170);
				
				Label lblDataPublicacao = new Label ("Data de Publicação: ");
				lblDataPublicacao.setLayoutX(229);
				lblDataPublicacao.setLayoutY(143);
				
					dpDataPublicacao.setPrefSize(120, 25);
					dpDataPublicacao.setLayoutX(229);
					dpDataPublicacao.setLayoutY(170);
			
						Label lblDataVencimento = new Label ("Data de Vencimento: ");
						lblDataVencimento.setLayoutX(358);
						lblDataVencimento.setLayoutY(143);
						
							dpDataVencimento.setPrefSize(120, 25);
							dpDataVencimento.setLayoutX(358);
							dpDataVencimento.setLayoutY(170);
							
								Label lblNumeroAto = new Label ("Número do Ato: ");
								lblNumeroAto.setLayoutX(489);
								lblNumeroAto.setLayoutY(143);
								
									tfNumeroAto.setPrefSize(89, 25);
									tfNumeroAto.setLayoutX(489);
									tfNumeroAto.setLayoutY(170);
									
									Label lblProcOutorga = new Label ("Processo de Outorga: "); // EBEBEB
									lblProcOutorga.setLayoutX(589);
									lblProcOutorga.setLayoutY(143);
									
										tfProcessoRenovacao.setPrefSize(125, 25);
										tfProcessoRenovacao.setLayoutX(589);
										tfProcessoRenovacao.setLayoutY(170);
										
										Label lblDespachoRenovacao = new Label ("Depacho de outorga: "); // EBEBEB
										lblDespachoRenovacao.setLayoutX(725);
										lblDespachoRenovacao.setLayoutY(143);
										
											tfDespachoRenovacao.setPrefSize(125, 25);
											tfDespachoRenovacao.setLayoutX(725);
											tfDespachoRenovacao.setLayoutY(170);
									
									
		
		pDadosBasicos.getChildren().addAll(
				
				lblInter, cbTipoInterferencia, lblBacia, cbBacia, lblUH, cbUnidHid, 
				lblTipoOutorga, cbTipoOutorga, lblTipoAto, cbTipoAto,
				lblLat, tfLat, lblLon, tfLon, btnCapturarCoord,
				lblSituacaoProcesso, cbSituacaoProcesso,  lblDataPublicacao, dpDataPublicacao, 
				lblDataVencimento, dpDataVencimento, lblNumeroAto, tfNumeroAto,
				lblRenovacao, lblProcOutorga, tfProcessoRenovacao, lblDespachoRenovacao, tfDespachoRenovacao
				);
	}
	
    public void obterPersistencia () {
    	
   	    pPersistencia.setPrefSize(900, 50);
   	    pPersistencia.setLayoutX(110);
   	    pPersistencia.setLayoutY(291);
   
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
	
	
	public void btnCancelarHab () {
		
			modularBotoes ();
			
			cbTipoInterferencia.getSelectionModel().clearSelection();
			cbBacia.setValue(null);

			tfLat.setText("");
			tfLon.setText("");
			
			pInterTipo.getChildren().clear();
		
		
	}
	
	//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
	public void btnPesquisarHab () {
		
		strPesquisa = tfPesquisar.getText();
		
		listarInterferencias(strPesquisa);
		
		modularBotoes ();
		
		cbTipoInterferencia.getSelectionModel().clearSelection();
		cbBacia.setValue(null);

		tfLat.setText("");
		tfLon.setText("");
		
	}
	
	public void btnCapturarCroqui (ActionEvent event) {
		
		
	}
	
	public void btnLatLngHab (ActionEvent event) {
			
	}
	
	public void setLatLng (double lat, double lng) {
		
		
	}
	
	public void btnIntMapsHab () {
		
		tfLat.setText( ControladorPrincipal.capturarGoogleMaps().getLat() );
		tfLon.setText( ControladorPrincipal.capturarGoogleMaps().getLon());
		
	}
	
	public void abrirTabs (int ti) throws IOException {
		
		if (ti == 1) {
			
			pInterTipo.getChildren().clear();
			Pane tabSupPane = new Pane();
			tabSupCon = new TabSuperficialController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TabSuperficial.fxml")); 
			loader.setRoot(tabSupPane);
			loader.setController(tabSupCon);
			loader.load();
			
			pInterTipo.getChildren().add(tabSupPane);
			
		}
		
		else if (ti == 2) {
			
			pInterTipo.getChildren().clear();
			Pane tabSubPane = new Pane();
			tabSubCon = new TabSubterraneaController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TabSubterranea.fxml")); 
			loader.setRoot(tabSubPane);
			loader.setController(tabSubCon);
			loader.load();
			pInterTipo.getChildren().add(tabSubPane);
			
			}
		
			else {
				
				pInterTipo.getChildren().clear();
				
				}
		
	}
	
	// --- método para listar interferencias --- //
	 	public void listarInterferencias (String strPesquisa) {
	 	
		 	// --- conexao - listar enderecos --- //
			InterferenciaDao interferenciaDao = new InterferenciaDao();
			List<Interferencia> interferenciaList = null;
			
			try {
				interferenciaList = interferenciaDao.listInterferencia(strPesquisa);
			} catch (Exception e) {
				System.out.println("erro ao listar as interferências!");
				e.printStackTrace();
			}
					
			if (!obsList.isEmpty()) {
				obsList.clear();
			}
			
			// preencher a observable lista para a table view //
			for (Interferencia i : interferenciaList) {
				
				i.getInterID();
				
				i.getInterTipoInterferenciaFK();
				i.getInterBaciaFK();
				i.getInterUHFK();
				i.getInterTipoOutorgaFK();
				i.getInterTipoAtoFK();
				
				i.getInterDDLatitude();
				i.getInterDDLongitude();
				
				i.getInterSituacaoProcessoFK();
				i.getInterDataPublicacao();
				i.getInterDataVencimento();
				i.getInterNumeroAto();
				
				obsList.add(i);
				
			}
			
			tvLista.setItems(obsList); 
			
	 }
	 	
	 	// metodo selecionar interferencia -- //
	 	public void selecionarInterferencia () {
		
	 		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Interferencia inter = (Interferencia) newValue;
				
				if (inter == null) {
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {
					
					cbTipoInterferencia.setValue(inter.getInterTipoInterferenciaFK().getTipoInterDescricao());
					cbBacia.setValue(inter.getInterBaciaFK().getBaciaNome());
					cbUnidHid.setValue(String.valueOf(inter.getInterUHFK().getUhID()));
					cbTipoOutorga.setValue(inter.getInterTipoOutorgaFK().getTipoOutorgaDescricao());
					cbTipoAto.setValue(inter.getInterTipoAtoFK().getTipoAtoDescricao());
					cbSituacaoProcesso.setValue(inter.getInterSituacaoProcessoFK().getSituacaoProcessoDescricao());
					
					// latitude e longitude
					tfLat.setText(inter.getInterDDLatitude().toString());
					tfLon.setText(inter.getInterDDLongitude().toString());
					
					Date dPub = inter.getInterDataPublicacao();
					dpDataPublicacao.setValue(dPub.toLocalDate());
					
					Date dVen = inter.getInterDataVencimento();
					dpDataVencimento.setValue(dVen.toLocalDate());
					
					tfNumeroAto.setText(inter.getInterNumeroAto());
					tfProcessoRenovacao.setText(inter.getInterProcRenovacao());
					tfDespachoRenovacao.setText(inter.getInterDespachoRenovacao());
					
					// mostrar data de atualizacao //
					FormatoData d = new FormatoData();
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(inter.getIntAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
							System.out.println("teste data atualizacao black");
					}catch (Exception e) {
							lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);
							System.out.println("teste data atualizacao red");
					}
				
					setEndereco(inter.getInterEnderecoFK());
					
					tipoInterferenciaID = inter.getInterTipoInterferenciaFK().getTipoInterID();
					
					if (tipoInterferenciaID == 2) {
						
						try {
							abrirTabs (tipoInterferenciaID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSubCon.imprimirSubterranea(((Subterranea) inter));
						
					}
					
					if (tipoInterferenciaID == 1 || tipoInterferenciaID == 3) {
						
						try {
							abrirTabs (tipoInterferenciaID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSupCon.imprimirSuperficial(((Superficial) inter));
						
					}
					
					//System.out.println("FK " + intTab.getEnderecoInterferenciaObjetoTabelaFK());
					
					//Double lat = Double.parseDouble(tfLat.getText());
					//Double  lng = Double.parseDouble(tfLon.getText() );
					
					/*
					if (wv1 != null) {
						
						String endCoord = "" + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLat_Endereco() + ","
										+ intTab.getEnderecoInterferenciaObjetoTabelaFK().getLon_Endereco();
						
						String intCoord = "" + intTab.getInter_Lat() +  "," + intTab.getInter_Lng();
						
						//System.out.println(endCoord);
						//System.out.println(intCoord);
						
						webEng.executeScript("" +
		                        "window.coordenadas = [" 
		                        + 	"'" + endCoord + "'," 
		                        +	"'" + intCoord + "'" 
		                        + "];"
		                        + "document.buscarCoordenadas(coordenadas);"
		                        + " map.setCenter({lat: " 
		                        + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLat_Endereco()
		                        + ", lng: "  
		                        + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLon_Endereco()
		                        + "});"
		                    );
		                    */
		                    
						
						/*
						webEng.executeScript("" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);"
		                    );
		                    */
						
					}
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					}
			});
		}
	 	
	 	public void modularBotoes () {
		
	 		cbTipoInterferencia.setDisable(true);
			cbBacia.setDisable(true);
			cbUnidHid.setDisable(true);
			cbTipoOutorga.setDisable(true);
			cbTipoAto.setDisable(true);
			cbSituacaoProcesso.setDisable(true);
			
			dpDataPublicacao.setDisable(true);
			dpDataVencimento.setDisable(true);
			tfNumeroAto.setDisable(true);
			tfProcessoRenovacao.setDisable(true);
			tfDespachoRenovacao.setDisable(true);
			
			tfLat.setDisable(true);
			tfLon.setDisable(true);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			
			btnNovo.setDisable(false);
		}
	

}
