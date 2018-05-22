package com.example.jinhyeokfang.loadmenu;

import android.util.Log;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoadMenu extends StringRequest {
    static Date currentTime = Calendar.getInstance().getTime();
    static int month = currentTime.getMonth() + 1;
    static int date = currentTime.getDate();


    final static private String URL = "https://today-menu-server.herokuapp.com/api";
    private Map<String, String> parameters;

    public LoadMenu(Response.Listener<String> listener, int time) {
        super(Method.GET, URL+'/'+time+'/'+Integer.toString(month)+"/"+Integer.toString(date), listener, null);
        parameters = new HashMap<String, String>();
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
