package llc.taurusgroup.exampleapp.mvp.models.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuruRequest {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("params")
    @Expose
    private List<String> params = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
