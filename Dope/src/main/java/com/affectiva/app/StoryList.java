package com.affectiva.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.Header;

public class StoryList extends Activity {

    private static String TAG = StoryList.class.getSimpleName();

    //http://api.androidhive.info/json/movies.json
    private String url = "http://192.168.1.67:3000/news";
    ListView mListView;
    private List<News> newsList = new ArrayList<News>();
    private ProgressDialog progDialog;
    private ProgressDialog pDialog;
    private newsListAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    String KEY_EMOTION = "";
    String KEY_DEGREE = "";
    String KEY_AGE = "";
    String KEY_ETHNICITY = "";
    String jsonString;
    JSONObject jsonObj;
    String sessionToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylist);

        //neelam changes starts
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             jsonString = getIntent().getStringExtra("jsonObjectString");
            try {

                jsonObj = new JSONObject(jsonString);
                KEY_EMOTION = jsonObj.getString("emotion");
                KEY_DEGREE = jsonObj.getString("degree");
                KEY_AGE = jsonObj.getString("age");
                KEY_ETHNICITY = jsonObj.getString("ethnicity");

                Log.d("Json Received:", jsonObj.toString());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //neelam changes ends


        mListView = (ListView) findViewById(R.id.listView);
        adapter = new newsListAdapter(this, newsList);
        mListView.setAdapter(adapter);

        progDialog = new ProgressDialog(this);
        progDialog.setMessage("LoadStoryDetail...");
        progDialog.setCancelable(false);
//
//        try {
//            url =url +  URLEncoder.encode("{\"emotion\":\"" + KEY_EMOTION + "\",\"degree\":\"" + KEY_DEGREE + "\",\"age\":\"" + KEY_AGE + "\",\"ethnicity\":\"" + KEY_ETHNICITY +"\"}", "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        makeJsonRequest();
       // jsonRequest();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               News Slecteditem = newsList.get(+position);
               Toast.makeText(getApplicationContext(), Slecteditem.getTitle(), Toast.LENGTH_LONG).show();
              // Toast.makeText(getApplicationContext(), Slecteditem.getDetail(), Toast.LENGTH_LONG).show();
              // Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
               Log.d("Neelam get newsID:",Slecteditem.getNewsID());
               Log.d("Neelam get details:",Slecteditem.getDetail());

               Intent intent = new Intent(StoryList.this, LoadStoryDetails.class);
               intent.putExtra("newsID", Slecteditem.getNewsID());
               intent.putExtra("details", Slecteditem.getDetail());
               startActivity(intent);
           }

       });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideDialog();
    }


    public void makeJsonRequest(){
        showDialog();
        JsonArrayRequest newsReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hideDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                News news = new News();
                                news.setNewsID(obj.getString("newsID"));
                                news.setTitle(obj.getString("title"));
                                news.setDetail(obj.getString("details"));
                                news.setExcerpt(obj.getString("excerpt"));
                                news.setPublisher(obj.getString("publisher"));
                                news.setDate(obj.getString("date"));
                                news.setImage(obj.getString("imageURL"));

                                // adding movie to movies array
                                newsList.add(news);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideDialog();

            }
        });

        // Adding request to request queue
        HttpHandler.getInstance().addToRequestQueue(newsReq);
    }





   /* public void makeJsonRequest() {
        showDialog();
        JsonArrayRequest movieReq = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Respose", response.toString());
                        hideDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            // Log.d("respose")
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                News news = new News();
                                news.setTitle(obj.getString("newsID"));
                                news.setExcerpt(obj.getString("title"));
                                news.setImage(obj.getString("imageURL"));

                                Log.d("obj", obj.toString());

                                // adding movie to movies array
                                newsList.add(news);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:", error.getMessage());
                hideDialog();

            }
        }
        ); // Request body ends

        // Adding request to request queue
        HttpHandler.getInstance().addToRequestQueue(movieReq);
    } */


//    public void jsonRequest() {
//        Map<String, String> postParam= new HashMap<String, String>();
//        postParam.put("emotion", KEY_EMOTION);
//        postParam.put("degree", KEY_DEGREE);
//        postParam.put("age", KEY_AGE);
//        postParam.put("ethnicity", KEY_ETHNICITY);
//
////        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), new Response.Listener</*JSONArray*/JSONObject>() {
////
////            @Override
////            public void onResponse(JSONObject response) {
////                Log.d(TAG, response.toString());
////
////                try {
////                    JSONArray arr = response.getJSONArray("News");
////                    for (int i = 0; i < response.length(); i++) {
////                        //try {
////                        JSONObject obj = arr.getJSONObject(i);
////                        News news = new News();
////                        news.setNewsID(obj.getString("newsID"));
////                        news.setTitle(obj.getString("title"));
////                        news.setDetail(obj.getString("details"));
////                        news.setExcerpt(obj.getString("excerpt"));
////                        news.setPublisher(obj.getString("publisher"));
////                        news.setDate(obj.getString("date"));
////                        news.setImage(obj.getString("imageURL"));
////
////                        // adding movie to movies array
////                        newsList.add(news);
////
////                        /*} catch (JSONException e) {
////                            e.printStackTrace();
////                        }*/
////
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////
////
////        }, new Response.ErrorListener(){
////
////            @Override
////            public void onErrorResponse(VolleyError volleyError) {
////                Toast.makeText(StoryList.this,volleyError.toString(),Toast.LENGTH_LONG).show();
////
////                Log.d("neelam erro respone:",volleyError.toString());
////            }
////        }
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(StoryList.this,response,Toast.LENGTH_LONG).show();
//                        Log.d("neelam1",response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        try {
//                            String responseBody = new String( error.networkResponse.data, "utf-8" );
//                            JSONObject jsonObject = new JSONObject( responseBody );
//                        } catch ( JSONException e ) {
//                            //Handle a malformed json response
//                        } catch (UnsupportedEncodingException error2){
//
//                        }
//
//                        Toast.makeText(StoryList.this,error.toString(),Toast.LENGTH_LONG).show();
//                        Log.d("neelam1", error.toString());
//                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String, String> postParam= new HashMap<String, String>();
//                postParam.put("emotion", "Happy");
//                postParam.put("degree", "smileDegree");
//                postParam.put("ethnicity", "ethnicity");
//                return postParam;
//            }
//
//        };
//
//       HttpHandler.getInstance().addToRequestQueue(stringRequest);
//    }


    private void showDialog() {
        if (!progDialog.isShowing())
            progDialog.show();
    }

    private void hideDialog() {
        if (progDialog.isShowing())
            progDialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
