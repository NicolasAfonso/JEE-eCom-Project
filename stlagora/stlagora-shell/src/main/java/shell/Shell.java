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


public class Shell implements Runnable, ShellConstantes {
    private TreeMap _commandNameMap = new TreeMap();
    private ShellContext _context = new ShellContextImpl(_commandNameMap);
    private boolean stop = false;
    protected InputStream in;
    protected PrintStream out;
    protected PrintStream err;

    public Shell(String[] args) {
        out = System.out;
        err = System.err;

        if (!parseArgs(args)) {
            stop();
        }

        ShellContext context = getContext();
        // Add "context" command.
        addCommand(new VarCommandImpl(context));

        // Add "echo" command.
        addCommand(new EchoCommandImpl(context));

        // Add "help" command.
        addCommand(new HelpCommandImpl(context));

        // Add "prompt" command.
        //		addCommand(new PromptCommandImpl(context));

        // Add "quit" command.
        addCommand(new shell.SimpleQuitCommandImpl(context));

        // put the EBs' homes in the context
        context.setVar(PROMPT, "->");
        context.setVar(BANNER, getBanner());
        context.setVar(CURRENT_LANGUAGE, LANGUAGE_EN);
    }

    public static void main(String[] args) {
        // Start shell thread.
        //		new Thread(new Shell(args)).start();
        new Shell(args).run();
    }

    protected boolean parseArgs(String[] args) {
        if (args.length < 1) {
            in = System.in;

            return true;
        } else {
            try {
                in = new FileInputStream(args[0]);

                return true;
            } catch (java.io.FileNotFoundException f) {
                err.println("File \"" + args[0] + "\"not found");

                return false;
            }
        }
    }

    protected final ShellContext getContext() {
        return _context;
    }

    protected final void stop() {
        stop = true;
    }

    public final void run() {
        String banner;
        String line;
        BufferedReader br = null;

        if (!stop) {
            banner = (String) _context.getVar(BANNER);

            if (banner != null) {
                out.println(banner);
            }

            line = null;
            br = new BufferedReader(new InputStreamReader(in));
        }

        while (!stop) {
            String prompt;
            prompt = (String) _context.getVar(PROMPT);

            if (prompt == null) {
                prompt = ">";
            }

            out.print(prompt);

            try {
                line = br.readLine();
            } catch (IOException ex) {
                err.println("Could not read input, please try again.");

                continue;
            }

            line = line.trim();

            if (line.length() == 0) {
                continue;
            }

            try {
                executeCommand(line, out, err);
            } catch (Exception ex) {
                err.println("Shell: " + ex);
            }
        }
    }

    private String getBanner() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n-------------------------------------------------");
        sb.append("\n| Generic Shell                                 |");
        sb.append("\n| Author: D. Donsez, 11/2002                    |");
        sb.append("\n-------------------------------------------------");

        return sb.toString();
    }

    protected final synchronized void removeCommand(String name) {
        out.println("Removing command  : " + name);
        _commandNameMap.remove(name);
    }

    protected final synchronized void executeCommand(String commandLine,
        PrintStream out, PrintStream err) throws Exception {
        commandLine = commandLine.trim();

        String commandName = (commandLine.indexOf(' ') >= 0)
            ? commandLine.substring(0, commandLine.indexOf(' ')) : commandLine;
        ShellCommand command = getCommand(commandName);

        if (command != null) {
            command.execute(commandLine, out, err);
        } else {
            err.println("Command not found.");
        }
    }

    protected final synchronized ShellCommand getCommand(String name) {
        ShellCommand sc = (ShellCommand) _commandNameMap.get(name);

        return sc;
    }

    protected final synchronized void addCommand(ShellCommand sc) {
        _commandNameMap.put(sc.getName(), sc);
    }

    protected final synchronized void clearCommands() {
        _commandNameMap.clear();
    }
}
