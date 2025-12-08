package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.enums.UnidadeMedida;
import com.example.demo.model.Estoque;
import com.example.demo.model.Produtos;
import com.example.demo.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEstoque {


    @Autowired
    EstoqueRepository estoqueRepository;
    @Autowired
    ServiceProd serviceProd;

    public void registrarEntrada(Long produtoId, Integer quantidade, UnidadeMedida unidade) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findByProduto_Id(produtoId);
        if (estoqueOpt.isPresent()) {
            Estoque estoque = estoqueOpt.get();
            estoque.setQuantidade(quantidade + estoque.getQuantidade());
            estoque.setStatus(Status.ATIVO);
            estoque.setUnidadeMedida(estoque.getUnidadeMedida());
            estoque.setDataRequisicao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            estoqueRepository.save(estoque);

        } else {
            Produtos produto = serviceProd.procurarPorId(produtoId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            Estoque novo = new Estoque(
                    produto,
                    quantidade,
                    Status.ATIVO,
                    unidade,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            );
            estoqueRepository.save(novo);
        }

    }

    public void registrarSaida(Long id, Integer quantidadeSaida) {
        Estoque produtoNoEstoque = estoqueRepository.findByProduto_Id(id)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

        if (produtoNoEstoque.getQuantidade() >= quantidadeSaida) {

            idReferenciaProd(produtoNoEstoque.getId()).map(atualiza -> {
                        atualiza.setId(atualiza.getId());
                        atualiza.setQuantidade(atualiza.getQuantidade() - quantidadeSaida);
                        return estoqueRepository.save(atualiza);
                    }
            ).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Quantidade insuficiente em estoque"));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Erro na requisição"
            );
        }
    }

    public Optional<Estoque> idFind(Long id) {
        return estoqueRepository.findById(id);
    }

    public Optional<Estoque> idReferenciaProd(Long id) {
        return estoqueRepository.findById(id);
    }

    // isso deve ser feito apenas por ADMIN
    public Estoque atualizarRegistro(Long id, Estoque dados) {

        return idFind(id).map(atualizado -> {

            atualizado.setQuantidade(dados.getQuantidade());
            atualizado.setStatus(dados.getStatus());
            atualizado.setUnidadeMedida(dados.getUnidadeMedida());
            atualizado.setDataRequisicao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            return estoqueRepository.save(atualizado);

        }).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    // isso deve ser feito apenas por ADMIN
    public void deletandoRegistro(Long id) {
        Optional<Estoque> dataId = idFind(id);
        if (dataId.isEmpty()) {
            throw new RuntimeException("não encontrado");
        }
        estoqueRepository.deleteById(id);
    }

    //buscar ID do produto
    public List<Estoque> procurarTodos() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> procurarPorId(Long id) {
        return estoqueRepository.findById(id);
    }


}
