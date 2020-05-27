/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import dynamicconfigurstion.CustomDesign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
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
    private int errorCount;

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }

    public GatewayIpPanel(List IpList) {
        panel = new JPanel();
        this.ipList = IpList;
        gridConstraints = new GridBagConstraints();
        this.setPreferredSize(new Dimension(180, 100));
        //gatewayXIpPanel.setPreferredSize(new Dimension(180, 300));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        this.setBackground(Color.red);
        panel.setBackground(Color.red);
        //panel.setPreferredSize(new Dimension(180, 350));

        panel.setLayout(new GridBagLayout());
        //gatewayXIpPanel.setBackground(Color.red);
        //this.add(panel, "Center");
        this.showFields(0);

        this.add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JComponent ipField = (JTextField) e.getSource();
        if (ipField.getName().equals("")) {

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {

        JComponent ipField = (JTextField) e.getSource();
        
        if (!ipField.getName().equals("")) {
            if ((CustomDesign.ipValidate(((JTextField) e.getSource()).getText())) && (!ipList.contains(((JTextField) e.getSource()).getText()))) {
                //ipList.remove(Integer.parseInt(ipField.getName().trim()));
                CustomDesign.getInstance().getErrorMap().put(e.getSource(), true);
                if (Integer.parseInt(ipField.getName()) >= ipList.size()) {
                    ipList.add(Integer.parseInt(ipField.getName()), ((JTextField) e.getSource()).getText());
                } else {
                    ipList.set(Integer.parseInt(ipField.getName()), ((JTextField) e.getSource()).getText());
                }
                System.err.println(((JTextField) e.getSource()).getText());
                ipField.setBackground(Color.GREEN);
                if (!CustomDesign.getInstance().getErrorMap().containsValue(false)) {
                    CustomDesign.getInstance().getSaveConfiguration().setEnabled(true);
                    CustomDesign.getInstance().getSaveConfiguration().setText("Save Configuration");
                }
            } else {
                CustomDesign.getInstance().getErrorMap().put(e.getSource(), false);
                
                ipList.remove(Integer.parseInt(ipField.getName()));
                ipField.setBackground(Color.red);
                CustomDesign.getInstance().getSaveConfiguration().setEnabled(false);
                CustomDesign.getInstance().getSaveConfiguration().setText("Please Correct Your IP");
                //JOptionPane.showMessageDialog(this, "Invalid IP!");
            }
        } else {
            try {
                int a = Integer.parseInt(((JTextField) e.getSource()).getText());
                this.addFields(a);
                panel.revalidate();
                panel.repaint();
            } catch (Exception ex) {

            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addFields(int count) {
        panel.removeAll();
        if (ipList.size() < count) {

            this.showFields(0);

            for (int i = 0; i < count - ipList.size(); i++) {

                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
                ip = new JTextField(12);
                //ip.addActionListener(this);
                ip.addKeyListener(this);
                ip.setActionCommand((ipList.size() + i) + "");
                ip.setName((ipList.size() + i) + "");

                panel.add(ip, gridConstraints);
                //this.add(ip, gridConstraints);
                //ip.setText(ipList.get(i));
                yPoint++;

            }
        } else {
            this.showFields(count);
            this.ipList = tempList;
        }
    }

    public void showFields(int count) {
        int iterater = 0;
        if (count == 0) {
            iterater = ipList.size();
        } else {
            iterater = count;
        }

        tempList.removeAll(tempList);
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

            panel.add(ip, gridConstraints);
            //this.add(ip, gridConstraints);
            tempList.add(i, ipList.get(i));
            ip.setText(ipList.get(i));
            yPoint++;

        }
    }

}
