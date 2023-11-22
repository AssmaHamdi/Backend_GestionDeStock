package com.hamdi.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamdi.gestiondestock.model.MvtStk;
import com.hamdi.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Integer id;

    @JsonIgnore
    private ArticleDto article;

    private Instant dteMvt;

    private BigDecimal quantite;

    @JsonIgnore
    private TypeMvtStk typemvtStk;

    private Integer idEntreprise;


    public static MvtStkDto FromEntity(MvtStk mvtStk) {

        if (mvtStk == null) {
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dteMvt(mvtStk.getDteMvt())
                .quantite(mvtStk.getQuantite())
                .idEntreprise(mvtStk.getIdEnreprise())
                .build();
    }

    public static MvtStk ToEntity (MvtStkDto mvtStkDto) {

        if (mvtStkDto == null) {
            return null;
        }

        MvtStk mvtStk = new MvtStk();

        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDteMvt(mvtStkDto.getDteMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setIdEnreprise(mvtStkDto.getIdEntreprise());

        return mvtStk;

    }
}
