import java.io.*;
import java.util.*;
import java.sql.*;
class movie2
{
 public static void main(String args[])
 {
   int ch,movch,id1=1,no,rno,theach,price,total=0,re,c=0,usercount=0;	
   String name="",pass,con_pass,n,u_name,u_pass,date_time,seats="",mob,mob1,fst,cdate,ctime;
   boolean result;     
   try
   {  
     Class.forName("com.mysql.cj.jdbc.Driver");
	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movie","root","");
	 Statement stmt=con.createStatement();
	 System.out.println("WELCOME");
	 System.out.println("1.login");
	 System.out.println("2.Register");
	 System.out.println("Enter your option:");
	 Scanner s=new Scanner(System.in);
	 ch=s.nextInt();
	 System.out.println("choice:"+ch);
	 System.out.println();
	 if(ch==1)
     {
       System.out.println("enter the username:");
       name=s.nextLine();
       name=s.nextLine();
	   //System.out.println("name:"+name);
	   System.out.println("enter the password:");
	   pass=s.nextLine();
       //System.out.println("pass:"+pass);
	   ResultSet rs=stmt.executeQuery("SELECT * FROM USERDETAILS WHERE name=\""+name+"\"and password=\""+pass+"\";");   
	   //System.out.println("Data: "+rs.toString());
	   //System.out.println("RS: "+rs.next());   
       result=rs.next();	  
	   //System.out.println("result:"+result);
   	   if(result==true)
	   {
		   System.out.println("WARM WELCOME TO YOU!");
	   }
	   else
	   {
		   
		System.out.println("User doesnot exists!kindly do register");
		System.out.println("1.login");
		System.out.println("2.Register");
		System.out.println("Enter your option:");
		ch=s.nextInt();
		if(ch==1)
	    {
		  System.out.println("User doesnot exists!kindly do register");
		  System.out.println("1.login");
		  System.out.println("2.Register");
		  System.out.println("Enter your option:");
		  ch=s.nextInt();	
		}       			
	   }   
    }  
    if(ch==2)
    {
	 System.out.println("enter the username:");
     name=s.nextLine();
     name=s.nextLine();
     System.out.println("enter your mobile no:");
     mob=s.nextLine();
	 //mob=s.nextLine();
	 if(mob.length()<10||mob.length()>10)
	 {
       System.out.println("mobile no should contain 10 digits only");
       mob=s.nextLine();
      // mob=s.nextLine();	   
	 }
     System.out.println("enter your password:");
     pass=s.nextLine();	
	 //pass=s.nextLine();
	 if(pass.length()>8||pass.length()<8)
     {
       System.out.println("password should contain maximum 8 characters");	
	   pass=s.nextLine();	
     }	   
     System.out.println("confirm the password(re-enter):");
     con_pass=s.nextLine();	
    // System.out.println("name:"+n);
     String insert="INSERT INTO USERDETAILS VALUES(\""+name+"\",\""+mob+"\",\""+pass+"\",\""+con_pass+"\");";
     //System.out.println(insert);
     int ins=stmt.executeUpdate(insert);  
	 System.out.println("User registered successfully");
	}
	System.out.println();
	System.out.println("do you want to see the past booking history?");
	System.out.println("press 'y' to see the history or press 'n' to move to ticket booking page");
	char his_ch=s.next().charAt(0);
	if(his_ch=='y'||his_ch=='Y')
	{
       ResultSet rs=stmt.executeQuery("SELECT *FROM USERHIS_NEW WHERE NAME=\""+name+"\";");
       while(rs.next())
       {
         int his_id=rs.getInt("id");
		 String his_movie=rs.getString("movie");
		 String his_theatre=rs.getString("theatre");
		 String his_date=rs.getString("date_time");
		 int his_price=rs.getInt("price");
		 int his_admit=rs.getInt("admit");
		 int his_total=rs.getInt("total");
		 String his_seats=rs.getString("seats");
         //System.out.println(his_id+"-"+his_movie+"-"+his_theatre+"-"+his_date+"-"+his_price+"-"+his_admit+"-"+his_total+"-"+his_seats);
	     System.out.println();
		 System.out.println("id:"+his_id);
         System.out.println("movie:"+his_movie);
         System.out.println("theatre:"+his_theatre);
         System.out.println("booked date&time:"+his_date);
         System.out.println("price:"+his_price);
         System.out.println("admit:"+his_admit);
         System.out.println("total:"+his_total);
         System.out.println("seats:"+his_seats);
	   }
    }
    System.out.println();
    ResultSet rs5=stmt.executeQuery("SELECT ID FROM USERHIS_NEW WHERE NAME=\""+name+"\";");
    while(rs5.next())
    {
      int his_id=rs5.getInt("id");
      usercount++;
    } 	  
	//System.out.println("usercount:"+usercount);
	if(usercount>=5)
	{
      System.out.println("THANK YOU FOR BEING WITH US...YOU ARE OUR REGULAR CUSTOMER");
	  //System.out.println("YOU WILL GET 10% OFFER IN YOUR BOOKING");
    }	  
	System.out.println();
	System.out.println("Welcome");
	System.out.println("MOVIES LIST:");
	System.out.println();
	ResultSet rs=stmt.executeQuery("SELECT *FROM MOVIES;");
	while(rs.next())
	{
      int id=rs.getInt("id"); 		
	  String mov_name=rs.getString("movie_name");
      System.out.println(id+"."+mov_name);
    }	  
	System.out.println("enter your choice:");
	movch=s.nextInt();
	System.out.println();
	System.out.println("THEATRES LIST:");
	System.out.println();
	ResultSet rs1=stmt.executeQuery("SELECT THEATRE,T_ID FROM MOVIE_THEATRE WHERE ID=\""+movch+"\";");
	while(rs1.next())
	{
	 String theatre_name=rs1.getString("theatre"); 
	 int thea_id=rs1.getInt("t_id");
	 System.out.println(thea_id+"."+theatre_name);
	}
	System.out.println("enter your choice:");
	theach=s.nextInt();
	System.out.println();
	System.out.println("Enter the convinient date for booking:");
    cdate=s.nextLine();
	cdate=s.nextLine();
	System.out.println("Enter the convinient time:");
	ctime=s.nextLine();
	String sea[]=new String[25];
	System.out.println();
	System.out.println("enter the no of seats:");
    no=s.nextInt();
	if(movch==1&&theach==1)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM BAHUBALI_OSCAR;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE BAHUBALI_OSCAR SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE BAHUBALI_OSCAR SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE BAHUBALI_OSCAR SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE BAHUBALI_OSCAR SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE BAHUBALI_OSCAR SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==1&&theach==2)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM BAHUBALI_KAILASH;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE BAHUBALI_KAILASH SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE BAHUBALI_KAILASH SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE BAHUBALI_KAILASH SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE BAHUBALI_KAILASH SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE BAHUBALI_KAILASH SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==2&&theach==1)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM PS_OSCAR ;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	  // System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE PS_OSCAR SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE PS_OSCAR SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE PS_OSCAR SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE PS_OSCAR SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE PS_OSCAR SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==2&&theach==2)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM PS_KAILASH;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE PS_KAILASH SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE PS_KAILASH SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE PS_KAILASH SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE PS_KAILASH SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE PS_KAILASH SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==3&&theach==3)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM SIVAJI_GEETHA;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE SIVAJI_GEETHA SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE SIVAJI_GEETHA SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE SIVAJI_GEETHA SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE SIVAJI_GEETHA SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE SIVAJI_GEETHA SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==4&&theach==4)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM MUDHALVAN_AR;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE MUDHALVAN_AR SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE MUDHALVAN_AR SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE MUDHALVAN_AR SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE MUDHALVAN_AR SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE MUDHALVAN_AR SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
	if(movch==5&&theach==4)	
    {
       System.out.println("SEAT LAYOUT");
	   System.out.println("************");
       ResultSet rs6=stmt.executeQuery("SELECT *FROM ENTHIRAN_AR;");
	   while(rs6.next())
	   {	   
       String col1=rs6.getString("col1");
	   String col2=rs6.getString("col2");
	   String col3=rs6.getString("col3");
	   String col4=rs6.getString("col4");
	   String col5=rs6.getString("col5");
	   System.out.println(col1+"    "+col2+"    "+col3+"    "+col4+"    "+col5);
	   } 
	   System.out.println("**FU** means *FILLED* or *BOOKED* seats please choose other seats");
	   System.out.println();
	   System.out.println("choose your seats:");		
	   for(int i=0;i<=no;i++)
	   {
		sea[i]=s.nextLine();
       }	
	   String sep=",";
	   for(int i=0;i<=no;i++)
	   {
         seats+=String.join(sep,sea[i]); 
	   }  
	   //System.out.println(seats);
	   for(int i=0;i<=no;i++)
	   {
         String update="UPDATE ENTHIRAN_AR SET COL1='FU' WHERE COL1=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update); 
         String update2="UPDATE ENTHIRAN_AR SET COL2='FU' WHERE COL2=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update2);
         String update3="UPDATE ENTHIRAN_AR SET COL3='FU' WHERE COL3=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update3);
         String update4="UPDATE ENTHIRAN_AR SET COL4='FU' WHERE COL4=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update4);
         String update5="UPDATE ENTHIRAN_AR SET COL5='FU' WHERE COL5=\""+sea[i]+"\";"; 
         stmt.executeUpdate(update5);  		 
       }		 
	}	
   System.out.println();
   System.out.println("TICKET DETAILS");
   System.out.println("***************");
   ResultSet rs2=stmt.executeQuery("SELECT MOVIE_NAME FROM MOVIES WHERE ID=\""+movch+"\";");
   while(rs2.next())
   {
	 String movie=rs2.getString("movie_name");
     System.out.println("MOVIE NAME:"+movie);
   }
   ResultSet rs4=stmt.executeQuery("SELECT THEATRE FROM MOVIE_THEATRE WHERE T_ID=\""+theach+"\";");
   while(rs4.next())
   {
    String name_th=rs4.getString("theatre"); 	   
    System.out.println("THEATRE NAME:"+name_th);
	break;
   } 	
   Calendar cal=Calendar.getInstance();
   //Date date_time=cal.getTime();
   date_time=cal.getTime().toString();
   System.out.println("BOOKED DATE AND TIME:"+date_time);
   price=150;
   System.out.println("DATE AND TIME FOR SHOW:"+cdate+" "+ctime);
   System.out.println("PRICE:"+price);
   System.out.println("ADMIT:"+no);  
   System.out.print("SEAT NO:");
   System.out.print(seats);	   
   total=no*150;
   System.out.println();
   System.out.println("TOTAL PRICE:"+total);
   System.out.println();
  //System.out.println("Selected Seats are: "+seats); 
   Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/movie","root","");
   Statement stmt1=con.createStatement();
   ResultSet rs3=stmt.executeQuery("SELECT MOVIE_NAME,THEATRE FROM MOVIE_THEATRE WHERE (ID=\""+movch+"\" AND T_ID=\""+theach+"\");");
   while(rs3.next())
   {
	 String movie_name=rs3.getString("movie_name");
    // System.out.println("MOVIE NAME1:"+movie_name);
	 String thea_name=rs3.getString("theatre"); 	   
     //System.out.println("THEATRE NAME1:"+thea_name);
	 String history="INSERT INTO USERHIS_NEW(NAME,MOVIE,THEATRE,DATE_TIME,PRICE,ADMIT,TOTAL,SEATS)VALUES(\""+name+"\",\""+movie_name+"\",\""+thea_name+"\",\""+date_time+"\","+price+","+no+","+total+",\""+seats+"\");";
    // System.out.println(history);
     stmt1.executeUpdate(history);
	 break;
   }
   
   System.out.println("THANKS FOR BOOKING TICKETS");
 }  
   catch(Exception ex)
   {
     System.out.println("database not connected");
     System.out.println(ex.toString());
   }
 	  
 }
} 
	 
	 
	 