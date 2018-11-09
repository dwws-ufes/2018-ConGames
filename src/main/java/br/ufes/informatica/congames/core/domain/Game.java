package br.ufes.informatica.congames.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Game extends PersistentObjectSupport implements Comparable<Game> {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 100)
	private String name;
	
	@Size(max = 500)
	private String description;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date publishDate;
	
	@ManyToOne
	private Genre genre;


	public String getName() {
		return name;
	}

	public void setName(String _name) {
		name = _name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String _description) {
		description = _description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date _publishDate) {
		publishDate = _publishDate;
	}
	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public int compareTo(Game o) {
		return 1;
	}

}