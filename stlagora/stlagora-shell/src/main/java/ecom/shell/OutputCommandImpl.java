/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package ecom.shell;

import shell.ShellCommand;
import shell.ShellContext;

import java.io.PrintStream;

import java.util.StringTokenizer;


public class OutputCommandImpl implements ShellCommand, EcomShellConstantes {
    private ShellContext context = null;

    public OutputCommandImpl(ShellContext context) {
        this.context = context;
    }

    public String getName() {
        return "output";
    }

    public String getUsage() {
        return "output [HTML|WML|XML|TEXT]";
    }

    public String getShortDescription() {
        return "get or set the current output format.";
    }

    public void execute(String cmdline, PrintStream out, PrintStream err) {
        StringTokenizer st = new StringTokenizer(cmdline, " ");

        // Ignore the command name.
        st.nextToken();

        // There should be at least one string.
        if (st.countTokens() == 0) {
            out.println("Current output format is " +
                context.getVar(OUTPUT_MIME_FORMAT));
        } else if (st.countTokens() == 1) {
            String format = st.nextToken();

            if (format.equals("TEXT")) {
                context.setVar(OUTPUT_MIME_FORMAT, TEXT_MIMETYPE);
            } else if (format.equals("XML")) {
                context.setVar(OUTPUT_MIME_FORMAT, XML_MIMETYPE);
            } else {
                err.println("Unknown or unsupported format");
            }
        } else {
            err.println("Incorrect number of arguments");
        }
    }
}
