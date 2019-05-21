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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table (name="Tipo_Outorga")
public class TipoOutorga implements Serializable  {

	private static final long serialVersionUID = -9104131413245083170L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="to_ID")
	private int tipoOutorgaID; 

    @Column (name="to_Descricao", columnDefinition="varchar(250)")
	private String tipoOutorgaDescricao;
    
    public TipoOutorga () {
		
	}


	public int getTipoOutorgaID() {
		return tipoOutorgaID;
	}


	public void setTipoOutorgaID(int tipoOutorgaID) {
		this.tipoOutorgaID = tipoOutorgaID;
	}


	public String getTipoOutorgaDescricao() {
		return tipoOutorgaDescricao;
	}


	public void setTipoOutorgaDescricao(String tipoOutorgaDescricao) {
		this.tipoOutorgaDescricao = tipoOutorgaDescricao;
	}
    
    

}
