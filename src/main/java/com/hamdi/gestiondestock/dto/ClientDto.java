package com.hamdi.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Adresse;
import com.hamdi.gestiondestock.model.Client;
import com.hamdi.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data

public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String mail;

    private String numTel;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    private Integer idEntreprise;


    public static ClientDto FromEntity(Client client) {

        if (client == null) {
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .photo((client.getPhoto()))
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .idEntreprise(client.getIdEnreprise())
                .build();
    }

    public static Client ToEntity(ClientDto clientDto) {

        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setPhoto(clientDto.getPhoto());
        client.setMail(clientDto.getMail());
        client.setNumTel(clientDto.getNumTel());
        client.setIdEnreprise(clientDto.getIdEntreprise());

        return client;

    }

}
