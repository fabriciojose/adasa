package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import entidades.Documento;
import entidades.HibernateUtil;
import entidades.Parecer;
import entidades.Vistoria;

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
	public List<Documento> listarDocumentos (String strPesquisa) {
		
		List<Documento> list = new ArrayList<Documento>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
			s.beginTransaction();
		
		Criteria crit = s.createCriteria(Documento.class, "d");
		
		crit.createAlias("d.docEnderecoFK" , "e", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("e.endRAFK", "ra", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("d.docProcessoFK", "p", JoinType.LEFT_OUTER_JOIN);

		
		Criterion docTipo = Restrictions.like("docTipo", '%' + strPesquisa + '%');
		Criterion docNumero = Restrictions.like("docNumeracao", '%' + strPesquisa + '%');
		Criterion docSEI = Restrictions.like("docSEI", '%' + strPesquisa + '%');
		Criterion docProcesso = Restrictions.like("docProcesso", '%' + strPesquisa + '%');
		
		
		Disjunction orExp = Restrictions.or(docTipo, docNumero,docSEI, docProcesso);
		
		// adicionar os critérios e garantir resultados não  repetidos
		crit.add(orExp).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		list = crit.list();
		
		
		s.getTransaction().commit();
		s.close();
		return list;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Documento> listarParecer (String strPesquisa) {
		
		List<Documento> list = new ArrayList<Documento>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		Criteria crit = s.createCriteria(Parecer.class, "d");

		crit.createAlias("d.usuarios" , "usuarios", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("d.usuarios.enderecos" , "usEnderecos", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("usEnderecos.interferencias" , "usInterferencias", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usEnderecos.endRAFK", "regiaoAdm", JoinType.LEFT_OUTER_JOIN);
			
			//crit.createAlias("usEnderecos.documentos", "endDocumentos", JoinType.LEFT_OUTER_JOIN);
			
			// join relacionado com os usuários relacionados ao documento //
				
			crit.createAlias("usInterferencias.interTipoInterferenciaFK", "tipoInter", JoinType.LEFT_OUTER_JOIN);
			
			crit.createAlias("usInterferencias.interTipoOutorgaFK", "tipoOutorga", JoinType.LEFT_OUTER_JOIN); 
			crit.createAlias("usInterferencias.interSubtipoOutorgaFK", "SubTipoOutorga", JoinType.LEFT_OUTER_JOIN); 
			crit.createAlias("usInterferencias.interTipoAtoFK", "tipoAto", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usInterferencias.interSituacaoProcessoFK", "situacaoProcesso", JoinType.LEFT_OUTER_JOIN);
			
			crit.createAlias("usInterferencias.interBaciaFK", "baciaInter", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usInterferencias.interUHFK", "unidaHidInter", JoinType.LEFT_OUTER_JOIN);
			
			crit.createAlias("usInterferencias.subTipoPocoFK", "tipoPoco", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usInterferencias.subSubSistemaFK", "subSistema", JoinType.LEFT_OUTER_JOIN);
			
			crit.createAlias("usInterferencias.supFormaCaptacaoFK", "formaCaptacao", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usInterferencias.supLocalCaptacaoFK", "localCaptacao", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("usInterferencias.supMetodoIrrigacaoFK", "metodoIrrigacao", JoinType.LEFT_OUTER_JOIN);
		
		//crit.createAlias("usEnderecos.endUsuarioFK", "endUsuarioFK", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("d.docProcessoFK", "p", JoinType.LEFT_OUTER_JOIN);
		
		Criterion docTipo = Restrictions.like("docTipo", '%' + strPesquisa + '%');
		Criterion docNumero = Restrictions.like("docNumeracao", '%' + strPesquisa + '%');
		Criterion docSEI = Restrictions.like("docSEI", '%' + strPesquisa + '%');
		Criterion docProcesso = Restrictions.like("docProcesso", '%' + strPesquisa + '%');
		
		Disjunction orExp = Restrictions.or(docTipo, docNumero,docSEI, docProcesso);
		
		// adicionar os critérios e garantir resultados não  repetidos
		crit.add(orExp).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		list = crit.list();
		
		
		s.getTransaction().commit();
		s.close();
		return list;
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Documento> listarAtosOutorga (String strPesquisa) {
		

		List<Documento> list = new ArrayList<Documento>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
			s.beginTransaction();
		
		Criteria crit = s.createCriteria(Documento.class, "d");
		
		crit.createAlias("d.docEnderecoFK" , "e", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("e.endRAFK", "ra", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("d.docProcessoFK", "p", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("e.interferencias", "i", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("i.subTipoPocoFK", "tipoPoco", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.subSubSistemaFK", "subSistema", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("i.supFormaCaptacaoFK", "formaCaptacao", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.supLocalCaptacaoFK", "localCaptacao", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.supMetodoIrrigacaoFK", "metodoIrrigacao", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.interSituacaoProcessoFK", "situacaoProcesso", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.interTipoAtoFK", "tipoAto", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("i.interTipoInterferenciaFK", "tipoInter", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.interBaciaFK", "baciaInter", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("i.interUHFK", "unidaHidInter", JoinType.LEFT_OUTER_JOIN);
		
		crit.createAlias("i.interTipoOutorgaFK", "tipoOutorga", JoinType.LEFT_OUTER_JOIN);
		
		
		Criterion docTipo = Restrictions.like("docTipo", '%' + strPesquisa + '%');
		Criterion docNumero = Restrictions.like("docNumeracao", '%' + strPesquisa + '%');
		Criterion docSEI = Restrictions.like("docSEI", '%' + strPesquisa + '%');
		Criterion docProcesso = Restrictions.like("docProcesso", '%' + strPesquisa + '%');
		
		
		Disjunction orExp = Restrictions.or(docTipo, docNumero,docSEI, docProcesso);
		
		// adicionar os critérios e garantir resultados não  repetidos
		crit.add(orExp).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		list = crit.list();
		
		
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
