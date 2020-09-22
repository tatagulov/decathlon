package test.decathlon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.decathlon.dto.CalcResult;
import test.decathlon.dto.EventResult;
import test.decathlon.service.CalculateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calc")
public class CalculateController {

    private final CalculateService calculateService;

    @PostMapping("/calc")
    public List<CalcResult> calc(@Valid @RequestBody List<EventResult> eventResults)  {
        return calculateService.calc(eventResults);
    }
}
