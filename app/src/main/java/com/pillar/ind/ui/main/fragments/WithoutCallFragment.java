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
import com.pillar.ind.model.WithoutCall;
import com.pillar.ind.util.FirebaseDatabaseHelper2;
import com.pillar.ind.util.RecyclerView_Config2;

import java.util.List;


public class WithoutCallFragment extends Fragment {

   private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_without_call, container, false);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        final int color = getResources().getColor(R.color.colorPrimary); // акцент color взял для примера, можно любой
        mProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mRecyclerView = root.findViewById(R.id.recyclerview_without_call);
        mProgressBar.setAlpha(1);
        new FirebaseDatabaseHelper2().readCredits(new FirebaseDatabaseHelper2.DataStatus2() {
            @Override
            public void DataIsLoaded(List<WithoutCall> zaims, List<String> keys) {
                new RecyclerView_Config2().setConfig(mRecyclerView,getContext(),zaims,keys);
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
