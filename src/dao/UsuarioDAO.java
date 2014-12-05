package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import service.UsuarioService;
import utils.HibernateUtil;
import utils.MensagensUtil;
import entity.Usuario;

public class UsuarioDAO implements UsuarioService {
	private Session session;
	
	private static UsuarioDAO uniqueInstance;
	
	private UsuarioDAO(){
		
	}
	
	public static synchronized UsuarioDAO getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new UsuarioDAO();
		}
		return uniqueInstance;
	}

	@Override
	public int salvar(Usuario usuario) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.save(usuario);
			session.getTransaction().commit();
			session.close();
			return 1;
		} catch(HibernateException e){
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			session.close();
			return 0;
		}
	}

	@Override
	public int atualizar(Usuario usuario) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.update(usuario);
			session.getTransaction().commit();
			session.close();
			return 1;
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			return 0;
		}
	}

	@Override
	public void excluir(Usuario usuario) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.getTransaction().begin();
			session.delete(usuario);
			session.getTransaction().commit();
			session.close();
		} catch(HibernateException e){
			session.getTransaction().rollback();
			session.close();
			MensagensUtil.mensagemErro(e.getMessage());
		}


	}

	@Override
	public Usuario carregar(Integer id) {
		Usuario usuario;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		usuario = (Usuario) session.get(Usuario.class, id);
		session.getTransaction().commit();
		session.close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar() {
		List<Usuario> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Usuario.class);
		cr.addOrder(Order.asc("nome"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;
	}
	
	public boolean login(String login, String senha){
		boolean bRetorno;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Usuario.class);
		cr.add(Restrictions.eq("login", login));
		cr.add(Restrictions.eq("senha", senha));
		bRetorno = cr.list().size() > 0;
		session.getTransaction().commit();
		session.close();		 		
		return bRetorno;
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		Usuario usuario;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Usuario.class);
		cr.add(Restrictions.eq("login", login));
		usuario = (Usuario) cr.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarRecebeEmail(){
		List<Usuario> lista;
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Criteria cr = session.createCriteria(Usuario.class);
		cr.add(Restrictions.eq("recebeemail", true));
		cr.addOrder(Order.asc("nome"));
		lista = cr.list();
		session.getTransaction().commit();
		session.close();
		return lista;		
	}
	
}
