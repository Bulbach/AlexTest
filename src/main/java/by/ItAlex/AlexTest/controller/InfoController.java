package by.ItAlex.AlexTest.controller;

import by.ItAlex.AlexTest.dto.Info;
import by.ItAlex.AlexTest.service.InfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Data
@RestController

@RequestMapping("/info")
public class InfoController {

     @Autowired
    private final InfoService infoService;

    @GetMapping("/add")
    public Info createInfo(@ModelAttribute Info info) {
        try {
            return infoService.createInfo(info);
        } catch (Exception e) {
            return new Info();
        }
    }

    @PutMapping("/update/{id}")
    public Info updateInfo(@ModelAttribute Info info) {
        try {
            return infoService.updateInfo(info);
        } catch (Exception e) {
            return new Info();
        }
    }


}
