package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// enkele imports (vooral uit javax.persistence) ...

// Je tikt @Entity juist voor de entity class.
@Entity
// Je tikt @Table voor de entity class, met de table naam. Je mag @Table weglaten als de table naam gelijk is aan de class naam.
@Table(name = "artikels")
// JPA raadt aan dat de class Serializable implementeert.
// Dit is niet noodzakelijk voor de samenwerking met de database. Het is wel noodzakelijk als je objecten via serialization naar een binar bestand zou wegschrijven.
// We volgen in de cursus deze aanbeveling.
public class Artikel implements Serializable
{
	private static final long serialVersionUID = 1L;

	// Je tikt @Id voor de private variabele die hoort bij de primary key kolom.
	@Id
	// Je tikt @GeneratedValue als de database de kolom zelf invult.
	// Dit is zo in de table docenten: id is een autonumber kolom.
	@GeneratedValue
	// JPA associeert een private variabele met een table kolom met dezelfde naam.
	// Je tikt @Column voor een private variabele als de naam van de bijbehorende table kolom verschilt van de naam van die private variabele.
	// Je tikt bij de parameter name de naam van de kolom: @Column(name = "kolomnaam")
	// @Column(name = "kolomnaam")
	private long id;

	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;

	// Je tikt @Transient voor een private variabele als die private variabele geen bijbehorende kolom heeft in de database table.
	
	// Je tikt @Temporal voor een private Date variabele. Je geeft met een parameter het type aan van de bijbehorende table kolom.
	// @Temporal(TemporalType.DATE) Het kolomtype is Date (de kolom bevat enkel een datum)
	// @Temporal(TemporalType.TIME) Het kolomtype is Time (de kolom bevat enkel een tijd)
	// @Temporal(TemporalType.TIMESTAMP) Het kolomtype is DateTime (de kolom bevat een datum en een tijd)	
	
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
}
