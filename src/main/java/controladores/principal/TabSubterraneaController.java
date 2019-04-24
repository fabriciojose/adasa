package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import dao.BaciasHidrograficasDao;
import dao.UnidadeHidrograficaDao;
import entidades.BaciasHidrograficas;
import entidades.GetterAndSetter;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import principal.Alerta;
import principal.Componentes;

public class TabSubterraneaController implements Initializable {
	
	public Subterranea sub = new Subterranea();
	
	// variaveis de finalidade e reflexao //
	String strVariaveisFinalidades [] = {"subFinalidade1", "subFinalidade2", "subFinalidade3", "subFinalidade4", "subFinalidade5"};
	String strVariaveisSubfinaldades [] = {"subSubfinalidade1", "subSubfinalidade2", "subSubfinalidade3", "subSubfinalidade4", "subSubfinalidade5"};
	String strVariaveisQuantidades [] = {"subQuantidade1", "subQuantidade2", "subQuantidade3", "subQuantidade4", "subQuantidade5"};
	String strVariaveisConsumo [] = {"subConsumo1", "subConsumo2", "subConsumo3", "subConsumo4", "subConsumo5"};
	String strVariaveisVazao [] = {"subVazao1", "subVazao2", "subVazao3", "subVazao4", "subVazao5"};
	
	// variaveis de vazao mensal e reflexao //
			String strVariaveisVazaoMes [] = {
					
					"subQDiaJan","subQDiaFev","subQDiaMar","subQDiaAbr","subQDiaMai","subQDiaJun",
					"subQDiaJul","subQDiaAgo","subQDiaSet","subQDiaOut","subQDiaNov","subQDiaDez",	
					
			};
			
			String strVariaveisVazaoHora [] = {
					"subQHoraJan","subQHoraFev","subQHoraMar","subQHoraAbr","subQHoraMai","subQHoraJun",
					"subQHoraJul","subQHoraAgo","subQHoraSet","subQHoraOut","subQHoraNov","subQHoraDez",

			};
			
			String strVariaveisTempo  [] = {
				
					"subTempoCapJan","subTempoCapFev","subTempoCapMar","subTempoCapAbr","subTempoCapMai","subTempoCapJun",
					"subTempoCapJul","subTempoCapAgo","subTempoCapSet","subTempoCapOut","subTempoCapNov","subTempoCapDez",

			};
	
	public Subterranea getSubterranea () {
		
		System.out.println("metodo getSubterranea id " + sub.getInterID());
		
		sub.setInterID(sub.getInterID());
		
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
			sub.setSubDataOperacao(Date.valueOf(dpDataOperacao.getValue()));
			
			}
		
		// Finalidades Sub Quan Cons e Vazao //
		GetterAndSetter gs  = new GetterAndSetter();
		
		for (int i = 0; i< strVariaveisFinalidades.length; i++) {
			
			String strQuantidade;
			String strConsumo;
			String strVazoes;
			
			gs.callSetter(sub, strVariaveisFinalidades[i], tfListFinalidades[i].getText());
			gs.callSetter(sub, strVariaveisSubfinaldades[i], tfListSubfinalidades[i].getText());
			
			// Conferir se está vazio o campo
			if (! tfListQuantidades[i].getText().isEmpty()) {
						// Conferir se há letras e mudar por zero
						strQuantidade = String.valueOf(tfListQuantidades[i].getText()).replace('.', ','); 
				} else { strQuantidade = "0,0"; }
			
				try {	gs.callSetter(sub, strVariaveisQuantidades[i], NumberFormat.getInstance().parse(strQuantidade).doubleValue());
							} catch (ParseException e) {e.printStackTrace();};
							
							// Conferir se está vazio o campo
							if (! tfListConsumo[i].getText().isEmpty()) {
										// Conferir se há letras e mudar por zero
										strConsumo = String.valueOf(tfListConsumo[i].getText()).replace('.', ','); 
								} else { strConsumo = "0,0"; }
							
								try {	
										gs.callSetter(sub, strVariaveisConsumo[i], NumberFormat.getInstance().parse(strConsumo).doubleValue());
											} catch (ParseException e) {e.printStackTrace();};
											
											// Conferir se está vazio o campo
											if (! tfListFinVazoes[i].getText().isEmpty()) {
														// Conferir se há letras e mudar por zero
														strVazoes = String.valueOf(tfListFinVazoes[i].getText()).replace('.', ','); 
												} else { strVazoes = "0,0"; }
											
												try {
														gs.callSetter(sub, strVariaveisVazao[i], NumberFormat.getInstance().parse(strVazoes).doubleValue());
														} catch (ParseException e) {e.printStackTrace();};
							
						
		}
		
		// Setar vazao total, soma das vazoes das finalidades requeridas. //
		try {sub.setSubVazaoTotal(Double.parseDouble(lblCalTotal.getText())); } catch (Exception e) {sub.setSubVazaoTotal(0.0);};
		
		for (int i = 0; i< strVariaveisVazaoMes.length; i++) {
			
			String strVazoesMes;
			String strVazoesHora;
			String strPeriodos;
			
			// Conferir se está vazio o campo
			if (! tfVazoesLD[i].getText().isEmpty()) {
						// Conferir se há letras e mudar por zero
						strVazoesMes = String.valueOf(tfVazoesLD[i].getText()).replaceAll("[a-zA-Z]", "0");
						// Padronizar a forma de salvar, mudando virgula por ponto
						strVazoesMes = strVazoesMes.replace('.', ','); 
						
				} else { strVazoesMes = "0,0"; }
			
				try {
					gs.callSetter(sub, strVariaveisVazaoMes[i], NumberFormat.getInstance().parse(strVazoesMes).doubleValue() );
						} catch (ParseException e) {
							e.printStackTrace();
						}
			
						// Conferir se está vazio o campo
						if (! tfVazoesHD[i].getText().isEmpty()) {
									// Conferir se há letras e mudar por zero
									strVazoesHora = String.valueOf(tfVazoesHD[i].getText()).replaceAll("[a-zA-Z , .]", "0"); 
									/* 
									 * ainda nao e o melhor 04/02/19, mudar o a-z por zero da certo, 
									 * 		mas virgula e ponto seria melhor tirar tudo depois de virgula com substring
									 * 
									 * penso que nao precisa mais pois coloquei filtro no textfield para nao aceitar outros 
									 *  	caracteres diferente de numero
									 */
									
							} else { strVazoesHora = "0"; }
						
						try {
							gs.callSetter(sub, strVariaveisVazaoHora[i], NumberFormat.getInstance().parse(strVazoesHora).intValue() );
							} catch (ParseException e) {
								e.printStackTrace();
							}
						
							// Conferir se está vazio o campo
							if (! tfPeriodoDM[i].getText().isEmpty()) {
										// Conferir se há letras e mudar por zero
								strPeriodos = String.valueOf(tfPeriodoDM[i].getText()).replaceAll("[a-zA-Z , .]", "0"); 
										/* 
										 * ainda nao e o melhor 04/02/19, mudar o a-z por zero da certo, 
										 * 		mas virgula e ponto seria melhor tirar tudo depois de virgula com substring
										 * 
										 * penso que nao precisa mais pois coloquei filtro no textfield para nao aceitar outros 
										 *  	caracteres diferente de numero
										 */
										
								} else { strPeriodos = "0"; }
							
							try {
								gs.callSetter(sub, strVariaveisTempo[i], NumberFormat.getInstance().parse(strPeriodos).intValue() );
								} catch (ParseException e) {
									e.printStackTrace();
								}
						
			
		}

		sub.setInterID(this.sub.getInterID());
		System.out.println("subterranea id - metodo obter subterranea " + sub.getInterID());
		
		return sub;
	
	}
	
	public void setSubterranea (Subterranea sub) {
		
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
		
		// tabela de finalidades e consumo  //
		
		// Finalidades Sub Quan Cons e Vazao //
		entidades.GetterAndSetter gs  = new entidades.GetterAndSetter();
				
		System.out.println(gs.callGetter(sub, "subFinalidade1"));
		
		for (int i = 0; i< strVariaveisFinalidades.length; i++) {
			
			tfListFinalidades[i].setText(gs.callGetter(sub, strVariaveisFinalidades[i]));
			tfListSubfinalidades[i].setText(gs.callGetter(sub, strVariaveisSubfinaldades[i]));
			tfListQuantidades[i].setText(gs.callGetter(sub, strVariaveisQuantidades[i]));
			tfListConsumo[i].setText(gs.callGetter(sub, strVariaveisConsumo[i]));
			tfListFinVazoes[i].setText(gs.callGetter(sub, strVariaveisVazao[i]));
		}
		
		try {lblCalTotal.setText(String.valueOf(sub.getSubVazaoTotal()));} catch (Exception e) {lblCalTotal.setText("");};
		
		for (int i = 0; i< strVariaveisVazaoMes.length; i++) {
			
			tfVazoesLD[i].setText( String.valueOf(gs.callGetter(sub, strVariaveisVazaoMes[i]).replace('.', ',')));
			tfVazoesHD[i].setText(gs.callGetter(sub, strVariaveisVazaoHora[i]));
			tfPeriodoDM[i].setText(gs.callGetter(sub, strVariaveisTempo[i]));
			
		}
		
		this.sub = sub;	
	
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
		
		System.out.println("tabSubterranea inicializada");
		
		inicializarComponentes();
		
		//iniciarDadosFinalidade ();
		
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
	    		
	    		System.out.println(" bacia id" + baciaID);
	    		
            }
	    });
		
		cbUnidadeHidrografica.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		unidHidID = listaUHID [(int) new_value];
	    		
	    		System.out.println("unidade hidr selecionada " + unidHidID);
	    		
            }
	    });
		
		cbTipoPoco.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoPocoID = listaTipoPoco [(int) new_value];
	    		System.out.println(" tipo poço id" + tipoPocoID);
	    		
            }
	    });
		
		cbSubsistema.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			subSistemaID = listaSubsistema [(int) new_value];
	    		System.out.println("susbsistema ID tabSubterranea " + subSistemaID);
	    		
            }
	    });
		
		
		
		
		
		System.out.println("TabSubterranea inicializada!");
		
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
		
		Pane pFinalidade;
		Pane pVazoes;
		
		GridPane gpFinalidades = new GridPane();
		GridPane gpVazoes = new GridPane();
		
		TextField[] tfListFinalidades = new TextField[5];
		TextField[] tfListSubfinalidades = new TextField[5];
		TextField[] tfListQuantidades = new TextField[5];
		TextField[] tfListConsumo = new TextField[5];
		TextField[] tfListFinVazoes = new TextField[5];
		
		Button [] btnListCalcular = new Button[6];
		Label lblCalTotal = new Label();
		
		ChoiceBox<String>[] listCbFinalidade = new ChoiceBox[5];
		ChoiceBox<String>[] listCbSubfinalidades = new ChoiceBox[5];
		
		TextField[] tfVazoesLD = new TextField[12];
		TextField[] tfVazoesHD = new TextField[12]; //  
		TextField[] tfPeriodoDM = new TextField[12];
		Button [] btnListCalMeses = new Button[3];
		
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
				{910.0,180.0,61.0,0.0},
				{95.0,30.0,165.0,10.0},
				{140.0,30.0,260.0,10.0},
				{95.0,30.0,410.0,10.0},
				{140.0,30.0,505.0,10.0},
				{25.0,25.0,658.0,10.0},
				{160.0,30.0,34.0,45.0},
				{160.0,30.0,34.0,75.0},
				{60.0,30.0,204.0,45.0},
				{60.0,30.0,204.0,75.0},
				{150.0,30.0,274.0,45.0},
				{150.0,30.0,274.0,75.0},
				{150.0,30.0,434.0,45.0},
				{150.0,30.0,434.0,75.0},
				{150.0,30.0,594.0,45.0},
				{150.0,30.0,594.0,75.0},
				{120.0,30.0,754.0,45.0},
				{120.0,30.0,754.0,75.0},
				{120.0,30.0,172.0,110.0},
				{120.0,30.0,172.0,140.0},
				{125.0,30.0,302.0,110.0},
				{125.0,30.0,301.0,140.0},
				{120.0,30.0,436.0,110.0},
				{120.0,30.0,436.0,140.0},
				{130.0,30.0,565.0,110.0},
				{130.0,30.0,565.0,140.0},
	    	};
		    	
			    com = new Componentes();
			    com.popularTela(listaComponentes, prefSizeWHeLayXY, pSubterranea);
			    
		pFinalidade = new Pane();
		pFinalidade.setPrefSize(910, 150);
		pFinalidade.setLayoutX(60);
		pFinalidade.setLayoutY(190);
		
		pVazoes = new Pane();
		pVazoes.setPrefSize(910, 120);
		pVazoes.setLayoutX(60);
		pVazoes.setLayoutY(370);
		
		pSubterranea.getChildren().addAll(pFinalidade, pVazoes);
		
		pFinalidade.getChildren().add(gpFinalidades);
		pVazoes.getChildren().add(gpVazoes);
		
		 //Setting size for the pane  
		gpVazoes.setMaxSize(910, 190); 
		

		Label lbl;
		
		 String[] strLabelFinalidade = {
				 "Finalidade", " ","Subfinalidade", " ", "Quant (unid)", "Consumo(L/dia)", "TOTAL", " "
				 		};
		 
		 for (int i = 0; i<8; i++ ) {
			 gpFinalidades.add(lbl = new Label(strLabelFinalidade[i]), i, 0); // child, columnIndex, rowIndex
			 lbl.setAlignment( Pos.CENTER );
			 lbl.setMinSize(50, 20);
		 }
		 
		for (int i = 0; i<5; i++ ) {
			
			TextField tfFin = tfListFinalidades [i] = new TextField();
			TextField tfSub = tfListSubfinalidades [i] = new TextField();
			TextField tfQuant = tfListQuantidades [i] = new TextField();
			TextField tfCon = tfListConsumo [i] = new TextField();
			TextField tfVaz = tfListFinVazoes [i] = new TextField();
			
			Button btnCal = btnListCalcular[i] = new Button();
			
			btnCal.setPrefSize(25, 25);
			
			ChoiceBox<String> cbFin =  listCbFinalidade [i] = new ChoiceBox<String>();
			cbFin.setItems(olFinalidades);
			cbFin.setPrefSize(50, 20);
			
			ChoiceBox<String> cbSub =  listCbSubfinalidades [i] = new ChoiceBox<String>();
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
		Button btnCal6 = btnListCalcular[5] = new Button();
		btnCal6.setPrefSize(25, 25);
		gpFinalidades.add(btnCal6, 7, 6);
		
		// adicionar label com resultado total no gridpane e centralizar o texto
		lblCalTotal.setText("0.0");
		lblCalTotal.setMaxWidth(Double.MAX_VALUE);
		lblCalTotal.setAlignment(Pos.CENTER);
		gpFinalidades.add(lblCalTotal, 6, 6);
		
		// calcular o valor total das finalidades
		btnCal6.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	Double result = 0.0;
	        	
	        	for (int i = 0; i<5;i++) {
	        		if (! tfListFinVazoes[i].getText().isEmpty())
	        		result += Double.parseDouble(
		        					tfListFinVazoes[i].getText());
	        		
	        		
	        	}
	        	lblCalTotal.setText(String.valueOf(result));
	        	
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
				
	
	}
	
	public void iniciarDadosFinalidade () {
		
		
		for (int i = 0; i<5; i++ ) {
			
			TextField tfFin = tfListFinalidades [i] = new TextField();
			TextField tfSub = tfListSubfinalidades [i] = new TextField();
			TextField tfQuant = tfListQuantidades [i] = new TextField();
			TextField tfCon = tfListConsumo [i] = new TextField();
			TextField tfVaz = tfListFinVazoes [i] = new TextField();
			
			Button btnCal = btnListCalcular[i] = new Button();
			
			btnCal.setPrefSize(25, 25);
			
			ChoiceBox<String> cbFin =  listCbFinalidade [i] = new ChoiceBox<String>();
			cbFin.setItems(olFinalidades);
			cbFin.setPrefSize(50, 20);
			
			ChoiceBox<String> cbSub =  listCbSubfinalidades [i] = new ChoiceBox<String>();
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
		
		for (int i = 0; i<3; i++ ) {
			
			Button btnCalMes = btnListCalMeses[i] = new Button();
			btnCalMes.setPrefSize(25, 25);
			gpVazoes.add(btnCalMes, 13, i+1);
			
		} // fim loop for
		
		// botao para calcular o valor total de TODAS as finalidades
		Button btnCal6 = btnListCalcular[5] = new Button();
		btnCal6.setPrefSize(25, 25);
		gpFinalidades.add(btnCal6, 7, 6);
		
		// adicionar label com resultado total no gridpane e centralizar o texto
		lblCalTotal.setText("0.0");
		lblCalTotal.setMaxWidth(Double.MAX_VALUE);
		lblCalTotal.setAlignment(Pos.CENTER);
		gpFinalidades.add(lblCalTotal, 6, 6);
		
		// calcular o valor total das finalidades
		btnCal6.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	Double result = 0.0;
	        	
	        	for (int i = 0; i<5;i++) {
	        		if (! tfListFinVazoes[i].getText().isEmpty())
	        		result += Double.parseDouble(
		        					tfListFinVazoes[i].getText());
	        		
	        		
	        	}
	        	lblCalTotal.setText(String.valueOf(result));
	        	
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
		
	} // fim metodo iniciarDadosFinalidade

	public void capturarCoordenadas () {
		
		String lat = ControladorPrincipal.capturarGoogleMaps().getLat();
		String lon = ControladorPrincipal.capturarGoogleMaps().getLon();
		
		tfLatitude.setText(lat);
		tfLongitude.setText(lon);
		
		System.out.println(lat + "," + lon);
		
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
				
				System.out.println("nome da bacia " + b.getBaciaNome() );
			}
			
		} // fim loop bacias hidrograficas
		
		for (UnidadeHidrografica u : listUnidades) {
			
			
			if (p.intersects(u.getShape())) {
				
			cbUnidadeHidrografica.setValue(String.valueOf(u.getUhCodigo()));
				
			System.out.println("nome da uh " + u.getUhNome() );
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

