package uk.co.ivandimitrov.SpringImgToAscii.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

public class ControllerUtil {

    /**
     * Gets the ASCII chars you get when you convert the image.
     * 
     * @param url The image url.
     * @return returns a String containing the converted image chars, or an error
     *         message if there's an error.
     */
    public static String getAsciiFromUrl(String url) {
        try (InputStream in = new URL(url).openStream();) {
            return ImageToAsciiConverter.convertImageToAscii(in);
        } catch (MalformedURLException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * Gets the ASCII chars you get when you convert the image.
     * 
     * @param url The image file.
     * @return returns a String containing the converted image chars, or an error
     *         message if there's an error.
     */
    public static String getAsciiFromFile(MultipartFile file) {
        try (InputStream in = file.getInputStream();) {
            return ImageToAsciiConverter.convertImageToAscii(in);
        } catch (IOException e) {
            return e.toString();
        }
    }
}