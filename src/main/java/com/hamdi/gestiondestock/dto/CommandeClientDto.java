package com.hamdi.gestiondestock.dto;
import com.hamdi.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClient;

    private Integer idEntreprise;


    public static CommandeClientDto FromEntity(CommandeClient commandeClient) {

        if (commandeClient == null) {
            return null;
        }

      return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEnreprise())
                .client(ClientDto.FromEntity(commandeClient.getClient()))
                .build();
    }

    public static CommandeClient ToEntity(CommandeClientDto commandeClientDto) {

        if (commandeClientDto == null) {
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setIdEnreprise(commandeClientDto.getIdEntreprise());

        return commandeClient;
    }
}
