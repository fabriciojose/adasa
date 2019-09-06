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

	@Column (name="sub_Caesb", columnDefinition="varchar(3)")
	private String subCaesb;  // tem caesb () sim () não

	@Column (name="sub_Estatico", columnDefinition="varchar(20)")
	private String subEstatico;  // em metros

	@Column (name="sub_Dinamico", columnDefinition="varchar(20)")
	private String subDinamico;  // em metros

	
	@Column (name="sub_Vazao_Poco")
	private Double subVazaoPoco;  // em l/h - litros por hora

	@Column (name="sub_Profundidade",columnDefinition="varchar(20)")
	private String subProfundidade;  // em metros

	@Basic
	@Column (name="sub_Data_Operacao")
	private java.sql.Date subDataOperacao;

	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "sub_Tipo_Poco_FK")
	private TipoPoco subTipoPocoFK; // Manual Tubular

	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "sub_Subsistema_FK")
	private SubSistema subSubSistemaFK;

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

	public Double getSubVazaoPoco() {
		return subVazaoPoco;
	}

	public void setSubVazaoPoco(Double subVazaoPoco) {
		this.subVazaoPoco = subVazaoPoco;
	}
	
	



}
