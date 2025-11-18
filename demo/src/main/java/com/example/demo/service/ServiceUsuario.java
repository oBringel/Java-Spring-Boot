package com.example.demo.service;

import com.example.demo.model.DTOs.UsersResquestDTO;
import com.example.demo.model.Usuarios;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUsuario {
    @Autowired
    private UsersRepository usersRepository;

    public Usuarios salvar(UsersResquestDTO dados) {
        Usuarios usuario = new Usuarios(
                null,
                dados.nome(),
                dados.cpf()
        );
        return usersRepository.save(usuario);
    }
    public Usuarios salvar(Usuarios usuario) {
        return usersRepository.save(usuario);
    }

    public List<Usuarios> procurar(){
        return usersRepository.findAll();
    }
    //Entender o "pq" usar Optional;
    public Optional<Usuarios> procurarPorId(Long id){
        return usersRepository.findById(id);
    }

    public Optional<Usuarios> procurarPorCpf(Long cpf){
        return usersRepository.findByCpf(cpf);
    }

    public void deletarPorId(Long id){
        usersRepository.deleteById(id);
    }
}
