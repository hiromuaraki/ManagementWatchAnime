package com.example.viewsampleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dto.MultiDto;
import adapter.CustomAdapter;

public class MainActivity extends AppCompatActivity {
    //画面オブジェクトの宣言
    private TextView txtLabelInput;
    private TextView cnt;
    private TextView rst;
    private EditText txtInput;
    private Button btn_Multi;
    private Button btn_Clear;
    private Button btn_Next;
    private CheckBox chkDrink;
    private CheckBox chkFood;
    private RadioButton rdoMan;
    private RadioButton rdoWoman;
    private Spinner spCurryList;

    //複数選択領域
    private LinearLayout multiLayout = null;
    private ListView muList;
    private CustomAdapter adp;
    private List<MultiDto> mDtoList = new ArrayList<>();
    MultiDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyty_main_sample);
        findViewId();
        changeVisibility();
        createList();
        setListeners();
    }

    /**
     * 複数選択領域の非表示制御
     */
    private void changeVisibility() {
        multiLayout.setVisibility(View.GONE);
    }

    /**
     * オブジェクトの取得
     */
    private void findViewId() {
        txtLabelInput = (TextView) findViewById(R.id.txtLabelInput);
        cnt = (TextView) findViewById(R.id.cnt);
        rst = (TextView) findViewById(R.id.rst);
        txtInput = (EditText) findViewById(R.id.txtInput);
        btn_Multi = (Button) findViewById(R.id.btn_Multi);
        btn_Clear = (Button) findViewById(R.id.btn_Clear);
        btn_Next = (Button) findViewById(R.id.btn_Next);
        chkDrink = (CheckBox) findViewById(R.id.chkDrink);
        chkFood = (CheckBox) findViewById(R.id.chkFood);
        rdoMan = (RadioButton) findViewById(R.id.rdoMan);
        rdoWoman = (RadioButton) findViewById(R.id.rdoWoman);
        spCurryList = (Spinner) findViewById(R.id.spCurryList);
        multiLayout = (LinearLayout) findViewById(R.id.multiLayout);
        muList = (ListView) findViewById(R.id.muList);
        //複数選択モードへ変更
        muList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        cnt.setText(0 + "件");

    }

    /**
     * リスナーのセット
     *
     */
    private void setListeners() {
        //「複数」ボタン押下時
        btn_Multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //複数選択領域の制御
                if (multiLayout.getVisibility() == View.GONE) {
                    multiLayout.setVisibility(View.VISIBLE);
                    spCurryList.setEnabled(true);
                } else {
                    multiLayout.setVisibility(View.GONE);
                    spCurryList.setEnabled(false);
                }
            }
        });

        //リスト選択イベント
        muList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //選択行を取得
                ListView multiListView = (ListView) parent;
                MultiDto itemDto = mDtoList.get(position);
                if (multiListView.isItemChecked(position)) {
                    //選択行のフラグをONへ
                    itemDto.setisFlg(true);
                } else {
                    itemDto.setisFlg(false);
                }
                rst.setText(multiListView.getCheckedItemCount() + "件");
                ((CustomAdapter) multiListView.getAdapter()).notifyDataSetChanged();
            }
        });

        //「次へ」ボタン押下時
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (multiLayout.getVisibility() == View.VISIBLE && dto.getChar1() != null) {
                    Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                    intent.putExtra("Menu", getPrmAppends(mDtoList));
                    //IntentActivity画面へ遷移
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "メニューを選択してください。", Toast.LENGTH_LONG).show();
                }

            }
        });

        //「クリア」ボタン押下時
        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText("");
                rdoMan.setChecked(true);
                chkDrink.setChecked(true);
            }
        });
    }

    /**
     * 選択された行のみ文字列連結させる
     *
     * @param dto
     * @return rst
     */
    private String getPrmAppends(List<MultiDto> dto) {
        StringBuilder sb = new StringBuilder();
        for (MultiDto dtoItem : dto) {
            //選択された行のみ文字列連結
            if (dtoItem.getisFlg()) {

                if (sb.length() > 0 || sb != null) {
                    sb.append("'").append(dtoItem.getChar1()).append("'");
                } else {
                    sb.append(",'").append(dtoItem.getChar1()).append("'");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 要素の作成しアダプタへセット
     *
     * @return
     */
    private ListView createList() {
        String[] item = {
                "*"
                , "ドライカレー"
                ,""
                , "カツカレー"
                , "ビーフカレー"
                , "チキンカレー"
                , "*"
                , "シーフードカレー"
                , "キーマーカレー"
                ,""
                , "グリーンカレー"
        };

        for (int i = 0; i < item.length; i++) {
            dto = new MultiDto();

            for (String element : item) {
                if (element.equals("*") || element.equals("")) {
                    continue;
                } else {
                    dto.setChar1(element);
                    mDtoList.add(dto);
                }
            }
        }

        adp = new CustomAdapter(MainActivity.this, R.layout.main_list, mDtoList);
        muList.setAdapter(adp);
        return muList;
    }
}
