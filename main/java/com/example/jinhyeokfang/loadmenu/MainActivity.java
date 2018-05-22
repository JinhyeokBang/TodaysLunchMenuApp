package com.example.jinhyeokfang.loadmenu;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String[] COUNTRIES = new String[] { "Greece", "Italy", "France", "Spain", "Germany", "Poland", "Romania", "Turkey", "Switzerland", "Netherlands", "Luxembourg", "Ukrain" };

    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    public List<String> allMenu = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(mClickListener);




    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray cast = new JSONArray(response);

                        listView = (ListView) findViewById( R.id.listView );
                        ArrayList<String> planetList = new ArrayList<String>();
                        listAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.simplerow, planetList);

                        for (int i=0; i<cast.length(); i++) {
                            JSONObject menuJson = cast.getJSONObject(i);
                            String name = menuJson.getString("menu");
                            allMenu.add(name);
                            listAdapter.add(name);
                        }

                        listView.setAdapter( listAdapter );
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            LoadMenu loadMenu = new LoadMenu(responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loadMenu);
        }
    };
}
