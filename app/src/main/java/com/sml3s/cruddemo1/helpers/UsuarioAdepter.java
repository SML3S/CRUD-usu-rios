package com.sml3s.cruddemo1.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sml3s.cruddemo1.R;
import com.sml3s.cruddemo1.activites.AlteracaoUsuarioActivity;
import com.sml3s.cruddemo1.activites.ListaUsuarioActivity;
import com.sml3s.cruddemo1.dto.DtoUser;

import java.util.List;

public class UsuarioAdepter extends RecyclerView.Adapter<UsuarioAdepter.UsuarioHolder>{

    private LayoutInflater mInflater;
    private Context context;
    private List<DtoUser> lista;

    //swipe
    private DtoUser mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public UsuarioAdepter(Context context, List<DtoUser> lista) {

        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycleview_layout_item_todos_usuario, parent,false);
        return new UsuarioHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        String nome = lista.get(position).getName();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void deleteItem(int position){
        Toast.makeText(context, "excluindo item", Toast.LENGTH_LONG);
        mRecentlyDeletedItem = lista.get(position);
        mRecentlyDeletedItemPosition = position;
        lista.remove(position);
        notifyItemRemoved(position);
        showAlertDialogButtonClicked();
    }

    private void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Exclusão de usuario");
        builder.setMessage("Você confirma a ação");

        builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                unoDelete();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void excluir() {
        ((ListaUsuarioActivity) context).excluirItem(mRecentlyDeletedItem.getId());

    }

    private void unoDelete() {
        lista.add(mRecentlyDeletedItemPosition, mRecentlyDeletedItem);
        notifyItemRemoved(mRecentlyDeletedItemPosition);
    }

    public  Context getContext(){return  context;}

    public class UsuarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final UsuarioAdepter usuarioAdepter;
        public  final TextView nome;
        public UsuarioHolder(@NonNull View itemView, UsuarioAdepter usuarioAdepter) {
            super(itemView);
            this.usuarioAdepter = usuarioAdepter;
            nome = itemView.findViewById(R.id.tv_recycleview_nome_usuario);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DtoUser user =  lista.get(getLayoutPosition());
            String nome =  user.getName();
            int id = user.getId();
            String email = user.getEmail();
            String tel = user.getPhone();
            Intent intent = new Intent(context, AlteracaoUsuarioActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nome", nome);
            intent.putExtra("email", email);
            intent.putExtra("tel", tel);
            context.startActivity(intent);

        }
    }


}
