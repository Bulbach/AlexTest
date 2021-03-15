package by.ItAlex.AlexTest.controller;

import by.ItAlex.AlexTest.persistance.dto.ToolDto;
import by.ItAlex.AlexTest.persistance.model.Tool;
import by.ItAlex.AlexTest.persistance.repository.ToolRepository;
import by.ItAlex.AlexTest.service.ToolService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RequiredArgsConstructor

@RestController
@RequestMapping("/tools")
public class MainController {

    private final ToolRepository toolRepository;

    private final ToolService toolService;

    @GetMapping("/all")
    public List<ToolDto> getTools() {
        return toolService.getAll();
    }


    @GetMapping("/get/{id}")
    public ToolDto getTool(@PathVariable Long id) {

        try {

            return toolService.getById(id);

        } catch (Exception e) {

            return new ToolDto();
        }

    }

    @GetMapping("/add")
    public ToolDto addTool(@ModelAttribute Tool tool) {
        try {

            return toolService.registerTool(tool);
        } catch (Exception e) {

            return new ToolDto();
        }
    }

    @GetMapping("/update")
    public ToolDto update(@ModelAttribute Tool tool) {

        try {

            return toolService.updateTool(tool);

        } catch (Exception e) {

            return new ToolDto();
        }
    }


}