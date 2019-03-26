package entidades;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import principal.LeituraTXT;

public class HibernateUtil {
	
	private static SessionFactory factory = null;
	
	private static Configuration conf;
	
	private static String newUserName;
	private static String newPassword;
	
	public static void getUserPass ( String newUserName, String newPassword) {
		
		HibernateUtil.newUserName = newUserName;
		HibernateUtil.newPassword = newPassword;
	}
	
	private static SessionFactory buildSessionFactory () {
		try {
		conf = new Configuration();
		conf.configure("/Hibernate.cfg.xml"); // /hibernate/Hibernate.cfg.xml
		// /resources/hibernate.cfg.xml  original: hibernate.cfg.xml
		
			LeituraTXT  leitura = new LeituraTXT();
				String strUserPass [] = new String[2];
					strUserPass = leitura.lerArquivo().split(",");
		
			conf.getProperties().setProperty("hibernate.connection.username",strUserPass[0]);
			conf.getProperties().setProperty("hibernate.connection.password",strUserPass[1]);
		
			System.out.println("Configurou!");
		
		factory = conf.buildSessionFactory();
		
			System.out.println("Construiu a fabrica de sessoes");
		
		return factory;
		
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory () {
		if (factory == null) 
			factory = buildSessionFactory();
		
		return factory;
	}
}