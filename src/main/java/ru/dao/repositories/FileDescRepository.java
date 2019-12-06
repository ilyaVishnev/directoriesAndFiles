package ru.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.models.FileDesc;

@Repository
public interface FileDescRepository extends CrudRepository<FileDesc,Integer> {

}
