package instant.system.demo.services;

import com.fasterxml.jackson.databind.JsonNode;
import instant.system.demo.controller.DataFactoryTest;
import instant.system.demo.dto.PlacesRecords;
import instant.system.demo.dto.RecordsDto;
import instant.system.demo.utils.JsonUrlReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ParsingDataServiceTest extends DataFactoryTest {

    @InjectMocks
    private ParsingDataService service;


    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "urlParking",
                "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3Le");
        ReflectionTestUtils.setField(service, "urlPlaces",
                "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom");

    }
    @Test
    public void shouldParseAndGetListOfParkingFromUrl() throws IOException {
        //given
        String datasetId = "mobilite-parkings-grand-poitiers-donnees-metiers";

        //Call
        var result = service.getListOfParking(datasetId, List.of(46.552806, 0.3495996));

        //then
        assertNotNull(result);
        Assertions.assertTrue(result.get(0).getNb_places_available() > 0);
        Assertions.assertTrue(result.get(0).getDistance() > 0);
    }

    @Test
    public void shouldNotParseSinceDataSetIdIsEmpty() throws IOException {
        //given
        String datasetId = StringUtils.EMPTY;

        //Call
        var result = service.getListOfParking(datasetId, List.of(0.0,0.0));

        //then
        assertNull(result);
    }

    @Test
    public void testMapping(){

        final var placesDtoList = List.of(PlacesRecords.builder().fields(buildListOfPlaces().get(0)).build());
        final var parkingDtoList = List.of(RecordsDto.builder().fields(buildListOfParking().get(0)).build());

        //When
        var result = service.mapAvailablePlacesToParking(placesDtoList, parkingDtoList, List.of(0.0,0.0));

        //then
        //we should have nb place available set in the dto
        Assertions.assertEquals(225, result.get(0).getNb_places_available());

    }

    @Test
    public void testGet() throws IOException {
        String url = "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3Le";
        JsonNode node = JsonUrlReader.get(url);

        // Check that the JSON response is not null
        Assertions.assertNotNull(node);

        // Check that the JSON response has a specific property
        Assertions.assertTrue(node.has("records"));
    }
}