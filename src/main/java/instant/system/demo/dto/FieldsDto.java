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
public class FieldsDto {

    private String nom_du_par;
    private List<Double> geo_point_2d;
    private String  adresse;
    private int nb_places;
    private long nb_places_available;
    private double distance;
}
