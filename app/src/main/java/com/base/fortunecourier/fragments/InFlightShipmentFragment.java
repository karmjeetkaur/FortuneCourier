package com.base.fortunecourier.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.base.fortunecourier.R;
import com.base.fortunecourier.adapter.ShipmentListAdapter;

import java.util.ArrayList;

public class InFlightShipmentFragment extends Fragment
{
    RecyclerView recycleView;
    ArrayList<String> arrayList = new ArrayList<String>();

    public InFlightShipmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_in_flight_shipment, container, false);

        recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(layoutManager);

        arrayList.add("Salentro");
        arrayList.add("Salentro");
        arrayList.add("Salentro");
        arrayList.add("Salentro");
        arrayList.add("Salentro");

        recycleView.setAdapter(new ShipmentListAdapter(arrayList,getActivity()));

        return view;
    }

}
