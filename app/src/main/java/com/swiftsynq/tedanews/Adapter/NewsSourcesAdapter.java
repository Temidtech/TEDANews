package com.swiftsynq.tedanews.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swiftsynq.tedanews.Model.NewsSources;
import com.swiftsynq.tedanews.Model.Sources;
import com.swiftsynq.tedanews.R;

import java.util.List;

public class NewsSourcesAdapter extends RecyclerView.Adapter<NewsSourcesAdapter.ItemViewHolder> {
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_THREE = 3;

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    private List<Sources> mItems;
    private GridLayoutManager mLayoutManager;
    Context context;

    public NewsSourcesAdapter(Context ctx, List<Sources> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
        context=ctx;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sources_card, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sources_card, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Sources item = mItems.get(position);
        holder.title.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.category.setText(item.getCategory());
        switchResource(item.getCategory(),holder);

    }
    private void switchResource(String category, ItemViewHolder holder)
    {
        if(category.equals(context.getString(R.string.businesscategory))) {
            holder.imgBg.setImageResource(R.drawable.business);
            holder.category.setBackgroundResource(R.color.colorBusiness);
        }
        else if(category.equals(context.getString(R.string.generalcategory))) {
            holder.category.setBackgroundResource(R.color.colorPrimaryDark);
            holder.imgBg.setImageResource(R.drawable.business);
        }
        else if(category.equals(context.getString(R.string.entertainmentcategory))) {
            holder.category.setBackgroundResource(R.color.colorEntertainment);
            holder.imgBg.setImageResource(R.drawable.entertainment);
        }
        else if(category.equals(context.getString(R.string.sportscategory))) {
            holder.category.setBackgroundResource(R.color.colorSports);
            holder.imgBg.setImageResource(R.drawable.sports);
        }
        else if(category.equals(context.getString(R.string.healthcategory))) {
            holder.category.setBackgroundResource(R.color.colorHealth);
            holder.imgBg.setImageResource(R.drawable.health);
        }
        else if(category.equals(context.getString(R.string.technologycategory))) {
            holder.category.setBackgroundResource(R.color.colorTechnology);
            holder.imgBg.setImageResource(R.drawable.technology);
        }
        else if(category.equals(context.getString(R.string.sciencecategory))) {
            holder.category.setBackgroundResource(R.color.colorScience);
            holder.imgBg.setImageResource(R.drawable.science);
        }
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        TextView description;
        TextView category;
        ImageView imgBg;
        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_BIG) {

                title = (TextView) itemView.findViewById(R.id.tvTtile);
                description = (TextView) itemView.findViewById(R.id.tvDescription);
                category = (TextView) itemView.findViewById(R.id.tvCategory);
                imgBg = (ImageView) itemView.findViewById(R.id.imgBg);
            } else {
                title = (TextView) itemView.findViewById(R.id.tvTtile);
            }
        }
    }
}
