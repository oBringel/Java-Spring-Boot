package com.example.demo.controller;


import com.example.demo.model.Produtos;
import com.example.demo.model.Usuarios;
import com.example.demo.service.ServiceQRCode;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/{idUsuario}/qrcode/")
public class ControllerQrCode {
    @Autowired
    ServiceQRCode serviceQRCode;

    @GetMapping(value = "/{idproduto}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> gerarQRCode(@PathVariable Usuarios idUsuario, @PathVariable Produtos idproduto) throws IOException, WriterException {
        return   ResponseEntity.status(HttpStatus.OK).body(serviceQRCode.generateQRCode(idUsuario, idproduto));
    }

}
