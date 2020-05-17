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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lenovo
 */
public class GateWayPanel extends JPanel{
    
    private JLabel headerLabel;
    private JTextField gatewayCountField;
    private GatewayIpPanel ipPanel;
     private GridBagConstraints gridConstraints;

    public JTextField getGatewayCountField() {
        return gatewayCountField;
    }

    public void setGatewayCountField(JTextField gatewayCountField) {
        this.gatewayCountField = gatewayCountField;
    }

    public JLabel getHeaderLabel() {
        return headerLabel;
    }

    public void setHeaderLabel(JLabel headerLabel) {
        this.headerLabel = headerLabel;
    }

    public GatewayIpPanel getIpPanel() {
        return ipPanel;
    }

    public void setIpPanel(GatewayIpPanel ipPanel) {
        this.ipPanel = ipPanel;
    }
    
    
    public GateWayPanel(GatewayDataClass data){
        this.setPreferredSize(new Dimension(180, 300));
        this.setBackground(Color.BLUE);
        TitledBorder title=BorderFactory.createTitledBorder("Ip List");
        this.setBorder(title);
        gridConstraints=new GridBagConstraints();
        
        
        headerLabel=new JLabel(data.getGatewayName().toUpperCase());
          gridConstraints.fill = GridBagConstraints.VERTICAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        this.add(headerLabel,gridConstraints);
        gatewayCountField=new JTextField(15);
         gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        this.add(gatewayCountField,gridConstraints);
        gatewayCountField.setText((data.getGatewayValues().size()+""));
        ipPanel=new GatewayIpPanel(data.getGatewayValues());
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        this.add(ipPanel,gridConstraints);
    }
    
}
