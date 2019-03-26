package controladores.fiscalizacao;

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

public class ControladorFiscalizacao {
	
	@FXML Pane pFiscalizacao;
	@FXML TabPane tpFiscalizacao = new TabPane();
	
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
	
					@FXML Tab tpTabVistoria;
					Pane pVistoria = new Pane();
					TabVistoriaControlador tabVis = new TabVistoriaControlador();
					String strVis = "/fxml/fiscalizacao/TabVistoria.fxml";
	
						@FXML Tab tpTabAto;
						Pane pAto = new Pane();
						TabAtoControlador tabAto = new TabAtoControlador();
						String strAto = "/fxml/fiscalizacao/TabAto.fxml";
	
	
	@FXML 
    private void initialize() {
		
		tpFiscalizacao.prefWidthProperty().bind(pFiscalizacao.widthProperty());
		tpFiscalizacao.prefHeightProperty().bind(pFiscalizacao.heightProperty());
		
		abrirTab (pDemanda ,  tabDem, strDem, tpTabDemanda );
			abrirTab (pEndereco ,  tabEnd, strEnd, tpTabEndereco );
				abrirTab (pInterferencia ,  tabInt, strInt, tpTabInterferencia );
					abrirTab (pUsuario ,  tabUs, strUs, tpTabUsuario );
						abrirTab (pVistoria ,  tabVis, strVis, tpTabVistoria );
							abrirTab (pAto ,  tabAto, strAto, tpTabAto );
			
		
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
		
		p.minWidthProperty().bind(pFiscalizacao.widthProperty());
		p.minHeightProperty().bind(pFiscalizacao.heightProperty());
		
		t.setContent(p);
	}

}



/*
Pane pVistoria = new Pane();

tabVis = new TabVistoriaControlador();

FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/TabVistoria.fxml"));

loader.setRoot(pVistoria);
loader.setController(tabVis);
try {
	loader.load();
} catch (IOException e) {
	System.out.println("erro na abertura do pane vistoria");
	e.printStackTrace();
}

pVistoria.minWidthProperty().bind(pFiscalizacao.widthProperty());
pVistoria.minHeightProperty().bind(pFiscalizacao.heightProperty());

tpTabVistoria.setContent(pVistoria);
*/


/*
tpFiscalizacao.widthProperty().addListener(new ChangeListener<Number>() {
	   
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	//tabVistoria.redimWid (newValue);
    	
    	System.out.println("bind tpFisc " + newValue);
    	
    }
});
*/



/*
if (tabDemandaControlador == null) {
	
	tabDemandaControlador = new TabDemandaControlador();
}
*/

/*
pFiscalizacao.widthProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	
    	System.out.println("largura p fiscalizacao "  + pFiscalizacao.getWidth());
    	//tabDemandaControlador.redimWid (newValue);
    	
    }
});

pFiscalizacao.heightProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	
    	System.out.println("altura p fiscalizacao "  + pFiscalizacao.getHeight());
    	//tabDemandaControlador.redimHei (newValue);
    	
    }
});
*/


/*
p = new Pane();

tabDemandaControlador = new TabDemandaControlador();

FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/TabDemanda.fxml"));

//loader.setRoot(p);
loader.setController(tabDemandaControlador);

try {
	loader.load();
} catch (IOException e) {
	e.printStackTrace();
}
*/


/*
tabDemandaControlador = new TabDemandaControlador();

pFiscalizacao.widthProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	// Demanda //
    	tabDemandaControlador.redimensionarLargura(newValue);
    	
    }
});



pFiscalizacao.heightProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	// Demanda //
    	tabDemandaControlador.redimensionarAltura(newValue);
    	
    }
});
*/
