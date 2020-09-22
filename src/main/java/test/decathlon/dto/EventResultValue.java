package test.decathlon.dto;

import lombok.Data;
import test.decathlon.enums.Event;

@Data
public class EventResultValue {
    private Event event;
    private Float value;
}
