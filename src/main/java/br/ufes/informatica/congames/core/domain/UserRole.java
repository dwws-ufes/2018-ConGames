package br.ufes.informatica.congames.core.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class UserRole extends PersistentObjectSupport {

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

}