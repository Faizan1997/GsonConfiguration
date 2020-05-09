package dynamicconfigurstion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomDesign extends JFrame {

    public CustomDesign() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(600, 20));
        JLabel headingLabel = new JLabel("Configuration");
        JButton buton = new JButton("New utton");
        headerPanel.add(headingLabel, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 500));

        //JPanel basicIpPanel=new JPanel(new GridLayout(1, 4));
        JPanel basicIpPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        basicIpPanel.setPreferredSize(new Dimension(600, 50));
        JLabel serverIpLabel = new JLabel("Server IP    ");
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        basicIpPanel.add(serverIpLabel, gridConstraints);
        JTextField serverIpField = new JTextField(20);
        serverIpField.setPreferredSize(new Dimension(30, 20));
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        basicIpPanel.add(serverIpField, gridConstraints);
        JLabel datbaseIpLabel = new JLabel("Server IP    ");
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        basicIpPanel.add(datbaseIpLabel, gridConstraints);
        JTextField databaseIpField = new JTextField(20);
        databaseIpField.setPreferredSize(new Dimension(30, 20));
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 0;
        basicIpPanel.add(databaseIpField, gridConstraints);

        mainPanel.add(basicIpPanel, BorderLayout.NORTH);

        JPanel mainGatewayPanel = new JPanel(new GridLayout(1, 3));
        mainGatewayPanel.setPreferredSize(new Dimension(600, 400));

        //JPanel gatewayXPanel = new JPanel(new GridLayout(3, 1));
        JPanel gatewayXPanel = new JPanel(new GridBagLayout());
        gatewayXPanel.setPreferredSize(new Dimension(200, 400));

        JLabel gatewayXLable = new JLabel("GateWay X");
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gatewayXPanel.add(gatewayXLable,gridConstraints);

        JTextField gatewayXField = new JTextField();
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
 
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gatewayXPanel.add(gatewayXField,gridConstraints);

        JPanel gatewayXIpPanel = new JPanel();
        gatewayXIpPanel.setPreferredSize(new Dimension(200, 350));
        gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.ipady = 10;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        mainGatewayPanel.add(gatewayXPanel,gridConstraints);

        JPanel gatewayYPanel = new JPanel(new GridLayout(3, 1));
        gatewayYPanel.setPreferredSize(new Dimension(200, 400));

        JLabel gatewayYLable = new JLabel("GateWay Y");
        gatewayYPanel.add(gatewayYLable);

        JTextField gatewayYField = new JTextField();
        gatewayYPanel.add(gatewayYField);

        JPanel gatewayYIpPanel = new JPanel();
        gatewayYIpPanel.setPreferredSize(new Dimension(200, 350));

        mainGatewayPanel.add(gatewayYPanel);

        JPanel gatewayZPanel = new JPanel(new GridLayout(3, 1));
        gatewayZPanel.setPreferredSize(new Dimension(200, 400));

        JLabel gatewayZLable = new JLabel("GateWay Z");
        gatewayZPanel.add(gatewayZLable);

        JTextField gatewayZField = new JTextField();
        gatewayZPanel.add(gatewayZField);

        JPanel gatewayZIpPanel = new JPanel();
        gatewayZIpPanel.setPreferredSize(new Dimension(200, 350));

        mainGatewayPanel.add(gatewayZPanel);

        mainPanel.add(mainGatewayPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();

    }
}
