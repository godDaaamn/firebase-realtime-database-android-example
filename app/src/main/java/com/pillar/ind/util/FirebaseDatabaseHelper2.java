package com.pillar.ind.util;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pillar.ind.model.WithoutCall;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper2 {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCredits;
    private List<WithoutCall> credits = new ArrayList<>();
    public interface DataStatus2{
        void DataIsLoaded(List<WithoutCall> credits, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper2() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceCredits = mDatabase.getReference("without_call");

    }

    public void readCredits(final FirebaseDatabaseHelper2.DataStatus2 dataStatus2){
        mReferenceCredits.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                credits.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    WithoutCall credit = keyNode.getValue(WithoutCall.class);
                    credits.add(credit);
                }
                dataStatus2.DataIsLoaded(credits,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
