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
import com.pillar.ind.model.WithoutPercent;
import com.pillar.ind.util.FirebaseDatabaseHelper1;
import com.pillar.ind.util.RecyclerView_Config1;

import java.util.List;


public class WithoutPercentFragment extends Fragment {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_without_percent, container, false);
        mProgressBar = root.findViewById(R.id.progressBar2);
        final int color = getResources().getColor(R.color.colorPrimary);
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mProgressBar.setAlpha(1);
        mRecyclerView = root.findViewById(R.id.recyclerview_without_percent);
        new FirebaseDatabaseHelper1().readCards(new FirebaseDatabaseHelper1.DataStatus1() {
            @Override
            public void DataIsLoaded(List<WithoutPercent> zaims, List<String> keys) {
                new RecyclerView_Config1().setConfig(mRecyclerView,getContext(),zaims,keys);
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