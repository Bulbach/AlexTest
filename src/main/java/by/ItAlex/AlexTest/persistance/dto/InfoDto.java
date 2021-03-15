package by.ItAlex.AlexTest.persistance.dto;

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

}
