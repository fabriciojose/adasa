package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Registro {


	public ArrayList<String> lerRegistro () throws IOException {

		BufferedReader lerArq = new BufferedReader(new InputStreamReader(Registro.class.getClass().getResourceAsStream("/propriedades/propriedadesUsuario.txt")));

		String linha = lerArq.readLine(); // l� a primeira linha

		ArrayList<String> resultado = new ArrayList<String>();

		while (linha != null) {

			linha = lerArq.readLine(); // da segunda ate a ultima linha

			resultado.add(linha);
		}
		
		lerArq.close();

		return resultado;

	}
	
	public void salvarRegistro (List<String> str) throws URISyntaxException, IOException {

       //File file = new File(new InputStreamReader(Registro.class.getClass().getResourceAsStream("/propriedades/propriedadesUsuario.txt")).toString());
        
      //  File file = new File(Registro.class.getClass().getResourceAsStream("/propriedades/propriedadesUsuario.txt"));
		
		PrintWriter gravarArq = 
	               new PrintWriter(
	                     new File(Registro.class.getResource("/src/main/resources/propriedades/propriedadesUsuario.txt").getPath()));
		

        System.out.println("salvar registr local arquivo file " + Registro.class.getResource("/propriedades/propriedadesUsuario.txt").getPath());

		//PrintWriter gravarArq = new PrintWriter(file);

		gravarArq.println("Registros - Escolhas do Usuario");
		
		for (String s : str) {
			gravarArq.println(s);
			
			System.out.println("metodo salvar Registro - linhas salvas \n " + s);
		}
		
		gravarArq.flush();
		gravarArq.close();
		//arq.close();

	}

}
