package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "files")
public class FileDesc {

    private int id;
    private String nameFile;
    private String filesize;
    private Record record;

    public FileDesc() {
    }

    public FileDesc(String nameFile, String filesize, Record record) {
        this.nameFile = nameFile;
        this.filesize = filesize;
        this.record = record;
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

    @Column(name = "namefile")
    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    @Column(name = "filesize")
    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_dir", referencedColumnName = "id")
    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        FileDesc fileDesc = (FileDesc) o;
        if (this.getId() == fileDesc.getId()) {
            return true;
        }
        return false;
    }
}
