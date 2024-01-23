package com.kulswaminitech.sanjivanimentormeet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchActivityStudent extends AppCompatActivity {

    RecyclerView recycle;
    SearchView search;

    List<FetchStudentClass> filterList;
    List<FetchStudentClass> list=new ArrayList<>();
  public static String table123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_student);
        table123=getIntent().getStringExtra("table");

        recycle=findViewById(R.id.recycle);
        search=findViewById(R.id.search);
        search.clearFocus();
        recycle.setLayoutManager(new LinearLayoutManager(this));
        view();

        //search view listener
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }



    private void view() {
        StringRequest request=new StringRequest(Request.Method.GET, linkApi.url+"StudentView.php?table="+getIntent().getStringExtra("table"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject=array.getJSONObject(i);
                        list.add(new FetchStudentClass(
                                jsonObject.getString("enrollmentNo"),
                                jsonObject.getString("fyRoll"),
                                jsonObject.getString("syRoll"),
                                jsonObject.getString("tyRoll"),
                                jsonObject.getString("NameOfStudent"),
                                jsonObject.getString("emailId"),
                                jsonObject.getString("contact"),
                                jsonObject.getString("parentNo"),
                                jsonObject.getString("category"),
                                jsonObject.getString("gender"),
                                jsonObject.getString("batch"),
                                jsonObject.getString("address1"),
                                jsonObject.getString("Mentor"),
                                jsonObject.getString("fatherName"),
                                jsonObject.getString("motherName"),
                                jsonObject.getString("bloodGroup")
                        ));
                    }
//                    table=getIntent().getStringExtra("table");
                    FetchStudentAdapter adapter=new FetchStudentAdapter(FetchActivityStudent.this,list,table123);
                    recycle.setAdapter(adapter);
                }
                catch (JSONException e) {
//                    Toast.makeText(FetchActivityStudent.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error",""+e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    //searchView implementation
    private void filterList(String newText) {
        filterList = new ArrayList<>();
        for (FetchStudentClass viewClass : list) {
            if (viewClass.getNameOfStudent().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(viewClass);
            }
        }
        if (filterList.isEmpty()) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            FetchStudentAdapter adapter = new FetchStudentAdapter(FetchActivityStudent.this, filterList,table123);
            recycle.setAdapter(adapter); // Set the filtered list to the RecyclerView
        }
    }
    }
