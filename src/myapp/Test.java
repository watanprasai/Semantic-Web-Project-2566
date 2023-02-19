package myapp;

import javax.swing.*;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cards;

    public Test() {
        // Create a card layout to switch between panels
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        JButton btnMaps = new JButton("Maps");
        btnMaps.setBounds(0, 0, 384, 100);
        btnMaps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                try {
                	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:Maps } ";
                    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                    while (results.hasNext()) {
                        QuerySolution soln = results.nextSolution();
                        RDFNode subclass = soln.get("classname");
                        String Component = subclass.toString();
                        ListComponent.add(Component);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create a panel for the data and add it to the card layout
                JPanel dataPanel = new JPanel();
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                // Create a back button to return to the search panel
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                dataPanel.add(backButton);

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        buttonPanel.setLayout(null);
        buttonPanel.add(btnMaps);

        // Create a panel for the search page and add it to the card layout
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(buttonPanel, BorderLayout.CENTER);
        
        JButton btnModes = new JButton("Modes");
        btnModes.setBounds(0, 100, 384, 100);
        buttonPanel.add(btnModes);
        btnModes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                try {
                	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:Modes } ";
                    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                    while (results.hasNext()) {
                        QuerySolution soln = results.nextSolution();
                        RDFNode subclass = soln.get("classname");
                        String Component = subclass.toString();
                        ListComponent.add(Component);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create a panel for the data and add it to the card layout
                JPanel dataPanel = new JPanel();
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);
                
                // Create a back button to return to the search panel
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                dataPanel.add(backButton);

                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            // Add code here to show the selected data on a new panel
                            JPanel selectedDataPanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedDataPanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedDataPanel.add(backButton);

                            cards.add(selectedDataPanel, "selectedDataPanel");
                            cardLayout.show(cards, "selectedDataPanel");
                        }
                    }
                });
                
                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        JButton btnAgents = new JButton("Agents");
        btnAgents.setBounds(0, 200, 384, 100);
        buttonPanel.add(btnAgents);
        btnAgents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                try {
                	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:Agents } ";
                    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                    while (results.hasNext()) {
                        QuerySolution soln = results.nextSolution();
                        RDFNode subclass = soln.get("classname");
                        String Component = subclass.toString();
                        ListComponent.add(Component);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create a panel for the data and add it to the card layout
                JPanel dataPanel = new JPanel();
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                // Create a back button to return to the search panel
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                dataPanel.add(backButton);

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        
        JButton btnSkins = new JButton("Skins");
        btnSkins.setBounds(0, 299, 384, 100);
        buttonPanel.add(btnSkins);
        btnSkins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                try {
                	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:Skins } ";
                    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                    while (results.hasNext()) {
                        QuerySolution soln = results.nextSolution();
                        RDFNode subclass = soln.get("classname");
                        String Component = subclass.toString();
                        ListComponent.add(Component);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create a panel for the data and add it to the card layout
                JPanel dataPanel = new JPanel();
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                // Create a back button to return to the search panel
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                dataPanel.add(backButton);

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        JButton btnWeapons = new JButton("Weapons");
        btnWeapons.setBounds(0, 399, 384, 100);
        buttonPanel.add(btnWeapons);
        btnWeapons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                try {
                	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
	    			        + "SELECT (strafter(str(?name), '#') AS ?classname) "
	    			        + "WHERE { ?name rdf:type ex:Weapons } ";
                    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                    while (results.hasNext()) {
                        QuerySolution soln = results.nextSolution();
                        RDFNode subclass = soln.get("classname");
                        String Component = subclass.toString();
                        ListComponent.add(Component);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create a panel for the data and add it to the card layout
                JPanel dataPanel = new JPanel();
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                // Create a back button to return to the search panel
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                dataPanel.add(backButton);

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        cards.add(searchPanel, "searchPanel");

        // Show the search panel
        cardLayout.show(cards, "searchPanel");

        // Add the card layout to the window
        getContentPane().add(cards);

        setSize(400, 539);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Test window = new Test();
        window.setVisible(true);
    }
}
