package com.base.fortunecourier.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import com.base.fortunecourier.R;
import com.base.fortunecourier.adapter.CustomSpinnerAdapter;
import java.util.ArrayList;

public class CreateShipmentFragment extends Activity
{
    ImageView back_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_shipment);

        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayList<String> country_list = new ArrayList<String>();
        country_list.add("Country*");
        country_list.add("US");
        country_list.add("IN");
        country_list.add("NZ");
        country_list.add("AF");
        country_list.add("AL");
        country_list.add("CA");

        Spinner spinnerShipFromCountry= (Spinner) findViewById(R.id.spinnerShipFromCountry);
        Spinner spinnerShiptoCountry = (Spinner) findViewById(R.id.spinnerShiptoCountry);
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(CreateShipmentFragment.this,country_list);
        spinnerShipFromCountry.setAdapter(customSpinnerAdapter);
        spinnerShiptoCountry.setAdapter(customSpinnerAdapter);


        ArrayList<String> city_list = new ArrayList<String>();
        city_list.add("City*");
        city_list.add("New York");
        city_list.add("Los Angeles");
        city_list.add("Chicago");
        city_list.add("Houston");
        city_list.add("San Antonio");
        city_list.add("Columbus");
        Spinner spinnerShiptoCity = (Spinner) findViewById(R.id.spinnerShiptoCity);
        Spinner spinnerShipFromCity = (Spinner) findViewById(R.id.spinnerShipFromCity);
        CustomSpinnerAdapter citySpinnerAdapter=new CustomSpinnerAdapter(CreateShipmentFragment.this,city_list);
        spinnerShiptoCity.setAdapter(citySpinnerAdapter);
        spinnerShipFromCity.setAdapter(citySpinnerAdapter);

        ArrayList<String> numberOflb_list = new ArrayList<String>();
        numberOflb_list.add("+1");
        Spinner spinnerShiptoCountryCode = (Spinner) findViewById(R.id.spinnerShiptoCountryCode);
        Spinner spinnerShipFromCountryCode = (Spinner) findViewById(R.id.spinnerShipFromCountryCode);
        CustomSpinnerAdapter lbSpinnerAdapter=new CustomSpinnerAdapter(CreateShipmentFragment.this,numberOflb_list);
        spinnerShipFromCountryCode.setAdapter(lbSpinnerAdapter);
        spinnerShiptoCountryCode.setAdapter(lbSpinnerAdapter);

        ArrayList<String> state_list = new ArrayList<String>();
        state_list.add("State*");
        state_list.add("New York");
        state_list.add("Los Angeles");
        state_list.add("Chicago");
        state_list.add("Houston");
        Spinner spinnerShipFromState = (Spinner) findViewById(R.id.spinnerShipFromState);
        Spinner spinnerShipToState = (Spinner) findViewById(R.id.spinnerShipToState);
        CustomSpinnerAdapter stateSpinnerAdapter=new CustomSpinnerAdapter(CreateShipmentFragment.this,state_list);
        spinnerShipFromState.setAdapter(stateSpinnerAdapter);
        spinnerShipToState.setAdapter(stateSpinnerAdapter);
    }
}
