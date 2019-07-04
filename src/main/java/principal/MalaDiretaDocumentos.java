package principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Documento;
import entidades.Finalidade;
import entidades.FinalidadeAutorizada;
import entidades.FinalidadeRequerida;
import entidades.GetterAndSetter;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.Usuario;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** Classe de preechimento de html 
 * 
 * @author fabricio.barrozo
 *
 */
public class MalaDiretaDocumentos {

	String modeloHTML;

	List<String> listVariaveisFinalidadesRequeridas = Arrays.asList("frFinalidade1", "frFinalidade2", "frFinalidade3", "frFinalidade4", "frFinalidade5");
	List<String> listVariaveisSubfinaldadesRequeridas = Arrays.asList("frSubfinalidade1", "frSubfinalidade2", "frSubfinalidade3", "frSubfinalidade4", "frSubfinalidade5");
	List<String> listVariaveisQuantidadesRequeridas = Arrays.asList("frQuantidade1", "frQuantidade2", "frQuantidade3", "frQuantidade4", "frQuantidade5");
	List<String> listVariaveisConsumoRequeridas = Arrays.asList("frConsumo1", "frConsumo2", "frConsumo3", "frConsumo4", "frConsumo5");
	List<String> listVariaveisVazaoRequeridas = Arrays.asList("frVazao1", "frVazao2", "frVazao3", "frVazao4", "frVazao5");

	// variaveis de vazao mensal e reflexao //
	List<String> listVariaveisVazaoMesRequeridas = Arrays.asList(

			"frQDiaJan","frQDiaFev","frQDiaMar","frQDiaAbr","frQDiaMai","frQDiaJun",
			"frQDiaJul","frQDiaAgo","frQDiaSet","frQDiaOut","frQDiaNov","frQDiaDez"	

			);

	List<String> listVariaveisVazaoHoraRequeridas = Arrays.asList(

			"frQHoraJan","frQHoraFev","frQHoraMar","frQHoraAbr","frQHoraMai","frQHoraJun",
			"frQHoraJul","frQHoraAgo","frQHoraSet","frQHoraOut","frQHoraNov","frQHoraDez"

			);

	List<String> listVariaveisTempoRequeridas = Arrays.asList(

			"frTempoCapJan","frTempoCapFev","frTempoCapMar","frTempoCapAbr","frTempoCapMai","frTempoCapJun",
			"frTempoCapJul","frTempoCapAgo","frTempoCapSet","frTempoCapOut","frTempoCapNov","frTempoCapDez"

			);

	List<String> listVariaveisFinalidadesAutorizadas = Arrays.asList("faFinalidade1", "faFinalidade2", "faFinalidade3", "faFinalidade4", "faFinalidade5");
	List<String> listVariaveisSubfinaldadesAutorizadas = Arrays.asList("faSubfinalidade1", "faSubfinalidade2", "faSubfinalidade3", "faSubfinalidade4", "faSubfinalidade5");
	List<String> listVariaveisQuantidadesAutorizadas = Arrays.asList("faQuantidade1", "faQuantidade2", "faQuantidade3", "faQuantidade4", "faQuantidade5");
	List<String> listVariaveisConsumoAutorizadas = Arrays.asList("faConsumo1", "faConsumo2", "faConsumo3", "faConsumo4", "faConsumo5");
	List<String> listVariaveisVazaoAutorizadas = Arrays.asList("faVazao1", "faVazao2", "faVazao3", "faVazao4", "faVazao5");

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


	List<Object[][]> listMalaDireta = new ArrayList<>();

	Documento documento = new Documento();

	/**
	 * Construtor
	 * @param modeloHTML
	 * @param documento
	 * @param listMalaDireta
	 */
	public MalaDiretaDocumentos (String modeloHTML, Documento documento, List<Object[][]> listMalaDireta) {

		this.modeloHTML = modeloHTML;
		this.documento = documento;
		this.listMalaDireta = listMalaDireta;

	}

	FinalidadeRequerida finSubReq;
	FinalidadeRequerida finSupReq;

	FinalidadeAutorizada finSubAut;
	FinalidadeAutorizada finSupAut;

	/**
	 * Metodo de criacao do documento html
	 * @return String strhtml com o html pronto para inserir no editor do site SEI
	 */
	public String criarDocumento () {


		Document docHtml = null;

		docHtml = Jsoup.parse(modeloHTML, "UTF-8").clone();


		// dados documento
		if (!(documento == null)) {

			finSubReq = new FinalidadeRequerida();
			finSupReq = new FinalidadeRequerida();

			inserirFinalidade (
					docHtml, 
					finSubReq, finSupReq, 
					"entidades.FinalidadeRequerida", "tfr_tag", "frVazaoTotal",
					listVariaveisFinalidadesRequeridas,listVariaveisSubfinaldadesRequeridas,listVariaveisQuantidadesRequeridas,listVariaveisConsumoRequeridas,
					listVariaveisVazaoRequeridas,
					listVariaveisVazaoMesRequeridas,listVariaveisVazaoHoraRequeridas,listVariaveisTempoRequeridas

					);

			finSubAut = new FinalidadeAutorizada();
			finSupAut = new FinalidadeAutorizada();

			inserirFinalidade (docHtml, 
					finSubAut, finSupAut, 
					"entidades.FinalidadeAutorizada", "tfa_tag", "faVazaoTotal",
					listVariaveisFinalidadesAutorizadas,listVariaveisSubfinaldadesAutorizadas,listVariaveisQuantidadesAutorizadas,listVariaveisConsumoAutorizadas,
					listVariaveisVazaoAutorizadas,
					listVariaveisVazaoMesAutorizadas,listVariaveisVazaoHoraAutorizadas,listVariaveisTempoAutorizadas

					);


		} // fim if documento null


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

	/** Metodo de inserir as finalidades no parecer coletivo
	 * 
	 * @param docHtml
	 * @param finSub
	 * @param finSup
	 * @param strFinalidade
	 * @param strTag
	 * @param vazaoTotal
	 * @param listVariaveisFinalidades
	 * @param listVariaveisSubfinaldades
	 * @param listVariaveisQuantidades
	 * @param listVariaveisConsumo
	 * @param listVariaveisVazao
	 * @param listVariaveisVazaoMes
	 * @param listVariaveisVazaoHora
	 * @param listVariaveisTempo
	 */
	public void inserirFinalidade (
			Document docHtml, 
			Finalidade finSub, 
			Finalidade finSup, 
			String strFinalidade, String strTag, String vazaoTotal,
			List<String> listVariaveisFinalidades,List<String> listVariaveisSubfinaldades,List<String> listVariaveisQuantidades,List<String> listVariaveisConsumo,
			List<String> listVariaveisVazao,
			List<String> listVariaveisVazaoMes,List<String> listVariaveisVazaoHora,List<String> listVariaveisTempo

			) {

		StringBuilder strTable = new StringBuilder();

		GetterAndSetter gs  = new GetterAndSetter();

		strTable.append("<table border='1' cellspacing='0' style='width: 800px;'>"
				+ "<tbody><tr><td>Processo</td><td>Requerente</td>"
				+ "<td>Solicita&ccedil;&atilde;o</td><td>Finalidade</td><td>Quantidade</td>"
				+ "<td>Demanda (L/dia)</td><td>Demanda Total (L/dia)</td></tr>");

		for (int i = 0; i<listMalaDireta.size(); i++) {

			strTable.append("<tr>");

			for (int ii=0; ii < listMalaDireta.get(i)[0].length; ii++) {

				switch (listMalaDireta.get(i)[0][ii].getClass().getName()) {
				case "entidades.Documento":
					strTable.append("<td>" + ((Documento)listMalaDireta.get(i)[0][ii]).getDocProcessoFK().getProSEI()  + "</td>");
					break;
				case "entidades.Usuario":
					strTable.append("<td>" + ((Usuario)listMalaDireta.get(i)[0][ii]).getUsNome() + "</td>");
					break;
				case "entidades.Subterranea":

					for (Finalidade fSub : ((Subterranea)listMalaDireta.get(i)[0][ii]).getFinalidades() ) {

						if (fSub.getClass().getName() == strFinalidade) {
							finSub = fSub;

						}

					}

					strTable.append("<td>" + 
							((Subterranea)listMalaDireta.get(i)[0][ii]).getInterTipoOutorgaFK().getTipoOutorgaDescricao()  
							+ 	"<p>" +  ((Subterranea)listMalaDireta.get(i)[0][ii]).getInterSubtipoOutorgaFK().getSubtipoOutorgaDescricao()
							+ 	"</td>");

					strTable.append("<td width='200'>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSub, 
								listVariaveisFinalidades.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSub, 
								listVariaveisQuantidades.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSub, 
								listVariaveisConsumo.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>" + String.valueOf(gs.callGetter(finSub, vazaoTotal))	+ "</td>");


					break;
				case "entidades.Superficial":

					for (Finalidade fSup : ((Superficial)listMalaDireta.get(i)[0][ii]).getFinalidades() ) {

						if (fSup.getClass().getName() == strFinalidade) {
							finSup = fSup;

						}

					}

					strTable.append("<td>" + 
							((Superficial)listMalaDireta.get(i)[0][ii]).getInterTipoOutorgaFK().getTipoOutorgaDescricao()  
							+ 	"<p>" +  ((Superficial)listMalaDireta.get(i)[0][ii]).getInterSubtipoOutorgaFK().getSubtipoOutorgaDescricao()
							+ 	"</td>");

					strTable.append("<td width='200'>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSup, 
								listVariaveisFinalidades.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSup, 
								listVariaveisQuantidades.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>");

					for (int a = 0; a<5; a++) {

						strTable.append("<p>" + (gs.callGetter(finSup, 
								listVariaveisConsumo.get(a)))	+ "</p>");
					}

					strTable.append("</td>");

					strTable.append("<td>" + String.valueOf(gs.callGetter(finSup, vazaoTotal))	+ "</td>");

					break;

				default:
					break;
				} // fim while

				//System.out.println(listMalaDireta.get(i)[0][ii].getClass().getName());

			}

			strTable.append("</tr>");

		} // fim loop for

		strTable.append("</tbody></table>");

		docHtml.select(strTag).append(String.valueOf(strTable));

	}

}


/*
 * para o parecer coletivo
 * <tr>
					<td><doc_processo_tag></doc_processo_tag></td>
					<td><inter_tipo_outorga_tag></inter_tipo_outorga_tag> </td>
					<td><us_nome_tag></us_nome_tag></td>
					<td><abast_hum_tag></abast_hum_tag> <cria_anim_tag></cria_anim_tag> <irrig_tag></irrig_tag> <uso_ind_tag></uso_ind_tag> </td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>


					StringBuilder strAbasHum = new StringBuilder();

					strAbasHum
					.append("<div align='justify'><strong style='font-style: italic; font-size: 12px;'>- ABASTECIMENTO HUMANO</strong>"
							+	"<table border='1' cellspacing='0' style='width: 800px;'><tbody><tr><td colspan='1'>"
							+ 	"Popula&ccedil;&atilde;o:&nbsp;")    // populacao
					.append(listQuantidadesCadastradas.get(i))
					.append("</td><td colspan='1'>Consumo diário por habitante:&nbsp;") //  consumo
					.append(listConsumosCadastrados.get(i))
					.append("</td><td colspan='1' width='20%'>Total:")              // total
					.append(listVazoesCadastradas.get(i));

		//--------------- conversor de data para fiscar no formato por extenso 	---------------------------------//		

						"doc_data_tag"

						// formatador
		//DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			// string com a data:  Terça-feira, 2 de Abril de 2019
			//String dataExtenso = formatador.format(documento.getDocDataCriacao());

				//System.out.println(dataExtenso);

					// retirar o dia da semana:  2 de Abril de 2019
					//int index  = dataExtenso.indexOf(","); // inicio da substring

						//System.out.println(dataExtenso.substring(index + 2));
		// -----------------------------------------------------------------------------------------------------//


				// tipo outorga (outorga, outorga prévia, registro)	"inter_tipo_outorga_tag",


 */

