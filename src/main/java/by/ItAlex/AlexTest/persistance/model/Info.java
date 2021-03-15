package by.ItAlex.AlexTest.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "tool")
@ToString(exclude = "tool")


@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Long price;


    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_entity_id", referencedColumnName = "id")
    private Tool tool;




}
