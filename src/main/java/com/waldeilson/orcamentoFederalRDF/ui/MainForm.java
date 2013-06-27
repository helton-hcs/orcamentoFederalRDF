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

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.waldeilson.orcamentoFederalRDF.core.Endpoint;
import com.waldeilson.orcamentoFederalRDF.core.QueryFileReader;
import com.waldeilson.orcamentoFederalRDF.core.QueryManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.Locale;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 2757288894157382433L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxClassificador;
	private JComboBox<String> comboBoxFiltro;
	private JLabel lblResult; 
	private JTextArea txtrQuery;
	private JTextArea txtrResultado;
	private static final String PADRAO_CLASSIFICADOR = "%padraoClassificador%";
	private static final String PADRAO_FILTRO = "%padraoFiltro%";
	private JLabel lblFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);			
					EndpointUI endpointUI = new EndpointUI(frame);
					endpointUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String getValorFormatado(Double valor) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
	}
	
	public void calculaTotalClassificador(){
		String queryText = QueryFileReader.getQuery("queryTotalPorClassificador.sparql").replace("%pattern%",comboBoxClassificador.getSelectedItem().toString());
        ResultSet results = QueryManager.getResultSet(queryText);
    	txtrQuery.setText(queryText);        
        try {
        	QuerySolution qs = results.nextSolution();
        	Double d = Double.parseDouble(qs.getLiteral("total").toString());
        	lblResult.setText(getValorFormatado(d));
        }
        catch(NullPointerException npe) {
            lblResult.setText("");	
        }        
	}
	
	
	private void calculaTotalFiltroClassificador(){
		if (comboBoxFiltro.getSelectedItem() != null) {
			String queryText = QueryFileReader.getQuery("queryTotalClassificadorFiltro.sparql")
					.replace(PADRAO_CLASSIFICADOR,comboBoxClassificador.getSelectedItem().toString())
							.replace(PADRAO_FILTRO,comboBoxFiltro.getSelectedItem().toString());
			txtrQuery.setText(queryText);
			ResultSet results = QueryManager.getResultSet(queryText);
	        try {
	        	QuerySolution qs = results.nextSolution();
	        	Double d = Double.parseDouble(qs.getLiteral("total").toString());
	        	lblResult.setText(getValorFormatado(d));
	        }
	        catch(NullPointerException npe) {
	            lblResult.setText("");		 
	        }        
		}
	}	
	
	
	public void carregaClassificadores() {				
		String queryText = QueryFileReader.getQuery("getClassificadores.sparql");
        ResultSet results = QueryManager.getResultSet(queryText);
        comboBoxClassificador.removeAll();
        while (results.hasNext()) {
        	comboBoxClassificador.addItem(results.nextSolution().getResource("label").getLocalName().substring(3));//retirar "tem"    	
        }        
	}
	
	private void carregaFiltro(String valor, JComboBox<String> destino) {		
		String queryText = QueryFileReader.getQuery("getValoresDisponiveis.sparql").replace("%pattern%", valor);
        ResultSet results = QueryManager.getResultSet(queryText);
        destino.removeAllItems();
        while (results.hasNext()) {
        	destino.addItem(results.nextSolution().getLiteral("label").toString());    	
        }	
	}
	
	/**
	 * Create the frame.
	 */
	public MainForm() {		 
		setTitle("Explorando o Orçamamento Federal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 371);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEndpoint = new JMenu("Configuração");
		menuBar.add(mnEndpoint);
		
		JMenuItem mntmConfiguracoesEndpoint = new JMenuItem("Configurar endpoint");
		
		final MainForm mf = this;
		mntmConfiguracoesEndpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EndpointUI endpointUI = new EndpointUI(mf);
				endpointUI.setVisible(true);	
			}
		});
		
		mnEndpoint.add(mntmConfiguracoesEndpoint);
		
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
		panel_PesquisaComFiltros.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_Classificador = new JPanel();
		panel_PesquisaComFiltros.add(panel_Classificador);
		panel_Classificador.setLayout(null);
		
		JLabel lblClassificador = new JLabel("Classificador");
		lblClassificador.setBounds(12, 12, 117, 15);
		panel_Classificador.add(lblClassificador);
		
		comboBoxClassificador = new JComboBox<String>();		
		comboBoxClassificador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(e.getSource().equals(comboBoxClassificador) && comboBoxClassificador.getSelectedItem() != null){
					carregaFiltro(comboBoxClassificador.getSelectedItem().toString(), comboBoxFiltro);
					calculaTotalClassificador();
					lblFiltro.setText(comboBoxClassificador.getSelectedItem().toString());
				}
			}
		});
		comboBoxClassificador.setBounds(12, 28, 703, 24);
		panel_Classificador.add(comboBoxClassificador);
		
		lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(12, 64, 359, 15);
		panel_Classificador.add(lblFiltro);
		
		comboBoxFiltro = new JComboBox<String>();
		comboBoxFiltro.setBounds(12, 78, 703, 24);
		panel_Classificador.add(comboBoxFiltro);
		comboBoxFiltro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calculaTotalFiltroClassificador();
			}
		});
		
		JPanel panel_Resultado = new JPanel();
		panel_PesquisaComFiltros.add(panel_Resultado);
		panel_Resultado.setLayout(null);
		
		lblResult = new JLabel("");
		lblResult.setBounds(12, 28, 703, 73);
		panel_Resultado.add(lblResult);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Dialog", Font.BOLD, 40));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(tabbedPane)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 298, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_QueryResultado = new JPanel();
		tabbedPane.addTab("Query", null, panel_QueryResultado, null);
		panel_QueryResultado.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_Query = new JPanel();
		panel_Query.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_QueryResultado.add(panel_Query);
		panel_Query.setLayout(null);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(256, 238, 95, 25);
		panel_Query.add(btnExecutar);
		
		txtrQuery = new JTextArea();
		txtrQuery.setBounds(12, 12, 339, 214);
		panel_Query.add(txtrQuery);
		
		JPanel panel_ResultadoQuery = new JPanel();
		panel_ResultadoQuery.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_QueryResultado.add(panel_ResultadoQuery);
		panel_ResultadoQuery.setLayout(null);
		
		txtrResultado = new JTextArea();
		txtrResultado.setBounds(12, 12, 339, 251);
		panel_ResultadoQuery.add(txtrResultado);
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
