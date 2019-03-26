package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="UNIDADE_HIDROGRAFICA")
public class UnidadeHidrografica implements Serializable{

	private static final long serialVersionUID = -6207059484986075380L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="OBJECTID_1")
	private int uhID; 
	
	@Column (name="OBJECTID")
	private int objectID;

	@Column (name="bacia_nome", columnDefinition="varchar(70)")
	private String uhNome;
	
	@Column (name="bacia_codi")
	private int uhCodigo;
	
	@Column (name="subbacia_n", columnDefinition="varchar(150)")
	private String uhBaciaNome;

	@OneToMany (mappedBy = "interUHFK", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY, targetEntity = Interferencia.class)
	@Fetch(FetchMode.SUBSELECT) 
	private List<Interferencia> interferencias = new ArrayList<Interferencia>();
	
	//CONSTRUTOR PADRÃO
	public UnidadeHidrografica () {
		
	}

	public int getUhID() {
		return uhID;
	}

	public void setUhID(int uhID) {
		this.uhID = uhID;
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	public String getUhNome() {
		return uhNome;
	}

	public void setUhNome(String uhNome) {
		this.uhNome = uhNome;
	}

	public int getUhCodigo() {
		return uhCodigo;
	}

	public void setUhCodigo(int uhCodigo) {
		this.uhCodigo = uhCodigo;
	}

	public String getUhBaciaNome() {
		return uhBaciaNome;
	}

	public void setUhBaciaNome(String uhBaciaNome) {
		this.uhBaciaNome = uhBaciaNome;
	}

	public List<Interferencia> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(List<Interferencia> interferencias) {
		this.interferencias = interferencias;
	}
	
	
	
	
	
}
