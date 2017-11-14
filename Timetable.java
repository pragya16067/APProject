package application;

import java.util.regex.Pattern;

public class Timetable {
	private String Course;
	private String M;
	private String T;
	private String W;
	private String Th;
	private String F;
	private String Tu;
	private String La;
	
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
	public String getM()
	{
		if(!M.equals(""))
		{String m[] = M.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
		return M;
	}
	public String getT()
	{
		if(!T.equals(""))
		{String m[] = T.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return T;
	}
	public String getW()
	{
		if(!W.equals(""))
		{String m[] = W.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return W;
	}
	public String getTh()
	{
		if(!Th.equals(""))
		{String m[] = Th.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return Th;
	}
	public String getF()
	{
		if(!F.equals(""))
		{String m[] = F.split(Pattern.quote("$"));
		return m[0]+"-"+m[1];
		}
		else
			return F;
	}
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