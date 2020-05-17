/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicconfigurstion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class GatewayIpPanel extends JPanel {

    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    private GridBagConstraints gridConstraints;

    public GatewayIpPanel(List IpList) {
        gridConstraints = new GridBagConstraints();
        this.setPreferredSize(new Dimension(180, 330));
        //gatewayXIpPanel.setPreferredSize(new Dimension(180, 300));

        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // gatewayXIpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        this.setBackground(Color.red);
        //gatewayXIpPanel.setBackground(Color.red);
        xPoint = 0;
        yPoint = 0;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        for (int i = 0; i < IpList.size(); i++) {

            gridConstraints.gridx = xPoint;
            gridConstraints.gridy = yPoint;
            ip=new JTextField(12);
            this.add(ip, gridConstraints);
            ip.setText(IpList.get(i).toString());
            yPoint++;

        }

    }

}
