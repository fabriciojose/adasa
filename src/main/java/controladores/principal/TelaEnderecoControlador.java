package controladores.principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entidades.Demanda;
import entidades.Endereco;
import entidades.Processo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import principal.Componentes;

public class TelaEnderecoControlador implements Initializable {
	
	@FXML Pane pTelaEndereco;
	
	Pane pDemanda;
		Label lblDemanda;
			Button btnDemanda;

				ArrayList<Node> listNodesDemanda = new ArrayList<Node>();
				
				
				Pane pDadosEndereco;
					TextField tfLogradouro;
						ComboBox<String> cbRA;
							TextField tfCEP;
								TextField tfCidade;
									ComboBox<String> cbUF;
										TextField tfLatitude;
											TextField tfLongitude;
												Button btnLatLon;
											
												ArrayList<Node> listNodeDadosEndereco = new ArrayList<Node>();
										
												 Pane pPersistencia;
												  	Button btnNovo;
												  		Button btnSalvar;
												  			Button btnEditar;
												  				Button btnExcluir;
												  					Button btnCancelar;
												  						Button btnPesquisar;
												  						
												  							TextField tfPesquisar;
												  							
												  								ArrayList<Node> listNodesPersistencia= new ArrayList<Node>();
												  								
												 
	//-- TableView endereco --//
	private TableView <Endereco> tvLista = new TableView<>();

	TableColumn<Endereco, String> tcDesEnd = new TableColumn<>("Endereço");
	TableColumn<Endereco, String> tcEndRA = new TableColumn<>("Região Administrativa");
	TableColumn<Endereco, String> tcEndCid = new TableColumn<>("CEP");
												
	int intRA = 1;
	String strRA = "Plano Piloto";
	
	final int [] listaRA = new int [] {
			
			20	,
			4	,
			19	,
			9	,
			11	,
			31	,
			2	,
			10	,
			28	,
			27	,
			18	,
			16	,
			8	,
			7	,
			24	,
			6	,
			1	,
			15	,
			17	,
			21	,
			12	,
			13	,
			14	,
			25	,
			29	,
			5	,
			26	,
			22	,
			3	,
			23	,
			30	,

	};
	
	ObservableList<String> olEndRA = FXCollections
			.observableArrayList(
					
					"Águas Claras"	,
					"Brazlândia"	,
					"Candangolândia"	,
					"Ceilândia"	,
					"Cruzeiro"	,
					"Fercal"	,
					"Gama"	,
					"Guará"	,
					"Itapoã"	,
					"Jardim Botânico"	,
					"Lago Norte"	,
					"Lago Sul"	,
					"Núcleo Bandeirante"	,
					"Paranoá"	,
					"Park Way"	,
					"Planaltina"	,
					"Plano Piloto"	,
					"Recanto das Emas"	,
					"Riacho Fundo"	,
					"Riacho Fundo II"	,
					"Samambaia"	,
					"Santa Maria"	,
					"São Sebastião"	,
					"SCIA/Estrutural"	,
					"SIA"	,
					"Sobradinho"	,
					"Sobradinho II"	,
					"Sudoeste/Octogonal"	,
					"Taguatinga"	,
					"Varjão"	,
					"Vicente Pires"	


					); 	
	
	ObservableList<String> olEndUF = FXCollections
			.observableArrayList("DF" , "GO", "Outro");
	
	TelaEnderecoControlador tabEndCon;
	
	/* array de posicoes prefWidth prefHeight Layout Y e X */
	Double prefSizeWHeLayXY [][];
	
	Componentes com;
	
	ObservableList<Endereco> obsList = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		tabEndCon = this;
		
		pTelaEndereco.setStyle("-fx-background-color:#FFFFFF;");
	    
		listNodesDemanda.add(pDemanda = new Pane());
			listNodesDemanda.add(new Label("DEMANDA:"));
				listNodesDemanda.add(lblDemanda = new Label());
					listNodesDemanda.add(btnDemanda = new Button("<<<"));
	    
	    prefSizeWHeLayXY = new Double [][] { 
    		{930.0,60.0,10.0,10.0}, 
    			{75.0,30.0,11.0,15.0}, 
    				{750.0,30.0,87.0,15.0}, 
    					{75.0,20.0,844.0,18.0}};
	    	
	    com = new Componentes();
	    com.popularTela(listNodesDemanda, prefSizeWHeLayXY, pTelaEndereco);
	    
	    
	    listNodeDadosEndereco.add(pDadosEndereco = new Pane());
	    	listNodeDadosEndereco.add(new Label("ENDEREÇO DO EMPREENDIMENTO:"));
	    	listNodeDadosEndereco.add(tfLogradouro = new TextField());
	    		listNodeDadosEndereco.add(new Label("REGIÃO ADMINISTRATIVA:"));
	    		listNodeDadosEndereco.add(cbRA = new  ComboBox<>());
	    			listNodeDadosEndereco.add(new Label("CEP:"));
	    			listNodeDadosEndereco.add(tfCEP = new TextField());
					    listNodeDadosEndereco.add(new Label("CIDADE:"));
					    listNodeDadosEndereco.add(tfCidade = new TextField());
						    listNodeDadosEndereco.add(new Label("UF:"));
							listNodeDadosEndereco.add(cbUF = new  ComboBox<>());
								listNodeDadosEndereco.add(new Label("LATITUDE (Y):"));
							    listNodeDadosEndereco.add(tfLatitude = new TextField());
							    	listNodeDadosEndereco.add(new Label("LONGITUDE(X):"));
								    listNodeDadosEndereco.add(tfLongitude = new TextField());
								    	listNodeDadosEndereco.add(btnLatLon = new Button());
	    
	
	    prefSizeWHeLayXY = new Double [][] { 
			{900.0,130.0,25.0,83.0}, 
			
				{380.0,30.0,16.0,0.0}, 
				{380.0,30.0,16.0,30.0}, // tfLogradouro
					{150.0,30.0,407.0,0.0}, 
					{150.0,30.0,407.0,30.0}, // comboBox RA
						{100.0,30.0,570.0,0.0}, 
						{100.0,30.0,570.0,30.0}, // tfCEP
						
							{90.0,30.0,682.0,0.0}, 
							{90.0,30.0,682.0,30.0}, // tfCidade
							
								{100.0,30.0,784.0,0.0}, 
								{100.0,30.0,784.0,30.0}, // comobobx UF
						
									{100.0,30.0,185.0,78.0}, 
									{140.0,30.0,284.0,78.0},
									
										{100.0,30.0,435.0,78.0}, 
										{140.0,30.0,538.0,78.0},
				
											{25.0,25.0,689.0,81.0},
	    };
	    	
	    com = new Componentes();
	    com.popularTela(listNodeDadosEndereco, prefSizeWHeLayXY, pTelaEndereco);
	    
	    
	    listNodesPersistencia.add(pPersistencia = new Pane());
	    listNodesPersistencia.add(btnNovo = new Button("NOVO"));
	    listNodesPersistencia.add(btnSalvar = new Button("SALVAR"));
	    listNodesPersistencia.add(btnEditar = new Button("EDITAR"));
	    listNodesPersistencia.add(btnExcluir = new Button("EXCLUIR"));
	    listNodesPersistencia.add(btnCancelar = new Button("CANCELAR"));
	    listNodesPersistencia.add(tfPesquisar = new TextField());
	    listNodesPersistencia.add(btnPesquisar = new Button("PESQUISAR"));
	    
	    prefSizeWHeLayXY = new Double[][] { 
	    	{900.0,60.0,25.0,225.0}, 
	    		{85.0,20.0,15.0,15.0}, 
	    			{85.0,20.0,111.0,15.0}, 
	    				{85.0,20.0,207.0,15.0}, 
					    	{85.0,20.0,303.0,15.0}, 
					    		{85.0,20.0,399.0,15.0}, 
					    			{295.0,20.0,495.0,15.0}, 
					    				{85.0,20.0,801.0,15.0} }; 
					    				
					    				 com = new Componentes();
					    				    com.popularTela(listNodesPersistencia, prefSizeWHeLayXY, pTelaEndereco);
	    
	    // para trazer o valor da entidade principal, no caso Endereco
	    tcDesEnd.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endLogradouro"));
		// para trazer valor de outra entidade, no caso RA
	    tcEndRA.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Endereco, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Endereco, String> e) {
		    	return new SimpleStringProperty(e.getValue().getEndRAFK().getRaNome());
		       
		    }
		});
		
		tcDesEnd.setPrefWidth(409);
		tcEndRA.setPrefWidth(232);
		tcEndCid.setPrefWidth(232);
			
	    tvLista.getColumns().add(tcDesEnd);
	    tvLista.getColumns().add(tcEndRA);
	    tvLista.getColumns().add(tcEndCid);
	  
	    tvLista.setItems(obsList);
	    
	    tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(25);
		tvLista.setLayoutY(305);
		
		pTelaEndereco.getChildren().add(tvLista);
		
		
    
	    acionarBotoes ();
		
	} // FIM INITIALIZE
	
	public void acionarBotoes () {
		  

	    btnDemanda.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	TabDemandaControlador.tabDemCon.movimentarTelaEndereco(15.0);
	        }
	    });
	  
  }
	
	Object objetoDeEdicao = new Object();
	
	public void setObjetoDeEdicao (Object objetoDeEdicao) {
		
		EditarEnderecoControlador.objetoDeEdicao = objetoDeEdicao;
		
	}
	
	

}
