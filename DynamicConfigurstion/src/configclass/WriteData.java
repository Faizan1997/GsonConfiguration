
package configclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    private Gson gson = new Gson();
    
    public int writeJsonData(String FileName,Custom data){
         gson = new GsonBuilder().setPrettyPrinting().create();
        String strJson = gson.toJson(data);
        FileWriter writer = null;
        try {
            writer = new FileWriter(FileName);
            writer.write(strJson);
        } catch (IOException e) {
            return 0;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
        return 1;
    }
}
