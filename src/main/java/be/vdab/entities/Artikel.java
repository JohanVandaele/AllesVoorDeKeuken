package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artikels")
public class Artikel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;

	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs)
	{
		if (! isAankoopprijsVerkoopprijsValid(aankoopprijs, verkoopprijs))
		{
			throw new IllegalArgumentException();
		}	

		setNaam(naam);
		setVerkoopprijs(verkoopprijs);
		setAankoopprijs(aankoopprijs);
	}
	
	// default constructor is vereiste voor JPA
	protected Artikel()
	{
	}
	
	public BigDecimal getWinstPercentage()
	{
		return verkoopprijs.subtract(aankoopprijs).divide(aankoopprijs, 2,RoundingMode.HALF_UP).multiply(new BigDecimal(100));
	}
	
	// Properties
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNaam()
	{
		return naam;
	}

	public void setNaam(String naam)
	{
		this.naam = naam;
	}

	public BigDecimal getAankoopprijs()
	{
		return aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs)
	{
		this.aankoopprijs = aankoopprijs;
	}

	public BigDecimal getVerkoopprijs()
	{
		return verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs)
	{
		this.verkoopprijs = verkoopprijs;
	}
	
	public static boolean isNaamValid(String naam)
	{
		return naam != null && ! naam.isEmpty();
	}
	
	public static boolean isAankoopprijsValid(BigDecimal aankoopprijs)
	{
		return aankoopprijs != null && aankoopprijs.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public static boolean isVerkoopprijsValid(BigDecimal verkoopprijs)
	{
		return verkoopprijs != null && verkoopprijs.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public static boolean isAankoopprijsVerkoopprijsValid(BigDecimal aankoopprijs,BigDecimal verkoopprijs)
	{
		return aankoopprijs.compareTo(verkoopprijs) <= 0;
	}
}
