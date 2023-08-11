package com.example.my_app;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class CommonMethod {
    CommonMethod(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    CommonMethod(View view,String message){
        Snackbar.make(view, message, Toast.LENGTH_SHORT).show();
    }

    CommonMethod(Context context,Class<?> nextclass){
        Intent intent = new Intent(context,nextclass);
        context.startActivity(intent);
    }

}
