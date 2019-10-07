package com.sml3s.cruddemo1.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.sml3s.cruddemo1.R;
import com.sml3s.cruddemo1.dto.DtoUser;
import com.sml3s.cruddemo1.helpers.UsuarioAdepter;
import com.sml3s.cruddemo1.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaUsuarioActivity extends AppCompatActivity {

    private static final String TAG ="ListaUsuarioActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        buscarDados();
    }

    private void preencheRecycleview(List<DtoUser> lista){
        RecyclerView mRecycleView = findViewById(R.id.rv_todos_usuarios);
        UsuarioAdepter mAdapter = new UsuarioAdepter(this,lista);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void buscarDados() {
        SharedPreferences sp = getSharedPreferences("dados", 0);
        String token = sp.getString("token", null);

        RetrofitService.getServico(this).todosUsuarios("Bearer"+token).enqueue(new Callback<List<DtoUser>>() {

            @Override
            public void onResponse(Call<List<DtoUser>> call, Response<List<DtoUser>> response) {
                List<DtoUser> lista = response.body();
                preencheRecycleview(lista);
            }

            @Override
            public void onFailure(Call<List<DtoUser>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
