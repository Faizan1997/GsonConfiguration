package dynamicconfigurstion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class CustomDesign extends JFrame {

    private JPanel headerPanel;
    private JLabel headingLabel;
    private JPanel mainPanel;
    private JPanel basicIpPanel;
    private GridBagConstraints gridConstraints;
    private JPanel mainGatewayPanel;

    private ReadData readData = new ReadData();
    private Map<String, JTextField> dataMembersMap = new HashMap<>();
    private Map<String, Method> methodMap = new HashMap<>();
    private Map<Integer, Map> gatewayMap = new HashMap<>();

    private int xPoint = 0;
    private int yPoint = 0;
    private Object object;
    private int gatewayCount = 0;
    private int listCount = 0;

    private Field[] dataFieldsrArray;
    private Method[] methodsArray;

    public CustomDesign() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(600, 50));
        headingLabel = new JLabel("CONFIGURATION", JLabel.CENTER);

        headingLabel.setVerticalAlignment(JLabel.CENTER);
        headingLabel.setFont(new Font("Verdana", Font.PLAIN, 25));

        headingLabel.setForeground(new Color(255, 255, 255));
        // headingLabel.setBackground(new Color(100, 20, 70));
//        Border border = BorderFactory.createLineBorder(Color.ORANGE);
//        headingLabel.setBorder(border);

        headerPanel.add(headingLabel, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 500));

        basicIpPanel = new JPanel(new GridBagLayout());
        basicIpPanel.setBackground(Color.BLUE);
        gridConstraints = new GridBagConstraints();

        basicIpPanel.setPreferredSize(new Dimension(600, 100));
        TitledBorder title = BorderFactory.createTitledBorder("Application Ip Configuration");
        basicIpPanel.setBorder(title);

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

                object = (methodMap.get("get" + dataFieldsrArray[i].getName().toLowerCase())).invoke(data, null);

            } catch (Exception e) {

            }
            //JPanel basicIpPanel=new JPanel(new GridLayout(1, 4));
            if (dataFieldsrArray[i].getType() == String.class && (object != null)) {
                if (xPoint == 0) {
                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                }
                //serverIpLabel = new JLabel(datamember[i].getName());
                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
                dataMembersMap.put(dataFieldsrArray[i].getName(), new JTextField(12));
                basicIpPanel.add(new JLabel(dataFieldsrArray[i].getName().toUpperCase()+"    "), gridConstraints);
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
                dataMembersMap.get(dataFieldsrArray[i].getName()).setText(object.toString());
                // fields.get(datamember[i].getName()).setText("12345");
            } else if (dataFieldsrArray[i].getType() == List.class && (object != null)) {

                gatewayCount++;
                gatewayMap.put(gatewayCount, new HashMap<Integer, Object>());

                drawGatewayPanel((List) object, dataFieldsrArray[i].getName(), gatewayMap.get(gatewayCount), gatewayCount);

            }

        }

//                gridConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridConstraints.ipady = 10;
//        gridConstraints.gridx = 0;
//        gridConstraints.gridy = 1;
//         mainGatewayPanel.add(new GateWayPanel((List) object, "gateway"));
        mainPanel.add(basicIpPanel, BorderLayout.NORTH);
        mainPanel.add(mainGatewayPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        this.pack();

    }

    public void countofList() {
        for (int i = 0; i < dataFieldsrArray.length; i++) {
            if (dataFieldsrArray[i].getType() == List.class) {
            }
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
        ((JTextField) myPanel.get(3)).setText(x.size() + "");
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
            ((JPanel) myPanel.get(4)).add(((JTextField) myPanel.get(i + 5)), gridConstraints);
            //gatewayXIpPanel.add(new JTextField(12), gridConstraints);
            ((JTextField) myPanel.get(i + 5)).setText(x.get(i).toString());
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
        mainGatewayPanel.add(new GateWayPanel(x, name), gridConstraints);
        // mainGatewayPanel.add(((JPanel) myPanel.get(1)), gridConstraints);
        //mainGatewayPanel.add(gatewayXPanel, gridConstraints);

    }

    public void addGateWaysPanel() {

    }
}
