package com.waldeilson.orcamentoFederalRDF.core;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class QueryManager {

	public static String execute(String queryText) {
    	Query query = QueryFactory.create(queryText);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(Connection.getURL(), query);
        ResultSet results = qExe.execSelect();
        return ResultSetFormatter.asText(results);
	}
	
}
