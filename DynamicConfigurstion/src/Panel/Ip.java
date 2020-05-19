
package Panel;

import DataClass.BasicIP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Ip extends JPanel{
    
    private GridBagConstraints gridConstraints;
    private int xPoint = 0;
    private int yPoint = 0;
    private JTextField ip;
    private JScrollPane scroll;
    private JPanel panel;
    public Ip(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 80));
        this.setBackground(Color.BLUE);
        TitledBorder title = BorderFactory.createTitledBorder("Application Ip Configuration");
        this.setBorder(title);
        gridConstraints = new GridBagConstraints();
        panel=new JPanel(new GridBagLayout());
         //panel.setPreferredSize(new Dimension(600, 50));
        panel.setBackground(Color.BLUE);
        scroll=new JScrollPane(panel);
        scroll.setViewportView(panel);
        this.add(scroll, BorderLayout.CENTER);
       
        
        
    }
    
    public void addNewIpValue(BasicIP data){
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
                ip=new JTextField(15);
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
}
