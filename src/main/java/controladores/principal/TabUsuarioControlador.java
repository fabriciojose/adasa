package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import dao.ModelosDao;
import dao.UsuarioDao;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.ModelosHTML;
import entidades.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import principal.Alerta;
import principal.Componentes;
import principal.FormatoData;
import principal.MalaDireta;

public class TabUsuarioControlador implements Initializable {
	
	Usuario usuario = new Usuario();
		Endereco endereco = new Endereco ();
			Interferencia interferencia = new Interferencia();
		
	public void setEndereco (Endereco endereco) {
		
		this.endereco = endereco;
		
		// preencher o label com a demanda selecionada //
		
		if (endereco != null) {
		
			lblEndereco.setText(
					endereco.getEndLogradouro() 
					+ ", CEP: " + endereco.getEndCEP()
					+ ", Cidade: " + endereco.getEndCidade()
					);
		
			lblEndereco.setStyle("-fx-text-fill: #4A4A4A;"); 
			
		} else {
			
			lblEndereco.setText("Não há endereco de empreendimento cadastrado!");
			lblEndereco.setStyle("-fx-text-fill: #FF0000;");
		}
		
	}
	
	//-- Strings --//
	String strPesquisa = "";
	
		//-- TableView Endereço --//
		private TableView <Usuario> tvLista = new TableView<>();
		
		TableColumn<Usuario, String> tcNome = new TableColumn<>("Nome");
		TableColumn<Usuario, String> tcCPFCNPJ = new TableColumn<>("CPF/CNPJ");
		TableColumn<Usuario, String> tcEndereco = new TableColumn<>("Endereço de Correpondência");
		
	ObservableList<Usuario> obsList = FXCollections.observableArrayList();
	
	ObservableList<Endereco> obsListEnderecoEmpreendimento = FXCollections.observableArrayList();

	ObservableList<Interferencia> obsListInterferencia = FXCollections.observableArrayList();
	
	ObservableList<String> olTipoPessoa = FXCollections
		.observableArrayList("Física" , "Jurídica"); // box - seleção pessoa físcia ou jurídica

	ObservableList<String> olRA = FXCollections
		.observableArrayList(
					
					"Águas Claras",
					"Brasília",
					"Brazlândia",
					"Candangolândia",
					"Ceilândia",
					"Cruzeiro",
					"Fercal",
					"Gama",
					"Guará",
					"Itapoã",
					"Jardim Botânico",
					"Lago Norte",
					"Lago Sul",
					"Núcleo Bandeirante",
					"Paranoá",
					"Park Way",
					"Planaltina",
					"Recanto das Emas",
					"Riacho Fundo II",
					"Riacho Fundo",
					"Samambaia",
					"Santa Maria",
					"São Sebastião",
					"SCIA",
					"SIA",
					"Sobradinho II",
					"Sobradinho	",
					"Sudoeste/Octogonal",
					"Taguatinga	",
					"Varjão	",
					"Vicente Pires"
					
					); 	

	ObservableList<String> olDF = FXCollections
		.observableArrayList("DF" , "GO", "Outro"); // box - seleção pessoa físcia ou jurídica
				
	public void btnNovoHab () {
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText(null);
		tfCPFCNPJ.setText(null);
		tfLogradouro.setText(null);
		
		cbRA.setValue(null);
		
		tfCEP.setText(null);
		tfCidade.setText("Brasília");
		
		cbUF.setValue("DF");
		
		tfTelefone.setText(null);
		tfCelular.setText(null);
		tfEmail.setText(null);
		
		cbTipoPessoa.setDisable(false);
		
		tfNome.setDisable(false);
		tfCPFCNPJ.setDisable(false);
		
		checkEnderecoEmpreendimento.setDisable(false);
		
		tfLogradouro.setDisable(false);
		cbRA.setDisable(false);
		tfCEP.setDisable(false);
		tfCidade.setDisable(false);
		cbUF.setDisable(false);
		tfTelefone.setDisable(false);
		tfCelular.setDisable(false);
		tfEmail.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
		
	}
	
	public void btnSalvarHab () {
		
		
		if (endereco.getEndLogradouro() == null) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Endereço relacionado ao usuário não selecionado!!!", ButtonType.OK));
			
		} else {
			
				if (cbTipoPessoa.getValue() == null  ||
						tfNome.getText().isEmpty()
						
						) {
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Tipo e Nome do Usuário!!!", ButtonType.OK));
					
				} else {
		
						Usuario us = new  Usuario ();
						
							us.setUsTipo(cbTipoPessoa.getValue());
							us.setUsNome(tfNome.getText());
							us.setUsCPFCNPJ(tfCPFCNPJ.getText()); 
							us.setUsLogadouro(tfLogradouro.getText());
							us.setUsRA(cbRA.getValue());
							us.setUsCidade(tfCidade.getText());
							us.setUsEstado(cbUF.getValue());
							us.setUsCEP(tfCEP.getText());
							us.setUsTelefone(tfTelefone.getText());
							us.setUsCelular(tfCelular.getText());
							us.setUsEmail(tfEmail.getText());
							
							us.setUsDataAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
		
						// chama instancia endereco //	
						Endereco end = new Endereco();
						
							// iguala endereco ao selecionado pelo usuario
							end = endereco;
							// neste endereco seta o usuario
							end.setEndUsuarioFK(us);
							// na lista de endereco que  pertence ao usuario adiciona o endereco em questao
							us.getEnderecos().add(end);
							
							// aquii
							for(Interferencia i:  end.getInterferencias()) {
								System.out.println(" interferencias - btn salvar " + i.getInterDDLatitude());
							}
							
						UsuarioDao  usDao = new UsuarioDao();
						
							usDao.salvarUsuario(us);
							usDao.mergeUsuario(us);
						
						obsList.add(us);
						
						modularBotoesInicial();
						
						//Alerta //
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.INFORMATION, "Informe: Cadastro salvo com sucesso!!!", ButtonType.OK));
						
						System.out.println("btn salvar " + usuario.getUsNome());
					
				}
		}
			
	}
	
	public void btnEditarHab () {
		
		if (cbTipoPessoa.isDisable()) {
			
			cbTipoPessoa.setDisable(false);
			tfNome.setDisable(false);
			
			checkEnderecoEmpreendimento.setDisable(false);
			
			tfCPFCNPJ.setDisable(false);
			tfLogradouro.setDisable(false);
			cbRA.setDisable(false);
			tfCEP.setDisable(false);
			tfCidade.setDisable(false);
			cbUF.setDisable(false);
			tfTelefone.setDisable(false);
			tfCelular.setDisable(false);
			tfEmail.setDisable(false);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(false);
			btnExcluir.setDisable(true);
			btnCancelar.setDisable(false);
			
		}
		
		else {
			
			if (cbTipoPessoa.getValue() == null  ||
					tfNome.getText().isEmpty()
					
					) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Tipo e Nome do Usuário!!!", ButtonType.OK));
				
			} else {
	
			Usuario us = tvLista.getSelectionModel().getSelectedItem(); 
			
				// -- preencher os campos -- //
				us.setUsTipo(cbTipoPessoa.getValue()); 
				us.setUsNome(tfNome.getText());
				us.setUsCPFCNPJ(tfCPFCNPJ.getText());
				us.setUsLogadouro(tfLogradouro.getText()); 
				
				us.setUsRA(cbRA.getValue()); 
				
				us.setUsCEP(tfCEP.getText()); 
				us.setUsCidade(tfCidade.getText()); 
				
				us.setUsEstado(cbUF.getValue()); 
				
				us.setUsTelefone(tfTelefone.getText());
				us.setUsCelular(tfCelular.getText());
				us.setUsEmail(tfEmail.getText());
				us.setUsDataAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
				
			
			Endereco end = new Endereco();
				// captura um endereco relacionado
				end = endereco;
				// adiciona neste endereco o id usuario selecionado
				end.setEndUsuarioFK(us);
				// adiciona este endereco no setEnderecos do usuario
				us.getEnderecos().add(end);
			
			/*
			// para não dar repeticao de objetos //
			for (int i = 0 ; i < us.getEnderecos().size(); i++) {
				if (us.getEnderecos().hashCode(i) == (end.getEndID())) {
					us.getEnderecos().remove(us.getEnderecos().hashCode(i));
				}
			}
			*/
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.mergeUsuario(us);
			
			obsList.remove(us);
			obsList.add(us);
			
			modularBotoesInicial();
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!!!", ButtonType.OK));
			
			}
			
		}
		
	}
	
	public void btnExcluirHab () {
		
		try {
			//-- capturar usuário selecionado --//
			Usuario usuario = tvLista.getSelectionModel().getSelectedItem(); 
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.removerUsuario(usuario.getUsID());
			
			obsList.remove(usuario);
			
			modularBotoesInicial();
		
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluído com sucesso!!!", ButtonType.OK)); 
	
		}	catch (Exception e) {
		
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao escluir o cadastro!!!", ButtonType.OK)); 
				
		}
	}
	
	public void btnCancelarHab () {
		
		modularBotoesInicial ();
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText("");
		tfCPFCNPJ.setText("");
		tfLogradouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		tfCidade.setText("");
		
		cbUF.setValue(null);
		
		tfTelefone.setText("");
		tfCelular.setText("");
		tfEmail.setText("");
		
	}
	
	//-- botão pesquisar usuário --//
	public void btnPesquisarHab () {
		
		strPesquisa = tfPesquisar.getText();
		
		listarUsuarios (strPesquisa);
		
		modularBotoesInicial();
		
	}

	public void imprimirEnderecoEmpreendimento () {
		
		int count = 0;
		
		if (checkEnderecoEmpreendimento.isSelected()) {
			count ++;
			try{tfLogradouro.setText(endereco.getEndLogradouro());}catch (Exception e) {tfLogradouro.setText(null);};
			try{cbRA.setValue(endereco.getEndRAFK().getRaNome());}catch (Exception e) {cbRA.setValue(null);};
			try{tfCEP.setText(endereco.getEndCEP());}catch (Exception e) {tfCEP.setText(null);};
			try{tfCidade.setText(endereco.getEndCidade());}catch (Exception e) {tfCidade.setText(null);};
			try{cbUF.setValue(endereco.getEndUF());}catch (Exception e) {cbUF.setValue(null);};
			
		}
		System.out.println("check box usuario - endereço: " + count);
	}
	
	@FXML Pane pUsuario;

	Pane p1 = new Pane();
	BorderPane bp1 = new BorderPane();
	BorderPane bp2 = new BorderPane();
	ScrollPane sp = new ScrollPane();
	Pane pMapa = new Pane();
	
	Label lblDataAtualizacao = new Label();
	
	public static TabUsuarioControlador tabUsCon;
	
	public static TabUsuarioControlador controladorAtendimento;
	public static TabUsuarioControlador controladorFiscalizacao;
	public static TabUsuarioControlador controladorOutorga;
	
	int intControlador;
	
	public TabUsuarioControlador (int i) {
		
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
		
		tabUsCon = this;
		
		 bp1.minWidthProperty().bind(pUsuario.widthProperty());
		    bp1.maxHeightProperty().bind(pUsuario.heightProperty().subtract(60));
		    
		    bp1.getStyleClass().add("border-pane");
		    
		    bp2.setPrefHeight(800.0D);
		    bp2.minWidthProperty().bind(pUsuario.widthProperty());
		    
		    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		    
		    sp.setContent(bp2);
		    
		    bp1.setCenter(sp);
		    
		    pUsuario.getChildren().add(bp1);
		    
		    p1.setMaxSize(980.0, 1000.0);
		    p1.setMinSize(980.0, 1000.0);
		    
		    bp2.setTop(p1);
		    BorderPane.setAlignment(p1, Pos.CENTER);
		 
		inicializarComponentes ();
		acionarBotoes();
		    
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(705);
	    lblDataAtualizacao.setLayoutY(600);
	    
		cbTipoPessoa.setValue("Física");
		cbTipoPessoa.setItems(olTipoPessoa);
		
		cbRA.setItems(olRA);
		
		cbUF.setValue("DF");
		cbUF.setItems(olDF);
		
		tcNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usNome"));
		tcCPFCNPJ.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usCPFCNPJ"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usLogadouro"));
		
		tcNome.setPrefWidth(410);
		tcCPFCNPJ.setPrefWidth(180);
		tcEndereco.setPrefWidth(320);
		
		tvLista.setPrefSize(930, 185);
		tvLista.setLayoutX(25);
		tvLista.setLayoutY(405);
		
		
		tvLista.getColumns().add(tcNome);
		tvLista.getColumns().add(tcCPFCNPJ);
		tvLista.getColumns().add(tcEndereco);
		
		tvLista.setItems(obsList);
		
		p1.getChildren().addAll(
				tvLista, 
				lblDataAtualizacao
				);
		
		modularBotoesInicial();
		
		selecionarUsuario();
		// selecionarInterferencia ();
		    
	}
	
	Pane pEndereco;
	Label lblEndereco = new Label();
	Button btnEndereco;
			
		ArrayList<Node> listaComonentesEndereco = new ArrayList<Node>();
				
	Pane pPersistencia;
		Button btnNovo;
		Button btnSalvar;
		Button btnEditar;
		Button btnExcluir;
		Button btnCancelar;
		Button btnPesquisar;
		TextField tfPesquisar;
					
		ArrayList<Node> listNodesPersistencia= new ArrayList<Node>();
	  								
	Pane pDadosUsuario;
		ComboBox<String> cbTipoPessoa;
		TextField tfNome;
		TextField tfCPFCNPJ;
		CheckBox checkEnderecoEmpreendimento;
		TextField tfLogradouro;
		TextField tfCEP;
		ComboBox<String> cbRA;
		TextField tfCidade;
		ComboBox<String> cbUF;
		TextField tfTelefone;
		TextField tfCelular;
		TextField tfEmail;
	
		ArrayList<Node> listaComponentesUsuario = new ArrayList<Node>();												
		
	  	
	Pane pInterferencia;
		ComboBox<Endereco> cbEndereco;
		ComboBox<Interferencia> cbInterferencia;
		Button btnRequerimento;
				
		ArrayList<Node> listaComonentesInterferencia = new ArrayList<Node>();

	Componentes com;
		Double prefSizeWHeLayXY [][];
		
	public void inicializarComponentes () {
		
		
		listaComonentesEndereco.add(pEndereco = new Pane());
		listaComonentesEndereco.add(new Label("ENDERECO:"));
		listaComonentesEndereco.add(lblEndereco = new Label());
		listaComonentesEndereco.add(btnEndereco = new  Button("<<<"));
		
			prefSizeWHeLayXY = new Double [][]  { 
				
				{950.0,60.0,15.0,14.0},
				{85.0,30.0,25.0,15.0},
				{740.0,30.0,110.0,15.0},
				{65.0,25.0,860.0,17.0},
		    					    	
			}; 
		    	
			com = new Componentes();
		    com.popularTela(listaComonentesEndereco, prefSizeWHeLayXY, p1);
		
		
		listaComponentesUsuario.add(pDadosUsuario = new Pane());
		listaComponentesUsuario.add(new Label("TIPO:"));
		listaComponentesUsuario.add(cbTipoPessoa = new ComboBox<String>());
		listaComponentesUsuario.add(new Label("NOME/RAZÃO SOCIAL:"));
		listaComponentesUsuario.add(tfNome = new TextField());
		listaComponentesUsuario.add(new Label("CPF/CNPJ: "));
		listaComponentesUsuario.add(tfCPFCNPJ = new TextField());
		listaComponentesUsuario.add(checkEnderecoEmpreendimento = new CheckBox("importar endereço do empreendimento. "));
		listaComponentesUsuario.add(new Label("ENDERECO:"));
		listaComponentesUsuario.add(tfLogradouro = new TextField());
		listaComponentesUsuario.add(new Label("RA: "));
		listaComponentesUsuario.add(cbRA = new ComboBox<String>());
		listaComponentesUsuario.add(new Label("CEP: "));
		listaComponentesUsuario.add(tfCEP = new TextField());
		listaComponentesUsuario.add(new Label("CIDADE:"));
		listaComponentesUsuario.add(tfCidade = new TextField());
		listaComponentesUsuario.add(new Label("UF: "));
		listaComponentesUsuario.add(cbUF = new ComboBox<String>());
		listaComponentesUsuario.add(new Label("TELEFONE:"));
		listaComponentesUsuario.add(tfTelefone = new TextField());
		listaComponentesUsuario.add(new Label("CELULAR:"));
		listaComponentesUsuario.add(tfCelular = new TextField());
		listaComponentesUsuario.add(new Label("EMAIL:"));
		listaComponentesUsuario.add(tfEmail = new TextField());
		
			prefSizeWHeLayXY = new Double [][]  { 
		    	
				{930.0,231.0,25.0,85.0},
				{110.0,30.0,46.0,5.0},
				{110.0,30.0,46.0,35.0},
				{510.0,30.0,166.0,5.0},
				{510.0,30.0,165.0,35.0},
				{200.0,30.0,687.0,5.0},
				{200.0,30.0,685.0,35.0},
				{370.0,30.0,45.0,66.0},
				{390.0,30.0,45.0,95.0},
				{390.0,30.0,45.0,125.0},
				{150.0,30.0,445.0,95.0},
				{150.0,30.0,445.0,125.0},
				{85.0,30.0,605.0,95.0},
				{85.0,30.0,605.0,125.0},
				{110.0,30.0,700.0,95.0},
				{110.0,30.0,700.0,125.0},
				{60.0,30.0,820.0,95.0},
				{60.0,30.0,820.0,125.0},
				{140.0,30.0,45.0,155.0},
				{140.0,30.0,44.0,185.0},
				{140.0,30.0,195.0,155.0},
				{140.0,30.0,195.0,185.0},
				{535.0,30.0,345.0,155.0},
				{535.0,30.0,345.0,185.0},
								    	
			}; 
		    	
			com = new Componentes();
		    com.popularTela(listaComponentesUsuario, prefSizeWHeLayXY, p1);
	    
		listNodesPersistencia.add(pPersistencia = new Pane());
	    listNodesPersistencia.add(btnNovo = new Button("NOVO"));
	    listNodesPersistencia.add(btnSalvar = new Button("SALVAR"));
		listNodesPersistencia.add(btnEditar = new Button("EDITAR"));
		listNodesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
		listNodesPersistencia.add(btnCancelar = new Button("CANCELAR"));
	    
		listNodesPersistencia.add(tfPesquisar = new TextField());
		listNodesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
			prefSizeWHeLayXY = new Double [][]  { 
		    	
				{930.0,60.0,25.0,330.0},
				{95.0,25.0,18.0,18.0},
				{95.0,25.0,123.0,18.0},
				{95.0,25.0,228.0,18.0},
				{95.0,25.0,333.0,18.0},
				{95.0,25.0,438.0,18.0},
				{265.0,25.0,543.0,18.0},
				{95.0,25.0,818.0,18.0},
								    	
			}; 
		    	
			com = new Componentes();
		    com.popularTela(listNodesPersistencia, prefSizeWHeLayXY, p1);
	    
		listaComonentesInterferencia.add(pInterferencia = new Pane());
		listaComonentesInterferencia.add(new Label("Endereço:"));
		listaComonentesInterferencia.add(cbEndereco = new ComboBox<Endereco>());
		listaComonentesInterferencia.add(new Label("Interferência:"));
		listaComonentesInterferencia.add(cbInterferencia = new ComboBox<Interferencia>());
		listaComonentesInterferencia.add(btnRequerimento = new  Button("GERAR REQUERIMENTO"));
				
			prefSizeWHeLayXY = new Double [][]  { 
				
				{930.0,81.0,25.0,630.0},
				{350.0,30.0,21.0,9.0},
				{350.0,30.0,21.0,42.0},
				{350.0,30.0,381.0,9.0},
				{350.0,30.0,381.0,42.0},
				{175.0,25.0,741.0,45.0},
		    					    	
			}; 
				    	
			com = new Componentes();
		    com.popularTela(listaComonentesInterferencia, prefSizeWHeLayXY, p1);
		    
		    cbEndereco.setItems(obsListEnderecoEmpreendimento);
		    cbInterferencia.setItems(obsListInterferencia);
		    
		
	}
	
	WebView webTermo;
	WebEngine engTermo;
	
	public void gerarRequerimento (Usuario us, Interferencia inter) {
		
		
		// buscar tipo de documento 
		HTMLEditor htmlEditor = new HTMLEditor();
		
		ModelosDao modDao = new ModelosDao();
		
		List<ModelosHTML> listRequerimento = null;
		
	
		if (inter.getInterTipoInterferenciaFK().getTipoInterDescricao().equals("Subterrânea")) {
			
			listRequerimento = modDao.listarModelo("Requerimento de Outorga Subterrânea");
		} 
		
		if (inter.getInterTipoInterferenciaFK().getTipoInterDescricao().equals("Superficial")) {
			
			listRequerimento = modDao.listarModelo("Requerimento de Outorga Superficial");
		} 
		
		
		// editar tipo de documento com dados do usuario, interferencia etc
		MalaDireta ml = new MalaDireta(endereco, interferencia, usuario);
		
		ml.setHtmlRel(listRequerimento.get(0).getModConteudo());
		
		String strHTML = ml.criarDocumento();
		
		try { ControladorNavegacao.conNav.setHTML(strHTML); } 
			catch (Exception e) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Inicialize o navegador SEI !!!", ButtonType.OK));
		}
		
			
		htmlEditor.setHtmlText(strHTML);
		
		// adicionar um novo botao ao htmlEditor //
		final String TOP_TOOLBAR = ".top-toolbar";
	  
		ToolBar tooImprimir = new ToolBar();
		Button btnImprimir = new Button("Imprimir");
		
        Node nod;
        
        nod = htmlEditor.lookup(TOP_TOOLBAR);
        if (nod instanceof ToolBar) {
        	tooImprimir = (ToolBar) nod;
        }
        
        tooImprimir.getItems().add(btnImprimir);
        tooImprimir.getItems().add(new Separator(Orientation.VERTICAL));
		
        // imprimir o requerimento //
        btnImprimir.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	
	        	ChoiceDialog<Printer> dialog = new ChoiceDialog<Printer>(Printer.getDefaultPrinter(), Printer.getAllPrinters());
	        	dialog.setHeaderText("Escolha a impressora!");
	        	dialog.setContentText("Impressoras disponíveis...");
	        	dialog.setTitle("Printer Choice");
	        	Optional<Printer> opt = dialog.showAndWait();
	        	if (opt.isPresent()) {
	        	    //Printer printer = opt.get();
	        	    
	        	    PrinterJob job = PrinterJob.createPrinterJob();
					 if (job != null) {
					    boolean success = true;
					    if (success) {
					    	htmlEditor.print(job);
					        job.endJob();
					    }
					 }
	        	}
	        	
				 
				 
	        }
	    });
	    
 		Scene scene = new Scene(htmlEditor);
		
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1150);
		stage.setHeight(750);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        
        stage.show();
        
        
      //WebView webRequerimento = new WebView();
      		//WebEngine engReq = webRequerimento.getEngine();
        
      //engReq.load(getClass().getResource("/html/termoNotificacao.html").toExternalForm()); 
		
      		/*
      		//engReq.load(getClass().getResource(listRequerimento.get(0).getModConteudo()); 
      		engReq.loadContent(listRequerimento.get(0).getModConteudo());
      		
      		engReq.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>(){ 

                   public void changed(final ObservableValue<? extends Worker.State> observableValue, 

                                       final Worker.State oldState, 
                                       final Worker.State newState) 

                   { 
                   	if (newState == Worker.State.SUCCEEDED){  
                   		String strRequerimento = (String) engReq.executeScript("document.documentElement.outerHTML"); 
                   		
                   		System.out.println("string \n " + strRequerimento);
                   		
                   		editor.setHtmlText(strRequerimento);
                   		
                   		Scene scene = new Scene(editor);
              			
              			Stage stage = new Stage(StageStyle.UTILITY);
              			stage.setWidth(1150);
              			stage.setHeight(750);
              	        stage.setScene(scene);
              	        stage.setMaximized(false);
              	        stage.setResizable(false);
              	        
              	        stage.show();
                   		
                   	} 
                   	
                  } 
      		}); 
      		
      		*/
	
	
	}
	
	public void acionarBotoes () {
		
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
		    
		    checkEnderecoEmpreendimento.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> ov,
		            Boolean old_val, Boolean new_val) {
		                if(new_val == true) {
		                	imprimirEnderecoEmpreendimento();
		                }
		        }
		    });
		 
		    btnRequerimento.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	gerarRequerimento (usuario, interferencia);
		        	
		        }
		    });
		
		    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	btnExcluirHab ();
		        }
		    });
		
		    cbEndereco.setConverter(new StringConverter<Endereco>() {
				
				public String toString(Endereco e) {
					return e.getEndLogradouro() + ", RA: " + e.getEndRAFK().getRaNome();
				}
				
				public Endereco fromString(String string) {
					return null;
				}
			});
		    
		    cbEndereco.valueProperty().addListener(new ChangeListener<Endereco>() {
	            @Override 
	            public void changed(ObservableValue<? extends Endereco> ov, Endereco oldValue, Endereco newValue) {  
	            	
	            	obsListInterferencia.clear();
	            	
	            	if (newValue != null)
					for(Interferencia i: newValue.getInterferencias()) {
						obsListInterferencia.add(i);
						
					}
	            	endereco = newValue;
	            }    
	        });
		    
		    cbInterferencia.setConverter(new StringConverter<Interferencia>() {
				public String toString(Interferencia i) {
					return i.getInterTipoInterferenciaFK().getTipoInterDescricao() + " --- " + i.getInterTipoOutorgaFK().getTipoOutorgaDescricao();
				}
				
				public Interferencia fromString(String string) {
					return null;
				}
			});
		    
		    
		    cbInterferencia.valueProperty().addListener(new ChangeListener<Interferencia>() {
	            @Override 
	            public void changed(ObservableValue<? extends Interferencia> ov, Interferencia oldValue, Interferencia newValue) {  
	            	if (newValue != null)
					interferencia = newValue;
	            }    
	        });
		  
	}
	
	Button btnBuscaEnderecoEmpreendimento = new Button();
	
	private void modularBotoesInicial () {
		
		cbTipoPessoa.setDisable(true);
		tfNome.setDisable(true); 
		tfCPFCNPJ.setDisable(true);
		
		checkEnderecoEmpreendimento.setDisable(true);
		
		tfLogradouro.setDisable(true);
		
		cbRA.setDisable(true); 
		
		tfCEP.setDisable(true);
		tfCidade.setDisable(true);
		
		cbUF.setDisable(true);
		
		tfTelefone.setDisable(true);
		tfCelular.setDisable(true);
		tfEmail.setDisable(true);
		
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		
	}
	
	//-- método listar usuários --//
	public void listarUsuarios (String strPesquisa) {
			
			UsuarioDao usDao = new UsuarioDao();
			List<Usuario> usuarioList = usDao.listarUsuario(strPesquisa);
			obsList = FXCollections.observableArrayList();
			
			
			if (!obsList.isEmpty()) {
				obsList.clear();
			}
			
				for (Usuario usuario : usuarioList) {
					
					
							usuario.getUsID();
							usuario.getUsTipo();
							usuario.getUsNome();
							usuario.getUsCPFCNPJ();
							usuario.getUsLogadouro();
							usuario.getUsRA();
							usuario.getUsCidade();
							usuario.getUsEstado();
							usuario.getUsCEP();
							usuario.getUsTelefone();
							usuario.getUsCelular();
							usuario.getUsEmail();
							
							usuario.getUsDataAtualizacao();
							// capturar os enderecos  relacionados
							usuario.getEnderecos();
							
					obsList.add(usuario);
						
					}
					
					tvLista.setItems(obsList);
		}
				
	public void selecionarUsuario () {
			
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Usuario us = (Usuario) newValue;
				
				if (us == null) {
					
					cbTipoPessoa.setValue(null);
					
					tfNome.setText(null);
					tfCPFCNPJ.setText(null);
					tfLogradouro.setText(null);
					
					cbRA.setValue(null);
					
					tfCEP.setText(null);
					tfCidade.setText(null);
					
					cbUF.setValue(null);
					
					tfTelefone.setText(null);
					tfCelular.setText(null);
					tfEmail.setText(null);
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					System.out.println(" usuário null");
					
				} else {

					// -- preencher os campos -- //
					cbTipoPessoa.setValue(us.getUsTipo());
					
					tfNome.setText(us.getUsNome());
					tfCPFCNPJ.setText(us.getUsCPFCNPJ());
					tfLogradouro.setText(us.getUsLogadouro());
					
					cbRA.setValue(us.getUsRA());
					
					tfCEP.setText(us.getUsCEP());
					tfCidade.setText(us.getUsCidade());
					
					cbUF.setValue(us.getUsEstado());
					
					tfTelefone.setText(us.getUsTelefone());
					tfCelular.setText(us.getUsCelular());
					tfEmail.setText(us.getUsEmail());
	
					
					// mostrar data de atualizacao //
					FormatoData d = new FormatoData();
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(us.getUsDataAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
					
					usuario = us;
					
					//enditarEnderecoControlador.setObjetoDeEdicao(us);
				
					obsListEnderecoEmpreendimento.clear();
					
					Set<Endereco> setEnderecos = us.getEnderecos();
					
					if (! us.getEnderecos().isEmpty()) {
						
						for(Endereco e: setEnderecos) {
							System.out.println("set<Endereco> " + e.getEndLogradouro());
							
							obsListEnderecoEmpreendimento.add(e);
							/*
							 * para atualizar o endereço com qualquer um dos  relacionados
							 * 		o objeto endereco não pode ficar vazio
							 */
							endereco = e;
							
						}
						
					} else {
						endereco = null;
					}
					
					obsListInterferencia.clear();
					
					setEndereco (endereco);
					
					// copiar cpf do usuario ao selecionar //
					Clipboard clip = Clipboard.getSystemClipboard();
	                ClipboardContent conteudo = new ClipboardContent();
	                conteudo.putString(us.getUsCPFCNPJ());
	                clip.setContent(conteudo);
	                
					// -- habilitar e desabilitar botões -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				}
				}
			});
	}

	/*
	public void selecionarInterferencia () {
		
		
         
		
		
		/*
		tvListaInterferencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Interferencia inter = (Interferencia) newValue;
				
				if (inter == null) {
					
					System.out.println("metodo selecionar interferencia - não há interferencia ");
					
				} else {

					interferencia = inter;
					
					System.out.println(inter.getInterDDLatitude());
					
				}
				}
			});
			*/
	//}
	
	
	//EditarEnderecoControlador editarEnderecoControlador;
	
	public void abrirPaneEditarEndereco () {
		
		Pane pEndereco = new Pane();
		//editarEnderecoControlador = new EditarEnderecoControlador();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/EditarEndereco.fxml"));
		loader.setRoot(pEndereco);
		//loader.setController(editarEnderecoControlador);
		
		try {
			loader.load();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		Scene scene = new Scene(pEndereco);
		Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
		stage.setWidth(964);
		stage.setHeight(600);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true); 
        stage.show();
	}
	
}
