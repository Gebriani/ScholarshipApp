package com.orcaella.scholarshipapp.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orcaella.scholarshipapp.App;
import com.orcaella.scholarshipapp.R;
import com.orcaella.scholarshipapp.model.CategoryModel;
import com.orcaella.scholarshipapp.network.RestClient;
import com.orcaella.scholarshipapp.ui.adapter.InterestAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseInterestActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView rvInterest;
    private InterestAdapter adapter;
    private TextView tvTitle;
    private TextView tvDesc;
    private Button btDone;
    private ProgressBar progressBar;
    private List<CategoryModel> categoryModelList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_interest);
        rvInterest = findViewById(R.id.choose_interest_tag_rvlist);
        tvTitle = findViewById(R.id.choose_interest_title);
        tvDesc = findViewById(R.id.choose_interest_desc);
        btDone = findViewById(R.id.choose_interest_done_button);
        progressBar = findViewById(R.id.choose_interest_progressBar);

        Typeface helti = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        Typeface opensans = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        tvTitle.setTypeface(helti);
        tvDesc.setTypeface(opensans);
        btDone.setTypeface(helti);


        adapter = new InterestAdapter(this, categoryModelList);
        rvInterest.setAdapter(adapter);
        btDone.setOnClickListener(this);
        getListCategories();
    }

    private void getListCategories(){
        RestClient.getClient().getCategories().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoryModelList.clear();
                    categoryModelList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choose_interest_done_button:
                if (adapter.getCatChoosed().size() < 5 ){
                    Toast.makeText(this, "Please pick 5 or more the category you are interested", Toast.LENGTH_SHORT).show();
                } else {
                    HomeActivity.show(this, App.getInstance().getGson().toJson(adapter.getCatChoosed()));
                    finish();
                }
                break;
        }
    }
}
