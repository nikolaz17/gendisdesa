package id.go.kominfobms.gendisdesa.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerModel {

    /**
     * status : 1
     * message : Banner ditemukan
     * data : ["http://simpkb.banyumaskab.go.id/themes/booking/assets/slider/img/banner2.png","http://simpkb.banyumaskab.go.id/themes/booking/assets/slider/img/banner1.png","http://simpkb.banyumaskab.go.id/themes/booking/assets/slider/img/banner.png"]
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

}
