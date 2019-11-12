package com.ben.prayerschedule.util;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class LastItemListener extends RecyclerView.OnScrollListener {


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (layoutManager.getChildCount() > 0) {
            int indexOfLastItemViewVisible = layoutManager.getChildCount() - 1;
            View lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible);
            int adapterPosition = layoutManager.getPosition(lastItemViewVisible);
            boolean isLastItemVisible = (adapterPosition == adapter.getItemCount() - 1);

            if (isLastItemVisible)
                onLastItemVisible();
        }
    }


    public abstract void onLastItemVisible();

}
