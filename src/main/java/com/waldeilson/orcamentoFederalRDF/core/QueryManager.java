package com.waldeilson.orcamentoFederalRDF.core;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class QueryManager {
	
	public static String executar(String queryText) {
    	Query query = QueryFactory.create(queryText);    	
        QueryExecution qExe = QueryExecutionFactory.sparqlService(Endpoint.getURL(), query);
        ResultSet results = qExe.execSelect();
        return ResultSetFormatter.asText(results);
	}	

	public static ResultSet getResultSet(String queryText) {
    	Query query = QueryFactory.create(queryText);    	
        QueryExecution qExe = QueryExecutionFactory.sparqlService(Endpoint.getURL(), query);
        return qExe.execSelect();
	}	
	
}
