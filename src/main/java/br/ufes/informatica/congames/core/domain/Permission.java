package br.ufes.informatica.congames.core.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Permission extends PersistentObjectSupport implements Comparable<Permission> {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Permission o) {
		if (name.equals(o.name))
			return 0;
		else
			return 1;
	}

}