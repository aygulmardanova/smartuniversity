package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.entity.*;
import ru.kpfu.itis.services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    WishInfoService wishInfoService;

    @Autowired
    UserService userService;

    @Autowired
    WishService wishService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    IupService iupService;

    @Autowired
    AuditoryService auditoryService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        return "main";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String returnTrainersPage(ModelMap model) throws IOException {
        List<UserEntity> teachers = userService.getAllTeachers();
        model.put("teachers", teachers);
        model.put("user", userService.getBySurname("Мальков"));
        return "teachers";
    }

    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public String saveStudToTeachWish(ModelMap model,
                                      @RequestParam("user_id") Long userId,
                                      @RequestParam("teacher_id") Long teacherId,
                                      @RequestParam(required = false, name = "subjects") List<String> subjects) throws IOException {

//        List<UserEntity> teachers = userService.getAllTeachers();
//        model.put("teachers", teachers);
        return "redirect:teachers";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generateWishes(ModelMap model) throws IOException {
        List<UserEntity> users = new ArrayList<>();
        users.add(userService.getBySurname("Форсенко"));
        users.add(userService.getBySurname("Зайнуллина"));
        users.add(userService.getBySurname("Ионов"));
        model.put("users", users);
        List<AuditoryEntity> auditories = auditoryService.getAllAuditories();
        List<SubjectEntity> subjects = subjectService.getAllSubjects();

        return "main";
    }

}
