package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Jogo;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Time;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    List<Jogo> findByTime1AndEncerrado(Time time1, boolean encerrado);

    List<Jogo> findByTime2AndEncerrado(Time time2, boolean encerrado);  
}
