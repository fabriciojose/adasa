package principal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import entidades.Documento;
import entidades.Endereco;
import entidades.Finalidade;
import entidades.FinalidadeRequerida;
import entidades.GetterAndSetter;
import entidades.Interferencia;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.Usuario;

public class MalaDireta {

	String htmlRel;

	Documento documento;
	Endereco endereco;
	Interferencia interferencia;
	Usuario usuario;

	/*
	 * Dados das finalidades
	 */
	//List<String> supplierNames;

	List<String> listVariaveisFinalidades = Arrays.asList("frFinalidade1", "frFinalidade2", "frFinalidade3", "frFinalidade4", "frFinalidade5");
	List<String> listVariaveisSubfinaldades = Arrays.asList("frSubfinalidade1", "frSubfinalidade2", "frSubfinalidade3", "frSubfinalidade4", "frSubfinalidade5");
	List<String> listVariaveisQuantidades = Arrays.asList("frQuantidade1", "frQuantidade2", "frQuantidade3", "frQuantidade4", "frQuantidade5");
	List<String> listVariaveisConsumo = Arrays.asList("frConsumo1", "frConsumo2", "frConsumo3", "frConsumo4", "frConsumo5");
	List<String> listVariaveisVazao = Arrays.asList("frVazao1", "frVazao2", "frVazao3", "frVazao4", "frVazao5");

	// variaveis de vazao mensal e reflexao //
	List<String> listVariaveisVazaoMes = Arrays.asList(

			"frQDiaJan","frQDiaFev","frQDiaMar","frQDiaAbr","frQDiaMai","frQDiaJun",
			"frQDiaJul","frQDiaAgo","frQDiaSet","frQDiaOut","frQDiaNov","frQDiaDez"	

			);

	List<String> listVariaveisVazaoHora = Arrays.asList(

			"frQHoraJan","frQHoraFev","frQHoraMar","frQHoraAbr","frQHoraMai","frQHoraJun",
			"frQHoraJul","frQHoraAgo","frQHoraSet","frQHoraOut","frQHoraNov","frQHoraDez"

			);

	List<String> listVariaveisTempo = Arrays.asList(

			"frTempoCapJan","frTempoCapFev","frTempoCapMar","frTempoCapAbr","frTempoCapMai","frTempoCapJun",
			"frTempoCapJul","frTempoCapAgo","frTempoCapSet","frTempoCapOut","frTempoCapNov","frTempoCapDez"

			);

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


	public String getHtmlRel() {
		return htmlRel;
	}

	public void setHtmlRel(String htmlRel) {
		this.htmlRel = htmlRel;
	}

	public MalaDireta () {

	}

	public MalaDireta (Endereco endereco, Interferencia interferencia,Usuario usuario) {

		this.endereco = endereco;
		this.interferencia = interferencia;
		this.usuario = usuario;

	}

	public MalaDireta (Documento documento,Endereco endereco,Interferencia interferencia, Usuario usuario) {

		this.documento = documento;
		this.endereco = endereco;
		this.interferencia = interferencia;
		this.usuario = usuario;
	}




	public String criarDocumento () {

		GetterAndSetter gs  = new GetterAndSetter();

		Document docHtml = null;

		docHtml = Jsoup.parse(htmlRel, "UTF-8").clone();



		// dados documento
		if (!(documento == null)) {

			String strPosicoesDocumento [] = {
					"doc_num_tag",
			"doc_data_tag"	};

			// formatador
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			// string com a data:  Terça-feira, 2 de Abril de 2019
			String dataExtenso = formatador.format(documento.getDocDataCriacao());

			System.out.println(dataExtenso);

			// retirar o dia da semana:  2 de Abril de 2019
			int index  = dataExtenso.indexOf(","); // inicio da substring

			System.out.println(dataExtenso.substring(index + 2));

			String strDocumento [] = {
					documento.getDocNumeracao(),
					dataExtenso.substring(index + 2)	};

			for (int i  = 0; i<strPosicoesDocumento.length; i++) {

				try { docHtml.select(strPosicoesDocumento[i]).prepend(strDocumento[i]);} 
				catch (Exception e) {docHtml.select(strPosicoesDocumento[i]).prepend("");};
			}


		} // fim if documento null


		// dados do usuario
		if (usuario.getUsNome() != null ) {

			String strPosicoesUsuario [] = {

					"us_nome_tag",
					"us_cpfcnpj_tag",
					"us_tel_tag",
					"us_cel_tag",
					"us_end_cor_tag",
					"us_cep_tag",
					"us_email_tag"
			};

			String strUsuario [] = {
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

		}


		/*
		 * dados do endereco do empreendimento
		 */

		if (endereco.getEndLogradouro() != null) {

			String strPosicoesEndereco [] = {

					"end_log_tag",
					"end_ra_tag",
					"end_cep_tag",

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

		}


		/*
		 * dados da interferencia
		 */

		// SUPERFICIAL //
		if ( interferencia.getInterTipoInterferenciaFK().getTipoInterID() == 1) {

			// FINALIDADE REQUERIDA //
			FinalidadeRequerida fr = new FinalidadeRequerida();

			for (Finalidade f : ((Superficial) interferencia).getFinalidades() ) {

				if (f.getClass().getName() == "entidades.FinalidadeRequerida") {
					fr = (FinalidadeRequerida) f;
					System.out.println("MALA DIRETA superfi - finalidade requerida ID " + fr.getFinID());
				}

			}

			for (int i = 0; i<5; i++) {

				listFinalidadesCadastradas.add(gs.callGetter(fr, listVariaveisFinalidades.get(i)));
				listSubfinalidadesCadastradas.add(gs.callGetter(fr, listVariaveisSubfinaldades.get(i)));
				listQuantidadesCadastradas.add(gs.callGetter(fr, listVariaveisQuantidades.get(i)));
				listConsumosCadastrados.add(gs.callGetter(fr, listVariaveisConsumo.get(i)));
				listVazoesCadastradas.add(gs.callGetter(fr, listVariaveisVazao.get(i)));

			}

			for (int i = 0; i<12; i++) {

				listVazoesDia.add(gs.callGetter(fr, listVariaveisVazaoMes.get(i))); //.getIntSupFK()
				listVazoesHora.add(gs.callGetter(fr, listVariaveisVazaoHora.get(i)));
				listPeriodos.add(gs.callGetter(fr, listVariaveisTempo.get(i)));

			}


			String strPosicoesRequerimentoSuperificial  [] = {

					"inter_tipo_outorga_tag", 
					"inter_lat_tag", 
					"inter_lon_tag",

					"caesb_tag", 
					"local_cap_tag", 
					"cor_hid_tag", 

					"for_capt_tag", 
					"marca_bomba_tag",
					"pot_bomba_tag",

					"tempo_cap_tag",
					"vazao_bomba_tag",
					"data_oper_tag",


			};

			String strDataOperação;
			try {
				strDataOperação = new SimpleDateFormat("dd/MM/yyyy").format(((Superficial) interferencia).getSupDataOperacao());
			}
			catch (Exception e) {strDataOperação = null;
			};

			String strInterferenciaSuperficial [] = {

					interferencia.getInterTipoOutorgaFK().getTipoOutorgaDescricao(), 
					interferencia.getInterDDLatitude().toString() + ",",
					interferencia.getInterDDLongitude().toString(),

					((Superficial) interferencia).getSupCaesb(), //inter.getIntSupFK()
					((Superficial) interferencia).getSupLocalCaptacaoFK().getLocalCaptacaoDescricao(),
					((Superficial) interferencia).getSupCorpoHidrico(),

					((Superficial) interferencia).getSupFormaCaptacaoFK().getFormaCaptacaoDescricao(),
					((Superficial) interferencia).getSupMarcaBomba(),
					((Superficial) interferencia).getSupPotenciaBomba(),

					//String.valueOf(((Superficial) interferencia).getSupTempoCapJul()),
					//"-- vazao da bomba-- ",
					strDataOperação

			};

			for (int i  = 0; i<strPosicoesRequerimentoSuperificial.length; i++) {

				try { docHtml.select(strPosicoesRequerimentoSuperificial[i]).prepend(strInterferenciaSuperficial[i]);} 
				catch (Exception e) {docHtml.select(strPosicoesRequerimentoSuperificial[i]).prepend("");};
			}

			try { docHtml.select("vazaototaltag").prepend(String.valueOf(fr.getFrVazaoTotal()));} catch (Exception e) {docHtml.select("vazaototaltag").prepend("");};	
		
		} // FIM IF SUPERFICIAL

		// SUBTERRANEA //
		if ( interferencia.getInterTipoInterferenciaFK().getTipoInterID() == 2) {


			// FINALIDADE REQUERIDA //
			FinalidadeRequerida fr = new FinalidadeRequerida();

			for (Finalidade f : ((Subterranea) interferencia).getFinalidades() ) {

				if (f.getClass().getName() == "entidades.FinalidadeRequerida") {
					fr = (FinalidadeRequerida) f;
					System.out.println("MALA DIRETA - sub - finalidade requerida ID " + fr.getFinID());
				}

			}

			for (int i = 0; i<5; i++) {

				listFinalidadesCadastradas.add(gs.callGetter(fr, listVariaveisFinalidades.get(i))); 
				listSubfinalidadesCadastradas.add(gs.callGetter(fr, listVariaveisSubfinaldades.get(i)));
				listQuantidadesCadastradas.add(gs.callGetter(fr, listVariaveisQuantidades.get(i)));
				listConsumosCadastrados.add(gs.callGetter(fr, listVariaveisConsumo.get(i)));
				listVazoesCadastradas.add(gs.callGetter(fr, listVariaveisVazao.get(i)));

			}


			for (int i = 0; i<12; i++) {

				listVazoesDia.add(gs.callGetter(fr, listVariaveisVazaoMes.get(i)));

				listVazoesHora.add(gs.callGetter(fr, listVariaveisVazaoHora.get(i)));

				listPeriodos.add(gs.callGetter(fr, listVariaveisTempo.get(i)));

			}

			String strPosicoesRequerimentoSubterranea  [] = {

					"inter_tipo_outorga_tag", 
					"inter_tipo_poco_tag", //  <inter_tipo_poco_tag></inter_tipo_poco_tag>
					"inter_caesb_tag", 
					"inter_vazao_tag", 
					"inter_nivel_est_tag", 
					"inter_niv_din_tag", 
					"inter_prof_tag", 
					"inter_lat_tag", 
					"inter_lon_tag", 
					"inter_data_oper_tag",

			};

			String strDataOperação;
			try {
				strDataOperação = new SimpleDateFormat("dd/MM/yyyy").format(((Subterranea) interferencia).getSubDataOperacao());}
			catch (Exception e) {strDataOperação = null;};

			String strInterferenciaSubterranea [] = {
					interferencia.getInterTipoOutorgaFK().getTipoOutorgaDescricao(), 
					((Subterranea) interferencia).getSubTipoPocoFK().getTipoPocoDescricao(), 
					((Subterranea) interferencia).getSubCaesb(),
					((Subterranea) interferencia).getSubVazao(),
					((Subterranea) interferencia).getSubEstatico(),
					((Subterranea) interferencia).getSubDinamico(),
					((Subterranea) interferencia).getSubProfundidade(),
					interferencia.getInterDDLatitude().toString() + ",",
					interferencia.getInterDDLongitude().toString(),
					strDataOperação


			};

			for (int i  = 0; i<strPosicoesRequerimentoSubterranea.length; i++) {

				try { docHtml.select(strPosicoesRequerimentoSubterranea[i]).prepend(strInterferenciaSubterranea[i]);} 
				catch (Exception e) {docHtml.select(strPosicoesRequerimentoSubterranea[i]).prepend("");};
			}

			try { docHtml.select("vazaototaltag").prepend(String.valueOf(fr.getFrVazaoTotal()));} catch (Exception e) {docHtml.select("vazaototaltag").prepend("");};

		} // FIM IF SUBTERRANEA


			// dados da captacao - vazao listros dia
		String strListrosDiaTag [] = {

				"listros_dia_jan_tag",
				"listros_dia_fev_tag",
				"listros_dia_mar_tag",
				"listros_dia_abr_tag",
				"listros_dia_mai_tag",
				"listros_dia_jun_tag",
				"listros_dia_jul_tag",
				"listros_dia_ago_tag",
				"listros_dia_set_tag",
				"listros_dia_out_tag",
				"listros_dia_nov_tag",
				"listros_dia_dez_tag"

		};

		String strListrosHoraTag [] = {
				
				// <litros_hora_jan_tag></litros_hora_jan_tag>

				"litros_hora_jan_tag", 
				"litros_hora_fev_tag",
				"litros_hora_mar_tag",
				"litros_hora_abr_tag",
				"litros_hora_mai_tag",
				"litros_hora_jun_tag",
				"litros_hora_jul_tag",
				"litros_hora_ago_tag",
				"litros_hora_set_tag",
				"litros_hora_out_tag",
				"litros_hora_nov_tag",
				"litros_hora_dez_tag"

		};

		String strPeriodoMesTag [] = {

				// <periodo_mes_jan_tag></periodo_mes_jan_tag>
				
				"periodo_mes_jan_tag",
				"periodo_mes_fev_tag",
				"periodo_mes_mar_tag",
				"periodo_mes_abr_tag",
				"periodo_mes_mai_tag",
				"periodo_mes_jun_tag",
				"periodo_mes_jul_tag",
				"periodo_mes_ago_tag",
				"periodo_mes_set_tag",
				"periodo_mes_out_tag",
				"periodo_mes_nov_tag",
				"periodo_mes_dez_tag"

		};



		for (int i = 0; i<5; i++) {

			switch (listFinalidadesCadastradas.get(i)) {

			case "Abastecimento Humano": 

				/*
				 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
				 */

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

				System.out.println(strAbasHum);

				try { docHtml.select("abast_hum_tag").prepend(String.valueOf(strAbasHum));} 
				catch (Exception e) {docHtml.select("abast_hum_tag").prepend("erro");};


				break;

			case "Criação De Animais": 

				/*
				 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
				 */

				StringBuilder strCriacaoAnimais = new StringBuilder();

				strCriacaoAnimais
				.append("<strong style='font-style: italic; font-size: 12px;'>- CRIA&Ccedil;&Atilde;O DE ANIMAIS</strong>"
						+ "<table border='1' cellspacing='0' style='width: 800px;'>"
						+ "<tbody><tr><td colspan='2'>Esp&eacute;cie:&nbsp;")                         // especie
				.append(listFinalidadesCadastradas.get(i))
				.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>Total:&nbsp;")   // total
				.append(listVazoesCadastradas.get(i))
				.append("</span></td></tr><tr><td colspan='1'>Quantidade:&nbsp;")          // quantidade
				.append(listQuantidadesCadastradas.get(i))
				.append("</td><td colspan='1'>Consumo:&nbsp;")                    // consumo
				.append(listConsumosCadastrados.get(i));

				System.out.println(strCriacaoAnimais);

				try { docHtml.select("cria_anim_tag").prepend(String.valueOf(strCriacaoAnimais));} 
				catch (Exception e) {docHtml.select("cria_anim_tag").prepend("erro");};

				break;	


			case "Irrigação": 

				/*
				 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
				 */

				StringBuilder strIrrigacao = new StringBuilder();

				strIrrigacao
				.append("<strong style='font-style: italic; font-size: 12px;'>- IRRIGA&Ccedil;&Atilde;O</strong>" + 
						"<table border='1' cellspacing='0' style='width: 800px;'>")
				.append("<tbody><tr><td colspan='2'>Esp&eacute;cie:&nbsp;") //  cultura
				.append(listSubfinalidadesCadastradas.get(i))
				.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>Total:&nbsp;") // total
				.append(listVazoesCadastradas.get(i))
				.append("</span></td></tr><tr><td colspan='1'>Quantidade:&nbsp;") // quantidade
				.append(listQuantidadesCadastradas.get(i))
				.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
				.append(listConsumosCadastrados.get(i))
				.append("</td></tr></tbody></table>");


				try { docHtml.select("irrig_tag").prepend(String.valueOf(strIrrigacao));} 
				catch (Exception e) {docHtml.select("irrig_tag").prepend("erro");};

				break;	

			case "Uso Industrial": 

				/*
				 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
				 */

				StringBuilder strUsoIndustrial = new StringBuilder();

				strUsoIndustrial
				.append("<strong style='font-style: italic; font-size: 12px;'>- IND&Uacute;STRIA</strong>" + 
						"<table border='1' cellspacing='0' style='width: 800px;'>")
				.append("<tbody><tr><td colspan='2'>Produto:&nbsp;") //  produto
				.append(listSubfinalidadesCadastradas.get(i))
				.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>Total:&nbsp;") // total
				.append(listVazoesCadastradas.get(i))
				.append("</span></td></tr><tr><td colspan='1'>Produ&ccedil;&atilde;o:&nbsp;&nbsp;") // producao
				.append(listQuantidadesCadastradas.get(i))
				.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
				.append(listConsumosCadastrados.get(i))
				.append("</td></tr></tbody></table>");

				try { docHtml.select("uso_ind_tag").prepend(String.valueOf(strUsoIndustrial));} 
				catch (Exception e) {docHtml.select("uso_ind_tag").prepend("erro");};

				break;	

			case "Outras Finalidades": 

				/*
				 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
				 */

				StringBuilder strOutrasFinalidades= new StringBuilder();

				strOutrasFinalidades
				.append("<strong style='font-style: italic; font-size: 12px;'>- OUTRAS FINALIDADES</strong>" + 
						"<table border='1' cellspacing='0' style='width: 800px;'>")
				.append("<tbody><tr><td colspan='2'>Produto:&nbsp;") //  produto
				.append(listSubfinalidadesCadastradas.get(i))
				.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>Total:&nbsp;") // total
				.append(listVazoesCadastradas.get(i))
				.append("</span></td></tr><tr><td colspan='1'>Produ&ccedil;&atilde;o:&nbsp;&nbsp;") // producao
				.append(listQuantidadesCadastradas.get(i))
				.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
				.append(listConsumosCadastrados.get(i))
				.append("</td></tr></tbody></table>");


				try { docHtml.select("outras_fin_tag").prepend(String.valueOf(strOutrasFinalidades));} 
				catch (Exception e) {docHtml.select("outras_fin_tag").prepend("erro");};
				break;	

			}

			// uso_comer_tag
		}



		/*
		 * preenchimento das vazoes litros/dia litros/hora e periodos/mes
		 */
		for (int i = 0; i<12; i++) {

			try { docHtml.select(strListrosDiaTag[i]).prepend(String.valueOf(listVazoesDia.get(i)));} catch (Exception e) {docHtml.select(strListrosDiaTag[i]).prepend("");};
			try { docHtml.select(strListrosHoraTag[i]).prepend(String.valueOf(listVazoesHora.get(i)));} catch (Exception e) {docHtml.select(strListrosHoraTag [i]).prepend("");};
			try { docHtml.select(strPeriodoMesTag[i]).prepend(String.valueOf(listPeriodos.get(i)));} catch (Exception e) {docHtml.select(strPeriodoMesTag [i]).prepend("");};

		}

		String html = new String ();

		html = docHtml.toString();

		html = html.replace("\"", "'");
		html = html.replace("\n", "");

		html =  "\"" + html + "\"";

		return html;

	}




}
