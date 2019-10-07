package com.sml3s.cruddemo1.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonParseException;
import com.sml3s.cruddemo1.R;
import com.sml3s.cruddemo1.dto.DtoUser;

import java.util.List;

public class UsuarioAdepter extends RecyclerView.Adapter<UsuarioAdepter.UsuarioHolder>{

    private LayoutInflater mInflater;
    private Context context;
    private List<DtoUser> lista;

    public UsuarioAdepter(Context context, List<DtoUser> lista) {

        this.context = context;
        this.lista = lista;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UsuarioAdepter.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_recycleview_layout_item_todos_usuario, parent,false);
        return new UsuarioHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdepter.UsuarioHolder holder, int position) {
        String nome = lista.get(position).getName();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder{
        final UsuarioAdepter usuarioAdepter;
        public  final TextView nome;
        public UsuarioHolder(@NonNull View itemView, UsuarioAdepter usuarioAdepter) {
            super(itemView);
            this.usuarioAdepter = usuarioAdepter;
            this.nome = itemView.findViewById(R.id.tv_recycleview_nome_usuario);
        }
    }


}
