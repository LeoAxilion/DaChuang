package com.example.xinyuxin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.xinyuxin.Config.Config;
import com.example.xinyuxin.Entity.ReturnType;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class RegisterActivity extends BaseActivity {
    //定义眼睛
    private ToggleButton tb;

    //密码可见定义
    EditText et;
    EditText name;
    EditText number;

    Button turnBack = null;
    Button signIn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.cookieConfig(getApplicationContext());
        setContentView(R.layout.activity_register);

        //隐藏系统自带菜单栏
        ActionBar actionBar = getSupportActionBar();
        if( actionBar !=null){
            actionBar.hide();
        }

        et=(EditText)findViewById(R.id.register_passwordEdit) ;

//设置眼睛开关,密码可见与不可见
        tb=(ToggleButton)findViewById(R.id.tbeye);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(arg1){
                    tb.setBackgroundResource(R.drawable.eye_on);
                    et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    tb.setBackgroundResource(R.drawable.eye);
                    et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        //设置注册按钮字体提示
        TextView tv =(TextView) findViewById(R.id.register_tip);
        String tip ="点击上面的“注册”按钮，即表示你同意<u><font color='#3F51B5'>《腾讯微信软件许可及服务协议》</font></u>和<u><font color='#3F51B5'>《腾讯隐私政策》</font></u>";
        tv.setText(Html.fromHtml(tip));

//设置返回键
        turnBack = (Button)findViewById(R.id.register_turnback);
        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        name = (EditText)findViewById(R.id.register_nameEdit);
        number = (EditText)findViewById(R.id.register_phoneEdit);


        signIn = (Button)findViewById(R.id.register_registerBtn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUp(number.getText().toString(),name.getText().toString(),et.getText().toString());
            }
        });
    }

    private boolean checkUp(String phone, final String name, String password){
        OkHttpUtils
                .post()
                .url(Config.ServerUrl+"user/register")
                        .addParams("username", ((EditText)findViewById(R.id.register_nameEdit)).getText().toString())
                        .addParams("password", ((EditText)findViewById(R.id.register_passwordEdit)).getText().toString())
                        .addParams("telphone", ((EditText)findViewById(R.id.register_phoneEdit)).getText().toString())
//                .addParams("username", "caoyuxin")
//                .addParams("password", "123456")
//                .addParams("telphone", "13837439381")
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                 e.printStackTrace();
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 Log.d("register:login",response);
                                 Gson gson =new Gson();
                                 ReturnType result = gson.fromJson(response , ReturnType.class);
                                 if(result.getCode()==200){
                                     Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                                     FunctionActivity.phoneNumber = ((EditText)findViewById(R.id.register_phoneEdit)).getText().toString();
                                     FunctionActivity.name =  ((EditText)findViewById(R.id.register_nameEdit)).getText().toString();
                                     //注册完毕之后直接登陆不用再到登录那里  ， 服务器已经记住状态
                                     Intent intent = new Intent(RegisterActivity.this,FunctionActivity.class);
                                     FunctionActivity.name=name ;
                                     finishAll();
                                     startActivity(intent);
                                     RegisterActivity.this.finish();
                                 }else{
                                     Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                                 }


                             }
                         }
                );
        return  true ;
//        MyDatabaseHelper databaseHelper= new MyDatabaseHelper(RegisterActivity.this, "Booking.db", null, 1);
//        SQLiteDatabase database = databaseHelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery("Select * from Book where phoneNumber = ?",new String[] {phone});
//        Cursor cursor2 = database.rawQuery("Select * from Book where name = ?",new String[] {name});
//        if(cursor.moveToFirst()){
//            Toast.makeText(RegisterActivity.this,"注册失败，手机号已存在",Toast.LENGTH_SHORT).show();
//            cursor.close();
//            return false;
//        }
//        else if(cursor2.moveToFirst()){
//            Toast.makeText(RegisterActivity.this,"注册失败，用户名已存在",Toast.LENGTH_SHORT).show();
//            cursor.close();
//            return false;
//        }
//        else if(phone.length()!=11){
//            Toast.makeText(RegisterActivity.this,"注册失败，请输入真实手机号",Toast.LENGTH_SHORT).show();
//            cursor.close();
//            return false;
//        }
//        else if(password.length()<8||password.length()>16){
//            Toast.makeText(RegisterActivity.this,"注册失败，密码为8-16位",Toast.LENGTH_SHORT).show();
//            cursor.close();
//            return false;
//        }
//        else{
//            if(phone.equals("")||name.equals("")){
//                Toast.makeText(RegisterActivity.this,"注册失败，用户名或密码不能为空",Toast.LENGTH_SHORT).show();
//                cursor.close();
//                return false;
//            }
//            else{
//                cursor.close();
//                return true;
//            }
//        }

    }
}
