package com.example.quackhacks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;

public class MainActivity extends AppCompatActivity {

    EditText protestStrings;
    EditText petitionStrings;
    EditText charitiesStrings;
    EditText businessStrings;

    String protests;
    String petitions;
    String charities;
    String businesses;

    public static String fullString;

    Button saveBtn;
    Button qrCodeBtn;

    private String text;
    private String text2;
    private String text3;
    private String text4;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String TEXT4 = "text4";

    public static final String DEV_KEY = "WK4qvKCq4A5RHQv4s3wGVpDblATY9Sb_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        protestStrings = (EditText)findViewById(R.id.protestStrings);
        petitionStrings = (EditText)findViewById(R.id.petitionStrings);
        charitiesStrings = (EditText)findViewById(R.id.charityStrings);
        businessStrings = (EditText)findViewById(R.id.businessStrings);

//        final TextView urlCheck = findViewById(R.id.urlCheck);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protests = protestStrings.getText().toString();
                petitions = petitionStrings.getText().toString();
                charities = charitiesStrings.getText().toString();
                businesses = businessStrings.getText().toString();
                saveData();

            }
        });

//        protests = protestStrings.getText().toString();
//        petitions = petitionStrings.getText().toString();
//        charities = charitiesStrings.getText().toString();
//        businesses = businessStrings.getText().toString();

        fullString = "Protests\n" + protests + "\n\nPetitions\n" + petitions + "\n\nCharities\n" + charities + "\n\nLocal Business\n" + businesses;

        qrCodeBtn = findViewById(R.id.qrCode);
        qrCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetQRCode.class);
                intent.putExtra("fullString", fullString);
                startActivity(intent);
            }
        });

        loadData();
        updateView();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, protestStrings.getText().toString());
        editor.putString(TEXT2, petitionStrings.getText().toString());
        editor.putString(TEXT3, charitiesStrings.getText().toString());
        editor.putString(TEXT4, businessStrings.getText().toString());


        editor.apply();



        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show();
    }
    //Clears local data
    private  void clearData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, "");
        editor.putString(TEXT2, "");
        editor.putString(TEXT3, "");
        editor.putString(TEXT4, "");

        editor.apply();
    }

    //Default conditions for sharedPreferences
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        text2 = sharedPreferences.getString(TEXT2, "");
        text3 = sharedPreferences.getString(TEXT3, "");
        text4 = sharedPreferences.getString(TEXT4, "");
    }

    //sets both edit texts and check box to whatever is saved in shared preferences
    public void updateView(){
        protestStrings.setText(text);
        petitionStrings.setText(text2);
        charitiesStrings.setText(text3);
        businessStrings.setText(text4);
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
            System.out.println("ERROR!!");
            return postResult.getError();
        }

        System.out.println(postResult.get() + "********************************");
        return postResult.get();
    }
}