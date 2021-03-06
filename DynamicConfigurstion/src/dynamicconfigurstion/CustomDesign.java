package dynamicconfigurstion;

import configclass.ReadData;
import configclass.Custom;
import configclass.StatusListener;
import configclass.WriteData;
import panels.BasicIpPanel;
import panels.GatewayPanel;
import dataclasses.BasicIPModel;
import dataclasses.GatewayModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CustomDesign extends JFrame implements ActionListener, StatusListener {

    private JPanel headerPanel;
    private JLabel headingLabel;
    private JPanel mainPanel;
    private JPanel basicIpPanel;
    private GridBagConstraints gridConstraints;
    private JPanel mainGatewayPanel;
    private ReadData readData = new ReadData();
    private WriteData writeData = new WriteData();
    private Map<String, Method> methodMap = new HashMap<>();
    private Object object;
    private int listCount = 0;
    private Field[] dataFieldsrArray;
    private Method[] methodsArray;
    private BasicIpPanel ipPanel;
    private BasicIPModel ipData;
    private GatewayModel gatewayData;
    private Custom data;
    private Dimension dimension;
    private Map<String, GatewayPanel> gatewayMap;
    private GatewayPanel gateway;
    private JButton saveConfiguration;
    private JPanel buttonPanel;
    private Object[] keyArray;
    private Map basicIpMap;
    // private static CustomDesign frame = new CustomDesign();
    private Map errorMap = new HashMap();
    private Map fieldMap = new HashMap();
    private List gatewaysList = new ArrayList();
    private List<String> tempList = new ArrayList();
    private Map gatewayIpMap = new HashMap();
    private Map IpPanelErrorMap = new HashMap();

    public void setErrorMap(Map errorMap) {
        this.errorMap = errorMap;
    }

    public CustomDesign() {

        this.setLayout(new BorderLayout());
        dimension = new Dimension(600, 370);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Config");
        this.setAlwaysOnTop(true);
        saveConfiguration = new JButton();
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLUE);

        saveConfiguration.addActionListener(this);
        saveConfiguration.setBackground(Color.PINK);
        saveConfiguration.setText("Save Configuration");
        ipPanel = new BasicIpPanel(this);
        gatewayMap = new HashMap<>();
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
        basicIpPanel.setPreferredSize(new Dimension(600, 80));
        TitledBorder title = BorderFactory.createTitledBorder("Application Ip Configuration");
        basicIpPanel.setBorder(title);
        try {
            data = readData.readJsonData("custom.json");
            if (data!=null) {
               // data = readData.readJsonData("custom.json");
                dataFieldsrArray = data.getClass().getDeclaredFields();
            }else{
                JOptionPane.showMessageDialog(this, "Found Some Errors In Your Configuration File! Please Check");
                System.exit(0);
            }

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
                if(ipValidate(object.toString().trim())){
                ipData = new BasicIPModel();
                ipData.setFieldName(dataFieldsrArray[i].getName());
                ipData.setFieldValue(object.toString());

                ipPanel.addNewIpValue(ipData);
                }else{
                    JOptionPane.showMessageDialog(this, "Configuration File Contains Duplicate Ips Or Invalid Ips!");
                    System.exit(0);
                }
            } else if (dataFieldsrArray[i].getType() == List.class && (object != null)) {

                if (this.setList((List) object)) {
                    gatewayData = new GatewayModel();
                    gatewayData.setGatewayName(dataFieldsrArray[i].getName());
                    gatewayData.setGatewayValues((List) object);

                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                    gridConstraints.ipady = 10;
                    gridConstraints.gridx = 0;
                    gridConstraints.gridy = 1;
                    gateway = new GatewayPanel(gatewayData, this);
                    gatewayMap.put(gatewayData.getGatewayName().toLowerCase(), gateway);

                    mainGatewayPanel.add(gateway, gridConstraints);
                } else {
                    JOptionPane.showMessageDialog(this, "Configuration File Contains Duplicate Ips Or Invalid Ips!");
                    System.exit(0);
                }
            }

        }

        mainPanel.add(ipPanel, BorderLayout.NORTH);
        //mainPanel.add(basicIpPanel, BorderLayout.NORTH);
        mainPanel.add(mainGatewayPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
        buttonPanel.add(saveConfiguration);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();

    }

//    public static CustomDesign getInstance() {
//        if (frame != null) {
//            return frame;
//        } else {
//            frame = new CustomDesign();
//            return frame;
//        }
//    }
    public JButton getSaveConfiguration() {
        return saveConfiguration;
    }

    public void setSaveConfiguration(JButton saveConfiguration) {
        this.saveConfiguration = saveConfiguration;
    }

    public void countofList() {
        for (int i = 0; i < dataFieldsrArray.length; i++) {
            if (dataFieldsrArray[i].getType() == List.class) {
            }
            listCount++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        basicIpMap = ipPanel.getIpMap();
        data = new Custom();

        keyArray = basicIpMap.keySet().toArray();
        for (int i = 0; i < basicIpMap.size(); i++) {
            try {
                methodMap.get("set" + keyArray[i].toString().toLowerCase()).invoke(data, basicIpMap.get(keyArray[i].toString()));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.err.println("Something wrong");
            }
        }

        keyArray = gatewayMap.keySet().toArray();
        for (int i = 0; i < gatewayMap.size(); i++) {
            try {
                //System.err.println("Before Submitting"+((GatewayPanel) gatewayMap.get(keyArray[i].toString())).getIpPanel().getIpList());
                methodMap.get("set" + keyArray[i].toString().toLowerCase()).invoke(data, ((GatewayPanel) gatewayMap.get(keyArray[i].toString())).getIpPanel().getIpList());;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.err.println("Something wrong");
            }
        }

        int result = writeData.writeJsonData("custom.json", data);
        if (result == 0) {
            JOptionPane.showMessageDialog(this, "Configuration File Not been Updated");
        } else if (result == 1) {
            JOptionPane.showMessageDialog(this, "Configuration File Has been Successfully Updated");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean ipValidate(String ip) {

        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        Pattern pattern = Pattern.compile(regex);

        if (ip == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    @Override
    public void getStatus(boolean status) {
        saveConfiguration.setEnabled(status);
        if (status) {
            saveConfiguration.setText("Save Configuration");
        } else {
            saveConfiguration.setText("Please Correct Your IP");
        }

    }

    @Override
    public void getSource(KeyEvent e, boolean status) {
        errorMap.put(e.getSource(), status);
    }

    @Override
    public Map getErrorMap() {
        return errorMap;
    }

    @Override
    public Map getIpMap() {
        return gatewayIpMap;

    }

    private boolean setList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            tempList.add(list.get(i));
        }
        return checkDuplication();
    }

    private boolean checkDuplication() {
        Map<String, Integer> hm = new HashMap<String, Integer>();
        List temp = new ArrayList();
        boolean ipRegex = false;
        boolean count = false;
        for (String i : tempList) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
            if (ipValidate(i)) {
                ipRegex = true;
            } else {
                ipRegex = false;
                break;
            }
        }
        temp = new ArrayList(hm.values());

        for (int i = 0; i < temp.size(); i++) {
            if (((int) temp.get(i)) > 1) {
                count = false;
                break;
            } else {
                count = true;
            }
        }

        if (count && ipRegex) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map getFeildMap() {
        return fieldMap;
    }

    @Override
    public Map getIpPanelErrorMap() {
        return IpPanelErrorMap;
    }

}
