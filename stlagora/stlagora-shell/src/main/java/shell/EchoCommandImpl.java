/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.PrintStream;

import java.util.StringTokenizer;


public class EchoCommandImpl implements ShellCommand {
    private ShellContext context = null;

    public EchoCommandImpl(ShellContext context) {
        this.context = context;
    }

    public String getName() {
        return "echo";
    }

    public String getUsage() {
        return "echo <messages>";
    }

    public String getShortDescription() {
        return "echo messages.";
    }

    public void execute(String cmdline, PrintStream out, PrintStream err) {
        StringTokenizer st = new StringTokenizer(cmdline, " ");

        // Ignore the command name.
        st.nextToken();

        // There should be at least one string.
        if (st.countTokens() >= 0) {
            while (st.hasMoreTokens()) {
                out.print(st.nextToken().trim());
                out.print(' ');
            }

            out.println();
        } else {
            err.println("Incorrect number of arguments");
        }
    }
}
