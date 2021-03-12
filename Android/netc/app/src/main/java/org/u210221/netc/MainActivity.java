package org.u210221.netc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        //操作空间
        //设置字体大小
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        //设置字体颜色
        textView1.setTextColor(0xffff0000);
        //设置字体


    }
}