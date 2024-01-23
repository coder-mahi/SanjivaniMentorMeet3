package com.kulswaminitech.sanjivanimentormeet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_navigation;
    TextView profilename, enrollmentNo, loggedInStatus;
    ImageButton noticationBtn;
    DrawerLayout drawerLayout;  //maindrawer
    Toolbar toolbar;     //toolbar
    NavigationView navigationView;   //nDrawer
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    ActionBarDrawerToggle drawerToggle;
    //header
    ImageView header_profile_icon;
    TextView header_enrollment,header_username,header_login_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticationBtn = (ImageButton) findViewById(R.id.noticationBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar); //Application name inside toolbar
        noticationBtn = (ImageButton) findViewById(R.id.noticationBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar); //Application name inside toolbar

//header Details
        View header = LayoutInflater.from(getApplicationContext()).inflate(R.layout.header,null);
        navigationView.addHeaderView(header);
        navigationView.getHeaderView(R.layout.header);

        header_profile_icon = findViewById(R.id.header_profile_icon);
        header_username = findViewById(R.id.header_username);
        header_enrollment = findViewById(R.id.header_enrollment_no);
//
//        View header = navigationView.getHeaderView(0);
//
        profilename =  header.findViewById(R.id.header_username);
        enrollmentNo =  header.findViewById(R.id.header_enrollment_no);
        loggedInStatus = header.findViewById(R.id.header_login_status);

        String selectedUser = getIntent().getStringExtra("selected_user");
        String Enrollment_No = getIntent().getStringExtra("enrollment_no");
        enrollmentNo.setText(Enrollment_No);
        loggedInStatus.setText(selectedUser);
//        //profilename.setText( From Database Access the Name Of User);
//
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottom_navigation.setBackground(null);
        if(selectedUser.equals("admin")){
            MainActivity.this.loadFragment(new AdminDashboardFragment());
        } else if (selectedUser.equals("staff")) {
            MainActivity.this.loadFragment(new MentorDashboardFragment());
        }else{
            MainActivity.this.loadFragment(new HomeFragment());
        }

////        bottom navigation
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home_menu) {
                    if(selectedUser.equals("admin")){
                        MainActivity.this.loadFragment(new AdminDashboardFragment());
                    } else if (selectedUser.equals("staff")) {
                        MainActivity.this.loadFragment(new MentorDashboardFragment());
                    }else{
                        MainActivity.this.loadFragment(new HomeFragment());
                    }                } else if (id == R.id.bottom_chat_menu) {
//                    MainActivity.this.loadFragment(new ChatFragment());
                    Toast.makeText(MainActivity.this, "Chat menu..!", Toast.LENGTH_SHORT).show();
                } else if(id == R.id.bottom_profile_menu){
                    //passing enrollment no to profile fragment
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id", Enrollment_No);
                    ProfileFragment fragobj = new ProfileFragment();
                    fragobj.setArguments(bundle);
                    MainActivity.this.loadFragment(new ProfileFragment());
                    Toast.makeText(MainActivity.this, "Profile menu..!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        noticationBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,"Notifications are not yet available..!",Toast.LENGTH_SHORT).show());

    }
//    //        drawer navigation
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.profile_menu) {
        MainActivity.this.loadFragment(new ProfileFragment());
        Toast.makeText(MainActivity.this, "Profile menu..!", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.rate_menu) {
//        MainActivity.this.loadFragment(new RateFragment());
        Toast.makeText(MainActivity.this, "Rate menu..!", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.reset_password_menu) {
        Toast.makeText(MainActivity.this, "You are not able to change Password..!", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.share_menu) {
//                    loadFragment(new HomeFragment());
        Toast.makeText(MainActivity.this, "Share menu..!", Toast.LENGTH_SHORT).show();
    } else if (id == R.id.logout_menu) {
        Intent intent = new Intent(MainActivity.this, SelectUserActivity.class);
        startActivity(intent);
        finish();
        Toast toast = Toast.makeText(MainActivity.this, "Logged Out...!", Toast.LENGTH_SHORT);
        toast.show();
    }
    drawerLayout.closeDrawer(GravityCompat.START);
    return true;
}
    //    });
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //fragment loading via method
    private void loadFragment(Fragment fragment) {
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.removeAllViews();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }
//    @SuppressLint("UnsafeIntentLaunch")
//    @Override
//    public void recreate(){
//        finish();
//        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
//        startActivity(getIntent());
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//    }
}
