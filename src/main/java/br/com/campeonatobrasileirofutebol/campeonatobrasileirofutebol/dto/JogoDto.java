package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class JogoDto {
    private Integer id;
    private Integer golsTime1; 
    private Integer golsTime2; 
    private Integer publicoPagante;
    private LocalDateTime dataJogo;
    private Integer rodada;
    private Boolean encerrado;
    private TimeDto time1;
    private TimeDto time2; 
}
