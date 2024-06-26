package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.ClassificacaoDTO;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.ClassificacaoTimeDTO;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.JogoDto;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Jogo;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Time;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.repository.JogoRepository;

@Service
public class JogoService {
    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private TimeService timeService;

    public void gerarJogos(LocalDateTime primeiraRodada) {
        final List<Time> times = timeService.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);//.subList(0, times.size()/2));
        all2.addAll(times);//.subList(all1.size(), times.size()));

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size() / 2;
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for (int i = 0; i < t - 1; i++) {
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time  1 null");
                }
                jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            jogos2.add(gerarJogo(jogo.getDataJogo().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
    }

    private JogoDto toDTO(Jogo entity) {
        JogoDto dto = new JogoDto();
        dto.setId(entity.getId());
        dto.setDataJogo(entity.getDataJogo());
        dto.setEncerrado(entity.getEncerrado());
        dto.setGolsTime1(entity.getGolsTime1());
        dto.setGolsTime2(entity.getGolsTime2());
        dto.setPublicoPagante(entity.getPublicoPagante());
        dto.setRodada(entity.getRodada());
        dto.setTime1(timeService.entityToDto(entity.getTime1()));
        dto.setTime2(timeService.entityToDto(entity.getTime2()));
        return dto;
    }


    private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
        Jogo jogo = new Jogo();
        jogo.setTime1(time1);
        jogo.setTime2(time2);
        jogo.setRodada(rodada);
        jogo.setDataJogo(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(0);
        jogo.setGolsTime2(0);
        jogo.setPublicoPagante(0);
        return jogo;
    }

    public List<JogoDto> listarJogos() {
        return jogoRepository.findAll().stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }   
    
    public JogoDto finalizar(JogoDto jogoDto, Integer id) throws Exception{
        Optional<Jogo> optionalJogo = jogoRepository.findById(id);
        if(optionalJogo.isPresent()){
            final Jogo jogo = optionalJogo.get();
            jogo.setGolsTime1(jogoDto.getGolsTime1());
            jogo.setGolsTime2(jogoDto.getGolsTime2());
            jogo.setEncerrado(true);
            jogo.setPublicoPagante(jogoDto.getPublicoPagante());
            return toDTO(jogoRepository.save(jogo));
        }else{
            throw new Exception("Jogo não existe");
        }
    }

    public JogoDto obterJogo(Integer id){ 
        return toDTO(jogoRepository.findById(id).get());
    }

    public ClassificacaoDTO obterClassificacao(){
        ClassificacaoDTO classificacaoDTO = new ClassificacaoDTO();
        classificacaoDTO.setTimes(new ArrayList<>()); // Inicializa a lista de times
        final List<Time> times = timeService.findAll();
    
        times.forEach(time -> {
        final List<Jogo> jogosMandante = jogoRepository.findByTime1AndEncerrado(time, true);
        final List<Jogo> jogosVisitante = jogoRepository.findByTime2AndEncerrado(time, true);
        
        int vitorias = 0;
        int derrotas = 0;
        int empates = 0;
        int golsSofridos = 0;
        int golsMarcados = 0;
        int pontos = 0;
        
        for (Jogo jogo : jogosMandante) {
            golsMarcados += jogo.getGolsTime1();
            golsSofridos += jogo.getGolsTime2();
            if (jogo.getGolsTime1() > jogo.getGolsTime2()) {
                vitorias++;
            } else if (jogo.getGolsTime1() < jogo.getGolsTime2()) {
                derrotas++;
            } else {
                empates++;
            }
        }

        for (Jogo jogo : jogosVisitante) {
            golsMarcados += jogo.getGolsTime2();
            golsSofridos += jogo.getGolsTime1();
            if (jogo.getGolsTime2() > jogo.getGolsTime1()) {
                vitorias++;
                pontos += 3;
            } else if (jogo.getGolsTime2() < jogo.getGolsTime1()) {
                derrotas++;
            } else {
                empates++;
                pontos += 1;
            }
        }
        
        ClassificacaoTimeDTO classificacaoTimeDTO = new ClassificacaoTimeDTO();
        classificacaoTimeDTO.setDerrotas(derrotas);
        classificacaoTimeDTO.setEmpates(empates);
        classificacaoTimeDTO.setVitorias(vitorias);
        classificacaoTimeDTO.setGolsMarcados(golsMarcados);
        classificacaoTimeDTO.setGolsSofridos(golsSofridos);
        classificacaoTimeDTO.setPontos(pontos);
        
        classificacaoDTO.getTimes().add(classificacaoTimeDTO);
    });
    Collections.sort(classificacaoDTO.getTimes(), Collections.reverseOrder());
    int posicao = 0;
    for(ClassificacaoTimeDTO time : classificacaoDTO.getTimes()){
        time.setPosicao(posicao++);
    }

    return classificacaoDTO;
    }
}
