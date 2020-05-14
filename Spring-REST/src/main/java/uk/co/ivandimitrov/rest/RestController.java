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
    RfamRepository rfamRepository; // Used to get items from the database.

    /**
     * This method is called when you visit http://localhost:8080/query
     * <p>
     * http://localhost:8080/query?mincreatedate=2014-04-16&maxcreatedate=2020-04-16
     * is a request with optional RequestParam variables "mincreatedate" and
     * "maxcreatedate"
     */
    @RequestMapping(value = "/query")
    @ResponseBody // This method's return value is the webpage's body after execution.
    public List<Family> getMembersWithParams(
            @RequestParam(value = "mincreatedate", required = false, defaultValue = "2010-01-01") String minCreateDate,
            @RequestParam(value = "maxcreatedate", required = false, defaultValue = "2035-01-01") String maxCreateDate) {
        return rfamRepository.findByCreateDate(minCreateDate + " 00:00:00", maxCreateDate + " 00:00:00");
    }
}