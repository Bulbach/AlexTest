package by.ItAlex.AlexTest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Tool {

    private Long id;
    private String name;
    private List<Info> infos;

}
