package com.waldeilson.orcamentoFederalRDF.core;

public class Connection {

	private static String serverIP;
	private static String port;
	private static String datasetName;
	
	public static String getURL() {
		return "http://"+serverIP+":"+port+"/"+datasetName+"/query";
	}
	
	public static void setup(String serverIP, String port, String databaseName) {
		Connection.serverIP = serverIP;
		Connection.port = port;
		Connection.datasetName = databaseName;
	}
	
}
