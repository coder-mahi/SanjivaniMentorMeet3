package com.kulswaminitech.sanjivanimentormeet;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    EditText t1,t2;
    TextView text;
    public static String fix="";
    Button login;
    private  static  final  String apiurl="https://sanjivanimentor.000webhostapp.com/SanjivaniMentor/login_student.php";
    String selected_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        text=findViewById(R.id.text);
        //taking values form fristActivity
//        String admin=getIntent().getStringExtra("AdminName");
//        String student=getIntent().getStringExtra("StudentName");
//        String staff=getIntent().getStringExtra("StaffName");

        selected_user = getIntent().getStringExtra("selected_user");
//        if( admin != null)
//        {
//            fix= getIntent().getStringExtra("AdminName");
//            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
////            text.setText(fix);
//        }
//        else if(staff != null )
//        {
//            fix=getIntent().getStringExtra("StaffName");
//            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
//        } else if (student != null) {
//            fix=getIntent().getStringExtra("StudentName");
//            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
//        }

        if(Objects.equals(selected_user, "admin")){
            fix= getIntent().getStringExtra("selected_user");
            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
//            text.setText(fix);
        } else if (Objects.equals(selected_user, "staff")) {
            fix=getIntent().getStringExtra("selected_user");
            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
        } else if (Objects.equals(selected_user, "student")) {
            fix=getIntent().getStringExtra("selected_user");
            Toast.makeText(this, fix, Toast.LENGTH_SHORT).show();
        }

//        checkexistenceuser();
        checklogoutmsg(text);
    }
    public  void login_process(View view)
    {
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText) findViewById(R.id.t2);
        text=(TextView)findViewById(R.id.text);
        String t=t1.getText().toString();
        String qry="?t1="+t1.getText().toString().trim()+"&t2="+t2.getText().toString().trim()+"&table="+fix;
        class dbproces extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                if(data.equals("found"))
                {
                    SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("uname",t1.getText().toString());

                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("enrollment_no",t);
//                    i.putExtra("admin",fix);
                    i.putExtra("selected_user",fix);
                    startActivity(i);
                    finish();
                }
                else{
                    t1.setText("");
                    t2.setText("");
                    Toast.makeText(LoginActivity.this, data, Toast.LENGTH_SHORT).show();
                    text.setText(data);
                }
            }
            @Override
            protected String doInBackground(String... strings) {
                String furl=strings[0];
                try {
                    URL url=new URL(furl);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return  br.readLine();
                }catch (Exception e)
                {
                    return  e.getMessage();
                }
            }
        }
        dbproces obj=new dbproces();
        obj.execute(apiurl+qry);
    }
    void checkexistenceuser()
    {
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("uname"))
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        else{
            Toast.makeText(this, "plz login", Toast.LENGTH_SHORT).show();
        }
    }

    public void  checklogoutmsg(View view)
    {
        text=(TextView)findViewById(R.id.text);
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("msg"))
        {
            text.setText(sp.getString("msg",""));
            SharedPreferences.Editor ed=sp.edit();
            ed.remove("msg");
            ed.commit();
        }
    }
}