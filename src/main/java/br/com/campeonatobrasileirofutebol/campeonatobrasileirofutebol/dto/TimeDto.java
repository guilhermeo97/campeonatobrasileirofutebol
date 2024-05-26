package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto;

import lombok.Data;

@Data
public class TimeDto{
    private Integer id;
    private String nomeTime;
    private String siglaTime;
    private String ufTime;
    private String estadoTime;
}