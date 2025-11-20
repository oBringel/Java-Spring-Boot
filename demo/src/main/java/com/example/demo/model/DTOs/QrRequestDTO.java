package com.example.demo.model.DTOs;

import com.example.demo.model.Produtos;

public record QrRequestDTO( String uuid,
         UsersResquestDTO usersResquestDTO,  Produtos produtos) {
}
