package org.uestc1218.resonec;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Typeface font = Typeface.createFromAsset(getAssets(),"kt.ttf");

        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);


        TextView textView=new TextView(this);
        textView.setId(1);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10,10,10,10);
        textView.setText("UESTC");
        textView.setTextColor(0x80ff8011);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP);
        textView.setBackgroundColor(0x80ff00ff);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_launcher_foreground,0,0,0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0);
        lp.setMargins(10,10,10,10);
        lp.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp);
//        linear.addView(textView);

        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(10,10,10,10);
        lp2.gravity=Gravity.RIGHT;
        imageView.setLayoutParams(lp2);
        linear.addView(imageView);

        LinearLayout linhor = new LinearLayout(this);
        linhor.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp3.setMargins(0,10,0,0);
        linhor.addView(textView);
        linhor.addView(imageView);
        linear.addView(linhor);


    }
}