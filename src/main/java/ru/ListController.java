package ru;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.RecordService;
import ru.models.FileDesc;
import ru.models.Record;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ListController {

    private int countOfDirectories = 0;
    private int countOfFiles = 0;
    private long sizeOfAll = 0;
    private List<FileDesc> fileDescs = new ArrayList<>();
    private Record myRecord;

    @Autowired
    private RecordService recordService;

    @GetMapping("/list")
    protected ModelAndView menu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("records", recordService.findAll());
        return modelAndView;
    }

    @PostMapping("/list")
    protected ModelAndView saveRecord(@RequestParam(name = "record") String record) {
        File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + record);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        myRecord = new Record();
        fullListFiles(file);
        Collections.sort(fileDescs, new Comparator<FileDesc>() {
            @Override
            public int compare(FileDesc o1, FileDesc o2) {
                String sizeOne = o1.getFilesize();
                String sizeTwo = o2.getFilesize();
                if (!sizeOne.equals(sizeTwo) && ((sizeOne.equals("<DIR>")) || sizeTwo.equals("<DIR>")))
                    return sizeOne.equals("<DIR>") ? -1 : 1;
                return 0;
            }
        }.thenComparing(new Comparator<FileDesc>() {
            @Override
            public int compare(FileDesc o1, FileDesc o2) {
                return o1.getNameFile().substring(0,1).toLowerCase().compareTo(o2.getNameFile().substring(0,1).toLowerCase());
            }
        }.thenComparing(new Comparator<FileDesc>() {
            @Override
            public int compare(FileDesc o1, FileDesc o2) {
                String nameOne = o1.getNameFile();
                String nameTwo = o2.getNameFile();
                if (nameOne.matches(".*\\d.*") && nameTwo.matches(".*\\d.*"))
                    return Integer.parseInt(nameOne.replaceAll("\\D", "")) -
                            Integer.parseInt(nameTwo.replaceAll("\\D", ""));
                return 0;
            }
        })));
        myRecord.setCountOfDirectories(countOfDirectories);
        myRecord.setCountOfFiles(countOfFiles);
        myRecord.setDateCreation(currentTime);
        myRecord.setDirectory(record);
        myRecord.setFileDescs(fileDescs);
        myRecord.setSizeOfAll(getSize(sizeOfAll));
        recordService.save(myRecord);
        countOfDirectories = 0;
        countOfFiles = 0;
        sizeOfAll = 0;
        fileDescs = new ArrayList<>();
        return menu();
    }

    private void fullListFiles(File file) {
        File[] files = file.listFiles();
        for (File next : files) {
            String size = "";
            if (next.isDirectory()) {
                countOfDirectories++;
                size = "<DIR>";
            } else {
                size = getSize(next.length());
                sizeOfAll += file.length();
                countOfFiles++;
            }
            fileDescs.add(new FileDesc(next.getName(), size, myRecord));
            if (!next.isFile())
                fullListFiles(next);
        }
    }

    private String getSize(long size) {
        int mb = 1024 * 1024;
        int kb = 1024;
        if (size > mb)
            return Math.round((((double) size) / mb) * 100) / 100d + "Mb";
        return Math.round((((double) size) / kb) * 100) / 100d + "Kb";
    }
}