package com.stone.EasyTeaching.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.utilities.MyRelativeLayout;
import com.stone.EasyTeaching.utilities.OnSizeChangedListener;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    /**APP*/
    private ETApplication app;
    /**
     * 异步请求Client
     */
    private AsyncHttpClient client;
    /**登录按钮*/
    private Button btnLogin;
    /**注册按钮*/
    private Button btnRegister;
    /**找回密码*/
    private TextView tvGetPassword;
    /**页面*/
    private MyRelativeLayout myRelative;
    /**头部*/
    private LinearLayout topLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = (ETApplication) getApplication();
        client = app.getClient();
        init();
    }

    /**
     * 绑定UI
     */
    private void init(){
        btnLogin = (Button) findViewById(R.id.login_button_login);
        btnRegister = (Button) findViewById(R.id.login_button_register);
        tvGetPassword = (TextView) findViewById(R.id.text_get_password);
        myRelative = (MyRelativeLayout) findViewById(R.id.my_relative);
        topLayout = (LinearLayout) findViewById(R.id.top);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvGetPassword.setOnClickListener(this);

        myRelative.setOnSizeChangedListener(new OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                if (oldh>h&&oldh!=0){
                    topLayout.setVisibility(View.GONE);
                }else if(h>oldh){
                    topLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button_login:
                Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.login_button_register:
                Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.text_get_password:
                Intent intentGetPassword = new Intent(LoginActivity.this,GetPasswordActivity.class);
                startActivity(intentGetPassword);
                break;
        }
    }
}
