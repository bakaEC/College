package org.u210316.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener {
    private TextView txt;
    private Button btn;
    private CheckBox checkBox;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.text);
        txt.setOnClickListener(this);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(this);
        checkBox.setOnClickListener(this);
        radioGroup=(RadioGroup)findViewById(R.id.rg);

    }

    @Override
    public void onClick(View v) {
        if(v==txt){
            Toast.makeText(this,"你按了文本框", Toast.LENGTH_SHORT).show();
        }else if (v==btn){
            Toast.makeText(this,"你按了按钮", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this,"你按了多选按钮", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //CompoundButton - 选择性按钮，子类：CheckBox,RadioButton,Switch
        //isChecked表示状态-打勾、打开
        Toast.makeText(this,isChecked?"你选择了我":"你没有选择我", Toast.LENGTH_SHORT).show();


    }
}