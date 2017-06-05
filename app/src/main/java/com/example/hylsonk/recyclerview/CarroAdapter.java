package com.example.hylsonk.recyclerview;

import android.content.Context;
import android.support.v7.widget.*;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hylsonk.recyclerview.domain.Carro;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nelson on 04/06/2017.
 */

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarrosViewHolder> {
    protected static final String TAG = "livroandroid";
    private final List<Carro> carros;
    private final Context context;
    private CarroOnClickListener carroOnClickListener;

    public CarroAdapter(List<Carro> carros, Context context, CarroOnClickListener carroOnClickListener) {
        this.carros = carros;
        this.context = context;
        this.carroOnClickListener = carroOnClickListener;
    }

    public int getItemCount(){
        return this.carros!=null ? this.carros.size():0;
    }

    public CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro,viewGroup,false);
        CarrosViewHolder holder = new CarrosViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final CarrosViewHolder holder, final int position){
        // Atualiza a View
        Carro c = carros.get(position);
        holder.tNome.setText(c.nome);
        holder.progress.setVisibility(View.VISIBLE);
        // Faz o download da foto e mostra o ProgressBar
        Picasso.with(context).load(c.urlFoto).fit().into(holder.img, new com.squareup.picasso.Callback(){

            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE); //download ok
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });
        // Click
        if (carroOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    // A variavel position é final
                    carroOnClickListener.onClickCarro(holder.itemView, position);
                }
            });
        }
    }
    public interface CarroOnClickListener{
        public void onClickCarro(View view, int idx);
    }
    // ViewHolder com as views
    public static class CarrosViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progress;
        CardView cardView;
        public CarrosViewHolder(View view){
            super(view);
            tNome = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
