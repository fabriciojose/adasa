package principal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Endereco;
import entidades.Finalidade;
import entidades.GetterAndSetter;
import entidades.Interferencia;
import entidades.Subterranea;
import entidades.Usuario;

public class MalaDiretaAnexoParecer {
	
	

	/*
	 * tags anexo
	 * 
	 * <us_nome_tag></us_nome_tag>
	 * <us_cpfcnpj_tag></us_cpfcnpj_tag>
	 * 
	 * <end_log_tag></end_log_tag>
	 * 
	 * <finalidades_tag></finalidades_tag>
	 * 
	 * <inter_tipo_poco_tag></inter_tipo_poco_tag>
	 * <inter_lat_tag></inter_lat_tag>,<inter_lon_tag></inter_lon_tag></li>
	 * 
	 * 
	 * 	<inter_tipo_poco_tag></inter_tipo_poco_tag>
	  	<inter_prof_tag></inter_prof_tag>
		<inter_nivel_est_tag></inter_nivel_est_tag>
		<inter_niv_din_tag></inter_niv_din_tag>
		<inter_vazao_tag></inter_vazao_tag>
		
		tabelas
		
		<div align="justify"><inter_dados_basicos_tag></inter_dados_basicos_tag></div>
	 * 
	 */

	List<Object[][]> listMalaDireta = new ArrayList<>();
	String strAnexoParecer;
	String strTabela1, strTabela2;
	
	List<String> listVariaveisFinalidadesAutorizadas = Arrays.asList("faFinalidade1", "faFinalidade2", "faFinalidade3", "faFinalidade4", "faFinalidade5");
	
	GetterAndSetter gs  = new GetterAndSetter();
	
	
	String q_litros_hora_tag [] = {
			
			"q_litros_hora_jan_tag",
			"q_litros_hora_fev_tag",
			"q_litros_hora_mar_tag",
			"q_litros_hora_abr_tag",
			"q_litros_hora_mai_tag",
			"q_litros_hora_jun_tag",
			"q_litros_hora_jul_tag",
			"q_litros_hora_ago_tag",
			"q_litros_hora_set_tag",
			"q_litros_hora_out_tag",
			"q_litros_hora_nov_tag",
			"q_litros_hora_dez_tag"
			
	};
	
	String q_metros_hora_tag [] = {
			
			"q_metros_hora_jan_tag",
			"q_metros_hora_fev_tag",
			"q_metros_hora_mar_tag",
			"q_metros_hora_abr_tag",
			"q_metros_hora_mai_tag",
			"q_metros_hora_jun_tag",
			"q_metros_hora_jul_tag",
			"q_metros_hora_ago_tag",
			"q_metros_hora_set_tag",
			"q_metros_hora_out_tag",
			"q_metros_hora_nov_tag",
			"q_metros_hora_dez_tag"
};
	
	
	String t_dias_mes_tag [] = {
			
			"t_dias_mes_jan_tag",
			"t_dias_mes_fev_tag",
			"t_dias_mes_mar_tag",
			"t_dias_mes_abr_tag",
			"t_dias_mes_mai_tag",
			"t_dias_mes_jun_tag",
			"t_dias_mes_jul_tag",
			"t_dias_mes_ago_tag",
			"t_dias_mes_set_tag",
			"t_dias_mes_out_tag",
			"t_dias_mes_nov_tag",
			"t_dias_mes_dez_tag",
			
	};
	
	String q_metros_mes_tag [] = {
			
		"q_metros_mes_jan_tag",
		"q_metros_mes_fev_tag",
		"q_metros_mes_mar_tag",
		"q_metros_mes_abr_tag",
		"q_metros_mes_mai_tag",
		"q_metros_mes_jun_tag",
		"q_metros_mes_jul_tag",
		"q_metros_mes_ago_tag",
		"q_metros_mes_set_tag",
		"q_metros_mes_out_tag",
		"q_metros_mes_nov_tag",
		"q_metros_mes_dez_tag",
	
};
	
	
	String variaveis_litros_dia [] = {
	
				"faQDiaJan","faQDiaFev","faQDiaMar","faQDiaAbr","faQDiaMai","faQDiaJun",
				"faQDiaJul","faQDiaAgo","faQDiaSet","faQDiaOut","faQDiaNov","faQDiaDez"	

	};
	
	String t_horas_dia_tag [] = {
	
			"t_horas_dia_jan_tag",
			"t_horas_dia_fev_tag",
			"t_horas_dia_mar_tag",
			"t_horas_dia_abr_tag",
			"t_horas_dia_mai_tag",
			"t_horas_dia_jun_tag",
			"t_horas_dia_jul_tag",
			"t_horas_dia_ago_tag",
			"t_horas_dia_set_tag",
			"t_horas_dia_out_tag",
			"t_horas_dia_nov_tag",
			"t_horas_dia_dez_tag"

	};
	
	
	// variaveis de vazao mensal e reflexao //
	List<String> listVariaveisVazaoMesAutorizadas = Arrays.asList(

			"faQDiaJan","faQDiaFev","faQDiaMar","faQDiaAbr","faQDiaMai","faQDiaJun",
			"faQDiaJul","faQDiaAgo","faQDiaSet","faQDiaOut","faQDiaNov","faQDiaDez"	

			);

	List<String> listVariaveisVazaoHoraAutorizadas = Arrays.asList(

			"faQHoraJan","faQHoraFev","faQHoraMar","faQHoraAbr","faQHoraMai","faQHoraJun",
			"faQHoraJul","faQHoraAgo","faQHoraSet","faQHoraOut","faQHoraNov","faQHoraDez"

			);

	List<String> listVariaveisTempoAutorizadas = Arrays.asList(

			"faTempoCapJan","faTempoCapFev","faTempoCapMar","faTempoCapAbr","faTempoCapMai","faTempoCapJun",
			"faTempoCapJul","faTempoCapAgo","faTempoCapSet","faTempoCapOut","faTempoCapNov","faTempoCapDez"

			);

	
	
	public MalaDiretaAnexoParecer (List<Object[][]> listMalaDireta, String strAnexoParecer, String strTabela1, String strTabela2) {
		
		this.listMalaDireta = listMalaDireta;
		this.strAnexoParecer = strAnexoParecer;
		this.strTabela1 = strTabela1;
		this.strTabela2 = strTabela2;
	}
	
	// formatar 1000.50 para 1.000,50 e retirar zeros irrelevantes como ,00 - 15.00 fica 15
	DecimalFormat df = new DecimalFormat("#,##0.00"); 
	
	// trazer um numero que informe qual é o usuario que será anexado pela interferencia
	
	// trazer duas tabelas para o anexo
	
	/**
	 * 
	 * @param in = interferencia selecionada no combobox na tabNavegador
	 * @return
	 */
	public String criarAnexoParecer (int in) {
		
		System.out.println("numero combo box "+ in);
		
		Document docHtml= null;
		Document docHtmlTabelasLimitesOutorgados = null;
		Document docHTMLTabelaPontoCaptacao = null;

		docHtml = Jsoup.parse(strAnexoParecer, "UTF-8").clone();
		
		//<us_nome_tag></us_nome_tag>
		 //* <us_cpfcnpj_tag></us_cpfcnpj_tag>

		try { docHtml.select("us_nome_tag").prepend(((Usuario)listMalaDireta.get(in)[0][1]).getUsNome());} catch (Exception e) {docHtml.select("us_nome_tag").prepend("");};
			
		try { docHtml.select("us_cpfcnpj_tag").prepend(((Usuario)listMalaDireta.get(in)[0][1]).getUsCPFCNPJ());} catch (Exception e) {docHtml.select("us_cpfcnpj_tag").prepend("");};
		
		// <end_log_tag></end_log_tag>
			
		try { docHtml.select("end_log_tag").prepend(((Endereco)listMalaDireta.get(in)[0][3]).getEndLogradouro());} catch (Exception e) {docHtml.select("end_log_tag").prepend("");};
		
		//  * <finalidades_tag></finalidades_tag>
		
		for (Finalidade f : ((Interferencia) listMalaDireta.get(in)[0][2]).getFinalidades()) {
        	
        	if ( f.getClass().getName() == "entidades.FinalidadeAutorizada") {
				
        		for (int i = 0; i<5; i++) {
				
        			
        			try { docHtml.select("finalidades_tag").prepend(gs.callGetter(f, listVariaveisFinalidadesAutorizadas.get(i)) + ", ");} 
        			catch (Exception e) {docHtml.select("finalidades_tag").prepend("");};
					
				}
        	}
		}
		
		
		try { docHtml.select("inter_lat_tag").prepend(
				((Subterranea)listMalaDireta.get(in)[0][2]).getInterDDLatitude()
				+ "," +
				((Subterranea)listMalaDireta.get(in)[0][2]).getInterDDLongitude())
			;
		
		} catch (Exception e) {docHtml.select("us_cpfcnpj_tag").prepend("");};
		
		try { docHtml.select("inter_tipo_poco_tag").prepend(((Subterranea)listMalaDireta.get(in)[0][2]).getSubTipoPocoFK().getTipoPocoDescricao());} catch (Exception e) {docHtml.select("inter_tipo_poco_tag").prepend("");};
		try { docHtml.select("inter_prof_tag").prepend(((Subterranea)listMalaDireta.get(in)[0][2]).getSubProfundidade());} catch (Exception e) {docHtml.select("inter_prof_tag").prepend("");};
		try { docHtml.select("inter_nivel_est_tag").prepend(((Subterranea)listMalaDireta.get(in)[0][2]).getSubEstatico());} catch (Exception e) {docHtml.select("inter_nivel_est_tag").prepend("");};
		try { docHtml.select("inter_niv_din_tag").prepend(((Subterranea)listMalaDireta.get(in)[0][2]).getSubDinamico());} catch (Exception e) {docHtml.select("inter_niv_din_tag").prepend("");};
		try { docHtml.select("inter_vazao_tag").prepend(df.format(((Subterranea)listMalaDireta.get(in)[0][2]).getSubVazaoPoco()).replaceAll(",00", ""));} catch (Exception e) {docHtml.select("inter_vazao_tag").prepend("");};
		
		/*
		 * <li>Coordenadas SIRGAS 2000: <inter_lat_tag></inter_lat_tag>,<inter_lon_tag></inter_lon_tag></li>
				<li>Tipo de Poço: <inter_tipo_poco_tag></inter_tipo_poco_tag></li>
				<li>Profundidade: <inter_prof_tag></inter_prof_tag>.</li>
				<li>Nível Estático N.E: <inter_nivel_est_tag></inter_nivel_est_tag>.</li>
				<li>Nível Dinâmico N.D: <inter_niv_din_tag></inter_niv_din_tag>.</li>
				<li>Vazão média do Subsistema (L/h):</li>
				<li>Vazão teste (L/h): <inter_vazao_tag></inter_vazao_tag>.</li>
		 */
		
		
		docHTMLTabelaPontoCaptacao = Jsoup.parse(strTabela1, "UTF-8").clone();
		
		
		try { docHTMLTabelaPontoCaptacao.select("inter_bacia_tag").prepend(((Subterranea)listMalaDireta.get(in)[0][2]).getInterBaciaFK().getBaciaNome());} 
			catch (Exception e) {docHtml.select("inter_bacia_tag").prepend("");};
		try { docHTMLTabelaPontoCaptacao.select("inter_uh_tag").prepend(String.valueOf(((Subterranea)listMalaDireta.get(in)[0][2]).getInterUHFK().getUhCodigo()));} 
			catch (Exception e) {docHtml.select("inter_uh_tag").prepend("");};
		try { docHTMLTabelaPontoCaptacao.select("inter_lat_tag").prepend(String.valueOf(((Subterranea)listMalaDireta.get(in)[0][2]).getInterDDLatitude()));} 
			catch (Exception e) {docHtml.select("inter_lat_tag").prepend("");};
		try { docHTMLTabelaPontoCaptacao.select("inter_lon_tag").prepend(String.valueOf(((Subterranea)listMalaDireta.get(in)[0][2]).getInterDDLongitude()));} 
			catch (Exception e) {docHtml.select("inter_lon_tag").prepend("");};
		
		
		docHtmlTabelasLimitesOutorgados = Jsoup.parse(strTabela2, "UTF-8").clone();
		
		for (Finalidade f : ((Interferencia) listMalaDireta.get(in)[0][2]).getFinalidades() ) {
			
			if (f.getClass().getName() == "entidades.FinalidadeAutorizada") {
				
				double dbl_q_metros_hora;
				int int_t_horas_dia;
				int int_t_dias_mes;
			
				for (int i = 0; i<12; i++) {
					
			
					//double dbl_q_metros_hora = Double.parseDouble(((Subterranea) listMalaDireta.get(in)[0][2]).getSubVazao())/1000;
					//int int_t_horas_dia =  Integer.parseInt(gs.callGetter(f, listVariaveisVazaoHoraAutorizadas.get(i)));
					//int int_t_dias_mes = Integer.parseInt((gs.callGetter(f,listVariaveisTempoAutorizadas.get(i))));
					
					try {dbl_q_metros_hora = (((Subterranea) listMalaDireta.get(in)[0][2]).getSubVazaoPoco())/1000;} 
						catch (Exception e ) {
						dbl_q_metros_hora = 0.0;
					
					}
					
					try {int_t_horas_dia = Integer.parseInt(gs.callGetter(f, listVariaveisVazaoHoraAutorizadas.get(i)));} 
						catch (Exception e ) {
						int_t_horas_dia = 0;
						
					}
					
					try {int_t_dias_mes = Integer.parseInt((gs.callGetter(f,listVariaveisTempoAutorizadas.get(i))));} 
						catch (Exception e ) {
						int_t_dias_mes = 0;
						//System.out.println("dbl_q_metros_hora zero ");
					}
				
					try { docHtmlTabelasLimitesOutorgados.select(q_litros_hora_tag [i]).prepend(df.format((((Subterranea) listMalaDireta.get(in)[0][2])).getSubVazaoPoco()) .replaceAll(",00", ""));} 
						
						catch (Exception e) {docHtmlTabelasLimitesOutorgados.select(q_litros_hora_tag[i]).prepend("");};
					
					
					try { docHtmlTabelasLimitesOutorgados.select(q_metros_hora_tag [i]).prepend(  String.valueOf(dbl_q_metros_hora) );} 
					
						catch (Exception e) {docHtmlTabelasLimitesOutorgados.select(q_metros_hora_tag[i]).prepend("");};
					
					try { docHtmlTabelasLimitesOutorgados.select(t_horas_dia_tag [i]).prepend( String.valueOf(int_t_horas_dia) );} 
					
						catch (Exception e) {docHtmlTabelasLimitesOutorgados.select(t_horas_dia_tag[i]).prepend("");};
						
						
					try { docHtmlTabelasLimitesOutorgados.select(t_dias_mes_tag [i]).prepend( String.valueOf(int_t_dias_mes) );} 
						
						catch (Exception e) {docHtmlTabelasLimitesOutorgados.select(t_dias_mes_tag[i]).prepend("");};
						
						
					try { docHtmlTabelasLimitesOutorgados.select(q_metros_mes_tag [i]).prepend( String.format("%.0f", dbl_q_metros_hora*int_t_horas_dia*int_t_dias_mes) );} 
			
						catch (Exception e) {docHtmlTabelasLimitesOutorgados.select(q_metros_mes_tag[i]).prepend("");};
						
				}		
					
			} // fim if finalidade autorizada
					
		
		} // fim loop tabela 2	
		
		
		
		docHtml.select("tabela_ponto_captacao_tag").append(String.valueOf(docHTMLTabelaPontoCaptacao));
		docHtml.select("tabela_limites_outorgados_tag").append(String.valueOf(docHtmlTabelasLimitesOutorgados));
		
		
		String html = new String ();

		html = docHtml.toString();

		html = html.replace("\"", "'");
		html = html.replace("\n", "");

		html =  "\"" + html + "\"";
		
		
		
		
		return html;
		
	}
	
	
	
}
