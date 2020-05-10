
package dynamicconfigurstion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "databaseIp",
    "ServerIp",
    "SMUIp",
    "gatewayX",
    "gatewayY",
    "gatewayZ"
})
public class Custom {

    @JsonProperty("databaseIp")
    private String databaseIp;
    @JsonProperty("ServerIp")
    private String serverIp;
    @JsonProperty("SMUIp")
    private String sMUIp;
    @JsonProperty("gatewayX")
    private List<String> gatewayX = new ArrayList<String>();
    @JsonProperty("gatewayY")
    private List<String> gatewayY = new ArrayList<String>();
    @JsonProperty("gatewayZ")
    private List<String> gatewayZ = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("databaseIp")
    public String getDatabaseIp() {
        return databaseIp;
    }

    @JsonProperty("databaseIp")
    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
    }

    public Custom withDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
        return this;
    }

    @JsonProperty("ServerIp")
    public String getServerIp() {
        return serverIp;
    }

    @JsonProperty("ServerIp")
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Custom withServerIp(String serverIp) {
        this.serverIp = serverIp;
        return this;
    }

    @JsonProperty("SMUIp")
    public String getSMUIp() {
        return sMUIp;
    }

    @JsonProperty("SMUIp")
    public void setSMUIp(String sMUIp) {
        this.sMUIp = sMUIp;
    }

    public Custom withSMUIp(String sMUIp) {
        this.sMUIp = sMUIp;
        return this;
    }

    @JsonProperty("gatewayX")
    public List<String> getGatewayX() {
        return gatewayX;
    }

    @JsonProperty("gatewayX")
    public void setGatewayX(List<String> gatewayX) {
        this.gatewayX = gatewayX;
    }

    public Custom withGatewayX(List<String> gatewayX) {
        this.gatewayX = gatewayX;
        return this;
    }

    @JsonProperty("gatewayY")
    public List<String> getGatewayY() {
        return gatewayY;
    }

    @JsonProperty("gatewayY")
    public void setGatewayY(List<String> gatewayY) {
        this.gatewayY = gatewayY;
    }

    public Custom withGatewayY(List<String> gatewayY) {
        this.gatewayY = gatewayY;
        return this;
    }

    @JsonProperty("gatewayZ")
    public List<String> getGatewayZ() {
        return gatewayZ;
    }

    @JsonProperty("gatewayZ")
    public void setGatewayZ(List<String> gatewayZ) {
        this.gatewayZ = gatewayZ;
    }

    public Custom withGatewayZ(List<String> gatewayZ) {
        this.gatewayZ = gatewayZ;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
