package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import service.ApontamentoService;
import utils.HibernateUtil;
import utils.MensagensUtil;
import entity.Apontamento;
import entity.Usuario;

public class ApontamentoDAO implements ApontamentoService {
	
	private Session session;
	
	private static ApontamentoDAO uniqueInstance;
	
	private ApontamentoDAO(){
		
	}
	
	public static synchronized ApontamentoDAO getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new ApontamentoDAO();
		}
		return uniqueInstance;
	}
	
	@Override
	public Apontamento salvar(Apontamento aponte) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.save(aponte);
			session.getTransaction().commit();
			session.close();
			return aponte;
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return null;
		}
	}

	@Override
	public Apontamento atualizar(Apontamento aponte) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.update(aponte);
			session.getTransaction().commit();
			session.close();
			return aponte;
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
			return null;
		}
	}

	@Override
	public void excluir(Apontamento aponte) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.delete(aponte);
			session.getTransaction().commit();
			session.close();
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
		}

	}

	@Override
	public Apontamento carregar(Integer id) {
		Apontamento aponte;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		aponte = (Apontamento) session.get(Apontamento.class, id);
		session.getTransaction().commit();
		session.close();
		return aponte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apontamento> listar(Usuario usuario) {
		List<Apontamento> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Apontamento.class);
		cr.add(Restrictions.eq("usuario", usuario));
		cr.addOrder(Order.desc("data"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Apontamento> listarPorData(Usuario usuario, Date di, Date df){
		List<Apontamento> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Apontamento.class);
		cr.add(Restrictions.eq("usuario", usuario));
		if (di != null && df != null){
			cr.add(Restrictions.between("data", di, df));
		} else if (di != null){
			cr.add(Restrictions.ge("data", di));
		} else if (df != null){
			cr.add(Restrictions.le("data", df));
		}
		cr.addOrder(Order.desc("data"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apontamento> listarGeral() {
		List<Apontamento> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Apontamento.class);
		cr.addOrder(Order.desc("data"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}

}
