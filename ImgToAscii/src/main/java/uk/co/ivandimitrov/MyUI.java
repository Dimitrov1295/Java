package uk.co.ivandimitrov;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        // Declarations of this small UI.
        TextField link = new TextField();
        Label introLabel = new Label();
        Upload uploadButton = new Upload("", new ImageReceiver());
        Label output = new Label();
        Label or = new Label("or");

        introLabel.setId("intro");

        output.setId("output");
        output.setContentMode(ContentMode.PREFORMATTED);

        uploadButton.setButtonCaption("Upload an image");
        uploadButton.setAcceptMimeTypes("image/*");
        // Checks if the mimeType is an image. If it's not, it stops the upload and
        // shows an error notification.
        uploadButton.addStartedListener(e -> {
            if (!e.getMIMEType().contains("image")) {
                uploadButton.interruptUpload();
                Notification.show("Unsupported format", Notification.TYPE_ERROR_MESSAGE).setDelayMsec(2000);
            }
        });
        // Gets the receiver, which holds the data from the upload and tries to pass
        // that
        // data in the imgToAscii conversion method. If it fails it displays a
        // notification.
        uploadButton.addSucceededListener(e -> {
            try {
                InputStream inputStream = ((ImageReceiver) (e.getUpload().getReceiver())).getInputStream();
                String ascii = ImageToAsciiConverter.convertImageToAscii(inputStream);
                output.setValue(ascii);
            } catch (IOException e1) {
                Notification.show("Conversion failed.", Notification.TYPE_ERROR_MESSAGE).setDelayMsec(2000);
                e1.printStackTrace();
            }
        });

        link.setPlaceholder("Paste an image link");
        // Once the value of the TextField is changet, it tries to get a URL from the
        // changed value. If the URL is not valid it shows an error notification. If it
        // succeeds, it opens a stream and it passes it to the
        // imgToAscii conversion method. Else, it shows an error notification.
        link.addValueChangeListener(e -> {
            try {
                String ascii = ImageToAsciiConverter.convertImageToAscii(new URL(e.getValue()).openStream());
                output.setValue(ascii);
            } catch (IOException e1) {
                if (e1 instanceof MalformedURLException)
                    Notification.show("Invalid URL.", Notification.TYPE_ERROR_MESSAGE).setDelayMsec(2000);
                else
                    Notification.show("Conversion failed.", Notification.TYPE_ERROR_MESSAGE).setDelayMsec(2000);
                e1.printStackTrace();
            }
        });

        introLabel.setValue(
                "This is an image to ASCII converter. It takes an image and transforms it into ASCII characters, which when looked at on a black background, look like the image itself.");
        introLabel.setWidthFull();

        // Adds the components and sets their alignment.
        layout.addComponents(introLabel, link, or, uploadButton, output);
        layout.setComponentAlignment(introLabel, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(or, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(output, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(uploadButton, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(link, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
