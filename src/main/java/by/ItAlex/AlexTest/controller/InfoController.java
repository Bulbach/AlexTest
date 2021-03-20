package by.ItAlex.AlexTest.controller;

import by.ItAlex.AlexTest.persistance.dto.InfoDto;
import by.ItAlex.AlexTest.persistance.repository.InfoRepository;
import by.ItAlex.AlexTest.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;
    private final InfoRepository infoRepository;

    @PostMapping("/add")
    public InfoDto createInfo(@RequestBody InfoDto infoDto) {

            return infoService.createInfo(infoDto);

    }

    @PutMapping("/update/{id}")
    public InfoDto updateInfo(@ModelAttribute InfoDto infoDto) {
        try {
            return infoService.updateInfo(infoDto);
        } catch (Exception e) {
            return new InfoDto();
        }
    }
    @GetMapping("/all")
    public List<InfoDto> getAll() {
        return infoRepository.findAllByOrderByDateAsc()
                .stream()
                .map(i -> new InfoDto().modelToDto(i))
                .collect(Collectors.toList());
    }

}
