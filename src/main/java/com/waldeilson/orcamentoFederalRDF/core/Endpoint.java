package com.waldeilson.orcamentoFederalRDF.core;

public class Endpoint {

	private static String host;
	private static String port;
	private static String datasetName;
		private static boolean configurado = false;
	
	public static void setup(String host, String port, String datasetName) {
		Endpoint.host = host;
		Endpoint.port = port;
		Endpoint.datasetName = datasetName;
		Endpoint.configurado = true;
	}
	
	public static boolean isConfigurado() {
		return configurado;
	}
	
	public static String getURL() {
		return "http://" + host + ":" + port + "/" + datasetName + "/query";
	}		

	public static String getHost() {
		return host;
	}

	public static String getPort() {
		return port;
	}

	public static String getDatasetName() {
		return datasetName;
	}
	
	
}
