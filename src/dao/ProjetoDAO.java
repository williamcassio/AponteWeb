package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entity.Projeto;
import service.ProjetoService;
import utils.HibernateUtil;
import utils.MensagensUtil;

public class ProjetoDAO implements ProjetoService {
	private Session session;

	private static ProjetoDAO uniqueInstance;

	private ProjetoDAO() {

	}

	public static synchronized ProjetoDAO getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ProjetoDAO();
		}
		return uniqueInstance;
	}

	@Override
	public int salvar(Projeto projeto) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(projeto);
			session.getTransaction().commit();
			session.close();
			return projeto.getId();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}
	}

	@Override
	public int atualiza(Projeto projeto) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.update(projeto);
			session.getTransaction().commit();
			session.close();
			return projeto.getId();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}	}

	@Override
	public void excluir(Projeto projeto) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.delete(projeto);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
		}

	}

	@Override
	public Projeto carregar(Integer id) {
		Projeto projeto;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		projeto = (Projeto) session.get(Projeto.class, id);
		session.getTransaction().commit();
		session.close();
		return projeto;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> listar() {
		List<Projeto> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Projeto.class);
		cr.addOrder(Order.asc("sigla"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}

}
