package com.waldeilson.orcamentoFederalRDF.core;

import java.awt.EventQueue;

import com.waldeilson.orcamentoFederalRDF.ui.ConnectionUI;

public class Loader {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {			
			public void run() {
				ConnectionUI ui = new ConnectionUI();				
				ui.setupAndShow();				
			}
		});		
	}
	
}
