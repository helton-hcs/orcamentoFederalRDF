package com.waldeilson.orcamentoFederalRDF.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;

import java.awt.GridLayout;

import javax.swing.JLabel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.waldeilson.orcamentoFederalRDF.core.Endpoint;
import com.waldeilson.orcamentoFederalRDF.core.QueryFileReader;
import com.waldeilson.orcamentoFederalRDF.core.QueryManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 2757288894157382433L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxAtividade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);			
					EndpointUI endpointUI = new EndpointUI();
					endpointUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void loadComboBoxes() {				
		String queryText = QueryFileReader.getQuery("loadComboBoxes.sparql");
		JOptionPane.showMessageDialog(null, queryText);
    	Query query = QueryFactory.create(queryText);    	
        QueryExecution qExe = QueryExecutionFactory.sparqlService(Endpoint.getURL(), query);
        ResultSet results = qExe.execSelect();
		comboBoxAtividade.addItem(results.nextSolution().getLiteral("items").getString());
	}
	
	/**
	 * Create the frame.
	 */
	public MainForm() {		 
		setTitle("Explorando o Orçamamento Federal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 421);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuery = new JMenu("Query");
		menuBar.add(mnQuery);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnQuery.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnQuery.add(mntmSalvar);
		
		JMenu mnEndpoint = new JMenu("Endpoint");
		menuBar.add(mnEndpoint);
		
		JMenuItem mntmConfiguracoes = new JMenuItem("Configurações");
		
		mntmConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EndpointUI endpointUI = new EndpointUI();
				endpointUI.setVisible(true);	
			}
		});
		
		mnEndpoint.add(mntmConfiguracoes);
		
		JMenuItem mntmVerUrlAtual = new JMenuItem("Ver URL atual");
		mntmVerUrlAtual.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Endpoint.getURL());				
			}
			
		});
		mnEndpoint.add(mntmVerUrlAtual);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel_PesquisaComFiltros = new JPanel();
		tabbedPane.addTab("Pesquisa com Filtros", null, panel_PesquisaComFiltros, null);
		panel_PesquisaComFiltros.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_Atividade = new JPanel();
		panel_PesquisaComFiltros.add(panel_Atividade);
		panel_Atividade.setLayout(null);
		
		JLabel lblAtividade = new JLabel("Atividade");
		lblAtividade.setBounds(12, 12, 70, 15);
		panel_Atividade.add(lblAtividade);
		
		comboBoxAtividade = new JComboBox<String>();
		comboBoxAtividade.setBounds(12, 28, 513, 24);
		panel_Atividade.add(comboBoxAtividade);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadComboBoxes();
			}
		});
		btnLoad.setBounds(12, 72, 117, 25);
		panel_Atividade.add(btnLoad);
		
		JPanel panel_4 = new JPanel();
		panel_PesquisaComFiltros.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_PesquisaComFiltros.add(panel_5);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE))
		);
		
		JPanel panel_Query = new JPanel();
		tabbedPane.addTab("Query", null, panel_Query, null);
		panel_Query.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_Query.add(panel);
		panel.setLayout(null);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(161, 292, 95, 25);
		panel.add(btnExecutar);
		
		final JTextArea txtrQuery = new JTextArea();
		txtrQuery.setBounds(12, 12, 244, 268);
		panel.add(txtrQuery);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_Query.add(panel_1);
		panel_1.setLayout(null);
		
		final JTextArea txtrResultado = new JTextArea();
		txtrResultado.setBounds(12, 12, 244, 305);
		panel_1.add(txtrResultado);
		contentPane.setLayout(gl_contentPane);

		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (txtrQuery.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Query vazia.");
				}
				else {
					txtrResultado.setText(QueryManager.executar(txtrQuery.getText()));					
				}				
			}
		});
				
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())  / 2,
			    (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
	}
}
