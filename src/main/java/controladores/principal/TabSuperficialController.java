package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
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
import entidades.FormaCaptacao;
import entidades.GetterAndSetter;
import entidades.GetterAndSetterFinalidades;
import entidades.LocalCaptacao;
import entidades.MetodoIrrigacao;
import entidades.Superficial;
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

public class TabSuperficialController implements Initializable{

	Superficial superficial = new Superficial();

	ObservableList<String> olLocalCaptacao = FXCollections
			.observableArrayList(

					"Nascente",
					"Rio",
					"Reservatório",
					"Canal",
					"Lago Natural"

					); 

	ObservableList<String> olFormaCaptacao = FXCollections
			.observableArrayList(
					"Bombeamento" , 
					"Gravidade"
					); 

	ObservableList<String> olMetodoIrrigacao = FXCollections
			.observableArrayList(
					"Aspersão"	,
					"Gotejamento",
					"Pivô",
					"Manual",
					"Aspersão/gotejamento"	

					); 

	ObservableList<String> olBarramento = FXCollections
			.observableArrayList(
					"Sim",
					"Não"
					); 

	ObservableList<String> olCaesb = FXCollections
			.observableArrayList(
					"Sim" , 
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


	int baciaID = 1;
	final int [] listaBaciasID = new int [] { 1,2,3,4,5,6,7,8 };

	int unidHidID = 1;
	final int [] listaUHID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41};

	int localCaptacaoID = 1;
	String strLocalCaptacao = "Nascente";
	final int [] listaLocalCaptacao = new int [] { 1,2,3,4,5 };

	int formaCaptacaoID = 1;
	String strFormaCaptacao = "Bombeamento";
	final int [] listaFormaCaptacao = new int [] { 1,2};

	int metodoIrrigacaoID = 1;
	String strMetodoIrrigacao = "Aspersão";
	final int [] listaMetodoIrrigacao = new int [] { 1,2,3,4,5 };

	

	BaciasHidrograficas baciaHidrografica = new  BaciasHidrograficas();
	UnidadeHidrografica unidadeHidrografica = new UnidadeHidrografica();

	public Superficial getSuperficial () {

		Superficial sup = superficial;

		
		try {
		// valores double de coordenadas - latitude e longitude
		sup.setInterDDLatitude(Double.parseDouble(tfLatitude.getText()));
		sup.setInterDDLongitude(Double.parseDouble(tfLongitude.getText()));
		} catch (Exception e) {
			
			// buscar letras entre os numeros
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
			e.printStackTrace();
			
			
		}
		

		// valor geometry de  coordenadas latitude longitude
		GeometryFactory geoFac = new GeometryFactory();

		Point p = geoFac.createPoint(new Coordinate(
				Double.parseDouble(tfLongitude.getText()),
				Double.parseDouble(tfLatitude.getText()
						)));

		p.setSRID(4674);

		sup.setInterGeom(p);

		// adicionar o id escolhido no combobox
		baciaHidrografica.setBaciaID(baciaID);
		unidadeHidrografica.setUhID(unidHidID);

		LocalCaptacao lc = new LocalCaptacao();
		lc.setLocalCatacaoID(localCaptacaoID);

		FormaCaptacao fc = new FormaCaptacao();
		fc.setFormaCaptacaoID(formaCaptacaoID);

		MetodoIrrigacao mi = new MetodoIrrigacao();
		mi.setMetodoIrrigacaoID(metodoIrrigacaoID);

		sup.setSupBarramento(cbBarramento.getValue());

		sup.setInterBaciaFK(baciaHidrografica);
		sup.setInterUHFK(unidadeHidrografica);

		sup.setSupLocalCaptacaoFK(lc);		// sup_Local; //-- () canal () rio () reservatório () lago natural () nascente
		sup.setSupFormaCaptacaoFK(fc); // bombeamento gravidade 
		sup.setSupMetodoIrrigacaoFK(mi); // Aspersão Gotejamento Pivô Manual Aspersão/gotejamento

		if (dpDataOperacao.getValue() == null) {

			sup.setSupDataOperacao(null);}
		else {
			sup.setSupDataOperacao(Date.valueOf(dpDataOperacao.getValue()));

		}

		sup.setSupCaesb(cbCaesb.getValue());

		sup.setSupCorpoHidrico(tfCorpoHidrico.getText());
		sup.setSupAreaIrrigada(tfAreaIrrigada.getText());
		sup.setSupAreaContribuicao(tfAreaContribuicao.getText());

		sup.setSupPotenciaBomba(tfPotenciaBomba.getText()); // potência da bomba
		sup.setSupMarcaBomba(tfMarcaBomba.getText()); // marca da bomba

		sup.setSupAreaPropriedade(tfAreaPropriedade.getText());

		// FINALIDADE REQUERIDA //
		FinalidadeRequerida fr = new FinalidadeRequerida();

		for (Finalidade f : sup.getFinalidades() ) {

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

		fr.setFinInterferenciaFK(sup);
		sup.getFinalidades().add(fr);
		
		// FINALIDADE AUTORIZADA //
		FinalidadeAutorizada fa = new FinalidadeAutorizada();

		for (Finalidade f : sup.getFinalidades() ) {

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

		fa.setFinInterferenciaFK(sup);
		sup.getFinalidades().add(fa);

		return sup;

	};

	public void setSuperficial (Superficial sup) {

		tfLatitude.setText(String.valueOf(sup.getInterDDLatitude()));
		tfLongitude.setText(String.valueOf(sup.getInterDDLongitude()));

		// valor geometry de  coordenadas latitude longitude
		GeometryFactory geoFac = new GeometryFactory();

		Point p = geoFac.createPoint(new Coordinate(
				Double.parseDouble(tfLongitude.getText()),
				Double.parseDouble(tfLatitude.getText()
						)));

		p.setSRID(4674);

		sup.setInterGeom(p);

		cbBaciaHidrografica.setValue(sup.getInterBaciaFK().getBaciaNome());
		cbUnidadeHidrografica.setValue(String.valueOf(sup.getInterUHFK().getUhID()));

		cbLocalCaptacao.setValue(sup.getSupLocalCaptacaoFK().getLocalCaptacaoDescricao());
		cbFormaCaptacao.setValue(sup.getSupFormaCaptacaoFK().getFormaCaptacaoDescricao());
		cbMetodoIrrigacao.setValue(sup.getSupMetodoIrrigacaoFK().getMetodoIrrigacaoDescricao());
		cbBarramento.setValue(sup.getSupBarramento());

		if (sup.getSupDataOperacao() == null) {
			dpDataOperacao.getEditor().clear();
		} else {
			Date d = sup.getSupDataOperacao();  
			dpDataOperacao.setValue(d.toLocalDate());
		}

		tfPotenciaBomba.setText(sup.getSupPotenciaBomba());
		tfMarcaBomba.setText(sup.getSupMarcaBomba());

		tfCorpoHidrico.setText(sup.getSupCorpoHidrico());
		tfAreaIrrigada.setText(sup.getSupAreaIrrigada());
		tfAreaContribuicao.setText(sup.getSupAreaContribuicao());
		tfAreaPropriedade.setText(sup.getSupAreaPropriedade());

		cbCaesb.setValue(sup.getSupCaesb());

		// FINALIDADES REQUERIDAS
				FinalidadeRequerida fr = new FinalidadeRequerida(); 

				for (Finalidade f : sup.getFinalidades() ) {

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
				
				fr.setFinInterferenciaFK(sup);

				System.out.println("tab SUB antes do iterator " + sup.getFinalidades().size());

				Iterator<Finalidade> it;

				for (it = sup.getFinalidades().iterator(); it.hasNext();)
				{
					Finalidade f = (Finalidade) it.next();
					if (f.getFinID() == fr.getFinID()) {
						it.remove();
						System.out.println("TABSUPERFICIAL - finalidade já existente? Iterator " + ( f.getFinID() == fr.getFinID()));
					}
				}
				System.out.println("tab SUP depois antes de adicionar fr " + sup.getFinalidades().size());

				sup.getFinalidades().add(fr);

				System.out.println("tab SUP depois " + sup.getFinalidades().size());
				
				
				// FINALIDADES AUTORIZADAS
				FinalidadeAutorizada fa = new FinalidadeAutorizada();

				for (Finalidade f : sup.getFinalidades() ) {

					if (f.getClass().getName() == "entidades.FinalidadeAutorizada") {
						fa = (FinalidadeAutorizada) f;
						System.out.println("SUP - finalidade id - autorizada " + fr.getFinID());
					}

				}
				
				GetterAndSetterFinalidades gsAutorizada = new GetterAndSetterFinalidades();
				
				gsAutorizada.inicializarVariaveisFinalidadesAutorizadas();
				
				gsAutorizada.getFinalidade(
						fa, 
						tfListFinAut, tfListSubfinAut, tfListQuanAut, tfListConAut, tfListVazoesAut, 
						lblCalTotalAut, 
						tfVazoesLDAut, tfVazoesHDAut, tfPeriodoDMAut);
				
				fa.setFinInterferenciaFK(sup);

				System.out.println("tab SUP antes do iterator " + sup.getFinalidades().size());

				Iterator<Finalidade> itAut;

				for (itAut = sup.getFinalidades().iterator(); itAut.hasNext();)
				{
					Finalidade f = (Finalidade) itAut.next();
					if (f.getFinID() == fa.getFinID()) {
						itAut.remove();
						System.out.println("TabSUPERFICIAL - finalidade autorizada já existente? Iterator " + ( f.getFinID() == fa.getFinID()));
					}
				}
				System.out.println("tab SUP depois antes de adicionar fa " + sup.getFinalidades().size());

				sup.getFinalidades().add(fa);

				System.out.println("tab SUP depois finalida autorizada " + sup.getFinalidades().size());
		
		this.superficial = sup;

	}

	@FXML Pane pSuperficial;

	public static TabSuperficialController tabSupCon;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tabSupCon = this;

		inicializarComponentes();

		cbBaciaHidrografica.setItems(olBacia);
		cbUnidadeHidrografica.setItems(olUniHid);
		cbLocalCaptacao.setItems(olLocalCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
		cbMetodoIrrigacao.setItems(olMetodoIrrigacao);
		cbBarramento.setItems(olBarramento);
		cbCaesb.setItems(olCaesb);

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


		cbLocalCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)

					localCaptacaoID = listaLocalCaptacao [(int) new_value];

				//System.out.println("+++ local captacao id " + localCaptacaoID);

			}
		});

		cbFormaCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)

					formaCaptacaoID = listaFormaCaptacao [(int) new_value];

				//System.out.println("---------------------------- forma captacao id " + formaCaptacaoID);

			}
		});

		cbMetodoIrrigacao.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
					Number value, Number new_value) {

				if ( (Integer) new_value !=  -1)

					metodoIrrigacaoID = listaMetodoIrrigacao [(int) new_value];

				//System.out.println("/////////////////////////////////////////////// metodo irrigacao id " + metodoIrrigacaoID);

			}
		});


		tfPotenciaBomba.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// Check if the new character is greater than LIMIT
					if (tfPotenciaBomba.getText().length() >= 5) {

						// if it's 11th character then just setText to previous
						// one
						tfPotenciaBomba.setText(tfPotenciaBomba.getText().substring(0, 5));
					}
				}
			}
		});

		/*
		tf.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfTempoBomba.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfTempoBomba.setText(tfTempoBomba.getText().substring(0, 5));
                    }
                }
            }
        });
		 */


		/*
		tfAreaContribuicao.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfAreaContribuicao.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfAreaContribuicao.setText(tfAreaContribuicao.getText().substring(0, 5));
                    }
                }
            }
        });

		 */


	} // fim initialize

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

	Componentes com;

	Pane pDadosSuperficial;

	TextField tfLatitude;
	TextField tfLongitude;

	Button btnLatLon;

	ComboBox<String> cbBaciaHidrografica;
	ComboBox<String> cbUnidadeHidrografica;
	ComboBox<String> cbLocalCaptacao;
	ComboBox<String> cbFormaCaptacao;	
	ComboBox<String> cbMetodoIrrigacao;
	ComboBox<String> cbBarramento;
	ComboBox<String> cbCaesb;

	TextField tfCorpoHidrico;
	TextField tfAreaIrrigada;
	TextField tfAreaContribuicao;
	TextField tfPotenciaBomba;
	TextField tfMarcaBomba;
	TextField tfAreaPropriedade;

	DatePicker dpDataOperacao;

	ArrayList<Node> listaComponentes = new ArrayList<Node>();


	Pane pFinalidades;
	Pane pVazoes;

	GridPane gpFinalidades;
	GridPane gpVazoes;

	public void inicializarComponentes () {

		listaComponentes.add(pDadosSuperficial = new Pane());		    
		listaComponentes.add(new Label ("Latitude (Y):"));
		listaComponentes.add(tfLatitude = new TextField());
		listaComponentes.add(new Label ("Longitude (X):"));
		listaComponentes.add(tfLongitude = new TextField());
		listaComponentes.add(btnLatLon = new Button());


		listaComponentes.add(new Label ("Bacia: "));
		listaComponentes.add(cbBaciaHidrografica = new ComboBox<>());
		listaComponentes.add(new Label ("UH: "));
		listaComponentes.add(cbUnidadeHidrografica = new ComboBox<>());
		listaComponentes.add(new Label ("Local de Captação:  "));
		listaComponentes.add(cbLocalCaptacao = new ComboBox<>());
		listaComponentes.add(new Label ("Forma de Captação: "));
		listaComponentes.add(cbFormaCaptacao = new ComboBox<>());
		listaComponentes.add(new Label ("Método de Irrigação: "));
		listaComponentes.add(cbMetodoIrrigacao = new ComboBox<>());
		listaComponentes.add(new Label ("Barramento: "));
		listaComponentes.add(cbBarramento = new ComboBox<>());
		listaComponentes.add(new Label ("Caesb: "));
		listaComponentes.add(cbCaesb = new ComboBox<>());
		listaComponentes.add(new Label ("Em operação desde: "));
		listaComponentes.add(dpDataOperacao = new DatePicker());
		

		listaComponentes.add(new Label ("Corpo Hídrico: "));
		listaComponentes.add(tfCorpoHidrico = new TextField());
		listaComponentes.add(new Label ("Área Irrigada (ha): "));
		listaComponentes.add(tfAreaIrrigada = new TextField());
		listaComponentes.add(new Label ("Área de Contribuição (ha):"));
		listaComponentes.add(tfAreaContribuicao = new TextField());
		listaComponentes.add(new Label ("Potência da Bomba (cv): "));
		listaComponentes.add(tfPotenciaBomba = new TextField());
		listaComponentes.add(new Label ("Marca da Bomba: "));
		listaComponentes.add(tfMarcaBomba = new TextField());
		listaComponentes.add(new Label ("Área da Propriedade (ha):"));
		listaComponentes.add(tfAreaPropriedade = new TextField());

		Double  prefSizeWHeLayXY  [][] = { 
				{950.0,232.0,0.0,0.0},
				{95.0,30.0,207.0,5.0},
				{140.0,30.0,302.0,5.0},
				{95.0,30.0,452.0,5.0},
				{140.0,30.0,547.0,5.0},
				{25.0,25.0,698.0,8.0},
				{160.0,30.0,5.0,45.0},
				{160.0,30.0,5.0,75.0},
				{60.0,30.0,175.0,45.0},
				{60.0,30.0,175.0,75.0},
				{140.0,30.0,245.0,45.0},
				{140.0,30.0,245.0,75.0},
				{140.0,30.0,395.0,45.0},
				{140.0,30.0,395.0,75.0},
				{140.0,30.0,545.0,45.0},
				{140.0,30.0,545.0,75.0},
				{140.0,30.0,695.0,45.0},
				{140.0,30.0,695.0,75.0},
				{100.0,30.0,847.0,45.0},
				{100.0,30.0,845.0,75.0},
				{130.0,30.0,45.0,110.0},
				{130.0,30.0,45.0,135.0},
				{260.0,30.0,185.0,110.0},
				{260.0,30.0,185.0,135.0},
				{120.0,30.0,455.0,105.0},
				{120.0,30.0,455.0,135.0},
				{160.0,30.0,586.0,105.0},
				{160.0,30.0,586.0,135.0},
				{150.0,30.0,756.0,105.0},
				{150.0,30.0,756.0,135.0},
				{140.0,30.0,320.0,165.0},
				{140.0,30.0,320.0,195.0},
				{160.0,30.0,470.0,165.0},
				{160.0,30.0,470.0,195.0},
		};

		com = new Componentes();
		com.popularTela(listaComponentes, prefSizeWHeLayXY, pSuperficial);
		
		TabPane tp = new TabPane();
		
		tp.setPrefSize(950, 320);
		tp.setLayoutX(0.0);
		tp.setLayoutY(240.0);

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

			
		pSuperficial.getChildren().add(tp);



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

		//System.out.println(lat + "," + lon);

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

		//System.out.println(listUnidades.size());

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




//ImageView	iVewSuper = new ImageView();
//Image imgSuper = new Image(TabSuperficialController.class.getResourceAsStream("/images/superficial.png"));



