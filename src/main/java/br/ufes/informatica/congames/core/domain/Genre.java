package br.ufes.informatica.congames.core.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Genre extends PersistentObjectSupport implements Comparable<Genre> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 100)
	private String name;

	@Size(max = 500)
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private Set<Game> games;

	public String getDescription() {
		return description;
	}

	public void setDescription(String _description) {
		description = _description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{
	    return this.name;
	}

	@Override
	public int compareTo(Genre o) {
		if (name.equals(o.name))
			return 0;
		else
			return 1;
	}

}