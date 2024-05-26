package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassificacaoDTO {

    private List<ClassificacaoTimeDTO> times = new ArrayList<>();

}
