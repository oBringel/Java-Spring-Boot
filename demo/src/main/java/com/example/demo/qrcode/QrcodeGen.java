package com.example.demo.qrcode;

import com.example.demo.model.DTOs.ProdutosRequestDTO;
import com.example.demo.model.Produtos;
import com.example.demo.repository.ProdRepository;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;

public class QrcodeGen {

    public Produtos qrcodeProd(ProdutosRequestDTO produtosRequestDTO){
        try{
        Gson gson = new Gson();
        String conteudoQr = gson.toJson(produtosRequestDTO);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(conteudoQr,  BarcodeFormat.QR_CODE, 300, 300);}
        catch (WriterException e) {
            throw new RuntimeException(e);
        }
        return  null;
    }

}
