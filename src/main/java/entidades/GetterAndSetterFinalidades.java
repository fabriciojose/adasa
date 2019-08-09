package entidades;

import java.text.NumberFormat;
import java.text.ParseException;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GetterAndSetterFinalidades {
	
	String [] strVariaveisFinalidades;
	String [] strVariaveisSubfinaldades;
	String [] strVariaveisQuantidades;
	String [] strVariaveisConsumo;
	String [] strVariaveisVazao;

	String [] strVariaveisVazaoMes;
	String [] strVariaveisVazaoHora;
	String [] strVariaveisTempo;
	
	String vazaoTotal;
	
	String frVazaoTotal = "frVazaoTotal";
	
	String faVazaoTotal = "faVazaoTotal";
	
	// variaveis de finalidade e reflexao //
	String strVariaveisFinalidadesRequeridas [] = {"frFinalidade1", "frFinalidade2", "frFinalidade3", "frFinalidade4", "frFinalidade5"};
	String strVariaveisSubfinaldadesRequeridas  [] = {"frSubfinalidade1", "frSubfinalidade2", "frSubfinalidade3", "frSubfinalidade4", "frSubfinalidade5"};
	String strVariaveisQuantidadesRequeridas  [] = {"frQuantidade1", "frQuantidade2", "frQuantidade3", "frQuantidade4", "frQuantidade5"};
	String strVariaveisConsumoRequeridas  [] = {"frConsumo1", "frConsumo2", "frConsumo3", "frConsumo4", "frConsumo5"};
	String strVariaveisVazaoRequeridas  [] = {"frVazao1", "frVazao2", "frVazao3", "frVazao4", "frVazao5"};

	// variaveis de vazao mensal e reflexao //
	String strVariaveisVazaoMesRequeridas  [] = {

			"frQDiaJan","frQDiaFev","frQDiaMar","frQDiaAbr","frQDiaMai","frQDiaJun",
			"frQDiaJul","frQDiaAgo","frQDiaSet","frQDiaOut","frQDiaNov","frQDiaDez",	

	};

	String strVariaveisVazaoHoraRequeridas  [] = {
			"frQHoraJan","frQHoraFev","frQHoraMar","frQHoraAbr","frQHoraMai","frQHoraJun",
			"frQHoraJul","frQHoraAgo","frQHoraSet","frQHoraOut","frQHoraNov","frQHoraDez",

	};

	String strVariaveisTempoRequeridas   [] = {

			"frTempoCapJan","frTempoCapFev","frTempoCapMar","frTempoCapAbr","frTempoCapMai","frTempoCapJun",
			"frTempoCapJul","frTempoCapAgo","frTempoCapSet","frTempoCapOut","frTempoCapNov","frTempoCapDez",

	};

	// variaveis de finalidade e reflexao //
	String strVariaveisFinalidadesAutorizadas [] = {"faFinalidade1", "faFinalidade2", "faFinalidade3", "faFinalidade4", "faFinalidade5"};
	String strVariaveisSubfinaldadesAutorizadas  [] = {"faSubfinalidade1", "faSubfinalidade2", "faSubfinalidade3", "faSubfinalidade4", "faSubfinalidade5"};
	String strVariaveisQuantidadesAutorizadas  [] = {"faQuantidade1", "faQuantidade2", "faQuantidade3", "faQuantidade4", "faQuantidade5"};
	String strVariaveisConsumoAutorizadas  [] = {"faConsumo1", "faConsumo2", "faConsumo3", "faConsumo4", "faConsumo5"};
	String strVariaveisVazaoAutorizadas  [] = {"faVazao1", "faVazao2", "faVazao3", "faVazao4", "faVazao5"};

	// variaveis de vazao mensal e reflexao //
	String strVariaveisVazaoMesAutorizadas  [] = {

			"faQDiaJan","faQDiaFev","faQDiaMar","faQDiaAbr","faQDiaMai","faQDiaJun",
			"faQDiaJul","faQDiaAgo","faQDiaSet","faQDiaOut","faQDiaNov","faQDiaDez",	

	};

	String strVariaveisVazaoHoraAutorizadas  [] = {
			"faQHoraJan","faQHoraFev","faQHoraMar","faQHoraAbr","faQHoraMai","faQHoraJun",
			"faQHoraJul","faQHoraAgo","faQHoraSet","faQHoraOut","faQHoraNov","faQHoraDez",

	};

	String strVariaveisTempoAutorizadas   [] = {

			"faTempoCapJan","faTempoCapFev","faTempoCapMar","faTempoCapAbr","faTempoCapMai","faTempoCapJun",
			"faTempoCapJul","faTempoCapAgo","faTempoCapSet","faTempoCapOut","faTempoCapNov","faTempoCapDez",

	};
	
	public void inicializarVariaveisFinalidadesRequeridas () {
		
		 strVariaveisFinalidades = this.strVariaveisFinalidadesRequeridas;
		 strVariaveisSubfinaldades = this.strVariaveisSubfinaldadesRequeridas;
		 strVariaveisQuantidades = this.strVariaveisQuantidadesRequeridas;
		 strVariaveisConsumo = this.strVariaveisConsumoRequeridas;
		 strVariaveisVazao = this.strVariaveisVazaoRequeridas;

		 strVariaveisVazaoMes = this.strVariaveisVazaoMesRequeridas;
		 strVariaveisVazaoHora = this.strVariaveisVazaoHoraRequeridas;
		 strVariaveisTempo = this.strVariaveisTempoRequeridas;
		 vazaoTotal = this.frVazaoTotal;
		 
		
	}
	
	public void inicializarVariaveisFinalidadesAutorizadas () {
		
		 strVariaveisFinalidades = this.strVariaveisFinalidadesAutorizadas;
		 strVariaveisSubfinaldades = this.strVariaveisSubfinaldadesAutorizadas;
		 strVariaveisQuantidades = this.strVariaveisQuantidadesAutorizadas;
		 strVariaveisConsumo = this.strVariaveisConsumoAutorizadas;
		 strVariaveisVazao = this.strVariaveisVazaoAutorizadas;

		 strVariaveisVazaoMes = this.strVariaveisVazaoMesAutorizadas;
		 strVariaveisVazaoHora = this.strVariaveisVazaoHoraAutorizadas;
		 strVariaveisTempo = this.strVariaveisTempoAutorizadas;
		 vazaoTotal = this.faVazaoTotal;
		 
		
	}
	
	public void getFinalidade (
			Finalidade finalidade, 
			TextField [] tfListFinalidades, TextField [] tfListSubfinalidades, TextField [] tfListQuantidades, TextField [] tfListConsumo, 
			TextField [] tfListFinVazoes,
			Label lblCalTotal,
			TextField [] tfVazoesLD, TextField [] tfVazoesHD, TextField []  tfPeriodoDM
			
			) {

		// tabela de finalidades e consumo  //

		// Finalidades Sub Quan Cons e Vazao //
		entidades.GetterAndSetter gs  = new entidades.GetterAndSetter();

		//System.out.println(gs.callGetter(sub, "subFinalidade1"));

		for (int i = 0; i< strVariaveisFinalidades.length; i++) {

			tfListFinalidades[i].setText(gs.callGetter(finalidade, strVariaveisFinalidades[i]));
			tfListSubfinalidades[i].setText(gs.callGetter(finalidade, strVariaveisSubfinaldades[i]));
			tfListQuantidades[i].setText(gs.callGetter(finalidade, strVariaveisQuantidades[i]));
			tfListConsumo[i].setText(gs.callGetter(finalidade, strVariaveisConsumo[i]));
			tfListFinVazoes[i].setText(gs.callGetter(finalidade, strVariaveisVazao[i]));
		}

		//try {lblCalTotal.setText(String.valueOf(finalidade.getFrVazaoTotal()));} catch (Exception e) {lblCalTotal.setText("");};
		
		try {lblCalTotal.setText(String.valueOf(gs.callGetter(finalidade, vazaoTotal)));} catch (Exception e) {lblCalTotal.setText("");};
		

		for (int i = 0; i< strVariaveisVazaoMes.length; i++) {

			tfVazoesLD[i].setText( String.valueOf(gs.callGetter(finalidade, strVariaveisVazaoMes[i]).replace('.', ',')));
			tfVazoesHD[i].setText(gs.callGetter(finalidade, strVariaveisVazaoHora[i]));
			tfPeriodoDM[i].setText(gs.callGetter(finalidade, strVariaveisTempo[i]));

		}


	}
	
	public void setFinalidade (
			Finalidade finalidade, 
			TextField [] tfListFinalidades, TextField [] tfListSubfinalidades, TextField [] tfListQuantidades, TextField [] tfListConsumo, 
			TextField [] tfListFinVazoes,
			Label lblCalTotal,
			TextField [] tfVazoesLD, TextField [] tfVazoesHD, TextField []  tfPeriodoDM
			) {

		// Finalidades Sub Quan Cons e Vazao //
		GetterAndSetter gs  = new GetterAndSetter();

		for (int i = 0; i< strVariaveisFinalidades.length; i++) {

			String strQuantidade;
			String strConsumo;
			String strVazoes;

			gs.callSetter(finalidade, strVariaveisFinalidades[i], tfListFinalidades[i].getText());
			gs.callSetter(finalidade, strVariaveisSubfinaldades[i], tfListSubfinalidades[i].getText());

			// Conferir se está vazio o campo
			if (! tfListQuantidades[i].getText().isEmpty()) {
				// Conferir se há letras e mudar por zero
				strQuantidade = String.valueOf(tfListQuantidades[i].getText()).replace('.', ','); 
			} else { strQuantidade = "0,0"; }

			try {	gs.callSetter(finalidade, strVariaveisQuantidades[i], NumberFormat.getInstance().parse(strQuantidade).doubleValue());
			} catch (ParseException e) {e.printStackTrace();};

			// Conferir se está vazio o campo
			if (! tfListConsumo[i].getText().isEmpty()) {
				// Conferir se há letras e mudar por zero
				strConsumo = String.valueOf(tfListConsumo[i].getText()).replace('.', ','); 
			} else { strConsumo = "0,0"; }

			try {	
				gs.callSetter(finalidade, strVariaveisConsumo[i], NumberFormat.getInstance().parse(strConsumo).doubleValue());
			} catch (ParseException e) {e.printStackTrace();};

			// Conferir se está vazio o campo
			if (! tfListFinVazoes[i].getText().isEmpty()) {
				// Conferir se há letras e mudar por zero
				strVazoes = String.valueOf(tfListFinVazoes[i].getText()).replace('.', ','); 
			} else { strVazoes = "0,0"; }

			try {
				gs.callSetter(finalidade, strVariaveisVazao[i], NumberFormat.getInstance().parse(strVazoes).doubleValue());
			} catch (ParseException e) {e.printStackTrace();};


		} // fim loop for variaveis finalidades, subfinalidades, quantidades, consumo

		// Setar vazao total, soma das vazoes das finalidades requeridas. //
		
		try {	
			gs.callSetter(finalidade, vazaoTotal, Double.parseDouble(lblCalTotal.getText()));
		} catch (Exception e) {e.printStackTrace();};
		
		//try {finalidade.setFrVazaoTotal(Double.parseDouble(lblCalTotal.getText())); } catch (Exception e) {finalidade.setFrVazaoTotal(0.0);};

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
				gs.callSetter(finalidade, strVariaveisVazaoMes[i], NumberFormat.getInstance().parse(strVazoesMes).doubleValue() );
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
				gs.callSetter(finalidade, strVariaveisVazaoHora[i], NumberFormat.getInstance().parse(strVazoesHora).intValue() );
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
				gs.callSetter(finalidade, strVariaveisTempo[i], NumberFormat.getInstance().parse(strPeriodos).intValue() );
			} catch (ParseException e) {
				e.printStackTrace();
			}


		} // fim loop for variaveis vazao mes


	}

	String strVariaveisTabelas  [] = {

			"baciaNome"	

	};
	
	

}
