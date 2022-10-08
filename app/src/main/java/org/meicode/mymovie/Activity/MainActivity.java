package org.meicode.mymovie.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.meicode.mymovie.Fragment.FavoriteFragment;
import org.meicode.mymovie.Fragment.HomeFragment;
import org.meicode.mymovie.Genres.GenresActivity;
import org.meicode.mymovie.LoadingDialogBar;
import org.meicode.mymovie.R;
import org.meicode.mymovie.User.DetailUserResponse;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment = new HomeFragment();

    Intent intent = new Intent();

    TextView txtNameHeader;
    TextView txtEmailHeader;
    LoadingDialogBar loadingDialogBar = new LoadingDialogBar(this);


    public static Intent newIntentToMainactivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frameLayout, HomeFragment.class, null)
                    .commit();
        }
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        homeFragment = HomeFragment.newInstance();
                        toolbar.setTitle("Home");
                        break;
                    case R.id.favouriteFragment:
                        homeFragment = FavoriteFragment.newInstance();
                        toolbar.setTitle("Favourite");
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                return true;
            }
        });

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);


        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        txtEmailHeader = header.findViewById(R.id.txtEmailHeader);
        txtNameHeader = header.findViewById(R.id.txtNameHeader);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", "");
        DetailUserResponse detailUserResponse = gson.fromJson(json, DetailUserResponse.class);

        txtEmailHeader.setText(detailUserResponse.getUsername() + "@gmail.com");
        txtNameHeader.setText(detailUserResponse.getName());


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                intent = ProfileActivity.newIntentToProfileActivity(this);
                loadingDialogBar.showDialog("Profile");
                startActivity(intent);

                break;
            case R.id.genres:
                intent = GenresActivity.newIntentToGenresActivity(this);
                startActivity(intent);
            case R.id.search:
                intent = SearchActivity.newIntentToSearchActivity(this);
                startActivity(intent);


        }
        return true;
    }
}