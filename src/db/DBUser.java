package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Todouser;
import customTools.DBUtil;

public class DBUser
{
	public static long login(Todouser user)
	{
		//method to return the userId
		
		//get user name
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT d.userId FROM Todouser d WHERE d.userName = :userName AND d.userPassword = :password";
		try
		{
			long l = (long) em.createQuery(query)
					.setParameter("userName", user.getUserName())
					.setParameter("password", user.getUserPassword())
					.getSingleResult();
			return l;
		}
		catch(Exception e)
		{
			return 0;
		}
		finally
		{
			em.close();
		}
	}
	

	
	public static boolean isAvailable(Todouser user)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT count(d.userId) FROM Todouser d WHERE UPPER(d.userName) = :userName";
		try
		{
			long l = (long) em.createQuery(query)
					.setParameter("userName", user.getUserName().toUpperCase())
					.getSingleResult();
			if (l>0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			em.close();
		}
	}
	
	public static Todouser getUser(long userId)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
			Todouser user = em.find(Todouser.class, userId);
			return user;
		}
		finally
		{
			em.close();
		}
	}
	
	public static List<Todouser> getAllUsers()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryStr = "SELECT u FROM Todouser u";
		List<Todouser> users = null;
		try
		{
			Query query = em.createQuery(queryStr);
			users =  query.getResultList();
			System.out.println("size = " + users.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		return users;
	}
	
	public static void insert(Todouser user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.persist(user);
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

	public static void update(Todouser user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.merge(user);
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

	public static void delete(Todouser user) 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin(); 
		try 
		{
			em.remove(em.merge(user));
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
