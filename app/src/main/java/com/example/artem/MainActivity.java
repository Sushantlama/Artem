package com.example.artem;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.artem.Home.HomeFragment;
import com.example.artem.SEARCH.SearchFragment;
import com.example.artem.User.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    String homebackname= HomeFragment.class.getName();
    String searchbackname=SearchFragment.class.getName();
    String userbackname=UserFragment.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar=findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.d_1));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment()).commit();

        navigationView=findViewById(R.id.bottom_nav_bar);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment()).addToBackStack(homebackname).commit();
                        break;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new SearchFragment()).addToBackStack(searchbackname).commit();
                        break;
                    case R.id.user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new UserFragment()).addToBackStack(userbackname).commit();
                        break;


                }
                return true;
            }
        });




    }

}