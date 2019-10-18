package com.sml3s.cruddemo1.helpers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.sml3s.cruddemo1.R;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private UsuarioAdepter mAdapter;
    private Drawable icom;
    private final ColorDrawable background;


    public SwipeToDeleteCallback(UsuarioAdepter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        background = new ColorDrawable(Color.RED);
        icom = ContextCompat.getDrawable(mAdapter.getContext(), R.drawable.ic_delete);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset= 20;

        int iconMargin = (itemView.getHeight()- icom.getIntrinsicHeight())/2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icom.getIntrinsicHeight())/2;
        int iconBotton = iconTop + icom.getIntrinsicHeight();

        if(dX>0){
            int iconRight = itemView.getLeft() + iconMargin + icom.getIntrinsicWidth();
            int iconLeft = itemView.getLeft() + iconMargin;
            icom.setBounds(iconLeft,iconTop, iconRight, iconTop);

            background.setBounds(itemView.getLeft(), itemView.getTop(),itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                            itemView.getBottom());
        }else if (dX < 0) { // Swiping to the left
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }
        background.draw(c);
    }
}
