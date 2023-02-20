package instant.system.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AvailableParkingDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldsDto> fields;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDescription;
}
