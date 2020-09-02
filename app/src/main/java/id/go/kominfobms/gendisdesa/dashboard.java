package id.go.kominfobms.gendisdesa;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;

public class dashboard extends AppCompatActivity {
    Context context = this;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.layoutDaftarAkun)
    LinearLayout layoutDaftarAkun;
    @BindView(R.id.cardDaftar)
    LinearLayout cardDaftar;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.layoutLupaPass)
    LinearLayout layoutLupaPass;
    @BindView(R.id.layoutInputKode)
    LinearLayout layoutInputKode;
    @BindView(R.id.layoutInputUlangKode)
    LinearLayout layoutInputUlangKode;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;
    @BindView(R.id.banner_slider)
    Slider bannerSlider;
    @BindView(R.id.layoutBanner)
    ConstraintLayout layoutBanner;
    @BindView(R.id.tvAntrian1)
    TextView tvAntrian1;
    @BindView(R.id.tvAntrian4)
    TextView tvAntrian4;
    @BindView(R.id.totalAntrian1)
    TextView totalAntrian1;
    @BindView(R.id.totalAntrian4)
    TextView totalAntrian4;
    @BindView(R.id.layoutKontak)
    LinearLayout layoutKontak;
    private Dialog dialogKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initComponent();
        getBanner();
        getLoket1();
        getLoket4();
        getKontak();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("TAG", "runing get data loket  ");
                getLoket1();
                getLoket4();
            }
        }, 0, 10000);
    }

    private void getKontak() {
        Log.e("TAG", "initSOP: OKE");
        dialogKontak = new Dialog(Objects.requireNonNull(context));
        dialogKontak.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        View view = getLayoutInflater().inflate(R.layout.dialog_image, null);
        dialogKontak.setContentView(view);
        Objects.requireNonNull(dialogKontak.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogKontak.setCancelable(true);

        ImageButton btnDialogClose = view.findViewById(R.id.btnDialogClose);
        ImageView imageDialog = view.findViewById(R.id.imageDialog);
        btnDialogClose.setOnClickListener(v -> dialogKontak.dismiss());

        Picasso.get()
                .load("http://simpkb.banyumaskab.go.id/themes/booking/asset/img/dinashub1.png")
                .placeholder(R.drawable.icon_login)
                .into(imageDialog);

        layoutKontak.setOnClickListener(v -> dialogKontak.show());
    }

    private void getLoket4() {
        Api.createService(context, Simpkb.class)
                .getLoket4()
                .enqueue(new Callback<NoAntrianModel>() {
                    @Override
                    public void onResponse(Call<NoAntrianModel> call, Response<NoAntrianModel> response) {
                        Log.e("TAG", "onResponse: " + new Gson().toJson(response.body()));
                        assert response.body() != null;
                        totalAntrian4.setText("Total : " + response.body().getTotal());
                        if (response.body().getStatus() == 1) {
                            tvAntrian4.setText(response.body().getData().getNoAntrian());
                        } else {
                            tvAntrian4.setText("-");
                        }
                    }

                    @Override
                    public void onFailure(Call<NoAntrianModel> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + new Gson().toJson(t.getMessage()));
//                        Toast.makeText(context, "Terjadi kesalahan saat mengambil no Antrian : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getLoket1() {
        Api.createService(context, Simpkb.class)
                .getLoket1()
                .enqueue(new Callback<NoAntrianModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<NoAntrianModel> call, Response<NoAntrianModel> response) {
                        Log.e("TAG", "onResponse: " + new Gson().toJson(response.body()));
                        assert response.body() != null;
                        totalAntrian1.setText("Total : " + response.body().getTotal());
                        if (response.body().getStatus() == 1) {
                            tvAntrian1.setText(response.body().getData().getNoAntrian());
                        } else {
                            tvAntrian1.setText("-");
                        }
                    }

                    @Override
                    public void onFailure(Call<NoAntrianModel> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + new Gson().toJson(t.getMessage()));
//                        Toast.makeText(context, "Terjadi kesalahan saat mengambil no Antrian : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getBanner() {
        Api.createService(context, Simpkb.class)
                .getBanner()
                .enqueue(new Callback<BannerModel>() {
                    @Override
                    public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                        assert response.body() != null;
                        if (response.body().getStatus() == 1) {
                            Slider.init(new PicassoImageLoadingService());
                            bannerSlider.setAdapter(new BannerAdapter(response.body()));
                            bannerSlider.setInterval(5000);
                            bannerSlider.setLoopSlides(true);
                        } else {
                            Toast.makeText(context, "Banner tidak ditemukan", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BannerModel> call, Throwable t) {
                        Toast.makeText(context, "Terjadi kesalahan saat mengambil banner : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void initComponent() {
//        cardDaftar.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ListTempatActivity.class);
//            intent.putExtra("title", context.getResources().getString(R.string.pasar_tradisional));
//            startActivity(intent);
//        });

        layoutDaftarAkun.setOnClickListener(v -> {
            Intent intent = new Intent(context, daftar_akun.class);
            startActivity(intent);
        });

        layoutLogin.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        layoutLupaPass.setOnClickListener(v -> {
            Intent intent = new Intent(context, lupa_password.class);
            startActivity(intent);
        });

        layoutInputKode.setOnClickListener(v -> {
            Intent intent = new Intent(context, input_kode.class);
            startActivity(intent);
        });

        layoutInputUlangKode.setOnClickListener(v -> {
            Intent intent = new Intent(context, input_ulang_kode.class);
            startActivity(intent);
        });

    }
}