package com.kulswaminitech.sanjivanimentormeet;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button personal_details = view.findViewById(R.id.personal_Details);
        Button course_details = view.findViewById(R.id.profile_courseDetails);
        Button upload_certificate = view.findViewById(R.id.upload_certificate);
        TextView allocatedMentorName = view.findViewById(R.id.allocatedMentorName);

        // String MentorName = fetch mentor name from db;
        // allocatedMentorName.setText(MentorName);
        personal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select query
                Toast.makeText(getContext(),"Your Personal Details..!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PersonalDetailsActivity.class);
                startActivity(intent);
            }
        });

        course_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select query
                Toast.makeText(getContext(),"Your Academics Details..!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });

        upload_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert query
                Toast.makeText(getContext(),"Upload Certificate..!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), UploadCertificateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}