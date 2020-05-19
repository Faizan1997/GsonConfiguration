/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import javax.naming.spi.DirStateFactory;

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
            System.err.println(e.getMessage());
        }

        return result;

    }
}
