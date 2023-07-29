package com.example.firstdemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstdemo.R;
import com.example.firstdemo.adapter.SystemAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SystemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SystemFragment extends Fragment {

    private String[] mTitles = {"体系", "导航"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;




    public SystemFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SystemFragment newInstance() {
        SystemFragment fragment = new SystemFragment();
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
        View v = inflater.inflate(R.layout.fragment_system, container, false);
        viewPager = v.findViewById(R.id.fixedViewPager);
        slidingTabLayout = v.findViewById(R.id.slidingTabLayout);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragments.add(LittleSystemFragment.newInstance());
        mFragments.add(NavigateFragment.newInstance());

        //预加载fragment
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.setAdapter(new SystemAdapter(getFragmentManager(), mTitles, mFragments));
        slidingTabLayout.setViewPager(viewPager);

    }
}