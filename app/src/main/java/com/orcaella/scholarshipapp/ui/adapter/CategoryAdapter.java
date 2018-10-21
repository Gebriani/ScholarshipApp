package com.orcaella.scholarshipapp.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orcaella.scholarshipapp.R;
import com.orcaella.scholarshipapp.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryModel> catList;

    public CategoryAdapter(Context context, List<CategoryModel> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category, viewGroup, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int i) {
        CategoryModel item = catList.get(i);
        Glide.with(context)
                .load(item.getImage_url())
                .into(holder.ivCatImage);
        Typeface opensans = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans_Regular.ttf");
        holder.tvTitle.setTypeface(opensans);
        holder.tvDescription.setTypeface(opensans);
        holder.tvTitle.setText(item.getName());
        holder.tvDescription.setText(item.getDescription_eng());
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCatImage;
        private TextView tvTitle;
        private TextView tvDescription;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCatImage = itemView.findViewById(R.id.adapter_category_image);
            tvTitle = itemView.findViewById(R.id.adapter_category_title);
            tvDescription = itemView.findViewById(R.id.adapter_category_desc);

        }
    }

}
