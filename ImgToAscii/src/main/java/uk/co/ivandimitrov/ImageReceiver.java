package uk.co.ivandimitrov;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;

public class ImageReceiver extends ByteArrayOutputStream implements Receiver {

    /**
     *
     */
    private static final long serialVersionUID = -237074518764916519L;
    private byte[] bytes;

    /*
     * This code runs when the Upload component in MyUI.java gets an upload file
     * from the user.
     */
    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        this.bytes = null; // Resets the bytes value to null so that new uploads can overwrite this
                           // object's data.
        return this;
    }

    /*
     * Overriden write(b,off,len) method that works with the Upload class to write
     * uploaded data to this object.
     */
    @Override
    public synchronized void write(byte[] b, int off, int len) {
        bytes = appendData(bytes, b);
    }

    // Joins two byte[] together one after the other and returns the resulting
    // byte[].
    private byte[] appendData(byte[] firstObject, byte[] secondObject) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            if (firstObject != null && firstObject.length != 0)
                outputStream.write(firstObject);
            if (secondObject != null && secondObject.length != 0)
                outputStream.write(secondObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    /**
     * @return Returns a new input stream containing the bytes that have been
     *         received by this object.
     */
    public ByteArrayInputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

}