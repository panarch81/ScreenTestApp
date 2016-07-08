package com.example.root.screentestapp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by root on 06-07-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse {

    public String title;
    public List<ReportResponse> item;
    public NewsResponse(){

    }

}
