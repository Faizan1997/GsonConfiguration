package panels;

import configclass.StatusListener;
import dataclasses.BasicIPModel;
import dynamicconfigurstion.CustomDesign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BasicIpPanel extends JPanel implements KeyListener {

    private StatusListener listener;
    private GridBagConstraints gridConstraints;
    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    private JScrollPane scroll;
    private JPanel panel;
    private Map<String, String> ipMap;

    public Map<String, String> getIpMap() {
        return ipMap;
    }

    public void setIpMap(Map<String, String> ipMap) {
        this.ipMap = ipMap;
    }

    public BasicIpPanel(StatusListener listener) {
        this.listener = listener;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 80));
        this.setBackground(Color.BLUE);
        TitledBorder title = BorderFactory.createTitledBorder("Application Ip Configuration");
        this.setBorder(title);
        gridConstraints = new GridBagConstraints();
        panel = new JPanel(new FlowLayout());
        ipMap = new HashMap<>();
        // panel.setPreferredSize(new Dimension(600, 50));
        panel.setBackground(Color.BLUE);
        scroll = new JScrollPane(panel);
        scroll.setViewportView(panel);
        this.add(scroll, BorderLayout.CENTER);

    }

    public void addNewIpValue(BasicIPModel data) {
        if (xPoint == 0) {
            gridConstraints.fill = GridBagConstraints.BOTH;
        }
        //serverIpLabel = new JLabel(datamember[i].getName());
        gridConstraints.gridx = xPoint;
        gridConstraints.gridy = yPoint;
        // dataMembersMap.put(dataFieldsrArray[i].getName(), new JTextField(12));
        panel.add(new JLabel(data.getFieldName().toUpperCase() + "    "), gridConstraints);
        xPoint++;
        gridConstraints.gridx = xPoint;
        gridConstraints.gridy = yPoint;
        ip = new JTextField(17);
        ip.setName(data.getFieldName());
        ip.setActionCommand(data.getFieldName());
        //ip.addActionListener(this);
        //ip.addFocusListener(this);
        ip.addKeyListener(this);
        ipMap.put(data.getFieldName().toLowerCase(), data.getFieldValue());
        panel.add(ip, gridConstraints);
        ip.setText(data.getFieldValue());
        // if (xPoint == 3) {
        xPoint = 0;
        yPoint = yPoint + 10;
        // } else {
        //   xPoint++;
        //}
        // dataMembersMap.get(dataFieldsrArray[i].getName()).setText(object.toString());
    }

    @Override
    public void keyTyped(KeyEvent e) {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        evaluate(e);
    }

    public void evaluate(KeyEvent e) {
        JComponent ipField = (JTextField) e.getSource();

        if (CustomDesign.ipValidate(((JTextField) e.getSource()).getText().trim()) && !((JTextField) e.getSource()).getText().trim().equals("")) {
            listener.getSource(e, true);
            //CustomDesign.getInstance().getErrorMap().put(e.getSource(), true);
            //ipMap.remove(ipField.getName().toLowerCase());
            ipMap.put(ipField.getName().toLowerCase(), ((JTextField) e.getSource()).getText().trim());
            System.err.println(ipMap.get(ipField.getName().toLowerCase()));
            ipField.setBackground(Color.green);
            if (!listener.getErrorMap().containsValue(false)) {
                listener.getStatus(true);
                // CustomDesign.getInstance().getSaveConfiguration().setEnabled(true);
                //CustomDesign.getInstance().getSaveConfiguration().setText("Save Configuration");
            }

        } else if (((JTextField) e.getSource()).getText().trim().equals("")) {
            listener.getSource(e, true);
            ipField.setBackground(Color.white);
            ipMap.put(ipField.getName().toLowerCase(), ((JTextField) e.getSource()).getText().trim());
            
        } else {
            listener.getStatus(false);
            listener.getSource(e, false);
            // CustomDesign.getInstance().getErrorMap().put(e.getSource(), false);

            ipField.setBackground(Color.red);
            //CustomDesign.getInstance().getSaveConfiguration().setEnabled(false);
            //CustomDesign.getInstance().getSaveConfiguration().setText("Please Correct Your IP");
            //JOptionPane.showMessageDialog(this, "Invalid IP!");
        }

    }
}
