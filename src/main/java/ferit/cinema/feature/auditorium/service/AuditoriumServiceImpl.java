package ferit.cinema.feature.auditorium.service;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.auditorium.AuditoriumDto;
import ferit.cinema.feature.auditorium.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService{
    private final AuditoriumRepository auditoriumRepository;
    @Override
    public List<AuditoriumDto> getAllAuditoriums() {
        List<Auditorium> auditoriums = auditoriumRepository.findAll();
        List<AuditoriumDto> dtos = new ArrayList<>();
        for(Auditorium auditorium: auditoriums){
            AuditoriumDto dto = new AuditoriumDto(auditorium.getId(), auditorium.getName(), auditorium.getSeatsNo());
            dtos.add(dto);
        }
        return dtos;
    }
}
