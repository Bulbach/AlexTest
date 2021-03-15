package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.exeption.ToolAlreadyExistException;
import by.ItAlex.AlexTest.persistance.dto.ToolDto;
import by.ItAlex.AlexTest.persistance.model.Tool;
import by.ItAlex.AlexTest.persistance.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ToolService {
    private final ToolRepository toolRepository;


    public ToolDto registerTool(Tool tool) throws ToolAlreadyExistException {
        if (tool.getName() == null) {
            throw new ToolAlreadyExistException("Имя не заполнено");
        } else if (toolRepository.findByName(tool.getName()) != null) {
            throw new ToolAlreadyExistException("Продукт с таким именем уже зарегестрирован");
        }
        return new ToolDto().modelToDto(toolRepository.save(tool));
    }

    public ToolDto getById(Long id) {
        Tool tool = toolRepository.getOne(id);

        return new ToolDto().modelToDto(tool);
    }

    public ToolDto updateTool(Tool tool) {

        Tool toolEntity = toolRepository.getOne(tool.getId());

        toolEntity.setName(tool.getName());

        return new ToolDto().modelToDto(toolRepository.save(toolEntity));
    }

    public List<ToolDto> getAll() {
        return toolRepository.findAll()
                .stream()
                .map(t -> new ToolDto().modelToDto(t))
                .collect(Collectors.toList());
    }
}

