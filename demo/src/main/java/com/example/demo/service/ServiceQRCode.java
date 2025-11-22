package com.example.demo.service;


import com.example.demo.model.Produtos;
import com.example.demo.model.Usuarios;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class ServiceQRCode {
    @Autowired
    ServiceUsuario serviceUsuario;
    @Autowired
    ServiceProd serviceProd;
    @Autowired
    ToStringQrCode  toStringQrCode;

    public byte[] generateQRCode(Usuarios idUsuario, Produtos idProduto)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Optional<Usuarios> usuarios1 = serviceUsuario.procurarPorId(idUsuario.getId());
        Optional<Produtos> produtos1 = serviceProd.procurarPorId(idProduto.getId());

        if (usuarios1.isEmpty() || produtos1.isEmpty()) {
            throw new RuntimeException("Usuário ou Produto não encontrado");
        }
        String conteudo = toStringQrCode.getString(idUsuario, idProduto);
        var bitMatrix = qrCodeWriter.encode(conteudo, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);


        return pngOutputStream.toByteArray();
    }


}