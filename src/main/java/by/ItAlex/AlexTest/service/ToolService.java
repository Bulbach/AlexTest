package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.dto.Tool;
import by.ItAlex.AlexTest.exeption.ToolAlreadyExistException;
import by.ItAlex.AlexTest.model.ToolEntity;
import by.ItAlex.AlexTest.repository.ToolRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = {"infoService"})
@ToString(exclude = {"infoService"})

@Service
public class ToolService {
    private final ToolRepository toolRepository;
    private InfoService infoService;

    @Autowired
    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    public Tool registerTool(ToolEntity tool) throws ToolAlreadyExistException {
        if (tool.getToolName() == null) {
            throw new ToolAlreadyExistException("Имя не заполнено");
        } else if (toolRepository.findByToolName(tool.getToolName()) != null) {
            throw new ToolAlreadyExistException("Продукт с таким именем уже зарегестрирован");
        }
        return modelToDto(toolRepository.save(tool));
    }

    public Tool getById(Long id) {
        ToolEntity tool = toolRepository.getOne(id);

        return modelToDto(tool);
    }

    public Tool updateTool(ToolEntity tool) {

        ToolEntity toolEntity = toolRepository.getOne(tool.getId());

        toolEntity.setToolName(tool.getToolName());

        return modelToDto(toolRepository.save(toolEntity));
    }


    Tool modelToDto(ToolEntity toolEntity) {
        Tool tool = new Tool();
        if (toolEntity != null) {
            tool.setId(toolEntity.getId());
            tool.setName(toolEntity.getToolName());
            tool.setInfos(toolEntity.getInformationEntities()
                    .stream()
                    .map(infoService::modelToDto)
                    .collect(Collectors.toList())
            );
        }
        return tool;
    }

    ToolEntity dtoToModel(Tool tool) {
        ToolEntity entity = new ToolEntity();
        if (tool != null) {
            entity.setId(tool.getId());
            entity.setToolName(tool.getName());
            entity.setInformationEntities(
                    tool.getInfos().stream()
                            .map(infoService::dtoToModel)
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public List<Tool> getAll() {
        return toolRepository.findAll()
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}

