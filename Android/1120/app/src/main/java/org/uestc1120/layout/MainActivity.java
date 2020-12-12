package org.uestc1120.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bd, bm, ba,bp, d, mc, mp, mm, mr, c, mu, equl;
    ImageButton del;
    private EditText editText, editText2,editText3;
    StringBuffer stb = new StringBuffer();
    float x = 0,temp=0,mem=0;
    int flag = 0; //1+\2-\3*\4÷\5%
    public Boolean isCalculated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);

        bm = findViewById(R.id.bm);
        ba = findViewById(R.id.ba);
        d = findViewById(R.id.d);
        equl = findViewById(R.id.bequl);
        mc = findViewById(R.id.mc);
        mp = findViewById(R.id.mp);
        mm = findViewById(R.id.mm);
        mr = findViewById(R.id.mr);
        c = findViewById(R.id.c);
        mu = findViewById(R.id.mu);
        bd = findViewById(R.id.bd);
        bp = findViewById(R.id.bp);

        del = findViewById(R.id.del);


    }

    @SuppressLint("SetTextI18n")
    public void press(View v) {

        if (v == b0) {

            stb.append("0");
            editText.setText(stb);
        }
        if (v == b1) {
            stb.append("1");
            editText.setText(stb);
        }
        if (v == b2) {
            stb.append("2");
            editText.setText(stb);
        }
        if (v == b3) {
            stb.append("3");
            editText.setText(stb);
        }
        if (v == b4) {
            stb.append("4");
            editText.setText(stb);
        }
        if (v == b5) {
            stb.append("5");
            editText.setText(stb);
        }
        if (v == b6) {
            stb.append("6");
            editText.setText(stb);
        }
        if (v == b7) {
            stb.append("7");
            editText.setText(stb);
        }
        if (v == b8) {
            stb.append("8");
            editText.setText(stb);
        }
        if (v == b9) {
            stb.append("9");
            editText.setText(stb);
        }

        if (v == bd) {
            stb.append(".");
            editText.setText(stb);
        }


        if (v == del) {
            if (stb.length() != 0) {
                stb.delete(stb.length() - 1, stb.length());
            }
            editText.setText(stb);
        }
        if (v == c) {
            stb.delete(0, stb.length());
            x = 0;
            flag = 0;
            temp=0;
            editText.setText(stb);
            editText2.setText("");
        }

        if (v==bp){
            x = Float.parseFloat(stb.toString())/100;
            stb.delete(0,stb.length());
            stb.append(x);
            editText.setText(stb);
        }

        if (v == ba) {
            if (stb.length() != 0) {
                x += Float.parseFloat(stb.toString());
                editText2.setText(DeleteZero(Float.toString(x)));
                editText.setText("+");
                flag = 1;
                stb.delete(0, stb.length());
//                if (isCalculated){
//                    if (stb.length()!=0) {
//                        x = equal(1, temp, Float.parseFloat(stb.toString()));
//                    }else {
//                        x=temp;
//                    }
//                    editText2.setText(DeleteZero(Float.toString(x)));
//                    editText.setText("");
//                    System.out.println("isCaculated");
//               }

            }
        }
        if (v == bm) {
            if (stb.length() != 0) {
                if (x!=0) {
                    x -= Float.parseFloat(stb.toString());
                }else {
                    x = Float.parseFloat(stb.toString());
                }
                editText2.setText(DeleteZero(Float.toString(x)));
                editText.setText("-");
                flag = 2;
                stb.delete(0, stb.length());
            }
        }
        if (v == mu) {
            if (stb.length() != 0) {
                if (x != 0) {
                    x *= Float.parseFloat(stb.toString());
                }else {
                    x = Float.parseFloat(stb.toString());
                }
                editText2.setText(DeleteZero(Float.toString(x)));
                editText.setText("×");
                flag = 3;
                stb.delete(0, stb.length());
            }
        }
        if (v == d) {
            if (stb.length() != 0) {
                if (x != 0) {
                    x /= Float.parseFloat(stb.toString());
                }else {
                    x = Float.parseFloat(stb.toString());
                }
                editText2.setText(DeleteZero(Float.toString(x)));
                editText.setText("÷");
                flag = 4;
                stb.delete(0, stb.length());
            }
        }


        if (v == equl) {
            temp=equal(flag,x,Float.parseFloat(stb.toString()));
            String resultp = DeleteZero(Float.toString(temp));
            editText.setText(resultp);
            editText2.setText("");
            flag = 0;
            isCalculated=true;
            stb.delete(0, stb.length());
            stb.append(temp);
            System.out.println(temp);
        }



        if(v==mp){
            mem+=Float.parseFloat(stb.toString());
            editText3.setText("M="+DeleteZero(String.valueOf(mem)));
        }
        if(v==mm){
            mem-=temp;
            editText3.setText("M="+DeleteZero(String.valueOf(mem)));
        }
        if(v==mc){
            mem=0;
            editText3.setText("");
        }
        if(v==mr){
            stb.delete(0,stb.length());
            stb.append(mem);
            editText.setText(DeleteZero(stb.toString()));
        }
    }

    private String DeleteZero(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(str.length() - 1).equals("0")) {
                str = str.substring(0, str.length() - 1);
                if (str.substring(str.length() - 1).equals(".")) {
                    str = str.substring(0, str.length() - 1);
                } else {
                    str = str + "0";
                }
            } else {
                return str;
            }
        }
        return str;
    }
    
    private float equal(int flag,float x,float y){
        float result = 0;
            switch (flag) {
                case 0:
                    result = y;
                    break;
                case 1:
                    result = x + y;
                    break;
                case 2:
                    result = x - y;
                    break;
                case 3:
                    result = x * y;
                    break;
                case 4:
                    result = x / y;
                    break;
            }
            return result;
    }
    //http://github.com/bakaEC
}
