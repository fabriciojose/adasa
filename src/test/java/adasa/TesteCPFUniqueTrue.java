package adasa;

import java.util.List;

import dao.UsuarioDao;
import entidades.Endereco;
import entidades.Usuario;

public class TesteCPFUniqueTrue {

	public static void main(String[] args) {

			Endereco end = new Endereco();
			
			end.setEndID(6);
			
			
			Usuario us = new Usuario("Chevrolet", new String( "777555666"));
			
			
			UsuarioDao usDao = new UsuarioDao();
			
		    usDao.salvarUsuario(us);
		
		/*
		//UsuarioDao usDao = new UsuarioDao();
		
		List<Usuario> list = usDao.listarUsuario("");
		
		for (Usuario u : list) {
			System.out.println(u.getUsNome());
		}
		*/
	
			

	}

}
