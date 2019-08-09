package controladores.principal;

import java.io.IOException;

import entidades.Documento;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorAtendimento {

	@FXML Pane pAtendimento;
	@FXML TabPane tpAtendimento = new TabPane();

	@FXML Tab tpTabDocumento;
	String strDem = "/fxml/principal/TabDocumento.fxml";

	@FXML Tab tpTabEndereco;
	String strEnd = "/fxml/principal/TabEndereco.fxml";

	@FXML Tab tpTabInterferencia;
	String strInt = "/fxml/principal/TabInterferencia.fxml";

	@FXML Tab tpTabUsuario;
	String strUs = "/fxml/principal/TabUsuario.fxml";


	TabDocumentoControlador tabDocCon;// = new TabDocumentoControlador(2);
	TabEnderecoControlador tabEndCon;// = new TabEnderecoControlador(2);
	TabInterferenciaControlador tabInterCon;
	TabUsuarioControlador tabUsCon;
	TabParecerControlador tabParCon;
	TabAtosOutorgaControlador tabAtosOutCon;


	ControladorPrincipal controladorPrincipal;
	/*
	 * Construtor
	 * @ControladorPrincipal - transmitindo a inicialiacao do controlador principal para enviar objectos de uma aba para outra
	 */
	public ControladorAtendimento (ControladorPrincipal controladorPrincipal) {
		this.controladorPrincipal = controladorPrincipal;
	}

	// setar documentos nas tabs da tabpane
	public void setDocumento (Documento doc) {
		tabEndCon.setDocumento(doc);
	}
	
	@FXML 
    private void initialize() {
		
		tpAtendimento.prefWidthProperty().bind(pAtendimento.widthProperty());
		tpAtendimento.prefHeightProperty().bind(pAtendimento.heightProperty());
		


		abrirTab (new Pane() , tabDocCon = new TabDocumentoControlador(this), strDem, tpTabDocumento );
			abrirTab (new Pane()  , tabEndCon = new TabEnderecoControlador(this), strEnd, tpTabEndereco );
				abrirTab (new Pane() , tabInterCon =  new TabInterferenciaControlador(this), strInt, tpTabInterferencia );
					abrirTab (new Pane() , tabUsCon =  new TabUsuarioControlador(this), strUs, tpTabUsuario );
					
						
		
		
		
	}
	
	public void abrirTab (Pane p , Object o, String strFXML, Tab t ) {
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(strFXML));
		
		loader.setRoot(p);
		loader.setController(o);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("erro na abertura do pane");
			e.printStackTrace();
		}
		
		p.minWidthProperty().bind(pAtendimento.widthProperty());
		p.minHeightProperty().bind(pAtendimento.heightProperty());
		
		t.setContent(p);
	}

}
