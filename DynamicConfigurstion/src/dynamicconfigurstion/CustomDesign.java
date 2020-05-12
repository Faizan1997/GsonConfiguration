package dynamicconfigurstion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
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
    ReadData readData = new ReadData();
    Map<String, JTextField> dataMembersMap = new HashMap<>();
    Map<String, Method> methodMap = new HashMap<>();
    Map<Integer, Map> gatewayMap = new HashMap<>();

    int xPoint = 0;
    int yPoint = 0;
    Object s;
    int gatewayCount = 0;
    int listCount=0;
    
    Field[] dataFieldsrArray;
    Method[] methodsArray;

    public CustomDesign() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.lightGray);
        headerPanel.setPreferredSize(new Dimension(600, 50));
        headingLabel = new JLabel("Configuration");

        headerPanel.add(headingLabel, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 500));

        basicIpPanel = new JPanel(new GridBagLayout());
        basicIpPanel.setBackground(Color.YELLOW);
        gridConstraints = new GridBagConstraints();

        basicIpPanel.setPreferredSize(new Dimension(600, 100));

        Custom data = readData.readJsonData("custom.json");
        System.err.println(data.getDatabaseIp());
        System.err.println(data.getServerIp());
        System.err.println(data.getSMUIp());
        System.out.println("Done");
        dataFieldsrArray = data.getClass().getDeclaredFields();
        Class obj = Custom.class;
        methodsArray = obj.getMethods();
        countofList();
        try {

            for (int j = 0; j < methodsArray.length; j++) {

                methodMap.put(methodsArray[j].getName().toLowerCase(), methodsArray[j]);

            }

        } catch (Exception e) {

        }

        
        mainGatewayPanel = new JPanel(new GridLayout(1, listCount));
       // mainGatewayPanel.setPreferredSize(new Dimension(600, 400));
        mainGatewayPanel.setBackground(Color.PINK);
        
        
        
        
        for (int i = 0; i < dataFieldsrArray.length; i++) {
            try {

                s = (methodMap.get("get" + dataFieldsrArray[i].getName().toLowerCase())).invoke(data, null);

            } catch (Exception e) {

            }
            //JPanel basicIpPanel=new JPanel(new GridLayout(1, 4));
            if (dataFieldsrArray[i].getType() == String.class && (s != null)) {
                if (xPoint == 0) {
                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                }
                //serverIpLabel = new JLabel(datamember[i].getName());
                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
                dataMembersMap.put(dataFieldsrArray[i].getName(), new JTextField(20));
                basicIpPanel.add(new JLabel(dataFieldsrArray[i].getName()), gridConstraints);
                xPoint++;
                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;

                basicIpPanel.add(dataMembersMap.get(dataFieldsrArray[i].getName()), gridConstraints);

                if (xPoint == 3) {
                    xPoint = 0;
                    yPoint = yPoint + 10;
                } else {
                    xPoint++;
                }
                dataMembersMap.get(dataFieldsrArray[i].getName()).setText(s.toString());
                // fields.get(datamember[i].getName()).setText("12345");
            } else if (dataFieldsrArray[i].getType() == List.class && (s != null)) {

                gatewayCount++;
                gatewayMap.put(gatewayCount, new HashMap<Integer, Object>());

                drawGatewayPanel((List) s, dataFieldsrArray[i].getName(), gatewayMap.get(gatewayCount), gatewayCount);

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

        

        
        
        
        
        
        
        
//        //JPanel gatewayXPanel = new JPanel(new GridLayout(3, 1));
//        gatewayXPanel = new JPanel(new GridBagLayout());
//        // gatewayXPanel.setPreferredSize(new Dimension(200, 400));
//
//        gatewayXLable = new JLabel("GateWay X");
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 0;
//        gatewayXPanel.add(gatewayXLable, gridConstraints);
//
//        gatewayXField = new JTextField(15);
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        gatewayXPanel.add(gatewayXField, gridConstraints);
//
//        gatewayXIpPanel = new JPanel();
//        gatewayXIpPanel.setPreferredSize(new Dimension(180, 300));
//        gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
//        gatewayXIpPanel.setBackground(Color.red);
//        xPoint = 0;
//        yPoint = 0;
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        for (int i = 0; i < 10; i++) {
//            // if(datamember[i].getType()== String.class){
//
//            //serverIpLabel = new JLabel(datamember[i].getName());
//            gridConstraints.gridx = xPoint;
//            gridConstraints.gridy = yPoint;
//            // fields.put(datamember[i].getName(), new JTextField(20));
//
//            gatewayXIpPanel.add(new JTextField(12), gridConstraints);
//            yPoint++;
//
//            // fields.get(datamember[i].getName()).setText("12345");
//        }
//        //}
//
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 2;
//        gatewayXPanel.add(gatewayXIpPanel, gridConstraints);
//
//        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridConstraints.ipady = 10;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        mainGatewayPanel.add(gatewayXPanel, gridConstraints);
//
//        gatewayYPanel = new JPanel(new GridBagLayout());
//        gatewayYPanel.setPreferredSize(new Dimension(200, 400));
//
//        gatewayYLable = new JLabel("GateWay Y");
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 0;
//        gatewayYPanel.add(gatewayYLable, gridConstraints);
//
//        gatewayYField = new JTextField(15);
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        gatewayYPanel.add(gatewayYField, gridConstraints);
//
//        gatewayYIpPanel = new JPanel();
//        gatewayYIpPanel.setPreferredSize(new Dimension(180, 300));
//        gatewayYIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
//        gatewayYIpPanel.setBackground(Color.red);
//        xPoint = 0;
//        yPoint = 0;
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        for (int i = 0; i < 10; i++) {
//            // if(datamember[i].getType()== String.class){
//
//            //serverIpLabel = new JLabel(datamember[i].getName());
//            gridConstraints.gridx = xPoint;
//            gridConstraints.gridy = yPoint;
//            // fields.put(datamember[i].getName(), new JTextField(20));
//
//            gatewayYIpPanel.add(new JTextField(12), gridConstraints);
//            yPoint++;
//
//            // fields.get(datamember[i].getName()).setText("12345");
//        }
//        //}
//
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 2;
//        gatewayYPanel.add(gatewayYIpPanel, gridConstraints);
//
//        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridConstraints.ipady = 10;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        mainGatewayPanel.add(gatewayYPanel, gridConstraints);
//
//        gatewayZPanel = new JPanel(new GridBagLayout());
//        gatewayZPanel.setPreferredSize(new Dimension(200, 400));
//
//        gatewayZLable = new JLabel("GateWay Z");
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 0;
//        gatewayZPanel.add(gatewayZLable, gridConstraints);
//
//        gatewayZField = new JTextField(15);
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        gatewayZPanel.add(gatewayZField, gridConstraints);
//
//        gatewayZIpPanel = new JPanel();
//        gatewayZIpPanel.setPreferredSize(new Dimension(180, 300));
//        gatewayZIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
//        gatewayZIpPanel.setBackground(Color.red);
//        xPoint = 0;
//        yPoint = 0;
//        gridConstraints.fill = GridBagConstraints.VERTICAL;
//        for (int i = 0; i < 10; i++) {
//            // if(datamember[i].getType()== String.class){
//
//            //serverIpLabel = new JLabel(datamember[i].getName());
//            gridConstraints.gridx = xPoint;
//            gridConstraints.gridy = yPoint;
//            // fields.put(datamember[i].getName(), new JTextField(20));
//
//            gatewayZIpPanel.add(new JTextField(12), gridConstraints);
//            yPoint++;
//
//            // fields.get(datamember[i].getName()).setText("12345");
//        }
//        //}
//
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 2;
//        gatewayZPanel.add(gatewayZIpPanel, gridConstraints);
//
//        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridConstraints.ipady = 10;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//        mainGatewayPanel.add(gatewayZPanel, gridConstraints);

//        gatewayZPanel = new JPanel(new GridLayout(3, 1));
//        gatewayZPanel.setPreferredSize(new Dimension(200, 400));
//
//        gatewayZLable = new JLabel("GateWay Z");
//        gatewayZPanel.add(gatewayZLable);
//
//        gatewayZField = new JTextField();
//        gatewayZPanel.add(gatewayZField);
//
//        gatewayZIpPanel = new JPanel();
//        gatewayZIpPanel.setPreferredSize(new Dimension(200, 350));
//
//        mainGatewayPanel.add(gatewayZPanel);
        mainPanel.add(mainGatewayPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();

    }

    public void countofList() {
          for (int i = 0; i < dataFieldsrArray.length; i++) {
              if (dataFieldsrArray[i].getType() == List.class) {}
              listCount++;
          }
          

    }

    public void drawGatewayPanel(List x, String name, Map myPanel, int panelNumber) {

        myPanel.put(1, new JPanel(new GridBagLayout()));
        //gatewayXPanel = new JPanel(new GridBagLayout());

        myPanel.put(2, new JLabel(name));
        //gatewayXLable = new JLabel(name);

        gridConstraints.fill = GridBagConstraints.VERTICAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;

        ((JPanel) myPanel.get(1)).add((JLabel) myPanel.get(2), gridConstraints);
        //gatewayXPanel.add(gatewayXLable, gridConstraints);

        myPanel.put(3, new JTextField(15));
        //gatewayXField = new JTextField(15);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        ((JPanel) myPanel.get(1)).add((JTextField) myPanel.get(3), gridConstraints);
        ((JTextField)myPanel.get(3)).setText(x.size()+"");
        // gatewayXPanel.add(gatewayXField, gridConstraints);

        myPanel.put(4, new JPanel());
        //gatewayXIpPanel = new JPanel();

        ((JPanel) myPanel.get(4)).setPreferredSize(new Dimension(180, 300));
        //gatewayXIpPanel.setPreferredSize(new Dimension(180, 300));

        ((JPanel) myPanel.get(4)).setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        // gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        ((JPanel) myPanel.get(4)).setBackground(Color.red);
        //gatewayXIpPanel.setBackground(Color.red);
        xPoint = 0;
        yPoint = 0;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        for (int i = 0; i < x.size(); i++) {
            // if(datamember[i].getType()== String.class){

            //serverIpLabel = new JLabel(datamember[i].getName());
            gridConstraints.gridx = xPoint;
            gridConstraints.gridy = yPoint;
            // fields.put(datamember[i].getName(), new JTextField(20));
            myPanel.put(i + 5, new JTextField(15));
            ((JPanel) myPanel.get(4)).add(((JTextField)myPanel.get(i+5)), gridConstraints);
            //gatewayXIpPanel.add(new JTextField(12), gridConstraints);
            ((JTextField)myPanel.get(i+5)).setText(x.get(i).toString());
            yPoint++;

            // fields.get(datamember[i].getName()).setText("12345");
        }
        //}

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        ((JPanel) myPanel.get(1)).add(((JPanel) myPanel.get(4)), gridConstraints);
        // gatewayXPanel.add(gatewayXIpPanel, gridConstraints);

        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.ipady = 10;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        mainGatewayPanel.add(((JPanel) myPanel.get(1)), gridConstraints);
        //mainGatewayPanel.add(gatewayXPanel, gridConstraints);

    }

    public void addGateWaysPanel() {

    }
}
