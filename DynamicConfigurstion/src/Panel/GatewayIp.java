/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class GatewayIp extends JPanel {

    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    private GridBagConstraints gridConstraints;
    private JPanel panel;

    public GatewayIp(List IpList) {
        panel=new JPanel();
        gridConstraints = new GridBagConstraints();
       this.setPreferredSize(new Dimension(180, 370));
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
        xPoint = 0;
        yPoint = 0;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        for (int i = 0; i < IpList.size(); i++) {

            gridConstraints.gridx = xPoint;
            gridConstraints.gridy = yPoint;
            ip = new JTextField(12);
            panel.add(ip,gridConstraints);
            //this.add(ip, gridConstraints);
            ip.setText(IpList.get(i).toString());
            yPoint++;

        }
        
        this.add(new JScrollPane(panel), BorderLayout.CENTER);
    }
    
  

}
