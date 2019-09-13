package util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import entidades.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import principal.Componentes;
import principal.MalaDiretaAnexoParecer;

public class NavegadorExterno {

	Pane pNavegadorExterno;
	
	Button btnBrowser;
	Button bntDocumentoHTML;

	ObservableList<Integer> obsListIframe = FXCollections
			.observableArrayList(0,1,2);
	ComboBox<Integer> cbIframe; 

	ComboBox<String> cbUsuarios;
	
	Button btnDocumentoAnexo;
	Button btnWebDriver;

	Componentes com;
	ArrayList<Node> listaDeComponentes;
	Double prefSizeWHeLayXY [][];

	String strWebDriver;
	Registro r;

	// Navegador Externo
	WebDriver driver;

	String strHTML;
	
	Pane p1;
	
	public NavegadorExterno (Pane p1) {
		this.p1 = p1;
	}

	public void  inicializarNavegadorExterno (Double layoutX, Double layoutY) {

		listaDeComponentes = new ArrayList<Node>();

		listaDeComponentes.add(pNavegadorExterno = new Pane());

		listaDeComponentes.add(btnBrowser = new Button("Chrome"));
		listaDeComponentes.add(bntDocumentoHTML = new Button("Inserir"));
		listaDeComponentes.add(cbIframe = new ComboBox<Integer>(obsListIframe));
		listaDeComponentes.add(cbUsuarios = new ComboBox<String>(this.obsListUsuariosMalaDireta));
		listaDeComponentes.add(btnDocumentoAnexo = new Button("Anexo"));
		listaDeComponentes.add(btnWebDriver = new Button("Driver"));
		
		// Layout do Pane layoutX,layoutY

		prefSizeWHeLayXY = new Double [][]  { 

			{930.0,70.0,layoutX,layoutY},
		
			{70.0,25.0,5.0,20.0},
			{70.0,25.0,88.0,20.0},
			{70.0,25.0,163.0,20.0},
			{530.0,25.0,238.0,20.0},
			{70.0,25.0,773.0,20.0},
			{60.0,15.0,865.0,20.0},
		}; 

		//posicao do pane 25.0,772.0},
		// string html


		com = new Componentes();
		com.popularTela(listaDeComponentes, prefSizeWHeLayXY, p1);

		cbIframe.setValue(2);


		btnBrowser.setOnAction((event) -> {

			if (strWebDriver == null) {

				System.out.println(strWebDriver == null);

				r = new Registro();
				List<String> strList = null;

				try {

					strList = r.lerRegistro();

					for (String s : strList) {
						System.out.println("strings " + s);
					}

				} catch (IOException e2) {

					e2.printStackTrace();
				}

				if(strList.size() != 0) {
					strWebDriver = strList.get(0);
				}

				System.out.println(strWebDriver);

			} // fim if strWebDriver == null


			System.setProperty("webdriver.chrome.driver", strWebDriver);

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-infobars");
			options.addArguments("start-maximized");

			driver = new ChromeDriver(options);

			driver.navigate().to("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");


		}); // btnBrowser


		// ação do botão para receber o excel
		btnWebDriver.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				r = new Registro();
				List<String> strList = null;

				try {
					strList = r.lerRegistro();
				} catch (IOException e2) {

					e2.printStackTrace();
				}

				if(strList.size() != 0) {
					strWebDriver = strList.get(1);
				}

				if (strWebDriver == null) {

					// para escolher o arquivo  no computador
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Selecione o Web Driver");
					fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXE" , "*.exe"));        
					File file = fileChooser.showOpenDialog(null);

					strWebDriver = file.toString();

					r = new Registro();

					try {
						r.salvarRegistro(strWebDriver);

					} catch (URISyntaxException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}


				}
			}


		}); // fim btnWebBrowser


		bntDocumentoHTML.setOnAction((event) -> {

			Set<String> s1 = driver.getWindowHandles();

			List<String> strList = new ArrayList<>();

			for (String s : s1) {
				strList.add(s);
			}

			driver.switchTo().window(strList.get(1));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			System.out.println("cbiframe value " + cbIframe.getValue());

			System.out.println(strHTML);

			// retirar todas as haspas duplas "
			strHTML = strHTML.replace("\"", "");
			// trocar todas a haspa simples ' por haspas duplas "
			strHTML = strHTML.replace("'", "\"");

			js.executeScript("document.getElementsByTagName('iframe')['"+cbIframe.getValue()+"'].contentDocument.body.innerHTML = '"+strHTML+"';");
		

		});
		
		// selecionar o usuario que será impresso no anexo do paracer coletivo
		cbUsuarios.getSelectionModel().selectedIndexProperty().addListener(new
 	            ChangeListener<Number>() {
 	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
 	    		Number value, Number new_value) {
 	    		
 	    		if ( (Integer) new_value !=  -1)
 	    		int_interferencia_selecionada = (int) new_value;
 	    		
             }
 	    });
		
		
		btnDocumentoAnexo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	MalaDiretaAnexoParecer anexo = new MalaDiretaAnexoParecer(listMalaDireta, strAnexoParecer, strTabela1, strTabela2);
            	String strAnexo = anexo.criarAnexoParecer(int_interferencia_selecionada);
            	
            	System.out.println("anexo criar anexo " + strAnexo);
            	

    			// retirar todas as haspas duplas "
            	strAnexo = strAnexo.replace("\"", "");
    			// trocar todas a haspa simples ' por haspas duplas "
            	strAnexo = strAnexo.replace("'", "\"");
          
            	
            	Set<String> s1 = driver.getWindowHandles();

    			List<String> strList = new ArrayList<>();

    			for (String s : s1) {
    				strList.add(s);
    			}

    			driver.switchTo().window(strList.get(1));

    			JavascriptExecutor js = (JavascriptExecutor) driver;
    			
    			//driver.findElements(By.tagName("anexo_parecer_tag")).get(0).sendKeys("<b>bola</b>");
            	
    			// var a = document.getElementsByTagName('iframe')[2];
    			// var b = a.contentDocument.getElementsByTagName('anexo_parecer_tag')[0].innerHTML = '<b>bula</b>';
    			
    			
        		//-- imprimir o relatório ou tn no editor do SEI --//
    			js.executeScript("var a = document.getElementsByTagName('iframe')['"+cbIframe.getValue()+"'];"
    					+ "var b = a.contentDocument.getElementsByTagName('anexo_parecer_tag')[0].innerHTML = '"+ strAnexo +"';");
					
            }
        });

	}
	
	public void setarStringHTML (String strHTML) {
		this.strHTML = strHTML;
	}
	
	String strAnexoParecer;
	
	String strTabela1;
	String strTabela2;
	
	ObservableList<String> obsListUsuariosMalaDireta;
	
	String strAnexo;
	
	List<Object[][]> listMalaDireta = new ArrayList<>();
	
	// selecionar qual anexo ira para o parecer coletivo
	int int_interferencia_selecionada = 0;
	
	public void setObjetosAnexo (List<Object[][]> listMalaDireta, ObservableList<String>  obsListUsuariosMalaDireta, String strAnexoParecer, String strTabela1, String strTabela2) {
		this.listMalaDireta = listMalaDireta;
		this.obsListUsuariosMalaDireta = obsListUsuariosMalaDireta;
		this.strAnexoParecer = strAnexoParecer;
		this.strTabela1 = strTabela1;
		this.strTabela2 = strTabela2;
		
		cbUsuarios.setItems(this.obsListUsuariosMalaDireta);
		
	}
	

	
}
