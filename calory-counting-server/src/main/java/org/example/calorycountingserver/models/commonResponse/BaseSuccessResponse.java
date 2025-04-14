package org.example.calorycountingserver.models.commonResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseSuccessResponse {
    private Integer statusCode;

    private Boolean success = true;

    private List<Integer> codes;

    public BaseSuccessResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public BaseSuccessResponse(int statusCode, List<Integer> codes) {
        this.statusCode = statusCode;
        this.codes = codes;
    }
}
