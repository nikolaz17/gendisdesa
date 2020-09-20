package id.go.kominfobms.gendisdesanew.Model;

import com.google.gson.annotations.SerializedName;

public class DifabelModel {

    /**
     * status : 1
     * message : Data Ditemukan
     * total : 2
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("total")
    private int total;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
