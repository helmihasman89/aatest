package com.airasia.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.airasia.test.adapter.EngineerAdapter;
import com.airasia.test.exception.ScheduleException;
import com.airasia.test.modules.Engineer;
import com.airasia.test.modules.Schedule;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private List<Engineer> engineerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EngineerAdapter eAdapter;
    Button gen_schedule_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        gen_schedule_btn = findViewById(R.id.gen_schedule_btn);

        eAdapter = new EngineerAdapter(engineerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(eAdapter);

        getEngineerData();

        gen_schedule_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, SchedulesActivity.class);
                //intent.putExtra("engineer_list", engineerList.toString());
                intent.putExtra("engineer_list", (Serializable) engineerList);
                startActivity(intent);

            }
        });
    }

    private void getEngineerData() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://aatest-1581298142564.appspot.com/api/v1/engineers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        System.out.println("string respose---");
                        System.out.println(response);

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray();
                            jsonArray = obj.getJSONArray("engineers");

                            Engineer engineer;
                            System.out.println("------engineerss-----");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);

                                engineer = new Engineer(row.getString("id"),row.getString("name"),"");
                                engineerList.add(engineer);

                            }

                            eAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
