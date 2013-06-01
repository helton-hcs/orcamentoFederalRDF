package com.waldeilson.orcamentoFederalRDF.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.waldeilson.orcamentoFederalRDF.core.QueryManager;

public class QueryUI extends CustomUI {

	private static final long serialVersionUID = 504100768279167999L;
	protected JTextArea query;
	protected JScrollPane queryScrollPanel;
	protected JButton executeQuery;
	protected JScrollPane resultsScrollPanel;
	protected JTextArea results;
	
	public QueryUI() {
		super("Explorando dados do Orçamento Nacional - Queries em SPARQL");		
	}
	
	protected void setComponents() {
		query = new JTextArea(10, 40);
		query.setToolTipText("Digite a query a ser executada aqui.");
		queryScrollPanel = new JScrollPane(query, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		add(queryScrollPanel);
		
		executeQuery = new JButton("Execute");
		executeQuery.setToolTipText("Executa a query atual");
		add(executeQuery);
		
		results = new JTextArea(10, 40);				
		results.setToolTipText("Exibe o resultado da query em format de texto");					
		resultsScrollPanel = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(resultsScrollPanel);		
	}
	
	protected void setActionsListeners() {
		executeQuery.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if (!query.getText().isEmpty()) {
					results.setText(QueryManager.execute(query.getText()));
				}
			}
		});		
	}
	
}