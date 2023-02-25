package myapp;

import javax.swing.*;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TestValorantApp extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cards;
    
    public class XYLayout implements LayoutManager {

        public void addLayoutComponent(String name, Component comp) {}

        public void removeLayoutComponent(Component comp) {}

        public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                for (Component c : parent.getComponents()) {
                    if (c.isVisible()) {
                        c.setBounds(c.getX(), c.getY(), c.getWidth(), c.getHeight());
                    }
                }
            }
        }
    }

    public TestValorantApp() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TestValorantApp.class.getResource("/myapp/images/images.png")));
    	setTitle("Valorant App");
    	
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 384, 554);
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
                String state = "relessMap";
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

                
                JPanel dataPanel = new JPanel(new XYLayout());
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JLabel showDataText = new JLabel("List of Maps:");
                dataPanel.add(showDataText);
                showDataText.setBounds(10,20,100,20);
                showDataText.setForeground(Color.white);
                JScrollPane scrollPane = new JScrollPane(list);
                scrollPane.setBounds(10,50,363,190);
                dataPanel.add(scrollPane);
                dataPanel.setBackground(new Color(14, 25, 35));
                JButton backButton = new JButton("Back");
                dataPanel.add(backButton);
                backButton.setFocusPainted(false);
                backButton.setBounds(293,20,80,22);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            
                            List<String> ListPatch = new ArrayList<>();
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?y), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + "?x rdfs:subClassOf ex:Valorant . "
            	    			        + "FILTER (?x =ex:Patch). "
            	    			        + "?y rdf:type ?x. "
            	    			        + "?y ?predicate ?object . "
            	    			        + "FILTER(?predicate = ex:" + state + " && ?object = ex:" + selectedItem + ")"
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode patch = soln.get("classname");
                                    String patchComponent = patch.toString();
                                    ListPatch.add(patchComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            
                            JPanel selectedMapPanel = new JPanel();
                            selectedMapPanel.setBackground(new Color(14, 25, 35));
                            JLabel selectedDataLabel = new JLabel("Map: " + selectedItem);
                            selectedDataLabel.setForeground(Color.WHITE);
                            selectedMapPanel.add(selectedDataLabel);
                            
                            JLabel selectedPatch = new JLabel("Releases: " + ListPatch.get(0));
                            selectedPatch.setForeground(Color.WHITE);
                            selectedMapPanel.add(selectedPatch); 
                            
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedMapPanel.add(backButton);

                            if ( selectedItem.equals("Haven")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Haven.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Ascent")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Ascent.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Bind")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Bind.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Breeze")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Breeze.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Fracture")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Fracture.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Icebox")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Icebox .jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Lotus")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Lotus.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Pearl")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Pearl.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("The_Range")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Range.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Split")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Loading_Screen_Split.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedMapPanel.add(imageLabel);
                            }
                            cards.add(selectedMapPanel, "selectedMapPanel");
                            cardLayout.show(cards, "selectedMapPanel");
                        }
                    }
                });
                

                cards.add(dataPanel, "dataPanel");
                cardLayout.show(cards, "dataPanel");
            }
        });
        buttonPanel.setLayout(null);
        buttonPanel.add(btnMaps);

        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.add(buttonPanel);
        
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
                String state = "relessMode";
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
                
                JPanel dataPanel = new JPanel(new XYLayout());
                dataPanel.setBackground(new Color(14, 25, 35));
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);
                
                JLabel showDataText = new JLabel("List of Modes:");
                dataPanel.add(showDataText);
                showDataText.setBounds(10,20,100,20);
                showDataText.setForeground(Color.white);
                JButton backButton = new JButton("Back");
                dataPanel.add(backButton);
                backButton.setFocusPainted(false);
                backButton.setBounds(293,20,80,22);
                scrollPane.setBounds(10,50,363,208);
                
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });

                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            List<String> modeList = new ArrayList<>();
                            List<String> ListMode = new ArrayList<>();
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?y), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + "?x rdfs:subClassOf ex:Valorant . "
            	    			        + "FILTER (?x =ex:Patch). "
            	    			        + "?y rdf:type ?x. "
            	    			        + "?y ?predicate ?object . "
            	    			        + "FILTER(?predicate = ex:" + state + " && ?object = ex:" + selectedItem + ")"
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode mode = soln.get("classname");
                                    String modeComponent = mode.toString();
                                    ListMode.add(modeComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?object), '#') AS ?classname) "
            	    			        + "WHERE {  ex:" + selectedItem + " ?predicate ?object . "
            	    			        + "FILTER(?object = ex:Plant%2FDefuse || ?object = ex:Deathmatch  || ?object = ex:Practice_Game_Modes) "
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode subclass = soln.get("classname");
                                    String Component = subclass.toString();
                                    modeList.add(Component);
                                }
                                if (modeList.size() == 0) {
                                	modeList.add("Plant/Defuse");
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            
                            JPanel selectedModePanel = new JPanel(new XYLayout());
                            selectedModePanel.setBackground(new Color(14, 25, 35));
                            JLabel selectedDataLabel = new JLabel("Mode: " + selectedItem);
                            selectedDataLabel.setBounds(125,170,400,150);
                            JLabel selectedMode = new JLabel("Releases: " + ListMode.get(0));
                            selectedMode.setForeground(Color.white);
                            selectedMode.setBounds(140,130,400,150);
                            JLabel modeLabel = new JLabel("Mode Type: " + modeList.get(0)) ;
                            modeLabel.setForeground(Color.white);
                            modeLabel.setBounds(105,210,400,150);
                            selectedDataLabel.setForeground(Color.white);
                            selectedModePanel.add(selectedDataLabel);
                            selectedModePanel.add(selectedMode);
                            modeLabel.setFont(new Font("Arial", Font.BOLD, 17));
                            selectedDataLabel.setFont(new Font("Arial", Font.BOLD, 17));
                            selectedMode.setFont(new Font("Arial", Font.BOLD, 17));
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedModePanel.add(backButton);
                            backButton.setBounds(293,20,80,22);
                            selectedModePanel.add(modeLabel);

                            if ( selectedItem.equals("Deathmatch")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Deathmatch.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Competitive")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Competitive.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Spike_Planting")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Unrate.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Spike_Rush")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Spike_Rush.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Shooting_Test")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Unrate.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            
                            if ( selectedItem.equals("Replication")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/TX_Replication_Icon.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Swiftplay")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Swiftplay.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Snowball_Fight")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Snowball Fight.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Escalation")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Escalation.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Unrated")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Unrate.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Spike_Defuse")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Unrate.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(122, 30, 150, 150);
        		        		selectedModePanel.add(imageLabel);
                            }
                            
                            
                            cards.add(selectedModePanel, "selectedModePanel");
                            cardLayout.show(cards, "selectedModePanel");
                        }
                    }
                });
                
                cards.add(dataPanel, "dataPanel");
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
                String state = "relessAgent";
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

                JPanel dataPanel = new JPanel(new XYLayout());
                dataPanel.setBackground(new Color(14, 25, 35));
                
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);
                JLabel showDataText = new JLabel("List of Agents:");
                dataPanel.add(showDataText);
                showDataText.setBounds(10,20,100,20);
                showDataText.setForeground(Color.white);
                JButton backButton = new JButton("Back");
                dataPanel.add(backButton);
                backButton.setFocusPainted(false);
                backButton.setBounds(293,20,80,22);
                scrollPane.setBounds(10,50,363,370);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            List<String> ListAgent = new ArrayList<>();
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?y), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + "?x rdfs:subClassOf ex:Valorant . "
            	    			        + "FILTER (?x =ex:Patch). "
            	    			        + "?y rdf:type ?x. "
            	    			        + "?y ?predicate ?object . "
            	    			        + "FILTER(?predicate = ex:" + state + " && ?object = ex:" + selectedItem + ")"
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode agent = soln.get("classname");
                                    String agentComponent = agent.toString();
                                    ListAgent.add(agentComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            
                            JPanel selectedAgentPanel = new JPanel();
                            selectedAgentPanel.setBackground(new Color(14, 25, 35));
                            JLabel selectedDataLabel = new JLabel("Agent: " + selectedItem);
                            selectedDataLabel.setForeground(Color.white);
                            selectedAgentPanel.add(selectedDataLabel);
                            JLabel selectedAgent = new JLabel("Releases: " + ListAgent.get(0));
                            selectedAgent.setForeground(Color.white);
                            selectedAgentPanel.add(selectedAgent);
                            JButton backButton = new JButton("Back");
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            selectedAgentPanel.add(backButton);
                            
                            if ( selectedItem.equals("Cypher")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Cypher_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Astra")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Astra_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Neon")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Neon_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Jett")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Jett_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Sova")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sova_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Killjoy")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Killjoy_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Breach")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Breach_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Omen")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Omen_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Sage")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sage_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Phoenix")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Phoenix_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Reyna")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Reyna_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Viper")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Viper_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Harbor")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Harbor_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("KAYO")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/KAYO_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Yoru")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Yoru_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Brimstone")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Brimstone_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Skye")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Skye_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Raze")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Raze_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Fade")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Fade_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Chamber")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Chamber_icon.jpg");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(10, 100, 150, 150);
        		        		selectedAgentPanel.add(imageLabel);
                            }

                            cards.add(selectedAgentPanel, "selectedAgentPanel");
                            cardLayout.show(cards, "selectedAgentPanel");
                        }
                    }
                });

                cards.add(dataPanel, "dataPanel");
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
                String state = "relessSkin";
                
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

                JPanel dataPanel = new JPanel(new XYLayout());
                dataPanel.setBackground(new Color(14, 25, 35));
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                JLabel showDataText = new JLabel("List of Skins:");
                dataPanel.add(showDataText);
                showDataText.setBounds(10,20,100,20);
                showDataText.setForeground(Color.white);
                JButton backButton = new JButton("Back");
                dataPanel.add(backButton);
                backButton.setFocusPainted(false);
                backButton.setBounds(293,20,80,22);
                scrollPane.setBounds(10,50,363,385);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                        	List<String> ListComponentSkins = new ArrayList<>();
                        	String selectedItem = list.getSelectedValue();
                        	try {
                        	    String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
                        	            + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        	            + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                        	            + "SELECT (strafter(str(?object), '#') AS ?localName) "
                        	            + "WHERE { ex:" + selectedItem + " ?predicate ?object . "
                        	            + "FILTER(?predicate != rdf:type && ?object != ex:" + selectedItem + ") }";
                        	    org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                        	    while (results.hasNext()) {
                        	        QuerySolution soln = results.nextSolution();
                        	        RDFNode localNameNode = soln.get("localName");
                        	        String localName = localNameNode.toString();
                        	        ListComponentSkins.add(localName);
                        	    }
                        	} catch (Exception ex) {
                        	    ex.printStackTrace();
                        	}
                        	
                        	List<String> ListSkin = new ArrayList<>();
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?y), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + "?x rdfs:subClassOf ex:Valorant . "
            	    			        + "FILTER (?x =ex:Patch). "
            	    			        + "?y rdf:type ?x. "
            	    			        + "?y ?predicate ?object . "
            	    			        + "FILTER(?predicate = ex:" + state + " && ?object = ex:" + selectedItem + ")"
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode skin = soln.get("classname");
                                    String skinComponent = skin.toString();
                                    ListSkin.add(skinComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            JPanel selectedSkinPanel = new JPanel(new XYLayout());
                            selectedSkinPanel.setBackground(new Color(14, 25, 35));
                            JLabel selectedDataLabel = new JLabel("Collection: " + selectedItem);
                            selectedDataLabel.setForeground(Color.white);
                            selectedSkinPanel.add(selectedDataLabel);
                            JLabel selectedSkin = new JLabel("Releases: " + ListSkin.get(0));
                            selectedSkin.setForeground(Color.white);
                            selectedSkin.setBounds(290,50,300,20);
                            selectedSkinPanel.add(selectedSkin);
                            JList<String> listSkins = new JList<>(ListComponentSkins.toArray(new String[0]));
                            JScrollPane scrollPaneSkins = new JScrollPane(listSkins);
                            selectedSkinPanel.add(scrollPaneSkins);
                            
                            
                            JLabel collection = new JLabel("Collection: " + selectedItem);
                            selectedSkinPanel.add(collection);
                            collection.setBounds(10,20,300,20);
                            collection.setForeground(Color.white);
                            JLabel showDataText = new JLabel("List of Weapons:");
                            selectedSkinPanel.add(showDataText);
                            showDataText.setBounds(10,50,300,20);
                            showDataText.setForeground(Color.white);
                            JButton backButton = new JButton("Back");
                            selectedSkinPanel.add(backButton);
                            backButton.setFocusPainted(false);
                            backButton.setBounds(293,20,80,22);
                            scrollPaneSkins.setBounds(10,80,363,385);
                            
                            listSkins.addMouseListener(new java.awt.event.MouseAdapter() {
                                @Override
                                public void mouseClicked(java.awt.event.MouseEvent e ) {
                                    if (e.getClickCount() == 2) {
                                    	String skinName = selectedItem + " " + listSkins.getSelectedValue();
                                        JPanel selectedSkinOfWeaponPanel = new JPanel(new XYLayout());
                                        selectedSkinOfWeaponPanel.setBackground(new Color(14, 25, 35));
                                        
                                        JLabel selectedSkinOfWeapon = new JLabel("Skin: " + skinName);
                                        selectedSkinOfWeapon.setForeground(Color.white);
                                        selectedSkinOfWeapon.setBounds(40,-115,300,300);
                                        selectedSkinOfWeaponPanel.add(selectedSkinOfWeapon);
                                        
                                        cards.add(selectedSkinOfWeaponPanel, "selectedSkinOfWeaponPanel");
                                        cardLayout.show(cards, "selectedSkinOfWeaponPanel");
                                        selectedSkinOfWeapon.setFont(new Font("Arial", Font.BOLD, 17));
                                        JButton backButton = new JButton("Back");
                                        backButton.setBounds(480,20,80,22);
                                        backButton.addActionListener(new ActionListener() {
                                            @Override	
                                            public void actionPerformed(ActionEvent e) {
                                                cardLayout.show(cards, "selectedSkinPanel");
                                                setSize(400,593);
                                            }
                                        });
                                        selectedSkinOfWeaponPanel.add(backButton);    
                                        
                                        if ( skinName.equals("Oni Guardian")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Oni_Guardian.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Oni Shorty")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Oni_Shorty.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prism_II Shorty")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prism_II_Shorty.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ruination Ghost")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ruination_Ghost.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Horizon Frenzy")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Horizon_Frenzy.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Horizon Bulldog")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Horizon_Bulldog.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Spectrum Bulldog")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/SPECTRUM_Bulldog.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("BlastX Odin")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/BlastX_Odin.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("BlastX Phantom")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/BlastX_Phantom.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Infantry Ghost")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Infantry_Ghost.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prism Tactical_Knife")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prism_Knife.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Sakura Sheriff")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sakura_Sheriff.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Sakura Stinger")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sakura_Stinger.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Aristocrat Ares")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Aristocrat_Ares.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Luna Marshal")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Luna_Marshal.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Magepunk Bucky")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Magepunk_Bucky.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ego Stinger")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ego_Stinger.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ego Tactical_Knife")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ego_Knife.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ion Vandal")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ion_Vandal.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ion Operator")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ion_Operator.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ion Sheriff")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ion_Sheriff.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Ion Phantom")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ion_Phantom.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime Classic")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_Classic.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime Vandal")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_Vandal.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime Guardian")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_Guardian.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime Spectre")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_Spectre.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Sovereign Marshal")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sovereign_Marshal.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Elderflame Operator")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Elderflame_Operator.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Elderflame Judge")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Elderflame_Judge.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Smite Judge")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Smite_Judge.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Smite Classic")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Smite_Classic.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime2 Odin")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_2.0_Odin.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Prime2 Bucky")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Prime_2.0_Bucky.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Spline Spectre")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Spline_Spectre.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Rush Frenzy")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Rush_Frenzy.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        if ( skinName.equals("Rush Ares")) {
                                        	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Rush_Ares.png");
                    		        		JLabel imageLabel = new JLabel(imageIcon);
                    		        		imageLabel.setBounds(40, 100, 500, 150);
                    		        		selectedSkinOfWeaponPanel.add(imageLabel);
                                        }
                                        setSize(600,400);
                                    }
                                }
                            });
                            
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                }
                            });
                            
                            cards.add(selectedSkinPanel, "selectedSkinPanel");
                            cardLayout.show(cards, "selectedSkinPanel");
                        }
                    }
                });

                cards.add(dataPanel, "dataPanel");
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
        lblNewLabel.setFont(new Font("VALORANT", Font.PLAIN, 33));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(86, 21, 209, 33);
        buttonPanel.add(lblNewLabel);
        btnWeapons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Query the database for data
                List<String> ListComponent = new ArrayList<>();
                String state = "relessWeapon";
                
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

                JPanel dataPanel = new JPanel(new XYLayout());
                dataPanel.setBackground(new Color(14, 25, 35));
                JList<String> list = new JList<>(ListComponent.toArray(new String[0]));
                JScrollPane scrollPane = new JScrollPane(list);
                dataPanel.add(scrollPane);

                JLabel showDataText = new JLabel("List of Weapons:");
                dataPanel.add(showDataText);
                showDataText.setBounds(10,20,100,20);
                showDataText.setForeground(Color.white);
                JButton backButton = new JButton("Back");
                dataPanel.add(backButton);
                backButton.setFocusPainted(false);
                backButton.setBounds(293,20,80,22);
                scrollPane.setBounds(10,50,363,370);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cards, "searchPanel");
                    }
                });
                
                list.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e ) {
                        if (e.getClickCount() == 2) {
                            String selectedItem = list.getSelectedValue();
                            List<String> ListWeapon = new ArrayList<>();
                            List<String> WeaponType = new ArrayList<>();
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?result), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + " ?x rdfs:subClassOf ex:Weapons."
            	    			        + " ex:" + selectedItem + " rdf:type ?result."
            	    			        + " FILTER(?result = ?x && ?result != ex:Weapons) }";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode type = soln.get("classname");
                                    String weaponTypeComponent = type.toString();
                                    WeaponType.add(weaponTypeComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            
                            try {
                            	String queryString = "PREFIX ex: <http://www.semanticweb.org/asus/ontologies/2566/0/untitled-ontology-17#> "
            	    			        + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
            	    			        + "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
            	    			        + "SELECT (strafter(str(?y), '#') AS ?classname) "
            	    			        + "WHERE { "
            	    			        + "?x rdfs:subClassOf ex:Valorant . "
            	    			        + "FILTER (?x =ex:Patch). "
            	    			        + "?y rdf:type ?x. "
            	    			        + "?y ?predicate ?object . "
            	    			        + "FILTER(?predicate = ex:" + state + " && ?object = ex:" + selectedItem + ")"
            	    			        + "} ";
                                org.apache.jena.query.ResultSet results = OpenOWL.ExecSparQl(queryString);
                                while (results.hasNext()) {
                                    QuerySolution soln = results.nextSolution();
                                    RDFNode weapon = soln.get("classname");
                                    String weaponComponent = weapon.toString();
                                    ListWeapon.add(weaponComponent);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            setSize(600,400);
                            
                            JPanel selectedWeaponPanel = new JPanel(new XYLayout());
                            selectedWeaponPanel.setBackground(new Color(14, 25, 35));
                            JLabel selectedDataLabel = new JLabel("Weapon: " + selectedItem);
                            selectedDataLabel.setForeground(Color.white);
                            selectedDataLabel.setBounds(40,-115,300,300);
                            selectedWeaponPanel.add(selectedDataLabel);
                            JLabel selectedWeapon = new JLabel("Releases: " + ListWeapon.get(0));
                            selectedWeapon.setForeground(Color.white);
                            selectedWeaponPanel.add(selectedWeapon);
                            selectedWeapon.setBounds(370,-115,300,300);
                            JButton backButton = new JButton("Back");
                            backButton.setBounds(480,20,80,22);
                            JLabel selectedWeaponType = new JLabel("Weapon Type: " + WeaponType.get(0));
                            selectedWeaponType.setForeground(Color.white);
                            selectedWeaponType.setBounds(40,-85,300,300);
                            selectedWeaponPanel.add(selectedWeaponType);
                            backButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cardLayout.show(cards, "dataPanel");
                                    setSize(400,593);
                                }
                            });
                            selectedWeaponPanel.add(backButton);

                            if ( selectedItem.equals("Vandal")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Vandal.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Snowball_Launcher")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/SnowballLauncher_icon.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Judge")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Judge.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Classic")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Classic.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Frenzy")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Frenzy.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Sheriff")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Sheriff.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Marshal")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Marshal.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Tactical_Knife")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/TacticalKnife.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Odin")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Odin.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Ghost")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ghost.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Stinger")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Stinger.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Guardian")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Guardian.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Operator")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Operator.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Spectre")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Spectre.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Bulldog")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Bulldog.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Phantom")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Phantom.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Shorty")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Shorty.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Golden_Gun")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/GoldenGun.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Ares")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Ares.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            if ( selectedItem.equals("Bucky")) {
                            	ImageIcon imageIcon = new ImageIcon("src/myapp/images/Bucky.png");
        		        		JLabel imageLabel = new JLabel(imageIcon);
        		        		imageLabel.setBounds(40, 100, 500, 150);
        		        		selectedWeaponPanel.add(imageLabel);
                            }
                            
                            cards.add(selectedWeaponPanel, "selectedWeaponPanel");
                            cardLayout.show(cards, "selectedWeaponPanel");
                        }
                    }
                });

                cards.add(dataPanel, "dataPanel");

                cardLayout.show(cards, "dataPanel");
            }
        });
        cards.add(searchPanel, "searchPanel");

        cardLayout.show(cards, "searchPanel");

        getContentPane().add(cards);

        setSize(400, 593);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	TestValorantApp window = new TestValorantApp();
        window.setVisible(true);
    }
}
