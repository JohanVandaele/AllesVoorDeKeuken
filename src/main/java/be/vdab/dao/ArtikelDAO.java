package be.vdab.dao;

import java.math.BigDecimal;

import be.vdab.entities.Artikel;

public class ArtikelDAO extends AbstractDAO
{
	public Artikel read(long id)
	{
		return getEntityManager().find(Artikel.class, id);
	}
	
	public void create(Artikel artikel)
	{
		getEntityManager().persist(artikel);
	}	

	public Iterable<Artikel> findByNaamLike(String like)
	{
		return getEntityManager().createNamedQuery("Artikel.findByNameLike", Artikel.class)
				.setParameter("like","%"+like+"%")
				.getResultList();
	}

	public void algemeneOpslag(BigDecimal factor)
	{
		getEntityManager().createNamedQuery("Artikel.algemeneOpslag")
			.setParameter("factor", factor)
			.executeUpdate();
	}
	
	public Iterable<Artikel> findAll()
	{
		return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class).getResultList();
	}
}

