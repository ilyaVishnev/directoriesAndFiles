package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dao.repositories.RecordRepository;
import ru.models.Record;

import javax.validation.constraints.Null;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public void save(Record record) {
        recordRepository.save(record);
    }

    public List<Record> findAll() {
        return recordRepository.findAllBy();
    }

    public Record findById(int id) {
        return recordRepository.findById(id);
    }
}
