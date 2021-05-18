package org.u210316.weightcaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText input;
    private RadioButton radioButtonMan;
    private RadioButton radioButtonWoman;
    private Button button;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.input);
        button = (Button)findViewById(R.id.button);
        radioButtonMan=(RadioButton)findViewById(R.id.radButtonMan);
        radioButtonWoman=(RadioButton)findViewById(R.id.radButtonWoman);
        textViewResult=(TextView)findViewById(R.id.textViewResult);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int height=Integer.parseInt(input.getText().toString());
        double result = 0;
        if (radioButtonMan.isChecked()){
            result=(height-100)*0.9;
        }else if (radioButtonWoman.isChecked()){
            result=(height-100)*0.9-2.5;
        }
        textViewResult.setText("您的标准体重应该是："+result+"KG");
    }
}