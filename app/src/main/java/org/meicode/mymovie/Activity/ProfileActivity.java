package org.meicode.mymovie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.meicode.mymovie.Genres.GenresActivity;
import org.meicode.mymovie.LoadingDialogBar;
import org.meicode.mymovie.R;
import org.meicode.mymovie.User.DetailUserResponse;
import org.reactivestreams.FlowAdapters;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    TextView txtNameHeader;
    TextView txtEmailHeader;

    TextView txtHintEmail;
    TextView txtHintName;
    TextView txtHintMobile;
    TextView txtHintAddress;
    TextView txtHintDob;

    TextView txt_namePro;
    TextView txt_emailPro;

    Button btnBack;
    LoadingDialogBar loadingDialogBar = new LoadingDialogBar(this);

    public static Intent newIntentToProfileActivity(Context context){
        Intent intent = new Intent(context , ProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        imageView = findViewById(R.id.imgPro);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User" , "");
        DetailUserResponse detailUserResponse = gson.fromJson(json , DetailUserResponse.class);
        if ("".equals(detailUserResponse.getName())){
            Glide.with(this)
                    .load(detailUserResponse.getName())
                    .placeholder(R.drawable.thor)
                    .into(imageView);
        }

        txtHintEmail = findViewById(R.id.txtHintEmail);
        txtHintName = findViewById(R.id.txtHintName);
        txtHintMobile = findViewById(R.id.txtHintMobile);
        txtHintDob = findViewById(R.id.txtHintDOB);
        txtHintAddress = findViewById(R.id.txtHintAddress);

        txtHintEmail.setText(detailUserResponse.getUsername() + "@gmail.com");
        txtHintName.setText(detailUserResponse.getName());
        txtHintMobile.setText(String.valueOf(detailUserResponse.getId()));
        txtHintAddress.setText("");
        txtHintDob.setText("");

        txt_namePro = findViewById(R.id.txt_namePro);
        txt_emailPro = findViewById(R.id.txt_emailPro);

        txt_namePro.setText(detailUserResponse.getName());
        txt_emailPro.setText(detailUserResponse.getUsername() + "@gmail.com");



        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}