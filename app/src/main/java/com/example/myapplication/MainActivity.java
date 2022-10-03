package com.example.myapplication;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebViewAssetLoader;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends Fragment {

    TextView tvContent;
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_main, container,false);
        tvContent = view.findViewById(R.id.tvNewsContent);
        webView = view.findViewById(R.id.webView);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        // webView.loadUrl("https://google.com");
       /* String unencodedHtml =
                "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);
        webView.loadData(encodedHtml, "text/html", "base64");*/

        loadHtmlAssets();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadHtmlAssets() {
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(getContext()))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(getContext()))
                .build();
        webView.setWebViewClient(new LocalContentWebViewClient(assetLoader));
        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }

    public void setHeadline(@NotNull String headline) {
        tvContent.setText(headline);
    }
}
