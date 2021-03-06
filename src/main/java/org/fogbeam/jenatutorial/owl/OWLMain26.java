package org.fogbeam.jenatutorial.owl;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.util.FileManager;

public class OWLMain26
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex26-data.ttl");
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String queryString = "SELECT ?z WHERE { <http://www.example.org/example#GraduallyMix> <http://www.example.org/example#neighborStep> ?z }";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?z WHERE { <http://www.example.org/example#GraduallyMix> <http://www.example.org/example#inSameRecipe> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);		
		
		System.out.println( "\n---------------\n" );		
		
		queryString = "SELECT ?z WHERE { <http://www.example.org/example#GraduallyMix> <http://www.example.org/example#otherStep> ?z }";

		/* Now create and execute the query using a Query object */
		query = QueryFactory.create(queryString) ;
		qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		System.out.println( "done" );		
	}
}