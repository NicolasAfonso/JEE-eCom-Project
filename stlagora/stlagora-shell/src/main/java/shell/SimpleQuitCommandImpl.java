/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.PrintStream;

import java.util.StringTokenizer;


public class SimpleQuitCommandImpl implements ShellCommand {
    private ShellContext _context = null;

    public SimpleQuitCommandImpl(ShellContext context) {
        _context = context;
    }

    public String getName() {
        return "quit";
    }

    public String getUsage() {
        return "quit";
    }

    public String getShortDescription() {
        return "Quit the shell";
    }

    public void execute(String s, PrintStream out, PrintStream err) {
        System.exit(0);

        return;
    }
}
