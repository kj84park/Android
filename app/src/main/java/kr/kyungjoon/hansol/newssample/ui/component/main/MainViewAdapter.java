package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.kyungjoon.hansol.newssample.R;
import kr.kyungjoon.hansol.newssample.ui.listener.RecyclerItemListener;
import kr.kyungjoon.hansol.newssample.network.dto.Articles;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class MainViewAdapter extends  RecyclerView.Adapter<MainViewHoler>{

    public final String TAG = MainViewAdapter.class.getName();
    private RecyclerItemListener recyclerItemListener;

    private final List<Articles> news;

    MainViewAdapter(List<Articles> news, RecyclerItemListener listener) {
        this.news = news;
        this.recyclerItemListener = listener;
    }

    @Override
    public MainViewHoler onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MainViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MainViewHoler holder, int position) {
        holder.bind(position, news.get(position));
        holder.itemView.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}
