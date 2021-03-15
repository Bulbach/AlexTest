package by.ItAlex.AlexTest.controller;

import by.ItAlex.AlexTest.persistance.dto.InfoDto;
import by.ItAlex.AlexTest.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/add")
    public InfoDto createInfo(@ModelAttribute InfoDto infoDto) {
        try {
            return infoService.createInfo(infoDto);
        } catch (Exception e) {
            return new InfoDto();
        }
    }

    @PutMapping("/update/{id}")
    public InfoDto updateInfo(@ModelAttribute InfoDto infoDto) {
        try {
            return infoService.updateInfo(infoDto);
        } catch (Exception e) {
            return new InfoDto();
        }
    }


}
