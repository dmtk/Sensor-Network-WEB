package com.github.dmtk.web;

import com.github.dmtk.entity.Sensor;
import com.github.dmtk.logic.SensorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorRestController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public List<Sensor> getAll() {
        List<Sensor> sensors = sensorService.getList();
        return sensors;

    }
    
    @RequestMapping(value = "/sensor/{id}", method = RequestMethod.GET)
    public Sensor getSensorById(@PathVariable("id") Long id) {
        Sensor sensor = sensorService.retrive(id);
        return sensor;

    }
    
    @RequestMapping(value = "/sensor/{id}/name", method = RequestMethod.GET)
    public String getSensorNameById(@PathVariable("id") Long id) {
        String sensorName = sensorService.retrive(id).getName();
        return sensorName;

    }

}
