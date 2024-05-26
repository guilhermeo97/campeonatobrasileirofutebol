package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.ClassificacaoDTO;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.JogoDto;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.service.JogoService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/jogos")
public class JogoRestController {
    
    @Autowired
    private JogoService jogoService;

    @PostMapping(value = "/gerar-jogos")
    public ResponseEntity<Void> gerarJogos() {
        jogoService.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<JogoDto>> obterJogos() {
        return ResponseEntity.ok().body(jogoService.listarJogos());
    }
    
    @PostMapping(value = "/finalizar/{id}")
    public ResponseEntity<JogoDto> finalizarJogo(@RequestBody JogoDto jogoDto, @PathVariable Integer id) throws Exception{
        return ResponseEntity.ok().body(jogoService.finalizar(jogoDto, id));
    }

    @GetMapping(value = "/classificacao")
    public ResponseEntity<ClassificacaoDTO> classificacao() {
        return ResponseEntity.ok().body(jogoService.obterClassificacao());
}


    @GetMapping(value = "/jogo/{id}")
    public ResponseEntity<JogoDto> obterJogo(@PathVariable Integer id){
        return ResponseEntity.ok().body(jogoService.obterJogo(id));
    }
}
