package entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interferencia implements Serializable {
	
	private static final long serialVersionUID = -1830876095445773286L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="inter_ID")
	private int interID;
	
	//-- RELACIONAMENTO ENDEREÇO --//
	@ManyToOne (fetch = FetchType.LAZY) 
	@JoinColumn (name = "inter_Endereco_FK")
	private Endereco interEnderecoFK;
	
		@ManyToOne (fetch = FetchType.LAZY) 
		@JoinColumn (name = "inter_Tipo_Interferencia_FK")
		private TipoInterferencia interTipoInterferenciaFK;
		
			@ManyToOne (fetch = FetchType.LAZY) 
			@JoinColumn (name = "inter_Bacia_FK")
			private BaciasHidrograficas interBaciaFK;
		
				@ManyToOne (fetch = FetchType.LAZY) 
				@JoinColumn (name = "inter_UH_FK")
				private UnidadeHidrografica interUHFK;
				
					@ManyToOne (fetch = FetchType.LAZY) 
					@JoinColumn (name = "inter_Situacao_Processo_FK")
					private SituacaoProcesso interSituacaoProcessoFK;
					
						@ManyToOne (fetch = FetchType.LAZY) 
						@JoinColumn (name = "inter_Tipo_Ato_FK")
						private TipoAto interTipoAtoFK;
						
							@ManyToOne (fetch = FetchType.LAZY) 
							@JoinColumn (name = "inter_Tipo_Outorga_FK")
							private TipoOutorga interTipoOutorgaFK;
							
	
	@Column (name="inter_DD_Latitude")
	private Double interDDLatitude;
	
	@Column (name="inter_DD_Longitude")
	private Double interDDLongitude;
	
	@Column(name="inter_Geom")
	private Geometry interGeom;
	
	@Basic
	@Column (name="inter_Atualizacao")
	private java.sql.Timestamp intAtualizacao;
	
	@Basic
	@Column (name="inter_Data_Vencimento")
	private java.sql.Date interDataVencimento;
	
	@Basic
	@Column (name="inter_Data_Publicacao")
	private java.sql.Date interDataPublicacao;
	
	@Column (name="inter_Num_Ato", columnDefinition="varchar(20)")
	private String interNumeroAto;
	
	@Column (name="inter_Proc_Renovacao",columnDefinition="varchar(40)")
	private String interProcRenovacao;
	
	@Column (name="inter_Despacho_Renovacao",columnDefinition="varchar(250)")
	private String interDespachoRenovacao;

	//CONSTRUTOR PADRÃO
	public Interferencia () {
		
	}

	public int getInterID() {
		return interID;
	}

	public void setInterID(int interID) {
		this.interID = interID;
	}

	public Endereco getInterEnderecoFK() {
		return interEnderecoFK;
	}

	public void setInterEnderecoFK(Endereco interEnderecoFK) {
		this.interEnderecoFK = interEnderecoFK;
	}

	
	public TipoInterferencia getInterTipoInterferenciaFK() {
		return interTipoInterferenciaFK;
	}

	public void setInterTipoInterferenciaFK(TipoInterferencia interTipoInterferencia) {
		this.interTipoInterferenciaFK = interTipoInterferencia;
	}

	public BaciasHidrograficas getInterBaciaFK() {
		return interBaciaFK;
	}

	public void setInterBaciaFK(BaciasHidrograficas interBaciasHidrograficas) {
		this.interBaciaFK = interBaciasHidrograficas;
	}

	public UnidadeHidrografica getInterUHFK() {
		return interUHFK;
	}

	public void setInterUHFK(UnidadeHidrografica interUH) {
		this.interUHFK = interUH;
	}

	
	public Double getInterDDLatitude() {
		return interDDLatitude;
	}

	public void setInterDDLatitude(Double interDDLatitude) {
		this.interDDLatitude = interDDLatitude;
	}

	public Double getInterDDLongitude() {
		return interDDLongitude;
	}

	public void setInterDDLongitude(Double interDDLongitude) {
		this.interDDLongitude = interDDLongitude;
	}

	
	public java.sql.Timestamp getIntAtualizacao() {
		return intAtualizacao;
	}

	public void setIntAtualizacao(java.sql.Timestamp intAtualizacao) {
		this.intAtualizacao = intAtualizacao;
	}

	public Geometry getInterGeom() {
		return interGeom;
	}

	public void setInterGeom(Geometry interGeom) {
		this.interGeom = interGeom;
	}

	public SituacaoProcesso getInterSituacaoProcessoFK() {
		return interSituacaoProcessoFK;
	}

	public void setInterSituacaoProcessoFK(SituacaoProcesso interSituacaoProcessoFK) {
		this.interSituacaoProcessoFK = interSituacaoProcessoFK;
	}

	public TipoAto getInterTipoAtoFK() {
		return interTipoAtoFK;
	}

	public void setInterTipoAtoFK(TipoAto interTipoAtoFK) {
		this.interTipoAtoFK = interTipoAtoFK;
	}

	public TipoOutorga getInterTipoOutorgaFK() {
		return interTipoOutorgaFK;
	}

	public void setInterTipoOutorgaFK(TipoOutorga interTipoOutorgaFK) {
		this.interTipoOutorgaFK = interTipoOutorgaFK;
	}

	public java.sql.Date getInterDataVencimento() {
		return interDataVencimento;
	}

	public void setInterDataVencimento(java.sql.Date interDataVencimento) {
		this.interDataVencimento = interDataVencimento;
	}

	public java.sql.Date getInterDataPublicacao() {
		return interDataPublicacao;
	}

	public void setInterDataPublicacao(java.sql.Date interDataPublicacao) {
		this.interDataPublicacao = interDataPublicacao;
	}

	public String getInterNumeroAto() {
		return interNumeroAto;
	}

	public void setInterNumeroAto(String interNumeroAto) {
		this.interNumeroAto = interNumeroAto;
	}

	public String getInterProcRenovacao() {
		return interProcRenovacao;
	}

	public void setInterProcRenovacao(String interProcRenovacao) {
		this.interProcRenovacao = interProcRenovacao;
	}

	public String getInterDespachoRenovacao() {
		return interDespachoRenovacao;
	}

	public void setInterDespachoRenovacao(String interDespachoRenovacao) {
		this.interDespachoRenovacao = interDespachoRenovacao;
	}

}
