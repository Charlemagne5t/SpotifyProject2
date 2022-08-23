package com.example.spotifyproject.exceptionHandlers.errorDetailsClasses;

public class ErrorDetails {
    private String information;

    private String stackTraceInfo;

    public String getStackTraceInfo() {
        return stackTraceInfo;
    }


    public void setStackTraceInfo(String stackTraceInfo) {
        this.stackTraceInfo = stackTraceInfo;
    }



    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
