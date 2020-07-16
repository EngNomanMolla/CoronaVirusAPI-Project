package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView totalCasesTextView;
    List<ModelClass> arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalCasesTextView=findViewById(R.id.totalCaseId);
            getUserList();

    }

    private void getUserList() {
        try{

            ApiServics servics=ApiClint.getRetrofit().create(ApiServics.class);
            Call<ModelClass> call=servics.getData();
            call.enqueue(new Callback<ModelClass>() {
                @Override
                public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                    if(response.isSuccessful()){
                        List<ModelClass> userList= (List<ModelClass>) response.body();
                        int cases=  userList.get(0).getCases();
                       totalCasesTextView.setText(cases);

                    }
                    else {


                    }

                }


                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {

                }
            });


        }catch(Exception ex){




        }
    }
}
