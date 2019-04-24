package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import entidades.Documento;
import entidades.HibernateUtil;

public class DocumentoDao {
	
public void salvarDocumento (Documento documento) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(documento);
		s.getTransaction().commit();
		s.flush();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> listarDocumentos(String strPesquisa) {
		
		List<Documento> list = new ArrayList<Documento>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		/*
		List<Documento> list = s.createQuery(
				"SELECT d FROM Documento AS d "
				+ "LEFT OUTER JOIN FETCH d.demEnderecoFK "
				+ "WHERE (d.demDocumento LIKE '%"+strPesquisa+"%' "
						+ "OR d.demDocumentoSEI LIKE '%"+strPesquisa+"%' OR d.demProcessoSEI LIKE '%"+strPesquisa+"%')"
				).list();
		
		
		*/
		s.beginTransaction();
		
		Criteria crit = s.createCriteria(Documento.class, "d");
		crit.createAlias("d.docEnderecoFK" , "e", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("e.endRAFK", "ra", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("d.docProcessoFK", "p", JoinType.LEFT_OUTER_JOIN);
		
		
		Criterion docTipo = Restrictions.like("docTipo", '%' + strPesquisa + '%');
		Criterion docNumero = Restrictions.like("docNumero", '%' + strPesquisa + '%');
		Criterion docSEI = Restrictions.like("docSEI", '%' + strPesquisa + '%');
		Criterion docProcesso = Restrictions.like("docProcesso", '%' + strPesquisa + '%');
		
		Disjunction orExp = Restrictions.or(docTipo, docNumero,docSEI, docProcesso);
		
		crit.add(orExp);
		
		//crit.add(Restrictions.like("demDocumento", '%' + strPesquisa + '%'));
		list = crit.list();
		
		// SQL list = s.createSQLQuery("SELECT * FROM Documento WHERE Documento_Denuncia LIKE '%strPesquisa%'").list();
		//list = s.createQuery("from Documento d where d.Documento_Denuncia= : strPesquisa").setString("strPesquisa",strPesquisa).list();
		
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public void removerDocumento(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Documento c = (Documento) s.load(Documento.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void editarDocumento(Documento documento) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(documento);
		s.getTransaction().commit();
		s.close();
	}
	
	public void mergeDocumento(Documento documento) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.merge(documento);
		s.getTransaction().commit();
		s.close();
	}
	
	public void persistDocumento(Documento documento) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.persist(documento);
		s.getTransaction().commit();
		s.close();
	}


}
