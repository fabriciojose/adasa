package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import dao.BaciasHidrograficasDao;
import dao.UnidadeHidrograficaDao;
import entidades.BaciasHidrograficas;
import entidades.Finalidade;
import entidades.FinalidadeAutorizada;
import entidades.FinalidadeRequerida;
import entidades.GetterAndSetterFinalidades;
import entidades.SubSistema;
import entidades.Subterranea;
import entidades.TipoPoco;
import entidades.UnidadeHidrografica;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import principal.Alerta;
import principal.Componentes;

public class TabSubterraneaController implements Initializable {

	public Subterranea subterranea = new Subterranea();

	public Subterranea getSubterranea () {

		Subterranea sub = subterranea;

		// valores double de coordenadas - latitude e longitude
		sub.setInterDDLatitude(Double.parseDouble(tfLatitude.getText()));
		sub.setInterDDLongitude(Double.parseDouble(tfLongitude.getText()));

		// valor geometry de  coordenadas latitude longitude
		GeometryFactory geoFac = new GeometryFactory();

			Point p = geoFac.createPoint(new Coordinate(
					Double.parseDouble(tfLongitude.getText()),
					Double.parseDouble(tfLatitude.getText()
							)));

			p.setSRID(4674);

			sub.setInterGeom(p);

		// adicionar o id escolhido no combobox
		baciaHidrografica.setBaciaID(baciaID);
		unidadeHidrografica.setUhID(unidHidID);
		tipoPoco.setTipoPocoID(tipoPocoID);
		subSistema.setSubID(subSistemaID);

		sub.setInterBaciaFK(baciaHidrografica);
		sub.setInterUHFK(unidadeHidrografica);
		sub.setSubTipoPocoFK(tipoPoco);
		sub.setSubSubSistemaFK(subSistema);

		sub.setSubVazao(tfVazaoPoco.getText());
		sub.setSubEstatico(tfEstatico.getText());
		sub.setSubDinamico(tfDinamico.getText());
		sub.setSubProfundidade(tfProfundidade.getText());
		sub.setSubCaesb(cbSubCaesb.getValue());

		if (dpDataOperacao.getValue() == null) {

			sub.setSubDataOperacao(null);}
		else {

			sub.setSubDataOperacao(Date.valueOf(dpDataOperacao.getValue()));}
		
		// FINALIDADE REQUERIDA //
		FinalidadeRequerida fr = new FinalidadeRequerida();

		for (Finalidade f : sub.getFinalidades() ) {

			if (f.getClass().getName() == "entidades.FinalidadeRequerida") {
				fr = (FinalidadeRequerida) f;
				System.out.println("sub - finalidade requerida ID " + fr.getFinID());
			}

		}

		GetterAndSetterFinalidades gsRequerida = new GetterAndSetterFinalidades();
		
		gsRequerida.inicializarVariaveisFinalidadesRequeridas();
		
		gsRequerida.setFinalidade(
				fr, 
				tfListFinReq, tfListSubfinReq, tfListQuanReq, tfListConReq, tfListVazoesReq, 
				lblCalTotalReq, 
				tfVazoesLDReq, tfVazoesHDReq, tfPeriodoDMReq);

		fr.setFinInterferenciaFK(sub);
		sub.getFinalidades().add(fr);
		
		// FINALIDADE AUTORIZADA //
		FinalidadeAutorizada fa = new FinalidadeAutorizada();

		for (Finalidade f : sub.getFinalidades() ) {

			if (f.getClass().getName() == "entidades.FinalidadeAutorizada") {
				fa = (FinalidadeAutorizada) f;
				System.out.println("sub - finalidade autorizada ID " + fa.getFinID());
			}

		}

		GetterAndSetterFinalidades gsAutorizada = new GetterAndSetterFinalidades();
		
		gsAutorizada.inicializarVariaveisFinalidadesAutorizadas();
		
		gsAutorizada.setFinalidade(
				fa, 
				tfListFinAut, tfListSubfinAut, tfListQuanAut, tfListConAut, tfListVazoesAut, 
				lblCalTotalAut, 
				tfVazoesLDAut, tfVazoesHDAut, tfPeriodoDMAut);

		fa.setFinInterferenciaFK(sub);
		sub.getFinalidades().add(fa);
			
		return sub;

	}

	public void setSubterranea (Subterranea sub) {

		System.out.println("TabSub - setSubterranea id " + sub.getInterID());

		tfLatitude.setText(String.valueOf(sub.getInterDDLatitude()));
		tfLongitude.setText(String.valueOf(sub.getInterDDLongitude()));

		cbBaciaHidrografica.setValue(sub.getInterBaciaFK().getBaciaNome());
		cbUnidadeHidrografica.setValue(String.valueOf(sub.getInterUHFK().getUhID()));

		cbTipoPoco.setValue(sub.getSubTipoPocoFK().getTipoPocoDescricao());
		cbSubsistema.setValue(sub.getSubSubSistemaFK().getSubDescricao());

		cbSubCaesb.setValue(sub.getSubCaesb());

		tfVazaoPoco.setText(sub.getSubVazao());
		tfEstatico.setText(sub.getSubEstatico());
		tfDinamico.setText(sub.getSubDinamico());
		tfProfundidade.setText(sub.getSubProfundidade());

		if (sub.getSubDataOperacao() == null) {
			dpDataOperacao.getEditor().clear();
		} else {
			Date d = sub.getSubDataOperacao();
			dpDataOperacao.setValue(d.toLocalDate());
		}
		
		// FINALIDADES REQUERIDAS
		FinalidadeRequerida fr = new FinalidadeRequerida(); 

		for (Finalidade f : sub.getFinalidades() ) {

			if (f.getClass().getName() == "entidades.FinalidadeRequerida") {
				fr = (FinalidadeRequerida) f;
				System.out.println("sub - finalidade id " + fr.getFinID());
			}

		}
		
		GetterAndSetterFinalidades gsFinalidades = new GetterAndSetterFinalidades();
		
		gsFinalidades.inicializarVariaveisFinalidadesRequeridas();
		
		gsFinalidades.getFinalidade(
				fr, 
				tfListFinReq, tfListSubfinReq, tfListQuanReq, tfListConReq, tfListVazoesReq, 
				lblCalTotalReq, 
				tfVazoesLDReq, tfVazoesHDReq, tfPeriodoDMReq);
		
		fr.setFinInterferenciaFK(sub);

		System.out.println("tab SUB antes do iterator " + sub.getFinalidades().size());

		Iterator<Finalidade> it;

		for (it = sub.getFinalidades().iterator(); it.hasNext();)
		{
			Finalidade f = (Finalidade) it.next();
			if (f.getFinID() == fr.getFinID()) {
				it.remove();
				System.out.println("TabSubterranea - finalidade já existente? Iterator " + ( f.getFinID() == fr.getFinID()));
			}
		}
		System.out.println("tab SUB depois antes de adicionar fr " + sub.getFinalidades().size());

		sub.getFinalidades().add(fr);

		System.out.println("tab SUB depois " + sub.getFinalidades().size());
		
		
		// FINALIDADES AUTORIZADAS
		FinalidadeAutorizada fa = new FinalidadeAutorizada();

		for (Finalidade f : sub.getFinalidades() ) {

			if (f.getClass().getName() == "entidades.FinalidadeAutorizada") {
				fa = (FinalidadeAutorizada) f;
				System.out.println("sub - finalidade id - autorizada " + fr.getFinID());
			}

		}
		
		GetterAndSetterFinalidades gsAutorizada = new GetterAndSetterFinalidades();
		
		gsAutorizada.inicializarVariaveisFinalidadesAutorizadas();
		
		gsAutorizada.getFinalidade(
				fa, 
				tfListFinAut, tfListSubfinAut, tfListQuanAut, tfListConAut, tfListVazoesAut, 
				lblCalTotalAut, 
				tfVazoesLDAut, tfVazoesHDAut, tfPeriodoDMAut);
		
		fa.setFinInterferenciaFK(sub);

		System.out.println("tab SUB antes do iterator " + sub.getFinalidades().size());

		Iterator<Finalidade> itAut;

		for (itAut = sub.getFinalidades().iterator(); itAut.hasNext();)
		{
			Finalidade f = (Finalidade) itAut.next();
			if (f.getFinID() == fa.getFinID()) {
				itAut.remove();
				System.out.println("TabSubterranea - finalidade autorizada já existente? Iterator " + ( f.getFinID() == fa.getFinID()));
			}
		}
		System.out.println("tab SUB depois antes de adicionar fa " + sub.getFinalidades().size());

		sub.getFinalidades().add(fa);

		System.out.println("tab SUB depois finalida autorizada " + sub.getFinalidades().size());
		
		this.subterranea = sub;	

	}

	int baciaID = 1;
	final int [] listaBaciasID = new int [] { 1,2,3,4,5,6,7,8 };

	int unidHidID = 1;
	final int [] listaUHID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41};

	int tipoPocoID = 1;
	final int [] listaTipoPoco = new int [] { 1,2 };

	int subSistemaID = 1;
	final int [] listaSubsistema = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13 };

	ObservableList<String> olFinalidades = FXCollections.observableArrayList(

			"Abastecimento Humano"	,
			"Criação De Animais"	,
			"Irrigação"				,

			"Uso Comercial"			,
			"Uso Industrial"		,

			"Piscicultura"			,
			"Lazer"					,

			"Outras Finalidades"	

			);

	ObservableList<String> olSubFinalidades = FXCollections.observableArrayList(

			"Área Rural"	,
			"Área Urbana"	,
			""	,
			"Aves"	,
			"Bovinos"	,
			"Bubalino"	,
			"Caprinos"	,
			"Equinos"	,
			"Ovinos"	,
			"Piscicultura"	,
			"Suínos"	,
			""	,
			"Abacaxi"	,
			"Abóbora"	,
			"Agrião"	,
			"Agrofloresta"	,
			"Agroindústria"	,
			"Agroturismo"	,
			""	,
			"Alface"	,
			"Alho"	,
			"Bambu"	,
			"Banana"	,
			"Café"	,
			"Feijão"	,
			"Flores"	,
			"Flores"	,
			"Frutífera"	,
			"Frutífera"	,
			"Gramínea"	,
			"Grãos"	,
			"Hortaliças"	,
			"Mandioca"	,
			"Milho"	,
			"Mogno"	,
			"Paisagismo"	,
			"Reflorestamento"	,
			"Tomate",
			""	,
			"Lavagem De Veículo"	,
			"Concreto"	,
			"Construção Civil"	,
			"Fabricação De Gelo"	,
			"Farmacêutica"	,
			""	,
			"Tanque Escavado Não Revestido"	,
			"Tanque Escavado Revestido"	,


			"Água Mineral"



			);

	ObservableList<String> olSubSis = FXCollections
			.observableArrayList(

					"S/A       ",
					"A         ",
					"R3/Q3     ",
					"R4        ",
					"F         ",
					"PPC       ",
					"F/Q/M     ",
					"P1        ",
					"P2        ",
					"P3        ",
					"P4        ",
					"BAMBUÍ    ",
					"ARAXÁ     "

					); 

	ObservableList<String> olTipoPoco = FXCollections
			.observableArrayList(
					"Manual", 
					"Tubular"

					); 

	ObservableList<String> olSubCaesb = FXCollections
			.observableArrayList(
					"Sim", 
					"Não"
					); 

	ObservableList<String>  olBacia = FXCollections
			.observableArrayList(

					"Rio Corumbá"			,
					"Rio Descoberto"		,
					"Rio Paranã"			,
					"Rio São Bartolomeu"	,
					"Rio São Marcos"		,
					"Rio Maranhão"			,
					"Rio Paranoá"			,
					"Rio Preto"	

					); 

	ObservableList<String> 	olUniHid = FXCollections
			.observableArrayList(

					"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
					"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41"
					); 

	SubSistema subSistema = new SubSistema ();
	TipoPoco tipoPoco = new TipoPoco();
	BaciasHidrograficas baciaHidrografica = new  BaciasHidrograficas();
	UnidadeHidrografica unidadeHidrografica = new UnidadeHidrografica();

	@FXML Pane pSubterranea;

	public static TabSubterraneaController tabSubCon;

	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tabSubCon = this;

		inicializarComponentes();

		cbBaciaHidrografica.setItems(olBacia);
		cbUnidadeHidrografica.setItems(olUniHid);
		cbBaciaHidrografica.setItems(olBacia);
		cbUnidadeHidrografica.setItems(olUniHid);
		cbTipoPoco.setItems(olTipoPoco);
		cbSubCaesb.setItems(olSubCaesb);
		cbSubsistema.setItems(olSubSis);

		cbBaciaHidrografica.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)

					baciaID = listaBaciasID [(int) new_value];

				//System.out.println(" bacia id" + baciaID);

			}
		});

		cbUnidadeHidrografica.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)
					unidHidID = listaUHID [(int) new_value];

				//System.out.println("unidade hidr selecionada " + unidHidID);

			}
		});

		cbTipoPoco.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)
					tipoPocoID = listaTipoPoco [(int) new_value];
				//System.out.println(" tipo poço id" + tipoPocoID);

			}
		});

		cbSubsistema.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)
					subSistemaID = listaSubsistema [(int) new_value];
				//System.out.println("susbsistema ID tabSubterranea " + subSistemaID);

			}
		});

		// listeners para envitar valor maior que cinco caracteres
		tfVazaoPoco.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than LIMIT
					if (tfVazaoPoco.getText().length() >= 5) {

						// if it's 11th character then just setText to previous
						// one
						tfVazaoPoco.setText(tfVazaoPoco.getText().substring(0, 5));
					}
				}
			}
		});

		tfEstatico.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than LIMIT
					if (tfEstatico.getText().length() >= 5) {

						// if it's 11th character then just setText to previous
						// one
						tfEstatico.setText(tfEstatico.getText().substring(0, 5));
					}
				}
			}
		});

		tfDinamico.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than LIMIT
					if (tfDinamico.getText().length() >= 5) {

						// if it's 11th character then just setText to previous
						// one
						tfDinamico.setText(tfDinamico.getText().substring(0, 5));
					}
				}
			}
		});

		tfProfundidade.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than LIMIT
					if (tfProfundidade.getText().length() >= 5) {

						// if it's 11th character then just setText to previous
						// one
						tfProfundidade.setText(tfProfundidade.getText().substring(0, 5));

					}

				} // fim if length

			}
		});

	} // FIM INITIALIZE

	Componentes com;

	Pane pDadosSubterranea;

	TextField tfLatitude;
	TextField tfLongitude;

	Button btnLatLon;

	ComboBox<String> cbBaciaHidrografica;
	ComboBox<String> cbUnidadeHidrografica;
	ComboBox<String> cbTipoPoco;
	ComboBox<String> cbSubsistema;	
	ComboBox<String> cbSubCaesb;

	TextField tfVazaoPoco;
	TextField tfEstatico;
	TextField tfDinamico;
	TextField tfProfundidade;

	DatePicker dpDataOperacao;

	ArrayList<Node> listaComponentes = new ArrayList<Node>();

	Pane pFinalidades;
	Pane pVazoes;

	GridPane gpFinalidades;
	GridPane gpVazoes;

	TextField[] tfListFinReq = new TextField[5];
	TextField[] tfListSubfinReq = new TextField[5];
	TextField[] tfListQuanReq = new TextField[5];
	TextField[] tfListConReq = new TextField[5];
	TextField[] tfListVazoesReq = new TextField[5];

	TextField[] tfListFinAut = new TextField[5];
	TextField[] tfListSubfinAut = new TextField[5];
	TextField[] tfListQuanAut = new TextField[5];
	TextField[] tfListConAut = new TextField[5];
	TextField[] tfListVazoesAut = new TextField[5];

	Button [] btnLisCalReq = new Button[6];
	Button [] btnLisCalAut = new Button[6];

	Label lblCalTotalReq = new Label();
	Label lblCalTotalAut = new Label();

	ChoiceBox<String>[] listCbFinReq = new ChoiceBox[5];
	ChoiceBox<String>[] listCbSubfinReq = new ChoiceBox[5];

	ChoiceBox<String>[] listCbFinAut = new ChoiceBox[5];
	ChoiceBox<String>[] listCbSubfinAut = new ChoiceBox[5];

	TextField[] tfVazoesLDReq = new TextField[12];
	TextField[] tfVazoesHDReq = new TextField[12]; //  
	TextField[] tfPeriodoDMReq = new TextField[12];
	Button [] btnListCalMesesReq = new Button[3];

	TextField[] tfVazoesLDAut = new TextField[12];
	TextField[] tfVazoesHDAut = new TextField[12]; //  
	TextField[] tfPeriodoDMAut = new TextField[12];
	Button [] btnListCalMesesAut = new Button[3];

	public void inicializarComponentes () {

		listaComponentes.add(pDadosSubterranea = new Pane());		    
		listaComponentes.add(new Label ("Latitude (Y):"));
		listaComponentes.add(tfLatitude = new TextField());
		listaComponentes.add(new Label ("Longitude (X):"));
		listaComponentes.add(tfLongitude = new TextField());
		listaComponentes.add(btnLatLon = new Button());

		listaComponentes.add(new Label ("Bacia: "));
		listaComponentes.add(cbBaciaHidrografica = new ComboBox<>());
		listaComponentes.add(new Label ("UH: "));
		listaComponentes.add(cbUnidadeHidrografica = new ComboBox<>());
		listaComponentes.add(new Label ("Tipo de Poço: "));
		listaComponentes.add(cbTipoPoco = new ComboBox<>());
		listaComponentes.add(new Label ("Subsistema: "));
		listaComponentes.add(cbSubsistema = new ComboBox<>());
		listaComponentes.add(new Label ("Área atendida (Caesb): "));
		listaComponentes.add(cbSubCaesb = new ComboBox<>());

		listaComponentes.add(new Label ("Vazão (L/h): "));
		listaComponentes.add(tfVazaoPoco = new TextField());
		listaComponentes.add(new Label ("Nível Estático (m): "));
		listaComponentes.add(tfEstatico = new TextField());
		listaComponentes.add(new Label ("Nível Dinâmico (m): "));
		listaComponentes.add(tfDinamico = new TextField());
		listaComponentes.add(new Label ("Profundidade (m): "));
		listaComponentes.add(tfProfundidade = new TextField());
		listaComponentes.add(new Label ("Em operação desde: "));
		listaComponentes.add(dpDataOperacao = new DatePicker());

		Double  prefSizeWHeLayXY  [][] = { 
				{910.0,170.0,21.0,0.0},
				{95.0,30.0,165.0,5.0},
				{140.0,30.0,260.0,5.0},
				{95.0,30.0,410.0,5.0},
				{140.0,30.0,505.0,5.0},
				{25.0,25.0,657.0,8.0},
				{160.0,30.0,34.0,35.0},
				{160.0,30.0,34.0,65.0},
				{60.0,30.0,204.0,35.0},
				{60.0,30.0,204.0,65.0},
				{150.0,30.0,274.0,35.0},
				{150.0,30.0,274.0,65.0},
				{150.0,30.0,434.0,35.0},
				{150.0,30.0,434.0,65.0},
				{150.0,30.0,594.0,35.0},
				{150.0,30.0,594.0,65.0},
				{120.0,30.0,754.0,35.0},
				{120.0,30.0,754.0,65.0},
				{120.0,30.0,172.0,95.0},
				{120.0,30.0,172.0,125.0},
				{125.0,30.0,302.0,95.0},
				{125.0,30.0,301.0,125.0},
				{120.0,30.0,436.0,95.0},
				{120.0,30.0,436.0,125.0},
				{130.0,30.0,565.0,95.0},
				{130.0,30.0,565.0,125.0},
		};

		com = new Componentes();
		com.popularTela(listaComponentes, prefSizeWHeLayXY, pSubterranea);

		TabPane tp = new TabPane();
		
		tp.setPrefSize(950, 320);
		tp.setLayoutX(0.0);
		tp.setLayoutY(170.0);

		Tab tabFinalidadeRequerida = new Tab("Finalidade Requerida");
		Tab tabFinalidadeAutorizada = new Tab("Finalidade Autorizada");
		
		tabFinalidadeRequerida.setClosable(false);
		tabFinalidadeAutorizada.setClosable(false);
		
		
		tp.getTabs().addAll(tabFinalidadeRequerida, tabFinalidadeAutorizada);

		pFinalidades = new Pane();
		pFinalidades.setPrefSize(910, 150);
		pFinalidades.setLayoutX(15);
		pFinalidades.setLayoutY(15);

		pVazoes = new Pane();
		pVazoes.setPrefSize(910, 120);
		pVazoes.setLayoutX(15);
		pVazoes.setLayoutY(180);

			Pane pFinReq = new  Pane();
			pFinReq.setPrefSize(940, 300);
			pFinReq.getChildren().addAll(pFinalidades, pVazoes);

			tabFinalidadeRequerida.setContent(pFinReq);
		
		gpFinalidades = new GridPane();
		gpVazoes = new GridPane();
		
		gpFinalidades.setMinSize(910, 140); 
		gpFinalidades.setMaxSize(910, 140); 
		gpFinalidades.setLayoutX(20);

		gpVazoes.setMinSize(910, 100); 
		gpVazoes.setMaxSize(910, 100); 
		gpVazoes.setLayoutX(20);

			pFinalidades.getChildren().add(gpFinalidades);
			pVazoes.getChildren().add(gpVazoes);

		
			inicializarFinalidades(
					gpFinalidades, gpVazoes,
					tfListFinReq,tfListSubfinReq,tfListQuanReq,tfListConReq,tfListVazoesReq,
					btnLisCalReq,lblCalTotalReq,listCbFinReq,listCbSubfinReq,
					tfVazoesLDReq,tfVazoesHDReq,tfPeriodoDMReq,btnListCalMesesReq
					);
			
		pFinalidades = new Pane();
		pFinalidades.setPrefSize(910, 150);
		pFinalidades.setLayoutX(15);
		pFinalidades.setLayoutY(15);

		pVazoes = new Pane();
		pVazoes.setPrefSize(910, 120);
		pVazoes.setLayoutX(15);
		pVazoes.setLayoutY(180);
		//pVazoesAutorizadas.setStyle("-fx-background-color: blue");
	
		Pane pFinAut = new  Pane();
		pFinAut.setPrefSize(940, 300);
		pFinAut.getChildren().addAll(pFinalidades, pVazoes);
		
		tabFinalidadeAutorizada.setContent(pFinAut);
		
			gpFinalidades = new GridPane();
			gpVazoes = new GridPane();
			
			gpFinalidades.setMinSize(910, 140); 
			gpFinalidades.setMaxSize(910, 140); 
			gpFinalidades.setLayoutX(20);
	
			gpVazoes.setMinSize(910, 100); 
			gpVazoes.setMaxSize(910, 100);  
			gpVazoes.setLayoutX(20);

			pFinalidades.getChildren().add(gpFinalidades);
			pVazoes.getChildren().add(gpVazoes);
		
			inicializarFinalidades(
					gpFinalidades, gpVazoes,
					tfListFinAut,tfListSubfinAut,tfListQuanAut,tfListConAut,tfListVazoesAut,
					btnLisCalAut,lblCalTotalAut,listCbFinAut,listCbSubfinAut,
					tfVazoesLDAut,tfVazoesHDAut,tfPeriodoDMAut,btnListCalMesesAut
					);

		
		pSubterranea.getChildren().add(tp);

	}

	public void inicializarFinalidades (
			GridPane gpFinalidades, GridPane gpVazoes,
			TextField[] tfFinalidade,TextField[] tfSubfinalidade,TextField[] tfQuantidade,TextField[] tfConsumo,TextField[] tfVazoes,
			Button [] btnCalculo,Label lblCalculoTotal,ChoiceBox<String>[] cbListFinalidade,ChoiceBox<String>[] cbListSubfinalidade,
			TextField[] tfVazoesLD,TextField[] tfVazoesHD,TextField[] tfPeriodoDM,Button [] btnListCalMeses
			) {


		String[] strLabelFinalidade = {
				"Finalidade", " ","Subfinalidade", " ", "Quant (unid)", "Consumo(L/dia)", "TOTAL", " "
		};

		Label lbl; 

		for (int i = 0; i<8; i++ ) {
			gpFinalidades.add(lbl = new Label(strLabelFinalidade[i]), i, 0); // child, columnIndex, rowIndex
			lbl.setAlignment( Pos.CENTER );
			lbl.setMinSize(50, 20);
		}

		for (int i = 0; i<5; i++ ) {

			TextField tfFin = tfFinalidade [i] = new TextField();
			TextField tfSub = tfSubfinalidade [i] = new TextField();
			TextField tfQuant = tfQuantidade [i] = new TextField();
			TextField tfCon = tfConsumo [i] = new TextField();
			TextField tfVaz = tfVazoes [i] = new TextField();

			Button btnCal = btnCalculo [i] = new Button();

			btnCal.setPrefSize(25, 25);

			ChoiceBox<String> cbFin =  cbListFinalidade [i] = new ChoiceBox<String>();
			cbFin.setItems(olFinalidades);
			cbFin.setPrefSize(50, 20);

			ChoiceBox<String> cbSub =  cbListSubfinalidade [i] = new ChoiceBox<String>();
			cbSub.setItems(olSubFinalidades);
			cbSub.setPrefSize(50, 20);

			gpFinalidades.add(tfFin, 0, i+1); // child, columnIndex, rowIndex
			gpFinalidades.add(cbFin, 1, i+1);

			gpFinalidades.add(tfSub, 2, i+1);
			gpFinalidades.add(cbSub, 3, i+1);

			gpFinalidades.add(tfQuant, 4, i+1); 
			gpFinalidades.add(tfCon, 5, i+1); 

			gpFinalidades.add(tfVaz, 6, i+1);

			gpFinalidades.add(btnCal, 7, i+1);

			tfQuant.lengthProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {

					if (newValue.intValue() > oldValue.intValue()) {
						// Check if the new character is greater than LIMIT
						if (tfQuant.getText().length() >= 0) {

							/*   Nao permitir letras - variavel double, somente numeros com ponto ou virgula
							 */
							if ( tfQuant.getText().matches("(.*)[a-zA-Z](.*)") == true ) {
								// buscar letras entre os numeros
								Alerta a = new Alerta ();
								a.alertar(new Alert(Alert.AlertType.ERROR, "Somente números!!!", ButtonType.OK));

								// retirar caracter errado, como letra, virgula etc
								tfQuant.setText(tfQuant.getText().substring(0, tfQuant.getText().length() - 1));

							}

						}

					} // fim if length
				}
			});

			// AÇOES DOS BOTOES

			tfCon.lengthProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {

					if (newValue.intValue() > oldValue.intValue()) {
						// Check if the new character is greater than LIMIT
						if (tfCon.getText().length() >= 0) {

							/*  Nao permitir letras - variavel double, somente numeros com ponto ou virgula
							 */
							if ( tfCon.getText().matches("(.*)[a-zA-Z](.*)") == true ) {
								// buscar letras entre os numeros
								Alerta a = new Alerta ();
								a.alertar(new Alert(Alert.AlertType.ERROR, "Somente números!!!", ButtonType.OK));

								// retirar caracter errado, como letra, virgula etc
								tfCon.setText(tfCon.getText().substring(0, tfCon.getText().length() - 1));

							}

						}

					} // fim if length
				}
			});

			cbFin.getSelectionModel().selectedItemProperty().addListener( 

					(ObservableValue<? extends String> observable, String oldValue, String newValue) ->

					tfFin.setText(newValue)
					);

			cbSub.getSelectionModel().selectedItemProperty().addListener( 

					(ObservableValue<? extends String> observable, String oldValue, String newValue) ->

					tfSub.setText(newValue)
					);

			btnCal.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Double result = Double.parseDouble(tfQuant.getText().replace(",", ".")) * Double.parseDouble(tfCon.getText().replace(",", "."));
					tfVaz.setText(String.valueOf(result));
				}
			});

		}

		String[] lblVazoesMeses = {
				"Mês","JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ", " ",
		};

		for (int i = 0; i<14; i++ ) {
			gpVazoes.add(lbl = new Label(lblVazoesMeses[i]), i, 0); // child, columnIndex, rowIndex
			lbl.setMinSize(50, 20);
		}


		for (int i = 0; i<12; i++ ) {

			TextField tfVazLD = tfVazoesLD [i] = new TextField();
			gpVazoes.add(tfVazLD, i+1, 1); // child, columnIndex, rowIndex

		}

		for (int i = 0; i<12; i++ ) {

			TextField tfVazHD = tfVazoesHD [i] = new TextField();
			gpVazoes.add(tfVazHD, i+1, 2);

			tfVazHD.lengthProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {

					if (newValue.intValue() > oldValue.intValue()) {
						// Check if the new character is greater than LIMIT
						if (tfVazHD.getText().length() >= 0) {

							/*  Nao permitir virgula e ponto no tempo de captacao e nos dias 
							 *  por mes que sempre e um numero inteiro
							 */
							if ( tfVazHD.getText().matches("(.*)\\D(.*)") == true ) {
								// "(.*)\\D(.*)" buscar qualquer digito diferente de numero no meio do que foi digitado
								Alerta a = new Alerta ();
								a.alertar(new Alert(Alert.AlertType.ERROR, "Somente números!!!", ButtonType.OK));

								// retirar caracter errado, como letra, virgula etc
								tfVazHD.setText(tfVazHD.getText().substring(0, tfVazHD.getText().length() - 1));

							}

						}

					} // fim if length
				}
			});

		}

		for (int i = 0; i<12; i++ ) {

			TextField tfPerDM = tfPeriodoDM [i] = new TextField();
			gpVazoes.add(tfPerDM, i+1, 3);

			tfPerDM.lengthProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {

					if (newValue.intValue() > oldValue.intValue()) {
						// Check if the new character is greater than LIMIT
						if (tfPerDM.getText().length() >= 0) {

							/*  Nao permitir virgula e ponto no tempo de captacao e nos dias 
							 *  por mes que sempre e um numero inteiro
							 */
							if (tfPerDM.getText().matches("(.*)\\D(.*)") == true ) { 
								// "(.*)\\D(.*)" buscar qualquer digito diferente de numero no meio do que foi digitado

								Alerta a = new Alerta ();
								a.alertar(new Alert(Alert.AlertType.ERROR, "Somente números!!!", ButtonType.OK));

								tfPerDM.setText(tfPerDM.getText().substring(0, tfPerDM.getText().length() - 1));

							}

						}

					} // fim if length
				}
			});

		}

		String[] lblMesVazaoTempoPeriodo = {
				"Vazão (l/dia) (*)","Tempo (h/dia)", "Período (dia/mês)",
		};

		for (int i = 0; i<3; i++ ) {

			Button btnCalMes = btnListCalMeses[i] = new Button();
			btnCalMes.setPrefSize(25, 25);
			gpVazoes.add(btnCalMes, 13, i+1);
			gpVazoes.add(lbl = new Label(lblMesVazaoTempoPeriodo[i]), 0, i+1);  // child, columnIndex, rowIndex
			lbl.setMinSize(110, 20);
			lbl.setAlignment( Pos.CENTER );
		} // fim loop for


		// botao para calcular o valor total de TODAS as finalidades
		Button btnCal6 = btnCalculo[5] = new Button();
		btnCal6.setPrefSize(25, 25);
		gpFinalidades.add(btnCal6, 7, 6);

		// adicionar label com resultado total no gridpane e centralizar o texto
		lblCalculoTotal.setText("0.0");
		lblCalculoTotal.setMaxWidth(Double.MAX_VALUE);
		lblCalculoTotal.setAlignment(Pos.CENTER);
		gpFinalidades.add(lblCalculoTotal, 6, 6);

		// calcular o valor total das finalidades
		btnCal6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Double result = 0.0;

				for (int i = 0; i<5;i++) {
					if (! tfVazoes[i].getText().isEmpty())
						result += Double.parseDouble(
								tfVazoes[i].getText());


				}
				lblCalculoTotal.setText(String.valueOf(result));

			}
		});

		// facilitar o cadastro dos meses 
		btnListCalMeses[2].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int meses [] =  {31,28,31,30,31,30,31,31,30,31,30,31};
				for (int i = 0; i<12;i++) {
					tfPeriodoDM [i].setText(String.valueOf(meses[i]));
				}

			}
		});


		// facilitar o cadastro dos meses 
		btnLatLon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				capturarCoordenadas();



			}
		});


	} // fim inicializarFinalidades

	public void capturarCoordenadas () {

		String lat = ControladorPrincipal.capturarGoogleMaps().getLat();
		String lon = ControladorPrincipal.capturarGoogleMaps().getLon();

		tfLatitude.setText(lat);
		tfLongitude.setText(lon);

		GeometryFactory geoFac;
		Point p;

		geoFac = new GeometryFactory();

		p = geoFac.createPoint(new Coordinate(
				Double.parseDouble(lon),
				Double.parseDouble(lat)
				));

		p.setSRID(4674);

		BaciasHidrograficasDao bacias = new BaciasHidrograficasDao();
		List<BaciasHidrograficas> listBacias = bacias.listarBaciasHidrograficas("");

		UnidadeHidrograficaDao uhs = new UnidadeHidrograficaDao();
		List<UnidadeHidrografica> listUnidades = uhs.listarUnidadesHidrograficas("");

		for (BaciasHidrograficas b : listBacias) {

			if (p.intersects(b.getBaciaShape())) {

				cbBaciaHidrografica.setValue(String.valueOf(b.getBaciaNome()));

				//System.out.println("nome da bacia " + b.getBaciaNome() );
			}

		} // fim loop bacias hidrograficas

		for (UnidadeHidrografica u : listUnidades) {


			if (p.intersects(u.getShape())) {

				cbUnidadeHidrografica.setValue(String.valueOf(u.getUhCodigo()));

				//System.out.println("nome da uh " + u.getUhNome() );
			}

		} // fim loop unidades hidrograficas

	} // fim metodo capturar coordenadas

}


//Image imgSub = new Image(TabSubterraneaController.class.getResourceAsStream("/images/subterranea.png"));
//@FXML ImageView	iVewSubt = new ImageView();


//iVewSubt.setImage(imgSub);

/*
dpDataSubterranea.setConverter(new StringConverter<LocalDate>() {

	@Override
	public String toString(LocalDate t) {
		if (t != null) {
			return formatter.format(t);
		}
		return null;
	}

	@Override
	public LocalDate fromString(String string) {
		if (string != null && !string.trim().isEmpty()) {
			return LocalDate.parse(string, formatter);
		}
		return null;
	}

});
 */

