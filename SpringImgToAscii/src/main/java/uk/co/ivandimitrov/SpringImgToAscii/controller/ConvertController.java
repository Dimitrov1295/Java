package uk.co.ivandimitrov.SpringImgToAscii.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import uk.co.ivandimitrov.SpringImgToAscii.util.ImageToAsciiConverter;

@Controller
public class ConvertController {

    @Autowired
    ImageToAsciiConverter converter;

    @RequestMapping("/")
    public ModelAndView home() {// Returns the home page where you have a form to fill with image data.
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping("convert")
    public ModelAndView convert(@RequestParam(value = "imageUrl", required = false) String url,
            @RequestParam(value = "imageFile", required = false) MultipartFile file)
            throws MalformedURLException, IOException {// Returns the converted image
        ModelAndView mv = new ModelAndView();
        mv.setViewName("image");
        InputStream in = null;
        if (url.length() > 0) {
            in = new URL(url).openStream();
        } else if (file != null) {
            in = file.getInputStream();
        }
        mv.addObject("ascii", converter.convertImageToAscii(in));
        return mv;
    }

}