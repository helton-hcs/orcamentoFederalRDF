package com.waldeilson.orcamentoFederalRDF.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.waldeilson.orcamentoFederalRDF.core.Connection;

public class ConnectionUI extends CustomUI {

	private static final long serialVersionUID = 6751965320703374819L;
	protected JTextField serverIP;
	protected JTextField port;
	protected JTextField datasetName;
	protected JButton confirm;
	
	public ConnectionUI() {
		super("Configurações de conexão");
	}
	
	public void setComponents() {
		serverIP = new JTextField(20);
		serverIP.setText("localhost");
		add(serverIP);
		
		port = new JTextField(5); //configurar para aceitar apenas números
		port.setText("3030");
		add(port);
		
		datasetName = new JTextField(20);
		datasetName.setText("ds");
		add(datasetName);
		
		confirm = new JButton("OK");
		add(confirm);
	}
	

	public void setActionListeners() {
		confirm.addActionListener(new ActionListener() {
			
			private void loadQueryUI() {
				QueryUI ui = new QueryUI();				
				ui.setupAndShow();			
			}
			
			private boolean canLoadQueryUI() {
				return !(serverIP.getText().isEmpty() && 
						 port.getText().isEmpty() && 
						 datasetName.getText().isEmpty());
			}
			
			public void actionPerformed(ActionEvent e) {
				if (canLoadQueryUI()) {
					Connection.setup(serverIP.getText(), port.getText(), datasetName.getText());
					loadQueryUI();			
					dispose();					
				}
			}
		});
	}
	
}
