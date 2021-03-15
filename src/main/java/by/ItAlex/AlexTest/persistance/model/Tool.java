package by.ItAlex.AlexTest.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "infos")
@ToString(exclude = "infos")

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    @OneToMany(mappedBy = "tool",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Info> infos = new ArrayList<>();

    public void addInformation(Info information){
        infos.add(information);
        information.setTool(this);
    }

    public void removeInformation(Info information){
        infos.remove(information);
        information.setTool(null);
    }


}
