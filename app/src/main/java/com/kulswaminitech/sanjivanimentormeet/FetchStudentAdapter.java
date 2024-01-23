package com.kulswaminitech.sanjivanimentormeet;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class FetchStudentAdapter extends RecyclerView.Adapter<FetchStudentAdapter.ViewHolder> {
    Context context;
    static List<FetchStudentClass> list;
    String table;


    public FetchStudentAdapter(Context context,List<FetchStudentClass> list,String table)
    {
        this.context=context;
        this.list=list;
        this.table=table;
    }
    @NonNull
    @Override
    public FetchStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.student_fetch_data,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FetchStudentAdapter.ViewHolder holder, int position) {
        final  FetchStudentClass studentClass=list.get(position);

        holder.enrollmentNo.setText(studentClass.getEnrollmentNo());
        holder.fyRoll.setText(studentClass.getFyRoll());
        holder.syRoll.setText(studentClass.getSyRoll());
        holder.tyRoll.setText(studentClass.getTyRoll());
        holder.NameOfStudent.setText(studentClass.getNameOfStudent());
        holder.emailId.setText(studentClass.getEmailId());
        holder.contact.setText(studentClass.getContact());
        holder.parentNo.setText(studentClass.getParentNo());
        holder.category.setText(studentClass.getCategory());
        holder.gender.setText(studentClass.getGender());
        holder.batch.setText(studentClass.getBatch());
        holder.address1.setText(studentClass.getAddress1());
        holder.Mentor.setText(studentClass.getMentor());
        holder.fatherName.setText(studentClass.getFatherName());

        //deleting student data
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, linkApi.url+"delete_student_data.php?enrollmentNo="+studentClass.getEnrollmentNo() +
                        "&table=" + table, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            boolean bol=response.getBoolean("success");
                            if(bol)
                            {
                                Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
                                // Finish the current activity
                                ((FetchActivityStudent) context).finish();

                                // Start a new instance of the activity
                                Intent intent = new Intent(context, FetchActivityStudent.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("table",table);
                                context.startActivity(intent);

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
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        holder.NameOfStudent.setText(error.getMessage());
                        Log.d("",""+error.getMessage());
                    }
                });
                Volley.newRequestQueue(context).add(objectRequest);


            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView enrollmentNo,fyRoll,syRoll,tyRoll,NameOfStudent,emailId,contact,parentNo,category,gender,batch,
                address1,Mentor,fatherName;
        AppCompatButton update,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            enrollmentNo=itemView.findViewById(R.id.enrollmentNo);
            fyRoll=itemView.findViewById(R.id.fyRoll);
            syRoll=itemView.findViewById(R.id.syRoll);
            tyRoll=itemView.findViewById(R.id.tyRoll);
            NameOfStudent=itemView.findViewById(R.id.NameOfStudent);
            emailId=itemView.findViewById(R.id.emailId);
            contact=itemView.findViewById(R.id.contact);
            parentNo=itemView.findViewById(R.id.parentNo);
            category=itemView.findViewById(R.id.category);
            gender=itemView.findViewById(R.id.gender);
            batch=itemView.findViewById(R.id.batch);
            address1=itemView.findViewById(R.id.address1);
            Mentor=itemView.findViewById(R.id.Mentor);
            fatherName=itemView.findViewById(R.id.fatherName);
            update=itemView.findViewById(R.id.update);
            delete=itemView.findViewById(R.id.delete);


        }
    }
}
