package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Todoitem;
import model.Todouser;
import customTools.DBUtil;

public class DBItem
{
	public static List<Todoitem> getAllActiveItems(Todouser user)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryStr = "SELECT u FROM Todoitem u WHERE u.todouser = :user AND u.statusCode = :statusCode ORDER BY u.itemPriority ASC, u.dueDate DESC";
		List<Todoitem> items = null;
		try
		{
			Query query = em.createQuery(queryStr)
					.setParameter("user", user)
					.setParameter("statusCode", "TODO");
			items =  query.getResultList();
			System.out.println("size = " + items.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		return items;
	}
	
	public static Todoitem getItem(long itemId)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
			Todoitem user = em.find(Todoitem.class, itemId);
			return user;
		}
		finally
		{
			em.close();
		}
	}
	
	public static List<Todoitem> getAllItemsByUser(Todouser user)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryStr = "SELECT u FROM Todoitem u WHERE u.todouser = :user ORDER BY  u.dueDate DESC, u.completedDate DESC ";
		List<Todoitem> items = null;
		try
		{
			Query query = em.createQuery(queryStr)
					.setParameter("user", user);
			items =  query.getResultList();
			System.out.println("size = " + items.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		return items;
	}
	
	public static List<Todoitem> getAllItems()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryStr = "SELECT u FROM Todoitem u";
		List<Todoitem> items = null;
		try
		{
			Query query = em.createQuery(queryStr);
			items =  query.getResultList();
			System.out.println("size = " + items.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		return items;
	}
	
	public static void insert(Todoitem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.persist(item);
			trans.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			trans.rollback();
		} 
		finally 
		{
			em.close();
		}
	}

	public static void update(Todoitem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.merge(item);
			trans.commit();
		} catch (Exception e) 
		{
			e.printStackTrace();
			trans.rollback();
		} 
		finally 
		{
			em.close();
		}
	}

	public static void delete(Todoitem item) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.remove(em.merge(item));
			trans.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			trans.rollback();
		} finally 
		{
			em.close();
		} 
	}
}
