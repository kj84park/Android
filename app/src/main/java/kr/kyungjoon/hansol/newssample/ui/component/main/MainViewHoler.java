package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.kyungjoon.hansol.newssample.R;
import kr.kyungjoon.hansol.newssample.network.dto.Articles;

/**
 * Created by HANSOL on 2018-03-18.
 */

class MainViewHoler extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_caption)
    TextView tvCaption;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_news_item)
    RelativeLayout newsItemLayout;
    @BindView(R.id.news_image)
    ImageView newsImage;

    View itemView;

    MainViewHoler(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    void bind(int position, Articles articles) {

        tvTitle.setText("[" + articles.getSource().getName() + "]" + articles.getTitle());

        if (articles.getDescription() != null && !"Chosun.com".equals(articles.getSource().getName())) {
            tvCaption.setText(articles.getDescription());

        } else {
            tvCaption.setText("");
        }

        Glide.with(itemView).load(articles.getUrlToImage()).into(newsImage);
    }
}
