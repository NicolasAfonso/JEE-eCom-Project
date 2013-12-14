/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.PrintStream;

import java.lang.StringBuffer;

import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;


public class VarCommandImpl implements ShellCommand {
    private ShellContext _context = null;

    public VarCommandImpl(ShellContext context) {
        _context = context;
    }

    public String getName() {
        return "var";
    }

    public String getUsage() {
        return "var";
    }

    public String getShortDescription() {
        return "list the variables.";
    }

    public void execute(String cmdline, PrintStream out, PrintStream err) {
        try {
            Set varNames = _context.getVarNames();
            Iterator iterator = varNames.iterator();

            if (iterator == null) {
                return;
            }

            StringBuffer sb = new StringBuffer();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = _context.getVar(key);
                sb.append(key);
                sb.append('=');

                if (value != null) {
                    sb.append(value.toString());
                }

                sb.append('\n');
            }

            out.println(sb);
        } catch (Exception ex) {
            ex.printStackTrace(err);
        }
    }
}
