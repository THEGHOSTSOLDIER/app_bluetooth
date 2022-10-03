package com.example.sensorstest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class SensorListAdapter extends RecyclerView.Adapter<SensorListAdapter.SensorViewHolder> {
    private static List<Sensor> mSensorList;
    private final LayoutInflater mInflater;
    public static final String EXTRA_MESSAGE = "com.example.sensorstest";
    // constructor which get the list have generated in your main class
    public SensorListAdapter(Context context,List<Sensor> sensorList) {
        mInflater = LayoutInflater.from(context);
        this.mSensorList = sensorList;
    }

    // make the link between the layout design and your code through the adapter
    public static class SensorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView sensorItemView;
        public final TextView typeItemView;
        public final TextView vendorItemView;
        public final TextView resolutionItemView;
        public final TextView powerItemView;
        public final TextView versionItemView;

        final SensorListAdapter mAdapter;

        public SensorViewHolder(View itemView, SensorListAdapter adapter) {
            super(itemView);
            sensorItemView = itemView.findViewById(R.id.sensor);
            typeItemView = itemView.findViewById(R.id.type);
            vendorItemView = itemView.findViewById(R.id.vendor);
            resolutionItemView = itemView.findViewById(R.id.resolution);
            powerItemView = itemView.findViewById(R.id.power);
            versionItemView = itemView.findViewById(R.id.version);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition =  getAdapterPosition();
            // Use that to access the affected item in mWordList.
            Sensor sensorClicked = mSensorList.get(mPosition);
            Log.d("sensor position", String.valueOf(mPosition));
            Intent intent = new Intent (v.getContext(),MyDisplaySensor.class); // the trick : indicates v.getContext()...
            String message = String.valueOf(sensorClicked.getType());
            intent.putExtra(EXTRA_MESSAGE, message);
            v.getContext().startActivity(intent);// the trick : indicates v.getContext()...
        }


    }





    @Override // create each view of the the list
    public SensorListAdapter.SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.sensorlist_item,parent, false);
        return new SensorViewHolder(mItemView,this);

    }

    @Override //populate the view
    public void onBindViewHolder( SensorListAdapter.SensorViewHolder holder, int position) {
        String mCurrent;
        mCurrent= mSensorList.get(position).getName();
        holder.sensorItemView.setText(mCurrent);
        mCurrent= "Type :"+mSensorList.get(position).getType();
        holder.typeItemView.setText(mCurrent);
        mCurrent= "Vendor :"+mSensorList.get(position).getVendor();
        holder.vendorItemView.setText(mCurrent);
        mCurrent= "Resolution :"+mSensorList.get(position).getResolution();
        holder.resolutionItemView.setText(mCurrent);
        mCurrent= "Power :"+mSensorList.get(position).getPower();
        holder.powerItemView.setText(mCurrent);
        mCurrent= "Version :"+mSensorList.get(position).getVersion();
        holder.versionItemView.setText(mCurrent);


    }

    @Override //get the list size
    public int getItemCount() {
        return mSensorList.size();
    }



}
