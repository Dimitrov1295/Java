package uk.co.ivandimitrov.SpringImgToAscii.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ij.ImagePlus;
import ij.process.ImageProcessor;

@Service
public class ImageToAsciiConverter {

    ImageToAsciiUtil util;

    @Autowired
    public ImageToAsciiConverter(ImageToAsciiUtil util) {
        this.util = util;
    }

    public String convertImageToAscii(String url, MultipartFile file) throws IOException {
        InputStream in = null;
        if (url.length() > 0) {
            in = new URL(url).openStream();
        } else if (file != null) {
            in = file.getInputStream();
        }
        return process(new ImagePlus("Image", ImageIO.read(in)));
    }

    // Processes the image pixel by pixel and sets appropriate ASCII chars for every
    // pixel value. Returns a string containing the ASCII picture.
    private String process(ImagePlus image) {
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setInterpolate(true);
        ImagePlus imp = new ImagePlus("", imageProcessor.resize(300, 300));
        int[] size = imp.getDimensions();
        int width = size[0], height = size[1];
        return IntStream.range(0, width * height)
                .map(i -> i % width != 0 ? util.convertToBrightness(imp.getPixel((i % width), (i / width))) : -1)
                .mapToObj(i -> i == -1 ? "\n" : util.getAsciiSymbol(i)).collect(Collectors.joining());
    }

    /*
     * Utility class for converting images to ASCII characters.
     */
    @Component
    private static class ImageToAsciiUtil {

        /**
         * @param arr the array containing pixel RGB data.
         * @return Returns the averaged RGB value. (( R + G + B ) / 3 )
         */
        private int convertToBrightness(int[] arr) {
            return ((arr[0] + arr[1] + arr[2]) / 3);
        }

        /**
         * @param s  the value to be changed
         * @param a1 lower range of s.
         * @param a2 upper range of s.
         * @param b1 new lower range.
         * @param b2 new upper range.
         * @return Returns s with values ranging from b1 to b2.
         */
        private float map(float s, float a1, float a2, float b1, float b2) {
            return b1 + (s - a1) * (b2 - b1) / (a2 - a1);
        }

        /**
         * .,:ilwW#MW&8%B@$
         * 
         * @param brightness The pixel brightness that's going to be associated with a
         *                   symbol.
         * @return Returns the ASCII symbol associated with the provided brightness.
         */
        private String getAsciiSymbol(int brightness) {
            String ASCII = " .,:ilwW#MW&8%B@$ШЩ";
            String s = String.valueOf(ASCII.charAt((int) map(brightness, 0, 255, 0, ASCII.length() - 1)));
            return s + s + s;
        }
    }
}