package pet.management.system.restapi.utilities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonSerialize
@AllArgsConstructor
public class JsonObject {
    private String message;
}
