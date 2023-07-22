package com.example.firstdemo.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdemo.R;
import com.example.firstdemo.entity.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private List<Article> articleList;

    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.bindArticleData(article);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticleList(List<Article> articleList){
        this.articleList = articleList;
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorTextView;
        private TextView updateTimeTextView;
        private TextView sourceTextView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            updateTimeTextView = itemView.findViewById(R.id.publishTimeTextView);
            sourceTextView = itemView.findViewById(R.id.originTextView);
        }

        public void bindArticleData(Article article) {
            titleTextView.setText(article.getTitle());
            authorTextView.setText("Author: " + article.getAuthor());
            updateTimeTextView.setText("Updated: " + article.getPublishTime());
            sourceTextView.setText("Source: " + article.getOrigin());
        }
    }
}