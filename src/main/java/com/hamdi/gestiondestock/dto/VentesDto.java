package com.hamdi.gestiondestock.dto;
import com.hamdi.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private List<LigneVenteDto> ligneVentes;

    private String code;

    private Instant datevente;

    private String commentaire;

    private Integer idEntreprise;


    public static VentesDto FromEntity(Ventes ventes) {

        if (ventes == null) {
            return null;
        }

        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .commentaire(ventes.getCommentaire())
                .idEntreprise(ventes.getIdEnreprise())
                .build();
    }

    public static Ventes ToEntity( VentesDto ventesDto) {

        if (ventesDto == null) {
            return  null;
        }

        Ventes ventes = new Ventes();

        ventes.setId(ventesDto.getId());
        ventes.setCode(ventesDto.getCode());
        ventes.setDatevente(ventesDto.getDatevente());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setIdEnreprise(ventesDto.getIdEntreprise());

        return ventes;

    }
}
