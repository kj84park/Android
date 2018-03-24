package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.kyungjoon.hansol.newssample.R;
import kr.kyungjoon.hansol.newssample.network.dto.Articles;
import kr.kyungjoon.hansol.newssample.ui.component.Base;
import kr.kyungjoon.hansol.newssample.ui.component.details.DetailedActivity;
import kr.kyungjoon.hansol.newssample.ui.listener.RecyclerItemListener;

public class MainActivity extends Base implements MainView {

    public static final String API_KEY = "04871c167cfb4a3c9c18b9d170d8ba7f";
    public final String TAG = MainActivity.class.getName();

    @BindView(R.id.news_list)
    RecyclerView recyclerView;

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @BindView(R.id.spinner_country)
    public Spinner spinnerCountry;

    @BindView(R.id.spinner_category)
    public Spinner spinnerCategory;

    List<Articles> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new MainPresenter(this,getMainComponent());
    }

    private final RecyclerItemListener recyclerItemListener = position -> {
        Articles article =  articles.get(position);
        Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    };

    @Override
    public void initRecyclerView(List<Articles> articles) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MainViewAdapter(articles, recyclerItemListener));
        this.articles = articles;
    }

    @Override
    public Spinner getSpinnerCountry() {
        return spinnerCountry;
    }

    @Override
    public Spinner getSpinnerCategory() {
        return spinnerCategory;
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}

