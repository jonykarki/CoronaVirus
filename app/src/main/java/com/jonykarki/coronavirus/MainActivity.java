package com.jonykarki.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private NotificationCompat.Builder notification_builder;
    private NotificationManager notification_manager;
    private TextView total_cases_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_cases_tv = (TextView) findViewById(R.id.totalcases);
        total_cases_tv.setText("Loading....");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://coronavirus-19-api.herokuapp.com/all";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            total_cases_tv.setText(response.getString("cases"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        total_cases_tv.setText("Internet Khai?");
                        error.printStackTrace();
                    }
                });

        queue.add(jsonObjectRequest);

//        MyTimerTask myTask = new MyTimerTask();
//        Timer myTimer = new Timer();
//
//        myTimer.schedule(myTask, 5000, 60000);
    }

//    class MyTimerTask extends TimerTask {
//        public void run() {
//            generateNotification(getApplicationContext());
//        }
//    }
//
//    private void generateNotification(Context context) {
//
//        long when = System.currentTimeMillis();
//        notification_manager = (NotificationManager) this
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String chanel_id = "3000";
//            CharSequence name = "Channel Name";
//            String description = "Chanel Description";
//            int importance = NotificationManager.IMPORTANCE_LOW;
//            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
//            mChannel.setDescription(description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.BLUE);
//            notification_manager.createNotificationChannel(mChannel);
//            notification_builder = new NotificationCompat.Builder(this, chanel_id);
//        } else {
//            notification_builder = new NotificationCompat.Builder(this);
//        }
//        notification_builder.setSmallIcon(R.drawable.ic_notify)
//                .setContentTitle("Total Cases")
//                .setContentText(getTotalCases())
//                .setAutoCancel(true);
//        notification_manager.notify((int) when, notification_builder.build());
//
//      }


//    public String getTotalCases(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "https://coronavirus-19-api.herokuapp.com/all";
//        final String[] return_val = new String[]{};
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            return_val[0] = response.get("cases").toString();
//                        }catch (JSONException e){
//                            return_val[0] = "Internet Khai?";
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                        return_val[0] = "Internet Khai?";
//                        error.printStackTrace();
//                    }
//                });
//
//        queue.add(jsonObjectRequest);
//        return return_val[0];
//    }

}
