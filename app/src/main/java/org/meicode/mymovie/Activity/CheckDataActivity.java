package org.meicode.mymovie.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.meicode.mymovie.Activity.LoginActivity;
import org.meicode.mymovie.Activity.MainActivity;
import org.meicode.mymovie.LoadingDialogBar;
import org.meicode.mymovie.R;

public class CheckDataActivity extends AppCompatActivity {
    LoadingDialogBar loadingDialogBar = new LoadingDialogBar(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_data);
        checkData();
    }

    public void checkData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String data = sharedPreferences.getString("User", "");
        Log.d("DataInSharePreference0", data);
        if ("".equals(data)) {
            Intent intent = LoginActivity.newIntentToLogin(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("SuccessLoginActivity", data.toString());
            startActivity(intent);

        } else {
            Intent intent = MainActivity.newIntentToMainactivity(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("SuccessMainActivity", data.toString());
            startActivity(intent);
        }
    }
}