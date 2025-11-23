package com.example.demo.model.qrcode;

import com.example.demo.model.Produtos;
import com.example.demo.model.Usuarios;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class QrCode {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    private Produtos produtos;

    @ManyToOne
    private Usuarios usuarios;


    private LocalDateTime localDateTime;
    protected QrCode() {}

    public QrCode(Produtos produtos, Usuarios usuarios) {
        this.produtos = produtos;
        this.usuarios = usuarios;
        this.uuid = UUID.randomUUID();
        this.localDateTime = LocalDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
