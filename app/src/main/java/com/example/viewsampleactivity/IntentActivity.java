package com.example.viewsampleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {
    private TextView txtLabel;
    private EditText txtMenu;
    private Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        findViewId();
        //リスト画面から渡されたデータ取得
        Intent intent = getIntent();
        String item = intent.getStringExtra("Menu");
        txtMenu.setText("ﾘｽﾄ:" + item.replace("'",""));
        txtMenu.setFocusable(false);
        setListener();

    }

    private void findViewId() {
        txtLabel = (TextView) findViewById(R.id.txtLabel);
        txtMenu = (EditText) findViewById(R.id.txtMenu);
        btn_return = (Button) findViewById(R.id.btn_return);
    }

    //「戻る」ボタン押下時
    private void setListener() {
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMenu.setText("");
                finish();
            }
        });
    }
}
