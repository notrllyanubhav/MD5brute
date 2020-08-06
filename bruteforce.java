import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.*;
import java.io.*;
import java.time.*;

// Java program to calculate MD5 hash value 
public class bruteforce
{
    static String hashes[]=new String[100]; static long l=0L; static long min=(long)Math.pow(26,6);
    static int count=0;
    

    public static String getMd5(String input) 
	{ 
        try
        {
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
            while (hashtext.length() < 32)
            { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e)
        { 
			throw new RuntimeException(e); 
		} 
    }

    static void printAllKLength(char[] set, int k) 
    { 
	    int n = set.length; 
	    printAllKLengthRec(set, "", n, k); 
    } 


    static void printAllKLengthRec(char[] set, String prefix, int n, int k) 
    { 
	
	    if (k == 0) 
	    { 
		    attac(prefix); 
		    return; 
	    } 

	
	    for (int i = 0; i < n; ++i) 
	    { 
		    String newPrefix = prefix + set[i]; 
		
		    printAllKLengthRec(set, newPrefix,n, k - 1); 
	    } 
    } 

    static void attac(String S)
    {
        String S1="";String S2="";
        S2=S;
        S1=getMd5(S2);
        List hs=Arrays.asList(hashes);
        boolean present=hs.contains(S1);
        if(present)
        {
            System.out.println("Encrypted Hash found:"+S1+"  "+"Password is: "+S2);
            count++;
        }
        l++;
    }
    
    public static void main(String args[])throws IOException,NoSuchAlgorithmException
    {
        Instant start= Instant.now();
        char[] set1 = {'a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        File f2=new File("C:\\Users\\anubh\\Desktop\\Stuff\\hashes.txt");
        BufferedReader bg=new BufferedReader(new FileReader(f2));
        String S;int c=0;
        while((S=bg.readLine())!=null)
        {
            hashes[c++]=S;
        }
        printAllKLength(set1, 6);
        Instant end= Instant.now();
        Duration timetaken= Duration.between(start, end);
        System.out.println("Number of hashes generated ="+l);
        System.out.println("Number of passwords recovered= "+count);
        System.out.println("Time taken in minutes is: "+timetaken.toMinutes());
    }
}
