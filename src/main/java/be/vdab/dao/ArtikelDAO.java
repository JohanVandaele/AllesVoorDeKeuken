package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

// enkele imports ...

public class ArtikelDAO
{
	public Artikel read(long id)
	{
		// Je vraagt een EntityManager aan de servlet filter JPAFilter.
		EntityManager entityManager = JPAFilter.getEntityManager();

		try
		{
			// Je zoekt een Docent entity op de primary key met de find method.
			// De 1� parameter is het type van de op te zoeken entity
			// De 2� parameter is de primary key waarde van de op te zoeken entity
			return entityManager.find(Artikel.class, id);
		}
		finally
		{
			entityManager.close();
		}
	}
}
