package com.bgeiotdev.eval.Others;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;


public class NetworkTask extends AsyncTask<Void, Void, Boolean> {

    Context context;
    Class<?> context2;
    Window window;
    ProgressBar progressBar;

    public NetworkTask(Context context, Class<?> context2, Window window, ProgressBar progressBar){
        this.context = context;
        this.context2 = context2;
        this.window = window;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((Activity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (ConnectivityHelper.isConnectedToNetwork(context)) {
            Intent intent = new Intent(context, context2);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if(!aBoolean){
            Toast toast = Toast.makeText(context, "Vous devez être connecté à internet pour y accéder", Toast.LENGTH_SHORT);
            toast.show();
        }
        progressBar.setVisibility(View.GONE);
    }
}
