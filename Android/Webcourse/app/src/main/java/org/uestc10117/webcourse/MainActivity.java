package org.uestc10117.webcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private RadioGroup group1;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.btn1);
        group1=findViewById(R.id.radiog);
        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
        radio3=findViewById(R.id.radio3);
        text1=findViewById(R.id.text1);

        radio1.setChecked(true);
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case  R.id.radio1:
                        text1.setText("你选中的课程是JAVA");
                        break;
                    case  R.id.radio2:
                        text1.setText("你选中的课程是安卓");
                        break;
                    case  R.id.radio3:
                        text1.setText("你选中的课程是Cpp");
                        break;
                }
            }
        });
        Listener listener=new Listener();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TEST","点");
            }
        });
    }
    public void click(View view){
        Log.i("TEST","XML事件");
    }
    class Listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("TEST","java事件");
        }
    }
}