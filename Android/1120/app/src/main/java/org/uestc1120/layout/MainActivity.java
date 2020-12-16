package org.uestc1120.layout;
/*
  @Author: bakaEC | ec@bakaec.design
 * @Github: github.com/bakaEC
 * @Date: 2020-12-11
 */
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bd, bm, ba,bp, d, mc, mp, mm, mr, c, mu, equl;
    ImageButton del;
    private EditText editText, editText2,editText3;
    StringBuffer stb = new StringBuffer();
    double x = 0,temp=0,mem=0;
    int flag = 0; //1+\2-\3*\4÷\5%
    public Boolean isCalculated=false ;

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
            if (stb.length() < 35) {
                StringBufferAppend("0");
                varyDisplay(stb);
                
            }
        }
        if (v == b1) {
            if (stb.length() < 35) {
                StringBufferAppend("1");
                varyDisplay(stb);
                
            }
        }
        if (v == b2) {
            if (stb.length() < 35) {
                StringBufferAppend("2");
                varyDisplay(stb);
                
            }
        }
        if (v == b3) {
            if (stb.length() < 35) {
                StringBufferAppend("3");
                varyDisplay(stb);
                
            }
        }
        if (v == b4) {
            if (stb.length() < 35) {
                StringBufferAppend("4");
                varyDisplay(stb);
                
            }
        }
        if (v == b5) {
            if (stb.length() < 35) {
                StringBufferAppend("5");
                varyDisplay(stb);
                
            }
        }
        if (v == b6) {
            if (stb.length() < 35) {
                StringBufferAppend("6");
                varyDisplay(stb);
                
            }
        }
        if (v == b7) {
            if (stb.length() < 35) {
                StringBufferAppend("7");
                varyDisplay(stb);
                
            }
        }
        if (v == b8) {
            if (stb.length() < 35) {
                StringBufferAppend("8");
                varyDisplay(stb);
                
            }
        }
        if (v == b9) {
            if (stb.length() < 35) {
                StringBufferAppend("9");
                varyDisplay(stb);
                
            }
        }

        if (v == bd) {
            if (stb.indexOf(".")==-1&&stb.length()<35) {
                stb.append(".");
            }
            varyDisplay(stb);
            
        }


        if (v == del) {
            if (stb.length() != 0) {
                stb.delete(stb.length() - 1, stb.length());
            }
            varyDisplay(stb);
        }
        if (v == c) {
            stb.delete(0, stb.length());
            x = 0;
            flag = 0;
            temp=0;
            varyDisplay(stb);
            editText2.setText("");
        }

        if (v==bp){
            x = Double.parseDouble(stb.toString())/100;
            stb.delete(0,stb.length());
            stb.append(x);
            varyDisplay(stb);
        }

        if (v == ba) {
            if (stb.length() != 0) {
                    x += Double.parseDouble(stb.toString());
                    editText2.setText(DeleteZero(Double.toString(x)));
                    editText.setText("+");
                    flag = 1;
                    stb.delete(0, stb.length());
            }
        }
        if (v == bm) {
            if (stb.length() != 0) {
                if (x!=0) {
                    x -= Double.parseDouble(stb.toString());
                }else {
                    x = Double.parseDouble(stb.toString());
                }
                editText2.setText(DeleteZero(Double.toString(x)));
                editText.setText("-");
                flag = 2;
                stb.delete(0, stb.length());
            }
        }
        if (v == mu) {
            if (stb.length() != 0) {
                if (x != 0) {
                    x *= Double.parseDouble(stb.toString());
                }else {
                    x = Double.parseDouble(stb.toString());
                }
                editText2.setText(DeleteZero(Double.toString(x)));
                editText.setText("×");
                flag = 3;
                stb.delete(0, stb.length());
            }
        }
        if (v == d) {
            if (stb.length() != 0) {
                if (x != 0) {
                    x /= Double.parseDouble(stb.toString());
                }else {
                    x = Double.parseDouble(stb.toString());
                }
                editText2.setText(DeleteZero(Double.toString(x)));
                editText.setText("÷");
                flag = 4;
                stb.delete(0, stb.length());
            }
        }


        if (v == equl) {
            temp=equal(flag,x,Double.parseDouble(stb.toString()));
            String resultp = DeleteZero(Double.toString(temp));
            editText.setText(resultp);
            editText2.setText("");
            flag = 0;
            x=0;
            isCalculated=true;
            stb.delete(0, stb.length());
            stb.append(temp);
            System.out.println(temp);
        }



        if(v==mp){
            mem+=Double.parseDouble(stb.toString());
            editText3.setText("M="+DeleteZero(String.valueOf(mem)));
        }
        if(v==mm){
            mem-=Double.parseDouble(stb.toString());
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
    
    private double equal(int flag,double x,double y){
        double result = 0;
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
    private void varyDisplay(StringBuffer stb){
        if(stb.length()>11){
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        }
        if (stb.length()>21){
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        } if(stb.length()<=11) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
        }
        editText.setText(stb);
    }

    private void StringBufferAppend(String c){
        if(stb.length()<25){
            stb.append(c);
        }
        if (isCalculated) {
            stb.delete(0,stb.length());
            temp=0;
            editText.setText("");
            isCalculated = false;
            StringBufferAppend(c);
        }
    }

}
