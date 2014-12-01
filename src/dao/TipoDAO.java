package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import service.TipoService;
import utils.HibernateUtil;
import utils.MensagensUtil;
import entity.Tipo;

public class TipoDAO implements TipoService {
	private Session session;

	private static TipoDAO uniqueInstance;

	private TipoDAO() {

	}

	public static synchronized TipoDAO getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TipoDAO();
		}
		return uniqueInstance;
	}

	@Override
	public int salvar(Tipo tipo) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(tipo);
			session.getTransaction().commit();
			session.close();
			return tipo.getId();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}

	}

	@Override
	public int atualiza(Tipo tipo) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.update(tipo);
			session.getTransaction().commit();
			session.close();
			return tipo.getId();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return 0;
		}

	}

	@Override
	public void excluir(Tipo tipo) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.delete(tipo);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
		}

	}

	@Override
	public Tipo carregar(Integer id) {
		Tipo tipo;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		tipo = (Tipo) session.get(Tipo.class, id);
		session.getTransaction().commit();
		session.close();
		return tipo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> listar() {
		List<Tipo> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Tipo.class);
		cr.addOrder(Order.asc("descricao"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}
}
