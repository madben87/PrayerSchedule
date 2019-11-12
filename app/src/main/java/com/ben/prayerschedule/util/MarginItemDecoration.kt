package com.ben.prayerschedule.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    var spaceLeft: Int,
    var spaceTop: Int,
    var spaceRight: Int,
    var spaceBottom: Int
) : RecyclerView.ItemDecoration() {

    constructor(spaceHeight: Int) : this(spaceHeight, spaceHeight, spaceHeight, spaceHeight)

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceTop
            }
            left = spaceLeft
            right = spaceRight
            bottom = spaceBottom
        }
    }
}