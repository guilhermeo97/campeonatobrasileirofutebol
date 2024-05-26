package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    
    @Column(name = "nome_time")
    private String nomeTime;

    @Column(name = "sigla_time")
    private String siglaTime;

    @Column(name = "uf_time")
    private String ufTime;

    @Column(name = "estado_time")
    private String estadoTime;
}