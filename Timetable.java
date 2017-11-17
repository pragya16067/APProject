package application;

import java.util.regex.Pattern;

/**
 * @author Pragya
 *
 */
public class Timetable {
	private String Course;
	private String M; //Monday Timetable
	private String T; //Tuesday
	private String W; //Wednesday
	private String Th; //Thursday
	private String F; //Friday
	private String Tu; //Tutorials
	private String La; //Labs
	
	/**
	 * Constructor
	 * 
	 * @param c : Course Code
	 * @param j : Monday Time slot
	 * @param d : Tuesday Time slot
	 * @param e : Wed Time slot
	 * @param f : Thursday Time slot
	 * @param g : Friday Time slot
	 * @param h : Tutorials Time slot
	 * @param i : Labs Time slot
	 */
	public Timetable(String c,String j, String d, String e, String f, String g, String h , String i) {
		Course=c;
		M=j;
		T=d;
		W=e;
		Th=f;
		F=g;
		Tu=h;
		La=i;
	}
	
	public String getCourse() {
		return Course;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course on Monday, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getM()
	{
		if(!M.equals(""))
		{String m[] = M.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
		return M;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course on Tuesday, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getT()
	{
		if(!T.equals(""))
		{String m[] = T.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return T;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course on Wednesday, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getW()
	{
		if(!W.equals(""))
		{String m[] = W.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return W;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course on Thursday, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getTh()
	{
		if(!Th.equals(""))
		{String m[] = Th.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return Th;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course on Friday, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getF()
	{
		if(!F.equals(""))
		{String m[] = F.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return F;
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course's tutorials, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getTu()
	{
		if(!Tu.equals(""))
		{String m[] = Tu.split(Pattern.quote("$"));
		String tul = m[0];
		for(int i=1;i<m.length;i++)
		{
			tul+=("-"+m[i]);
		}
		return tul;}
		else
			return Tu;
		
	}
	
	/**
	 * Getter function
	 * Get the timings of class for this course's labs, if any
	 * @return String of the format "Start time - End time"
	 */
	public String getLa()
	{
		if(!La.equals(""))
		{String m[] = La.split(Pattern.quote("$"));
		String tul = m[0];
		for(int i=1;i<m.length;i++)
		{
			tul+=("-"+m[i]);
		}
		return tul;
		}
		else
		return La;
	}
}