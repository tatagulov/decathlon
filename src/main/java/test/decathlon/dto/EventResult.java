package test.decathlon.dto;

import lombok.Data;
import test.decathlon.enums.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class EventResult {
    @NotBlank
    private String athleteName;

    @NotNull
    private Map<Event, Float> values;
}
