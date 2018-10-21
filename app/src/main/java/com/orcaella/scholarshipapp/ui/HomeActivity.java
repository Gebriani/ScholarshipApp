package com.orcaella.scholarshipapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.orcaella.scholarshipapp.App;
import com.orcaella.scholarshipapp.R;
import com.orcaella.scholarshipapp.model.CategoryModel;
import com.orcaella.scholarshipapp.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity{

    private RecyclerView rvCategoryList;
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private CategoryAdapter adapter;
    private Toolbar toolbar;
    private TextView titleToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvCategoryList = findViewById(R.id.home_category_list);
        toolbar = findViewById(R.id.toolbar);
        titleToolbar = findViewById(R.id.toolbar_title);
        rvCategoryList.setLayoutManager(new LinearLayoutManager(this));
        CategoryModel[] objArray = App.getInstance().getGson().fromJson(getIntent().getStringExtra("intent_data"), CategoryModel[].class);
        categoryModelList = Arrays.asList(objArray);
        adapter = new CategoryAdapter(this, categoryModelList);
        rvCategoryList.setAdapter(adapter);
        setSupportActionBar(toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
            titleToolbar.setText("Home");
        }
    }

    public static void show(Context context, String categories) {
        Intent intent = new Intent(context, HomeActivity.class);
        Bundle b = new Bundle();
        b.putString("intent_data", categories);
        intent.putExtras(b);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(0, 0);
    }
}
