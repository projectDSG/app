package com.example.quackhacks;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;



public class GetQRCode extends AppCompatActivity {


    private WebView web;

    public static final String DEV_KEY = "WK4qvKCq4A5RHQv4s3wGVpDblATY9Sb_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_q_r_code);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        String inputData = intent.getStringExtra("fullString");
        String pasteURL = postToPaste(inputData);



        //Opens a in app browser version of resolver
        //this is the same page that would if someone went to resolvers product page on the computer
        web = (WebView) findViewById(R.id.webView);
        web.getSettings().setLoadsImagesAutomatically(true);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("https://api.qrserver.com/v1/create-qr-code/?data=" + pasteURL);//builds resolver url


    }


    public static String postToPaste(String infoToBePosted) {
        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin(DEV_KEY);

        final PasteBuilder pasteBuilder = factory.createPaste();

        pasteBuilder.setTitle("projectDSG");
        pasteBuilder.setRaw(infoToBePosted);
        pasteBuilder.setMachineFriendlyLanguage("text");
        pasteBuilder.setVisiblity(PasteVisiblity.Public);
        pasteBuilder.setExpire(PasteExpire.OneDay);

        final Paste paste = pasteBuilder.build();

        final Response<String> postResult = pastebin.post(paste);

        if (postResult.hasError()) {
            return postResult.getError();
        }

        return postResult.get();
    }


}