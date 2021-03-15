package by.ItAlex.AlexTest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Info {

    private Long id;
    private LocalDate date;
    private Long price;

    private Tool tool;

}
