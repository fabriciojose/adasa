package adasa

import java.util.List
import org.controlsfx.control.textfield.TextFields
import dao.BancoAccessDao
import entidades.BancoAccess
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.util.StringConverter

class TextFieldAutoComplete extends Application {
	def static void main(String[] args) {
		launch(args)
	}

	override void start(Stage stage) throws Exception {
		var BancoAccessDao bDao = new BancoAccessDao()
		var List<BancoAccess> docList = bDao.listarBancoAccess("")
		var ObservableList<String> obsList = FXCollections::observableArrayList()
		if (!obsList.isEmpty()) {
			obsList.clear()
		}
		for (var int i = 0; i < docList.size(); i++) {
			obsList.add(docList.get(i).getBaInteressado())
		}
		var Pane p = new Pane()
		var TextField tf = new TextField()
		tf.setPrefSize(450, 20)
		tf.textProperty().addListener([observable, oldValue, newValue |
			{
				System::out.println('''textfield changed from «oldValue» to «newValue»'''.toString)
			}
		])
		TextFields.bindAutoCompletion(tf, obsList).setMinWidth(450.0);
		p.getChildren().addAll(tf)
		var Scene scene = new Scene(p, 450, 250)
		stage.setScene(scene)
		stage.show()
	}
	/*
	 *  backup combobox 
	 *  
	 *  	ComboBox<BancoAccess> cb = new ComboBox<>();
	 * 	cb.setPrefSize(450, 20);
	 * 	cb.setLayoutY(40);
	 * 	cb.setItems(obsList);
	 * 
	 * 	
	 * 	 // converter o objeto outorga e uma string com o nome, tipo de outorga etc
	 * 	cb.setConverter(new StringConverter<BancoAccess>() {
	 * 		
	 * 		public String toString(BancoAccess b) {
	 * 			
	 * 			return b.getBaInteressado();
	 * 		}
	 * 		
	 * 		public BancoAccess fromString(String string) {
	 * 			
	 * 			return null;
	 * 		}
	 * 	});


	 * 	cb.setEditable(true);
	 * 
	 *  
	 *  
	 *  
	 */
/* trazer para a coluna o uma parte do objeto endereco, o logradouro
 *  
 *  
 *  	
 * 	tcEndereco.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Documento, String>, ObservableValue<String>>() {
 * 	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Documento, String> d) {
 * 	    	return new SimpleStringProperty(d.getValue().getDocEnderecoFK().getEndLogradouro());
 * 	       
 * 	    }
 * 	});
 * 	
 */
}
