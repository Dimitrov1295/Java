package uk.co.ivandimitrov.SpringImgToAscii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import uk.co.ivandimitrov.SpringImgToAscii.util.ControllerUtil;

@Controller
public class ConvertController {

    @RequestMapping("/")
    public ModelAndView home() {// Returns the home page where you have a form to fill with image data.
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping("convert")
    public ModelAndView convert(@RequestParam(value = "imageUrl", required = false) String url,
            @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("image");
        if (url.length() > 0) {// Checks if the URL is present, and if it is, converts that image and displays
                               // it. Otherwise checks the file and if not present gives an error.
            mv.addObject("ascii", ControllerUtil.getAsciiFromUrl(url));
        } else if (file != null) {
            mv.addObject("ascii", ControllerUtil.getAsciiFromFile(file));
        }
        return mv;
    }

}