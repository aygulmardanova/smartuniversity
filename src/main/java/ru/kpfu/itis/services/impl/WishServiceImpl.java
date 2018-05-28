package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.repositories.WishRepository;
import ru.kpfu.itis.services.WishService;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    WishRepository wishRepository;

}
