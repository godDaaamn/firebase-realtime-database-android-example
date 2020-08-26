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
import com.pillar.ind.model.All;
import com.pillar.ind.ui.main.Browser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private ZaimAdapter mZaimAdapter;
    public static Boolean value;

    public void setConfig(RecyclerView recyclerView, Context context, List<All> zaims, List<String> keys) {
        mContext = context;
        mZaimAdapter = new ZaimAdapter(zaims, keys);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mZaimAdapter);
    }

        class ZaimItemView extends RecyclerView.ViewHolder {
            private TextView mComission;
            private TextView mFirst;
            private TextView mRepeated;
            private TextView mTime;
            private Button site;
            private String url;
            private ImageView image, rating_iv;
            private String key;
            String locale;

            public ZaimItemView(ViewGroup parent) {
                super(LayoutInflater.from(mContext).inflate(R.layout.card, parent, false));
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

            public void bind(final All zaim, String key) {
                mComission.setText(zaim.getComission());
                mFirst.setText(zaim.getFirst());
                mRepeated.setText(zaim.getRepeated());
                mTime.setText(zaim.getTime());
                if (value) {
                    site.setVisibility(View.GONE);
                }


                this.key = key;
                site.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Browser.class);
                        url = zaim.getLink();
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    }
                });
                Picasso.get().load(zaim.getImg_link()).into(image);
                Picasso.get().load(zaim.getRating_link()).into(rating_iv);


            }

        }

        class ZaimAdapter extends RecyclerView.Adapter<ZaimItemView> {

            private List<All> mZaimList;
            private List<String> mKeys;

            public ZaimAdapter(List<All> mZaimList, List<String> mKeys) {
                this.mZaimList = mZaimList;
                this.mKeys = mKeys;
            }

            @NonNull
            @Override
            public ZaimItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ZaimItemView(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull ZaimItemView holder, int position) {
                holder.bind(mZaimList.get(position), mKeys.get(position));
            }

            @Override
            public int getItemCount() {
                return mZaimList.size();
            }


        }


    }

