
package dynamicconfigurstion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class IpPanel extends JPanel{
    
    private GridBagConstraints gridConstraints;
    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    public IpPanel(){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(Color.BLUE);
        TitledBorder title = BorderFactory.createTitledBorder("Application Ip Configuration");
        this.setBorder(title);
        gridConstraints = new GridBagConstraints();
        
        
        
        
    }
    
    public void addNewIpValue(BasicIPDataClass data){
         if (xPoint == 0) {
                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                }
                //serverIpLabel = new JLabel(datamember[i].getName());
                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
               // dataMembersMap.put(dataFieldsrArray[i].getName(), new JTextField(12));
                this.add(new JLabel(data.getFieldName().toUpperCase() + "    "), gridConstraints);
                xPoint++;
                gridConstraints.gridx = xPoint;
                gridConstraints.gridy = yPoint;
                ip=new JTextField(12);
                this.add(ip, gridConstraints);
                ip.setText(data.getFieldValue());
                if (xPoint == 3) {
                    xPoint = 0;
                    yPoint = yPoint + 10;
                } else {
                    xPoint++;
                }
               // dataMembersMap.get(dataFieldsrArray[i].getName()).setText(object.toString());
    }
}
