package principal;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Documento;
import entidades.Endereco;
import entidades.GetterAndSetter;
import entidades.Interferencia;
import entidades.Usuario;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MalaDiretaDocumentos {
	
	String modeloHTML;
	
	List<String> listVariaveisFinalidades;
	List<String> listVariaveisSubfinaldades;
	List<String> listVariaveisQuantidades;
	List<String> listVariaveisConsumo;
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
	

	Set<Usuario> setUsuarios = new HashSet<>();
	Set<Endereco> setEnderecos = new HashSet<>();
	Set<Interferencia> setInterferencias = new HashSet<>();
	
	Documento documento = new Documento();
	
	
	public MalaDiretaDocumentos (String modeloHTML, Documento documento, Set<Usuario>setUsuarios, Set<Endereco> setEnderecos, Set<Interferencia> setInterferencias) {
		
		this.modeloHTML = modeloHTML;
		this.documento = documento;
		this.setUsuarios = setUsuarios;
		this.setEnderecos = setEnderecos;
		this.setInterferencias = setInterferencias;
		
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
						
	 */
	
	public String criarDocumento () {
		
		//GetterAndSetter gs  = new GetterAndSetter();
		
		Document docHtml = null;
		
		docHtml = Jsoup.parse(modeloHTML, "UTF-8").clone();
		
		// dados documento
		if (!(documento == null)) {
			
			String strPosicoesDocumento [] = {
					"doc_processo_tag",
						"doc_num_tag",
							"doc_data_tag"	};
			
			// formatador
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
				// string com a data:  Terça-feira, 2 de Abril de 2019
				String dataExtenso = formatador.format(documento.getDocDataCriacao());
			
					//System.out.println(dataExtenso);
			
						// retirar o dia da semana:  2 de Abril de 2019
						int index  = dataExtenso.indexOf(","); // inicio da substring
		
							//System.out.println(dataExtenso.substring(index + 2));
			
			String strDocumento [] = {
					documento.getDocProcesso(),
						documento.getDocNumeracao(),
							dataExtenso.substring(index + 2)	};
			
			for (int i  = 0; i<strPosicoesDocumento.length; i++) {
 				
 				try { docHtml.select(strPosicoesDocumento[i]).prepend(strDocumento[i]);} 
 					catch (Exception e) {docHtml.select(strPosicoesDocumento[i]).prepend("");};
 			}
			
			
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
