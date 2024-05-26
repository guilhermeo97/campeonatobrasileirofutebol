package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.dto.TimeDto;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.entity.Time;
import br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol.repository.TimeRepository;

@Service
public class TimeService{
    
    @Autowired
    private TimeRepository repository;

    
    public TimeDto cadastrarTimes(TimeDto time) throws Exception {
        Time entity = toEntity(time);
        if(time.getId() == null) {
            entity = repository.save(entity);
            return toDto(entity);
        } else{
           throw new Exception("Time já existe"); 
        }
    }

    private Time toEntity(TimeDto time) {
        Time entity = new Time();
        entity.setEstadoTime(time.getEstadoTime());
        entity.setUfTime(time.getUfTime());
        entity.setNomeTime(time.getNomeTime());
        entity.setSiglaTime(time.getSiglaTime());
        entity.setID(time.getId());
        return entity;
    }

    

    public List<TimeDto> listarTimes(){
        return repository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    public TimeDto obterTime(Integer id){
        return toDto(repository.findById(id).get());
    }

    public List<Time> findAll() {
        return repository.findAll();
    }

    public TimeDto toDto(Time entity) {
        TimeDto dto = new TimeDto();
        dto.setEstadoTime(entity.getEstadoTime());
        dto.setUfTime(entity.getUfTime());
        dto.setNomeTime(entity.getNomeTime());
        dto.setSiglaTime(entity.getSiglaTime());
        dto.setId(entity.getID());
        return dto;
    }

    public TimeDto entityToDto(Time entity) {
        TimeDto timeDTO = new TimeDto();
        timeDTO.setId(entity.getID());
        timeDTO.setNomeTime(entity.getNomeTime());
        timeDTO.setSiglaTime(entity.getSiglaTime());
        return timeDTO;
    }
}
