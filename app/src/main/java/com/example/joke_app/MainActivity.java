package com.example.joke_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue ;
    String url ="https://official-joke-api.appspot.com/random_joke";
    TextView text_jokes,text_id,text_setup,text_type,text_punchLine;
    ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        text_jokes = findViewById(R.id.textJokes);
        text_id = findViewById(R.id.textID);
        text_type = findViewById(R.id.textType);
        text_setup = findViewById(R.id.textSetup);
        text_punchLine = findViewById(R.id.textPunchLine);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.INVISIBLE);

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

    public void getJokes(View view) {
        progressBar3.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int ID = 0;
                String Setup,Type,PunchLine;

                try {
                    ID = response.getInt("id");
                    Type = response.getString("type");
                    Setup = response.getString("setup");
                    PunchLine = response.getString("punchline");

                    Joke joke = new Joke(ID,Type,Setup,PunchLine);

                    text_id.setText(joke.getId()+"");
                    text_id.setVisibility(View.VISIBLE);

                    text_type.setText(joke.getType()+"");
                    text_type.setVisibility(View.VISIBLE);

                    text_setup.setText(joke.getSetup()+"");
                    text_setup.setVisibility(View.VISIBLE);

                    text_punchLine.setText(joke.getPunchLine()+"");
                    text_punchLine.setVisibility(View.VISIBLE);

                    progressBar3.setVisibility(View.INVISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                text_jokes.setText("Response: " + ID);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                text_jokes.setText("Can not get data: " + error.toString());

            }
        });

        queue.add(jsonObjectRequest);
    }

}
