package uk.co.ivandimitrov.EQList;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.co.ivandimitrov.EQList.util.EQUtil;

@Controller
public class EarthquakeController {

    
    @RequestMapping("/")
    public ModelAndView query(@RequestParam(value = "minMag", required = false, defaultValue = "3") String minMag,
            @RequestParam(value = "maxMag", required = false, defaultValue = "10") String maxMag,
            @RequestParam(value = "minDate", required = false, defaultValue = "2020-01-01") String minDate,
            @RequestParam(value = "maxDate", required = false) String maxDate) {
        if (maxDate == null)
            maxDate = LocalDate.now().toString();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("eqdata");
        mv.addObject("earthquakes", EQUtil.getList(minMag, maxMag, minDate, maxDate));// gets a list of earthquakes and adds them to the ModelAndView object.
        return mv;
    }

}