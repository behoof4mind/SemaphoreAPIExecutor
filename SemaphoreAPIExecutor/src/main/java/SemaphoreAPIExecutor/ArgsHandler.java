package SemaphoreAPIExecutor;

import org.apache.commons.cli.*;


final class ArgsHandler {

    static boolean SKIP_SSL_CHECK = true;
    static CommandLine COMMAND_LINE;
    static Options POSIX_OPTIONS;
    static String SyntaxHeader = "\n\nOptions:";
    static String SyntaxHelp = "semaphorexec";
    static String SyntaxFooter = "\n\nPlease report issues at https://github.com/Sloneg-ETU/SemaphoreAPIExecutor";

    ArgsHandler(String[] args) throws ParseException {
        Options posixOptions = new Options();
        Option argSemaphoreIP = new Option("i", "ipaddr", true, "Semaphore ip address");
        posixOptions.addOption(argSemaphoreIP);
        Option argSemaphoreProjectID = new Option("p", "project-id", true, "Semaphore project ID");
        posixOptions.addOption(argSemaphoreProjectID);
        Option argSemaphoreYMLFileName = new Option("y", "yml-file", true, "Executable Ansible YML-file");
        posixOptions.addOption(argSemaphoreYMLFileName);
        Option argSemaphoreTemplateID = new Option("t", "template-id", true, "Semaphore template ID");
        posixOptions.addOption(argSemaphoreTemplateID);
        Option argSemaphoreUsername1 = new Option("U", "username", true, "Semaphore API username");
        posixOptions.addOption(argSemaphoreUsername1);
        Option argSemaphorePassword = new Option("P", "password", true, "Semaphore API user password");
        posixOptions.addOption(argSemaphorePassword);
        Option argGetHelp = new Option("h", "help", false, "Show this help message");
        posixOptions.addOption(argGetHelp);
        Option argGetSSLCheck = new Option("k", "skip-ssl-check", false, "Skip SSL/TLS certificate check");
        posixOptions.addOption(argGetSSLCheck);
        ArgsHandler.POSIX_OPTIONS = posixOptions;
        CommandLineParser cmdLinePosixParser = new PosixParser();
        ArgsHandler.COMMAND_LINE = cmdLinePosixParser.parse(posixOptions, args);

    }


}
