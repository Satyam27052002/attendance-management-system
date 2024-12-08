package sms;
import java.sql.*;
import java.util.Scanner;

public class Attendance 
{
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.println("\n"+"Select Options:");
			System.out.println("1) Add Student"+"\t \t"+"2) Display All Students"+"\t"+"3) Search by ID"+"\t \t"+"4) Search by Name");
			System.out.println("5) Search by Keyword"+"\t"+"6) Update Name by ID"+"\t"+"7) Delete Student by ID"+"\t"+"8) Update Attendance by ID");
			System.out.println("9) Sort by Name Asc"+"\t"+"10) Sort by Name Desc"+"\t"+"11) Exit");
			int key=s.nextInt();
			switch(key)
			{
			case 1:addStudent();
				break;
			case 2:displayStudents();
				break;
			case 3:searchByID();
				break;
			case 4:searchByName();
				break;
			case 5:searchByKeyword();
				break;
			case 6:updateNameByID();
				break;
			case 7:deleteStudentByID();
				break;
			case 8:updateAttendanceByID();
				break;
			case 9:sortNameAsc();
				break;
			case 10:sortNameDesc();
				break;
			case 11:System.exit(0);
				break;
			default:System.out.println("Invalid input");
				break;
			}
		}
	}

	private static void sortNameDesc() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from student order by name desc");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void sortNameAsc() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from student order by name asc");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void searchByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			System.out.println("Enter ID");
			ResultSet rs=st.executeQuery("select * from student where id ='"+s.nextInt()+"'");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void searchByKeyword() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			System.out.println("Enter keyword");
			ResultSet rs=st.executeQuery("select * from student where name like '"+s.next()+"%'");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void updateNameByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			PreparedStatement st=con.prepareStatement("update student set name=? where  id= ?");
			System.out.println("Enter new name");
			st.setString(1, s.next());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayStudents();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void deleteStudentByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			PreparedStatement st=con.prepareStatement("delete from student where id=?");
			System.out.println("Enter id");
			st.setInt(1, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");
			displayStudents();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void updateAttendanceByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			PreparedStatement st=con.prepareStatement("update student set cls_attended=? where  id= ?");
			System.out.println("Enter classes attended");
			st.setInt(1, s.nextInt());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayStudents();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void searchByName() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			System.out.println("Enter Name");
			String name=s.next();
			ResultSet rs=st.executeQuery("select * from student where name='"+name+"'");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void displayStudents() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from student");
			System.out.printf("%-5s | %-15s | %-10s | %-20s | %-15s | %-10s%n", "ID", "Name", "Age", "Email", "Phone","Class_Attended");
		    System.out.println("----------------------------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-10s | %-20s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),rs.getInt(6));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void addStudent() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance","root","");
			PreparedStatement st=con.prepareStatement("insert into student values(?,?,?,?,?,?)");
			System.out.print("Enter id \t");
			st.setInt(1, s.nextInt());
			System.out.print("Enter name \t");
			st.setString(2, s.next());
			System.out.print("Enter age \t");
			st.setInt(3, s.nextInt());
			System.out.print("Enter Email \t");
			st.setString(4, s.next());
			System.out.print("Enter Phone \t");
			st.setLong(5, s.nextLong());
			System.out.print("Enter cls_attended \t");
			st.setInt(6, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayStudents();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
