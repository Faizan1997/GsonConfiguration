/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class ReadData {

    private Custom result = null;
    private Gson gson = new Gson();

    public Custom readJsonData(String fileName) {

        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(fileName));
            result = gson.fromJson(br, Custom.class);

            br.close();

        } catch (Exception e) {
            return null;
        }

        return result;

    }
}
