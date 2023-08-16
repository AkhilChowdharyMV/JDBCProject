package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import services.Cart;
import services.CustomerLogin;
import services.CustomerRegistration;
import services.GetCustomerProducts;
import services.ViewProducts;

public class Main {
	

	static int customerId;
	static int totalPrice;

	public static void Login() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/shoppingcart";
    	String uname="root";
    	String pass="skhil";
		Connection con=DriverManager.getConnection(url, uname, pass);
		
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter Username : ");
        String username= scan.next();
        System.out.print("Enter Password : ");
        String password= scan.next();
        CustomerLogin customerLog=new CustomerLogin(con);
        customerLog.CheckCreds(username, password);
	}
	
	public static void Product() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/shoppingcart";
    	String uname="root";
    	String pass="skhil";
    	
		Connection con=DriverManager.getConnection(url, uname, pass);
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		System.out.println("Choose category of products : ");
        System.out.println("\n1.Electronics\n2.footware\n3.Clothing");
        int catChoice=scan.nextInt();
        ViewProducts view=new ViewProducts(con);
        switch(catChoice)
        {
             case 1 : view.AllProducts("Electronics");
                     break;
             case 2 : view.AllProducts("Footwear");
             		break;
             case 3 : view.AllProducts("Clothing");
             		break;
             default: 
            	 throw new IllegalArgumentException("Unexpected value: " + catChoice);
        }
	}
	
	public static void getTotalPrice(int total) throws SQLException
	{
		totalPrice=total;
		System.out.println("Total payable amount : "+totalPrice);
		System.out.println("Thank You!!");
		
		
	}
	
	public void getCustId(int custId)
	{
		customerId=custId;
	}
	public static void main(String[] args) 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/shoppingcart";
	    	String uname="root";
	    	String pass="skhil";
			Connection con=DriverManager.getConnection(url, uname, pass);
			
			@SuppressWarnings("resource")
			Scanner scan=new Scanner(System.in);
			System.out.println("1.New user? Register\n2.Login\n3.View Products\n4.Exit");
			System.out.println("Enter your choice : ");
			int choice=scan.nextInt();
			
			switch (choice)
			{
			    //for Registration
		        case 1:
					CustomerRegistration customerReg=new CustomerRegistration(con);
					customerReg.save();
					System.out.println("Enter your choice : ");
					System.out.println("\n1.Login\n2.Exit");
					int choice1=scan.nextInt();
					switch(choice1)
		            {
						case 1:
							Login();
							System.out.println();
				            System.out.println("1.View products\n2.Go to cart\n3.Exit");
				            System.out.print("Enter your choice : ");
				            int choice3=scan.nextInt();
				            switch (choice3)
				            {
							case 1: 
								    Product();
								    Cart cart=new Cart(con);
								    System.out.println("Enter your choice : ");
						        	System.out.println("1.Add item\n2.Exit");
						        	int choice7=scan.nextInt();
						        	switch(choice7)
						        	{
						        	case 1:  
						        		cart.AddToCart();
						        		System.out.println("Enter your choice : ");
									    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
									    int choice5=scan.nextInt();
									    while(choice5==1){
											    Product();
											    System.out.println("Enter your choice : ");
											    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
											    choice5=scan.nextInt();
											    
											    if(choice5!=1)
											    	break;
									    }
									    
									    if(choice5==3)
									    {
									    	System.out.println("Thank you!!");
									      break;
									    }
									    else if(choice5==2) {
									    	getTotalPrice(totalPrice);
									    }
						        	case 2: 
						        		System.out.println("Thank you!!");
						        		break;
						        	}
						        	
								break;
							case 2:
								GetCustomerProducts getProd=new GetCustomerProducts(con);
						    	getProd.MyProducts(customerId);
						    	
						    	System.out.println("Enter your choice");
						    	System.out.println("1.View products\n2.Checkout\n3.Exit");
						    	int choice6=scan.nextInt();
						    	switch(choice6)
						    	{
							       	case 1: Product();
							       		break;
							       	case 2: 
							       		getTotalPrice(totalPrice);
							       		break;
							       	case 3:
							       		System.out.println("Thank You!!");
							       		break; 
						    	}
						    	break;
							case 3: 
								System.out.println("Thank you!!");
								break;
				            }
					case 2:  
						System.out.println("Thank You!!");
					     break;
					default:
					    throw new IllegalArgumentException("Unexpected value: " + choice1);
					            
				                  	
		            }
		       
		            break;
		        //for Login    
		        case 2:
		            Login();
		            System.out.println();
		            System.out.println("1.View products\n2.Go to cart\n3.Exit");
		            System.out.print("Enter your choice : ");
		            int choice8=scan.nextInt();
		            switch (choice8) 
		            {
					case 1: 
						    Product();
						    Cart cart=new Cart(con);
						    System.out.println("Enter your choice : ");
				        	System.out.println("1.Add item\n2.Exit");
				        	int choice7=scan.nextInt();
				        	switch(choice7)
				        	{
				        	case 1:  
				        		cart.AddToCart();
				        		System.out.println("Enter your choice : ");
							    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
							    int choice5=scan.nextInt();
							    while(choice5==1){
									    Product();
									    System.out.println("Enter your choice : ");
									    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
									    choice5=scan.nextInt();
									    
									    if(choice5!=1)
									    	break;
							    }
							    
							    if(choice5==3)
							    {
							    	System.out.println("Thank you!!");
							      break;
							    }
							    else if(choice5==2) {
							    	getTotalPrice(totalPrice);
							    }
				        	case 2: 
				        		System.out.println("Thank you!!");
				        		break;
				        	}
				        	
						break;
					case 2:
						//customer products
						GetCustomerProducts getProd=new GetCustomerProducts(con);
				    	getProd.MyProducts(customerId);
				    	
				    	System.out.println("Enter your choice");
				    	System.out.println("1.View products\n2.Checkout\n3.Exit");
				    	int choice6=scan.nextInt();
				    	switch(choice6)
				    	{
					       	case 1: Product();
					       		break;
					       	case 2: 
					       		getTotalPrice(totalPrice);
					       		break;
					       	case 3:
					       		System.out.println("Thank You!!");
					       		break; 
				    	}
						break;
					case 3:
						System.out.println("Thank you!!");
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + choice8);
					}
		            break;
	            
		        //View all products
		        case 3 : 
		            Product();
		            System.out.println();
		            System.out.println();
		            System.out.println("Please login to add ");
		            System.out.println("\n1.Login\n2.Exit");
					int choice4=scan.nextInt();
					switch(choice4)
		            {
						case 1:
							Login();
							System.out.println();
				            System.out.println("1.View products\n2.Go to cart\n3.Exit");
				            System.out.print("Enter your choice : ");
				            int choice9=scan.nextInt();
				            switch (choice9) 
				            {
								case 1: 
									    Product();
									    Cart cart=new Cart(con);
									    System.out.println("Enter your choice : ");
							        	System.out.println("1.Add item\n2.Exit");
							        	int choice7=scan.nextInt();
							        	switch(choice7)
							        	{
								        	case 1:  
								        		cart.AddToCart();
								        		System.out.println("Enter your choice : ");
											    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
											    int choice5=scan.nextInt();
											    while(choice5==1){
													    Product();
													    System.out.println("Enter your choice : ");
													    System.out.println("1.continue shopping\n2.checkout\n3.Exit");
													    choice5=scan.nextInt();
													    
													    if(choice5!=1)
													    	break;
											    }
											    
											    if(choice5==3)
											    {
											    	System.out.println("Thank you!!");
											      break;
											    }
											    else if(choice5==2) {
											    	getTotalPrice(totalPrice);
											    }
								        	case 2: 
								        		System.out.println("Thank you!!");
								        		break;
							        	}
							        	break;
								case 2:
									GetCustomerProducts getProd=new GetCustomerProducts(con);
							    	getProd.MyProducts(customerId);
							    	
							    	System.out.println("Enter your choice");
							    	System.out.println("1.View products\n2.Checkout\n3.Exit");
							    	int choice6=scan.nextInt();
							    	switch(choice6)
							    	{
								       	case 1: Product();
								       		break;
								       	case 2: 
								       		getTotalPrice(totalPrice);
								       		break;
								       	case 3:
								       		System.out.println("Thank You!!");
								       		break; 
							    	}
							    	break;
								case 3: 
						        }
				           
						case 2:  
							System.out.println("Thank You!!");
				            break;
				        default:
				        	throw new IllegalArgumentException("Unexpected value: " + choice4);	
		            }
		            break;
		        case 4:
		            System.out.println("Thank You!!");
		            break;
		        default:
		        	throw new IllegalArgumentException("Unexpected value: " + choice);
			}      
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
