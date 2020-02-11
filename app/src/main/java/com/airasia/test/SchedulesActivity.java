package com.airasia.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.airasia.test.adapter.EngineerAdapter;
import com.airasia.test.adapter.ScheduleAdapter;
import com.airasia.test.exception.ScheduleException;
import com.airasia.test.modules.Engineer;
import com.airasia.test.modules.Schedule;
import com.airasia.test.modules.ShiftEngineer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class SchedulesActivity extends AppCompatActivity implements Serializable {

    LinkedHashMap<String, Engineer> cachedEngineers = new LinkedHashMap<String, Engineer>();
    private List<Engineer> engineerList;
    private List<ShiftEngineer> shift_engineerList = new ArrayList<>();;
    private RecyclerView schedules_view;
    private ScheduleAdapter seAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        Intent i = getIntent();
        engineerList = (List<Engineer>) i.getSerializableExtra("engineer_list");

        for(int k=0;k<engineerList.size();k++){
            cachedEngineers.put(engineerList.get(k).getId(), new Engineer(engineerList.get(k).getId(),engineerList.get(k).getName(),engineerList.get(k).getProfilePic()));
        }

        schedules_view = findViewById(R.id.schedules_view);
        seAdapter = new ScheduleAdapter(shift_engineerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        schedules_view.setLayoutManager(mLayoutManager);
        schedules_view.setItemAnimator(new DefaultItemAnimator());
        schedules_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        schedules_view.setAdapter(seAdapter);

        getSchedules();
    }

    /**
     * Gets schedules for engineers.
     *
     * @return the schedules for engineers or empty.
     * @see ScheduleService
     */
    public void getSchedules() {
        if (cachedEngineers != null && cachedEngineers.size() > 0) {
            List<Engineer> engineers = new ArrayList<>(cachedEngineers.values());
            ScheduleService scheduleService = new ScheduleService(engineers);
            List<Schedule> schedules;
            try {
                schedules = scheduleService.getSchedules();
                for (int i=0; i<schedules.size(); i++) {
                    System.out.println(schedules.get(i).getDay());
                    System.out.println(schedules.get(i).getShiftEngineers().get(0));
                    shift_engineerList.add(new ShiftEngineer(schedules.get(i).getDay(),schedules.get(i).getShiftEngineers().get(0).getName(),schedules.get(i).getShiftEngineers().get(1).getName()));
                }

            } catch (ScheduleException e) {

            }
        }
        seAdapter.notifyDataSetChanged();
    }
}
