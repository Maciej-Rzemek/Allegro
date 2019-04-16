package com.example.a2019_rozwizanie_mobilesoftwaredeveloper_torun;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView projectNameTextView, nameTextView, projectDateTextView, dateTextView;
    private static final String TAG = "MainActivity";
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectNameTextView = findViewById(R.id.project_name_text_view);
        nameTextView = findViewById(R.id.name_text_view);
        projectDateTextView = findViewById(R.id.project_date_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        layout = findViewById(R.id.constraint_layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (projectDateTextView.isShown()
                        && projectNameTextView.isShown()
                        && dateTextView.isShown()
                        && nameTextView.isShown()) {
                    projectDateTextView.setVisibility(View.INVISIBLE);
                    projectNameTextView.setVisibility(View.INVISIBLE);
                    nameTextView.setVisibility(View.INVISIBLE);
                    dateTextView.setVisibility(View.INVISIBLE);
                } else {
                    projectNameTextView.setVisibility(View.VISIBLE);
                    nameTextView.setVisibility(View.VISIBLE);
                    projectDateTextView.setVisibility(View.VISIBLE);
                    dateTextView.setVisibility(View.VISIBLE);
                }
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<List<Data>> call = jsonApi.getData();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (!response.isSuccessful()) {
                    projectNameTextView.setText(response.code());
                    return;
                }

                List<Data> list = response.body();
                Date date = list.get(0).getDate();

                Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy HH:mm").create();
                String currentDate = gson.toJson(date);

                projectNameTextView.setText(list.get(0).getName());
                projectDateTextView.setText(currentDate);
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
