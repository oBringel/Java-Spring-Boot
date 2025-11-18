package com.example.demo.controller;

import com.example.demo.model.DTOs.UsersResquestDTO;
import com.example.demo.model.Usuarios;
import com.example.demo.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controllers {
    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuarios> salvar(@RequestBody UsersResquestDTO usersResquestDTO){
       try{
           // aqui eu passo no parametro o cpf que o usuario vai mandar, e assim ele valida se existe ou não
          Optional<Usuarios> buscarUsuarios = serviceUsuario.procurarPorCpf(usersResquestDTO.cpf());

            //se existir, ele vai atualizar apenas o nome, então nos pegamos os getters do buscarUsuarios( que está recebendo o metodo procurarPorCpf)
           if(buscarUsuarios.isPresent()){
               Usuarios busca = buscarUsuarios.get();
               // com isso, nos criamos um nome Usuário, passando o mesmo Id ja existente, passando o novo nome, e passando o mesmo cpf existente
               Usuarios atualizado = new Usuarios(
                       busca.getId(),
                       usersResquestDTO.nome(),
                       busca.getCpf()
               );
               // e assim salvamos, mas muita atenção, esse salvar tem dois métodos. o Salvar passando os paramentros DTO e o Salvar passando os paramentros Usuarios, lá em Service
               Usuarios salvo = serviceUsuario.salvar(atualizado);
               return  ResponseEntity.ok(salvo);
           }
           Usuarios criado = serviceUsuario.salvar(usersResquestDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(criado);

       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    }

}
