package com.example.demo.service;

import com.example.demo.dto.AtualizarProdutosRequestDTO;
import com.example.demo.dto.SalvarProdutosRequestDTO;
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


    public Produtos atualiza(AtualizarProdutosRequestDTO dto) {
        Optional<Produtos> existe = procurarPorId(dto.id());

        if (existe.isPresent()) {
            Produtos atualizado = existe.get();
            atualizado.setId(atualizado.getId());
            atualizado.setNomeProd(dto.nomeProd());
            atualizado.setDescricaoProd(dto.descricaoProd());
            atualizado.setValidadeProd(dto.validadeProd());
            atualizado.setLoteProd(dto.loteProd());
            return prodRepository.save(atualizado);
        } else throw new RuntimeException("produto não encontrado");
    }

    public Produtos salvar(SalvarProdutosRequestDTO dto) {
        Produtos criar = new Produtos(
                dto.nomeProd(),
                dto.descricaoProd(),
                dto.validadeProd(),
                dto.loteProd());
        return prodRepository.save(criar);
    }

    public List<Produtos> procurarTodos() {
        return prodRepository.findAll();
    }

    public Optional<Produtos> produtosDados(SalvarProdutosRequestDTO produtosRequestDTO) {
        return prodRepository.findByNomeProdAndDescricaoProdAndValidadeProdAndLoteProd(produtosRequestDTO.nomeProd(), produtosRequestDTO.descricaoProd(), produtosRequestDTO.validadeProd(), produtosRequestDTO.loteProd());
    }

    public Optional<Produtos> procurarPorId(Long id) {
        return prodRepository.findById(id);
    }

    @Transactional
    public Produtos ativar(Long id) {
        Optional<Produtos> buscarProd = prodRepository.findById(id);
        try {
            if (buscarProd.isPresent()) {
                prodRepository.ativarProduto(id);
                Produtos produtos = buscarProd.get();
                produtos.setAtivo(true);
                return produtos;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public Produtos desativar(Long id) {
        Optional<Produtos> buscarProd = prodRepository.findById(id);
        try {
            if (buscarProd.isPresent()) {
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


    public void excluir(Long id) {
        prodRepository.deleteById(id);
    }
}
