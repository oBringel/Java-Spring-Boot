package com.example.demo.controller;

import com.example.demo.model.DTOs.UsersResquestDTO;
import com.example.demo.model.Usuarios;
import com.example.demo.service.ServiceUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controllers {
    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ResponseEntity<Optional<Usuarios>> get(@RequestBody Usuarios usuarios){
        Optional<Usuarios> listarPorCpf = serviceUsuario.procurarPorCpf(usuarios.getCpf());

        return ResponseEntity.ok(listarPorCpf);
    }
    @GetMapping("/usuarios")
    public ResponseEntity<Optional<Usuarios>> getTodosUsuarios (@RequestBody  Usuarios usuarios){
        Optional<Usuarios> listarPorId = serviceUsuario.procurarPorId(usuarios.getId());
        return  ResponseEntity.ok(listarPorId);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuarios> salvar(@RequestBody UsersResquestDTO usersResquestDTO){
       try{
             // aqui eu passo no parametro o cpf que o usuario vai mandar, e assim ele valida se existe ou não
          Optional<Usuarios> buscarUsuarios = serviceUsuario.procurarPorCpf(usersResquestDTO.cpf());
             //se existir, ele vai atualizar apenas o nome, então nos pegamos os getters do buscarUsuarios( que está recebendo o metodo procurarPorCpf)
           if(buscarUsuarios.isPresent()){
               Usuarios busca = buscarUsuarios.get();
               // com isso, nos criamos um nome Usuário, passando o mesmo Id ja existente,  o novo nome, e o mesmo cpf existente
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

    @Transactional
    @PatchMapping("/inativar")
    public ResponseEntity<Usuarios> inativar(@RequestBody Usuarios usuarios){
        Optional<Usuarios> buscarUsuarios = serviceUsuario.procurarPorCpf(usuarios.getCpf());
        if (buscarUsuarios.isPresent()){
            Usuarios atualizado = serviceUsuario.desativar(usuarios.getCpf());
            return ResponseEntity.ok(atualizado);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
