package br.com.comparecell.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.comparecell.Util.HibernateUtil;
import br.com.comparecell.entidades.Aparelho;
import br.com.comparecell.entidades.Rank;

public class CompareCellDAOImpl extends HibernateUtil implements CompareCellDAO {
	
	private Session session = getSessionFactory().openSession(); 

	
	public void gravarRank(Rank k) throws Exception{
		 
		
			session = getSessionFactory().openSession(); 
		
		
		try{
			session.saveOrUpdate(k);
			session.beginTransaction().commit();
		    session.close();
		    
		}catch(Exception d){
			throw new Exception(d);
		
		}
	}
	
	public List<Aparelho> listarAparelhos(){
		
		
		
		Criteria l = session.createCriteria(Aparelho.class);
		List<Aparelho> retorno =  l.list();
		return retorno;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Rank> listarRank(){
		
		Criteria l = session.createCriteria(Rank.class).addOrder(Order.desc("rankField"));
		return l.list();
		
		
	}
	
	
	public Rank recuperaCell(Integer cod){
		
		Criteria l = session.createCriteria(Rank.class);
		l.add(Restrictions.eq("aparelho.id", cod));
		return (Rank) l.uniqueResult();
	}
}
