package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.PairNumEntity;
import ru.kpfu.itis.repositories.PairNumRepository;
import ru.kpfu.itis.services.PairNumService;

import java.util.List;

@Service
public class PairNumServiceImpl implements PairNumService {

    @Autowired
    PairNumRepository pairNumRepository;

    @Override
    public List<PairNumEntity> getAll() {
        return pairNumRepository.findAll();
    }
}
