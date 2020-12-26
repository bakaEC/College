package org.uestc1120.layout;
/*
  @Author: bakaEC | ec@bakaec.design
 * @Github: github.com/bakaEC
 * @Date: 2020-12-11
 */
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //定义按钮
    private Button
            b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, c,
            button_dot, button_minus, button_add, button_percent, button_divide, button_equal,
            mc, mp, mm, mr, mu;
    ImageButton button_delete;

    //定义文本框
    private EditText Text_main, Text_append, Text_memory;

    //定义基础变量
    StringBuffer stringBuffer = new StringBuffer();
    double x = 0,          //计算数
            temp = 0,       //程序内可调用临时变量
            memory = 0;     //Memory值
    int flag = 0;           //1:+ 2:- 3:* 4:÷ 0:Null

    //isCalculated 测试是否生成最终结果(即button_equal是否曾被按下)
    public Boolean isCalculated = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化View对象
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

        Text_main = findViewById(R.id.editText);
        Text_append = findViewById(R.id.editText2);
        Text_memory = findViewById(R.id.editText3);

        button_minus = findViewById(R.id.bm);
        button_add = findViewById(R.id.ba);
        button_divide = findViewById(R.id.d);
        button_equal = findViewById(R.id.bequl);
        mc = findViewById(R.id.mc);
        mp = findViewById(R.id.mp);
        mm = findViewById(R.id.mm);
        mr = findViewById(R.id.mr);
        c = findViewById(R.id.c);
        mu = findViewById(R.id.mu);
        button_dot = findViewById(R.id.bd);
        button_percent = findViewById(R.id.bp);

        button_delete = findViewById(R.id.del);


    }


    public void press(View v) {

        if (v == b0) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("0");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b1) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("1");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b2) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("2");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b3) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("3");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b4) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("4");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b5) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("5");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b6) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("6");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b7) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("7");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b8) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("8");
                varyDisplay(stringBuffer);

            }
        }
        if (v == b9) {
            if (stringBuffer.length() < 35) {
                StringBufferAppend("9");
                varyDisplay(stringBuffer);

            }
        }

        if (v == button_dot) {
            if (stringBuffer.indexOf(".") == -1 && stringBuffer.length() < 35) {
                stringBuffer.append(".");
            }
            varyDisplay(stringBuffer);

        }


        if (v == button_delete) {
            if (stringBuffer.length() != 0) {
                stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            }
            varyDisplay(stringBuffer);
        }
        if (v == c) {
            stringBuffer.delete(0, stringBuffer.length());
            x = 0;
            flag = 0;
            temp = 0;
            varyDisplay(stringBuffer);
            Text_append.setText("");
        }

        if (v == button_percent) {
            x = Double.parseDouble(stringBuffer.toString()) / 100;
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(x);
            varyDisplay(stringBuffer);
        }

        if (v == button_add) {
            if (stringBuffer.length() != 0) {
                onFlagInputingResult();
                stringBuffer.delete(0, stringBuffer.length());
                Text_main.setText("+");
                flag = 1;
                Text_append.setText(DeleteZero(Double.toString(x)));
            }
        }
        if (v == button_minus) {
            if (stringBuffer.length() != 0) {
                onFlagInputingResult();
                stringBuffer.delete(0, stringBuffer.length());
                Text_main.setText("-");
                flag = 2;
                Text_append.setText(DeleteZero(Double.toString(x)));

            }
        }
        if (v == mu) {
            if (stringBuffer.length() != 0) {
                onFlagInputingResult();
                Text_append.setText(DeleteZero(Double.toString(x)));
                Text_main.setText("×");
                flag = 3;
                stringBuffer.delete(0, stringBuffer.length());
            }
        }
        if (v == button_divide) {
            if (stringBuffer.length() != 0) {
                onFlagInputingResult();
                Text_append.setText(DeleteZero(Double.toString(x)));
                Text_main.setText("÷");
                flag = 4;
                stringBuffer.delete(0, stringBuffer.length());
            }
        }


        if (v == button_equal) {
            temp = equal(flag, x, Double.parseDouble(stringBuffer.toString()));
            String resultp = DeleteZero(Double.toString(temp));
            Text_main.setText(resultp);
            Text_append.setText("");
            flag = 0;
            x = 0;
            isCalculated = true;
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(temp);
            System.out.println(temp);
        }



        if(v==mp) {
            memory += Double.parseDouble(stringBuffer.toString());
            String display = "M=" + DeleteZero(String.valueOf(memory));
            Text_memory.setText(display);
        }
        if(v==mm) {
            memory -= Double.parseDouble(stringBuffer.toString());
            String display = "M=" + DeleteZero(String.valueOf(memory));
            Text_memory.setText(display);
        }
        if(v==mc) {
            memory = 0;
            Text_memory.setText("");
        }
        if(v==mr) {
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(memory);
            Text_main.setText(DeleteZero(stringBuffer.toString()));
        }
    }

    //对输出字符串进行去零及小数点操作
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

    //集成四则运算
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
                    if (x!=0&&y!=0) {
                        result = x * y;
                    }else result=x;

                    break;
                case 4:
                    if (x != 0 && y != 0) {
                        result = x / y;
                    } else result = x;
                    break;
            }
        return result;
    }

    //可变大小显示
    private void varyDisplay(StringBuffer stb) {
        if (stb.length() > 11) {
            Text_main.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }
        if (stb.length() > 21) {
            Text_main.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
        if (stb.length() <= 11) {
            Text_main.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        }
        Text_main.setText(stb);
    }

    //带长度检测的添加数字，当已计算时将寄存结果，清除临时变量
    private void StringBufferAppend(String c) {
        if (stringBuffer.length() < 25) {
            stringBuffer.append(c);
        }
        if (isCalculated) {
            stringBuffer.delete(0, stringBuffer.length());
            temp = 0;
            Text_main.setText("");
            isCalculated = false;
            StringBufferAppend(c);
        }
    }

    //在符号输入时的计算结果（连续计算）
    private void onFlagInputingResult() {
        switch (flag) {
            case 1:
                x += Double.parseDouble(stringBuffer.toString());
                break;
            case 2:
                x -= Double.parseDouble(stringBuffer.toString());
                break;
            case 3:
                x *= Double.parseDouble(stringBuffer.toString());
                break;
            case 4:
                x /= Double.parseDouble(stringBuffer.toString());
                break;
            case 0:
                x = Double.parseDouble(stringBuffer.toString());
                break;
        }
    }
}


