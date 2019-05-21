package adasa;


import com.thoughtworks.xstream.XStream;

import entidades.Documento;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.Subterranea;
import entidades.Superficial;

public class RequerimentoXML {

	public static void main(String[] args) {
		
		XStream xstream = new XStream();
		
		xstream.alias("Documento", Documento.class);
		xstream.alias("Endereco", Endereco.class);
		xstream.alias("Interferencia", Interferencia.class);
		xstream.alias("Subterranea", Subterranea.class);
		xstream.alias("Superficial", Superficial.class);
		
		Endereco endereco = new Endereco("Rua das Andorinhas");

		//Subterranea subterranea = new Subterranea(15.0, 20, 31);

		//endereco.getInterferencias().add(subterranea);
			
		Documento doc = new Documento("Requerimento", "123456789", "987654321", "197.555.666/2015", endereco);
		
		String xml = xstream.toXML(doc);
		
		System.out.println(xml);
		

	}

}
