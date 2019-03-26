package entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="sub_Interferencia_FK")
public class Subterranea extends Interferencia {
	
	private static final long serialVersionUID = 7955498803306632784L;

		@ManyToOne (fetch = FetchType.EAGER) 
		@JoinColumn (name = "sub_Tipo_Poco_FK")
		private TipoPoco subTipoPocoFK; // Manual Tubular
		
			@ManyToOne (fetch = FetchType.EAGER) 
			@JoinColumn (name = "sub_Subsistema_FK")
			private SubSistema subSubSistemaFK;

	@Column (name="sub_Caesb", columnDefinition="varchar(3)")
	private String subCaesb;  // tem caesb () sim () não
	
	@Column (name="sub_Estatico", columnDefinition="varchar(5)")
	private String subEstatico;  // em metros
	
	@Column (name="sub_Dinamico", columnDefinition="varchar(5)")
	private String subDinamico;  // em metros
	
	@Column (name="sub_Vazao",columnDefinition="varchar(5)")
	private String subVazao;  // em l/h - litros por hora
	
	@Column (name="sub_Profundidade",columnDefinition="varchar(5)")
	private String subProfundidade;  // em metros
	
	@Basic
	@Column (name="sub_Data_Operacao")
	private java.sql.Date subDataOperacao;
	
	
	// 1 //
	@Column (name="sub_Finalidade_1",columnDefinition="varchar(250)")
	private String subFinalidade1;
	
	@Column (name="sub_Subfinalidade_1",columnDefinition="varchar(250)")
	private String subSubfinalidade1;
	
	@Column (name="sub_Quantidade_1")
	private Double subQuantidade1;
	
	@Column (name="sub_Consumo_1")
	private Double subConsumo1;
	
	@Column (name="sub_Vazao_1")
	private Double subVazao1;
	
		// 2 //
		@Column (name="sub_Finalidade_2",columnDefinition="varchar(250)")
		private String subFinalidade2;
		
		@Column (name="sub_Subfinalidade_2",columnDefinition="varchar(250)")
		private String subSubfinalidade2;
		
		@Column (name="sub_Quantidade_2")
		private Double subQuantidade2;
		
		@Column (name="sub_Consumo_2")
		private Double subConsumo2;
		
		@Column (name="sub_Vazao_2")
		private Double subVazao2;
		
			// 3 //
			@Column (name="sub_Finalidade_3",columnDefinition="varchar(350)")
			private String subFinalidade3;
			
			@Column (name="sub_Subfinalidade_3",columnDefinition="varchar(350)")
			private String subSubfinalidade3;
			
			@Column (name="sub_Quantidade_3")
			private Double subQuantidade3;
			
			@Column (name="sub_Consumo_3")
			private Double subConsumo3;
			
			@Column (name="sub_Vazao_3")
			private Double subVazao3;
			
				// 4 //
				@Column (name="sub_Finalidade_4",columnDefinition="varchar(450)")
				private String subFinalidade4;
				
				@Column (name="sub_Subfinalidade_4",columnDefinition="varchar(450)")
				private String subSubfinalidade4;
				
				@Column (name="sub_Quantidade_4")
				private Double subQuantidade4;
				
				@Column (name="sub_Consumo_4")
				private Double subConsumo4;
				
				@Column (name="sub_Vazao_4")
				private Double subVazao4;
					
					// 5 //
					@Column (name="sub_Finalidade_5",columnDefinition="varchar(550)")
					private String subFinalidade5;
					
					@Column (name="sub_Subfinalidade_5",columnDefinition="varchar(550)")
					private String subSubfinalidade5;
					
					@Column (name="sub_Quantidade_5")
					private Double subQuantidade5;
					
					@Column (name="sub_Consumo_5")
					private Double subConsumo5;
					
					@Column (name="sub_Vazao_5")
					private Double subVazao5;
			
	
	@Column (name="sub_Vazao_Total")
	private Double subVazaoTotal;
	
	// JANEIRO //
	@Column (name="sub_Q_Dia_Jan")
	private Double subQDiaJan;
	
	@Column (name="sub_Q_Hora_Jan")
	private int subQHoraJan;
	
	@Column (name="sub_Tempo_Cap_Jan")
	private int subTempoCapJan;
	
	// FEVEREIRO
	@Column (name="sub_Q_Dia_Fev")
	private Double subQDiaFev;
	
	@Column (name="sub_Q_Hora_Fev")
	private int subQHoraFev;
	
	@Column (name="sub_Tempo_Cap_Fev")
	private int subTempoCapFev;
	
	// MARCO //
	@Column (name="sub_Q_Dia_Mar")
	private Double subQDiaMar;
	
	@Column (name="sub_Q_Hora_Mar")
	private int subQHoraMar;
	
	@Column (name="sub_Tempo_Cap_Mar")
	private int subTempoCapMar;
	
	
	// ABRIL //
	@Column (name="sub_Q_Dia_Abr")
	private Double subQDiaAbr;
	
	@Column (name="sub_Q_Hora_Abr")
	private int subQHoraAbr;
	
	@Column (name="sub_Tempo_Cap_Abr")
	private int subTempoCapAbr;
	
	// MAIO //
	
	@Column (name="sub_Q_Dia_Mai")
	private Double subQDiaMai;
	
	@Column (name="sub_Q_Hora_Mai")
	private int subQHoraMai;
	
	@Column (name="sub_Tempo_Cap_Mai")
	private int subTempoCapMai;
	
	// JUNHO //
	
	@Column (name="sub_Q_Dia_Jun")
	private Double subQDiaJun;
	
	@Column (name="sub_Q_Hora_Jun")
	private int subQHoraJun;
	
	@Column (name="sub_Tempo_Cap_Jun")
	private int subTempoCapJun;
	
	// JULHO //
	@Column (name="sub_Q_Dia_Jul")
	private Double subQDiaJul;
	
	@Column (name="sub_Q_Hora_Jul")
	private int subQHoraJul;
	
	@Column (name="sub_Tempo_Cap_Jul")
	private int subTempoCapJul;
	
	
	// AGOSTO //
	@Column (name="sub_Q_Dia_Ago")
	private Double subQDiaAgo;
	
	@Column (name="sub_Q_Hora_Ago")
	private int subQHoraAgo;
	
	@Column (name="sub_Tempo_Cap_Ago")
	private int subTempoCapAgo;
	
	// SETEMBRO //
	@Column (name="sub_Q_Dia_Set")
	private Double subQDiaSet;
	
	@Column (name="sub_Q_Hora_Set")
	private int subQHoraSet;
	
	@Column (name="sub_Tempo_Cap_Set")
	private int subTempoCapSet;
	
	// OUTUBRO //
	@Column (name="sub_Q_Dia_Out")
	private Double subQDiaOut;
	
	@Column (name="sub_Q_Hora_Out")
	private int subQHoraOut;
	
	@Column (name="sub_Tempo_Cap_Out")
	private int subTempoCapOut;
	
	
	// NOVEMBRO //
	@Column (name="sub_Q_Dia_Nov")
	private Double subQDiaNov;
	
	@Column (name="sub_Q_Hora_Nov")
	private int subQHoraNov;
	
	@Column (name="sub_Tempo_Cap_Nov")
	private int subTempoCapNov;
	
	// DEZEMBRO //
	@Column (name="sub_Q_Dia_Dez")
	private Double subQDiaDez;
	
	@Column (name="sub_Q_Hora_Dez")
	private int subQHoraDez;
	
	@Column (name="sub_Tempo_Cap_Dez")
	private int subTempoCapDez;
	
	public Subterranea () {
		
	}

	//-- getters and setters --//
	
	public SubSistema getSubSubSistemaFK() {
		return subSubSistemaFK;
	}

	public java.sql.Date getSubDataOperacao() {
		return subDataOperacao;
	}


	public void setSubDataOperacao(java.sql.Date subDataOperacao) {
		this.subDataOperacao = subDataOperacao;
	}


	public void setSubSubSistemaFK(SubSistema subSubSistemaFK) {
		this.subSubSistemaFK = subSubSistemaFK;
	}

	public String getSubCaesb() {
		return subCaesb;
	}

	public void setSubCaesb(String subCaesb) {
		this.subCaesb = subCaesb;
	}

	public String getSubEstatico() {
		return subEstatico;
	}

	public void setSubEstatico(String subEstatico) {
		this.subEstatico = subEstatico;
	}

	public String getSubDinamico() {
		return subDinamico;
	}

	public void setSubDinamico(String subDinamico) {
		this.subDinamico = subDinamico;
	}

	public String getSubVazao() {
		return subVazao;
	}

	public void setSubVazao(String subVazao) {
		this.subVazao = subVazao;
	}

	public String getSubProfundidade() {
		return subProfundidade;
	}

	public void setSubProfundidade(String subProfundidade) {
		this.subProfundidade = subProfundidade;
	}

	public TipoPoco getSubTipoPocoFK() {
		return subTipoPocoFK;
	}

	public void setSubTipoPocoFK(TipoPoco subTipoPocoFK) {
		this.subTipoPocoFK = subTipoPocoFK;
	}

	

	public void setSubVazao1(Double subVazao1) {
		this.subVazao1 = subVazao1;
	}

	

	public Double getSubQDiaJan() {
		return subQDiaJan;
	}

	public void setSubQDiaJan(Double subQDiaJan) {
		this.subQDiaJan = subQDiaJan;
	}

	public int getSubQHoraJan() {
		return subQHoraJan;
	}

	public void setSubQHoraJan(int subQHoraJan) {
		this.subQHoraJan = subQHoraJan;
	}

	public int getSubTempoCapJan() {
		return subTempoCapJan;
	}

	public void setSubTempoCapJan(int subTempoCapJan) {
		this.subTempoCapJan = subTempoCapJan;
	}

	public Double getSubQDiaFev() {
		return subQDiaFev;
	}

	public void setSubQDiaFev(Double subQDiaFev) {
		this.subQDiaFev = subQDiaFev;
	}

	public int getSubQHoraFev() {
		return subQHoraFev;
	}

	public void setSubQHoraFev(int subQHoraFev) {
		this.subQHoraFev = subQHoraFev;
	}

	public int getSubTempoCapFev() {
		return subTempoCapFev;
	}

	public void setSubTempoCapFev(int subTempoCapFev) {
		this.subTempoCapFev = subTempoCapFev;
	}

	public Double getSubQDiaMar() {
		return subQDiaMar;
	}

	public void setSubQDiaMar(Double subQDiaMar) {
		this.subQDiaMar = subQDiaMar;
	}

	public int getSubQHoraMar() {
		return subQHoraMar;
	}

	public void setSubQHoraMar(int subQHoraMar) {
		this.subQHoraMar = subQHoraMar;
	}

	public int getSubTempoCapMar() {
		return subTempoCapMar;
	}

	public void setSubTempoCapMar(int subTempoCapMar) {
		this.subTempoCapMar = subTempoCapMar;
	}

	public Double getSubQDiaAbr() {
		return subQDiaAbr;
	}

	public void setSubQDiaAbr(Double subQDiaAbr) {
		this.subQDiaAbr = subQDiaAbr;
	}

	public int getSubQHoraAbr() {
		return subQHoraAbr;
	}

	public void setSubQHoraAbr(int subQHoraAbr) {
		this.subQHoraAbr = subQHoraAbr;
	}

	public int getSubTempoCapAbr() {
		return subTempoCapAbr;
	}

	public void setSubTempoCapAbr(int subTempoCapAbr) {
		this.subTempoCapAbr = subTempoCapAbr;
	}

	public Double getSubQDiaMai() {
		return subQDiaMai;
	}

	public void setSubQDiaMai(Double subQDiaMai) {
		this.subQDiaMai = subQDiaMai;
	}

	public int getSubQHoraMai() {
		return subQHoraMai;
	}

	public void setSubQHoraMai(int subQHoraMai) {
		this.subQHoraMai = subQHoraMai;
	}

	public int getSubTempoCapMai() {
		return subTempoCapMai;
	}

	public void setSubTempoCapMai(int subTempoCapMai) {
		this.subTempoCapMai = subTempoCapMai;
	}

	public Double getSubQDiaJun() {
		return subQDiaJun;
	}

	public void setSubQDiaJun(Double subQDiaJun) {
		this.subQDiaJun = subQDiaJun;
	}

	public int getSubQHoraJun() {
		return subQHoraJun;
	}

	public void setSubQHoraJun(int subQHoraJun) {
		this.subQHoraJun = subQHoraJun;
	}

	public int getSubTempoCapJun() {
		return subTempoCapJun;
	}

	public void setSubTempoCapJun(int subTempoCapJun) {
		this.subTempoCapJun = subTempoCapJun;
	}

	public Double getSubQDiaJul() {
		return subQDiaJul;
	}

	public void setSubQDiaJul(Double subQDiaJul) {
		this.subQDiaJul = subQDiaJul;
	}

	public int getSubQHoraJul() {
		return subQHoraJul;
	}

	public void setSubQHoraJul(int subQHoraJul) {
		this.subQHoraJul = subQHoraJul;
	}

	public int getSubTempoCapJul() {
		return subTempoCapJul;
	}

	public void setSubTempoCapJul(int subTempoCapJul) {
		this.subTempoCapJul = subTempoCapJul;
	}

	public Double getSubQDiaAgo() {
		return subQDiaAgo;
	}

	public void setSubQDiaAgo(Double subQDiaAgo) {
		this.subQDiaAgo = subQDiaAgo;
	}

	public int getSubQHoraAgo() {
		return subQHoraAgo;
	}

	public void setSubQHoraAgo(int subQHoraAgo) {
		this.subQHoraAgo = subQHoraAgo;
	}

	public int getSubTempoCapAgo() {
		return subTempoCapAgo;
	}

	public void setSubTempoCapAgo(int subTempoCapAgo) {
		this.subTempoCapAgo = subTempoCapAgo;
	}

	public Double getSubQDiaSet() {
		return subQDiaSet;
	}

	public void setSubQDiaSet(Double subQDiaSet) {
		this.subQDiaSet = subQDiaSet;
	}

	public int getSubQHoraSet() {
		return subQHoraSet;
	}

	public void setSubQHoraSet(int subQHoraSet) {
		this.subQHoraSet = subQHoraSet;
	}

	public int getSubTempoCapSet() {
		return subTempoCapSet;
	}

	public void setSubTempoCapSet(int subTempoCapSet) {
		this.subTempoCapSet = subTempoCapSet;
	}

	public Double getSubQDiaOut() {
		return subQDiaOut;
	}

	public void setSubQDiaOut(Double subQDiaOut) {
		this.subQDiaOut = subQDiaOut;
	}

	public int getSubQHoraOut() {
		return subQHoraOut;
	}

	public void setSubQHoraOut(int subQHoraOut) {
		this.subQHoraOut = subQHoraOut;
	}

	public int getSubTempoCapOut() {
		return subTempoCapOut;
	}

	public void setSubTempoCapOut(int subTempoCapOut) {
		this.subTempoCapOut = subTempoCapOut;
	}

	public Double getSubQDiaNov() {
		return subQDiaNov;
	}

	public void setSubQDiaNov(Double subQDiaNov) {
		this.subQDiaNov = subQDiaNov;
	}

	public int getSubQHoraNov() {
		return subQHoraNov;
	}

	public void setSubQHoraNov(int subQHoraNov) {
		this.subQHoraNov = subQHoraNov;
	}

	public int getSubTempoCapNov() {
		return subTempoCapNov;
	}

	public void setSubTempoCapNov(int subTempoCapNov) {
		this.subTempoCapNov = subTempoCapNov;
	}

	public Double getSubQDiaDez() {
		return subQDiaDez;
	}

	public void setSubQDiaDez(Double subQDiaDez) {
		this.subQDiaDez = subQDiaDez;
	}

	public int getSubQHoraDez() {
		return subQHoraDez;
	}

	public void setSubQHoraDez(int subQHoraDez) {
		this.subQHoraDez = subQHoraDez;
	}

	public int getSubTempoCapDez() {
		return subTempoCapDez;
	}

	public void setSubTempoCapDez(int subTempoCapDez) {
		this.subTempoCapDez = subTempoCapDez;
	}

	

	public Double getSubVazaoTotal() {
		return subVazaoTotal;
	}

	public void setSubVazaoTotal(Double subVazaoTotal) {
		this.subVazaoTotal = subVazaoTotal;
	}

	

	public String getSubSubfinalidade1() {
		return subSubfinalidade1;
	}

	public void setSubSubfinalidade1(String subSubfinalidade1) {
		this.subSubfinalidade1 = subSubfinalidade1;
	}

	public Double getSubQuantidade1() {
		return subQuantidade1;
	}

	public void setSubQuantidade1(Double subQuantidade1) {
		this.subQuantidade1 = subQuantidade1;
	}

	public Double getSubConsumo1() {
		return subConsumo1;
	}

	public void setSubConsumo1(Double subConsumo1) {
		this.subConsumo1 = subConsumo1;
	}

	
	public String getSubSubfinalidade2() {
		return subSubfinalidade2;
	}

	public void setSubSubfinalidade2(String subSubfinalidade2) {
		this.subSubfinalidade2 = subSubfinalidade2;
	}

	public Double getSubQuantidade2() {
		return subQuantidade2;
	}

	public void setSubQuantidade2(Double subQuantidade2) {
		this.subQuantidade2 = subQuantidade2;
	}

	public Double getSubConsumo2() {
		return subConsumo2;
	}

	public void setSubConsumo2(Double subConsumo2) {
		this.subConsumo2 = subConsumo2;
	}

	public Double getSubVazao2() {
		return subVazao2;
	}

	public void setSubVazao2(Double subVazao2) {
		this.subVazao2 = subVazao2;
	}

	

	public String getSubSubfinalidade3() {
		return subSubfinalidade3;
	}

	public void setSubSubfinalidade3(String subSubfinalidade3) {
		this.subSubfinalidade3 = subSubfinalidade3;
	}

	public Double getSubQuantidade3() {
		return subQuantidade3;
	}

	public void setSubQuantidade3(Double subQuantidade3) {
		this.subQuantidade3 = subQuantidade3;
	}

	public Double getSubConsumo3() {
		return subConsumo3;
	}

	public void setSubConsumo3(Double subConsumo3) {
		this.subConsumo3 = subConsumo3;
	}

	public Double getSubVazao3() {
		return subVazao3;
	}

	public void setSubVazao3(Double subVazao3) {
		this.subVazao3 = subVazao3;
	}


	public String getSubSubfinalidade4() {
		return subSubfinalidade4;
	}

	public void setSubSubfinalidade4(String subSubfinalidade4) {
		this.subSubfinalidade4 = subSubfinalidade4;
	}

	public Double getSubQuantidade4() {
		return subQuantidade4;
	}

	public void setSubQuantidade4(Double subQuantidade4) {
		this.subQuantidade4 = subQuantidade4;
	}

	public Double getSubConsumo4() {
		return subConsumo4;
	}

	public void setSubConsumo4(Double subConsumo4) {
		this.subConsumo4 = subConsumo4;
	}

	public Double getSubVazao4() {
		return subVazao4;
	}

	public void setSubVazao4(Double subVazao4) {
		this.subVazao4 = subVazao4;
	}

	
	public String getSubSubfinalidade5() {
		return subSubfinalidade5;
	}

	public void setSubSubfinalidade5(String subSubfinalidade5) {
		this.subSubfinalidade5 = subSubfinalidade5;
	}

	public Double getSubQuantidade5() {
		return subQuantidade5;
	}

	public void setSubQuantidade5(Double subQuantidade5) {
		this.subQuantidade5 = subQuantidade5;
	}

	public Double getSubConsumo5() {
		return subConsumo5;
	}

	public void setSubConsumo5(Double subConsumo5) {
		this.subConsumo5 = subConsumo5;
	}

	public Double getSubVazao5() {
		return subVazao5;
	}

	public void setSubVazao5(Double subVazao5) {
		this.subVazao5 = subVazao5;
	}

	public Double getSubVazao1() {
		return subVazao1;
	}

	public String getSubFinalidade1() {
		return subFinalidade1;
	}

	public void setSubFinalidade1(String subFinalidade1) {
		this.subFinalidade1 = subFinalidade1;
	}

	public String getSubFinalidade2() {
		return subFinalidade2;
	}

	public void setSubFinalidade2(String subFinalidade2) {
		this.subFinalidade2 = subFinalidade2;
	}

	public String getSubFinalidade3() {
		return subFinalidade3;
	}

	public void setSubFinalidade3(String subFinalidade3) {
		this.subFinalidade3 = subFinalidade3;
	}

	public String getSubFinalidade4() {
		return subFinalidade4;
	}

	public void setSubFinalidade4(String subFinalidade4) {
		this.subFinalidade4 = subFinalidade4;
	}

	public String getSubFinalidade5() {
		return subFinalidade5;
	}

	public void setSubFinalidade5(String subFinalidade5) {
		this.subFinalidade5 = subFinalidade5;
	}

}
