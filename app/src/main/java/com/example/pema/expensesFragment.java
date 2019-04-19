package com.example.pema;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class expensesFragment extends Fragment {

    private View summaryView;
    private FragmentTabHost mTabHost;


    public expensesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (summaryView == null) {
            summaryView = inflater.inflate(R.layout.fragment_expenses, container, false);

            mTabHost = (FragmentTabHost) summaryView.findViewById(android.R.id.tabhost);
            mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

            mTabHost.addTab(mTabHost.newTabSpec("Today").setIndicator("Today"), todayFragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("Week").setIndicator("Week"), weekFragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("Month").setIndicator("Month"), monthFragment.class, null);
        }
        return summaryView;
    }

    public static expensesFragment newInstance() {



        expensesFragment fragment = new expensesFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
