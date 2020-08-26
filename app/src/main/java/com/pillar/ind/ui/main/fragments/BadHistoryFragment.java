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
import com.pillar.ind.model.BadHistory;
import com.pillar.ind.util.FirebaseDatabaseHelper3;
import com.pillar.ind.util.RecyclerView_Config3;

import java.util.List;


public class BadHistoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bad_history, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_bad_history);
        mProgressBar = root.findViewById(R.id.progressBar4);
        final int color = getResources().getColor(R.color.colorPrimary);
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mProgressBar.setAlpha(1);
        new FirebaseDatabaseHelper3().readBad(new FirebaseDatabaseHelper3.DataStatus3() {
            @Override
            public void DataIsLoaded(List<BadHistory> zaims, List<String> keys) {
                new RecyclerView_Config3().setConfig(mRecyclerView, getContext(), zaims, keys);
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