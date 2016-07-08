package com.example.root.screentestapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by root on 06-07-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse {

    public String title;
    public String link;
    public String pubDate;

    public ReportResponse(){

    }
}
