package by.ItAlex.AlexTest.controller;

import by.ItAlex.AlexTest.dto.Tool;
import by.ItAlex.AlexTest.model.ToolEntity;
import by.ItAlex.AlexTest.repository.ToolRepository;
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
    public List<Tool> getTools() {
        return toolService.getAll();
    }


    @GetMapping("/get/{id}")
    public Tool getTool(@PathVariable Long id) {

        try {

            return toolService.getById(id);

        } catch (Exception e) {

            return new Tool();
        }

    }

    @GetMapping("/add")
    public Tool addTool(@ModelAttribute ToolEntity tool) {
        try {

            return toolService.registerTool(tool);
        } catch (Exception e) {

            return new Tool();
        }
    }

    @GetMapping("/update")
    public Tool update(@ModelAttribute ToolEntity tool) {

        try {

            return toolService.updateTool(tool);

        } catch (Exception e) {

            return new Tool();
        }
    }


}