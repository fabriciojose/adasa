package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entidades.FormaCaptacao;
import entidades.GetterAndSetter;
import entidades.LocalCaptacao;
import entidades.MetodoIrrigacao;
import entidades.Superficial;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import principal.Alerta;

public class TabSuperficialController implements Initializable{
	
	Superficial sup = new Superficial();
	
	@FXML TextField tfMarcaBomba = new TextField();
	@FXML TextField tfPotenciaBomba = new TextField();
	@FXML TextField tfCorpoHidrico = new TextField();
	@FXML TextField tfAreaIrrigada = new  TextField();
	@FXML TextField tfAreaContribuicao = new TextField();
	@FXML TextField tfAreaPropriedade = new TextField();
	
	@FXML DatePicker dpDataOperacao;
	
	@FXML Pane tabSuperficial;
	
	@FXML
	ChoiceBox<String> cbLocalCaptacao = new ChoiceBox<String>();
		ObservableList<String> olLocalCaptacao = FXCollections
			.observableArrayList(
					
					"Nascente",
					"Rio",
					"Reservatório",
					"Canal",
					"Lago Natural"
					
					); 
		@FXML
		ChoiceBox<String> cbFormaCaptacao = new ChoiceBox<String>();
			ObservableList<String> olFormaCaptacao = FXCollections
				.observableArrayList(
						"Bombeamento" , 
						"Gravidade"
						); 
			
			@FXML
			ChoiceBox<String> cbMetodoIrrigacao = new ChoiceBox<String>();
				ObservableList<String> olMetodoIrrigacao = FXCollections
					.observableArrayList(
							"Aspersão"	,
							"Gotejamento",
							"Pivô",
							"Manual",
							"Aspersão/gotejamento"	

							); 
				
				@FXML
				ChoiceBox<String> cbBarramento = new ChoiceBox<String>();
					ObservableList<String> olBarramento = FXCollections
						.observableArrayList(
								"Sim",
								"Não"
								); 
		
					@FXML
					ChoiceBox<String> cbCaesb = new ChoiceBox<String>();
						ObservableList<String> olCaesb = FXCollections
							.observableArrayList(
									"Sim" , 
									"Não"
									); 
		
					
					
	int localCaptacaoID = 1;
	String strLocalCaptacao = "Nascente";
	final int [] listaLocalCaptacao = new int [] { 1,2,3,4,5 };
	
	int formaCaptacaoID = 1;
	String strFormaCaptacao = "Bombeamento";
	final int [] listaFormaCaptacao = new int [] { 1,2};
	
	int metodoIrrigacaoID = 1;
	String strMetodoIrrigacao = "Aspersão";
	final int [] listaMetodoIrrigacao = new int [] { 1,2,3,4,5 };
	
				
	@FXML ImageView	iVewSuper = new ImageView();
	//Image imgSuper = new Image(TabSuperficialController.class.getResourceAsStream("/images/superficial.png"));
	
	
	
	// variaveis de finalidade e reflexao //
	String strVariaveisFinalidades [] = {"supFinalidade1", "supFinalidade2", "supFinalidade3", "supFinalidade4", "supFinalidade5"};
	String strVariaveisSubfinaldades [] = {"supSubfinalidade1", "supSubfinalidade2", "supSubfinalidade3", "supSubfinalidade4", "supSubfinalidade5"};
	String strVariaveisQuantidades [] = {"supQuantidade1", "supQuantidade2", "supQuantidade3", "supQuantidade4", "supQuantidade5"};
	String strVariaveisConsumo [] = {"supConsumo1", "supConsumo2", "supConsumo3", "supConsumo4", "supConsumo5"};
	String strVariaveisVazao [] = {"supVazao1", "supVazao2", "supVazao3", "supVazao4", "supVazao5"};
	
	// variaveis de vazao mensal e reflexao //
				String strVariaveisVazaoMes [] = {
						
						"supQDiaJan","supQDiaFev","supQDiaMar","supQDiaAbr","supQDiaMai","supQDiaJun",
						"supQDiaJul","supQDiaAgo","supQDiaSet","supQDiaOut","supQDiaNov","supQDiaDez",	
						
				};
				
				String strVariaveisVazaoHora [] = {
						"supQHoraJan","supQHoraFev","supQHoraMar","supQHoraAbr","supQHoraMai","supQHoraJun",
						"supQHoraJul","supQHoraAgo","supQHoraSet","supQHoraOut","supQHoraNov","supQHoraDez",

				};
				
				String strVariaveisTempo  [] = {
					
						"supTempoCapJan","supTempoCapFev","supTempoCapMar","supTempoCapAbr","supTempoCapMai","supTempoCapJun",
						"supTempoCapJul","supTempoCapAgo","supTempoCapSet","supTempoCapOut","supTempoCapNov","supTempoCapDez",

				};
				
				
	
	public Superficial obterSuperficial () {
		
		
		System.out.println("metodo obter superficial id " + sup.getInterID());
		
		sup.setInterID(sup.getInterID());
		
		LocalCaptacao lc = new LocalCaptacao();
		lc.setLocalCatacaoID(localCaptacaoID);
		
		FormaCaptacao fc = new FormaCaptacao();
		fc.setFormaCaptacaoID(formaCaptacaoID);
		
		MetodoIrrigacao mi = new MetodoIrrigacao();
		mi.setMetodoIrrigacaoID(metodoIrrigacaoID);
		
		sup.setSupLocalCaptacaoFK(lc);		// sup_Local; //-- () canal () rio () reservatório () lago natural () nascente
		sup.setSupFormaCaptacaoFK(fc); // bombeamento gravidade 
		sup.setSupMetodoIrrigacaoFK(mi); // Aspersão Gotejamento Pivô Manual Aspersão/gotejamento
		
		sup.setSupBarramento(cbBarramento.getValue());
		
		if (dpDataOperacao.getValue() == null) {
			
			sup.setSupDataOperacao(null);}
		else {
			sup.setSupDataOperacao(Date.valueOf(dpDataOperacao.getValue()));
			
			}
		
		sup.setSupPotenciaBomba(tfPotenciaBomba.getText()); // potência da bomba
		sup.setSupMarcaBomba(tfMarcaBomba.getText()); // marca da bomba
		
		sup.setSupCorpoHidrico(tfCorpoHidrico.getText());
		sup.setSupAreaIrrigada(tfAreaIrrigada.getText());
		sup.setSupAreaContribuicao(tfAreaContribuicao.getText());
		sup.setSupAreaPropriedade(tfAreaPropriedade.getText());
		
		sup.setSupCaesb(cbCaesb.getValue());
		
		// Finalidades Sub Quan Cons e Vazao //
				GetterAndSetter gs  = new GetterAndSetter();
				
				for (int i = 0; i< strVariaveisFinalidades.length; i++) {
					
					String strQuantidade;
					String strConsumo;
					String strVazoes;
					
					gs.callSetter(sup, strVariaveisFinalidades[i], tfListFinalidades[i].getText());
					gs.callSetter(sup, strVariaveisSubfinaldades[i], tfListSubfinalidades[i].getText());
					
					// Conferir se está vazio o campo
					if (! tfListQuantidades[i].getText().isEmpty()) {
								// Conferir se há letras e mudar por zero
								strQuantidade = String.valueOf(tfListQuantidades[i].getText()).replace('.', ','); 
						} else { strQuantidade = "0,0"; }
					
						try {	gs.callSetter(sup, strVariaveisQuantidades[i], NumberFormat.getInstance().parse(strQuantidade).doubleValue());
									} catch (ParseException e) {e.printStackTrace();};
									
									// Conferir se está vazio o campo
									if (! tfListConsumo[i].getText().isEmpty()) {
												// Conferir se há letras e mudar por zero
												strConsumo = String.valueOf(tfListConsumo[i].getText()).replace('.', ','); 
										} else { strConsumo = "0,0"; }
									
										try {	
												gs.callSetter(sup, strVariaveisConsumo[i], NumberFormat.getInstance().parse(strConsumo).doubleValue());
													} catch (ParseException e) {e.printStackTrace();};
													
													// Conferir se está vazio o campo
													if (! tfListFinVazoes[i].getText().isEmpty()) {
																// Conferir se há letras e mudar por zero
																strVazoes = String.valueOf(tfListFinVazoes[i].getText()).replace('.', ','); 
														} else { strVazoes = "0,0"; }
													
														try {
																gs.callSetter(sup, strVariaveisVazao[i], NumberFormat.getInstance().parse(strVazoes).doubleValue());
																} catch (ParseException e) {e.printStackTrace();};
									
																
				} // fim for finalidades
				
				// Setar vazao total, soma das vazoes das finalidades requeridas. //
				try {sup.setSupVazaoTotal(Double.parseDouble(lblCalTotal.getText())); } catch (Exception e) {sup.setSupVazaoTotal(0.0);};
				
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
							gs.callSetter(sup, strVariaveisVazaoMes[i], NumberFormat.getInstance().parse(strVazoesMes).doubleValue() );
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
									gs.callSetter(sup, strVariaveisVazaoHora[i], NumberFormat.getInstance().parse(strVazoesHora).intValue() );
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
										gs.callSetter(sup, strVariaveisTempo[i], NumberFormat.getInstance().parse(strPeriodos).intValue() );
										} catch (ParseException e) {
											e.printStackTrace();
										}
								
				} // fim for vazao mes a mes
		
		
		
	return sup;
	
	};
	
	public void imprimirSuperficial (Superficial sup) {
		
		System.out.println("metodo imprimirSuperficial - superficial id " + sup.getInterID());
		
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
		
		// tabela de finalidades e consumo  //
		
			// Finalidades Sub Quan Cons e Vazao //
			GetterAndSetter gs  = new GetterAndSetter();
			
			for (int i = 0; i< strVariaveisFinalidades.length; i++) {
				
				tfListFinalidades[i].setText(gs.callGetter(sup, strVariaveisFinalidades[i]));
				tfListSubfinalidades[i].setText(gs.callGetter(sup, strVariaveisSubfinaldades[i]));
				tfListQuantidades[i].setText(gs.callGetter(sup, strVariaveisQuantidades[i]));
				tfListConsumo[i].setText(gs.callGetter(sup, strVariaveisConsumo[i]));
				tfListFinVazoes[i].setText(gs.callGetter(sup, strVariaveisVazao[i]));
			}
			
			try {lblCalTotal.setText(String.valueOf(sup.getSupVazaoTotal()));} catch (Exception e) {lblCalTotal.setText("");};
			
			for (int i = 0; i< strVariaveisVazaoMes.length; i++) {
				
				tfVazoesLD[i].setText( String.valueOf(gs.callGetter(sup, strVariaveisVazaoMes[i]).replace('.', ',')));
				tfVazoesHD[i].setText(gs.callGetter(sup, strVariaveisVazaoHora[i]));
				tfPeriodoDM[i].setText(gs.callGetter(sup, strVariaveisTempo[i]));
				
			}
			
			this.sup = sup;
		
	}
	
	
	@FXML GridPane gpFinalidades;
	@FXML GridPane gpVazoes;
	
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		System.out.println("initialize superficial iniciado!");
		
		iniciarDadosFinalidade ();
		
		cbLocalCaptacao.setItems(olLocalCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
		cbMetodoIrrigacao.setItems(olMetodoIrrigacao);
		cbBarramento.setItems(olBarramento);
		
		cbCaesb.setItems(olCaesb);
		
		cbLocalCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		localCaptacaoID = listaLocalCaptacao [(int) new_value];
	    		
	    		System.out.println("+++ local captacao id " + localCaptacaoID);
	    		
            }
	    });

		cbFormaCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		formaCaptacaoID = listaFormaCaptacao [(int) new_value];
	    		
	    		System.out.println("---------------------------- forma captacao id " + formaCaptacaoID);
	    		
            }
	    });
		
		cbMetodoIrrigacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		metodoIrrigacaoID = listaMetodoIrrigacao [(int) new_value];
	    		
	    		System.out.println("/////////////////////////////////////////////// metodo irrigacao id " + metodoIrrigacaoID);
	    		
            }
	    });
		
		//iVewSuper.setImage(imgSuper);
		
		
		/*
		dpDataOperacao.setConverter(new StringConverter<LocalDate>() {
			
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
		
		tfArea.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfArea.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfArea.setText(tfArea.getText().substring(0, 5));
                    }
                }
            }
        });
        */
		
		
	} // fim initialize

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

}
