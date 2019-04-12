package controladores.principal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorAtendimento {
	
	@FXML Pane pAtendimento;
	@FXML TabPane tpAtendimento = new TabPane();
	
	@FXML Tab tpTabDemanda;
	Pane pDemanda_Aten = new Pane();
	TabDemandaControlador tabDem = new TabDemandaControlador();
	String strDem = "/fxml/principal/TabDemanda.fxml";
	
		@FXML Tab tpTabEndereco;
		Pane pEndereco_Aten = new Pane();
		TabEnderecoControlador tabEnd = new TabEnderecoControlador();
		String strEnd = "/fxml/principal/TabEndereco.fxml";

			@FXML Tab tpTabInterferencia;
			Pane pInterferencia_Aten = new Pane();
			TabInterferenciaControlador tabInt = new TabInterferenciaControlador();
			String strInt = "/fxml/principal/TabInterferencia.fxml";
			
				@FXML Tab tpTabUsuario;
				Pane pUsuario_Aten = new Pane();
				TabUsuarioControlador tabUs = new TabUsuarioControlador();
				String strUs = "/fxml/principal/TabUsuario.fxml";
	
	@FXML 
    private void initialize() {
		
		tpAtendimento.prefWidthProperty().bind(pAtendimento.widthProperty());
		tpAtendimento.prefHeightProperty().bind(pAtendimento.heightProperty());
		
		abrirTab (pDemanda_Aten ,  tabDem, strDem, tpTabDemanda );
			abrirTab (pEndereco_Aten ,  tabEnd, strEnd, tpTabEndereco );
				abrirTab (pInterferencia_Aten ,  tabInt, strInt, tpTabInterferencia );
					abrirTab (pUsuario_Aten ,  tabUs, strUs, tpTabUsuario );
		
		
		
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
