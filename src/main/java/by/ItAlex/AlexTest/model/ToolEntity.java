package by.ItAlex.AlexTest.model;

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
public class ToolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String toolName;

    @OneToMany(mappedBy = "toolEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<InformationEntity> informationEntities = new ArrayList<>();

    public void addInformation(InformationEntity information){
        informationEntities.add(information);
        information.setToolEntity(this);
    }

    public void removeInformation(InformationEntity information){
        informationEntities.remove(information);
        information.setToolEntity(null);
    }


}
