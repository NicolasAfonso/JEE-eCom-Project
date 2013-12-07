package ecom.shell;

import java.io.PrintStream;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.stlagora.controller.SearchController;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.entities.Product;

import shell.ShellCommand;
import shell.ShellContext;

public class ProductCommandImpl implements ShellCommand, EcomShellConstantes {

	private ShellContext shellContext ;
	private EntityManager em; 
	private String context_language;
	public ProductCommandImpl(ShellContext context) {
		shellContext=context;
	}

	public String getName() {
		return "product";
	}

	public String getUsage() {
		return("product [-add|-remove|-list]");
	}

	public String getShortDescription() {
		return "List of Product";
	}

	public void execute(String cmdline, PrintStream out, PrintStream err) {
		StringTokenizer st = new StringTokenizer(cmdline, " ");
		context_language = (String)this.shellContext.getVar(CURRENT_LANGUAGE);
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
			}else if(format.equals("-add"))
			{	ProductDaoImpl p = new ProductDaoImpl("STLAGORA_PU_SHELL");
				System.out.println("Tutu");
			}
			else if(format.equals("-remove"))
			{
				ProductDaoImpl p = new ProductDaoImpl("STLAGORA_PU_SHELL");
				System.out.println("ID product :");
				String id = st.nextToken();
				Product product = p.findById(Long.valueOf(id));
				p.delete(product);
			}
			else if(format.equals("-list"))
			{
				ProductDaoImpl p = new ProductDaoImpl("STLAGORA_PU_SHELL");
				List<Product> products = p.findAll();
				if(products != null)
				{
					for(Product tmp : products)
					{
						System.out.println("id: " + tmp.getId() +
								" nom: " + tmp.getName() +
								" seller: " + tmp.getSeller().getLogin());
					}
				}
			}else
			{
				System.out.println("No product in database");
			}
		}
		
	}




}
