package br.ufes.informatica.congames.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.rdf.model.Property;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.informatica.congames.core.application.ManageGamesService;
import br.ufes.informatica.congames.core.domain.Game;

@WebServlet(urlPatterns = { "/data/games/*" })
public class GameInRdfServlet extends HttpServlet
{
	private static final DateFormat df = new SimpleDateFormat("yyyy-MMdd'T'HH:mm:ss");

	@EJB
	private ManageGamesService manageGamesService;
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException 
	{
		

		String gameName = req.getPathInfo().substring(1);
	
		

		Game referedGame = manageGamesService.retrieveGameByName(gameName.replace('_',' '));
		if(referedGame == null)
		{

			resp.sendRedirect(req.getContextPath() + "/data/page_not_found");
			return;
		}
		
		resp.setContentType("text/xml");
		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/congames/data/games/";
		String dboNS = "http://dbpedia.org/resource/classes#";
		String grNS = "http://purl.org/goodrelations/v1#";
		model.setNsPrefix("dbo", dboNS);
		model.setNsPrefix("gr", grNS);
		
		Resource dboVideoGame = ResourceFactory.createResource(dboNS + "VideoGame");
		Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
		
		Property dboGenre= ResourceFactory.createProperty(dboNS + "genre");
		Property dboReleaseDate = ResourceFactory.createProperty(dboNS + "releaseDate");
		Property dboDeveloper = ResourceFactory.createProperty(dboNS + "developer");
		
		Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
		Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
		
		model.createResource(myNS + referedGame.getName().replace(' ','_'))
		.addProperty(RDF.type, dboVideoGame)
		.addProperty(RDFS.label, referedGame.getName())
		.addProperty(RDFS.comment, referedGame.getDescription())
		.addLiteral(dboDeveloper, referedGame.getPublisher().getName())
		.addLiteral(dboGenre, referedGame.getGenre().getName())
		.addLiteral(dboReleaseDate, ResourceFactory.createTypedLiteral(df.format(referedGame.getPublishDate()), XSDDatatype.XSDdateTime))
		.addProperty(grhasPriceSpecification, model.createResource()
				.addProperty(RDF.type, grPriceSpecification)
				.addLiteral(grhasCurrencyValue, referedGame.getPrice()) );

		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
			}
	
	
		

	}
	
	
}
