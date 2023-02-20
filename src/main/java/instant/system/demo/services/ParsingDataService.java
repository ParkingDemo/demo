package instant.system.demo.services;

import com.fasterxml.jackson.databind.JsonNode;
import instant.system.demo.dto.*;
import instant.system.demo.utils.JsonUrlReader;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static instant.system.demo.utils.JsonUrlReader.getParkingDataFromJson;

@Slf4j
@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ParsingDataService {

    @Value("${conf.app.urlParking}")
    private String urlParking;

    @Value("${conf.app.urlPlaces}")
    private String urlPlaces;

    public static final double EARTH_RADIUS = 6371; // Rayon de la Terre en kilomètres

    public List<FieldsDto> getListOfParking(String datasetId, List<Double> geoCoordinates) throws IOException {


        log.info("Retrieving parking data for dataset {}", datasetId);
        JsonNode node = JsonUrlReader.get(urlParking);
        var parkingList = getParkingDataFromJson(node.toPrettyString(), ParkingDto.class);

        var datasetIdExistFromParkingList = false;
        if (parkingList != null) {
            datasetIdExistFromParkingList = parkingList.getRecords().stream().anyMatch(i -> i.getDatasetid().equalsIgnoreCase(datasetId));
        }
        if (datasetIdExistFromParkingList) {
            JsonNode nodePlaces = JsonUrlReader.get(urlPlaces);
            var availablePlaces = getParkingDataFromJson(nodePlaces.toPrettyString(), PlacesDto.class);
            assert availablePlaces != null;
            return mapAvailablePlacesToParking(availablePlaces.getRecords(), parkingList.getRecords(), geoCoordinates);
        }
        return null;
    }

    public List<FieldsDto> mapAvailablePlacesToParking(List<PlacesRecords> placesRecords,
                                                       List<RecordsDto> parkingRecords,
                                                       List<Double> geoCoordiantes) {

        Map<String, Long> result = placesRecords.stream()
                .collect(Collectors.toMap(d -> d.getFields().getNom(), d -> d.getFields().getPlaces()));

        List<FieldsDto> parkingList = new ArrayList<>();
        parkingRecords.forEach(
                recordsDto -> {
                    var distance = 0;
                    var field = FieldsDto.builder();
                    if (recordsDto.getFields() != null) {
                        var geo = recordsDto.getFields().getGeo_point_2d();
                        distance = (int) distance(geo.get(0), geo.get(1), geoCoordiantes.get(0), geoCoordiantes.get(1));
                        if (result.get(recordsDto.getFields().getNom_du_par()) != null) {
                            field.nb_places_available(result.get(recordsDto.getFields().getNom_du_par()));
                        }
                        field
                                .adresse(recordsDto.getFields().getAdresse())
                                .geo_point_2d(recordsDto.getFields().getGeo_point_2d())
                                .distance(distance)
                                .nb_places(recordsDto.getFields().getNb_places())
                                .nom_du_par(recordsDto.getFields().getNom_du_par());
                    }
                    parkingList.add(field.build());
                }
        );
        return parkingList;

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c * 1000; // Convert to meters
        return distance;
    }

}
