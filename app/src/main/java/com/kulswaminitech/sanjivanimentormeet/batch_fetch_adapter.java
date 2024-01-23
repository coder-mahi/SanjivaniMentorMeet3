package com.kulswaminitech.sanjivanimentormeet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;


public class batch_fetch_adapter extends RecyclerView.Adapter<batch_fetch_adapter.ViewHolder> {
ArrayList<batch_fetch_student> arrayList;
Context context;
 batch_fetch_adapter( Context context,ArrayList<batch_fetch_student> arrayList)
 {
     this.arrayList=arrayList;
     this.context=context;
 }
    public class ViewHolder extends RecyclerView.ViewHolder {
  TextView textView;
//public  String table;
  AppCompatButton dropTable,fetchData;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.batch);
            dropTable=itemView.findViewById(R.id.dropTable);
            fetchData=itemView.findViewById(R.id.fetchData);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v= LayoutInflater.from(context).inflate(R.layout.batch_recycle,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     final batch_fetch_student student=arrayList.get(position);
     holder.textView.setText(student.getTable());

     //dropping the table in recycle view
        holder.dropTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert =new AlertDialog.Builder(context);
                alert.setTitle("Delete?");
                alert.setIcon(R.drawable.delete2);
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"delete_batch.php?table_name="+student.getTable(), null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    boolean bol=response.getBoolean("success");
                                    if(bol)
                                    {
                                        Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(context, FetchBatchActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intent);
                                        ((FetchBatchActivity) context).finish();

                                    }
                                    else{
                                        Toast.makeText(context, "Record Not Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e)
                                {
                                    Log.d("",""+e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Volley.newRequestQueue(context).add(objectRequest);
                    }
                });
//                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"delete_batch.php?table_name="+student.getTable(), null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try{
//                            boolean bol=response.getBoolean("success");
//                            if(bol)
//                            {
//                                Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(context,FetchBatchActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                context.startActivity(intent);
//                                ((FetchBatchActivity) context).finish();
//
//                            }
//                            else{
//                                Toast.makeText(context, "Record Not Deleted", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        catch (Exception e)
//                        {
//                            Log.d("",""+e);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//                Volley.newRequestQueue(context).add(objectRequest);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }

        });

        //fetching data
        holder.fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sp= context.getSharedPreferences("table_name",context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sp.edit();
//                editor.putString("table",student.getTable());
//                editor.commit();
                Intent i=new Intent(context,FetchActivityStudent.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                i.putExtra("table",student.getTable());
//                String table=student.getTable();
//                Toast.makeText(context, table, Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
