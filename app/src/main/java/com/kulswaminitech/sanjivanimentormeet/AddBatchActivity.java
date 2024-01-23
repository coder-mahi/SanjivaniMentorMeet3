package com.kulswaminitech.sanjivanimentormeet;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class AddBatchActivity extends AppCompatActivity {
Button AddBatch;
EditText batchId;
    private  static  final  String apiurl1="https://sanjivanimentor.000webhostapp.com/SanjivaniMentor/create_tab.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);
        AddBatch=findViewById(R.id.AddBatch);
        batchId=findViewById(R.id.batchId);
        AddBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String batchName = Objects.requireNonNull(batchId.getText()).toString();

                //for table creation

                if (batchName.equals("")) {
                    Toast.makeText(AddBatchActivity.this, "plz enter valid table name", Toast.LENGTH_SHORT).show();
                } else {


                    String qry = "?table=" + batchId.getText().toString().trim();
                    class dbproces extends AsyncTask<String, Void, String> {
                        protected void onPostExecute(String data) {

                            Toast.makeText(AddBatchActivity.this, "Table created", Toast.LENGTH_SHORT).show();
                             Intent i=new Intent(AddBatchActivity.this, FetchBatchActivity.class);
                             i.putExtra("table",batchName);
                             startActivity(i);
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
                }
            }
        });
    }

}