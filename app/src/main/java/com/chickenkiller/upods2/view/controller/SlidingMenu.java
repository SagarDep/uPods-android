package com.chickenkiller.upods2.view.controller;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.chickenkiller.upods2.R;
import com.chickenkiller.upods2.controllers.SlidingMenuAdapter;
import com.chickenkiller.upods2.interfaces.IFragmentsManager;
import com.chickenkiller.upods2.interfaces.ISlidingMenuManager;
import com.chickenkiller.upods2.models.SlidingMenuHeader;
import com.chickenkiller.upods2.models.SlidingMenuItem;
import com.chickenkiller.upods2.models.SlidingMenuRow;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alonzilberman on 7/4/15.
 */
public class SlidingMenu implements ISlidingMenuManager {

    private final int DEVIDER_SIZE = 2;
    private final int DEVIDER_MARGIN = 10;

    private Activity activity;
    private DrawerLayout mDrawerLayout;
    private RecyclerView rvDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private SlidingMenuAdapter slidingMenuAdapter;
    private HorizontalDividerItemDecoration devider;

    private final LinearLayoutManager layoutManager;


    public SlidingMenu(Activity mActivity, Toolbar mToolbar) {
        this.activity = mActivity;
        this.layoutManager = new LinearLayoutManager(mActivity);
        this.layoutManager.setOrientation(OrientationHelper.VERTICAL);
        this.mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        List<SlidingMenuItem> slidingMenuItems = new ArrayList<>();
        slidingMenuItems.add(new SlidingMenuHeader());
        slidingMenuItems.addAll(SlidingMenuRow.fromDefaultSlidingMenuSet(activity));
        if (activity instanceof IFragmentsManager) {
            this.slidingMenuAdapter = new SlidingMenuAdapter(slidingMenuItems,
                    R.layout.sliding_menu_item, this, (IFragmentsManager) activity);
        } else {
            this.slidingMenuAdapter = new SlidingMenuAdapter(slidingMenuItems,
                    R.layout.sliding_menu_item, this);
        }

        this.devider = new HorizontalDividerItemDecoration.Builder(mActivity)
                .color(R.color.sliding_menu_devider_color)
                .visibilityProvider(slidingMenuAdapter)
                .size(DEVIDER_SIZE)
                .margin(DEVIDER_MARGIN)
                .build();

        this.rvDrawerList = (RecyclerView) activity.findViewById(R.id.left_drawer);
        this.rvDrawerList.setHasFixedSize(true);
        this.rvDrawerList.setAdapter(slidingMenuAdapter);
        this.rvDrawerList.setLayoutManager(this.layoutManager);
        this.rvDrawerList.addItemDecoration(devider);

        this.mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                mToolbar,
                R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                activity.invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public SlidingMenuAdapter getAdapter(){
        return slidingMenuAdapter;
    }

    @Override
    public void toggle() {
        if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

}
