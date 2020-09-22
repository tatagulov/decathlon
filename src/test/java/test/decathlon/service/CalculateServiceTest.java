package test.decathlon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import test.decathlon.enums.Event;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateServiceTest {

    @Test
    void testWorldRecord() throws JsonProcessingException {
        Map<Event, Float> values = new HashMap<>();
        values.put(Event.RUN_100, 10.55f);
        values.put(Event.LONG_JUM, 7.80f);
        values.put(Event.SHOT_PUT, 16.00f);
        values.put(Event.HIGH_JUMP, 2.05f);
        values.put(Event.RUN_400, 48.42f);
        values.put(Event.HURDLES_110, 13.75f);
        values.put(Event.DISCUS_THROW, 50.54f);
        values.put(Event.POLE_VAULT, 5.45f);
        values.put(Event.JAVELIN_THROW, 71.90f);
        values.put(Event.RUN_1500, 4 * 60 + 36.11f);

        System.out.println(new ObjectMapper().writeValueAsString(values));

        CalculateService calculateService = new CalculateService();
        int totalPoint = calculateService.getTotalPoint(values);

        assertEquals(9126, totalPoint);
    }
}