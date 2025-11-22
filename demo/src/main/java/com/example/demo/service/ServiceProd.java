package com.example.demo.service;

import com.example.demo.model.DTOs.ProdutosRequestDTO;
import com.example.demo.model.Produtos;
import com.example.demo.repository.ProdRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProd {
    @Autowired
    private ProdRepository prodRepository;

    public Produtos salvar(Produtos produtos) {
        return prodRepository.save(produtos);
    }

    public Produtos salvar(ProdutosRequestDTO produtosRequestDTO){
        Produtos produtos = new Produtos(
                null,
                produtosRequestDTO.nomeProd(),
                produtosRequestDTO.descricaoProd(),
                produtosRequestDTO.validadeProd(),
                produtosRequestDTO.loteProd()
        );
        return prodRepository.save(produtos);
    }

    public List<Produtos> procurarTodos(){
        return  prodRepository.findAll();
    }

    public Optional<Produtos> produtosDados(ProdutosRequestDTO produtosRequestDTO){
        return  prodRepository.findByNomeProdAndDescricaoProdAndValidadeProdAndLoteProd(produtosRequestDTO.nomeProd(), produtosRequestDTO.descricaoProd(), produtosRequestDTO.validadeProd(), produtosRequestDTO.loteProd());
    }
    public Optional<Produtos> procurarPorId(Long id){
        return  prodRepository.findById(id);
    }

    @Transactional
    public Produtos ativar(Long id){
        Optional<Produtos> buscarProd = prodRepository.findById(id);
        try{
            if (buscarProd.isPresent()){
                prodRepository.ativarProduto(id);
                Produtos produtos =  buscarProd.get();
                produtos.setAtivo(true);
                return produtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Transactional
    public  Produtos desativar (Long id){
        Optional<Produtos> buscarProd = prodRepository.findById(id);
        try{
            if (buscarProd.isPresent()){
                prodRepository.desativarProduto(id);
                Produtos produtos = buscarProd.get();
                produtos.setAtivo(false);
                return produtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void excluir(Long id){
         prodRepository.deleteById(id);
    }
}
