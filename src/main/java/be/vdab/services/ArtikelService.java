package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;

public class ArtikelService
{
	private final ArtikelDAO artikelDAO = new ArtikelDAO();

	public Artikel read(long id)
	{
		return artikelDAO.read(id);
	}
	
	public void create(Artikel artikel)
	{
		artikelDAO.beginTransaction();
		artikelDAO.create(artikel);
		artikelDAO.commit();
	}	

	public Iterable<Artikel> findByNaamLike(String like)
	{
		return artikelDAO.findByNaamLike(like);
	}

	public void algemeneOpslag(BigDecimal percentage)
	{
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(new BigDecimal(100)));

		artikelDAO.beginTransaction();
		artikelDAO.algemeneOpslag(factor);
		artikelDAO.commit();
	}
}
