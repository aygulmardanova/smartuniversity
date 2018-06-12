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
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
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

    @Autowired
    WishStatusService wishStatusService;

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

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String autoGenerate(ModelMap model, @RequestParam(required = false) List<String> wishTypes) throws IOException {
        if (wishTypes == null || wishTypes.size() == 0 || wishTypes.contains("all"))
            wishService.generateWishes();
        else {
            System.out.println("l");
        }
//            wishTypes.forEach(wishType -> {
//                switch (wishType) {
//                    case "STUD_TO_STUD_ON_SUBJ":
//                        wishService.generateStudToStudForSubjectWishes();
//                    case "STUD_TO_STUD":
//                        wishService.generateStudToStudWishes();
//                    case "TEACH_TO_STUD_ON_SUBJ":
//                        wishService.generateTeachToStudForSubjectWishes();
//                    case "TEACH_TO_STUD":
//                        wishService.generateTeachToStudWishes();
//                    case "SUBJ_AUD":
//                        wishService.generateSubjectToAudWishes();
//                    case "TEACH_SUBJ_AUD":
//                        wishService.generateTeachToSubjAudWishes();
//                }
//            });
        model.put("wishTypes", wishTypes);
        return "redirect:auto-generate";
    }

    @RequestMapping(value = "/generateTeachToStudWishes", method = RequestMethod.GET)
    public String generateTeachToStudWishes(ModelMap model) throws IOException {
        wishService.generateStudToStudWishes();
        return "redirect:auto-generate";
    }

    @RequestMapping(value = "/generateStudToStudForSubjectWishes", method = RequestMethod.GET)
    public String generateStudToStudForSubjectWishes(ModelMap model) throws IOException {
        wishService.generateStudToStudForSubjectWishes();
        return "redirect:auto-generate";
    }

    @RequestMapping(value = "/generateTeachToStudForSubjectWishes", method = RequestMethod.GET)
    public String generateTeachToStudForSubjectWishes(ModelMap model) throws IOException {
        wishService.generateTeachToStudForSubjectWishes();
        return "redirect:auto-generate";
    }

    @RequestMapping(value = "/auto-generate", method = RequestMethod.GET)
    public String autoGeneratePage(ModelMap model, @RequestParam(required = false) List<String> wishTypes) throws IOException {
        List<WishInfoEntity> wishes = new ArrayList<>();
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD));
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD_ON_SUBJ));
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_TO_STUD));
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_TO_STUD_ON_SUBJ));
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_AUD));
        model.put("wishes", wishes);
        if (wishTypes != null && wishTypes.size() != 0) {
            List<String> generatedWishTypes = new ArrayList<>();
            wishTypes.forEach(wishType -> generatedWishTypes.add(wishInfoService.getWishInfoByType(WishTypeEnum.valueOf(wishType)).getName()));
            model.put("generatedWishTypes", generatedWishTypes);
        }
        return "auto-generate";
    }


    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public String saveStudToTeachWish(ModelMap model,
                                      @RequestParam("user_id") Long userId,
                                      @RequestParam("teacher_id") Long teacherId,
                                      @RequestParam(required = false, name = "subjects") List<String> subjects) throws IOException {

        WishInfoEntity type;
        List<WishEntity> wishes = new ArrayList<>();
        if (subjects == null || subjects.size() == 0) {
            type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH);
            WishEntity wish = new WishEntity();
            wish.setFromUser(userService.getById(userId));
            wish.setTeachUser(userService.getById(teacherId));
            wish.setWishInfo(type);
            wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT));
            wishes.add(wish);
        } else {
            type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH_ON_SUBJ);
            subjects.forEach(subject -> {
                WishEntity wish = new WishEntity();
                wish.setFromUser(userService.getById(userId));
                wish.setTeachUser(userService.getById(teacherId));
                wish.setSubject(subjectService.getByName(subject));
                wish.setWishInfo(type);
                wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT));
                wishes.add(wish);
            });
        }
        wishService.save(wishes);
        return "redirect:teachers";
    }

}
