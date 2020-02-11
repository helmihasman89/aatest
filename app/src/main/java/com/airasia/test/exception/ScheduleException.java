package com.airasia.test.exception;

public class ScheduleException extends RuntimeException {

    // General exception
    public static final String SCHEDULE_GENERAL_EXCEPTION = "An error occurred while generating the schedule.";
    // Invalid exception
    public static final String SCHEDULE_INVALID_EXCEPTION = "Base on the given setting we can not create the schedule.";


    public ScheduleException(String message) {
        super(message);
    }

    public ScheduleException(Throwable cause) {
        super(cause);
    }

    public ScheduleException(String message, Throwable throwable) {
        super(message, throwable);
    }


}