package controladores.principal;

import java.net.URL;
import java.util.ResourceBundle;

import entidades.Endereco;
import javafx.fxml.Initializable;

public class TelaDocumentoControlador implements Initializable {


		int intControlador;
		
		/* construtor para trazer o intControlador correto. 
		 * 0 para atendimento 
		 * 1 para fiscalizacao 
		 * 2 para outorga
		 * */
		
		public TelaDocumentoControlador (int intControlador) {
			  this.intControlador = intControlador;
		}
		
		TelaDocumentoControlador telaDocCon;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		telaDocCon = this;
		
		System.out.println("tela documento inicializada");
		
	
	}

}
