package controller;

import java.io.*;
import java.util.Date;

/**
 *
 * Main class for the Chess game
 *
 * Contains main method that starts a controller and sets error handling.
 */
public class Chess {
    private static ByteArrayOutputStream tempErr;

    public static void main(String[] args) {
        tempErr = new ByteArrayOutputStream(); //temporary errlog

        PrintStream ps = new PrintStream(tempErr);
        System.setErr(ps); //reroutes the output of system err to tempErr
        setUpHook();

        new ChessController();
    }

    /**
     * Sets up a shutdown hook that writes the errlog to a file if anything has
     * been written to it when the program shuts down.
     */
    public static void setUpHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (tempErr.size() > 0){

                    try {

                        FileOutputStream fos = new FileOutputStream(
                                new File("errlog.txt"),true);
                        PrintStream date = new PrintStream(fos);
                        date.print("\n" + new Date().toString() + "\n\n");
                        tempErr.writeTo(fos);
                        fos.close();
                    } catch (IOException e) {

                        System.setErr(new PrintStream(
                                new FileOutputStream(FileDescriptor.err)));
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
