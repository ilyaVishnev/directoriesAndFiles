package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "directoriesandfiles")
public class Record {

    private int id;
    private Timestamp dateCreation;
    private String directory;
    private int countOfDirectories;
    private int countOfFiles;
    private String sizeOfAll;
    private List<FileDesc> fileDescs = new ArrayList<>();

    public Record() {
    }

    public Record(Timestamp dateCreation, String directory, int countOfDirectories, int countOfFiles, String sizeOfAll) {
        this.dateCreation = dateCreation;
        this.directory = directory;
        this.countOfDirectories = countOfDirectories;
        this.countOfFiles = countOfFiles;
        this.sizeOfAll = sizeOfAll;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "datecreation")
    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Column(name = "directory")
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Column(name = "countofdirectories")
    public int getCountOfDirectories() {
        return countOfDirectories;
    }

    public void setCountOfDirectories(int countOfDirectories) {
        this.countOfDirectories = countOfDirectories;
    }

    @Column(name = "countoffiles")
    public int getCountOfFiles() {
        return countOfFiles;
    }

    public void setCountOfFiles(int countOfFiles) {
        this.countOfFiles = countOfFiles;
    }

    @Column(name = "sizeofall")
    public String getSizeOfAll() {
        return sizeOfAll;
    }

    public void setSizeOfAll(String sizeOfAll) {
        this.sizeOfAll = sizeOfAll;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "record")
    public List<FileDesc> getFileDescs() {
        return fileDescs;
    }

    public void setFileDescs(List<FileDesc> fileDescs) {
        this.fileDescs = fileDescs;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Record record = (Record) o;
        if (this.getId() == record.getId()) {
            return true;
        }
        return false;
    }
}
