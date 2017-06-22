package com.base.fortunecourier.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import com.base.fortunecourier.R;
import com.base.fortunecourier.adapter.CustomSpinnerAdapter;
import java.util.ArrayList;

public class ToolsFragment extends Fragment {

    public ToolsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View  view = inflater.inflate(R.layout.fragment_tools, container, false);

        Spinner spinnerCustom= (Spinner) view.findViewById(R.id.spinnerCustom);
        Spinner country_spinner2 = (Spinner) view.findViewById(R.id.country_spinner2);

        // Spinner Drop down elements
        ArrayList<String> country_list = new ArrayList<String>();
        country_list.add("US");
        country_list.add("IN");
        country_list.add("NZ");
        country_list.add("AF");
        country_list.add("AL");
        country_list.add("CA");

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),country_list);
        spinnerCustom.setAdapter(customSpinnerAdapter);

        country_spinner2.setAdapter(customSpinnerAdapter);

        return view;
    }

}
