package com.pillar.ind.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pillar.ind.ui.main.fragments.AllFragment;
import com.pillar.ind.R;
import com.pillar.ind.ui.main.fragments.BadHistoryFragment;
import com.pillar.ind.ui.main.fragments.WithoutCallFragment;
import com.pillar.ind.ui.main.fragments.WithoutPercentFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.fragment_all,R.string.fragment_without_percent,R.string.fragment_without_call,R.string.fragment_bad_history};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new AllFragment();
                break;
            case 1 :
                fragment = new WithoutPercentFragment();
                break;
            case 2 :
                fragment = new WithoutCallFragment();
                break;
            case 3 :
                fragment = new BadHistoryFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}