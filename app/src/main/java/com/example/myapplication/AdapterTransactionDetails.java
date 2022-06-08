package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTransactionDetails extends RecyclerView.Adapter<AdapterTransactionDetails.viewHolder>  {
    Context context;
    ArrayList<TransactionInfo> transactionInfo;

    public AdapterTransactionDetails(Context context, ArrayList<TransactionInfo> transactionInfo) {
        this.context = context;
        this.transactionInfo = transactionInfo;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_transaction,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TransactionInfo model=transactionInfo.get(position);
        holder.transactionId.setText(model.getTransactionId());
        holder.date.setText(model.getDate());
        holder.amount.setText("Rs. "+model.getAmount());
        holder.toFrom.setText(model.getTo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransactionDetailActivity.class);
                intent.putExtra("TransactionId",model.getTransactionId());
                intent.putExtra("Date",model.getDate());
                intent.putExtra("Amount",model.getAmount());
                intent.putExtra("toFrom",model.getTo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionInfo.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        CardView crdDetails;
        TextView transactionId;
        TextView date;
        TextView amount;
        TextView toFrom;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            crdDetails=itemView.findViewById(R.id.cardInfo);
            transactionId=itemView.findViewById(R.id.ShopName);
            date=itemView.findViewById(R.id.Name);
            amount=itemView.findViewById(R.id.Number);
            toFrom=itemView.findViewById(R.id.City);

        }
    }
}
