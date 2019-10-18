package com.sml3s.cruddemo1.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sml3s.cruddemo1.R;
import com.sml3s.cruddemo1.dto.DtoUser;
import com.sml3s.cruddemo1.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlteracaoUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "AlteracaoUsuarioActivit";
    EditText et_nome, et_email, et_telefone;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_usuario);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String nome = intent.getStringExtra("nome");
        String email = intent.getStringExtra("email");
        String tel = intent.getStringExtra("tel");

        et_email = findViewById(R.id.et_cadastro_usuario_email);
        et_nome = findViewById(R.id.et_cadastro_usuario_name);
        et_telefone = findViewById(R.id.et_cadastro_usuario_phone);

        et_telefone.setText(tel);
        et_nome.setText(nome);
        et_email.setText(email);
    }

    public void cadastrar(View view) {
        String nome = ((EditText) findViewById(R.id.et_cadastro_usuario_name)).getText().toString();
        String telefone = ((EditText) findViewById(R.id.et_cadastro_usuario_phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.et_cadastro_usuario_email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.et_cadastro_usuario_password)).getText().toString();
        DtoUser dtoUser;
        if(senha.isEmpty())
            dtoUser = new DtoUser(email,nome,senha ,telefone);
            else
                dtoUser = new DtoUser(email, nome, senha, telefone);
             String token = getTokem();
        RetrofitService.getServico(this).alteraUsuario(dtoUser, id,  "Bearer " + token).enqueue(new Callback<DtoUser>() {
            @Override
            public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                if(response.code()== 200)
                    Toast.makeText(AlteracaoUsuarioActivity.this, "Usuario  alterado com sucesso", Toast.LENGTH_LONG );
                else
                    Toast.makeText(AlteracaoUsuarioActivity.this, "Erro", Toast.LENGTH_LONG );
            }

            @Override
            public void onFailure(Call<DtoUser> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    private String getTokem() {
        SharedPreferences sp = getSharedPreferences("dados",0);
        return  sp.getString("token",null);
    }
}
