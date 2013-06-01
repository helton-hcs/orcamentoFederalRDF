package com.waldeilson.orcamentoFederalRDF.ui;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

public abstract class CustomUI extends JFrame {

	private static final long serialVersionUID = -5122676356274003191L;
	
	protected abstract void setComponents();
	protected abstract void setActionsListeners();	
	
	public CustomUI(String title) {
		super(title);
		setComponents();
		setActionsListeners();		
	}
	
	public void setupAndShow() {
		setLayout(new FlowLayout());
		pack();		
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())  / 2,
				    (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);			
	}	
	
}
