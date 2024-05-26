package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.TimeDto;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.service.TimeService;

@RestController
@RequestMapping(value = "/times")
public class TimeRestController {
    
    @Autowired
    private TimeService timeService;

    @GetMapping
    public ResponseEntity<List<TimeDto>> obterTimes(){
        return ResponseEntity.ok().body(timeService.listarTimes());
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeDto> obterTime(@PathVariable Integer id){
        return ResponseEntity.ok().body(timeService.obterTime(id));
    }

    @PostMapping
    public ResponseEntity<TimeDto> criarTime(@RequestBody @Validated TimeDto time) throws Exception{
        ;
        return ResponseEntity.ok().body(timeService.cadastrarTimes(time));
    }

}
