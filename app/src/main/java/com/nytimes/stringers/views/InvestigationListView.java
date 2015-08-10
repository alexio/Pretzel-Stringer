package com.nytimes.stringers.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by alexio on 8/9/15.
 */
public class InvestigationListView extends RecyclerView {

    public InvestigationListView(Context context) {
        this(context, null);
    }

    public InvestigationListView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InvestigationListView(Context context, @Nullable AttributeSet attributeSet, int defStyle) {
        super(context,attributeSet,defStyle);
    }
}
