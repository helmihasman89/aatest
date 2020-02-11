package com.airasia.test.modules;

import androidx.annotation.NonNull;

import com.airasia.test.util.GeneralUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.google.common.base.Strings.isNullOrEmpty;

public class ShiftEngineer implements Serializable {

    //The unique id of the engineer.
    private final int day;

    //The name of the engineer.
    private final String engineer_name1;

    //The profile picture of the engineer.
    @SerializedName("profilePic")
    private final String engineer_name2;


    /**
     * Use this constructor to create a new engineer.
     * @param day
     * @param engineer_name1
     * @param engineer_name2
     */
    public ShiftEngineer( int day, String engineer_name1, String engineer_name2) {
        this.day = day;
        this.engineer_name1 = engineer_name1;
        this.engineer_name2 = engineer_name2;

    }

    /**
     * Get the unique id of the engineer.
     *
     * @return The unique id of the engineer.
     */
    public int getDay() {
        return day;
    }


    /**
     * Get the name of the engineer.
     *
     * @return The name of the engineer.
     */
    public String getEngineer_name1() {
        return engineer_name1;
    }


    /**
     * Get the profile picture of the engineer.
     *
     * @return The profile picture  of the engineer.
     */
    public String getEngineer_name2() {
        return engineer_name2;
    }


}
