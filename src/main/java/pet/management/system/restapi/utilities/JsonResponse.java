package pet.management.system.restapi.utilities;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class JsonResponse {
    private int code;
    private String message;
}
