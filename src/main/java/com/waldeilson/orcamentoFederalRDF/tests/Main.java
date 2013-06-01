package com.waldeilson.orcamentoFederalRDF.tests;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.hp.hpl.jena.query.*;

public class Main
{
	static String readFile(String path, Charset encoding)  throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}	
	
	public static String getQuery(String queryName)  {
    	String input = "";
    	try {
			input = readFile("/media/Data/Dropbox/TrabalhoIA/Queries/"+queryName, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return input;
	}
	
    public static void main( String[] args ) {        
    	Query query = QueryFactory.create(getQuery("query3.txt"));
        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://localhost:3030/ds/query", query );
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query) ;    	
    }
}