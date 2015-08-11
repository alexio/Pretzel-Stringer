package com.nytimes.stringers.views.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nytimes.data.entity.InvestigationModel;
import com.nytimes.stringers.R;

public class InvestigationPageAdapter extends PagerAdapter {

    private final String[] PAGE_TITLES = {"Info", "Chat"};
    private LayoutInflater layoutInflater;
    private InvestigationModel model;

    public InvestigationPageAdapter(Context context, InvestigationModel model) {
        this.layoutInflater = LayoutInflater.from(context);
        this.model = model;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int res_id = position == 0 ? R.layout.investigation_info : R.layout.investigation_chat;
        View view =  layoutInflater.inflate(res_id, null);

        if (res_id == R.layout.investigation_info) {
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(model.getTitle());
            TextView description = (TextView) view.findViewById(R.id.description);
            description.setText(model.getDescription());
        }

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
