package ru.kpfu.itis.controllers;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.entity.*;
import ru.kpfu.itis.entity.enums.UserRoleEnum;
import ru.kpfu.itis.entity.enums.WeekDayEnum;
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
import ru.kpfu.itis.services.*;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    PairNumService pairNumService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() throws IOException {
        return "redirect:teachers";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        return "main";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String returnProfilePage(ModelMap model) throws IOException {
        UserEntity currentUser = userService.getBySurname("Мальков");
        model.put("user", currentUser);
        model.put("timeWishes", wishService.getWishesForUser(currentUser).stream().filter(wish ->
                (
                        wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT).equals(wish.getWishStatus())
                                || wishStatusService.getWishStatusByName(WishStatusEnum.REQUIRED).equals(wish.getWishStatus())
                )
                        && (wishInfoService.getWishInfoByType(WishTypeEnum.USER_START_TIME).equals(wish.getWishInfo())
                        || wishInfoService.getWishInfoByType(WishTypeEnum.USER_START_TIME_ON_SUBJ).equals(wish.getWishInfo())
                        || wishInfoService.getWishInfoByType(WishTypeEnum.USER_END_TIME).equals(wish.getWishInfo())
                        || wishInfoService.getWishInfoByType(WishTypeEnum.USER_END_TIME_ON_SUBJ).equals(wish.getWishInfo())
                )
        ));
        model.put("teacherWishes", wishService.getWishesForUser(currentUser).stream().filter(wish ->
                (
                        wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT).equals(wish.getWishStatus())
                                || wishStatusService.getWishStatusByName(WishStatusEnum.REQUIRED).equals(wish.getWishStatus())
                )
                        && (wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH).equals(wish.getWishInfo())
                        || wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH_ON_SUBJ).equals(wish.getWishInfo())
                )
        ));
        model.put("studentWishes", wishService.getWishesForUser(currentUser).stream().filter(wish ->
                (
                        wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT).equals(wish.getWishStatus())
                                || wishStatusService.getWishStatusByName(WishStatusEnum.REQUIRED).equals(wish.getWishStatus())
                )
                        && (wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD).equals(wish.getWishInfo())
                        || wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD_ON_SUBJ).equals(wish.getWishInfo())
                )
        ));
        return "profile";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String returnTeachersPage(ModelMap model,
                                     @RequestParam(name = "success_msg", required = false) Boolean success_msg) throws IOException {
        List<UserEntity> teachers = userService.getAllTeachers();
        model.put("teachers", teachers);
        model.put("user", userService.getBySurname("Мальков"));
        model.put("success_msg", success_msg);
        return "teachers";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String returnStudentsPage(ModelMap model,
                                     @RequestParam(required = false) String sort,
                                     @RequestParam(name = "success_msg", required = false) Boolean success_msg) throws IOException {
        UserEntity currentUser = userService.getBySurname("Мальков");
        List<UserEntity> students = new ArrayList<>();
        if (sort == null || "".equals(sort) || "fio".equals(sort))
            students = userService.getAllStudentsOrderBySurname();
        if ("similarity".equals(sort)) {
            students = userService.getAllStudentsOrderBySimilarity(currentUser);
        }
        model.put("students", students);
        model.put("user", currentUser);
        model.put("success_msg", success_msg);
        return "students";
    }

    @RequestMapping(value = "/time-wishes", method = RequestMethod.GET)
    public String returnTimeWishesPage(ModelMap model,
                                       @RequestParam(name = "success_msg", required = false) Boolean success_msg) throws IOException {
        UserEntity user = userService.getAllStudents().stream().findFirst().orElse(null);
        model.put("user", user);
        model.put("subjects", subjectService.getSubjectsByStudentFromIup(user));
        model.put("pairNums", pairNumService.getAll());
        model.put("success_msg", success_msg);
        return "time-wishes";
    }

    @RequestMapping(value = "/save-time-wish", method = RequestMethod.POST)
    public String saveTimeWish(ModelMap model,
                               @RequestParam(name = "user_id", required = false) Long fromUserId,
                               @RequestParam(name = "pair_st_num", required = false) Integer pairStNum,
                               @RequestParam(name = "pair_end_num", required = false) Integer pairEndNum,
                               @RequestParam(name = "week_day", required = false) Integer weekDay,
                               @RequestParam(name = "subject_id", required = false) Long subjectId) throws IOException {
        if (pairStNum != null && pairEndNum != null) {
            wishService.saveTimeWish(fromUserId, pairStNum, null, weekDay, subjectId);
            wishService.saveTimeWish(fromUserId, null, pairEndNum, weekDay, subjectId);
        } else
            wishService.saveTimeWish(fromUserId, pairStNum, pairEndNum, weekDay, subjectId);
        model.put("success_msg", true);
        return "redirect:time-wishes";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String autoGenerate(ModelMap model, @RequestParam(required = false) List<String> wishTypes) throws IOException {
        if (wishTypes == null || wishTypes.size() == 0 || wishTypes.contains("all"))
            wishService.generateWishes();
        else {
            wishTypes.forEach(wishType -> {
                switch (wishType) {
                    case "STUD_TO_STUD_ON_SUBJ":
                        wishService.generateStudToStudForSubjectWishes();
                    case "STUD_TO_STUD":
                        wishService.generateStudToStudWishes();
                    case "TEACH_TO_STUD_ON_SUBJ":
                        wishService.generateTeachToStudForSubjectWishes();
                    case "TEACH_TO_STUD":
                        wishService.generateTeachToStudWishes();
                    case "SUBJ_AUD":
                        wishService.generateSubjectToAudWishes();
                    case "TEACH_SUBJ_AUD":
                        wishService.generateTeachToSubjAudWishes();
                }
            });
        }
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
        wishes.add(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_SUBJ_AUD));
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
                                      @RequestParam("user_from_id") Long userFromId,
                                      @RequestParam("user_to_id") Long userToId,
                                      @RequestParam(required = false, name = "subjects") List<String> subjects) throws IOException {

        WishInfoEntity type;
        List<WishEntity> wishes = new ArrayList<>();
        UserEntity userFrom = userService.getById(userFromId);
        UserEntity userTo = userService.getById(userToId);
        WishStatusEntity wishStatus = wishStatusService.getWishStatusByName(WishStatusEnum.STUDENT);

        if (subjects == null || subjects.size() == 0) {
            if (UserRoleEnum.TEACHER.name().equals(userTo.getUserRole().getCode()))
                type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH);
            else
                type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD);
            WishEntity wish = new WishEntity();
            wish.setFromUser(userFrom);
            wish.setTeachUser(userTo);
            wish.setWishInfo(type);
            wish.setWishStatus(wishStatus);
            wishes.add(wish);
        } else {
            if (UserRoleEnum.TEACHER.name().equals(userTo.getUserRole().getCode()))
                type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_TEACH_ON_SUBJ);
            else
                type = wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD_ON_SUBJ);
            subjects.forEach(subject -> {
                WishEntity wish = new WishEntity();
                wish.setFromUser(userFrom);
                wish.setTeachUser(userTo);
                wish.setSubject(subjectService.getByName(subject));
                wish.setWishInfo(type);
                wish.setWishStatus(wishStatus);
                wishes.add(wish);
            });
        }
        wishService.save(wishes);
        model.put("success_msg", true);
        if (UserRoleEnum.TEACHER.name().equals(userTo.getUserRole().getCode()))
            return "redirect:teachers";
        else
            return "redirect:students";
    }
}
