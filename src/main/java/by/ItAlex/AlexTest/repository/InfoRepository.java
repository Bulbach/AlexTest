package by.ItAlex.AlexTest.repository;

import by.ItAlex.AlexTest.model.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<InformationEntity, Long> {

}
