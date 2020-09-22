package test.decathlon.service;

import org.springframework.stereotype.Service;
import test.decathlon.dto.CalcResult;
import test.decathlon.dto.EventResult;
import test.decathlon.enums.Event;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculateService {

    public List<CalcResult> calc(List<EventResult> eventResults) {
        return eventResults.stream()
                .map(this::calcResult)
                .sorted((t1, t2) -> t2.getTotalPoint().compareTo(t1.getTotalPoint()))
                .collect(Collectors.toList());
    }

    private CalcResult calcResult(EventResult eventResult) {
        if (eventResult.getValues().size() != 10) {
            throw new IllegalArgumentException("event size must be eq 10");
        }
        int totalPoint = getTotalPoint(eventResult.getValues());

        CalcResult calcResult = new CalcResult();
        calcResult.setAthleteName(eventResult.getAthleteName());
        calcResult.setTotalPoint(totalPoint);
        return calcResult;
    }

    public int getTotalPoint(Map<Event, Float> values) {
        return values.keySet().stream()
                .mapToInt(k -> calc(k, values.get(k)))
                .sum();
    }

    private Integer calc(Event event, Float value) {
        if (value < 0f) {
            throw new IllegalArgumentException("value can't be less zero");
        }
        return (int) (event.a * Math.pow(Math.abs(event.b - value), event.c));
    }

}
