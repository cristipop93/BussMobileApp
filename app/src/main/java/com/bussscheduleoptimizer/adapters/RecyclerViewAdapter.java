package com.bussscheduleoptimizer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bussscheduleoptimizer.R;
import com.bussscheduleoptimizer.model.Result;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Result> mResults = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Result> mResults) {
        this.mResults = mResults;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        final Result result = mResults.get(i);
        viewHolder.image.setImageResource(result.getVehicleType().getIconId());
        viewHolder.buss.setText(result.getBusId());
        viewHolder.delay.setText(result.getDelay());
        viewHolder.arriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: arrived");
                Toast.makeText(mContext, "arrived", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: parentLayout: " + result.getBusId());
                Toast.makeText(mContext, "parent: " + result.getBusId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView buss;
        TextView delay;
        ImageButton arriveButton;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.bus_icn);
            buss = itemView.findViewById(R.id.bus);
            delay = itemView.findViewById(R.id.delay);
            arriveButton = itemView.findViewById(R.id.btn_arrived);
            parentLayout = itemView.findViewById(R.id.item_parent);
        }
    }
}