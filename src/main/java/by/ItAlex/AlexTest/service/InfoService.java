package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.dto.Info;
import by.ItAlex.AlexTest.model.InformationEntity;
import by.ItAlex.AlexTest.model.ToolEntity;
import by.ItAlex.AlexTest.repository.InfoRepository;
import by.ItAlex.AlexTest.repository.ToolRepository;
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

    public Info createInfo(Info info) {
        ToolEntity tool = toolRepository.getOne(info.getId());

        InformationEntity entity = new InformationEntity();
        entity.setDate(info.getDate());
        entity.setPrice(info.getPrice());
        entity.setToolEntity(tool);
        return modelToDto(infoRepository.save(entity));
    }


    public Info updateInfo(Info info) {
        InformationEntity infoEntity = infoRepository.getOne(info.getId());
        infoEntity.setDate(info.getDate());
        infoEntity.setPrice(info.getPrice());

        return modelToDto(infoRepository.save(infoEntity));
    }

    Info modelToDto(InformationEntity informationEntity) {
        Info info = new Info();
        if (informationEntity != null) {

            info.setId(informationEntity.getId());
            info.setDate(informationEntity.getDate());
            info.setPrice(informationEntity.getPrice());
            info.setTool(toolService.modelToDto(informationEntity.getToolEntity()));
        }

        return info;
    }

    InformationEntity dtoToModel(Info info) {

        InformationEntity entity = new InformationEntity();
        if (info != null) {
            entity.setId(info.getId());
            entity.setDate(info.getDate());
            entity.setPrice(info.getPrice());
            entity.setToolEntity(toolService.dtoToModel(info.getTool()));
        }
        return entity;
    }
}


