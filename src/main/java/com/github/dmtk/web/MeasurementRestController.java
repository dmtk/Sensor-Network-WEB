package com.github.dmtk.web;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.logic.MeasurementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementRestController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value = "/measurement", method = RequestMethod.GET)
    public List<Measurement> getAllTasks() {
        List<Measurement> measurements = measurementService.getList(1000);
        return measurements;

    }
    
    @RequestMapping(value = "/measurement/{id}", method = RequestMethod.GET)
    public Measurement getMeasurementById(@PathVariable("id") Long id) {
        Measurement measurement = measurementService.retrive(id);
        return measurement;

    }

}
