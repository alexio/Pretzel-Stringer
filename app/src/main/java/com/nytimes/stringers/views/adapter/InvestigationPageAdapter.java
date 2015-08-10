package com.nytimes.stringers.views.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.stringers.R;

public class InvestigationPageAdapter extends PagerAdapter {

    private final String[] PAGE_TITLES = {"Info", "Chat"};
    private LayoutInflater layoutInflater;

    public InvestigationPageAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int res_id = position == 0 ? R.layout.investigation_info : R.layout.investigation_chat;
        View view =  layoutInflater.inflate(res_id, null);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
}

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
