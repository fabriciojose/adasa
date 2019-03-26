package entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="sup_Interferencia_FK")
public class Superficial extends Interferencia {

	private static final long serialVersionUID = 8310916470642159612L;

		@ManyToOne (fetch = FetchType.EAGER) 
		@JoinColumn (name = "sup_Forma_Captacao_FK")
		private FormaCaptacao supFormaCaptacaoFK; // Bombeamento Gravidade
		
			@ManyToOne (fetch = FetchType.EAGER) 
			@JoinColumn (name = "sup_Local_Captacao_FK")
			private LocalCaptacao supLocalCaptacaoFK; //-- () canal () rio () reservatório () lago natural () nascente
			
				@ManyToOne (fetch = FetchType.EAGER) 
				@JoinColumn (name = "sup_Metodo_Irrigacao_FK")
				private MetodoIrrigacao supMetodoIrrigacaoFK; //-- () canal () rio () reservatório () lago natural () nascente
				
		
	@Column (name="sup_Marca_Bomba", columnDefinition="varchar(30)")
	private String supMarcaBomba; // marca
	
	@Column (name="sup_Potencia_Bomba", columnDefinition="varchar(10)")
	private String supPotenciaBomba; // em cv - cavalos
	
	@Column (name="sup_Area_Irrigada", columnDefinition="varchar(10)")
	private String supAreaIrrigada;
	
	@Column (name="sup_Area_Contribuicao", columnDefinition="varchar(10)")
	private String supAreaContribuicao;
	
	@Column (name="sup_Area_Propriedade", columnDefinition="varchar(10)")
	private String supAreaPropriedade;
	
	@Basic
	@Column (name="sup_Data_Operacao")
	private java.sql.Date supDataOperacao;
	
	@Column (name="sup_Caesb", columnDefinition="varchar(3)")
	private String supCaesb;  // tem caesb () sim () não
	
	@Column (name="sup_Barramento", columnDefinition="varchar(3)")
	private String supBarramento; 
	
	@Column (name="sup_Corpo_Hidrico", columnDefinition="varchar (50)")
	private String supCorpoHidrico;
	
	
	// 1 //
		@Column (name="sup_Finalidade_1",columnDefinition="varchar(250)")
		private String supFinalidade1;
		
		@Column (name="sup_Subfinalidade_1",columnDefinition="varchar(250)")
		private String supSubfinalidade1;
		
		@Column (name="sup_Quantidade_1")
		private Double supQuantidade1;
		
		@Column (name="sup_Consumo_1")
		private Double supConsumo1;
		
		@Column (name="sup_Vazao_1")
		private Double supVazao1;
		
			// 2 //
			@Column (name="sup_Finalidade_2",columnDefinition="varchar(250)")
			private String supFinalidade2;
			
			@Column (name="sup_Subfinalidade_2",columnDefinition="varchar(250)")
			private String supSubfinalidade2;
			
			@Column (name="sup_Quantidade_2")
			private Double supQuantidade2;
			
			@Column (name="sup_Consumo_2")
			private Double supConsumo2;
			
			@Column (name="sup_Vazao_2")
			private Double supVazao2;
			
				// 3 //
				@Column (name="sup_Finalidade_3",columnDefinition="varchar(350)")
				private String supFinalidade3;
				
				@Column (name="sup_Subfinalidade_3",columnDefinition="varchar(350)")
				private String supSubfinalidade3;
				
				@Column (name="sup_Quantidade_3")
				private Double supQuantidade3;
				
				@Column (name="sup_Consumo_3")
				private Double supConsumo3;
				
				@Column (name="sup_Vazao_3")
				private Double supVazao3;
				
					// 4 //
					@Column (name="sup_Finalidade_4",columnDefinition="varchar(450)")
					private String supFinalidade4;
					
					@Column (name="sup_Subfinalidade_4",columnDefinition="varchar(450)")
					private String supSubfinalidade4;
					
					@Column (name="sup_Quantidade_4")
					private Double supQuantidade4;
					
					@Column (name="sup_Consumo_4")
					private Double supConsumo4;
					
					@Column (name="sup_Vazao_4")
					private Double supVazao4;
						
						// 5 //
						@Column (name="sup_Finalidade_5",columnDefinition="varchar(550)")
						private String supFinalidade5;
						
						@Column (name="sup_Subfinalidade_5",columnDefinition="varchar(550)")
						private String supSubfinalidade5;
						
						@Column (name="sup_Quantidade_5")
						private Double supQuantidade5;
						
						@Column (name="sup_Consumo_5")
						private Double supConsumo5;
						
						@Column (name="sup_Vazao_5")
						private Double supVazao5;
				
		
	@Column (name="sup_Vazao_Total")
	private Double supVazaoTotal;
	
	// JANEIRO //
	@Column (name="sup_Q_Dia_Jan")
	private Double supQDiaJan;
	
	@Column (name="sup_Q_Hora_Jan")
	private int supQHoraJan;
	
	@Column (name="sup_Tempo_Cap_Jan")
	private int supTempoCapJan;
	
	// FEVEREIRO
	@Column (name="sup_Q_Dia_Fev")
	private Double supQDiaFev;
	
	@Column (name="sup_Q_Hora_Fev")
	private int supQHoraFev;
	
	@Column (name="sup_Tempo_Cap_Fev")
	private int supTempoCapFev;
	
	// MARCO //
	@Column (name="sup_Q_Dia_Mar")
	private Double supQDiaMar;
	
	@Column (name="sup_Q_Hora_Mar")
	private int supQHoraMar;
	
	@Column (name="sup_Tempo_Cap_Mar")
	private int supTempoCapMar;
	
	
	// ABRIL //
	@Column (name="sup_Q_Dia_Abr")
	private Double supQDiaAbr;
	
	@Column (name="sup_Q_Hora_Abr")
	private int supQHoraAbr;
	
	@Column (name="sup_Tempo_Cap_Abr")
	private int supTempoCapAbr;
	
	// MAIO //
	
	@Column (name="sup_Q_Dia_Mai")
	private Double supQDiaMai;
	
	@Column (name="sup_Q_Hora_Mai")
	private int supQHoraMai;
	
	@Column (name="sup_Tempo_Cap_Mai")
	private int supTempoCapMai;
	
	// JUNHO //
	
	@Column (name="sup_Q_Dia_Jun")
	private Double supQDiaJun;
	
	@Column (name="sup_Q_Hora_Jun")
	private int supQHoraJun;
	
	@Column (name="sup_Tempo_Cap_Jun")
	private int supTempoCapJun;
	
	// JULHO //
	@Column (name="sup_Q_Dia_Jul")
	private Double supQDiaJul;
	
	@Column (name="sup_Q_Hora_Jul")
	private int supQHoraJul;
	
	@Column (name="sup_Tempo_Cap_Jul")
	private int supTempoCapJul;
	
	
	// AGOSTO //
	@Column (name="sup_Q_Dia_Ago")
	private Double supQDiaAgo;
	
	@Column (name="sup_Q_Hora_Ago")
	private int supQHoraAgo;
	
	@Column (name="sup_Tempo_Cap_Ago")
	private int supTempoCapAgo;
	
	
	// SETEMBRO //
	@Column (name="sup_Q_Dia_Set")
	private Double supQDiaSet;
	
	@Column (name="sup_Q_Hora_Set")
	private int supQHoraSet;
	
	@Column (name="sup_Tempo_Cap_Set")
	private int supTempoCapSet;
	
	// OUTUBRO //
	@Column (name="sup_Q_Dia_Out")
	private Double supQDiaOut;
	
	@Column (name="sup_Q_Hora_Out")
	private int supQHoraOut;
	
	@Column (name="sup_Tempo_Cap_Out")
	private int supTempoCapOut;
	
	
	// NOVEMBRO //
	@Column (name="sup_Q_Dia_Nov")
	private Double supQDiaNov;
	
	@Column (name="sup_Q_Hora_Nov")
	private int supQHoraNov;
	
	@Column (name="sup_Tempo_Cap_Nov")
	private int supTempoCapNov;
	
	// DEZEMBRO //
	@Column (name="sup_Q_Dia_Dez")
	private Double supQDiaDez;
	
	@Column (name="sup_Q_Hora_Dez")
	private int supQHoraDez;
	
	@Column (name="sup_Tempo_Cap_Dez")
	private int supTempoCapDez;

	public Superficial (){
		
	}
	
	//-- getters and setters --//
	
	public java.sql.Date getSupDataOperacao() {
		return supDataOperacao;
	}


	public void setSupDataOperacao(java.sql.Date supDataOperacao) {
		this.supDataOperacao = supDataOperacao;
	}


	public String getSupCaesb() {
		return supCaesb;
	}

	public void setSupCaesb(String supCaesb) {
		this.supCaesb = supCaesb;
	}

	

	public FormaCaptacao getSupFormaCaptacaoFK() {
		return supFormaCaptacaoFK;
	}

	public void setSupFormaCaptacaoFK(FormaCaptacao supFormaCaptacaoFK) {
		this.supFormaCaptacaoFK = supFormaCaptacaoFK;
	}

	public LocalCaptacao getSupLocalCaptacaoFK() {
		return supLocalCaptacaoFK;
	}

	public void setSupLocalCaptacaoFK(LocalCaptacao supLocalCaptacaoFK) {
		this.supLocalCaptacaoFK = supLocalCaptacaoFK;
	}

	public String getSupMarcaBomba() {
		return supMarcaBomba;
	}

	public void setSupMarcaBomba(String supMarcaBomba) {
		this.supMarcaBomba = supMarcaBomba;
	}

	public String getSupPotenciaBomba() {
		return supPotenciaBomba;
	}

	public void setSupPotenciaBomba(String supPotenciaBomba) {
		this.supPotenciaBomba = supPotenciaBomba;
	}

	public String getSupAreaIrrigada() {
		return supAreaIrrigada;
	}

	public void setSupAreaIrrigada(String supAreaIrrigada) {
		this.supAreaIrrigada = supAreaIrrigada;
	}

	public String getSupAreaContribuicao() {
		return supAreaContribuicao;
	}

	public void setSupAreaContribuicao(String supAreaContribuicao) {
		this.supAreaContribuicao = supAreaContribuicao;
	}

	public String getSupAreaPropriedade() {
		return supAreaPropriedade;
	}

	public void setSupAreaPropriedade(String supAreaPropriedade) {
		this.supAreaPropriedade = supAreaPropriedade;
	}

	public String getSupBarramento() {
		return supBarramento;
	}

	public void setSupBarramento(String supBarramento) {
		this.supBarramento = supBarramento;
	}

	public MetodoIrrigacao getSupMetodoIrrigacaoFK() {
		return supMetodoIrrigacaoFK;
	}

	public void setSupMetodoIrrigacaoFK(MetodoIrrigacao supMetodoIrrigacaoFK) {
		this.supMetodoIrrigacaoFK = supMetodoIrrigacaoFK;
	}

	public String getSupCorpoHidrico() {
		return supCorpoHidrico;
	}

	public void setSupCorpoHidrico(String supCorpoHidrico) {
		this.supCorpoHidrico = supCorpoHidrico;
	}

	public String getSupFinalidade1() {
		return supFinalidade1;
	}

	public void setSupFinalidade1(String supFinalidade1) {
		this.supFinalidade1 = supFinalidade1;
	}

	public String getSupSubfinalidade1() {
		return supSubfinalidade1;
	}

	public void setSupSubfinalidade1(String supSubfinalidade1) {
		this.supSubfinalidade1 = supSubfinalidade1;
	}

	public Double getSupQuantidade1() {
		return supQuantidade1;
	}

	public void setSupQuantidade1(Double supQuantidade1) {
		this.supQuantidade1 = supQuantidade1;
	}

	public Double getSupConsumo1() {
		return supConsumo1;
	}

	public void setSupConsumo1(Double supConsumo1) {
		this.supConsumo1 = supConsumo1;
	}

	public Double getSupVazao1() {
		return supVazao1;
	}

	public void setSupVazao1(Double supVazao1) {
		this.supVazao1 = supVazao1;
	}

	public String getSupFinalidade2() {
		return supFinalidade2;
	}

	public void setSupFinalidade2(String supFinalidade2) {
		this.supFinalidade2 = supFinalidade2;
	}

	public String getSupSubfinalidade2() {
		return supSubfinalidade2;
	}

	public void setSupSubfinalidade2(String supSubfinalidade2) {
		this.supSubfinalidade2 = supSubfinalidade2;
	}

	public Double getSupQuantidade2() {
		return supQuantidade2;
	}

	public void setSupQuantidade2(Double supQuantidade2) {
		this.supQuantidade2 = supQuantidade2;
	}

	public Double getSupConsumo2() {
		return supConsumo2;
	}

	public void setSupConsumo2(Double supConsumo2) {
		this.supConsumo2 = supConsumo2;
	}

	public Double getSupVazao2() {
		return supVazao2;
	}

	public void setSupVazao2(Double supVazao2) {
		this.supVazao2 = supVazao2;
	}

	public String getSupFinalidade3() {
		return supFinalidade3;
	}

	public void setSupFinalidade3(String supFinalidade3) {
		this.supFinalidade3 = supFinalidade3;
	}

	public String getSupSubfinalidade3() {
		return supSubfinalidade3;
	}

	public void setSupSubfinalidade3(String supSubfinalidade3) {
		this.supSubfinalidade3 = supSubfinalidade3;
	}

	public Double getSupQuantidade3() {
		return supQuantidade3;
	}

	public void setSupQuantidade3(Double supQuantidade3) {
		this.supQuantidade3 = supQuantidade3;
	}

	public Double getSupConsumo3() {
		return supConsumo3;
	}

	public void setSupConsumo3(Double supConsumo3) {
		this.supConsumo3 = supConsumo3;
	}

	public Double getSupVazao3() {
		return supVazao3;
	}

	public void setSupVazao3(Double supVazao3) {
		this.supVazao3 = supVazao3;
	}

	public String getSupFinalidade4() {
		return supFinalidade4;
	}

	public void setSupFinalidade4(String supFinalidade4) {
		this.supFinalidade4 = supFinalidade4;
	}

	public String getSupSubfinalidade4() {
		return supSubfinalidade4;
	}

	public void setSupSubfinalidade4(String supSubfinalidade4) {
		this.supSubfinalidade4 = supSubfinalidade4;
	}

	public Double getSupQuantidade4() {
		return supQuantidade4;
	}

	public void setSupQuantidade4(Double supQuantidade4) {
		this.supQuantidade4 = supQuantidade4;
	}

	public Double getSupConsumo4() {
		return supConsumo4;
	}

	public void setSupConsumo4(Double supConsumo4) {
		this.supConsumo4 = supConsumo4;
	}

	public Double getSupVazao4() {
		return supVazao4;
	}

	public void setSupVazao4(Double supVazao4) {
		this.supVazao4 = supVazao4;
	}

	public String getSupFinalidade5() {
		return supFinalidade5;
	}

	public void setSupFinalidade5(String supFinalidade5) {
		this.supFinalidade5 = supFinalidade5;
	}

	public String getSupSubfinalidade5() {
		return supSubfinalidade5;
	}

	public void setSupSubfinalidade5(String supSubfinalidade5) {
		this.supSubfinalidade5 = supSubfinalidade5;
	}

	public Double getSupQuantidade5() {
		return supQuantidade5;
	}

	public void setSupQuantidade5(Double supQuantidade5) {
		this.supQuantidade5 = supQuantidade5;
	}

	public Double getSupConsumo5() {
		return supConsumo5;
	}

	public void setSupConsumo5(Double supConsumo5) {
		this.supConsumo5 = supConsumo5;
	}

	public Double getSupVazao5() {
		return supVazao5;
	}

	public void setSupVazao5(Double supVazao5) {
		this.supVazao5 = supVazao5;
	}

	public Double getSupVazaoTotal() {
		return supVazaoTotal;
	}

	public void setSupVazaoTotal(Double supVazaoTotal) {
		this.supVazaoTotal = supVazaoTotal;
	}

	public Double getSupQDiaJan() {
		return supQDiaJan;
	}

	public void setSupQDiaJan(Double supQDiaJan) {
		this.supQDiaJan = supQDiaJan;
	}

	public int getSupQHoraJan() {
		return supQHoraJan;
	}

	public void setSupQHoraJan(int supQHoraJan) {
		this.supQHoraJan = supQHoraJan;
	}

	public int getSupTempoCapJan() {
		return supTempoCapJan;
	}

	public void setSupTempoCapJan(int supTempoCapJan) {
		this.supTempoCapJan = supTempoCapJan;
	}

	public Double getSupQDiaFev() {
		return supQDiaFev;
	}

	public void setSupQDiaFev(Double supQDiaFev) {
		this.supQDiaFev = supQDiaFev;
	}

	public int getSupQHoraFev() {
		return supQHoraFev;
	}

	public void setSupQHoraFev(int supQHoraFev) {
		this.supQHoraFev = supQHoraFev;
	}

	public int getSupTempoCapFev() {
		return supTempoCapFev;
	}

	public void setSupTempoCapFev(int supTempoCapFev) {
		this.supTempoCapFev = supTempoCapFev;
	}

	public Double getSupQDiaMar() {
		return supQDiaMar;
	}

	public void setSupQDiaMar(Double supQDiaMar) {
		this.supQDiaMar = supQDiaMar;
	}

	public int getSupQHoraMar() {
		return supQHoraMar;
	}

	public void setSupQHoraMar(int supQHoraMar) {
		this.supQHoraMar = supQHoraMar;
	}

	public int getSupTempoCapMar() {
		return supTempoCapMar;
	}

	public void setSupTempoCapMar(int supTempoCapMar) {
		this.supTempoCapMar = supTempoCapMar;
	}

	public Double getSupQDiaAbr() {
		return supQDiaAbr;
	}

	public void setSupQDiaAbr(Double supQDiaAbr) {
		this.supQDiaAbr = supQDiaAbr;
	}

	public int getSupQHoraAbr() {
		return supQHoraAbr;
	}

	public void setSupQHoraAbr(int supQHoraAbr) {
		this.supQHoraAbr = supQHoraAbr;
	}

	public int getSupTempoCapAbr() {
		return supTempoCapAbr;
	}

	public void setSupTempoCapAbr(int supTempoCapAbr) {
		this.supTempoCapAbr = supTempoCapAbr;
	}

	public Double getSupQDiaMai() {
		return supQDiaMai;
	}

	public void setSupQDiaMai(Double supQDiaMai) {
		this.supQDiaMai = supQDiaMai;
	}

	public int getSupQHoraMai() {
		return supQHoraMai;
	}

	public void setSupQHoraMai(int supQHoraMai) {
		this.supQHoraMai = supQHoraMai;
	}

	public int getSupTempoCapMai() {
		return supTempoCapMai;
	}

	public void setSupTempoCapMai(int supTempoCapMai) {
		this.supTempoCapMai = supTempoCapMai;
	}

	public Double getSupQDiaJun() {
		return supQDiaJun;
	}

	public void setSupQDiaJun(Double supQDiaJun) {
		this.supQDiaJun = supQDiaJun;
	}

	public int getSupQHoraJun() {
		return supQHoraJun;
	}

	public void setSupQHoraJun(int supQHoraJun) {
		this.supQHoraJun = supQHoraJun;
	}

	public int getSupTempoCapJun() {
		return supTempoCapJun;
	}

	public void setSupTempoCapJun(int supTempoCapJun) {
		this.supTempoCapJun = supTempoCapJun;
	}

	public Double getSupQDiaJul() {
		return supQDiaJul;
	}

	public void setSupQDiaJul(Double supQDiaJul) {
		this.supQDiaJul = supQDiaJul;
	}

	public int getSupQHoraJul() {
		return supQHoraJul;
	}

	public void setSupQHoraJul(int supQHoraJul) {
		this.supQHoraJul = supQHoraJul;
	}

	public int getSupTempoCapJul() {
		return supTempoCapJul;
	}

	public void setSupTempoCapJul(int supTempoCapJul) {
		this.supTempoCapJul = supTempoCapJul;
	}

	public Double getSupQDiaAgo() {
		return supQDiaAgo;
	}

	public void setSupQDiaAgo(Double supQDiaAgo) {
		this.supQDiaAgo = supQDiaAgo;
	}

	public int getSupQHoraAgo() {
		return supQHoraAgo;
	}

	public void setSupQHoraAgo(int supQHoraAgo) {
		this.supQHoraAgo = supQHoraAgo;
	}

	public int getSupTempoCapAgo() {
		return supTempoCapAgo;
	}

	public void setSupTempoCapAgo(int supTempoCapAgo) {
		this.supTempoCapAgo = supTempoCapAgo;
	}

	public Double getSupQDiaSet() {
		return supQDiaSet;
	}

	public void setSupQDiaSet(Double supQDiaSet) {
		this.supQDiaSet = supQDiaSet;
	}

	public int getSupQHoraSet() {
		return supQHoraSet;
	}

	public void setSupQHoraSet(int supQHoraSet) {
		this.supQHoraSet = supQHoraSet;
	}

	public int getSupTempoCapSet() {
		return supTempoCapSet;
	}

	public void setSupTempoCapSet(int supTempoCapSet) {
		this.supTempoCapSet = supTempoCapSet;
	}

	public Double getSupQDiaOut() {
		return supQDiaOut;
	}

	public void setSupQDiaOut(Double supQDiaOut) {
		this.supQDiaOut = supQDiaOut;
	}

	public int getSupQHoraOut() {
		return supQHoraOut;
	}

	public void setSupQHoraOut(int supQHoraOut) {
		this.supQHoraOut = supQHoraOut;
	}

	public int getSupTempoCapOut() {
		return supTempoCapOut;
	}

	public void setSupTempoCapOut(int supTempoCapOut) {
		this.supTempoCapOut = supTempoCapOut;
	}

	public Double getSupQDiaNov() {
		return supQDiaNov;
	}

	public void setSupQDiaNov(Double supQDiaNov) {
		this.supQDiaNov = supQDiaNov;
	}

	public int getSupQHoraNov() {
		return supQHoraNov;
	}

	public void setSupQHoraNov(int supQHoraNov) {
		this.supQHoraNov = supQHoraNov;
	}

	public int getSupTempoCapNov() {
		return supTempoCapNov;
	}

	public void setSupTempoCapNov(int supTempoCapNov) {
		this.supTempoCapNov = supTempoCapNov;
	}

	public Double getSupQDiaDez() {
		return supQDiaDez;
	}

	public void setSupQDiaDez(Double supQDiaDez) {
		this.supQDiaDez = supQDiaDez;
	}

	public int getSupQHoraDez() {
		return supQHoraDez;
	}

	public void setSupQHoraDez(int supQHoraDez) {
		this.supQHoraDez = supQHoraDez;
	}

	public int getSupTempoCapDez() {
		return supTempoCapDez;
	}

	public void setSupTempoCapDez(int supTempoCapDez) {
		this.supTempoCapDez = supTempoCapDez;
	}

	
}
