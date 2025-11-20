package com.example.demo.qrcode;

import com.example.demo.model.DTOs.ProdutosRequestDTO;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;

public class QrcodeGen {

    public static void qrcodeProd(ProdutosRequestDTO produtosRequestDTO) throws IOException{
        try{
        Gson gson = new Gson();
        String path = "C:\\Users\\Áquila Bringel\\Downloads\\demo\\demo\\src\\main\\java\\com\\example\\demo\\jpgQrCode";
        String conteudoQr = gson.toJson(produtosRequestDTO);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(conteudoQr,  BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(path));
        }
        catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

}
