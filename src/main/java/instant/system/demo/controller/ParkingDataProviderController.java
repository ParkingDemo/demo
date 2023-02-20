package instant.system.demo.controller;


import instant.system.demo.dto.AvailableParkingDto;
import instant.system.demo.dto.FieldsDto;
import instant.system.demo.services.ParsingDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Parking")
@RequiredArgsConstructor
@RequestMapping
public class ParkingDataProviderController {

    private final ParsingDataService service;

    private final String V1_PATH = "/v1/parking/availability";

    @ApiOperation("Return parking list and availability in this region")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Data successfully retrieved"),
            @ApiResponse(code = 500, message = "Problem processing request"),
    })
    @GetMapping(value = V1_PATH + "/{datasetId}")
    public ResponseEntity<AvailableParkingDto> getListOfParking(
            @ApiParam(value = "datasetId", required = true) @PathVariable("datasetId") String datasetId,
            @ApiParam(value = "latitude", required = true) @RequestParam("latitude") double latitude,
            @ApiParam(value = "longitude", required = true) @RequestParam("longitude") double longitude
    ) throws IOException {
        List<FieldsDto> parkingList;
        if (datasetId != null && !datasetId.isEmpty()) {
            parkingList = service.getListOfParking(datasetId, List.of(latitude, longitude));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AvailableParkingDto.builder()
                .errorDescription("datasetId is required").build());

        return ResponseEntity.status(HttpStatus.OK).body(AvailableParkingDto.builder().fields(parkingList).build());
    }

}
