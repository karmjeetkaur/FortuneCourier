package com.base.fortunecourier.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.base.fortunecourier.R;
import com.base.fortunecourier.activites.CreateShipmentActivity;
import com.base.fortunecourier.activites.DrawerActivity;
import com.base.fortunecourier.adapter.CustomSpinnerAdapter;
import java.util.ArrayList;

public class RateCalculatorFragment extends Fragment {

    Button btn_createShipment_order;
    Button btnMeasurementConverter;
    Button btnSubmit;
    LinearLayout rate_layout;

    public RateCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_rate_calculator, container, false);

        btn_createShipment_order = (Button) view.findViewById(R.id.btn_createShipment_order);
        btn_createShipment_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CreateShipmentActivity.class);
                startActivity(intent);
            }
        });

        btnMeasurementConverter = (Button) view.findViewById(R.id.btnMeasurementConverter);
        btnMeasurementConverter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DrawerActivity.toolbar.setTitle("Tools");
                Fragment fragment = new ToolsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, fragment);
                fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
                fragmentTransaction.addToBackStack("A_B_TAG");
                fragmentTransaction.commit();
            }
        });

        rate_layout = (LinearLayout) view.findViewById(R.id.rate_layout);

        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate_layout.setVisibility(View.VISIBLE);
            }
        });

        ArrayList<String> country_list = new ArrayList<String>();
        country_list.add("Country");
        country_list.add("US");
        country_list.add("IN");
        country_list.add("NZ");
        country_list.add("AF");
        country_list.add("AL");
        country_list.add("CA");

        Spinner spinnerShipFromCountry= (Spinner) view.findViewById(R.id.spinnerShipFromCountry);
        Spinner spinnerShiptoCountry = (Spinner) view.findViewById(R.id.spinnerShiptoCountry);
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),country_list);
        spinnerShipFromCountry.setAdapter(customSpinnerAdapter);
        spinnerShiptoCountry.setAdapter(customSpinnerAdapter);

        ArrayList<String> numberOfPackage_list = new ArrayList<String>();
        numberOfPackage_list.add("No of Packages");
        numberOfPackage_list.add("1");
        numberOfPackage_list.add("2");
        numberOfPackage_list.add("3");
        numberOfPackage_list.add("4");
        Spinner spinnerNumberOfPackage = (Spinner) view.findViewById(R.id.spinnerNumberOfPackage);
        CustomSpinnerAdapter packages_spinnerAdapter=new CustomSpinnerAdapter(getActivity(),numberOfPackage_list);
        spinnerNumberOfPackage.setAdapter(packages_spinnerAdapter);

        ArrayList<String> numberOfinch_list = new ArrayList<String>();
        numberOfinch_list.add("inch");
        numberOfinch_list.add("cm");
        Spinner spinnerCustom_inches = (Spinner) view.findViewById(R.id.spinnerCustom_inches);
        CustomSpinnerAdapter inch_spinnerAdapter=new CustomSpinnerAdapter(getActivity(),numberOfinch_list);
        spinnerCustom_inches.setAdapter(inch_spinnerAdapter);

        ArrayList<String> numberOfkg_list = new ArrayList<String>();
        numberOfkg_list.add("kg");
        Spinner spinnerCustom_kg = (Spinner) view.findViewById(R.id.spinnerCustom_kg);
        CustomSpinnerAdapter kg_spinnerAdapter=new CustomSpinnerAdapter(getActivity(),numberOfkg_list);
        spinnerCustom_kg.setAdapter(kg_spinnerAdapter);

        ArrayList<String> city_list = new ArrayList<String>();
        city_list.add("City");
        city_list.add("New York");
        city_list.add("Los Angeles");
        city_list.add("Chicago");
        city_list.add("Houston");
        city_list.add("San Antonio");
        city_list.add("Columbus");
        Spinner spinnerShiptoCity = (Spinner) view.findViewById(R.id.spinnerShiptoCity);
        Spinner spinnerShipFromCity = (Spinner) view.findViewById(R.id.spinnerShipFromCity);
        CustomSpinnerAdapter citySpinnerAdapter=new CustomSpinnerAdapter(getActivity(),city_list);
        spinnerShiptoCity.setAdapter(citySpinnerAdapter);
        spinnerShipFromCity.setAdapter(citySpinnerAdapter);

        ArrayList<String> numberOflb_list = new ArrayList<String>();
        numberOflb_list.add("lb");
        Spinner spinnerCustom_lb = (Spinner) view.findViewById(R.id.spinnerCustom_lb);
        CustomSpinnerAdapter lbSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),numberOflb_list);
        spinnerCustom_lb.setAdapter(lbSpinnerAdapter);

        TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        String value = "The price estimated above are not final and may vary basic the actual weight of your consignment as weighed at the store "
                + "and/or its volumetric weight. Whichever applicable,serviceability to the selected destination and other parameters." +
                " Relevant taxes/fees and surcharges will also be applied.";
        tv_desc.setText(value);

        return view;
    }
}
