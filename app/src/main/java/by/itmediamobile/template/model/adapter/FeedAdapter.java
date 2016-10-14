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
import by.itmediamobile.template.model.Feed;
import by.itmediamobile.template.model.NewsFeed;

/**
 * Created by Denis Kholevinsky
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Feed feed);
    }

    private final OnItemClickListener listener;

    public FeedAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Feed> data = new ArrayList<>();

    public List<Feed> getData() {
        return data;
    }

    public void setData(List<Feed> data) {
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsFeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NewsFeedViewHolder) {
            final NewsFeed feed = (NewsFeed) data.get(position);
            ((NewsFeedViewHolder) holder).bind(feed);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Новости
     */
    static class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener listener;

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.image)
        ImageView image;

        NewsFeedViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        void bind(final NewsFeed feed) {
            this.name.setText(feed.getTitle());
            this.description.setText(feed.getDescription());
            this.author.setText(feed.getAuthor());
            Picasso.with(itemView.getContext())
                    .load(feed.getImage())
                    .into(this.image);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(feed);
                }
            });
        }

    }
}
