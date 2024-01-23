package com.kulswaminitech.sanjivanimentormeet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MentorDashboardFragment extends Fragment {
    Button addMentee, viewMentees;

    public MentorDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addMentee = getView().findViewById(R.id.addMentee);
        viewMentees = getView().findViewById(R.id.viewMentees);
        addMentee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddMenteeActivity.class ));
                Toast.makeText(getContext(), "Enter Mentee Details Properly", Toast.LENGTH_SHORT).show();
            }
        });
        viewMentees.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), ViewMenteesActivity.class));
            Toast.makeText(getContext(), "Viewing Mentees in RecyclerView from DB", Toast.LENGTH_SHORT).show();
         }
         }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mentor_dashboard, container, false);
    }
}