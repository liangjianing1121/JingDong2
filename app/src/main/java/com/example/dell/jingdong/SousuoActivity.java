package com.example.dell.jingdong;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SousuoActivity extends AppCompatActivity {

    private SearchView searchView;
    private ImageView fanhui;
    private ListView lv;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        initView();
        initData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent=new Intent(SousuoActivity.this,Shop2Activity.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void initData() {
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        list = new ArrayList<>();
        list.add("手机");
        list.add("笔记本");
        list.add("坚果");
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list));
        lv.setTextFilterEnabled(true);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    lv.setFilterText(newText);
                }else{
                    lv.clearTextFilter();
                }
                return false;
            }
        });

    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchview);
        fanhui = (ImageView) findViewById(R.id.fanhui);
        lv = (ListView) findViewById(R.id.lv);

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
