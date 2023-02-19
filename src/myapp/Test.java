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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Test.class.getResource("/myapp/images/images.png")));
    	setTitle("Valorant App");
        // Create a card layout to switch between panels
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(14, 25, 35));
        JButton btnMaps = new JButton("Maps");
        btnMaps.setFont(new Font("Arial", Font.BOLD, 17));
        btnMaps.setBounds(0, 55, 384, 100);
        btnMaps.setBackground(new Color(255, 75, 80));
        btnMaps.setForeground(Color.WHITE);
        btnMaps.setFocusPainted(false);
        btnMaps.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        btnMaps.setBorder(BorderFactory.createLineBorder(new Color(14, 25, 35), 3, true));
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
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            // Add code here to show the selected data on a new panel
                            JPanel selectedMapPanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedMapPanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedMapPanel.add(backButton);

                            cards.add(selectedMapPanel, "selectedMapPanel");
                            cardLayout.show(cards, "selectedMapPanel");
                        }
                    }
                });
                

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
        btnModes.setFont(new Font("Arial", Font.BOLD, 17));
        btnModes.setBounds(0, 155, 384, 100);
        buttonPanel.add(btnModes);
        btnModes.setBackground(new Color(255, 75, 80));
        btnModes.setForeground(Color.WHITE);
        btnModes.setFocusPainted(false);
        btnModes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        btnModes.setBorder(BorderFactory.createLineBorder(new Color(14, 25, 35), 3, true));
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
                            JPanel selectedModePanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedModePanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedModePanel.add(backButton);

                            cards.add(selectedModePanel, "selectedModePanel");
                            cardLayout.show(cards, "selectedModePanel");
                        }
                    }
                });
                
                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        JButton btnAgents = new JButton("Agents");
        btnAgents.setFont(new Font("Arial", Font.BOLD, 17));
        btnAgents.setBounds(0, 255, 384, 100);
        buttonPanel.add(btnAgents);
        btnAgents.setBackground(new Color(255, 75, 80));
        btnAgents.setForeground(Color.WHITE);
        btnAgents.setFocusPainted(false);
        btnAgents.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        btnAgents.setBorder(BorderFactory.createLineBorder(new Color(14, 25, 35), 3, true));
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
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            // Add code here to show the selected data on a new panel
                            JPanel selectedAgentPanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedAgentPanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedAgentPanel.add(backButton);

                            cards.add(selectedAgentPanel, "selectedAgentPanel");
                            cardLayout.show(cards, "selectedAgentPanel");
                        }
                    }
                });

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        
        JButton btnSkins = new JButton("Skins");
        btnSkins.setFont(new Font("Arial", Font.BOLD, 17));
        btnSkins.setBounds(0, 354, 384, 100);
        buttonPanel.add(btnSkins);
        btnSkins.setBackground(new Color(255, 75, 80));
        btnSkins.setForeground(Color.WHITE);
        btnSkins.setFocusPainted(false);
        btnSkins.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        btnSkins.setBorder(BorderFactory.createLineBorder(new Color(14, 25, 35), 3, true));
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
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            // Add code here to show the selected data on a new panel
                            JPanel selectedSkinPanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedSkinPanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedSkinPanel.add(backButton);

                            cards.add(selectedSkinPanel, "selectedSkinPanel");
                            cardLayout.show(cards, "selectedSkinPanel");
                        }
                    }
                });

                cards.add(dataPanel, "dataPanel");

                // Show the data panel
                cardLayout.show(cards, "dataPanel");
            }
        });
        
        JButton btnWeapons = new JButton("Weapons");
        btnWeapons.setFont(new Font("Arial", Font.BOLD, 17));
        btnWeapons.setBounds(0, 454, 384, 100);
        buttonPanel.add(btnWeapons);
        btnWeapons.setBackground(new Color(255, 75, 80));
        btnWeapons.setForeground(Color.WHITE);
        btnWeapons.setFocusPainted(false);
        btnWeapons.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        btnWeapons.setBorder(BorderFactory.createLineBorder(new Color(14, 25, 35), 3, true));
        
        JLabel lblNewLabel = new JLabel("Valorant");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Westmeath", Font.PLAIN, 40));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(88, 11, 209, 33);
        buttonPanel.add(lblNewLabel);
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
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            // Add code here to show the selected data on a new panel
                            JPanel selectedWeaponPanel = new JPanel();
                            JLabel selectedDataLabel = new JLabel("You selected: " + selectedItem);
                            selectedWeaponPanel.add(selectedDataLabel);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedWeaponPanel.add(backButton);

                            cards.add(selectedWeaponPanel, "selectedWeaponPanel");
                            cardLayout.show(cards, "selectedWeaponPanel");
                        }
                    }
                });

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

        setSize(400, 593);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Test window = new Test();
        window.setVisible(true);
    }
}
