package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.MvtStkDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.MvtStkRepository;
import com.hamdi.gestiondestock.services.MvtStkService;
import com.hamdi.gestiondestock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private MvtStkRepository mvtStkRepository;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }

    @Override
    public MvtStkDto save(MvtStkDto dto) {
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("mvtstk is not valid", dto);
            throw new InvalidEntityException("mvt de stk n'est valid", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        return MvtStkDto.FromEntity(mvtStkRepository.save(MvtStkDto.ToEntity(dto)));
    }

    @Override
    public MvtStkDto findById(Integer id) {
        if (id == null) {
            log.error("mvtstk id is null");
            return null;
        }
        return mvtStkRepository.findById(id)
                .map(MvtStkDto::FromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException("mvtstk avec l'id" +id+"n'a ete touve dans la base",
                                ErrorCodes.MVT_STK_NOT_FOUND)
                );
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream().map(MvtStkDto::FromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("mvtstk id is null");
            return;
        }
        mvtStkRepository.deleteById(id);

    }
}
