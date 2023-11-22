package com.hamdi.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDto {

    private Integer id;

    private  String RoleName;

    @JsonIgnore
    private UtilisateurDto Utilisateur;

    private Integer idEntreprise;

    public RolesDto FromEntity(Roles roles) {

        if(roles == null) {
            return null;
        }

        return RolesDto.builder()
                .id(roles.getId())
                .RoleName(getRoleName())
                .idEntreprise(roles.getIdEnreprise())
                .build();
    }

    public static Roles ToEntity( RolesDto rolesDto) {

        if (rolesDto == null) {
            return null;
        }

        Roles roles = new Roles();

        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setIdEnreprise(roles.getIdEnreprise());

        return roles;


    }
}
