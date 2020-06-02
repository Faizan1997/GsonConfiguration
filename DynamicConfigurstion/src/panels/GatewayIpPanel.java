/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import configclass.StatusListener;
import dynamicconfigurstion.CustomDesign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class GatewayIpPanel extends JPanel implements KeyListener {

    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    private GridBagConstraints gridConstraints;
    private JPanel panel;
    private List tempList = new ArrayList();
    private List<String> ipList;
    private Map ipMap = new HashMap();
    private Map errorIpMap = new HashMap();

    private StatusListener listener;
    private int count = 0;

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }

    public GatewayIpPanel(List IpList, StatusListener listener) {

        this.count = IpList.size();
        this.listener = listener;
        panel = new JPanel();
        this.ipList = IpList;
        gridConstraints = new GridBagConstraints();
        this.setPreferredSize(new Dimension(180, 100));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.setBackground(Color.red);
        panel.setBackground(Color.red);
        panel.setLayout(new GridBagLayout());
        this.setIpMap(IpList);
        this.showFields(-1);
        this.add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JComponent ipField = (JTextField) e.getSource();
        if (isNotSpecialkey(e.getKeyCode())) {
            if (!ipField.getName().equals("")) {
                if ((CustomDesign.ipValidate(((JTextField) e.getSource()).getText())) && (!ipList.contains(((JTextField) e.getSource()).getText()))) {
                    errorIpMap.put(e.getSource(), true);
                    listener.getSource(e, true);
//                    if (Integer.parseInt(ipField.getName()) >= ipList.size()) {
//                        ipMap.put(Integer.parseInt(ipField.getName()), ((JTextField) e.getSource()).getText());
//                    } else {
//                        ipMap.put(Integer.parseInt(ipField.getName()), ((JTextField) e.getSource()).getText());
//                    }
                    ipMap.put(Integer.parseInt(ipField.getName()), ((JTextField) e.getSource()).getText());
                    ipField.setBackground(Color.GREEN);
                    ipList = new ArrayList<>(ipMap.values());
                    if (errorIpMap.containsValue(false)) {
                        chkFields();
                    }
                    if (!listener.getErrorMap().containsValue(false)) {
                        listener.getStatus(true);
                    }

                } else {
                    errorIpMap.put(e.getSource(), false);
                    listener.getStatus(false);
                    listener.getSource(e, false);
                    try {
                        ipMap.remove(Integer.parseInt(ipField.getName()));
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    ipField.setBackground(Color.red);

                    ipList = new ArrayList<>(ipMap.values());
                    if (errorIpMap.containsValue(false)) {
                        chkFields();
                    }
                }
            } else {
                try {
                    int key = e.getKeyCode();

                    if ((((key > 47 && key < 58) || (key > 95 && key < 106)) && (isValidNumber(e.getKeyChar()))) || (key == 8)) {

                        int a = Integer.parseInt(((JTextField) e.getSource()).getText().trim());
                        this.count = a;
                        this.addFields(a);
                        panel.revalidate();
                        panel.repaint();
                    } else {
                        ((JTextField) e.getSource()).setText(count + "");
                        ////((JTextField) e.getSource()).setText(((JTextField) e.getSource()).getText().substring(0, ((JTextField) e.getSource()).getText().length() - 1));
                        JOptionPane.showMessageDialog(this, "Please Enter Only Number");
                        e.consume();
                    }
                } catch (Exception ex) {

                }
            }
        }

    }

    public void addFields(int count) {
        panel.removeAll();
        if (ipList.size() < count) {

            this.showFields(-1);

            for (int i = 0; i < count - ipList.size(); i++) {

                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
                ip = new JTextField(12);
                ip.addKeyListener(this);
                ip.setActionCommand((ipList.size() + i) + "");
                ip.setName((ipList.size() + i) + "");

                panel.add(ip, gridConstraints);
                yPoint++;

            }
        } else {
            this.showFields(count);
            // this.ipList=new ArrayList<>(ipMap.values());
            this.ipList = tempList;
        }
    }

    public void showFields(int count) {
        int iterater;
        if (count == -1) {
            iterater = ipList.size();
        } else {
            iterater = count;
        }

        tempList = new ArrayList();
        ipMap.clear();
        panel.removeAll();

        xPoint = 0;
        yPoint = 0;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        for (int i = 0; i < iterater; i++) {

            gridConstraints.gridx = xPoint;
            gridConstraints.gridy = yPoint;
            ip = new JTextField(12);
            //ip.addActionListener(this);
            ip.addKeyListener(this);
            ip.setActionCommand(i + "");
            ip.setName(i + "");
            try {
                panel.add(ip, gridConstraints);
                // System.err.println(ipList);
                //this.add(ip, gridConstraints);
                //System.err.println(ipList.get(i));
                tempList.add(i, ipList.get(i));
                ipMap.put(i, ipList.get(i));
                ip.setText(ipList.get(i));
                yPoint++;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    public boolean isNotSpecialkey(int key) {
//|| (key == 127)
        return (((key == 32) || (key == 8) || (key > 43 && key < 112) || (key > 149 && key < 154) || (key > 159 && key < 224)));

    }

    public boolean isValidNumber(char number) {
        try {
            int num = Integer.parseInt((number + "").trim());
            List temp = new ArrayList(errorIpMap.keySet());

            for (int i = 0; i < temp.size(); i++) {
                listener.getErrorMap().remove(((JTextField) temp.get(i)));
                errorIpMap.remove(((JTextField) temp.get(i)));
            }
            if (listener.getErrorMap().containsValue(false)) {
                listener.getStatus(false);
            } else {
                listener.getStatus(true);
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void setIpMap(List IpList) {
        for (int i = 0; i < IpList.size(); i++) {
            ipMap.put(i, IpList.get(i));
        }
    }

    public void chkFields() {
        List temp = new ArrayList(errorIpMap.keySet());
        List currentIpList = new ArrayList();
        currentIpList.addAll(ipList);
        for (int i = 0; i < temp.size(); i++) {
            try {
                if ((CustomDesign.ipValidate(((JTextField) (temp.get(i))).getText())) && (!currentIpList.contains(((JTextField) (temp.get(i))).getText()))) {
                    ((JTextField) (temp.get(i))).setBackground(Color.GREEN);
                    listener.getErrorMap().put(temp.get(i), true);
                    errorIpMap.put(temp.get(i), true);
                    ipMap.put(Integer.parseInt(((JTextField) (temp.get(i))).getName().trim()), ((JTextField) (temp.get(i))).getText());
                    ipList = new ArrayList<>(ipMap.values());
                    currentIpList = new ArrayList(ipMap.values());
                    if (!listener.getErrorMap().containsValue(false)) {
                        listener.getStatus(true);
                    }

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
