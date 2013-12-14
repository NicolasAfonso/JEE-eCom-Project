/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.util.Set;


public interface ShellContext {
    public ShellCommand[] getCommands();

    public Object getVar(String key);

    public Object unsetVar(String key);

    public Set getVarNames();

    public void setVar(String key, Object value);
}
