package com.venu.layoutbackgroundchanger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Bitmap bitmap;
    private HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.venu);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
        StrictMode.setThreadPolicy(policy);
        bitmap=getBitmapFromURL("https://www.play4deal.com/androidapi/uploads/otpposter.png");

        Drawable drawable = new BitmapDrawable(bitmap);
        relativeLayout.setBackgroundDrawable(drawable);
    }
    public  Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            System.setProperty("http.keepAlive","true");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            //connection.setUseCaches(true);
            connection.connect();
            // it increases the speed of image loading just because we used Buffered input stream
            InputStream input = new BufferedInputStream(connection.getInputStream());
            // InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            connection.disconnect();
            e.printStackTrace();
            return null;
        }
    }
}
