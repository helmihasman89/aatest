package com.airasia.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airasia.test.R;
import com.airasia.test.modules.Engineer;

import java.util.List;

public class EngineerAdapter extends RecyclerView.Adapter<EngineerAdapter.MyViewHolder> {

    private List<Engineer> engineerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView engineer_name;

        public MyViewHolder(View view) {
            super(view);
            engineer_name = (TextView) view.findViewById(R.id.engineer_name);
        }
    }


    public EngineerAdapter(List<Engineer> moviesList) {
        this.engineerList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.engineer_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Engineer engineer = engineerList.get(position);
        holder.engineer_name.setText(engineer.getName());

    }

    @Override
    public int getItemCount() {
        return engineerList.size();
    }
}
