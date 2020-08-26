package com.pillar.ind.ui.main.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pillar.ind.R;
import com.pillar.ind.model.All;
import com.pillar.ind.util.FirebaseDatabaseHelper;
import com.pillar.ind.util.RecyclerView_Config;

import java.util.List;

public class AllFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_all);
        mProgressBar = root.findViewById(R.id.progressBar3);
        final int color = getResources().getColor(R.color.colorPrimary);
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mProgressBar.setAlpha(1);
        new FirebaseDatabaseHelper().readZaims(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<All> zaims, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,getContext(),zaims,keys);
                mProgressBar.setAlpha(0);


            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        return root;
    }
}