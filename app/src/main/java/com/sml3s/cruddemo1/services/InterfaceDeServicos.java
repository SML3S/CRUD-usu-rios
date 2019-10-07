package com.sml3s.cruddemo1.services;

import com.sml3s.cruddemo1.dto.DtoLogin;
import com.sml3s.cruddemo1.dto.DtoUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceDeServicos {

    @POST("/users")
    Call<DtoUser> cadastraUsuarios(@Body DtoUser dtoUser);

    @POST("/auth/login")
    Call<DtoLogin> login(@Body DtoLogin dtoLogin);

    @GET("/usder")
    Call<List<DtoUser>> todosUsuarios(@Header("Authorization") String authorization);
}
