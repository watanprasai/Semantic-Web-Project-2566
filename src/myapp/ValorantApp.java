package myapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;

import myapp.OpenOWL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ValorantApp {
	
	public ArrayList<String> ListComponent = new ArrayList<String>();
	// tempValue keep data of select combobox
	public 	String tempValue = new String();
	private JFrame frame;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValorantApp window = new ValorantApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ValorantApp() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
		frame.setTitle("Valorant App");
	    frame.setSize(400, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    
	    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	    JComboBox<String> comboBox = new JComboBox<>(model);
	    comboBox.setBounds(28, 80, 140, 22);
	    frame.getContentPane().add(comboBox);
//	    Color customColor = new Color(15, 20, 35); // RGB values for #0f1423
//	    frame.getContentPane().setBackground(customColor);
	    
	    // Create the table and table model
	    String[] columnNames = {"Name"};
	    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	    JTable table = new JTable(tableModel);
	    table.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent e) {
	            // Get the selected row
	            int row = table.getSelectedRow();
	            if (row >= 0 && tempValue.equals("Maps")) {
	                String componentName = (String) table.getValueAt(row, 0);
	                JFrame popupWindow = new JFrame();
	                popupWindow.setTitle("Valorant App");
	                popupWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
	                popupWindow.setSize(400, 300);
	                popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                popupWindow.getContentPane().setLayout(null);

	                JLabel nameLabel = new JLabel("Map Name: " + componentName);
	                nameLabel.setBounds(10, 10, 300, 30);
	                popupWindow.getContentPane().add(nameLabel);

	                popupWindow.setVisible(true);
	            }
	            if (row >= 0 && tempValue.equals("Modes")) {
	            	String componentName = (String) table.getValueAt(row, 0);
	                JFrame popupWindow = new JFrame();
	                popupWindow.setTitle("Valorant App");
	                popupWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
	                popupWindow.setSize(400, 300);
	                popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                popupWindow.getContentPane().setLayout(null);

	                JLabel nameLabel = new JLabel("Mode Name: " + componentName);
	                nameLabel.setBounds(10, 10, 300, 30);
	                popupWindow.getContentPane().add(nameLabel);

	                popupWindow.setVisible(true);
	            }
	            if (row >= 0 && tempValue.equals("Agents")) {
	            	String componentName = (String) table.getValueAt(row, 0);
	                JFrame popupWindow = new JFrame();
	                popupWindow.setTitle("Valorant App");
	                popupWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
	                popupWindow.setSize(400, 300);
	                popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                popupWindow.getContentPane().setLayout(null);

	                JLabel nameLabel = new JLabel("Agent Name: " + componentName);
	                nameLabel.setBounds(10, 10, 300, 30);
	                popupWindow.getContentPane().add(nameLabel);
	                
	                if (componentName.equals("Astra")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Astra_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);	
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Breach")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Breach_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Brimstone")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Brimstone_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Chamber")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Chamber_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Cypher")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Cypher_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Fade")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Fade_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Harbor")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Harbor_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Jett")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Jett_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("KAY/O")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/KAYO_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Killjoy")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Killjoy_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Neon")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Neon_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Omen")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Omen_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Phoenix")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Phoenix_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Raze")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Raze_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Reyna")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Reyna_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Sage")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sage_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Skye")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Skye_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Sova")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sova_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Viper")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Viper_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }
	                if (componentName.equals("Yoru")) {
	                	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Yoru_icon.jpg");
		        		JLabel imageLabel = new JLabel(imageIcon);
		        		imageLabel.setBounds(10, 100, 150, 150);
		        		popupWindow.getContentPane().add(imageLabel);
	                }

	                popupWindow.setVisible(true);
	            }
	            if (row >= 0 && tempValue.equals("Skins")) {
	            	String componentName = (String) table.getValueAt(row, 0);
	                JFrame popupWindow = new JFrame();
	                popupWindow.setTitle("Valorant App");
	                popupWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
	                popupWindow.setSize(400, 300);
	                popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                popupWindow.getContentPane().setLayout(null);

	                JLabel nameLabel = new JLabel("Skin Name: " + componentName);
	                nameLabel.setBounds(10, 10, 300, 30);
	                popupWindow.getContentPane().add(nameLabel);

	                popupWindow.setVisible(true);
	            }
	            if (row >= 0 && tempValue.equals("Weapons")) {
	            	String componentName = (String) table.getValueAt(row, 0);
	                JFrame popupWindow = new JFrame();
	                popupWindow.setTitle("Valorant App");
	                popupWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(ValorantApp.class.getResource("/myapp/images/images.png")));
	                popupWindow.setSize(400, 300);
	                popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                popupWindow.getContentPane().setLayout(null);

	                JLabel nameLabel = new JLabel("Weapon Name: " + componentName);
	                nameLabel.setBounds(10, 10, 300, 30);
	                popupWindow.getContentPane().add(nameLabel);

	                popupWindow.setVisible(true);
	            }
	        }
	    });
	    JButton btnNewButton = new JButton("Search");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		tableModel.setRowCount(0);
	    		String selectedComponent = (String) comboBox.getSelectedItem();
	    		tempValue = selectedComponent;
	    		ArrayList<String> temp = new ArrayList<String>();
	    		System.out.println("Selected component: " + selectedComponent);
	    		try {
	    			String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:" + selectedComponent + "} ";

	    	        org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);

	    	        while (results.hasNext()) {
	    	            QuerySolution soln = results.nextSolution();
	    	            RDFNode subclass = soln.get("classname");
	    	            String Component = subclass.toString();
	    	            System.out.println("Component: " + Component);
	    	            temp.add(Component);
	    	            Object[] tempData = { Component };
	    	            tableModel.addRow(tempData);
	    	        }
	    	        for (int i = 0; i < temp.size(); i++) {
	    	        	System.out.println("------------");
	    	        	System.out.println("number"+ i);
	    	            System.out.println(temp.get(i));
	    	        }
	    	    } catch (Exception ex){
	    	        ex.printStackTrace();
	    	    }
	    	}
	    });
	    btnNewButton.setBounds(243, 80, 89, 23);
	    frame.getContentPane().add(btnNewButton);

	    // Create a scroll pane for the table and add it to the frame
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(28, 167, 314, 300);
	    frame.getContentPane().add(scrollPane);

	    try {
	    	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    		    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    		    + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    		    + "SELECT (strafter(str(?subclass), '#') AS ?classname) "
	    		    + "WHERE { "
	    		    + "  ?subclass rdfs:subClassOf ex:Valorant_Detail . "
	    		    + "  FILTER (?subclass != ex:Valorant_Detail) "
	    		    + "} LIMIT 5";
	    	
	        org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);

	        while (results.hasNext()) {
	            QuerySolution soln = results.nextSolution();
	            RDFNode subclass = soln.get("classname");
	            String Component = subclass.toString();
	            // test --
	            System.out.println("Component: " + Component);
	            ListComponent.add(Component);
	        }
	        comboBox.removeAllItems();
	        for (int i = 0; i < ListComponent.size(); i++) {
	            comboBox.addItem(ListComponent.get(i));
	        }
	    } catch (Exception ex){
	        ex.printStackTrace();
	    }
	    
	}
}
