package org.u21.a0421netcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.i("TEST","onTouchEvent:Down X="+x+"Y="+y);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TEST","onTouchEvent:Move X="+x+"Y="+y);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TEST","onTouchEvent:UP X="+x+"Y="+y);
                break;
        }
        return false;
    }
}