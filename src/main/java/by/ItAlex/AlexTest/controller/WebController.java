package by.ItAlex.AlexTest.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Data
@Controller
public class WebController {

//    private final ToolRepository toolRepository;
//    private final ToolEntity toolEntity;

    @GetMapping("/")
    public String index(){
//        model.addAllAttributes(Set.of(toolRepository.findAll(),toolRepository);
        return "index";
    }
}
