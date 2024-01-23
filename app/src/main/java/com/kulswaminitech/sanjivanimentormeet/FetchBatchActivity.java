package com.kulswaminitech.sanjivanimentormeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class FetchBatchActivity extends AppCompatActivity {
ArrayList<batch_fetch_student> arr=new ArrayList<>();
RecyclerView recyclerView;
    batch_fetch_adapter adapter1;
  FloatingActionButton openDailog;
  private  static  final  String apiurl1="https://sanjivanimentor.000webhostapp.com/SanjivaniMentor/create_tab.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_student);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        openDailog=findViewById(R.id.openDailog);


        Dialog dailog=new Dialog(FetchBatchActivity.this);
        dailog.setContentView(R.layout.customlayout_fetchstudentactivity);
        EditText batchId=dailog.findViewById(R.id.batchId);
        AppCompatButton AddBatch=dailog.findViewById(R.id.AddBatch);

        //adding batch of students
        openDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBatch.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String batchName = Objects.requireNonNull(batchId.getText()).toString();
                        //for table creation

                        if (batchName.equals("")) {
                            Toast.makeText(FetchBatchActivity.this, "plz enter valid table name", Toast.LENGTH_SHORT).show();
                        } else {


                            String qry = "?table=" + batchId.getText().toString().trim();
                            class dbproces extends AsyncTask<String, Void, String> {
                                protected void onPostExecute(String data) {

//                                    SharedPreferences sp=getSharedPreferences("table_name",MODE_PRIVATE);
//                                    SharedPreferences.Editor editor=sp.edit();
////
//                                    editor.putString("table",);
//
//                                    editor.commit();
//
//                                    Toast.makeText(FetchBatchActivity.this, batchName, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                protected String doInBackground(String... strings) {
                                    String furl = strings[0];
                                    try {
                                        URL url = new URL(furl);
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                        return br.readLine();
                                    } catch (Exception e) {
                                        return e.getMessage();
                                    }
                                }
                            }
                            dbproces obj = new dbproces();
                            obj.execute(apiurl1 + qry);
                            dailog.dismiss();
                            
                        }


//                Toast.makeText(FetchBatchActivity.this, batchName, Toast.LENGTH_SHORT).show();

              //for inserting table into the database

                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url + "addTableBatch.php?table_name="+batchName
                        , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean bol = response.getBoolean("success");
                            if (bol) {
                                Toast.makeText(FetchBatchActivity.this, "records Inserted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(FetchBatchActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(FetchBatchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(objectRequest);


                        refreshActivity();

                    }

                 
                });
                dailog.show();
            }
        });

        StringRequest request=new StringRequest(Request.Method.GET, linkApi.url+"FetchBatch.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        arr.add(new batch_fetch_student(
                                jsonObject.getString("table_name")
                        ));
                    }
                    adapter1=new batch_fetch_adapter(FetchBatchActivity.this,arr);
                    recyclerView.setAdapter(adapter1);
                }
                catch (Exception e)
                {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void refreshActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}