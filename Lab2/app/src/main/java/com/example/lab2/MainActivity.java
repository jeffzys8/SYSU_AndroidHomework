package com.example.lab2;

import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.lab2.myDialog.ChangeSelfie;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView)findViewById(R.id.sysu);
        image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ChangeSelfie dialog = new  com.example.lab2.myDialog.ChangeSelfie();
                dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String text = "";
                switch (i){
                    case R.id.ratio_student:text = "您选择了[学生]";break;
                    case R.id.ratio_staff:text = "您选择了[教职工]";break;
                }
                Snackbar.make(findViewById(R.id.radioGroup),text,Snackbar.LENGTH_SHORT)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Toast.makeText(getApplicationContext(),"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        Button but_log = (Button)findViewById(R.id.but_log);
        but_log.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextInputLayout input_sid = (TextInputLayout)findViewById(R.id.input_sid);
                TextInputLayout input_pass = (TextInputLayout)findViewById(R.id.input_pass);

                if(input_sid.getEditText().getText().length() == 0) {
                    input_sid.setError("学号不能为空");
                    input_sid.setErrorEnabled(true);
                }
                else input_sid.setErrorEnabled(false);

                if(input_pass.getEditText().getText().length() == 0) {
                    input_pass.setError("密码不能为空");
                    input_pass.setErrorEnabled(true);
                }
                else input_pass.setErrorEnabled(false);

                String text = "";
                if(!input_pass.getEditText().getText().toString().equals("6666") ||
                        !input_sid.getEditText().getText().toString().equals("1234567")){
                    text = "学号或密码错误";
                }
                else text = "登陆成功";
                Snackbar.make(findViewById(R.id.radioGroup),text,Snackbar.LENGTH_SHORT)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Toast.makeText(getApplicationContext(),"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
        }});

        Button but_reg = (Button)findViewById(R.id.but_reg);
        but_reg.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String text="";
                switch (((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId()){
                    case R.id.ratio_student:text="学生注册功能尚未启用";break;
                    case R.id.ratio_staff:text="教职工注册功能尚未启用";break;
                }
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
