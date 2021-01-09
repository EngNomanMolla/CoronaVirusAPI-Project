package com.example.restapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class DialogeClass  {
      AlertDialog alertDialog;
    Activity activity;
    public DialogeClass(Activity activity) {
        this.activity=activity;
    }


    public void dialogeMethod(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
       View view= LayoutInflater.from(activity).inflate(R.layout.sample_view,null);
       builder.setView(view);
       alertDialog=builder.create();
       alertDialog.show();
       alertDialog.setCancelable(false);

    }
    public void dismissMethod(){
        alertDialog.dismiss();
    }
}
