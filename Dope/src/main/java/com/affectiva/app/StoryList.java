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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StoryList extends Activity {

    private static String TAG = StoryList.class.getSimpleName();

    //http://api.androidhive.info/json/movies.json
    private String urlJsonArry = "http://api.androidhive.info/json/movies.json";
    ListView mListView;
    private List<News> newsList = new ArrayList<News>();
    private ProgressDialog progDialog;
    private newsListAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylist);

        //neelam changes starts
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String jsonString = getIntent().getStringExtra("jsonObjectString");
            try {

                JSONObject jsonObj = new JSONObject(jsonString);
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

        makeJsonRequest();




       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               News Slecteditem = newsList.get(+position);
               Toast.makeText(getApplicationContext(), Slecteditem.getTitle(), Toast.LENGTH_LONG).show();
              // Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
               Log.d("Neelam get title:",Slecteditem.getTitle());

               Intent intent = new Intent(StoryList.this, LoadStoryDetails.class);
               intent.putExtra("title", Slecteditem.getTitle());
               startActivity(intent);
           }

       });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideDialog();
    }

    public void makeJsonRequest() {
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
                                news.setTitle(obj.getString("title"));
                                news.setExcerpt(obj.getString("rating"));
                                news.setImage(obj.getString("image"));

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
        });

        // Adding request to request queue
        HttpHandler.getInstance().addToRequestQueue(movieReq);
    }

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StoryList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.affectiva.app/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StoryList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.affectiva.app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
