package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    AdapterTransactionDetails adapterDetails;
    ArrayList<TransactionInfo> transactionInformation;
    RecyclerView recyclerTransaction;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        recyclerTransaction=findViewById(R.id.recycler_seller_infoBank);
        reference= FirebaseDatabase.getInstance().getReference("UserInfo");
        recyclerTransaction.setHasFixedSize(true);
        recyclerTransaction.setLayoutManager(new LinearLayoutManager(this));
        transactionInformation=new ArrayList<>();
        adapterDetails=new AdapterTransactionDetails(this,transactionInformation);
        recyclerTransaction.setAdapter(adapterDetails);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    String transactionId=snapshot.child("transactionId").getValue().toString();
//                    String amount=snapshot.child("amount").getValue().toString();
//                    String date=snapshot.child("date").getValue().toString();
//                    String to=snapshot.child("to").getValue().toString();
                    TransactionInfo model= snapshot.getValue(TransactionInfo.class);
                    transactionInformation.add(model);
                }
                Log.d("Msg", String.valueOf(transactionInformation.size()));
                adapterDetails.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}