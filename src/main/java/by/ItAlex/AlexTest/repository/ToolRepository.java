package by.ItAlex.AlexTest.repository;

import by.ItAlex.AlexTest.model.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToolRepository extends JpaRepository<ToolEntity,Long> {

       ToolEntity findByToolName(String toolName);
}
