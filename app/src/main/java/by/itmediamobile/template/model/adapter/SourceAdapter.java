package by.itmediamobile.template.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.itmediamobile.template.R;
import by.itmediamobile.template.model.Source;

/**
 * Created by Denis Kholevinsky
 */

public class SourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Source source);
    }

    private final OnItemClickListener listener;

    public SourceAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Source> data = new ArrayList<>();

    public List<Source> getData() {
        return data;
    }

    public void setData(List<Source> data) {
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SourceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof SourceViewHolder) {
            ((SourceViewHolder) holder).bind(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Список источников
     */
    static class SourceViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener listener;

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.image)
        ImageView image;

        SourceViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        void bind(final Source source) {
            this.name.setText(source.getName());
            this.description.setText(source.getDescription());
            Picasso.with(itemView.getContext())
                    .load(source.getImage())
                    .into(this.image);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(source);
                }
            });
        }
    }
}
