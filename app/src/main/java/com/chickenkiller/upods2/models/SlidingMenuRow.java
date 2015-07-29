package com.chickenkiller.upods2.models;

import android.content.Context;

import com.chickenkiller.upods2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alonzilberman on 7/4/15.
 */
public class SlidingMenuRow extends SlidingMenuItem {

    private String title;
    private int mainIconId;
    private int pressedIconId;
    public boolean isSelected;

    public SlidingMenuRow(String title, int mainIconId, int pressedIconId) {
        super();
        this.title = title;
        this.mainIconId = mainIconId;
        this.pressedIconId = pressedIconId;
    }

    public SlidingMenuRow(String title, int mainIconId, int pressedIconId, boolean hasDevider) {
        this(title, mainIconId, pressedIconId);
        this.hasDevider = hasDevider;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMainIconId() {
        return mainIconId;
    }

    public int getPressedIconId() {
        return pressedIconId;
    }


    public static List<SlidingMenuItem> fromDefaultSlidingMenuSet(Context mContext) {
        ArrayList<SlidingMenuItem> allItems = new ArrayList<SlidingMenuItem>();
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.profile_my_profile), R.drawable.ic_account_circle_grey600_24dp, R.drawable.pink_account_circle));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.radio_main), R.drawable.ic_radio_grey600_24dp, R.drawable.pink_radio));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.podcasts_main), R.drawable.ic_rss_grey600_24dp, R.drawable.pink_rss));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.cloud_sync), R.drawable.ic_cloud_upload_grey600_24dp, R.drawable.pink_cloud_upload, true));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.main_rate_app), R.drawable.ic_heart_grey600_24dp, R.drawable.pink_heart));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.main_about), R.drawable.ic_android_grey600_24dp, R.drawable.pink_android));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.main_help), R.drawable.ic_help_circle_grey600_24dp, R.drawable.pink_help_circle, true));
        allItems.add(new SlidingMenuRow(mContext.getString(R.string.main_settings), R.drawable.ic_settings_grey600_24dp, R.drawable.pink_settings));
        return allItems;
    }


}
