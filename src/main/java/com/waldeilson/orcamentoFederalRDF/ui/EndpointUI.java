package com.waldeilson.orcamentoFederalRDF.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.waldeilson.orcamentoFederalRDF.core.Endpoint;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndpointUI extends JFrame {
	
	private static final long serialVersionUID = -7432927015856609143L;
	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtDataSet;
	private JButton btnCancelar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					EndpointUI frame = new EndpointUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	private void carregarInformacoesEndpoint() {
		if (Endpoint.isConfigurado()) {
			txtHost.setText(Endpoint.getHost());
			txtPort.setText(Endpoint.getPort());
			txtDataSet.setText(Endpoint.getDatasetName());
		}
	}

	public EndpointUI() {			
		setTitle("Configuração de endpoint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(12, 12, 70, 15);
		contentPane.add(lblHost);
		
		txtHost = new JTextField();
		txtHost.setText("localhost");
		txtHost.setBounds(12, 27, 153, 19);
		contentPane.add(txtHost);
		txtHost.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(181, 12, 70, 15);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setText("3030");
		txtPort.setBounds(180, 27, 56, 19);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JLabel lblDataset = new JLabel("Dataset");
		lblDataset.setBounds(257, 12, 70, 15);
		contentPane.add(lblDataset);
		
		txtDataSet = new JTextField();
		txtDataSet.setText("ds");
		txtDataSet.setBounds(257, 27, 153, 19);
		contentPane.add(txtDataSet);
		txtDataSet.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		
		btnOk.addActionListener(new ActionListener() {
			
			private boolean validate() {
				return !(txtHost.getText().isEmpty() && 
						 txtPort.getText().isEmpty() && 
						 txtDataSet.getText().isEmpty());
			}
			
			public void actionPerformed(ActionEvent e) {
				if (validate()) {
					Endpoint.setup(txtHost.getText(), txtPort.getText(), txtDataSet.getText());
					dispose();					
				}				
			}
		});
		
		btnOk.setBounds(237, 58, 70, 25);
		contentPane.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {				
				if (!Endpoint.isConfigurado()) {
					JOptionPane.showMessageDialog(null, "Endpoint ainda não configurado. Favor informar os dados necessários.");
				}
				else {
					dispose();
				}
			}
		
		});
		btnCancelar.setBounds(314, 58, 96, 25);
		contentPane.add(btnCancelar);
		
		carregarInformacoesEndpoint();
		
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())  / 2,
			        (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);				
	}
}
