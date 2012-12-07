
package swingApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class WorldCupCountries implements ActionListener {
    final static int NUM_IMAGES = 8;
    final static int START_INDEX = 3;

    ImageIcon[] images = new ImageIcon[NUM_IMAGES];
    JPanel mainPanel, selectPanel, displayPanel;

    JComboBox countryChoices = null;
    JLabel countryIconLabel = null;

    public WorldCupCountries() {
        selectPanel = new JPanel();
        displayPanel = new JPanel();
        addWidgets();


        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        mainPanel.add(selectPanel);
        mainPanel.add(displayPanel);
    }

    private void addWidgets() {
        
        images[0]=createImageIcon("/images/Belarus.gif");
        images[1]=createImageIcon("/images/Belgium.gif");
        images[2]=createImageIcon("/images/Croatia.gif");
        images[3]=createImageIcon("/images/CzechRepublic.gif");
        images[4]=createImageIcon("/images/SouthKorea.gif");
        images[5]=createImageIcon("/images/Ukraine.gif");


        countryIconLabel = new JLabel();
        countryIconLabel.setHorizontalAlignment(JLabel.CENTER);
        countryIconLabel.setVerticalAlignment(JLabel.CENTER);
        countryIconLabel.setVerticalTextPosition(JLabel.CENTER);
        countryIconLabel.setHorizontalTextPosition(JLabel.CENTER);
        countryIconLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5,5,5,5)));

        countryIconLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0,0,10,0),
            countryIconLabel.getBorder()));


        String[] phases = { "Belarus", "Belgium", "Croatia", 
                            "CzechRepublic", "SouthKorea", "Ukraine" };
        countryChoices = new JComboBox(phases);
        countryChoices.setSelectedIndex(START_INDEX);
        countryIconLabel.setIcon(images[START_INDEX]);
        countryIconLabel.setText("");
        selectPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Select WorldCup 2006 Country"), 
            BorderFactory.createEmptyBorder(5,5,5,5)));
        displayPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("National Flag"), 
            BorderFactory.createEmptyBorder(5,5,5,5)));
        
        displayPanel.add(countryIconLabel);
        selectPanel.add(countryChoices);
        selectPanel.setPreferredSize(new Dimension(200,100));
        countryChoices.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if ("comboBoxChanged".equals(event.getActionCommand())) {
            countryIconLabel.setIcon(images[countryChoices.getSelectedIndex()]);
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imageURL = WorldCupCountries.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + path);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }
    
    private static void createAndShowGUI() {

        //JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");        
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }      
        
        WorldCupCountries flags = new WorldCupCountries();


        JFrame CountryFrame = new JFrame("WorldCup Countries");
        CountryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        CountryFrame.setContentPane(flags.mainPanel);

        CountryFrame.pack();
        CountryFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}