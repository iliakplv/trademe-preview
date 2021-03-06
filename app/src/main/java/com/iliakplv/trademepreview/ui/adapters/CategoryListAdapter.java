package com.iliakplv.trademepreview.ui.adapters;


import android.content.Context;
import android.os.Handler;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iliakplv.trademepreview.ApplicationModule;
import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.TradeMePreviewApp;
import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

import javax.inject.Inject;
import javax.inject.Named;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    @Inject
    @Named(ApplicationModule.MAIN_THREAD_HANDLER)
    Handler mainThreadHandler;

    private CategoriesListView view;
    private Category category;


    public CategoryListAdapter(@NonNull Context context, @NonNull CategoriesListView view) {
        TradeMePreviewApp.get(context).applicationComponent().inject(this);
        this.view = view;
    }


    @AnyThread
    public void setCategory(Category category) {
        this.category = category;
        mainThreadHandler.post(this::notifyDataSetChanged);
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(category.getSubcategory(position).getName());
    }

    @Override
    public int getItemCount() {
        if (category != null) {
            return category.subcategoriesCount();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        View selectButton;

        ViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView) itemView.findViewById(R.id.category_name);

            itemView.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                view.onCategoryClicked(category.getSubcategory(position));
            });

            selectButton = itemView.findViewById(R.id.select_button);
            selectButton.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                view.onCategorySelected(category.getSubcategory(position));
            });
        }
    }
}
