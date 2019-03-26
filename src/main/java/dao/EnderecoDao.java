package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import entidades.Endereco;
import entidades.HibernateUtil;


public class EnderecoDao {
	
	
	public void salvarEndereco (Endereco endereco) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(endereco);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listarEndereco(String strPesquisa) {
	
		List<Endereco> list = new ArrayList<Endereco>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		/*
		list = s.createQuery(
					"SELECT e FROM Endereco AS e "
				+	"JOIN e.demandas "
				+ 	"LEFT OUTER JOIN FETCH e.endRA "
				+ 	"WHERE (e.endLogradouro LIKE '%"+strPesquisa+"%')"
				).list();
				*/
		/*
		list = s.createQuery(
				"SELECT e FROM Endereco AS e "
			+	"JOIN e.demandas "
			+ 	"JOIN e.endRA "
			+ 	"WHERE (e.endLogradouro LIKE '%"+strPesquisa+"%')"
			).list();
		*/
		
		
		Criteria crit = s.createCriteria(Endereco.class, "e");
		//crit.createAlias("e.demandas", "d", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("e.endRAFK", "ra", JoinType.LEFT_OUTER_JOIN);
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
		
		crit.add(Restrictions.like("endLogradouro", '%' + strPesquisa + '%'))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = crit.list();
		
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public void removerEndereco(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Endereco e = (Endereco) s.load(Endereco.class, id);
		s.delete(e);
		s.getTransaction().commit();
		s.close();
	}

	public void editarEndereco(Endereco endereco) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(endereco);
		s.getTransaction().commit();
		s.close();
	}
	
	public void mergeEndereco (Endereco endereco) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.merge(endereco);
		s.getTransaction().commit();
		//s.flush(); // para retornar o id do objeto gravado
		s.close();
	}
	
	
}
