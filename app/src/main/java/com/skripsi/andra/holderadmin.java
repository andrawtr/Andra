package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class holderadmin extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private Context mContext;
private Activity mActivity;
private ArrayList<Model> mContentList;



public holderadmin(Context mContext, Activity mActivity, ArrayList<Model> mContentList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_holderadmin, parent, false);
        return new ViewHolder(view, viewType);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvnamaa,tvlokasia,tvtamua,tvtanggala,tvjama,tvstatusa,tvuuida,tvnopea;
        private CardView cardView;
        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cva);
            cardView.setOnClickListener(this);
            tvnamaa = (TextView) itemView.findViewById(R.id.tvnamaa);
            tvlokasia = (TextView) itemView.findViewById(R.id.tvlokasia);
            tvtanggala = (TextView) itemView.findViewById(R.id.tvtanggala);
            tvtamua = (TextView) itemView.findViewById(R.id.tvtamua);
            tvjama = (TextView) itemView.findViewById(R.id.tvjama);
            tvstatusa = (TextView) itemView.findViewById(R.id.tvstatusa);
            tvuuida = (TextView) itemView.findViewById(R.id.tvuuida);
            tvnopea = (TextView) itemView.findViewById(R.id.tvphonea);


        }

        @Override
        public void onClick(View view) {
            Context ctx = ViewHolder.this.cardView.getContext();
            Intent intent = new Intent(ctx,ubahevent.class);
            intent.putExtra("nama", tvnamaa.getText());
            intent.putExtra("lokasi", tvlokasia.getText());
            intent.putExtra("tanggal", tvtanggala.getText());
            intent.putExtra("tamu", tvtamua.getText());
            intent.putExtra("jam", tvjama.getText());
            intent.putExtra("status", tvstatusa .getText());
            intent.putExtra("nope", tvnopea.getText());
            intent.putExtra("uuid", tvuuida.getText());
            ctx.startActivity(intent);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        final Model model = mContentList.get(position);
        // setting data over views
        holder.tvnamaa.setText(model.getJenis());
        holder.tvlokasia.setText(model.getLokasi());
        holder.tvtanggala.setText(model.getTanggal());
        holder.tvtamua.setText(model.getTamu());
        holder.tvjama.setText(model.getJam());
        holder.tvstatusa.setText(model.getStatus());
        holder.tvuuida.setText(model.getUuid());
        holder.tvnopea.setText(model.getNope());
    }
    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}