package id.go.kominfobms.gendisdesanew;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class input_kode extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.webViewInput)
    WebView webViewInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kode);
        ButterKnife.bind(this);

        WebView webView = (WebView) findViewById(R.id.webViewInput);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://gendisdesa.banyumaskab.go.id/cari_dataandroid");
    }
}