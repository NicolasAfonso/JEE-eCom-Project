package ecom.shell;

import java.io.PrintStream;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.codec.digest.DigestUtils;

import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.ROLE;

import shell.ShellCommand;
import shell.ShellContext;

public class UserCommandImpl implements ShellCommand, EcomShellConstantes {

	private ShellContext shellContext;
	private String context_language;
	
	
	public String getName() {
		return "usermanager";
	}

	public String getUsage() {
		return "usermanager [-add|-remove|list]";
	}

	public String getShortDescription() {
		return "UserManager";
	}
	
	public UserCommandImpl(ShellContext context) {
		shellContext = context;
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
			if(!format.equals("-add") && !format.equals("-remove") &&  !format.equals("-list"))
			{
				err.print("\""+cmdline+"\" : ");
				if(context_language.equals("EN")) err.println(ERROR_UNKNOWN_OR_UNSUPPORTED_ARGS_EN);
				else if(context_language.equals("FR")) err.println(ERROR_UNKNOWN_OR_UNSUPPORTED_ARGS_FR);
			}
			else if(format.equals("-add") && userRole.equals(ROLE_ADMIN))
			{	UserDaoImpl userDao = new UserDaoImpl("STLAGORA_PU_SHELL");
			Scanner sc = new Scanner(System.in);
			System.out.println("Create User Interface");
			System.out.println("login : ");
			String login = sc.nextLine();
			System.out.println("password : ");
			String password = sc.nextLine();
			System.out.println(password);
			System.out.println("email : ");
			String email = sc.nextLine();
			System.out.println("surname : ");
			String surname = sc.nextLine();
			System.out.println("firstname : ");
			String firstname = sc.nextLine();
			System.out.println("rib : ");
			String rib = sc.nextLine();
			System.out.println("phonenumber : ");
			String phoneNumber = sc.nextLine();	
			System.out.println("comapnyname : ");
			String companyName = sc.nextLine();
			System.out.println("role : ");
			String role = sc.nextLine();
			System.out.println("siret : ");
			String siret = sc.nextLine();
			System.out.println("accountType : ");
			String accountType = sc.nextLine();
		
			User u = new User(login,surname,firstname,email,password,new Date(System.currentTimeMillis()),phoneNumber,siret,companyName,rib,ACCOUNT_TYPE.PRIVATE,ROLE.MEMBER);
			try{
				userDao.create(u);
			}catch(Exception e)
			{	
				System.out.println("ERROR insert" +u.getLogin() +" in database : "
						+e.getMessage());
			}

			}
			else if(format.equals("-remove") && userRole.equals(ROLE_ADMIN))
			{
				UserDaoImpl userDao = new UserDaoImpl("STLAGORA_PU_SHELL");
				String login = st.nextToken();
				User user = userDao.findByLogin(login);
				userDao.delete(user);
			}
			else if(format.equals("-list"))
			{
				UserDaoImpl userDao= new UserDaoImpl("STLAGORA_PU_SHELL");
				List<User> users = userDao.findAll();
				if(users != null)
				{
					for(User tmp : users)
					{
						System.out.println("Id: " + tmp.getId() +
								" Login: " + tmp.getLogin() +
								" FirstName: " + tmp.getFirstname()+
								" Surname: " + tmp.getSurname());
					}
				}
			}else
			{
				System.out.println("ERROR RIGHT");
			}
		}
		
	}

}
