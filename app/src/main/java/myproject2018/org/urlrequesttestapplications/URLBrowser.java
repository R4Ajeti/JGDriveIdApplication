package myproject2018.org.urlrequesttestapplications;

import java.net.*;
import java.io.*;

public class URLBrowser {
    public static void main(String args[]) {
        // Check that the correct number of arguments have been passed
        if (args.length == 1) {
            try {
                // Create a URL object for the URL passed on the command line
                URL url = new URL(args[0]);

                // Retrieve the InputStream from the URL
                InputStream is = url.openStream();

                // Create a BufferedInputStream to increase performance
                is = new BufferedInputStream(is);

                // Chain the InputStream to a Reader
                Reader reader  = new InputStreamReader(is);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }
            catch (MalformedURLException mfe) {
                System.err.println(args[0] + " is not a valid URL");
            }
            catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
        else {
            // Handle incorrect number or arguments
            System.err.println("Usage: URLBrowser <valid_url>");
        }
    }
}