package com.example.firstdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.firstdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigateFragment extends Fragment {


    public NavigateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NavigateFragment newInstance() {
        NavigateFragment fragment = new NavigateFragment();
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
        View v = inflater.inflate(R.layout.fragment_navigate, container, false);
        return v;
    }
}