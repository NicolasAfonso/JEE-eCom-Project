/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.PrintStream;

import java.util.StringTokenizer;


public class HelpCommandImpl implements ShellCommand {
    private ShellContext _context = null;

    public HelpCommandImpl(ShellContext context) {
        _context = context;
    }

    public String getName() {
        return "help";
    }

    public String getUsage() {
        return "help";
    }

    public String getShortDescription() {
        return "display shell commands.";
    }

    public void execute(String s, PrintStream out, PrintStream err) {
        try {
            ShellCommand[] cmds = _context.getCommands();
            String[] usage = new String[cmds.length];
            String[] desc = new String[cmds.length];
            int maxUsage = 0;

            for (int i = 0; i < cmds.length; i++) {
                usage[i] = cmds[i].getUsage();
                desc[i] = cmds[i].getShortDescription();

                // Just in case the command has gone away.
                if ((usage[i] != null) && (desc[i] != null)) {
                    maxUsage = Math.max(maxUsage, usage[i].length());
                }
            }

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < cmds.length; i++) {
                // Just in case the command has gone away.
                if ((usage[i] != null) && (desc[i] != null)) {
                    sb.delete(0, sb.length());

                    for (int j = 0; j < (maxUsage - usage[i].length()); j++) {
                        sb.append(' ');
                    }

                    out.println(usage[i] + sb + " - " + desc[i]);
                }
            }
        } catch (Exception ex) {
            err.println(ex.toString());
        }
    }
}
