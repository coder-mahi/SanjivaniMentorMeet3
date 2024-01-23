package com.kulswaminitech.sanjivanimentormeet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        String user_name = get user name from mainActivity
//        String user_email = get user name from mainActivity
        TextView profile_ID, profile_username, profile_emailId;
        profile_ID = view.findViewById(R.id.profile_ID);
        profile_username = view.findViewById(R.id.profile_username);
        profile_emailId = view.findViewById(R.id.profile_emailID);

        ImageButton editProfile = view.findViewById(R.id.edit_profile);
        Button personal_details = view.findViewById(R.id.personal_Details);
        Button course_details = view.findViewById(R.id.profile_courseDetails);
        Button upload_certificate = view.findViewById(R.id.upload_certificate);

        String user_id = getArguments().getString("user_id");
//        profile_ID.setText(user_id);

//        profile_username.setText(user_name);
//        profile_emailId.setText(user_email);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update query
                Toast.makeText(getContext(), "Enter All Deatils Correctly", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), EditInformationActivity.class);
//                startActivity(intent);
            }
        });

        personal_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select query
                Toast.makeText(getContext(), "Your Personal Details..!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PersonalDetailsActivity.class);
                startActivity(intent);
            }
        });

        course_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select query
                Toast.makeText(getContext(), "Your Academics Details..!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });

        upload_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert query
                Toast.makeText(getContext(), "Upload Certificate..!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), UploadCertificateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}