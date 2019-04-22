package com.example.pema;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class searchFragment extends Fragment  {


    public searchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_search,container,false);
       return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final Spinner mSpinner_income = (Spinner)view.findViewById(R.id.spinner_category_search);
        ArrayAdapter<String> adapter_income = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.categories_search));
        adapter_income.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_income.setAdapter(adapter_income);
         final TextView date_from = view.findViewById(R.id.date_search_from);
         final TextView date_to = view.findViewById(R.id.date_search_to);

         date_from.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FragmentManager fm = getActivity().getFragmentManager();
                 com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog_date = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                         String strDate = (monthOfYear+1) + "/" + dayOfMonth + "/"+year;
                         date_from.setText(strDate);
                     }
                 },year,month,day);

                 dialog_date.show(fm,"Datepicker");
             }
         });

         date_to.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FragmentManager fm = getActivity().getFragmentManager();
                 com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog_date = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                         String strDate = (monthOfYear+1) + "/" + dayOfMonth + "/"+year;
                         date_to.setText(strDate);
                     }
                 },year,month,day);

                 dialog_date.show(fm,"Datepicker");
             }
         });



    }





}
