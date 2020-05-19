
package ConfigClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Custom {

    @SerializedName("databaseIp")
    @Expose
    private String databaseIp;
    @SerializedName("ServerIp")
    @Expose
    private String serverIp;
    @SerializedName("SMUIp")
    @Expose
    private String sMUIp;
    @SerializedName("gatewayX")
    @Expose
    private List<String> gatewayX = null;
    @SerializedName("gatewayY")
    @Expose
    private List<String> gatewayY = null;
    @SerializedName("gatewayZ")
    @Expose
    private List<String> gatewayZ = null;

    public String getDatabaseIp() {
        return databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getSMUIp() {
        return sMUIp;
    }

    public void setSMUIp(String sMUIp) {
        this.sMUIp = sMUIp;
    }

    public List<String> getGatewayX() {
        return gatewayX;
    }

    public void setGatewayX(List<String> gatewayX) {
        this.gatewayX = gatewayX;
    }

    public List<String> getGatewayY() {
        return gatewayY;
    }

    public void setGatewayY(List<String> gatewayY) {
        this.gatewayY = gatewayY;
    }

    public List<String> getGatewayZ() {
        return gatewayZ;
    }

    public void setGatewayZ(List<String> gatewayZ) {
        this.gatewayZ = gatewayZ;
    }

}
