package org.fogbeam.jenatutorial.linkingdata;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

public class TermReconciliation_Subclass
{

	public static void main( String[] args )
	{
		// load some data that uses RDFS
		Model data = FileManager.get().loadModel("file:data/input/turtle/ex12-data.ttl");
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		reasoner.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, 
                ReasonerVocabulary.RDFS_DEFAULT);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data );
		
		/* Do a SPARQL Query over the data in the model */
		String 	queryString = "SELECT ?x WHERE { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.org/example#Analyst> }";

		/* Now create and execute the query using a Query object */
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, infmodel) ;

		QueryExecUtils.executeQuery(qexec);
		
		System.out.println( "\n---------------\n" );		
		
	}
}