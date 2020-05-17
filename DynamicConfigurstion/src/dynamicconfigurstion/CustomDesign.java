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
import javax.swing.JOptionPane;
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
    private Map<String, Method> methodMap = new HashMap<>();
    private Object object;
    private int listCount = 0;
    private Field[] dataFieldsrArray;
    private Method[] methodsArray;
    private IpPanel ipPanel;
    private BasicIPDataClass ipData;
    private GatewayDataClass gatewayData;
    private Custom data;
    
    public CustomDesign() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");
        ipPanel = new IpPanel();
        
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(600, 50));
        headingLabel = new JLabel("CONFIGURATION", JLabel.CENTER);
        headingLabel.setVerticalAlignment(JLabel.CENTER);
        headingLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        headingLabel.setForeground(new Color(255, 255, 255));
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
        try {
            data = readData.readJsonData("custom.json");
            dataFieldsrArray = data.getClass().getDeclaredFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "File Not Found! Please Check Your Configuration File");
            System.exit(0);
        }
        
        Class obj = Custom.class;
        methodsArray = obj.getMethods();
        countofList();
        try {
            
            for (Method methodsArray1 : methodsArray) {
                methodMap.put(methodsArray1.getName().toLowerCase(), methodsArray1);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Something Went Wrong in getting Methods From GSON File");
        }
        
        mainGatewayPanel = new JPanel(new GridLayout(1, listCount));
        // mainGatewayPanel.setPreferredSize(new Dimension(600, 400));
        mainGatewayPanel.setBackground(Color.PINK);
        
        for (int i = 0; i < dataFieldsrArray.length; i++) {
            try {
                
                object = (methodMap.get("get" + dataFieldsrArray[i].getName().toLowerCase())).invoke(data, null);
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Something Went Wrong in invoking Method From GSON File");
            }
            //JPanel basicIpPanel=new JPanel(new GridLayout(1, 4));
            if (dataFieldsrArray[i].getType() == String.class && (object != null)) {
                
                ipData = new BasicIPDataClass();
                ipData.setFieldName(dataFieldsrArray[i].getName());
                ipData.setFieldValue(object.toString());
                
                ipPanel.addNewIpValue(ipData);
                
            } else if (dataFieldsrArray[i].getType() == List.class && (object != null)) {
                
                gatewayData = new GatewayDataClass();
                gatewayData.setGatewayName(dataFieldsrArray[i].getName());
                gatewayData.setGatewayValues((List) object);
                
                gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                gridConstraints.ipady = 10;
                gridConstraints.gridx = 0;
                gridConstraints.gridy = 1;
                mainGatewayPanel.add(new GateWayPanel(gatewayData), gridConstraints);
                
            }
            
        }
        mainPanel.add(ipPanel, BorderLayout.NORTH);
        //mainPanel.add(basicIpPanel, BorderLayout.NORTH);
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
    
}
