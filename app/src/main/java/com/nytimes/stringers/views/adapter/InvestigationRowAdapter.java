package com.nytimes.stringers.views.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nytimes.data.entity.InvestigationModel;
import com.nytimes.stringers.R;

import java.util.List;

/**
 * Created by alexio on 8/9/15.
 * Adapter than manages a collection of {@link InvestigationModel}
 */
public class InvestigationRowAdapter extends RecyclerView.Adapter<InvestigationRowAdapter.InvestigationViewHolder> {

    public interface OnItemClickListener {
        void onInvestigationClicked(InvestigationModel model);
    }

    private final LayoutInflater layoutInflater;
    private final List<InvestigationModel> data;
    private OnItemClickListener itemClickListener;

    public InvestigationRowAdapter(Context context, List<InvestigationModel> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        validate();
    }

    @Override
    public InvestigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.investigation_item, parent, false);
        return new InvestigationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InvestigationViewHolder holder, int position) {
        final InvestigationModel model = data.get(position);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemClickListener itemClickListener = InvestigationRowAdapter.this.itemClickListener;
                if (itemClickListener != null) {
                    itemClickListener.onInvestigationClicked(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private void validate() {
        if (data == null) {
            throw new IllegalArgumentException("Investigation Collection is null");
        }
    }

    public static class InvestigationViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        CardView cardView;

        public InvestigationViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.investigation_card_view);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
