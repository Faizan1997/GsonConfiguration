/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configclass;

import java.awt.event.KeyEvent;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public interface StatusListener {
    
    public void getStatus(boolean status);
    public void getSource(KeyEvent event,boolean status);
    public Map getErrorMap();
}
