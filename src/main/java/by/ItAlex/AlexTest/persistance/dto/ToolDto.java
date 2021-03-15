package by.ItAlex.AlexTest.persistance.dto;

import by.ItAlex.AlexTest.persistance.model.Tool;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ToolDto {

    private Long id;
    private String name;
    private List<InfoDto> infosDto;

    public ToolDto modelToDto(Tool tool) {
        if (tool != null) {
            setId(tool.getId());
            setName(tool.getName());
            setInfosDto(tool.getInfos()
                    .stream()
                    .map(t -> new InfoDto().modelToDto(t))
                    .collect(Collectors.toList())
            );
        }

        return this;
    }

    Tool dtoToModel() {
        Tool tool = new Tool();

        tool.setId(id);
        tool.setName(name);
        tool.setInfos(
                getInfosDto()
                        .stream()
                        .map(InfoDto::dtoToModel)
                        .collect(Collectors.toList())
        );

        return tool;
    }

}
