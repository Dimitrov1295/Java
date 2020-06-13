package uk.co.ivandimitrov.EQList.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import uk.co.ivandimitrov.EQList.data.Earthquake;

@Controller
public class EarthquakeController {

    @RequestMapping("/")
    public ModelAndView query(@RequestParam(value = "minMag", required = false, defaultValue = "3") String minMag,
            @RequestParam(value = "maxMag", required = false, defaultValue = "10") String maxMag,
            @RequestParam(value = "minDate", required = false, defaultValue = "2020-01-01") String minDate,
            @RequestParam(value = "maxDate", required = false) String maxDate) {
        if (maxDate == null)
            maxDate = LocalDate.now().toString();
        // Setting up the ModelAndView
        ModelAndView mv = new ModelAndView();
        mv.setViewName("eqdata");

        // The query URL
        String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&limit=1000&starttime=" + minDate
                + "&endtime=" + maxDate + "&minmagnitude=" + minMag + "&maxmagnitude=" + maxMag;

        // Setting up rest template
        RestTemplate restTemplate = new RestTemplate();
        Earthquake earthquake = restTemplate.getForObject(url, Earthquake.class);

        // Adding earthquakes to app context
        mv.addObject("earthquakes", earthquake.getFeatures());
        return mv;
    }

}