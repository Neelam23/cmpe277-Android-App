package com.affectiva.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neelam on 10/7/2016.
 */
public class LoadStoryDetails  extends Activity {


    private String url = "http://192.168.1.67:3000/news";
   // ListView mListView;
   // private List<News> newsList = new ArrayList<News>();
    private ProgressDialog progDialog;
    String newsID;
    String details;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadstorydetails);
        textView1 = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            newsID = getIntent().getStringExtra("newsID");
            details = getIntent().getStringExtra("details");
            Log.d("neelam title Received:", newsID);
            Log.d("neelam title Received:", details);

            textView1.setText(details);

        }
    }

//        try {
//            url =url +  URLEncoder.encode("{\"newsID\":\"" + newsID +"\"}", "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
  //      makeJsonRequest();
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        hideDialog();
//    }
//    private void showDialog() {
//        if (!progDialog.isShowing())
//            progDialog.show();
//    }
//
//    private void hideDialog() {
//        if (progDialog.isShowing())
//            progDialog.dismiss();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }

//    public void makeJsonRequest(){
//        showDialog();
//        JsonObjectRequest newsReq = new JsonObjectRequest(url,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("Detail Screen", response.toString());
//                        hideDialog();
//
//                        // Parsing json
//
//                            try {
//
//                                JSONObject obj = response.getJSONObject(newsID);
//                                News news = new News();
//                                news.setDetail(obj.getString("details"));
//
//
//                                // adding movie to movies array
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Detail Screen", "Error: " + error.getMessage());
//                hideDialog();
//
//            }
//        });
//
//        // Adding request to request queue
//        HttpHandler.getInstance().addToRequestQueue(newsReq);
//    }







}
