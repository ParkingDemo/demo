package instant.system.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesFieldsDto {
    private String derniere_actualisation_bo;
    private long capacite;
    private long places;
    private String nom;
    private List<Double> geo_point_2d;
    private String derniere_mise_a_jour_base;


}
