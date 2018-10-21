package com.orcaella.scholarshipapp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.orcaella.scholarshipapp.R;
import com.orcaella.scholarshipapp.model.CategoryModel;
import java.util.ArrayList;
import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestViewHolder> {

    private List<CategoryModel> catList = new ArrayList<>();
    private List<CategoryModel> catChoosed = new ArrayList<>();
    private Context context;

    public InterestAdapter(Context context, List<CategoryModel> catList) {
        this.catList = catList;
        this.context = context;
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_interest, viewGroup, false);
        return new InterestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final InterestViewHolder interestViewHolder, final int i) {
        final CategoryModel item = catList.get(i);
        interestViewHolder.cbCategory.setText(item.getName());
        Typeface opensans = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans_Regular.ttf");
        interestViewHolder.cbCategory.setTypeface(opensans);

        if (item.isChecked()){
            catChoosed.add(item);
        }

        interestViewHolder.cbCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setChecked(b);
                if (b){
                    catChoosed.add(item);
                    interestViewHolder.cbCategory.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    catChoosed.remove(item);
                    interestViewHolder.cbCategory.setTextColor(Color.parseColor("#545454"));
                }

            }
        });
    }

    public List<CategoryModel> getCatChoosed() {
        return catChoosed;
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }


    public static class InterestViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbCategory;

        public InterestViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCategory = itemView.findViewById(R.id.item_interest_title);
        }
    }


}
