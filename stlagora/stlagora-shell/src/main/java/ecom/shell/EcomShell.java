/**
 * eCommerce Application Sample for J2EE Training
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002
 */
package ecom.shell;

import shell.Shell;
import shell.ShellContext;


public class EcomShell extends Shell implements EcomShellConstantes {
    public EcomShell(String[] args) {
        super(args);

        ShellContext context = getContext();
        // Remove or add commands

        // Remove "echo" command.
        // removeCommand("echo");

        // Add "begin" command.
        //addCommand(new ecom.shell.BeginTxCommandImpl(context));
        // Add "commit" command.
        //addCommand(new ecom.shell.CommitCurrentTxCommandImpl(context));
        // Add "rollback" command.
        //addCommand(new ecom.shell.RollbackCurrentTxCommandImpl(context));
        // Add "account" command.
        addCommand(new ecom.shell.UserCommandImpl(context));
        // Add "product" command.
        addCommand(new ecom.shell.ProductCommandImpl(context));
        // Add "productstore" command.
        //addCommand(new ecom.shell.ProductStoreCommandImpl(context));
        // Add "cart" command.
        //addCommand(new ecom.shell.CartCommandImpl(context));

        // Add "quit" command.
        //addCommand(new ecom.shell.QuitCommandImpl(context));

        // Add "currency" command.
        //addCommand(new ecom.shell.CurrencyCommandImpl(context));
        // Add "output" command.
        addCommand(new ecom.shell.OutputCommandImpl(context));

        // put the EBs' homes in the context
        //context.put(ACCOUNT_HOME,...);
        //context.put(PRODUCT_HOME,...);
        //context.put(PRODUCTSTORE_HOME,...);
        //context.put(CART_HOME,...);
        //context.put(EUROCONVERTOR_HOME,...);
        context.setVar(OUTPUT_MIME_FORMAT, TEXT_MIMETYPE);
        context.setVar(CURRENT_CURRENCY, CURRENCY_EUR);
        context.setVar(CURRENT_TX, null);

        context.setVar(BANNER, getBanner());
    }

    public static void main(String[] args) {
        new EcomShell(args).run();
    }

    protected String getBanner() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n-------------------------------------------------");
        sb.append("\n| eCommerce Application Sample for J2EE Training |");
        sb.append("\n| Authors: F. Boyer, D. Donsez, 07/2000-10/2006  |");
        sb.append("\n-------------------------------------------------");

        return sb.toString();
    }
}
