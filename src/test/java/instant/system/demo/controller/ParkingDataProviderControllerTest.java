package instant.system.demo.controller;

import instant.system.demo.services.ParsingDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParkingDataProviderControllerTest extends DataFactoryTest {


    @InjectMocks
    private ParkingDataProviderController controller;
    @Mock
    private ParsingDataService service;

    @Test
    public void whenNoDataSetIsSet() throws Exception {

        //when
        final var result = controller.getListOfParking("", 0.0,0.0);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }


    @Test
    public void shouldGetListParking_whenDataSetIdIsSet() throws Exception {
        String datasetId = "mobilite-parkings-grand-poitiers-donnees-metiers";
        Mockito.when(service.getListOfParking(datasetId, List.of(0.0,0.0))).thenReturn(buildListOfParking());
        //when
        final var result = controller.getListOfParking(datasetId, 0.0,0.0);

        //then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertNotNull(result.getBody().getFields());
    }


}