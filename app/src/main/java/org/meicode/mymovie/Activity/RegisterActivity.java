package org.meicode.mymovie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.meicode.mymovie.R;

public class RegisterActivity extends AppCompatActivity {
    public static Intent newIntentToRegisterActivity(Context context){
        Intent intent = new Intent(context , RegisterActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}