package com.example.demo.service;

import com.example.demo.model.Produtos;
import com.example.demo.model.estoque.Enum.Status;
import com.example.demo.model.estoque.Enum.UnidadeMedida;
import com.example.demo.model.estoque.Estoque;
import com.example.demo.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServiceEstoque {

    @Autowired
    ServiceProd serviceProd;
    @Autowired
    EstoqueRepository estoqueRepository;

    public void registrarEntrada( Long produtoId, Integer quantidade, UnidadeMedida unidade){
        Optional<Estoque> estoqueOpt = estoqueRepository.findByProdutoId(produtoId);
        if (estoqueOpt.isPresent()) {
            Estoque estoque = estoqueOpt.get();
            estoque.setQuantidade(quantidade + estoque.getQuantidade());
            estoque.setStatus(Status.ATIVO);
            estoque.setUnidadeMedida(estoque.getUnidadeMedida());
            estoqueRepository.save(estoque);

        } else {
            Produtos produto = serviceProd.procurarPorId(produtoId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            Estoque novo = new Estoque(
                    produto,
                    quantidade,
                    Status.ATIVO,
                    unidade
            );
            estoqueRepository.save(novo);
        }

    }

    public void registrarSaida( Long idProd, Integer quantidadeSaida){

        Estoque estoque  = idFind(idProd).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

        if(estoque.getQuantidade() >= quantidadeSaida) {
            estoque.setQuantidade(estoque.getQuantidade() - quantidadeSaida);

            
            if(estoque.getQuantidade() == 0){
                estoque.setStatus(Status.INATIVO);
            }

        } else {
            throw new RuntimeException("Quantidade insuficiente em estoque");
        }
        estoqueRepository.save(estoque);
    }
    public Optional<Estoque> idFind(Long id){
        return estoqueRepository.findById(id);
    }


    // isso deve ser feito apenas por ADMIN
    public Estoque atualizarRegistro(Long id, Estoque dados) {
        return idFind(id).map(atualizado -> {
            atualizado.setQuantidade(dados.getQuantidade());
            atualizado.setStatus(dados.getStatus());
            atualizado.setUnidadeMedida(dados.getUnidadeMedida());
            atualizado.setLocalDateTime(LocalDateTime.now());
            return estoqueRepository.save(atualizado);

        }).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    // isso deve ser feito apenas por ADMIN
    public void deletandoRegistro(Long id){
        Optional<Estoque> dataId = idFind(id);
        if(dataId.isEmpty()){
            throw new RuntimeException("não encontrado");
        }
        estoqueRepository.deleteById(id);
    }
}
