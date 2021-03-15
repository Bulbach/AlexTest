package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.persistance.dto.ToolDto;
import by.ItAlex.AlexTest.exeption.ToolAlreadyExistException;
import by.ItAlex.AlexTest.persistance.model.Tool;
import by.ItAlex.AlexTest.persistance.repository.ToolRepository;
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

    public ToolDto registerTool(Tool tool) throws ToolAlreadyExistException {
        if (tool.getName() == null) {
            throw new ToolAlreadyExistException("Имя не заполнено");
        } else if (toolRepository.findByToolName(tool.getName()) != null) {
            throw new ToolAlreadyExistException("Продукт с таким именем уже зарегестрирован");
        }
        return modelToDto(toolRepository.save(tool));
    }

    public ToolDto getById(Long id) {
        Tool tool = toolRepository.getOne(id);

        return modelToDto(tool);
    }

    public ToolDto updateTool(Tool tool) {

        Tool toolEntity = toolRepository.getOne(tool.getId());

        toolEntity.setName(tool.getName());

        return modelToDto(toolRepository.save(toolEntity));
    }


    ToolDto modelToDto(Tool tool) {
        ToolDto toolDto = new ToolDto();
        if (tool != null) {
            toolDto.setId(tool.getId());
            toolDto.setName(tool.getName());
            toolDto.setInfoDtos(tool.getInfos()
                    .stream()
                    .map(infoService::modelToDto)
                    .collect(Collectors.toList())
            );
        }
        return toolDto;
    }

    Tool dtoToModel(ToolDto toolDto) {
        Tool entity = new Tool();
        if (toolDto != null) {
            entity.setId(toolDto.getId());
            entity.setName(toolDto.getName());
            entity.setInfos(
                    toolDto.getInfoDtos().stream()
                            .map(infoService::dtoToModel)
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public List<ToolDto> getAll() {
        return toolRepository.findAll()
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}

