package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.persistance.dto.InfoDto;
import by.ItAlex.AlexTest.persistance.model.Info;
import by.ItAlex.AlexTest.persistance.model.Tool;
import by.ItAlex.AlexTest.persistance.repository.InfoRepository;
import by.ItAlex.AlexTest.persistance.repository.ToolRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@EqualsAndHashCode(exclude = {"toolService"})
@ToString(exclude = {"toolService"})

@Service
public class InfoService {

    private final InfoRepository infoRepository;
    private final ToolRepository toolRepository;
    private ToolService toolService;

    @Autowired
    public void setToolService(ToolService toolService) {
        this.toolService = toolService;
    }

    public InfoDto createInfo(InfoDto infoDto) {
        Tool tool = toolRepository.getOne(infoDto.getId());

        Info entity = new Info();
        entity.setDate(infoDto.getDate());
        entity.setPrice(infoDto.getPrice());
        entity.setTool(tool);
        return modelToDto(infoRepository.save(entity));
    }


    public InfoDto updateInfo(InfoDto infoDto) {
        Info infoEntity = infoRepository.getOne(infoDto.getId());
        infoEntity.setDate(infoDto.getDate());
        infoEntity.setPrice(infoDto.getPrice());

        return modelToDto(infoRepository.save(infoEntity));
    }

    InfoDto modelToDto(Info info) {
        InfoDto infoDto = new InfoDto();
        if (info != null) {

            infoDto.setId(info.getId());
            infoDto.setDate(info.getDate());
            infoDto.setPrice(info.getPrice());
            infoDto.setToolDto(toolService.modelToDto(info.getTool()));
        }

        return infoDto;
    }

    Info dtoToModel(InfoDto infoDto) {

        Info entity = new Info();
        if (infoDto != null) {
            entity.setId(infoDto.getId());
            entity.setDate(infoDto.getDate());
            entity.setPrice(infoDto.getPrice());
            entity.setTool(toolService.dtoToModel(infoDto.getToolDto()));
        }
        return entity;
    }
}


