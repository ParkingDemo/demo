package instant.system.demo.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import instant.system.demo.dto.PlacesFieldsDto;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUrlReader {

    private static PlacesFieldsDto getPlaceFromCsv(String line) {
        String[] fields = line.split(";");
        if(fields.length!=8)
            throw new RuntimeException("Invalid CSV line - " + line);

        List<Double> geoPoint= Arrays.stream(fields[7].split(",")).map(Double::valueOf).collect(Collectors.toList());
        return PlacesFieldsDto.builder()
                .nom(fields[1])
                .places(Long.parseLong(fields[2]))
                .capacite(Long.parseLong(fields[3]))
                .derniere_mise_a_jour_base(fields[4])
                .derniere_actualisation_bo(fields[5])
                .geo_point_2d(geoPoint).build();
    }

    public static void csvToJson(URL csvFile) throws IOException {

        final CsvMapper mapper = new CsvMapper();
        MappingIterator<PlacesFieldsDto> it = mapper
                .readerForListOf(String.class)
                .with(CsvParser.Feature.WRAP_AS_ARRAY)
                .readValues(csvFile);
        List<PlacesFieldsDto> all = it.readAll();
    }
}
