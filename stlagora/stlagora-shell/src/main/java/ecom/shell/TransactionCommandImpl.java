package ecom.shell;

import java.io.PrintStream;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.derby.tools.sysinfo;

import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;

import shell.ShellCommand;
import shell.ShellContext;

public class TransactionCommandImpl  implements ShellCommand, EcomShellConstantes {
	private ShellContext shellContext;
	private String context_language;

	public TransactionCommandImpl  (ShellContext context) {
		shellContext=context;
	}
	public String getName() {
		return "transaction";
	}

	public String getUsage() {
		return "transaction [-list all|-list user idUser [-o buy|sell] ]";
	}

	public String getShortDescription() {
		return	"give the list of transaction";
	}

	public void execute(String cmdline, PrintStream out, PrintStream err) {
		StringTokenizer st = new StringTokenizer(cmdline, " ");
		context_language = (String)this.shellContext.getVar(CURRENT_LANGUAGE);
		String userRole = (String)this.shellContext.getVar(USERROLE);
		st.nextToken();
		if (st.countTokens() == 0)
		{
			System.out.println(getUsage());
		}
		else{

			String  format = st.nextToken();
			if(!format.equals("-list"))
			{
				err.print("\""+cmdline+"\" : ");
				if(context_language.equals("EN")) err.println(ERROR_UNKNOWN_OR_UNSUPPORTED_ARGS_EN);
				else if(context_language.equals("FR")) err.println(ERROR_UNKNOWN_OR_UNSUPPORTED_ARGS_FR);
			}else if(format.equals("-list"))
			{	
				TransactionDaoImpl transactionDao = new TransactionDaoImpl("STLAGORA_PU_SHELL");
				UserDaoImpl userDao = new UserDaoImpl("STLAGORA_PU_SHELL");
				if(!st.hasMoreTokens())
				{
					List<Transaction> transactions = transactionDao.findAll();
					System.out.println("### List of Transaction");
					for(Transaction tmp : transactions)
					{
						System.out.println("Id: " + tmp.getId() +
								" Buyer: " + tmp.getBuyer().getLogin() +
								" Seller: " + tmp.getSeller().getLogin()+
								" Ammount: " + tmp.getAmount() +
								"Date: "+ tmp.getDate());
					}
				}else if(format.equals("-user")) {
					String userId = st.nextToken();
					if(!st.hasMoreTokens() && userId != null){
						User user = userDao.findById(Long.getLong(userId));
						List<Transaction> transactions = transactionDao.findByBuyer(user);
						System.out.println("### User Purchase ###");
						for(Transaction tmp : transactions)
						{
							System.out.println("Id: " + tmp.getId() +
									" Buyer: " + tmp.getBuyer().getLogin() +
									" Seller: " + tmp.getSeller().getLogin()+
									" Ammount: " + tmp.getAmount() +
									"Date: "+ tmp.getDate());
						}

						System.out.println("### User Sell ###");
						transactions = transactionDao.findBySeller(user);
						for(Transaction tmp : transactions)
						{
							System.out.println("Id: " + tmp.getId() +
									" Buyer: " + tmp.getBuyer().getLogin() +
									" Seller: " + tmp.getSeller().getLogin()+
									" Ammount: " + tmp.getAmount() +
									"Date: "+ tmp.getDate());
						}
					}
					else
					{
						format = st.nextToken();
						if(format.equals("-o") && userId != null )
						{

							if(!st.hasMoreTokens()){
								getUsage();
							}
							else 
							{
								format= st.nextToken();

								if(format.equals("buy")){
									User user = userDao.findById(Long.getLong(userId));
									List<Transaction> transactions = transactionDao.findByBuyer(user);
									System.out.println("### User Purchase ###");
									for(Transaction tmp : transactions)
									{
										System.out.println("Id: " + tmp.getId() +
												" Buyer: " + tmp.getBuyer().getLogin() +
												" Seller: " + tmp.getSeller().getLogin()+
												" Ammount: " + tmp.getAmount() +
												"Date: "+ tmp.getDate());
									}
								}else if(format.equals("sell") && userId != null)
								{
									User user = userDao.findById(Long.getLong(userId));
									List<Transaction> transactions = transactionDao.findBySeller(user);
									System.out.println("### User Sell ###");
									for(Transaction tmp : transactions)
									{
										System.out.println("Id: " + tmp.getId() +
												" Buyer: " + tmp.getBuyer().getLogin() +
												" Seller: " + tmp.getSeller().getLogin()+
												" Ammount: " + tmp.getAmount() +
												"Date: "+ tmp.getDate());
									}
								}
							}
						}
					}
				}
			}

		}
	}

}


