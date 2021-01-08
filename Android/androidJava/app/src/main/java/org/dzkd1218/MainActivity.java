package org.dzkd1218;

import android.os.Bundle;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class MainActivity extends Activity {
	//界面的线性布局对象
	private LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        
        //实例化这个线性布局
        linear=(LinearLayout) findViewById(R.id.linear);
        //添加一个新的文本视图到线性布局里
        //使用java的方式建立一个文本视图TextView对象，并且实例化
        TextView text = new TextView(this);
        
        //android:id="@+id/text"
        text.setId(1);
        
        //android:gravity="center"
        text.setGravity(Gravity.CENTER);
        
        /*android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:paddingTop="10dp"
        android:paddingBottom="10dp"*/
        //text.setPadding(left, top, right, bottom);
        text.setPadding(10, 10, 10, 10);
        
        //android:text="@string/hello_world" 
        text.setText("电子科技大学九里堤小区");
         /^[a-z][a-zA-Z0-9]{3,15}$/;
         /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
         /^((19\d{2})|(200\d{2}))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/
        //android:textColor="#FF0000"
        text.setTextColor(0xffff8811);
        
        //android:textSize="26sp"
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);
        
        //建立一个字体对象
        // android:typeface="sans"
        Typeface font=Typeface.createFromAsset(getAssets(), "kt.ttf");
        //android:textStyle="bold|italic"
        text.setTypeface(font,Typeface.BOLD_ITALIC);
        
        //android:background="@drawable/ic_launcher"
        text.setBackgroundResource(R.drawable.ic_launcher);
        
        text.setBackgroundColor(0xff0000ff);
        
        //android:drawableLeft="@drawable/ic_launcher"
        //text.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher,0,0,0);
        
        /*凡是带layout_属性，都是界面属性，不能直接设置，必须放到一个界面参数类LayoutParams对象，
         * 并且每一种布局有唯一性的界面参数类
        */
        //建立一个线性布局的界面参数类对象
        //android:layout_width="wrap_content"
        //android:layout_height="wrap_content"
               
        //LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width, height, weight)
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
       
        /*
         * android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginBottom="10dp"
         */
        //lp.setMargins(left, top, right, bottom)
        lp.setMargins(10,10,10,10);
        
        /*
         * 设置控件在屏幕中的位置android:Layou_gravity="center"
         * 对应java的实现方式
         * LayoutParams.gravity=Gravity.CENTER
         */
        lp.gravity=Gravity.CENTER;
        //控件设置界面参数LayoutParams
        text.setLayoutParams(lp);
        
        //将设置好的文本视图到线性布局里
        linear.addView(text);
        
        TextView txt = new TextView(this);
        txt.setId(1);
        txt.setText("2019安卓班");
        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
        txt.setTextColor(0xff00ff00);
        Typeface font1=Typeface.createFromAsset(getAssets(), "hwxw.TTF");
        txt.setTypeface(font1,Typeface.ITALIC);
        txt.setBackgroundColor(0x00ffff00);
        txt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_launcher,0);
        txt.setPadding(8,8,8,8);
        LinearLayout.LayoutParams lpara=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
        lpara.setMargins(20,20,20,20);
        
        //设置界面参数对象
        txt.setLayoutParams(lpara);
        //将新建的文本视图控件放到线性视图里
        linear.addView(txt);
        
        //使用java的方式添加一张图片视图
        //建立一个图片视图对象
        ImageView imgView = new ImageView(this);
        /*android:src="@drawable/ic_launcher"
         */
        imgView.setImageResource(R.drawable.ic_launcher);
        
        LinearLayout.LayoutParams lp2=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
        
        lp2.setMargins(10,10,10,10);
        lp2.gravity=Gravity.RIGHT;
        imgView.setLayoutParams(lp2);
        linear.addView(imgView);
        
        //新建一个线性布局对象
        LinearLayout linhor = new LinearLayout(this);
        //设置线性布局方向
        linhor.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp3=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 10,0,0);
        linhor.setLayoutParams(lp3);
        linhor.addView(text);
        linhor.addView(imgView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
