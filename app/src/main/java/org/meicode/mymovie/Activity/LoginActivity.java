package org.meicode.mymovie.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.meicode.mymovie.API.RetrofitClient;
import org.meicode.mymovie.LoadingDialogBar;
import org.meicode.mymovie.R;
import org.meicode.mymovie.User.DetailUserResponse;
import org.meicode.mymovie.User.RequestTokenResponse;
import org.meicode.mymovie.User.SessionIdResponse;
import org.meicode.mymovie.User.SessionRequest;
import org.meicode.mymovie.User.SessionRequestLoginResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txt_username;
    EditText txt_password;
    TextView txtSignup;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    LoadingDialogBar loadingDialogBar;
    Context context;
    Handler handler = new Handler();

    public static Intent newIntentToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_username = findViewById(R.id.edt_username);
        txt_password = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtSignup = findViewById(R.id.txt_signup);
        loadingDialogBar = new LoadingDialogBar(this);
        RetrofitClient retrofitClient = new RetrofitClient();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequestToken();

            }
        });
    }

    public void getRequestToken() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Disposable disposable = retrofitClient.getApiService().getRequestToken()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        Log.d("doOnSubcribe", "Accept doOnSubcribe");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialogBar.showDialog("Login");
                            }
                        });
                    }
                })
                .flatMap(new Function<RequestTokenResponse, ObservableSource<SessionRequestLoginResponse>>() {
                    @Override
                    public ObservableSource<SessionRequestLoginResponse> apply(RequestTokenResponse requestTokenResponse) throws Throwable {
                        Log.d("Token", requestTokenResponse.getRequestToken());
                        SessionRequest sessionRequest = new SessionRequest();
                        sessionRequest.setToken(requestTokenResponse.getRequestToken());
                        sessionRequest.setUsername(txt_username.getText().toString());
                        sessionRequest.setPassword(txt_password.getText().toString());
                        return retrofitClient.getApiService().createSessionRequest(sessionRequest);
                    }
                })
                .flatMap(new Function<SessionRequestLoginResponse, ObservableSource<SessionIdResponse>>() {
                    @Override
                    public ObservableSource<SessionIdResponse> apply(SessionRequestLoginResponse sessionRequestLoginResponse) throws Throwable {

                        return retrofitClient.getApiService().createSessionIdRequest(sessionRequestLoginResponse);
                    }
                })
                .flatMap(new Function<SessionIdResponse, ObservableSource<DetailUserResponse>>() {
                    @Override
                    public ObservableSource<DetailUserResponse> apply(SessionIdResponse sessionIdResponse) throws Throwable {
                        return retrofitClient.getApiService().getDetailUser(sessionIdResponse.getSessionId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("Here", "Have Run");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialogBar.hideDialog();
                            }
                        });
                    }
                })
                .subscribe(new Consumer<DetailUserResponse>() {
                    @Override
                    public void accept(DetailUserResponse detailUserResponse) throws Throwable {
                        Log.d("Subcribe", "Run Subscribe");
                        Gson gson = new Gson();
                        String json = gson.toJson(detailUserResponse);
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("User", json);
                        myEdit.apply();

                        Log.d("UserinsharePreferen", sharedPreferences.getString("User", ""));

                        Log.d("User1", gson.toJson(detailUserResponse));


                        Intent intent = MainActivity.newIntentToMainactivity(LoginActivity.this);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        setTitle("Home");

                        startActivity(intent);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("Error", throwable.getMessage());

                    }
                });
        compositeDisposable.add(disposable);
    }

//    public void postTokenGetSessionLogin(SessionRequest sessionRequest) {
//
//        RetrofitClient retrofitClient = new RetrofitClient();
//        retrofitClient.getApiService().createSessionRequest(sessionRequest).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<SessionRequestLoginResponse>() {
//                    @Override
//                    public void accept(SessionRequestLoginResponse sessionRequestLoginResponse) throws Throwable {
//                        Log.d("Have Token", "Have Session Id");
//                        postTokenGetSessionId(sessionRequestLoginResponse);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("Error", throwable.getMessage());
//                    }
//                });
//    }
//
//    public void postTokenGetSessionId(SessionRequestLoginResponse sessionRequestLoginResponse) {
//        RetrofitClient retrofitClient = new RetrofitClient();
//        retrofitClient.getApiService().createSessionIdRequest(sessionRequestLoginResponse).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<SessionIdResponse>() {
//                    @Override
//                    public void accept(SessionIdResponse sessionIdResponse) throws Throwable {
//                        Log.d("Have Token123", sessionIdResponse.getSessionId());
//                        getDetailUser(sessionIdResponse.getSessionId());
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("Error", throwable.getMessage());
//                    }
//                });
//    }
//
//
//    public void getDetailUser(String sessionIdResponse) {
//        RetrofitClient retrofitClient = new RetrofitClient();
//        retrofitClient.getApiService().getDetailUser(sessionIdResponse).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<DetailUserResponse>() {
//                    @Override
//                    public void accept(DetailUserResponse detailUserResponse) throws Throwable {
//                        Gson gson = new Gson();
//                        String json = gson.toJson(detailUserResponse);
//
//                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                        myEdit.putString("User", json);
//                        myEdit.apply();
//
//                        Log.d("UserinsharePreferen", sharedPreferences.getString("User", ""));
//
//                        Log.d("User1", gson.toJson(detailUserResponse));
//
//
//                        Intent intent = MainActivity.newIntentToMainactivity(LoginActivity.this);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        setTitle("Home");
//                        startActivity(intent);
//
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("Error", throwable.getMessage());
//                    }
//                });
//    }
}