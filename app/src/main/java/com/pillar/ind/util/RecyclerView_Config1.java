package com.pillar.ind.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pillar.ind.R;
import com.pillar.ind.model.WithoutPercent;
import com.pillar.ind.ui.main.Browser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Config1 {
    private Context mContext;
    String locale;
    Boolean value;
    private RecyclerView_Config1.CardAdapter mCardAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<WithoutPercent> cards, List<String> keys){
        mContext = context;
        mCardAdapter = new RecyclerView_Config1.CardAdapter(cards,keys);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mCardAdapter);

    }

    class CardItemView extends RecyclerView.ViewHolder {
        private TextView mComission;
        private TextView mFirst;
        private TextView mRepeated;
        private TextView mTime;
        private Button site;
        private String url;
        private ImageView image, rating_iv;
        private String key;
        String locale;
        public CardItemView(ViewGroup parent){
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
        public void bind(final WithoutPercent card, String key){
            mComission.setText(card.getComission());
            mFirst.setText(card.getFirst());
            mRepeated.setText(card.getRepeated());
            mTime.setText(card.getTime());
            if (value) {
                site.setVisibility(View.GONE);
            }


            this.key = key;
            site.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Browser.class);
                    url = card.getLink();
                    intent.putExtra("url", url);
                    mContext.startActivity(intent);
                }
            });
            Picasso.get().load(card.getImg_link()).into(image);
            Picasso.get().load(card.getRating_link()).into(rating_iv);

        }

    }

    class CardAdapter extends RecyclerView.Adapter<RecyclerView_Config1.CardItemView>{

        private List<WithoutPercent> mCardList;
        private List<String> mKeys;

        public CardAdapter(List<WithoutPercent> mCardList, List<String> mKeys) {
            this.mCardList = mCardList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecyclerView_Config1.CardItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView_Config1.CardItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView_Config1.CardItemView holder, int position) {
            holder.bind(mCardList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mCardList.size();
        }
    }
}
