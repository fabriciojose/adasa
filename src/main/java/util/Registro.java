package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Registro {


	public ArrayList<String> lerRegistro () throws IOException {

		BufferedReader lerArq = new BufferedReader(new InputStreamReader(Registro.class.getClass().getResourceAsStream("/propriedades/propriedadesUsuario.txt")));

		String linha = lerArq.readLine(); // l� a primeira linha

		ArrayList<String> resultado = new ArrayList<String>();

		while (linha != null) {

			linha = lerArq.readLine(); // da segunda ate a ultima linha

			resultado.add(linha);
		}

		return resultado;

	}
	
	public void salvarRegistro (String str) throws URISyntaxException, IOException {

        File file = new File(Registro.class.getClass().getResourceAsStream("/propriedades/propriedadesUsuario.txt").toString());

		FileWriter arq = new FileWriter(file);
		
		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.println("Registros - Escolhas do Usuario");
		
		gravarArq.println(str);

		arq.close();


	}

}
