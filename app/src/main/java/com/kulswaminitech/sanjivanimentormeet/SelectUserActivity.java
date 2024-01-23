package com.kulswaminitech.sanjivanimentormeet;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class SelectUserActivity extends AppCompatActivity {

    Button Admin,Student,Staff;
    String selected_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        Admin=findViewById(R.id.admin);
        Student=findViewById(R.id.student);
        Staff=findViewById(R.id.staff);
        String admin=Admin.getText().toString();
        String student=Student.getText().toString();
        String staff=Staff.getText().toString();

//        Toast.makeText(FristActivity.this, admin, Toast.LENGTH_SHORT).show();

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String admin=Admin.getText().toString();
//
//                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
//                i.putExtra("AdminName",admin);
//                startActivity(i);
                selected_user = "admin";
                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
                i.putExtra("selected_user",selected_user);
                startActivity(i);
            }
        });
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String student=Student.getText().toString();
//                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
//                i.putExtra("StudentName",student);
//                startActivity(i);
                selected_user = "student";
                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
                i.putExtra("selected_user",selected_user);
                startActivity(i);
            }
        });
        Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String staff=Staff.getText().toString();
//                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
//                i.putExtra("StaffName",staff);
//                startActivity(i);
                selected_user = "staff";
                Intent i=new Intent(SelectUserActivity.this, LoginActivity.class);
                i.putExtra("selected_user",selected_user);
                startActivity(i);
            }
        });

    }
}