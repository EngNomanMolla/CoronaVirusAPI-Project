package com.example.restapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalCasesTextView,todayCasesTextView,totalDeathsTextView,todayDeathsTextView,totalRecoverdTextView,
    activeCasesTextView,CriticalTextView,CPOMTextView,DPOMTextView,totalTestsTextView,TPOMTextView;
    List<ModelClass> arraylist;
     DialogeClass dialogeClass=new DialogeClass(MainActivity.this);


    private boolean isConnected(){
          ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
          return  networkInfo!=null && networkInfo.isConnected();

      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);
        totalCasesTextView=findViewById(R.id.totalCaseId);
        todayCasesTextView=findViewById(R.id.todayCaseId);
        totalDeathsTextView=findViewById(R.id.totalDeathsId);
        todayDeathsTextView=findViewById(R.id.todayDeathsId);
        totalRecoverdTextView=findViewById(R.id.totalRecoverdId);
        activeCasesTextView=findViewById(R.id.activeCasesId);
        CriticalTextView=findViewById(R.id.criticalId);
        CPOMTextView=findViewById(R.id.casesPerOneMillionId);
        DPOMTextView=findViewById(R.id.deathsPerOneMillionId);
        totalTestsTextView=findViewById(R.id.totalTestsId);
        TPOMTextView=findViewById(R.id.testsPerOneMillionId);

          this.setTitle("Bangladesh Corona Update");

           boolean test=true;
          if(!isConnected()){

              test=false;

              noInternetDialoge();

        }

           if(test==true){
              // Loading();
           }

            getUserList();
    }

    private void noInternetDialoge() {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Internet Connection Alert!");
        alert.setIcon(R.drawable.ic_wifi);
        alert.setCancelable(false);
        alert.setMessage("Please Cheak your Internet Connection..");
        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alert.show();
    }

   /* private void Loading() {
        dialogeClass.dialogeMethod();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogeClass.dismissMethod();
            }
        },3000);
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finishAffinity();
        }
        if(item.getItemId()==R.id.menuId){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
          View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.details,null);
          builder.setView(view);
          builder.setCancelable(false);
          builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
              }
          });
          AlertDialog dialog=builder.create();
          dialog.show();


        }
        return super.onOptionsItemSelected(item);
    }

    private void getUserList() {
        try{
            ApiServics servics=ApiClint.getRetrofit().create(ApiServics.class);
            Call<ModelClass> call=servics.getData();
            dialogeClass.dialogeMethod();
            call.enqueue(new Callback<ModelClass>() {
                @Override
                public void onResponse(Call<ModelClass> call, final Response<ModelClass> response) {

                    if(response.isSuccessful()){
                                dialogeClass.dismissMethod();
                                totalCasesTextView.setText(String.valueOf(response.body().getCases()));
                                todayCasesTextView.setText(String.valueOf(response.body().getTodayCases()));
                                totalDeathsTextView.setText(String.valueOf(response.body().getDeaths()));
                                todayDeathsTextView.setText(String.valueOf(response.body().getTodayDeaths()));
                                totalRecoverdTextView.setText(String.valueOf(response.body().getRecovered()));
                                activeCasesTextView.setText(String.valueOf(response.body().getActive()));
                                CriticalTextView.setText(String.valueOf(response.body().getCritical()));
                                CPOMTextView.setText(String.valueOf(response.body().getCasesPerOneMillion()));
                                DPOMTextView.setText(String.valueOf(response.body().getDeathsPerOneMillion()));
                                totalTestsTextView.setText(String.valueOf(response.body().getTotalTests()));
                                TPOMTextView.setText(String.valueOf(response.body().getTestsPerOneMillion()));
                    }
                }


                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {
                    dialogeClass.dismissMethod();
                    noInternetDialoge();

                }
            });


        }catch(Exception ex){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }





    @Override
    public void onClick(View v) {

    }
}
