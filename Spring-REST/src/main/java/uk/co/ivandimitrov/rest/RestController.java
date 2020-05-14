package uk.co.ivandimitrov.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @Autowired
    RfamRepository rfamRepository;

    @RequestMapping(value = "/query")
    @ResponseBody
    public List<Family> getMembersWithParams(
            @RequestParam(value = "mincreatedate", required = false, defaultValue = "2010-01-01") String minCreateDate,
            @RequestParam(value = "maxcreatedate", required = false, defaultValue = "2035-01-01") String maxCreateDate) {
        return rfamRepository.findByCreateDate(minCreateDate + " 00:00:00", maxCreateDate + " 00:00:00");
    }
}