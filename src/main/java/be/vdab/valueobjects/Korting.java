package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

// enkele imports

@Embeddable
public class Korting implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int vanafAantal;
	private BigDecimal kortingspercentage;	
	
	public Korting(int vanafAantal,BigDecimal kortingspercentage)
	{
		this.vanafAantal = vanafAantal;
		this.kortingspercentage = kortingspercentage;
	}

	protected Korting() {} // default constructor voor JPA
	
	@Override
	public int hashCode()
	{
		return vanafAantal;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Korting))
		{
			return false;
		}
		
		Korting andereKorting = (Korting) object;
		return vanafAantal == andereKorting.vanafAantal;
	}	
	
	public int getVanafAantal()
	{
		return vanafAantal;
	}

	public void setVanafAantal(int vanafAantal)
	{
		this.vanafAantal = vanafAantal;
	}

	public BigDecimal getKortingsPercentage()
	{
		return kortingspercentage;
	}

	public void setKortingsPercentage(BigDecimal kortingspercentage)
	{
		this.kortingspercentage = kortingspercentage;
	}

//	// Je stelt straks in de class Campus de telefoonnummers van een campus voor als een Set<TelefoonNr>.
//	// Deze Set laat geen TelefoonNr objecten met hetzelfde nummer toe.
//	// Je baseert daarom de equals method op het nummer.	
//	@Override
//	public boolean equals(Object obj)
//	{
//		if (!(obj instanceof TelefoonNr))
//		{
//			return false;
//		}
//		
//		TelefoonNr telefoonNr = (TelefoonNr) obj;
//		return nummer.equalsIgnoreCase(telefoonNr.nummer);
//	}
	
//	// Je baseert de method hashCode ook op het nummer.
//	@Override
//	public int hashCode()
//	{
//		return nummer.toUpperCase().hashCode();
//	}
}
