package ecom.shell.database;

import java.io.File;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

import javax.ejb.FinderException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

public class DatabaseCreator {

	public static char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static char[] charsExtended = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,;:. ".toCharArray();

	public static String[] imgPath = {"pathImg1","pathImg2","pathImg3","pathImg4","pathImg5", ""};
	public static String[] planPath = {"pathPlan1","pathPlan2","pathPlan3","pathPlan4","pathPlan5"};
	public static String[] productStatus = {"Available", "Not Available"};
	public static String[] categoryname={"Jouets","Gadgets", "Pièces de rechange", "Art & Déco", "Outils", "Objets du quotidien" };
	public static String[] planType = {"STL"};
	public static String[] sellerlist = {"tutu","tata","toto"};

	/**
	 * 
	 * @param length
	 * @return a random string of the specified length
	 */
	public static String createRandomString(int length, boolean typeTable1){
		char[] table = (typeTable1)? chars:charsExtended;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = table[random.nextInt(table.length)];
			sb.append(c);
		} 
		return sb.toString();
	}

	public static int randBetween(int start, int end) {
		return start + (int)Math.round(Math.random() * (end - start));
	}

	public static String createRandomDate(){
		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2013);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		return gc.get(gc.DAY_OF_MONTH) + "/" + gc.get(gc.MONTH) + "/" + gc.get(gc.YEAR);
	}


	//public int randomInt

	public void create(int nbEntries) {
		// the first argument is the number of products we want to put in our database
		try {

			long i = 0;
			Random random = new Random();

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("database");
			doc.appendChild(rootElement);

			Element productTable = doc.createElement("table");
			rootElement.appendChild(productTable);

			productTable.setAttribute("name", "product");

			

			for(;i<nbEntries;i++){

				Element productTable_row = doc.createElement("row");
				productTable.appendChild(productTable_row);



				Element productTable_id = doc.createElement("id");
				productTable_row.appendChild(productTable_id);

				productTable_id.appendChild(doc.createTextNode(String.valueOf(i)));

				Element productTable_name = doc.createElement("name");
				productTable_row.appendChild(productTable_name);

				productTable_name.appendChild(doc.createTextNode(createRandomString(5,true)));

				Element productTable_description = doc.createElement("description");
				productTable_row.appendChild(productTable_description);

				productTable_description.appendChild(doc.createTextNode(createRandomString(50,false)));

				Element productTable_image = doc.createElement("images");
				productTable_row.appendChild(productTable_image);

				productTable_image.appendChild(doc.createTextNode(imgPath[random.nextInt(imgPath.length)]));

				Element productTable_plan = doc.createElement("plan");
				productTable_row.appendChild(productTable_plan);
				productTable_plan.appendChild(doc.createTextNode(planPath[random.nextInt(planPath.length)]));

				Element productTable_price = doc.createElement("price");
				productTable_row.appendChild(productTable_price);

				int d1 = random.nextInt(50);
				int d2 = random.nextInt(100);
				String str = String.valueOf(d1)+"."+String.valueOf(d2)+"f";

				productTable_price.appendChild(doc.createTextNode(str));


				Element productTable_type = doc.createElement("type");
				productTable_row.appendChild(productTable_type);
				//productTable_type.appendChild(doc.createTextNode("STL"));
				productTable_type.appendChild(doc.createTextNode(planType[random.nextInt(planType.length)]));

				Element productTable_status = doc.createElement("status");
				productTable_row.appendChild(productTable_status);
				productTable_status.appendChild(doc.createTextNode(productStatus[random.nextInt(productStatus.length)]));


				Element productTable_seller = doc.createElement("seller");
				productTable_row.appendChild(productTable_seller);
				//userX where x is a random int in [1;10] range
				//productTable_seller.appendChild(doc.createTextNode("User"+random.nextInt(11)));
				//productTable_seller.appendChild(doc.createTextNode(String.valueOf(random.nextInt(1000))));
				productTable_seller.appendChild(doc.createTextNode(sellerlist[random.nextInt(sellerlist.length)]));

				Element productTable_category = doc.createElement("category");
				productTable_row.appendChild(productTable_category);
				productTable_category.appendChild(doc.createTextNode(categoryname[random.nextInt(productStatus.length)]));


				Element productTable_lastupdate = doc.createElement("lastupdate");
				productTable_row.appendChild(productTable_lastupdate);
				productTable_lastupdate.appendChild(doc.createTextNode(createRandomDate()));

				Element productTable_availabledate = doc.createElement("availabledate");
				productTable_row.appendChild(productTable_availabledate);
				productTable_availabledate.appendChild(doc.createTextNode(createRandomDate()));

				Element productTable_globalmark = doc.createElement("globalmark");
				productTable_row.appendChild(productTable_globalmark);
				productTable_globalmark.appendChild(doc.createTextNode(String.valueOf(random.nextInt(6))));


			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");



			//	char test[] = ", ".toCharArray();
			//System.out.println(Arrays.toString(test));
			//String str = createRandomString(5);

			//System.out.println(str);
		}
		catch (Exception e){
		}
	}

	public void FillDatabase(){

		try {


			File xmlFile = new File("file.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);


			NodeList nList = doc.getElementsByTagName("row");

			ArrayList<Product> productList = new ArrayList<Product>();

			UserDaoImpl userDao = new UserDaoImpl("STLAGORA_PU_SHELL");
			CategoryDaoImpl categoryDao = new CategoryDaoImpl("STLAGORA_PU_SHELL");
			Date date;

			for (int tmp = 0; tmp < nList.getLength(); tmp++) {

				Product currentProduct =  new Product();

				Node node = nList.item(tmp);

				Element element = (Element) node;
				//System.out.println("NOEUD : " + element.getElementsByTagName("name").item(0).getTextContent());


				currentProduct.setName(element.getElementsByTagName("name").item(0).getTextContent());



				SimpleDateFormat dateformatter = new SimpleDateFormat("d/M/y");

				System.out.println("DATE FORMATTER");

				
				try {
					//System.out.println(element.getAttribute("name"));
					date = new Date(dateformatter.parse(element.getElementsByTagName("availabledate").item(0).getTextContent()).getTime());
					currentProduct.setAvailableDate(date);
					System.out.println(date);
				} catch (Exception e) {
					//System.out.println(e);
					System.out.println(e.getMessage());
				}



				

				//currentProduct.setAvailableDate(date);
				System.out.println("IN BOUCLE");


				Category category = categoryDao.findByName(element.getElementsByTagName("category").item(0).getTextContent());
				//category.(element.getElementsByTagName("category").item(0).getTextContent());
				currentProduct.setCategory(category);

				currentProduct.setDescription(element.getElementsByTagName("description").item(0).getTextContent());

				float mark = Float.parseFloat(element.getElementsByTagName("globalmark").item(0).getTextContent());
				currentProduct.setGlobalMark(mark);

				currentProduct.setImages(element.getElementsByTagName("images").item(0).getTextContent());

				date = new Date(dateformatter.parse(element.getElementsByTagName("lastupdate").item(0).getTextContent()).getTime());

				currentProduct.setLastUpdate(date);

				currentProduct.setPlan(element.getElementsByTagName("plan").item(0).getTextContent());

				Float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
				currentProduct.setPrice(price);


				User user = userDao.findByLogin(element.getElementsByTagName("seller").item(0).getTextContent());
				
				//TODO si user est null problème
				currentProduct.setSeller(user);

				String st= element.getElementsByTagName("status").item(0).getTextContent();
				PRODUCT_STATUS var = (st.equals("AVailable")) ? PRODUCT_STATUS.AVAILABLE : PRODUCT_STATUS.NOTAVAILABLE;
				currentProduct.setStatus(var);

				st= element.getElementsByTagName("type").item(0).getTextContent();
				TYPE_FICHIER var2 = (st.equals("STL")) ? TYPE_FICHIER.STL : null;
				currentProduct.setType(var2);

				productList.add(currentProduct);
			}

			Product test = productList.get(0);
			System.out.println("Name "+ test.getName());
			System.out.println("Description "+test.getDescription());
			System.out.println("Image "+test.getImages());
			System.out.println("Plan "+ test.getPlan());
			System.out.println("lastupdate "+ test.getLastUpdate());
			System.out.println("type "+test.getType());
			System.out.println("status "+test.getStatus());
			System.out.println("seller "+test.getSeller());
			System.out.println("price "+test.getPrice());
			System.out.println("mark "+test.getGlobalMark());
			System.out.println("availabledate "+test.getAvailableDate());
			System.out.println("id "+test.getId());
			System.out.println("opinions "+test.getOpinions());
			
			
			//Iterator<Product> iterator = productList.iterator();
			ProductDaoImpl productDao = new ProductDaoImpl("STLAGORA_PU_SHELL");
			//while (iterator.hasNext()){
				for (Product product : productList) {
					
				

				try {
					productDao.createByEm(product);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

			}
				
				
		}catch(Exception e ){
			System.out.println("ERROR WHILE FILLING DB");
		}


	}

}

