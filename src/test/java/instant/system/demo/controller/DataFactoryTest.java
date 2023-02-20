package instant.system.demo.controller;

import instant.system.demo.dto.FieldsDto;
import instant.system.demo.dto.PlacesFieldsDto;

import java.util.List;

public class DataFactoryTest {

    public static List<FieldsDto> buildListOfParking() {
        return List.of(
                FieldsDto.builder()
                        .nom_du_par("BLOSSAC TISON")
                        .nb_places_available(0)
                        .nb_places(665)
                        .adresse("Carrefour Boulevard sous Blossac (niveau -3) et Boulevard de Tison (niveau -7)")
                        .geo_point_2d(List.of(46.57505317559496,
                                0.337126307915689))
                        .build(),
                FieldsDto.builder()
                        .nom_du_par("NOTRE DAME")
                        .nb_places_available(0)
                        .nb_places(642)
                        .adresse("Place Charles de Gaulle 86000 Poitiers")
                        .geo_point_2d(List.of(46.58349874703973,
                                0.3450022616476489))
                        .build()
        );
    }

    public static List<PlacesFieldsDto> buildListOfPlaces() {
        return List.of(
                PlacesFieldsDto.builder()
                        .nom("BLOSSAC TISON")
                        .derniere_actualisation_bo("2023-01-20T12:52:12.677Z")
                        .derniere_mise_a_jour_base("2023-01-20T12:52:12.677Z")
                        .capacite(665)
                        .places(225)
                        .geo_point_2d(List.of(46.57505317559496,
                                0.337126307915689))
                        .build(),
                PlacesFieldsDto.builder()
                        .nom("NOTRE DAME")
                        .derniere_actualisation_bo("2023-01-20T12:52:12.677Z")
                        .derniere_mise_a_jour_base("2023-01-20T12:52:12.677Z")
                        .capacite(642)
                        .places(200)
                        .geo_point_2d(List.of(46.58349874703973,
                                0.3450022616476489))
                        .build()
        );
    }
}
