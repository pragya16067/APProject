package application;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream 
     * @throws FileNotFoundException */
    static void init(String input) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
    static long nextLong() throws IOException {
	      return Long.parseLong( next() );
	  }
}
public class Main1
{
	public static void main(String args[]) throws IOException
	{
		Reader.init("./src/project-1csvfile_14904.csv");
		try
		{String line = Reader.reader.readLine();
		{ boolean check = true;
			while(check)
			{
				line = Reader.reader.readLine();
				System.out.println(line);
				if(line!=null)
				{String Array[] = line.split(",");
				try
				{
					Class.forName("java.sql.DriverManager");
			        Connection con=(Connection) DriverManager.getConnection(
			                "jdbc:mysql://localhost:3306/project","root","30july1998");
			        Statement stmt=(Statement) con.createStatement();
			        
			        String q = "Insert into Courses values ('"+Array[2]+"','"+Array[1]+"','"+Array[5]+"','"+Integer.parseInt(Array[4])+"','"+Array[0]+"','"+Array[3]+"','"+""+"');";
			        System.out.println(q);
			        stmt.executeUpdate(q);
			        int i =0;
			        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
			        for(i=0;i<7;i++)
			        {
			        	if(!Array[i+6].equals(""))
			        		{System.out.println("aa "+ Array[i+6]);
			        		if(i<5)
			        		{String RT[] = Array[i+6].split(Pattern.quote("$"));
			        		System.out.println(RT.length+" kk "+RT[0]);
			        		String room = RT[1];
			        		String stime = RT[0].split("-")[0];
			        		String etime = RT[0].split("-")[1];
			        		q ="Insert into Bookings values ('"+room+"','"+Array[2]+"','"+days[i]+"','"+stime+"','"+etime+"', 'Student');";
			        		stmt.executeUpdate(q);
			        		}
			        		else
			        		{
			        			String All[] = Array[i+6].split(" :");
			        			for(int j=0;j<All.length;j++)
			        			{
			        				String RT[]  = All[j].split(Pattern.quote("$"));
			        				String Day = RT[0];
			        				String stime = RT[1].split("-")[0];
					        		String etime = RT[1].split("-")[1];
					        		String room[] = RT[2].split(";");
					        		for(int k =0;k<room.length;k++)
					        		{String q1 ="Insert into Bookings values ('"+room[k]+"','"+Array[2]+"','"+Day+"','"+stime+"','"+etime+"', 'Student');";
					        		stmt.executeUpdate(q1);
					        		}
			        			}
			        			
			        		}
			        		
			        		}
			        }
				
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
				else
				{
					check = false;
				}
			}
		}
		
		}
		catch(EOFException e)
		{
			
		}
		
	}
}
