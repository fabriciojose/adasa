package principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Documento;
import entidades.GetterAndSetter;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.Usuario;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MalaDiretaDocumentos {
	
	String modeloHTML;
	
	List<String> finalidadesSubterranea;
	List<String> finalidadesSuperficial;
	
	List<String> listVariaveisSubfinaldades;
	List<String> quantidadesSuperficial;
	List<String> quantidadesSubterranea;
	
	List<String> consumoSubterranea;
	List<String> consumoSuperficial;
	
	List<String> listVariaveisVazao;
	
	List<String> listVariaveisVazaoMes;
	List<String> listVariaveisVazaoHora;
	List<String> listVariaveisTempo;
	
	/*
	 * capturar as finalidades cadastradas, subfinalidades etc
	 */
				
	List<String> listFinalidadesCadastradas = new ArrayList<String>();
	List<String> listSubfinalidadesCadastradas = new ArrayList<String>();
	List<String> listQuantidadesCadastradas = new ArrayList<String>();
	List<String> listConsumosCadastrados = new ArrayList<String>();
	List<String> listVazoesCadastradas = new ArrayList<String>();
	
	/*
	 * capturar as vazoes l/dia l/hora e periodo mes
	 */
	List<String> listVazoesDia = new ArrayList<String>();
	List<String> listVazoesHora = new ArrayList<String>();
	List<String> listPeriodos = new ArrayList<String>();
	
	
	List<Object[][]> listMalaDireta = new ArrayList<>();
	
	Documento documento = new Documento();
	
	public MalaDiretaDocumentos (String modeloHTML, Documento documento, List<Object[][]> listMalaDireta) {
	
		this.modeloHTML = modeloHTML;
		this.documento = documento;
		this.listMalaDireta = listMalaDireta;
	
	}
	
	public String criarDocumento () {
		
		//GetterAndSetter gs  = new GetterAndSetter();
		
		Document docHtml = null;
		
		docHtml = Jsoup.parse(modeloHTML, "UTF-8").clone();
		
		System.out.println("||||||||||||| inicio mala direta |||||||||||||||||||||||||||||");
		
		
		finalidadesSubterranea = Arrays.asList("subFinalidade1", "subFinalidade2", "subFinalidade3", "subFinalidade4", "subFinalidade5");
		listVariaveisSubfinaldades = Arrays.asList("subSubfinalidade1", "subSubfinalidade2", "subSubfinalidade3", "subSubfinalidade4", "subSubfinalidade5");
		quantidadesSubterranea = Arrays.asList("subQuantidade1", "subQuantidade2", "subQuantidade3", "subQuantidade4", "subQuantidade5");
		consumoSubterranea = Arrays.asList("subConsumo1", "subConsumo2", "subConsumo3", "subConsumo4", "subConsumo5");
		listVariaveisVazao = Arrays.asList("subVazao1", "subVazao2", "subVazao3", "subVazao4", "subVazao5");
		

		finalidadesSuperficial = Arrays.asList("supFinalidade1", "supFinalidade2", "supFinalidade3", "supFinalidade4", "supFinalidade5");
		listVariaveisSubfinaldades = Arrays.asList("supSubfinalidade1", "supSubfinalidade2", "supSubfinalidade3", "supSubfinalidade4", "supSubfinalidade5");
		quantidadesSuperficial = Arrays.asList("supQuantidade1", "supQuantidade2", "supQuantidade3", "supQuantidade4", "supQuantidade5");
		consumoSuperficial = Arrays.asList("supConsumo1", "supConsumo2", "supConsumo3", "supConsumo4", "supConsumo5");
		listVariaveisVazao = Arrays.asList("supVazao1", "supVazao2", "supVazao3", "supVazao4", "supVazao5");
		
		
		// dados documento
		if (!(documento == null)) {

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

						strTable.append("<td>" + 
								((Subterranea)listMalaDireta.get(i)[0][ii]).getInterTipoOutorgaFK().getTipoOutorgaDescricao()  
								+ 	"<p>" +  ((Subterranea)listMalaDireta.get(i)[0][ii]).getInterSubtipoOutorgaFK().getSubtipoOutorgaDescricao()
								+ 	"</td>");

						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Subterranea) listMalaDireta.get(i)[0][ii]), 
									finalidadesSubterranea.get(a)))	+ "</p>");
						}

						strTable.append("</td>");

						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Subterranea) listMalaDireta.get(i)[0][ii]), 
									quantidadesSubterranea.get(a)))	+ "</p>");
						}

						strTable.append("</td>");


						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Subterranea) listMalaDireta.get(i)[0][ii]), 
									consumoSubterranea.get(a)))	+ "</p>");
						}

						strTable.append("</td>");


						strTable.append("<td>" + 
								((Subterranea)listMalaDireta.get(i)[0][ii]).getSubVazaoTotal()
								+ 	"</td>");


						break;
					case "entidades.Superficial":

						strTable.append("<td>" + 
								((Superficial)listMalaDireta.get(i)[0][ii]).getInterTipoOutorgaFK().getTipoOutorgaDescricao()  
								+ 	"<p>" +  ((Superficial)listMalaDireta.get(i)[0][ii]).getInterSubtipoOutorgaFK().getSubtipoOutorgaDescricao()
								+ 	"</td>");

						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Superficial) listMalaDireta.get(i)[0][ii]), 
									finalidadesSuperficial.get(a)))	+ "</p>");
						}

						strTable.append("</td>");

						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Superficial) listMalaDireta.get(i)[0][ii]), 
									quantidadesSuperficial.get(a)))	+ "</p>");
						}

						strTable.append("</td>");

						strTable.append("<td>");

						for (int a = 0; a<5; a++) {

							strTable.append("<p>" + (gs.callGetter(((Superficial) listMalaDireta.get(i)[0][ii]), 
									consumoSuperficial.get(a)))	+ "</p>");
						}

						strTable.append("</td>");

						strTable.append("<td>" + 
								((Superficial)listMalaDireta.get(i)[0][ii]).getSupVazaoTotal() 
								+ 	"</td>");


						break;

					default:
						break;
					}

					System.out.println(listMalaDireta.get(i)[0][ii].getClass().getName());



				}

				strTable.append("</tr>");

			}

			strTable.append("</tbody></table>");

			docHtml.select("tabela_tag").append(String.valueOf(strTable));


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
	    
	   // //TabNavegadorController.html = html;
	   // TabNavegadorController.numIframe = 1;
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

