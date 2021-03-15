package by.ItAlex.AlexTest.customresponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomResponse<T> {
    private String message;
    private T object;
}
