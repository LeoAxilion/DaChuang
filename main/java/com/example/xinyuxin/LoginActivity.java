package com.example.xinyuxin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xinyuxin.Config.Config;
import com.example.xinyuxin.Entity.ReturnType;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class LoginActivity extends BaseActivity {
    String name = null;
    static String PN=null;
    private final static String TAG="thirdActivity";
    EditText phoneNumber = null;
    EditText password = null;
    CheckBox remember = null;
    CheckBox autoLogin = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button login_to_register;
    Button login;

    //定义Toolbar工具栏用来替代标题栏并添加菜单和返回图标
    private Toolbar mNormalToolbar;

    public void initialize(){
        login = (Button)findViewById(R.id.login_loginBtn);
        login_to_register = (Button)findViewById(R.id.login_to_register) ;
        phoneNumber = (EditText)findViewById(R.id.login_phoneEdit);
        password = (EditText)findViewById(R.id.login_passwordEdit);
        remember = (CheckBox)findViewById(R.id.login_remember);
        autoLogin = (CheckBox)findViewById(R.id.login_autoLogin);
        sharedPreferences=LoginActivity.this.getSharedPreferences("remember_the_password", MODE_PRIVATE);
        editor=sharedPreferences.edit();

        //定义点击自动登录的功能
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!autoLogin.isChecked()){
                    editor.remove("autoLogin");
                }else{
                    remember.setChecked(true);
                    Toast.makeText(LoginActivity.this,"登陆成功后有效",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //定义点击记住密码的功能
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!remember.isChecked()){
                    editor.clear();
                    autoLogin.setChecked(false);
                }else{
                    Toast.makeText(LoginActivity.this,"登陆成功后有效",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(sharedPreferences.getString("phoneNumber", "none").equals("none")){
            phoneNumber.setText("");
            password.setText("");
        }
        else{
            phoneNumber.setText(sharedPreferences.getString("phoneNumber", "none"));
            password.setText(sharedPreferences.getString("passWord", "none"));
            remember.setChecked(true);
        }
        if(PN!=null){
            phoneNumber.setText(PN);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();

//设置Toolbar工具栏替代标题栏并添加菜单和返回图标
        mNormalToolbar= (Toolbar) findViewById(R.id.login_toolbarLogin);

//设置返回图标监听器
        mNormalToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

//隐藏系统自带菜单栏
        ActionBar actionbar=getSupportActionBar();
        if( actionbar !=null){
            actionbar.hide();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String account = ((EditText)findViewById(R.id.login_phoneEdit)).getText().toString() ;
            String password = ((EditText)findViewById(R.id.login_passwordEdit)).getText().toString() ;

            OkHttpUtils
                .post()
                .url(Config.ServerUrl+"user/login")
                .addParams("account" ,account)
                .addParams("password" , password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(LoginActivity.this, "网络异常"+id, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson() ;
                        Log.d("register:login",response);
                        ReturnType re = gson.fromJson(response , ReturnType.class) ;
                        Toast.makeText(LoginActivity.this , re.getMessage() +":"+re.getCode(),Toast.LENGTH_SHORT) ;
                        if(re.getCode()==200){
                            //登陆成功
                            Toast.makeText(LoginActivity.this , re.getMessage() ,Toast.LENGTH_SHORT) ;

                            FunctionActivity.phoneNumber = phoneNumber.getText().toString();
                            Intent intent5= new Intent(LoginActivity.this, FunctionActivity.class);
                            FunctionActivity.name= re.getData().get("username").getAsString();
                            Toast.makeText(LoginActivity.this , re.getMessage() ,Toast.LENGTH_SHORT) ;

                            startActivity(intent5);

                        }else {
                            Toast.makeText(LoginActivity.this,re.getMessage() , Toast.LENGTH_SHORT) ;
                        }


                    }
                });



            }
        });

        login_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6= new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent6);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PN=null;
    }
}
