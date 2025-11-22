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
@RequestMapping("/usuario")
public class ControllerUser {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/{usuarios}")
    public ResponseEntity<Usuarios> get(@PathVariable Usuarios usuarios){
       return serviceUsuario.procurarPorId(usuarios.getId())
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todosUsuarios")
    public ResponseEntity<List<Usuarios>> getTodosUsuarios (){
        List<Usuarios> listarTodos = serviceUsuario.procurarTodos();
        if (listarTodos.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listarTodos);
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
    @Transactional
    @PatchMapping("/ativar")
    public  ResponseEntity<Usuarios> ativar (@RequestBody Usuarios usuarios){
        Optional<Usuarios> buscarCpf = serviceUsuario.procurarPorCpf(usuarios.getCpf());
        if (buscarCpf.isPresent()){
            Usuarios atualizado = serviceUsuario.ativar(usuarios.getCpf());
            return ResponseEntity.ok(atualizado);
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
