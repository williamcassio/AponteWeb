package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Apontamento;
import entity.Itens;
import service.ItensService;
import utils.HibernateUtil;
import utils.MensagensUtil;

public class ItensDAO implements ItensService {
	private Session session;
	
	private static ItensDAO uniqueInstance;
	
	private ItensDAO(){
		
	}
	
	public static synchronized ItensDAO getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new ItensDAO();
		}
		return uniqueInstance;
	}
	
	@Override
	public int salvar(Itens item) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.save(item);
			session.getTransaction().commit();
			session.close();
			return item.getId();
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}
	}

	@Override
	public int atualizar(Itens item) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.update(item);
			session.getTransaction().commit();
			session.close();
			return item.getId();
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}

	}

	@Override
	public void excluir(Itens item) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.delete(item);
			session.getTransaction().commit();
			session.close();
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
		}


	}

	@Override
	public Itens carregar(Integer id) {
		Itens item;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		item = (Itens) session.get(Itens.class, id);
		session.getTransaction().commit();
		session.close();
		return item;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Itens> listar(Apontamento aponte) {
		List<Itens> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Itens.class);
		cr.add(Restrictions.eq("apontamento", aponte));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}

}
