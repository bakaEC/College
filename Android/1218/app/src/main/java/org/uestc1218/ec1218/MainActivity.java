package org.uestc1218.ec1218;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setBackgroundResource(Color.parseColor("#000000"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);

        +
        ImageView imageView1 = new ImageView(this);
        imageView1.setId(0);
        LinearLayout.LayoutParams layout_2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_2.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layout_2.height = 311;
        imageView1.setImageResource(R.drawable.iconhead);
        imageView1.setLayoutParams(layout_2);
        linearLayout_1.addView(imageView1);

        TextView textView2 = new TextView(this);
        textView2.setId(1);
        textView2.setTextColor(Color.parseColor("#eaeaea"));
        textView2.setTextSize((22/getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
        textView2.setText("出租 | 温江大学城 地铁口全业态招");
        LinearLayout.LayoutParams layout_716 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_716.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layout_716.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_716.leftMargin = 30;
        layout_716.topMargin = 20;
        textView2.setLayoutParams(layout_716);
        linearLayout_1.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setId(2);
        textView3.setTextSize((22/getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
        textView3.setTextColor(Color.parseColor("#eaeaea"));
        textView3.setText("商中餐 KTV 健身房 桌游等");
        LinearLayout.LayoutParams layout_143 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_143.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layout_143.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_143.leftMargin = 30;
        layout_143.topMargin = 8;
        textView3.setLayoutParams(layout_143);
        linearLayout_1.addView(textView3);
        LinearLayout linearLayout_810 = new LinearLayout(this);
        linearLayout_810.setOrientation(0);
        LinearLayout.LayoutParams layout_24 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_24.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layout_24.height = 104;
        layout_24.topMargin = 12;
        linearLayout_810.setLayoutParams(layout_24);

        TextView textView4 = new TextView(this);
        textView4.setId(3);
        textView4.setGravity(Gravity.CENTER);
        textView4.setText("温江-南熏大道明信城商铺");
        textView4.setTextColor(Color.parseColor("#eaeaea"));
        textView4.setTextSize((18/getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
        LinearLayout.LayoutParams layout_416 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_416.width = 271;
        layout_416.height = 37;
        layout_416.leftMargin = 30;
        layout_416.weight = 1;
        textView4.setLayoutParams(layout_416);
        linearLayout_810.addView(textView4);

        ImageView imageView2 = new ImageView(this);
        imageView2.setId(4);
        LinearLayout.LayoutParams layout_23 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_23.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_23.height = 31;
        layout_23.weight = 1;
        imageView2.setImageResource(R.drawable.icon01);
        imageView2.setLayoutParams(layout_23);
        linearLayout_810.addView(imageView2);
        linearLayout_1.addView(linearLayout_810);

    }
}