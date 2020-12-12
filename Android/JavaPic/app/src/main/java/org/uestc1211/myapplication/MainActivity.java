package org.uestc1211.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textView);
        imageView= findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.a00);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.a02);
        imageView.setImageBitmap(bmp);
        Drawable drawable = getResources().getDrawable(R.drawable.a03);

        button=findViewById(R.id.button);
        button.setText("年末的歌吹啊工资");
        button.setTextColor(0xff00ff00);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);



    }
}