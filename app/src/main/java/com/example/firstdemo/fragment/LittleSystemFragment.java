package com.example.firstdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdemo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LittleSystemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LittleSystemFragment extends Fragment {

    private RecyclerView recyclerView;

    public LittleSystemFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LittleSystemFragment newInstance() {
        LittleSystemFragment fragment = new LittleSystemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_little_system, container, false);
        return v;
    }
}