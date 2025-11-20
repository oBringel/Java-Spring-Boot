package com.example.demo.service;

import com.example.demo.model.DTOs.UsersResquestDTO;
import com.example.demo.model.Usuarios;
import com.example.demo.repository.UsersRepository;
import jakarta.transaction.Transactional;
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

    public List<Usuarios> procurarTodos(){
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

    public Usuarios deleteByCpf(Long cpf){
       return usersRepository.deleteByCpf(cpf);
    }

    @Transactional
    public Usuarios desativar(Long cpf){
      Optional<Usuarios> optional = usersRepository.findByCpf(cpf);
      if (optional.isEmpty()){
          return  null;
      }
      usersRepository.desativar(cpf);
      Usuarios usuarios1 = optional.get();
      usuarios1.setAtivo(false);
      return usuarios1;
    }
    @Transactional
    public Usuarios ativar(Long cpf){
       Optional<Usuarios> optional = usersRepository.findByCpf(cpf);

        if (optional.isPresent()){
            usersRepository.ativar(cpf);
            Usuarios usuarios2 = optional.get();
            usuarios2.setAtivo(true);

            return usuarios2;
        }else {
            return null;
        }
    }


}
