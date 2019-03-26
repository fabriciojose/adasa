package entidades;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Demanda implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="dem_ID")
	private int demID;
	
	@ManyToOne //(fetch = FetchType.LAZY) 
	@JoinColumn (name = "dem_Endereco_FK")
	private Endereco demEnderecoFK;
	
	@Column (name="dem_Documento", columnDefinition="varchar(80)")
	private String demDocumento;
	
	@Column (name="dem_Documento_SEI", columnDefinition="varchar(25)")
	private String demDocumentoSEI; 
				// demDocumentoSEI
	
	@Column (name="dem_Processo_SEI", columnDefinition="varchar(25)")
	private String demProcessoSEI;
	
	@Basic
	@Column (name="dem_Distribuicao")
	private java.sql.Date demDistribuicao;
	
	@Basic
	@Column (name="dem_Recebimento")
	private java.sql.Date  demRecebimento;
	
	@Basic
	@Column (name="dem_Atualizacao")
	private java.sql.Timestamp demAtualizacao;
	
	
	//CONSTRUTOR PADRÃO
	public Demanda () {
		
	}
	
	// GETTERS / SETTERS
	
	public int getDemID() {
		return demID;
	}

	public void setDemID(int demID) {
		this.demID = demID;
	}

	public Endereco getDemEnderecoFK() {
		return demEnderecoFK;
	}

	public void setDemEnderecoFK(Endereco demEnderecoFK) {
		this.demEnderecoFK = demEnderecoFK;
	}

	public String getDemDocumento() {
		return demDocumento;
	}

	public void setDemDocumento(String demDocumento) {
		this.demDocumento = demDocumento;
	}

	public String getDemDocumentoSEI() {
		return demDocumentoSEI;
	}

	public void setDemDocumentoSEI(String demDocumentoSEI) {
		this.demDocumentoSEI = demDocumentoSEI;
	}

	public String getDemProcessoSEI() {
		return demProcessoSEI;
	}

	public void setDemProcessoSEI(String demProcessoSEI) {
		this.demProcessoSEI = demProcessoSEI;
	}

	
	public java.sql.Date getDemDistribuicao() {
		return demDistribuicao;
	}

	public void setDemDistribuicao(java.sql.Date demDistribuicao) {
		this.demDistribuicao = demDistribuicao;
	}

	public java.sql.Date getDemRecebimento() {
		return demRecebimento;
	}

	public void setDemRecebimento(java.sql.Date demRecebimento) {
		this.demRecebimento = demRecebimento;
	}

	public java.sql.Timestamp getDemAtualizacao() {
		return demAtualizacao;
	}

	public void setDemAtualizacao(java.sql.Timestamp demAtualizacao) {
		this.demAtualizacao = demAtualizacao;
	}

	
	
		
}