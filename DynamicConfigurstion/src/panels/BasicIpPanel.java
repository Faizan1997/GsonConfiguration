package panels;

import dataclasses.BasicIPModel;
import dynamicconfigurstion.CustomDesign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BasicIpPanel extends JPanel implements ActionListener {

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

    public BasicIpPanel() {
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
        ip.setActionCommand(data.getFieldName());
        ip.addActionListener(this);
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
    public void actionPerformed(ActionEvent e) {

        if (CustomDesign.ipValidate(((JTextField) e.getSource()).getText())) {
            ipMap.remove(e.getActionCommand().toLowerCase());
            ipMap.put(e.getActionCommand().toLowerCase(), ((JTextField) e.getSource()).getText());
            System.err.println(ipMap.get(e.getActionCommand().toLowerCase()));
        }else{
             JOptionPane.showMessageDialog(this, "Invalid IP!");
        }
    }
}
