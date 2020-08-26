package com.pillar.ind.util;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pillar.ind.model.BadHistory;


import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper3 {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCredits;
    private List<BadHistory> credits1 = new ArrayList<>();
    public interface DataStatus3{
        void DataIsLoaded(List<BadHistory> credits, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper3() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceCredits = mDatabase.getReference("bad_history");

    }

    public void readBad(final FirebaseDatabaseHelper3.DataStatus3 dataStatus3){
        mReferenceCredits.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                credits1.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    BadHistory credit1 = keyNode.getValue(BadHistory.class);
                    credits1.add(credit1);
                }
                dataStatus3.DataIsLoaded(credits1,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
