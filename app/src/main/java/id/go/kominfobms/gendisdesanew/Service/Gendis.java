package id.go.kominfobms.gendisdesanew.Service;

import id.go.kominfobms.gendisdesanew.Model.BannerModel;
import id.go.kominfobms.gendisdesanew.Model.DifabelModel;
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
