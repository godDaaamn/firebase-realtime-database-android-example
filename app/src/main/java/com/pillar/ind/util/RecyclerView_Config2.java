package com.pillar.ind.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pillar.ind.R;
import com.pillar.ind.model.WithoutCall;
import com.pillar.ind.ui.main.Browser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Config2 {
    private Context mContext;
    private ProgressBar mProgressBar;
    String locale;
    Boolean value;

    private RecyclerView_Config2.CreditAdapter mCreditAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<WithoutCall> credits, List<String> keys){
        mContext = context;
        mCreditAdapter = new RecyclerView_Config2.CreditAdapter(credits,keys);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mCreditAdapter);

    }

    class CreditItemView extends RecyclerView.ViewHolder {
        private TextView mComission;
        private TextView mFirst;
        private TextView mRepeated;
        private TextView mTime;
        private Button site;
        private String url;
        private ImageView image, rating_iv;
        private String key;

        String locale;

        public CreditItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.card,parent,false));
            value = false;

            locale = mContext.getResources().getConfiguration().locale.getCountry();
            if (locale.equals("RU")||locale.equals("UA")) {

            } else {
                value = true;
            }
            mComission = itemView.findViewById(R.id.commission_tv);
            mFirst = itemView.findViewById(R.id.first_credit_tv);
            mRepeated = itemView.findViewById(R.id.repeated_credit_tv);
            mTime = itemView.findViewById(R.id.time_tv);
            site = itemView.findViewById(R.id.button);
            image = itemView.findViewById(R.id.logo_iv);
            rating_iv = itemView.findViewById(R.id.stars_iv);


        }
        public void bind(final WithoutCall credit, String key){
            mComission.setText(credit.getComission());
            mFirst.setText(credit.getFirst());
            mRepeated.setText(credit.getRepeated());
            mTime.setText(credit.getTime());
            if (value) {
                site.setVisibility(View.GONE);
            }


            this.key = key;
            site.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Browser.class);
                    url = credit.getLink();
                    intent.putExtra("url", url);
                    mContext.startActivity(intent);
                }
            });
            Picasso.get().load(credit.getImg_link()).into(image);
            Picasso.get().load(credit.getRating_link()).into(rating_iv);
        }

    }

    class CreditAdapter extends RecyclerView.Adapter<RecyclerView_Config2.CreditItemView>{

        private List<WithoutCall> mCreditList;
        private List<String> mKeys;

        public CreditAdapter(List<WithoutCall> mCreditList, List<String> mKeys) {
            this.mCreditList = mCreditList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecyclerView_Config2.CreditItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView_Config2.CreditItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView_Config2.CreditItemView holder, int position) {
            holder.bind(mCreditList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mCreditList.size();
        }
    }
}
