package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.Operation.AdminOperation;
import com.whatever.nurseapp.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText idEt;
    private EditText passwordEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEt = (EditText) findViewById(R.id.admin_id);
        passwordEt = (EditText) findViewById(R.id.admin_password);
        loginBtn = (Button) findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(idEt.getText().toString());
                String password = passwordEt.getText().toString();
                try {
                    ArrayList list = AdminOperation.adminLogin(id, password);
                    if (Integer.parseInt((String) list.get(0)) == 200) {
                        Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        JSONObject object = new JSONObject((String) list.get(1));
                        String message = object.getString("data");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });
    }


}
