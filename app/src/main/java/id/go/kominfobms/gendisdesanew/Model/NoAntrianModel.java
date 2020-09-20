package id.go.kominfobms.gendisdesanew.Model;

import com.google.gson.annotations.SerializedName;

public class NoAntrianModel {

    /**
     * status : 1
     * message : Loket Antrian 1 Ditemukan
     * data : {"no_antrian":"A119"}
     * total : 51
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * no_antrian : A119
         */

        @SerializedName("no_antrian")
        private String noAntrian;

        public String getNoAntrian() {
            return noAntrian;
        }

        public void setNoAntrian(String noAntrian) {
            this.noAntrian = noAntrian;
        }
    }
}
