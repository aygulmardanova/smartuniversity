package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
import ru.kpfu.itis.services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/view")
public class ViewController {

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

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String returnViewToTeacherWishesPage(ModelMap model) throws IOException {
        UserEntity currentUser = userService.getBySurname("Мальков");
        List<WishEntity> toTeachWishes = wishService.getWishesForUser(currentUser).stream()
                .filter(wish -> WishStatusEnum.STUDENT.name().equals(wish.getWishStatus().getName()))
                .filter(wish ->
                        WishTypeEnum.STUD_TO_TEACH.name().equals(wish.getWishInfo().getType())
                                ||
                                WishTypeEnum.STUD_TO_TEACH_ON_SUBJ.name().equals(wish.getWishInfo().getType()))
                .collect(Collectors.toList());
        model.put("toTeachWishes", toTeachWishes);
        model.put("user", currentUser);
        return "teacher-wishes";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String returnViewToStudentWishesPage(ModelMap model) throws IOException {
        UserEntity currentUser = userService.getBySurname("Мальков");
        List<WishEntity> toStudWishes = wishService.getWishesForUser(currentUser).stream()
                .filter(wish -> WishStatusEnum.STUDENT.name().equals(wish.getWishStatus().getName()))
                .filter(wish ->
                        WishTypeEnum.STUD_TO_STUD.name().equals(wish.getWishInfo().getType())
                                ||
                                WishTypeEnum.STUD_TO_STUD_ON_SUBJ.name().equals(wish.getWishInfo().getType()))
                .collect(Collectors.toList());
        model.put("toStudWishes", toStudWishes);
        model.put("user", currentUser);
        return "student-wishes";
    }

    @RequestMapping(value = "/time-wishes", method = RequestMethod.GET)
    public String returnViewTimeWishesPage(ModelMap model,
                                           @RequestParam(name = "filter", required = false) String filter) throws IOException {
        UserEntity currentUser = userService.getAllStudents().stream().findFirst().orElse(null);
        List<WishEntity> timeWishes;
        if (filter == null || "".equals(filter)) {
            timeWishes = wishService.getWishesForUser(currentUser).stream()
                    .filter(wish -> WishStatusEnum.STUDENT.name().equals(wish.getWishStatus().getName()))
                    .filter(wish ->
                            WishTypeEnum.USER_START_TIME.name().equals(wish.getWishInfo().getType())
                                    ||
                                    WishTypeEnum.USER_START_TIME_ON_SUBJ.name().equals(wish.getWishInfo().getType())
                                    ||
                                    WishTypeEnum.USER_END_TIME.name().equals(wish.getWishInfo().getType())
                                    ||
                                    WishTypeEnum.USER_END_TIME_ON_SUBJ.name().equals(wish.getWishInfo().getType())
                    )
                    .collect(Collectors.toList());
        } else {
            if ("start".equals(filter)) {
                timeWishes = wishService.getWishesForUser(currentUser).stream()
                        .filter(wish -> WishStatusEnum.STUDENT.name().equals(wish.getWishStatus().getName()))
                        .filter(wish ->
                                WishTypeEnum.USER_START_TIME.name().equals(wish.getWishInfo().getType())
                                        ||
                                        WishTypeEnum.USER_START_TIME_ON_SUBJ.name().equals(wish.getWishInfo().getType())
                        )
                        .collect(Collectors.toList());
            } else {
                timeWishes = wishService.getWishesForUser(currentUser).stream()
                        .filter(wish -> WishStatusEnum.STUDENT.name().equals(wish.getWishStatus().getName()))
                        .filter(wish ->
                                WishTypeEnum.USER_END_TIME.name().equals(wish.getWishInfo().getType())
                                        ||
                                        WishTypeEnum.USER_END_TIME_ON_SUBJ.name().equals(wish.getWishInfo().getType())
                        )
                        .collect(Collectors.toList());
            }
        }
        model.put("timeWishes", timeWishes);
        model.put("user", currentUser);
        return "all-time-wishes";
    }

}