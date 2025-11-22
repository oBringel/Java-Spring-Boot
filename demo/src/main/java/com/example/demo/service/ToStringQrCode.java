package com.example.demo.service;

import com.example.demo.model.Produtos;
import com.example.demo.model.QrCode;
import com.example.demo.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
@Service
public class ToStringQrCode {
    @Autowired
    ServiceUsuario serviceUsuario;
    @Autowired
    ServiceProd serviceProd;



    public String getString(Usuarios idUsers, Produtos idProd) {
        Optional<Usuarios> usuarios1 = serviceUsuario.procurarPorId(idUsers.getId());
        Optional<Produtos> produtos1 = serviceProd.procurarPorId(idProd.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        QrCode qrCode = new QrCode(idProd, idUsers);

        String localDateTime = qrCode.getLocalDateTime().format(formatter);

        if (usuarios1.isPresent() && produtos1.isPresent()) {
            Usuarios buscandoUsuario = usuarios1.get();
            Produtos buscandoProd = produtos1.get();
            return
                    "ID do Produto: " + buscandoProd.getId() + "\n" +
                    "Nome do Produto: " + buscandoProd.getNomeProd() + "\n" +
                    "Lote: " + buscandoProd.getLoteProd() + "\n" +
                    "Data de validade: " + buscandoProd.getValidadeProd() + "\n" + "\n" + "\n" +
                    "UUID do QrCode: " + qrCode.getUuid()  + "\n" + "\n" +
                     "ID Colaborador: " + buscandoUsuario.getId() + "\n" +
                    "Colaborador: " + buscandoUsuario.getNome() + "\n" + "\n" +
                    "Data: " + localDateTime
                    ;
        }
        return "erro";
    }
}
