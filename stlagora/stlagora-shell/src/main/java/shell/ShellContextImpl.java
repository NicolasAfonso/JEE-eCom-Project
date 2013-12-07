/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package shell;

import java.io.*;
import java.io.PrintStream;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


class ShellContextImpl implements ShellContext {
    private Map _commands;
    private Map _environnementVariables;

    public ShellContextImpl(Map commands) {
        _commands = commands;
        _environnementVariables = new TreeMap();
    }

    public synchronized ShellCommand[] getCommands() {
        ShellCommand[] cmds = new ShellCommand[_commands.size()];
        Iterator iterator = _commands.values().iterator();

        for (int i = 0; iterator.hasNext(); i++) {
            cmds[i] = (ShellCommand) iterator.next();
        }

        return cmds;
    }

    public Object getVar(String key) {
        return _environnementVariables.get(key);
    }

    public Set getVarNames() {
        return _environnementVariables.keySet();
    }

    public void setVar(String key, Object value) {
        _environnementVariables.put(key, value);
    }

    public Object unsetVar(String key) {
        return _environnementVariables.remove(key);
    }
}
