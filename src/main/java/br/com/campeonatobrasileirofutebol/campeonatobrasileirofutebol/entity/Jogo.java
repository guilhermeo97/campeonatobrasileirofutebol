package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Jogos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Jogo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer golsTime1; 
    private Integer golsTime2; 
    private Integer publicoPagante;
    private LocalDateTime dataJogo;
    private Integer rodada;
    private Boolean encerrado;

    @ManyToOne
    @JoinColumn(name = "time1")
    private Time time1;

    @ManyToOne
    @JoinColumn(name = "time2")
    private Time time2;
}
