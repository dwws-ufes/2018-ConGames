package br.ufes.informatica.congames.core.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.informatica.congames.core.domain.Game;
import br.ufes.informatica.congames.core.domain.Genre;
import br.ufes.informatica.congames.core.domain.User;
import br.ufes.informatica.congames.core.exception.AlreadyBoughtGameException;
import br.ufes.informatica.congames.core.exception.InsufficientFundsException;
import br.ufes.informatica.congames.core.persistence.GameDAO;
import br.ufes.informatica.congames.core.persistence.GenreDAO;
import br.ufes.informatica.congames.core.persistence.UserDAO;

@Stateless
@PermitAll
public class PublishGamesServiceBean implements PublishGamesService {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameDAO gameDAO;

	@EJB
	private GenreDAO genreDAO;
	

	@Override
	public List<Game> SearchGamesByPublisher(String name) {
		
		if(name == null || name.length() <= 3)
			return null;
		
		String query = "PREFIX dbo: <http://dbpedia.org/ontology/> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"SELECT distinct ?game_name ?genre_name ?game_desc " +
			"WHERE { " +
			"?game a dbo:VideoGame; " +
			"dbo:developer ?pub; " +
			"rdfs:label ?game_name; " +
			"rdfs:comment ?game_desc; " +
			"dbo:genre ?game_genre. " +
			"?game_genre rdfs:label ?genre_name. " +
			"?pub rdfs:label ?pub_name. " +
			"FILTER regex(?pub_name, \"" + name + "\") " +
			"FILTER langMatches( lang(?game_name), \"EN\" ) " +
			"FILTER langMatches( lang(?genre_name), \"EN\" ) " +
			"FILTER langMatches( lang(?game_desc), \"EN\" ) " +
			"} " +
			"ORDER BY ASC(?game_name) " + 
			"LIMIT 20 ";
		
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		
		if(!results.hasNext()) return null;
		
		List<Game> games = new ArrayList<Game>();
		
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal nameLiteral = querySolution.getLiteral("game_name");
			Literal descLiteral = querySolution.getLiteral("game_desc");
			
			Game game = new Game();
			game.setName(nameLiteral.getValue().toString());
			game.setDescription(descLiteral.getValue().toString());
			
			games.add(game);
		}
		
		return games;

	}

}
