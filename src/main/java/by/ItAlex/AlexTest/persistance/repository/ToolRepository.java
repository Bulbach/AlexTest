package by.ItAlex.AlexTest.persistance.repository;

import by.ItAlex.AlexTest.persistance.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToolRepository extends JpaRepository<Tool,Long> {

       Tool findByName(String name);
}
