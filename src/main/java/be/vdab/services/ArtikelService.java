package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

// enkele imports ...

public class ArtikelService
{
	// DocentService gebruikt DocentDAO.
	private final ArtikelDAO artikelDAO = new ArtikelDAO();

	public Artikel read(long id)
	{
		// Je vraagt een EntityManager aan de servlet filter JPAFilter
		EntityManager entityManager = JPAFilter.getEntityManager();
		
		try
		{
			// Je roept de DAO layer op en geeft de EntityManager mee.
			return artikelDAO.read(id, entityManager);
		}
		finally
		{
			// Je sluit de EntityManager.
			entityManager.close();
		}
	}
	
	public void create(Artikel artikel)
	{
		EntityManager entityManager = JPAFilter.getEntityManager();
		
		try
		{
			entityManager.getTransaction().begin();
			artikelDAO.create(artikel, entityManager);
			entityManager.getTransaction().commit();
		}
		catch (RuntimeException ex)
		{
			entityManager.getTransaction().rollback();
			throw ex;
		}
		finally
		{
			entityManager.close();
		}
	}	
}
