package ecom.shell.client;

import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.derby.tools.sysinfo;

import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.User;

import ecom.shell.EcomShell;

public class Client {

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl("STLAGORA_PU_SHELL");
		boolean connected = false;
		Scanner sc = new Scanner(System.in);
		User user = null;
		while(!connected)
		{
		System.out.println("Login :");
		String login = sc.nextLine();
		System.out.println("Password :");
		String password = sc.nextLine();
		
		try{
			user = userDao.findByLogin(login);
		}
		catch(Exception e){
			System.out.println("Bad Credential");
		}
		
		if(user != null)
		{
			String shaPassword = new String(DigestUtils.sha512(password));
			if(user.getPassword().equals(shaPassword))
			{
				connected = true; 
			}
		}

		}
		System.out.println("Connected");

		new EcomShell(args,user.getRole()).run();
		
	}
	
}
