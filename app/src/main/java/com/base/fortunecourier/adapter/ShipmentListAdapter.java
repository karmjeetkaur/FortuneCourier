package com.base.fortunecourier.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.base.fortunecourier.R;
import java.util.ArrayList;

public class ShipmentListAdapter extends RecyclerView.Adapter<ShipmentListAdapter.Holder>
{
    ArrayList<String> stringArrayList;
    Activity m_activity;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shiping_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.tv_companyName.setText(stringArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_companyName;

        public Holder(View itemView) {
            super(itemView);
            tv_companyName = (TextView) itemView.findViewById(R.id.tv_companyName);
        }
    }

    public ShipmentListAdapter() {
    }

    public ShipmentListAdapter(ArrayList<String> arrayList, Activity activity) {

        this.stringArrayList = arrayList;
        m_activity = activity;
    }

}

