package by.ItAlex.AlexTest.persistance.dto;

import by.ItAlex.AlexTest.persistance.model.Info;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InfoDto {

    private Long id;
    private LocalDate date;
    private Long price;

    private ToolDto toolDto;

    InfoDto modelToDto(Info info) {
        if (info != null) {
            setId(info.getId());
            setDate(info.getDate());
            setPrice(info.getPrice());
            setToolDto(new ToolDto().modelToDto(info.getTool()));
        }

        return this;
    }

    Info dtoToModel() {

        Info info = new Info();

        info.setId(id);
        info.setDate(date);
        info.setPrice(price);
        info.setTool(toolDto.dtoToModel());

        return info;
    }

}
