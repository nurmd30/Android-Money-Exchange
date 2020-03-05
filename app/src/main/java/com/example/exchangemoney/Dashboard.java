package com.example.exchangemoney;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.material.navigation.NavigationView;


public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

/*         Navigation Drawer Menu                              */
        //Hide and show Item
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(true);

        //Condition Hide Profile
//        if (TextUtils.isEmpty(username)) {
//
//
//
//        }


        navigationView.bringToFront();// Navigation Select Item View
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation Item On Click
        navigationView.setNavigationItemSelectedListener(this);

        //Check navigation Item
        navigationView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                break;

            case R.id.nav_Gateway:
                Intent gatewayIntent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(gatewayIntent);
                break;

            case R.id.nav_login:
                Intent loginIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(loginIntent);
                break;

            case R.id.nav_signUp:
                Intent signUpIntent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUpIntent);
                break;

            case R.id.nav_profile:
                Intent profile = new Intent(getApplicationContext(),UserProfile.class);
                startActivity(profile);
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
