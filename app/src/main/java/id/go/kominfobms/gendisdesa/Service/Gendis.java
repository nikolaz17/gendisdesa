package id.go.kominfobms.gendisdesa.Service;

import id.go.kominfobms.gendisdesa.Model.BannerModel;
import id.go.kominfobms.gendisdesa.Model.DifabelModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Gendis {
    @GET("api")
    Call<BannerModel> getBanner();

    @GET("difabelverifikasi")
    Call<DifabelModel> getDifabelVerifikasi();

    @GET("difabelterdaftar")
    Call<DifabelModel> getDifabelTerdaftar();
}
