package ferit.cinema.web;

import ferit.cinema.feature.auditorium.Auditorium;
import ferit.cinema.feature.auditorium.AuditoriumDto;
import ferit.cinema.feature.auditorium.service.AuditoriumServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditoriums")
@CrossOrigin
@RequiredArgsConstructor
public class AuditoriumController {
    private final AuditoriumServiceImpl auditoriumService;
    @GetMapping
    public List<AuditoriumDto> getAllAuditoriums(){
        return auditoriumService.getAllAuditoriums();
    }
}
