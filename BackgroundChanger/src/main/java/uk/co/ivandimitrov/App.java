<<<<<<< HEAD
package uk.co.ivandimitrov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static interface User32 extends Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }

    public static void main(String[] args) {
        changeBackground();
    }

    private static void changeBackground() {
        String loc = Paths.get("Java\\BackgroundChanger").toAbsolutePath().toString() + "\\bg\\bg.jpg";
        try (ReadableByteChannel readChannel = Channels
                .newChannel(new URL("https://source.unsplash.com/1600x900/?").openStream());
                FileOutputStream fileOS = new FileOutputStream(loc);
                FileChannel writeChannel = fileOS.getChannel();) {
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
            User32.INSTANCE.SystemParametersInfo(0x0014, 0, loc, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
=======
package uk.co.ivandimitrov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static interface User32 extends Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }

    public static void main(String[] args) {
        changeBackground();
    }

    private static void changeBackground() {
        String loc = Paths.get("Java\\BackgroundChanger").toAbsolutePath().toString() + "\\bg\\bg.jpg";
        try (ReadableByteChannel readChannel = Channels
                .newChannel(new URL("https://source.unsplash.com/1600x900/?").openStream());
                FileOutputStream fileOS = new FileOutputStream(loc);
                FileChannel writeChannel = fileOS.getChannel();) {
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
            User32.INSTANCE.SystemParametersInfo(0x0014, 0, loc, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> 2a010a0... Add files via upload
