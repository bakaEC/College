package org.uestc1123.javatest;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/hksn.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/hwxs.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/hwxk.ttf");

        TextView textView1= findViewById(R.id.textView1);
        textView1.setText("赠汪伦");
        textView1.setTextColor(0xFFEFDAB7);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,36);
        textView1.setTypeface(tf1,Typeface.BOLD);

        TextView textView2= findViewById(R.id.textView2);
        textView2.setText("[唐] 李白");
        textView2.setTextColor(0xFFD37582);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        textView2.setTypeface(tf2,Typeface.ITALIC);

        TextView textView3= findViewById(R.id.textView3);
        textView3.setText("李白乘舟将欲行，");
        textView3.setTextColor(0xFF5A3939);
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView3.setTypeface(tf3,Typeface.BOLD);

        TextView textView4= findViewById(R.id.textView4);
        textView4.setText("忽闻岸上踏歌声。");
        textView4.setTextColor(0xFF5A3939);
        textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView4.setTypeface(tf3,Typeface.BOLD);

        TextView textView5= findViewById(R.id.textView5);
        textView5.setText("桃花潭水深千尺，");
        textView5.setTextColor(0xFF935F13);
        textView5.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView5.setTypeface(tf3,Typeface.BOLD);

        TextView textView6= findViewById(R.id.textView6);
        textView6.setText("不及汪伦送我情。");
        textView6.setTextColor(0xFF935F13);
        textView6.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView6.setTypeface(tf3,Typeface.BOLD);
    }
}