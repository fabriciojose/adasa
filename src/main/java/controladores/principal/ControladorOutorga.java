package controladores.principal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorOutorga {
	
	@FXML Pane pOutorga;
	@FXML TabPane tpOutorga = new TabPane();
	
	@FXML Tab tpTabDemanda;
		String strDem = "/fxml/principal/TabDocumento.fxml";
	
		@FXML Tab tpTabEndereco;
			String strEnd = "/fxml/principal/TabEndereco.fxml";

			@FXML Tab tpTabInterferencia;
			String strInt = "/fxml/principal/TabInterferencia.fxml";
			
				@FXML Tab tpTabUsuario;
				String strUs = "/fxml/principal/TabUsuario.fxml";
				
					@FXML Tab tpTabParecer;
					String strPar = "/fxml/principal/TabParecer.fxml";
				
				
	
	@FXML 
    private void initialize() {
		
		tpOutorga.prefWidthProperty().bind(pOutorga.widthProperty());
		tpOutorga.prefHeightProperty().bind(pOutorga.heightProperty());
		
		abrirTab (new Pane() ,  new TabDocumentoControlador(2), strDem, tpTabDemanda );
			abrirTab (new Pane()  ,  new TabEnderecoControlador(2), strEnd, tpTabEndereco );
				abrirTab (new Pane() ,  new TabInterferenciaControlador(2), strInt, tpTabInterferencia );
					abrirTab (new Pane() ,  new TabUsuarioControlador(2), strUs, tpTabUsuario );
						abrirTab (new Pane() ,  new TabParecerControlador(2), strPar, tpTabParecer );
		
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
		
		p.minWidthProperty().bind(pOutorga.widthProperty());
		p.minHeightProperty().bind(pOutorga.heightProperty());
		
		t.setContent(p);
	}

}
