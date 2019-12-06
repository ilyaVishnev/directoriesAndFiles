package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.RecordService;
import ru.models.FileDesc;
import ru.models.Record;

import java.util.Collections;
import java.util.List;

@Controller
public class FilesController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/files")
    protected ModelAndView showFiles(@RequestParam("recordId") Integer recordId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("files");
        Record record = recordService.findById(recordId);
        modelAndView.addObject("files", record.getFileDescs());
        return modelAndView;
    }
}
