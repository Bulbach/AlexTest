package by.ItAlex.AlexTest.persistance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ToolDto {

    private Long id;
    private String name;
    private List<InfoDto> infoDtos;

}
