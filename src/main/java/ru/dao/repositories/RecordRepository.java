package ru.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.models.Record;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record,Integer> {

    List<Record>findAllBy();
    Record findById(int id);

}
