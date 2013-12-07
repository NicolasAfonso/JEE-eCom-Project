/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.PrintStream;


public interface ShellCommand {
    public String getName();

    public String getUsage();

    public String getShortDescription();

    public void execute(String cmdline, PrintStream out, PrintStream err);
}
