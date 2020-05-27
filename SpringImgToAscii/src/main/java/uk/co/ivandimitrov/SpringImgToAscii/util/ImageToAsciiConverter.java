package uk.co.ivandimitrov.SpringImgToAscii.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageToAsciiConverter {

    public static synchronized String convertImageToAscii(InputStream in) throws IOException {
        ImagePlus image = new ImagePlus("Image", ImageIO.read(in));
        return new ImageToAsciiConverter().process(image);
    }

    // Processes the image pixel by pixel and sets appropriate ASCII chars for every
    // pixel value. Returns a string containing the ASCII picture.
    private String process(ImagePlus image) {
        ImageProcessor imageProcessor = image.getProcessor();
        imageProcessor.setInterpolate(true);
        ImagePlus imp = new ImagePlus("", imageProcessor.resize(300, 300));
        int[] size = imp.getDimensions();
        int width = size[0], height = size[1];
        ImageToAsciiUtil util = new ImageToAsciiUtil();
        return IntStream.range(0, width * height)
                .map(i -> i % width != 0 ? util.convertToBrightness(imp.getPixel((i % width), (i / width))) : -1)
                .mapToObj(i -> i == -1 ? "<br/>" : util.getAsciiSymbol(i)).collect(Collectors.joining());
    }

    /**
     * Utility class for converting images to ASCII characters.
     */
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
            String ASCII = ".,:ilwW#MW&8%B@$ШЩ";
            String s = String.valueOf(ASCII.charAt((int) map(brightness, 0, 255, 0, ASCII.length() - 1)));
            return s + s + s;
        }
    }

}