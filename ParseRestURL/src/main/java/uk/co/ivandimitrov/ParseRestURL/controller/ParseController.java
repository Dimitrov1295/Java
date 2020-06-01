package uk.co.ivandimitrov.ParseRestURL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.co.ivandimitrov.ParseRestURL.util.MyJsonParser;

@Controller
public class ParseController {

    // The controller. This takes a valid JSON source and formats it, to just then
    // add it to the model and view. It then returns the MV and displays the text on
    // the page using Thymeleaf(th:utext="${parsed}").
    @RequestMapping("/")
    public ModelAndView start(@RequestParam(value = "jsonSource", required = false) String source) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        if (source != null)
            mv.addObject("parsed", MyJsonParser.parse(source));
        mv.addObject("unparsed", source);
        return mv;
    }

}