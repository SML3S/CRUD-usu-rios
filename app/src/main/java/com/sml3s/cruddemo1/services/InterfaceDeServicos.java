package com.sml3s.cruddemo1.services;

import androidx.annotation.HalfFloat;

import com.sml3s.cruddemo1.dto.DtoLogin;
import com.sml3s.cruddemo1.dto.DtoUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceDeServicos {

    @POST("/users")
    Call<DtoUser> cadastraUsuarios(@Body DtoUser dtoUser);

    @POST("/auth/login")
    Call<DtoLogin> login(@Body DtoLogin dtoLogin);

    @GET("/users")
    Call<List<DtoUser>> todosUsuarios(@Header("Authorization") String authorization);

    @PUT("/users/{id}")
    Call<DtoUser> alteraUsuario(@Body DtoUser user, @Path("id") int id, @Header("Authorization") String authorization);

    @DELETE("/users/{id}")
    Call<Void> excluirUsuario(@Path("id") int id, @Header("Authorization") String token);
}
