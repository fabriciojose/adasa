package controladores.atendimento;

import java.io.IOException;

import controladores.principal.TabDemandaControlador;
import controladores.principal.TabEnderecoControlador;
import controladores.principal.TabInterferenciaControlador;
import controladores.principal.TabUsuarioControlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorAtendimento {
	
	@FXML Pane pAtendimento;
	@FXML TabPane tpAtendimento = new TabPane();
	
	@FXML Tab tpTabDemanda;
	Pane pDemanda = new Pane();
	TabDemandaControlador tabDem = new TabDemandaControlador();
	String strDem = "/fxml/principal/TabDemanda.fxml";
	
		@FXML Tab tpTabEndereco;
		Pane pEndereco = new Pane();
		TabEnderecoControlador tabEnd = new TabEnderecoControlador();
		String strEnd = "/fxml/principal/TabEndereco.fxml";

			@FXML Tab tpTabInterferencia;
			Pane pInterferencia = new Pane();
			TabInterferenciaControlador tabInt = new TabInterferenciaControlador();
			String strInt = "/fxml/principal/TabInterferencia.fxml";
			
				@FXML Tab tpTabUsuario;
				Pane pUsuario = new Pane();
				TabUsuarioControlador tabUs = new TabUsuarioControlador();
				String strUs = "/fxml/principal/TabUsuario.fxml";
	
	@FXML 
    private void initialize() {
		
		tpAtendimento.prefWidthProperty().bind(pAtendimento.widthProperty());
		tpAtendimento.prefHeightProperty().bind(pAtendimento.heightProperty());
		
		abrirTab (pDemanda ,  tabDem, strDem, tpTabDemanda );
			abrirTab (pEndereco ,  tabEnd, strEnd, tpTabEndereco );
				abrirTab (pInterferencia ,  tabInt, strInt, tpTabInterferencia );
					abrirTab (pUsuario ,  tabUs, strUs, tpTabUsuario );
		
		
		
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
