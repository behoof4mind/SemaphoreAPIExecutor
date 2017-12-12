package SemaphoreAPIExecutor;


import org.apache.commons.cli.*;

import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.io.*;

public class HttpsClient extends SemaphoreConnector {

    public static StringBuffer getAPIData(HttpURLConnection connection) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuffer output = new StringBuffer();
        do {
            try {
                if (br != null) {
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output.append(line).append("\n");
        } while (line != null);
        return output;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ParseException {

        new ArgsHandler(args);
        ArgsHandler.SKIP_SSL_CHECK = ArgsHandler.COMMAND_LINE.hasOption("skip");
        if (ArgsHandler.COMMAND_LINE.hasOption("help")) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp(ArgsHandler.SyntaxHelp, ArgsHandler.SyntaxHeader, ArgsHandler.POSIX_OPTIONS, ArgsHandler.SyntaxFooter, true);
        } else {
            String argIpaddr = ArgsHandler.COMMAND_LINE.getOptionValues("ipaddr")[0];
            Integer argProjectId = Integer.valueOf(ArgsHandler.COMMAND_LINE.getOptionValues("project-id")[0]);
            String argYmlFile = ArgsHandler.COMMAND_LINE.getOptionValues("yml-file")[0];
            Integer argTemplateId = Integer.valueOf(ArgsHandler.COMMAND_LINE.getOptionValues("template-id")[0]);
            String argUserName = ArgsHandler.COMMAND_LINE.getOptionValues("username")[0];
            String argPassWord = ArgsHandler.COMMAND_LINE.getOptionValues("password")[0];
            SemaphoreConnector semaphoreConnector = new SemaphoreConnector(argIpaddr, argUserName, argPassWord);
            semaphoreConnector.executeSemaphoreTask(argProjectId, argTemplateId, argYmlFile);
        }
    }
}



