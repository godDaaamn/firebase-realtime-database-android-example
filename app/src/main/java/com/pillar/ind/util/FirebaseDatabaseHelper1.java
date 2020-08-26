package com.pillar.ind.util;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pillar.ind.model.WithoutPercent;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper1 {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCards;
    private List<WithoutPercent> cards = new ArrayList<>();
    public interface DataStatus1{
        void DataIsLoaded(List<WithoutPercent> cards, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper1() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceCards = mDatabase.getReference("without_percent");

    }

    public void readCards(final FirebaseDatabaseHelper1.DataStatus1 dataStatus1){
        mReferenceCards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cards.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                   WithoutPercent card = keyNode.getValue(WithoutPercent.class);
                    cards.add(card);
                }
                dataStatus1.DataIsLoaded(cards,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
