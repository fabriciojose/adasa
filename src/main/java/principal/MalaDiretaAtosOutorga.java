package principal;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Documento;
import entidades.Endereco;
import entidades.Finalidade;
import entidades.GetterAndSetter;
import entidades.Interferencia;
import entidades.Subterranea;
import entidades.Usuario;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MalaDiretaAtosOutorga {
	
	
	Documento documento = new Documento();
	Endereco endereco = new Endereco();
	Usuario usuario = new Usuario();
	List<Interferencia> listInterferencia = new ArrayList<>();
	String modeloHTML;
	String modeloTabelaLimitesOutorgados;
	
	
	List<String> listVariaveisFinalidadesAutorizadas = Arrays.asList("faFinalidade1", "faFinalidade2", "faFinalidade3", "faFinalidade4", "faFinalidade5");
	List<String> listVariaveisSubfinaldadesAutorizadas = Arrays.asList("faSubfinalidade1", "faSubfinalidade2", "faSubfinalidade3", "faSubfinalidade4", "faSubfinalidade5");
	List<String> listVariaveisQuantidadesAutorizadas = Arrays.asList("faQuantidade1", "faQuantidade2", "faQuantidade3", "faQuantidade4", "faQuantidade5");
	List<String> listVariaveisConsumoAutorizadas = Arrays.asList("faConsumo1", "faConsumo2", "faConsumo3", "faConsumo4", "faConsumo5");
	List<String> listVariaveisVazaoAutorizadas = Arrays.asList("faVazao1", "faVazao2", "faVazao3", "faVazao4", "faVazao5");
	
	
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
	
	

	String q_metros_dia_tag [] = {
			
			"q_metros_dia_jan_tag",
			"q_metros_dia_fev_tag",
			"q_metros_dia_mar_tag",
			"q_metros_dia_abr_tag",
			"q_metros_dia_mai_tag",
			"q_metros_dia_jun_tag",
			"q_metros_dia_jul_tag",
			"q_metros_dia_ago_tag",
			"q_metros_dia_set_tag",
			"q_metros_dia_out_tag",
			"q_metros_dia_nov_tag",
			"q_metros_dia_dez_tag",
	
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

	
	
	public MalaDiretaAtosOutorga (String modeloHTML, String modeloTabelaLimitesOutorgados, Documento documento,Endereco endereco, Usuario usuario, List<Interferencia> listInterferencia) {
		
		this.modeloHTML = modeloHTML;
		this.modeloTabelaLimitesOutorgados = modeloTabelaLimitesOutorgados;
		
		this.documento = documento;
		this.endereco = endereco;
		this.usuario = usuario;
		
		this.listInterferencia = listInterferencia;
		
		
	}
	
	// formatar 1000.50 para 1.000,50 e retirar zeros irrelevantes como ,00 - 15.00 fica 15
	DecimalFormat df = new DecimalFormat("#,##0.00"); 
	
	public String criarAtoOutorga () {
		
		//GetterAndSetter gs  = new GetterAndSetter();

		Document docHtml = null;

		docHtml = Jsoup.parse(modeloHTML, "UTF-8").clone();
	
		 //nao coloquei inter_tipo_poco_tag

		String strPosicoesUsuario [] = {
				
				"doc_proc_principal_tag",
				"us_nome_tag", // <us_nome_tag></us_nome_tag>
				"us_cpfcnpj_tag", //<us_cpfcnpj_tag></us_cpfcnpj_tag>
				"us_tel_tag", // <us_tel_tag></us_tel_tag>
				"us_cel_tag", // <us_cel_tag></us_cel_tag>
				"us_end_cor_tag",  // <us_end_cor_tag></us_end_cor_tag>
				"us_cep_tag", // <us_cep_tag></us_cep_tag> 
				"us_email_tag" // <us_email_tag></us_email_tag>
		};

		String strUsuario [] = {
				
				documento.getDocProcessoFK().getProSEI(),
				usuario.getUsNome(),
				usuario.getUsCPFCNPJ(),						
				usuario.getUsTelefone()	,
				usuario.getUsCelular(),
				usuario.getUsLogadouro(),
				usuario.getUsCEP(),
				usuario.getUsEmail(),
		};

		for (int i  = 0; i<strPosicoesUsuario.length; i++) {

			try { docHtml.select(strPosicoesUsuario[i]).prepend(strUsuario[i]);} 
			catch (Exception e) {docHtml.select(strPosicoesUsuario[i]).prepend("");

			};
		}
		
		
		String strPosicoesEndereco [] = {

				"end_log_tag", // <end_log_tag></end_log_tag>
				"end_ra_tag",  // <end_ra_tag></end_ra_tag>
				"end_cep_tag",  // <end_cep_tag></end_cep_tag>

		};
		String strEndereco [] = {

				endereco.getEndLogradouro(),  
				endereco.getEndRAFK().getRaNome(),						
				endereco.getEndCEP(),

		};

		for (int i  = 0; i<strPosicoesEndereco.length; i++) {
			try { docHtml.select(strPosicoesEndereco[i]).prepend(strEndereco[i]);} 
			catch (Exception e) {docHtml.select(strPosicoesEndereco[i]).prepend("");
			};
		}
		
		
		GetterAndSetter gs  = new GetterAndSetter();
		
		StringBuilder strFinalidades = new StringBuilder();
		
		//	StringBuilder strVazaoPeriodo = new StringBuilder();
		
		
		for (Finalidade f : listInterferencia.get(0).getFinalidades() ) {
		
			if (f.getClass().getName() == "entidades.FinalidadeAutorizada") {
			
				for (int i = 0; i<5; i++) {
					
					strFinalidades.append(gs.callGetter(f, listVariaveisFinalidadesAutorizadas.get(i)) + ", ");
					
				}
				
		}
		
			
		}
		
		System.out.println("Mala Direta Atos Outorga - finalidades " + strFinalidades);

		docHtml.select("finalidades_tag").append(String.valueOf(strFinalidades));
		
		
		String strTabelaPontoCaptacaoComeco = "<table border='1' cellspacing='0' style='width: 800px;'><tbody>"
				+ "<tr>"
				+ "<td rowspan='2'>Ponto de Capta&ccedil;&atilde;o</td>"
				+ "<td rowspan='2'>Bacia Hidrogr&aacute;fica</td>"
				+ "<td rowspan='2'>Unidade Hidrogr&aacute;fica</td>"
				+ "<td colspan='2'>Coordenadas do Ponto de Capta&ccedil;&atilde;o (SIRGAS 2000)</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Latitude</td>"
				+ "<td>Longitude</td>"
				+ "</tr>";
		
		
		String strTabelaPontoCaptacaoFim = "</tbody></table>";
		
		StringBuilder strTabelaPontoCaptacaoCompleta = new StringBuilder();
		
		strTabelaPontoCaptacaoCompleta.append(strTabelaPontoCaptacaoComeco);
		
		for (Interferencia i : listInterferencia) {
			
			strTabelaPontoCaptacaoCompleta.append(
					
					"<tr>"
					+ "<td>"
					+ "<p>&nbsp;</p>"
					+ "</td>"
					+ "<td> " + i.getInterBaciaFK().getBaciaNome() + "</td>"
					+ "<td>" + 	i.getInterUHFK().getUhCodigo() + "</td>"
					+ "<td><b>" + 	i.getInterDDLatitude() + "</b></td>"
					+ "<td><b>" + 	i.getInterDDLongitude() + "</b></td>"
					+ "</tr>"
											
					
					);
			
		}
		
		strTabelaPontoCaptacaoCompleta.append(strTabelaPontoCaptacaoFim);

		// 	<inter_dados_basicos_tag></inter_dados_basicos_tag>
		docHtml.select("inter_dados_basicos_tag").append(String.valueOf(strTabelaPontoCaptacaoCompleta));


		for (Interferencia inter : listInterferencia) {
		
			Document docHTMLModeloTabelaLimitesOutorgados = null;

			docHTMLModeloTabelaLimitesOutorgados = Jsoup.parse(modeloTabelaLimitesOutorgados, "UTF-8").clone();
		
			if (inter.getClass().getName().equals("entidades.Subterranea")) {


			for (Finalidade f : inter.getFinalidades() ) {


				if (f.getClass().getName().equals("entidades.FinalidadeAutorizada")) {
					
					double dbl_q_metros_hora;
					int int_t_horas_dia;
					int int_t_dias_mes;

					for (int i = 0; i<12; i++) {

						
						try {dbl_q_metros_hora = ((Subterranea) inter).getSubVazaoPoco()/1000;} catch (Exception e ) {
							dbl_q_metros_hora = 0.0;
						
						}
						try {int_t_horas_dia =  Integer.parseInt(gs.callGetter(f, listVariaveisVazaoHoraAutorizadas.get(i)));} catch (Exception e ) {
							int_t_horas_dia = 0;
						
						}
						try {int_t_dias_mes = Integer.parseInt((gs.callGetter(f,listVariaveisTempoAutorizadas.get(i))));} catch (Exception e ) {
							int_t_dias_mes = 0;
						

						}

						//sub
						
						// litros hora L/H
						try { docHTMLModeloTabelaLimitesOutorgados.select(q_litros_hora_tag [i]).prepend(
								
								// formatar 1000.50 para 1.000,50 e retirar zeros irrelevantes como ,00 - 15.00 fica 15
								df.format(	((Subterranea) inter).getSubVazaoPoco()	).replaceAll(",00", "")	);} 

								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_litros_hora_tag[i]).prepend("");};

						// metros hora M/H
						try { docHTMLModeloTabelaLimitesOutorgados.select(q_metros_hora_tag [i]).prepend( 

								df.format(dbl_q_metros_hora) .replaceAll(",00", "") .replaceAll(",00", "") 	);} 

								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_metros_hora_tag[i]).prepend("");};
						
						// HORAS DIA H/D
						try { docHTMLModeloTabelaLimitesOutorgados.select(t_horas_dia_tag [i]).prepend( 
								
								df.format(		 int_t_horas_dia) .replaceAll(",00", ""));} 

								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(t_horas_dia_tag[i]).prepend("");};
								
						// metros cubicos dia M/D
						try { docHTMLModeloTabelaLimitesOutorgados.select(q_metros_dia_tag[i]).prepend( String.format("%.0f", dbl_q_metros_hora*int_t_horas_dia) );} 
								
								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_metros_dia_tag[i]).prepend("");};
						
						// DIA MES D/M	
						try { docHTMLModeloTabelaLimitesOutorgados.select(t_dias_mes_tag [i]).prepend( 
								
								String.valueOf(int_t_dias_mes) );} 

								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(t_dias_mes_tag[i]).prepend("");};

						// METROS MES M/M	
						try { docHTMLModeloTabelaLimitesOutorgados.select(q_metros_mes_tag [i]).prepend( 
								
								df.format(		  dbl_q_metros_hora*int_t_horas_dia*int_t_dias_mes	) .replaceAll(",00", ""));} 

							catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_metros_mes_tag[i]).prepend("");};

					} // fim loop 12 preenchimento

				} // fim if finalidade autorizaa
				
			} // for finalidade

			docHtml.select("tabela_limites_outorgados_tag").append(String.valueOf(docHTMLModeloTabelaLimitesOutorgados));
			
			
			}
			
			
			if (inter.getClass().getName().equals("entidades.Superficial")) {


				for (Finalidade f : inter.getFinalidades() ) {


					if (f.getClass().getName().equals("entidades.FinalidadeAutorizada")) {
						
						double dbl_vazao_autorizada;
						int int_tempo_autorizado;
						int int_periodo_autorizado;

						for (int i = 0; i<12; i++) {

							try {dbl_vazao_autorizada = Double.parseDouble((gs.callGetter(f, listVariaveisVazaoMesAutorizadas.get(i))));} catch (Exception e ) {
								dbl_vazao_autorizada = 0.0;
								System.out.println("dbl_q_metros_hora zero ");
							}
							try {int_tempo_autorizado =  Integer.parseInt(gs.callGetter(f, listVariaveisVazaoHoraAutorizadas.get(i)));} catch (Exception e ) {
								int_tempo_autorizado = 0;
								System.out.println("int_t_horas_dia zero ");
							}
							try {int_periodo_autorizado = Integer.parseInt((gs.callGetter(f,listVariaveisTempoAutorizadas.get(i))));} catch (Exception e ) {
								int_periodo_autorizado = 0;
								System.out.println("int_t_dias_mes zero ");

							}

							// String format - formatar um numero double em string e regular as casas decimais
							try { docHTMLModeloTabelaLimitesOutorgados.select(q_litros_hora_tag [i]).prepend(
									
									df.format(		 dbl_vazao_autorizada/int_tempo_autorizado/3600) .replaceAll(",00", "")	
									
									
									);} 
								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_litros_hora_tag[i]).prepend("");};

							try { docHTMLModeloTabelaLimitesOutorgados.select(q_metros_hora_tag [i]).prepend( 
									
									
									df.format(	dbl_vazao_autorizada/int_tempo_autorizado/1000) .replaceAll(",00", "")
									
									
									);} 
								catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_metros_hora_tag[i]).prepend("");};

							try { docHTMLModeloTabelaLimitesOutorgados.select(t_horas_dia_tag [i]).prepend( 
									
									String.valueOf(int_tempo_autorizado) 
									
									);} 

							catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(t_horas_dia_tag[i]).prepend("");};

							// fin aut	
							try { docHTMLModeloTabelaLimitesOutorgados.select(t_dias_mes_tag [i]).prepend( 
									
									String.valueOf(int_periodo_autorizado) 
									
									);} 

							catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(t_dias_mes_tag[i]).prepend("");};

							//fin aut	String.format("%.0f", dbl_vazao_autorizada*int_periodo_autorizado/1000)
							try { docHTMLModeloTabelaLimitesOutorgados.select(q_metros_mes_tag [i]).prepend( 
									
									
									df.format(	 dbl_vazao_autorizada*int_periodo_autorizado/1000) .replaceAll(",00", "")	
									
									
									);} 

							catch (Exception e) {docHTMLModeloTabelaLimitesOutorgados.select(q_metros_mes_tag[i]).prepend("");};

						} // fim loop 12 preenchimento

					} // fim if finalidade autorizaa
					
				} // for finalidade

				docHtml.select("tabela_limites_outorgados_tag").append(String.valueOf(docHTMLModeloTabelaLimitesOutorgados));
				
				
				}
			
			
		} // fim loop for interferencia



		
		String html = new String ();

		html = docHtml.toString();

		html = html.replace("\"", "'");
		html = html.replace("\n", "");

		html =  "\"" + html + "\"";

		//-- webview do relat�rio --//

		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.loadContent(html);

		Scene scene = new Scene(browser);

		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1000);
		stage.setHeight(650);
		stage.setScene(scene);
		stage.setMaximized(false);
		stage.setResizable(false);

		stage.show();

		return html;
		
	}
	
	
	

}
