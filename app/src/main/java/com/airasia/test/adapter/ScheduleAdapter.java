package com.airasia.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airasia.test.R;
import com.airasia.test.modules.Engineer;
import com.airasia.test.modules.ShiftEngineer;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private List<ShiftEngineer> engineerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView engineer_name1;
        public TextView engineer_name2;
        public TextView day;

        public MyViewHolder(View view) {
            super(view);
            engineer_name1 = view.findViewById(R.id.engineer_name1);
            engineer_name2 = view.findViewById(R.id.engineer_name2);
            day = view.findViewById(R.id.day_txt);
        }
    }


    public ScheduleAdapter(List<ShiftEngineer> moviesList) {
        this.engineerList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ShiftEngineer engineer = engineerList.get(position);
        holder.engineer_name1.setText(engineer.getEngineer_name1());
        holder.engineer_name2.setText(engineer.getEngineer_name2());
        holder.day.setText("Day "+Integer.toString(engineer.getDay()+1));

    }

    @Override
    public int getItemCount() {
        return engineerList.size();
    }
}
