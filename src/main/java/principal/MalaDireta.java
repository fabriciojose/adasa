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
	List<String> supplierNames;
		
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
											"us_email_tag"};
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
	 					catch (Exception e) {docHtml.select(strPosicoesUsuario[i]).prepend("");};
	 			}
			
		}
		
		
		/*
		 * dados do endereco
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
	 					catch (Exception e) {docHtml.select(strPosicoesEndereco[i]).prepend("");};
	 			}
		
		}
		
		
		/*
		 * dados da interferencia
		 */
		
		// SUPERFICIAL //
		if ( interferencia.getInterTipoInterferenciaFK().getTipoInterID() == 1) {
			
			listVariaveisFinalidades = Arrays.asList("supFinalidade1", "supFinalidade2", "supFinalidade3", "supFinalidade4", "supFinalidade5");
			listVariaveisSubfinaldades = Arrays.asList("supSubfinalidade1", "supSubfinalidade2", "supSubfinalidade3", "supSubfinalidade4", "supSubfinalidade5");
			listVariaveisQuantidades = Arrays.asList("supQuantidade1", "supQuantidade2", "supQuantidade3", "supQuantidade4", "supQuantidade5");
			listVariaveisConsumo = Arrays.asList("supConsumo1", "supConsumo2", "supConsumo3", "supConsumo4", "supConsumo5");
			listVariaveisVazao = Arrays.asList("supVazao1", "supVazao2", "supVazao3", "supVazao4", "supVazao5");
			
			
			System.out.println(listVariaveisFinalidades.get(0));
			
			listFinalidadesCadastradas.clear();
			listSubfinalidadesCadastradas.clear();
			listQuantidadesCadastradas.clear();
			listConsumosCadastrados.clear();
			listVazoesCadastradas.clear();
			
			for (int i = 0; i<5; i++) {
				
				listFinalidadesCadastradas.add(gs.callGetter(((Superficial) interferencia), listVariaveisFinalidades.get(i)));
				listSubfinalidadesCadastradas.add(gs.callGetter(((Superficial) interferencia), listVariaveisSubfinaldades.get(i)));
				listQuantidadesCadastradas.add(gs.callGetter(((Superficial) interferencia), listVariaveisQuantidades.get(i)));
				listConsumosCadastrados.add(gs.callGetter(((Superficial) interferencia), listVariaveisConsumo.get(i)));
				listVazoesCadastradas.add(gs.callGetter(((Superficial) interferencia), listVariaveisVazao.get(i)));
				
			}

			// variaveis de vazao mensal e reflexao //
			listVariaveisVazaoMes = Arrays.asList(
					
					"supQDiaJan","supQDiaFev","supQDiaMar","supQDiaAbr","supQDiaMai","supQDiaJun",
					"supQDiaJul","supQDiaAgo","supQDiaSet","supQDiaOut","supQDiaNov","supQDiaDez"	
					
					);
			
					listVariaveisVazaoHora = Arrays.asList(
							
							"supQHoraJan","supQHoraFev","supQHoraMar","supQHoraAbr","supQHoraMai","supQHoraJun",
							"supQHoraJul","supQHoraAgo","supQHoraSet","supQHoraOut","supQHoraNov","supQHoraDez"
		
							);
			
							listVariaveisTempo = Arrays.asList(
								
									"supTempoCapJan","supTempoCapFev","supTempoCapMar","supTempoCapAbr","supTempoCapMai","supTempoCapJun",
									"supTempoCapJul","supTempoCapAgo","supTempoCapSet","supTempoCapOut","supTempoCapNov","supTempoCapDez"
				
									);
			
							listVazoesDia.clear();
							listVazoesHora.clear();
							listPeriodos.clear();
							
							
							for (int i = 0; i<12; i++) {
								
								listVazoesDia.add(gs.callGetter(((Superficial) interferencia), listVariaveisVazaoMes.get(i))); //.getIntSupFK()
								listVazoesHora.add(gs.callGetter(((Superficial) interferencia), listVariaveisVazaoHora.get(i)));
								listPeriodos.add(gs.callGetter(((Superficial) interferencia), listVariaveisTempo.get(i)));
								
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
            	strDataOperação = new SimpleDateFormat("dd/MM/yyyy").format(((Superficial) interferencia).getSupDataOperacao());}
				catch (Exception e) {strDataOperação = null;};
            			
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
	            										
	            											String.valueOf(((Superficial) interferencia).getSupTempoCapJul()),
	            												"-- vazao da bomba-- ",
	            													strDataOperação
	            											
	  					};
            
	             for (int i  = 0; i<strPosicoesRequerimentoSuperificial.length; i++) {
	 				
	 				try { docHtml.select(strPosicoesRequerimentoSuperificial[i]).prepend(strInterferenciaSuperficial[i]);} 
	 					catch (Exception e) {docHtml.select(strPosicoesRequerimentoSuperificial[i]).prepend("");};
	 			}
			
	    try { docHtml.select("vazaototaltag").prepend(String.valueOf(((Superficial) interferencia).getSupVazaoTotal()));} catch (Exception e) {docHtml.select("vazaototaltag").prepend("");};	
		} 
		
		 // SUBTERRANEA //
		if ( interferencia.getInterTipoInterferenciaFK().getTipoInterID() == 2) {
			
			listVariaveisFinalidades = Arrays.asList("subFinalidade1", "subFinalidade2", "subFinalidade3", "subFinalidade4", "subFinalidade5");
			listVariaveisSubfinaldades = Arrays.asList("subSubfinalidade1", "subSubfinalidade2", "subSubfinalidade3", "subSubfinalidade4", "subSubfinalidade5");
			listVariaveisQuantidades = Arrays.asList("subQuantidade1", "subQuantidade2", "subQuantidade3", "subQuantidade4", "subQuantidade5");
			listVariaveisConsumo = Arrays.asList("subConsumo1", "subConsumo2", "subConsumo3", "subConsumo4", "subConsumo5");
			listVariaveisVazao = Arrays.asList("subVazao1", "subVazao2", "subVazao3", "subVazao4", "subVazao5");
			
			listFinalidadesCadastradas.clear();
			listSubfinalidadesCadastradas.clear();
			listQuantidadesCadastradas.clear();
			listConsumosCadastrados.clear();
			listVazoesCadastradas.clear();
			
			for (int i = 0; i<5; i++) {
				
				listFinalidadesCadastradas.add(gs.callGetter(((Subterranea) interferencia), listVariaveisFinalidades.get(i))); 
				listSubfinalidadesCadastradas.add(gs.callGetter(((Subterranea) interferencia), listVariaveisSubfinaldades.get(i)));
				listQuantidadesCadastradas.add(gs.callGetter(((Subterranea) interferencia), listVariaveisQuantidades.get(i)));
				listConsumosCadastrados.add(gs.callGetter(((Subterranea) interferencia), listVariaveisConsumo.get(i)));
				listVazoesCadastradas.add(gs.callGetter(((Subterranea) interferencia), listVariaveisVazao.get(i)));
				
			}
			
			// variaveis de vazao mensal e reflexao //
			listVariaveisVazaoMes = Arrays.asList(
					
					"subQDiaJan","subQDiaFev","subQDiaMar","subQDiaAbr","subQDiaMai","subQDiaJun",
					"subQDiaJul","subQDiaAgo","subQDiaSet","subQDiaOut","subQDiaNov","subQDiaDez"	
					
					);
			
					listVariaveisVazaoHora = Arrays.asList(
							
							"subQHoraJan","subQHoraFev","subQHoraMar","subQHoraAbr","subQHoraMai","subQHoraJun",
							"subQHoraJul","subQHoraAgo","subQHoraSet","subQHoraOut","subQHoraNov","subQHoraDez"
		
							);
			
							listVariaveisTempo = Arrays.asList(
								
									"subTempoCapJan","subTempoCapFev","subTempoCapMar","subTempoCapAbr","subTempoCapMai","subTempoCapJun",
									"subTempoCapJul","subTempoCapAgo","subTempoCapSet","subTempoCapOut","subTempoCapNov","subTempoCapDez"
				
									);
							
							listVazoesDia.clear();
							listVazoesHora.clear();
							listPeriodos.clear();
							
							for (int i = 0; i<12; i++) {
								
								listVazoesDia.add(gs.callGetter(((Subterranea) interferencia), listVariaveisVazaoMes.get(i)));
								
								listVazoesHora.add(gs.callGetter(((Subterranea) interferencia), listVariaveisVazaoHora.get(i)));
								
								listPeriodos.add(gs.callGetter(((Subterranea) interferencia), listVariaveisTempo.get(i)));
								
							}
							
             String strPosicoesRequerimentoSubterranea  [] = {
      
            		 "inter_tipo_outorga_tag", 
            		 	"inter_tipocaptacao_tag", 
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
			
	     try { docHtml.select("vazaototaltag").prepend(String.valueOf(((Subterranea) interferencia).getSubVazaoTotal()));} catch (Exception e) {docHtml.select("vazaototaltag").prepend("");};
	     
		} 
		
		
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
						.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;")   // total
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
						.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
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
						.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
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
						.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
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
		
		
		
		/*
		String strAbasHum [] = 
				
				"<div align='justify'><strong style='font-style: italic; font-size: 12px;'>- ABASTECIMENTO HUMANO</strong>"
						+	"<table border='1' cellspacing='0' style='width: 800px;'><tbody><tr><td colspan='1'>"
						+ 	"Popula&ccedil;&atilde;o:&nbsp;";
					
						
		String strConsTag = 
				"</td><td colspan='1'>Consumo diário por habitante:&nbsp;";
		
		String strTotTag = "</td><td colspan='1' width='20%'>Total:";
		
		String strFecTag = "</td></tr></tbody></table>";
		

		/*
		 	<ahpopulatag></abasthumtab><interpopulacao></interpopulacao>

			<ahconstag></ahcons><intercons></intercons>

			<ahtotaltag></ahtotaltag><intertotal></intertotal>
			
			<ahfechartab></ahfechartab>
			
			ahabasthumanotag
		
		 */
		
		/*
		 String strPosicoesAbasHum  [] = {
			      
        		 "ahpopulatag", 
        		 	"interpopulacao", 
        		 			"ahconstag", 
        		 				"intercons", 
        		 						"ahtotaltag", 
        		 							"intertotal", 
        		 										"ahfechartab"
        		 };
				 
				 String strAbasHum [] = {
						 strPopTag, 
		            			String.valueOf(inter.getIntSubFK().getSubSubfinQuan1())  , 
		            				strConsTag,
		            					String.valueOf(inter.getIntSubFK().getSubSubfinCon1()),
		            						strTotTag,
		            						String.valueOf(inter.getIntSubFK().getSubVazao1())
		  					};
		  					*/
		
		/*
		try { docHtml.select("tipoPoco").prepend(outorga.getTipoPoco());} catch (Exception e) {docHtml.select("tipoPoco").prepend("");};
		
		try { docHtml.select("finalidade1").prepend(outorga.getFinalidade()[0]);} catch (Exception e) {docHtml.select("finalidade1").prepend("");};
		try { docHtml.select("finalidade2").prepend(outorga.getFinalidade()[1]);} catch (Exception e) {docHtml.select("finalidade2").prepend("");};
		try { docHtml.select("finalidade3").prepend(outorga.getFinalidade()[2]);} catch (Exception e) {docHtml.select("finalidade3").prepend("");};
		try { docHtml.select("finalidade4").prepend(outorga.getFinalidade()[3]);} catch (Exception e) {docHtml.select("finalidade4").prepend("");};
		try { docHtml.select("finalidade5").prepend(outorga.getFinalidade()[4]);} catch (Exception e) {docHtml.select("finalidade5").prepend("");};
		
		try { docHtml.select("processo").prepend(outorga.getProcesso());} catch (Exception e) {docHtml.select("processo").prepend("");};
		
		
		try { docHtml.select("cpfCNPJ").prepend(outorga.getCpfCNPJ());} catch (Exception e) {docHtml.select("cpfCNPJ").prepend("");};
		try { docHtml.select("endereco").prepend(outorga.getEndereco());} catch (Exception e) {docHtml.select("endereco").prepend("");};
		
		try { docHtml.select("baciaHid").prepend(outorga.getBacia());} catch (Exception e) {docHtml.select("baciaHid").prepend("");};
		try { docHtml.select("baciaUH").prepend(outorga.getUh());} catch (Exception e) {docHtml.select("baciaUH").prepend("");};
		
		// coordenadas
		try { docHtml.select("pontoCapLat").prepend(outorga.getLat().toString());} catch (Exception e) {docHtml.select("pontoCapLat").prepend("");};
		try { docHtml.select("pontoCapLng").prepend(outorga.getLng().toString());} catch (Exception e) {docHtml.select("pontoCapLng").prepend("");};
		
		
		// VAZAO LITROS HORA
		try { docHtml.select("vazJanLH").prepend(String.valueOf(outorga.getVazaoHora()[0]));} catch (Exception e) {docHtml.select("vazJanLH").prepend("");};
		try { docHtml.select("vazFevLH").prepend(String.valueOf(outorga.getVazaoHora()[1]));} catch (Exception e) {docHtml.select("vazFevLH").prepend("");};
		try { docHtml.select("vazMarLH").prepend(String.valueOf(outorga.getVazaoHora()[2]));} catch (Exception e) {docHtml.select("vazMarLH").prepend("");};
		try { docHtml.select("varAbrLH").prepend(String.valueOf(outorga.getVazaoHora()[3]));} catch (Exception e) {docHtml.select("varAbrLH").prepend("");};
		try { docHtml.select("vazMaiLH").prepend(String.valueOf(outorga.getVazaoHora()[4]));} catch (Exception e) {docHtml.select("vazMaiLH").prepend("");};
		try { docHtml.select("vazJunLH").prepend(String.valueOf(outorga.getVazaoHora()[5]));} catch (Exception e) {docHtml.select("vazJunLH").prepend("");};
		
		try { docHtml.select("vazJulLH").prepend(String.valueOf(outorga.getVazaoHora()[6]));} catch (Exception e) {docHtml.select("vazJulLH").prepend("");};
		try { docHtml.select("vazAgoLH").prepend(String.valueOf(outorga.getVazaoHora()[7]));} catch (Exception e) {docHtml.select("vazAgoLH").prepend("");};
		try { docHtml.select("vazSetLH").prepend(String.valueOf(outorga.getVazaoHora()[8]));} catch (Exception e) {docHtml.select("vazSetLH").prepend("");};
		try { docHtml.select("vazOutLH").prepend(String.valueOf(outorga.getVazaoHora()[9]));} catch (Exception e) {docHtml.select("vazOutLH").prepend("");};
		try { docHtml.select("vazNovLH").prepend(String.valueOf(outorga.getVazaoHora()[10]));} catch (Exception e) {docHtml.select("vazNovLH").prepend("");};
		try { docHtml.select("vazDezLH").prepend(String.valueOf(outorga.getVazaoHora()[11]));} catch (Exception e) {docHtml.select("vazDezLH").prepend("");};
		
		// TEMPO DE CAPTACAO
		try { docHtml.select("tJAN").prepend(String.valueOf(outorga.getTempoCaptacao()[0]));} catch (Exception e) {docHtml.select("tJAN").prepend("");};
		try { docHtml.select("tFEV").prepend(String.valueOf(outorga.getTempoCaptacao()[1]));} catch (Exception e) {docHtml.select("tFEV").prepend("");};
		try { docHtml.select("tMAR").prepend(String.valueOf(outorga.getTempoCaptacao()[2]));} catch (Exception e) {docHtml.select("tMAR").prepend("");};
		try { docHtml.select("tABR").prepend(String.valueOf(outorga.getTempoCaptacao()[3]));} catch (Exception e) {docHtml.select("tABR").prepend("");};
		try { docHtml.select("tMAI").prepend(String.valueOf(outorga.getTempoCaptacao()[4]));} catch (Exception e) {docHtml.select("tMAI").prepend("");};
		try { docHtml.select("tJUN").prepend(String.valueOf(outorga.getTempoCaptacao()[5]));} catch (Exception e) {docHtml.select("tJUN").prepend("");};
		
		try { docHtml.select("tJUL").prepend(String.valueOf(outorga.getTempoCaptacao()[6]));} catch (Exception e) {docHtml.select("tJUL").prepend("");};
		try { docHtml.select("tAGO").prepend(String.valueOf(outorga.getTempoCaptacao()[7]));} catch (Exception e) {docHtml.select("tAGO").prepend("");};
		try { docHtml.select("tSET").prepend(String.valueOf(outorga.getTempoCaptacao()[8]));} catch (Exception e) {docHtml.select("tSET").prepend("");};
		try { docHtml.select("tOUT").prepend(String.valueOf(outorga.getTempoCaptacao()[9]));} catch (Exception e) {docHtml.select("tOUT").prepend("");};
		try { docHtml.select("tNOV").prepend(String.valueOf(outorga.getTempoCaptacao()[10]));} catch (Exception e) {docHtml.select("tNOV").prepend("");};
		try { docHtml.select("tDEZ").prepend(String.valueOf(outorga.getTempoCaptacao()[11]));} catch (Exception e) {docHtml.select("tDEZ").prepend("");};
		
		
		// VAZAO LITROS DIA
		try { docHtml.select("vazJanLD").prepend(String.valueOf(outorga.getVazaoDia()[0]));} catch (Exception e) {docHtml.select("vazJanLD").prepend("");};
		try { docHtml.select("vazFevLD").prepend(String.valueOf(outorga.getVazaoDia()[1]));} catch (Exception e) {docHtml.select("vazFevLD").prepend("");};
		try { docHtml.select("vazMarLD").prepend(String.valueOf(outorga.getVazaoDia()[2]));} catch (Exception e) {docHtml.select("vazMarLD").prepend("");};
		try { docHtml.select("varAbrLD").prepend(String.valueOf(outorga.getVazaoDia()[3]));} catch (Exception e) {docHtml.select("varAbrLD").prepend("");};
		try { docHtml.select("vazMaiLD").prepend(String.valueOf(outorga.getVazaoDia()[4]));} catch (Exception e) {docHtml.select("vazMaiLD").prepend("");};
		try { docHtml.select("vazJunLD").prepend(String.valueOf(outorga.getVazaoDia()[5]));} catch (Exception e) {docHtml.select("vazJunLD").prepend("");};
		
		try { docHtml.select("vazJulLD").prepend(String.valueOf(outorga.getVazaoDia()[6]));} catch (Exception e) {docHtml.select("vazJulLD").prepend("");};
		try { docHtml.select("vazAgoLD").prepend(String.valueOf(outorga.getVazaoDia()[7]));} catch (Exception e) {docHtml.select("vazAgoLD").prepend("");};
		try { docHtml.select("vazSetLD").prepend(String.valueOf(outorga.getVazaoDia()[8]));} catch (Exception e) {docHtml.select("vazSetLD").prepend("");};
		try { docHtml.select("vazOutLD").prepend(String.valueOf(outorga.getVazaoDia()[9]));} catch (Exception e) {docHtml.select("vazOutLD").prepend("");};
		try { docHtml.select("vazNovLD").prepend(String.valueOf(outorga.getVazaoDia()[10]));} catch (Exception e) {docHtml.select("vazNovLD").prepend("");};
		try { docHtml.select("vazDezLD").prepend(String.valueOf(outorga.getVazaoDia()[11]));} catch (Exception e) {docHtml.select("vazDezLD").prepend("");};
		
		try { docHtml.select("subsistema").prepend(outorga.getSubsistema());} catch (Exception e) {docHtml.select("subsistema").prepend("");};
		
		try { docHtml.select("vazaoMedia").prepend(String.format("%.2f", outorga.getVazaoMedia()));} catch (Exception e) {docHtml.select("vazaoMedia").prepend("");};
		try { docHtml.select("vazaoBombeamento").prepend(String.format("%.2f", outorga.getVazaoBombeamento()));} catch (Exception e) {docHtml.select("vazaoBombeamento").prepend("");};
		try { docHtml.select("profundidade").prepend(String.format("%.2f", outorga.getProfundidade()));} catch (Exception e) {docHtml.select("profundidade").prepend("");};
		try { docHtml.select("nivelEstatico").prepend(String.format("%.2f", outorga.getNivelEstatico()));} catch (Exception e) {docHtml.select("nivelEstatico").prepend("");};
		try { docHtml.select("nivelDinamico").prepend(String.format("%.2f", outorga.getNivelDinamico()));} catch (Exception e) {docHtml.select("nivelDinamico").prepend("");};
		
		// SUBFINALIDADE
		try { docHtml.select("subfinalidade1").prepend(outorga.getSubfinalidade()[0]);} catch (Exception e) {docHtml.select("subfinalidade1").prepend("");};
		try { docHtml.select("subfinalidade2").prepend(outorga.getSubfinalidade()[1]);} catch (Exception e) {docHtml.select("subfinalidade2").prepend("");};
		try { docHtml.select("subfinalidade3").prepend(outorga.getSubfinalidade()[2]);} catch (Exception e) {docHtml.select("subfinalidade3").prepend("");};
		try { docHtml.select("subfinalidade4").prepend(outorga.getSubfinalidade()[3]);} catch (Exception e) {docHtml.select("subfinalidade4").prepend("");};
		try { docHtml.select("subfinalidade5").prepend(outorga.getSubfinalidade()[4]);} catch (Exception e) {docHtml.select("subfinalidade5").prepend("");};
		
		// DEMANDA 
		try { docHtml.select("demanda1").prepend(String.format("%.2f", outorga.getDemanda()[0]));} catch (Exception e) {docHtml.select("demanda1").prepend("");};
		try { docHtml.select("demanda2").prepend(String.format("%.2f", outorga.getDemanda()[1]));} catch (Exception e) {docHtml.select("demanda2").prepend("");};
		try { docHtml.select("demanda3").prepend(String.format("%.2f", outorga.getDemanda()[2]));} catch (Exception e) {docHtml.select("demanda3").prepend("");};
		try { docHtml.select("demanda4").prepend(String.format("%.2f", outorga.getDemanda()[3]));} catch (Exception e) {docHtml.select("demanda4").prepend("");};
		try { docHtml.select("demanda5").prepend(String.format("%.2f", outorga.getDemanda()[4]));} catch (Exception e) {docHtml.select("demanda5").prepend("");};
		
		//DEMANDA IN
		try { docHtml.select("demandaIN1").prepend(String.format("%.2f", outorga.getDemandaIN()[0]));} catch (Exception e) {docHtml.select("demandaIN1").prepend("");};
		try { docHtml.select("demandaIN2").prepend(String.format("%.2f", outorga.getDemandaIN()[1]));} catch (Exception e) {docHtml.select("demandaIN2").prepend("");};
		try { docHtml.select("demandaIN3").prepend(String.format("%.2f", outorga.getDemandaIN()[2]));} catch (Exception e) {docHtml.select("demandaIN3").prepend("");};
		try { docHtml.select("demandaIN4").prepend(String.format("%.2f", outorga.getDemandaIN()[3]));} catch (Exception e) {docHtml.select("demandaIN4").prepend("");};
		try { docHtml.select("demandaIN5").prepend(String.format("%.2f", outorga.getDemandaIN()[4]));} catch (Exception e) {docHtml.select("demandaIN5").prepend("");};
		
		// VAZAO EXPLOTAVEL, N POCOS ...
		
		
		
		try { docHtml.select("vazaoExplotavel").prepend(String.format("%.0f", outorga.getVazaoExplotavel()));} catch (Exception e) {docHtml.select("vazaoExplotavel").prepend("");};
		try { docHtml.select("numeroDePocos").prepend(String.valueOf(outorga.getNumeroDePocos()));} catch (Exception e) {docHtml.select("numeroDePocos").prepend("");};
		try { docHtml.select("vazaoTotalOutorgavel").prepend(String.format("%.0f", outorga.getVazaoTotalOutorgavel()));} catch (Exception e) {docHtml.select("vazaoTotalOutorgavel").prepend("");};
		try { docHtml.select("porcentagemUtilizada").prepend(String.format("%.2f", outorga.getPorcentagemUtilizada()));} catch (Exception e) {docHtml.select("porcentagemUtilizada").prepend("");};
		try { docHtml.select("volumeDisponivelAtual").prepend(String.format("%.0f", outorga.getVolumeDisponivelAtual()));} catch (Exception e) {docHtml.select("volumeDisponivelAtual").prepend("");};
		
		try { docHtml.select("volumeDiponivelSuficiente").prepend(outorga.getVolumeDiponivelSuficiente());} catch (Exception e) {docHtml.select("volumeDiponivelSuficiente").prepend("");};
	
		*/
		
		
		String html = new String ();
		
		html = docHtml.toString();
		
		html = html.replace("\"", "'");
		html = html.replace("\n", "");
		
		html =  "\"" + html + "\"";
		
		//-- webview do relat�rio --//
		/*
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
	    */
	    
		return html;
	    
	   // //TabNavegadorController.html = html;
	   // TabNavegadorController.numIframe = 1;

	}

	
	

}
