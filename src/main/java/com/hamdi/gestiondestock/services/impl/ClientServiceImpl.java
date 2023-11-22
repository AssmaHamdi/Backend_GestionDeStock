package com.hamdi.gestiondestock.services.impl;

import com.hamdi.gestiondestock.dto.ClientDto;
import com.hamdi.gestiondestock.exception.EntityNotFoundException;
import com.hamdi.gestiondestock.exception.ErrorCodes;
import com.hamdi.gestiondestock.exception.InvalidEntityException;
import com.hamdi.gestiondestock.repository.ClientRepository;
import com.hamdi.gestiondestock.services.ClientService;
import com.hamdi.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {

        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("client is not valid", dto);
            throw new InvalidEntityException("client n'est pas valid", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.FromEntity(clientRepository.save(ClientDto.ToEntity(dto)));
    }

    @Override
    public ClientDto findById(Integer id) {

        if(id==null) {
            log.error("client id is null");
            return null;
        }
        return clientRepository.findById(id).
                map(ClientDto::FromEntity).
                orElseThrow(() -> new EntityNotFoundException(
                        "client avec l'id" +id+ "n'est pas trouve dans la BD", ErrorCodes.CLIENT_NOT_FOUND
                        )
                );

    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::FromEntity).
                collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("client id is null");
            return;
        }
         clientRepository.deleteById(id);
    }
}
