package SemaphoreAPIExecutor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


class SemaphoreConnector extends TrustModifier{
    private static String argSemaphoreIP;
    private static String argSemaphoreUsername;
    private static String argSemaphorePassword;
    private static String argCookie;

    SemaphoreConnector(String argSemaphoreIP, String argSemaphoreUsername, String argSemaphorePassword) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        SemaphoreConnector.argSemaphoreIP = argSemaphoreIP;
        SemaphoreConnector.argSemaphoreUsername = argSemaphoreUsername;
        SemaphoreConnector.argSemaphorePassword = argSemaphorePassword;
        SemaphoreConnector.argCookie = createCookie();
    }

    SemaphoreConnector() {
    }

    private String createCookie() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String SemaphoreURLString = "https://" + argSemaphoreIP + "/api/auth/login";
        URL SemaphoreURL = new URL(SemaphoreURLString);
        HttpURLConnection _connectionFirstTime = (HttpURLConnection) SemaphoreURL.openConnection();
        _connectionFirstTime.setDoOutput(true);
        _connectionFirstTime.setRequestMethod("POST");
        _connectionFirstTime.setRequestProperty("Content-Type", "application/json");
        String input = "{\"auth\":\"" + argSemaphoreUsername + "\",\"password\":\"" + argSemaphorePassword + "\"}";
        if (!ArgsHandler.SKIP_SSL_CHECK) relaxHostChecking(_connectionFirstTime);
        OutputStream os = _connectionFirstTime.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        Integer ResponseCode = _connectionFirstTime.getResponseCode();
        return  _connectionFirstTime.getHeaderField("Set-Cookie");
    }



    void executeSemaphoreTask(Integer ProjectID, Integer TemplateID, String YamlFile) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String SemaphoreURLString = "https://" + argSemaphoreIP + "/api/project/" + ProjectID + "/tasks";
        URL SemaphoreURL = new URL(SemaphoreURLString);
        HttpURLConnection _MethodConnection = (HttpURLConnection) SemaphoreURL.openConnection();
        _MethodConnection.setDoOutput(true);
        _MethodConnection.setRequestMethod("POST");
        _MethodConnection.setRequestProperty("Content-Type", "application/json");
        _MethodConnection.setRequestProperty("Accept", "application/json");
        _MethodConnection.setRequestProperty("Cookie", argCookie);
        String input = "{\"template_id\": " + TemplateID + ", \"debug\": false, \"dry_run\": false, \"playbook\": \"" + YamlFile +
            "\", \"environment\":\"\"}";
        if (!ArgsHandler.SKIP_SSL_CHECK) relaxHostChecking(_MethodConnection);
        OutputStream os1 = _MethodConnection.getOutputStream();
        os1.write(input.getBytes());
        os1.flush();
        Integer ResponseCode = _MethodConnection.getResponseCode();
//        return _MethodConnection;

    }
}
