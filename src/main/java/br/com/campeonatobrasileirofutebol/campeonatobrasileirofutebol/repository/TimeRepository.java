package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>{
    
}
