package com.hither.baike.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.hither.baike.R;
import com.hither.baike.fragment.ImFragment;
import com.hither.baike.fragment.MapFragment;
import com.hither.baike.fragment.NewsFragment;
import com.hither.baike.fragment.OtherFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.newsID)
    TextView newsID;
    @BindView(R.id.imID)
    TextView imID;
    @BindView(R.id.mapID)
    TextView mapID;
    @BindView(R.id.othersID)
    TextView othersID;
    private NewsFragment newsFragment;
    private ImFragment imFragment;
    private MapFragment mapFragment;
    private OtherFragment otherFragment;
    private Fragment[] fragments;
    private TextView textViews[];
    //代表刚选中的下标
    private int index;
    //代表当前fragment的index
    private int currentTabIndex;
    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        textViews = new TextView[4];
        textViews[0] = newsID;
        textViews[1] = imID;
        textViews[2] = mapID;
        textViews[3] = othersID;
        textViews[0].setSelected(true);
        newsFragment = new NewsFragment();
        imFragment = new ImFragment();
        mapFragment = new MapFragment();
        otherFragment = new OtherFragment();
        fragments = new Fragment[]{newsFragment, imFragment, mapFragment, otherFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutID, fragments[0]).add(R.id.frameLayoutID, fragments[1]).
                show(fragments[0]).hide(fragments[1]).commit();
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setMenu(R.layout.slidingmenu_left);
        slidingMenu.setBehindWidth(getResources().getDisplayMetrics().widthPixels * 4 / 5);
        slidingMenu.setSecondaryMenu(R.layout.sledingmenu_right);
        slidingMenu.setAboveOffset(0);
        slidingMenu.attachToActivity(this, SlidingMenu.TOUCHMODE_FULLSCREEN);
    }

    @OnClick({R.id.newsID, R.id.imID, R.id.mapID, R.id.othersID})
    public void onselectClick(View view) {
        switch (view.getId()) {
            case R.id.newsID:
                index = 0;
                indexFragment();
                break;
            case R.id.imID:
                index = 1;
                indexFragment();
                break;
            case R.id.mapID:
                index = 2;
                indexFragment();
                break;
            case R.id.othersID:
                index = 3;
                indexFragment();
                break;

        }
    }

    public void indexFragment() {
        if (currentTabIndex != index) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                fragmentTransaction.add(R.id.frameLayoutID, fragments[index]);
            }
            fragmentTransaction.show(fragments[index]).commit();
        }
        textViews[index].setSelected(true);
        textViews[currentTabIndex].setSelected(false);
        setSelected(index, currentTabIndex);
        currentTabIndex = index;
    }

    public void setSelected(int index, int currentTabIndex) {
        textViews[index].setClickable(false);
        textViews[currentTabIndex].setClickable(true);
    }
}
