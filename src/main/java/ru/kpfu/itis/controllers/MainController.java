package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishInfoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    WishInfoService wishInfoService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) throws IOException {
        return "main";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generateWishes(ModelMap model) throws IOException {
        List<UserEntity> users = new ArrayList<>();
        users.add(userService.getBySurname("Форсенко"));
        users.add(userService.getBySurname("Зайнуллина"));
        users.add(userService.getBySurname("Ионов"));
        model.put("users", users);
        return "main";
    }

}
