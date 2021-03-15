package by.ItAlex.AlexTest.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "informationEntities")
@ToString(exclude = "informationEntities")

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String toolName;

    @OneToMany(mappedBy = "toolEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Info> informationEntities = new ArrayList<>();

    public void addInformation(Info information){
        informationEntities.add(information);
        information.setTool(this);
    }

    public void removeInformation(Info information){
        informationEntities.remove(information);
        information.setTool(null);
    }


}
