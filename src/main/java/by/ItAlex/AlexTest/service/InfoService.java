package by.ItAlex.AlexTest.service;

import by.ItAlex.AlexTest.persistance.dto.InfoDto;
import by.ItAlex.AlexTest.persistance.model.Info;
import by.ItAlex.AlexTest.persistance.model.Tool;
import by.ItAlex.AlexTest.persistance.repository.InfoRepository;
import by.ItAlex.AlexTest.persistance.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InfoService {

    private final InfoRepository infoRepository;
    private final ToolRepository toolRepository;

    public InfoDto createInfo(InfoDto infoDto) {
       Tool tool = toolRepository.getOne(infoDto.getToolDto().getId());

        Info entity = new Info();
        entity.setDate(infoDto.getDate());
        entity.setPrice(infoDto.getPrice());
        entity.setTool(tool);

        return infoDto.modelToDto(infoRepository.save(entity));
    }


    public InfoDto updateInfo(InfoDto infoDto) {
        Info infoEntity = infoRepository.getOne(infoDto.getId());
        infoEntity.setDate(infoDto.getDate());
        infoEntity.setPrice(infoDto.getPrice());

        return infoDto.modelToDto(infoRepository.save(infoEntity));
    }

}


