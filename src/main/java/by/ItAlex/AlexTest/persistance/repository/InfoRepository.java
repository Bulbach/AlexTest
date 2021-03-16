package by.ItAlex.AlexTest.persistance.repository;

import by.ItAlex.AlexTest.persistance.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    List<Info> findAllByOrderByDateAsc();
}
