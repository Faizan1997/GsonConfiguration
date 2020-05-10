package dynamicconfigurstion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomDesign extends JFrame {

    JPanel headerPanel;
    JLabel headingLabel;
    JPanel mainPanel;
    JPanel basicIpPanel;
    GridBagConstraints gridConstraints;
    JLabel serverIpLabel;
    JTextField serverIpField;
    JLabel datbaseIpLabel;
    JTextField databaseIpField;
    JPanel mainGatewayPanel;
    JPanel gatewayXPanel;
    JLabel gatewayXLable;
    JTextField gatewayXField;
    JPanel gatewayXIpPanel;
    JPanel gatewayYPanel;
    JLabel gatewayYLable;
    JTextField gatewayYField;
    JPanel gatewayYIpPanel;
    JPanel gatewayZPanel;
    JLabel gatewayZLable;
    JTextField gatewayZField;
    JPanel gatewayZIpPanel;
    JLabel SMUIpLabel;
    JTextField SMUIpField;
    ReadData readData=new ReadData();

    public CustomDesign() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(600, 20));
        headingLabel = new JLabel("Configuration");

        headerPanel.add(headingLabel, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 500));

        basicIpPanel = new JPanel(new GridBagLayout());
        gridConstraints = new GridBagConstraints();

        basicIpPanel.setPreferredSize(new Dimension(600, 50));
        
        Custom data=readData.readJsonData("custom.json");
        Field[] datamember = data.getClass().getDeclaredFields();
        int x=0;
        int y=0;
        
        for (int i = 0; i < datamember.length; i++) {
            
            //JPanel basicIpPanel=new JPanel(new GridLayout(1, 4));
            if(datamember[i].getType()== String.class){
                if(x==0){
                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                }
                //serverIpLabel = new JLabel(datamember[i].getName());
                gridConstraints.gridx = x;
                gridConstraints.gridy = y;
                
                 basicIpPanel.add(new JLabel(datamember[i].getName()), gridConstraints);
                 x++;
                 gridConstraints.gridx = x;
                gridConstraints.gridy = y;
                
                 basicIpPanel.add(new JTextField(20), gridConstraints);
                 
                 if(x==3){
                     x=0;
                     y++;
                 }else
                     x++;
            }
            
            
            
//            if (i == 0) {
//                serverIpLabel = new JLabel("Server IP    ");
//                gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//                gridConstraints.gridx = 0;
//                gridConstraints.gridy = 0;
//
//                basicIpPanel.add(serverIpLabel, gridConstraints);
//
//                serverIpField = new JTextField(20);
//                serverIpField.setPreferredSize(new Dimension(30, 20));
//                gridConstraints.gridx = 1;
//                gridConstraints.gridy = 0;
//
//                basicIpPanel.add(serverIpField, gridConstraints);
//            }
//            if (i == 1) {
//                datbaseIpLabel = new JLabel("DB IP    ");
//                gridConstraints.gridx = 2;
//                gridConstraints.gridy = 0;
//
//                basicIpPanel.add(datbaseIpLabel, gridConstraints);
//
//                databaseIpField = new JTextField(20);
//                databaseIpField.setPreferredSize(new Dimension(30, 20));
//                gridConstraints.gridx = 3;
//                gridConstraints.gridy = 0;
//
//                basicIpPanel.add(databaseIpField, gridConstraints);
//            }
//            if (i == 1) {
//                SMUIpLabel = new JLabel("SMU IP    ");
//                gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//                gridConstraints.gridx = 0;
//                gridConstraints.gridy = 1;
//
//                basicIpPanel.add(SMUIpLabel, gridConstraints);
//
//                SMUIpField = new JTextField(20);
//                SMUIpField.setPreferredSize(new Dimension(30, 20));
//                gridConstraints.gridx = 1;
//                gridConstraints.gridy = 1;
//
//                basicIpPanel.add(SMUIpField, gridConstraints);
//            }
        }
        mainPanel.add(basicIpPanel, BorderLayout.NORTH);

        mainGatewayPanel = new JPanel(new GridLayout(1, 3));
        mainGatewayPanel.setPreferredSize(new Dimension(600, 400));

        //JPanel gatewayXPanel = new JPanel(new GridLayout(3, 1));
        gatewayXPanel = new JPanel(new GridBagLayout());
        gatewayXPanel.setPreferredSize(new Dimension(200, 400));

        gatewayXLable = new JLabel("GateWay X");
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gatewayXPanel.add(gatewayXLable, gridConstraints);

        gatewayXField = new JTextField(10);
        // gridConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gatewayXPanel.add(gatewayXField, gridConstraints);

        gatewayXIpPanel = new JPanel();
        gatewayXIpPanel.setPreferredSize(new Dimension(200, 350));
        gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.ipady = 10;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        mainGatewayPanel.add(gatewayXPanel, gridConstraints);

        gatewayYPanel = new JPanel(new GridLayout(3, 1));
        gatewayYPanel.setPreferredSize(new Dimension(200, 400));

        gatewayYLable = new JLabel("GateWay Y");
        gatewayYPanel.add(gatewayYLable);

        gatewayYField = new JTextField();
        gatewayYPanel.add(gatewayYField);

        gatewayYIpPanel = new JPanel();
        gatewayYIpPanel.setPreferredSize(new Dimension(200, 350));

        mainGatewayPanel.add(gatewayYPanel);

        gatewayZPanel = new JPanel(new GridLayout(3, 1));
        gatewayZPanel.setPreferredSize(new Dimension(200, 400));

        gatewayZLable = new JLabel("GateWay Z");
        gatewayZPanel.add(gatewayZLable);

        gatewayZField = new JTextField();
        gatewayZPanel.add(gatewayZField);

        gatewayZIpPanel = new JPanel();
        gatewayZIpPanel.setPreferredSize(new Dimension(200, 350));

        mainGatewayPanel.add(gatewayZPanel);

        mainPanel.add(mainGatewayPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();

    }
}
