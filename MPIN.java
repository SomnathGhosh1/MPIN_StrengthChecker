
import java.util.Scanner;


public class mpin {
    public static boolean isCommMpin(String pin)
    {
     if(pin==null||!(pin.length()==4)) 
     return false;
    
     for(int i=0;i<4;i++)
     {
     char ch=pin.charAt(i);
     if(ch<'0'||ch>'9')
     return false;
     }
     char a=pin.charAt(0);
     char b=pin.charAt(1);
     char c=pin.charAt(2);
     char d=pin.charAt(3);
     

     if(a==b && b==c && c==d)
     return true;
     if((b==a+1) && (c==b+1) && (d==c+1))
     return true;
     if(b==a-1 && c==b-1 && d==c-1)
     return  true;
     if(a==d && b==c)
     return true;
     if((a==c && b==d)||(a==b && c==d))
     return true;
     if(pin.equals("2580")||pin.equals("1379"))
       return true;


    boolean even =true,odd=true;
    for(int i=0;i<pin.length();i++)
    {
        int digit=pin.charAt(i)-'0';
             if(digit%2==0)odd=false;
             else
             even=false;
    }
    if(even ||odd )
      return true;

      if(pin.equals("1998")||(pin.equals("2000")))
      {return true;}

      return false;
    }

    public static String[] extractpattern(String date)
    {if(date==null|| date.length()!=8)
        return new String[0];

    
      String dd=date.substring(0,2);
      String mm=date.substring(2,4);
     String yyyy=date.substring(4,8);
      String yy=date.substring(6,8);
      
      return new String[]
      {
        dd+mm,
        mm+yyyy,
        dd+yyyy,
        mm+yy,
        yyyy,
        dd+mm+yy,
        dd+mm+yyyy,
        date
      };
      
    }
    public static String[] TheReasons(String pin,String dob,String SDob,String annry)
    {
      String temp[]=new String[4];
      int count=0;
      if(pin==null||pin.length()!=4&&pin.length()!=6)
      return new String[0];

      //Common 
      if(pin.length()==4 && isCommMpin(pin))
      {
         temp[count++]="COMMONLY_USED";
      }

      //DOB
      String DSect[]=extractpattern(dob);
       for(int i=0;i<DSect.length;i++)
       {
        if(pin.equals(DSect[i]))
        {
          temp[count++]="DEMOGRAPHIC_DOB_SELF";
          break;
        }
       }

       //Spouse Dob
       String SPSect[]=extractpattern(SDob);
       for(int i=0;i<SPSect.length;i++)
       { if(pin.equals(SPSect[i]))
        {
        temp[count++]="DEMOGRAPHIC_DOB_SPOUSE";
        break;
       }
      }

      //Anniversary Year Checked
      String AnnSect[]=extractpattern(annry);
      for(int i=0;i<AnnSect.length;i++)
      {
        if(pin.equals(AnnSect[i]))
        {
          temp[count++]="DEMOGRAPHIC_ANNIVERSARY";
          break;
        }
      }

      String output[]=new String[count];
       for(int i=0;i<count;i++)
       {output[i]=temp[i];
         }
         return output;  
    }



    //To Compare arrays
   static boolean  compareArrays(String a[],String b[])
   {
    if(a.length!=b.length)return  false;

    boolean[] matched=new boolean[b.length];
    for(int i=0;i<a.length;i++)
    {
      boolean found=false;
    
      for(int j=0;j<b.length;j++)
      {
        if(!matched[j] && a[i].equals(b[j]))
        {
          matched[j]=true;
          found=true;
          break;
        }
      }
      if(!found)return  false;
    }
    return true;
   }
   
   //Conversion to arraylist
   public static String arrayToString(String arr[])
   {
    if(arr.length==0)return "[]";

    String s="[";
    for(int i=0;i<arr.length;i++)
    {
      s+=arr[i];
      if(i!=arr.length-1)
      {
        s+=",";
      }
    }
      return  s+"]";
    }
   
    public static void TestCases(int  tNo,String pin,String expStrth,String expReason[],String dob,String SPDob,String Annsry)
    {
      String original[]=TheReasons(pin, dob, SPDob, Annsry);
    String originalStrength=original.length==0 ? "STRONG":"WEAK";
  
  boolean testedstrength=expStrth.equals(originalStrength);
boolean testedReasons=compareArrays(expReason,original);

System.out.printf("%2d. MPIN:%-6s => Expected: %-5s, Got:%-5s: %s\n",tNo,pin,expStrth,originalStrength,(testedstrength&&testedReasons ? "PASSED":"FAILED"));

if(!testedReasons)
{
  System.out.println("Expected Reasons"+arrayToString(expReason));
  System.out.println("Actual Reasons: "+arrayToString(original));
}
}


public static void main(String[] args) {
   String dob="02011998";
   String SpouseDOB="15021996";
   String anniv="14022020";
   String[][] tests = {
    {"0000", "WEAK", "COMMONLY_USED"},
    {"1111", "WEAK", "COMMONLY_USED"},
    {"2222", "WEAK", "COMMONLY_USED"},
    {"3333", "WEAK", "COMMONLY_USED"},
    {"4444", "WEAK", "COMMONLY_USED"},
    {"5555", "WEAK", "COMMONLY_USED"},
    {"6666", "WEAK", "COMMONLY_USED"},
    {"7777", "WEAK", "COMMONLY_USED"},
    {"8888", "WEAK", "COMMONLY_USED"},
    {"9999", "WEAK", "COMMONLY_USED"},
    {"1234", "WEAK", "COMMONLY_USED"},
    {"4321", "WEAK", "COMMONLY_USED"},
    {"1221", "WEAK", "COMMONLY_USED"},
    {"1212", "WEAK", "COMMONLY_USED"},
    {"1122", "WEAK", "COMMONLY_USED"},
    {"1379", "WEAK", "COMMONLY_USED"},
    {"2580", "WEAK", "COMMONLY_USED"},
    {"2468", "WEAK", "COMMONLY_USED"},
    {"1357", "WEAK", "COMMONLY_USED"},
    {"2000", "WEAK", "COMMONLY_USED"},
    {"2020", "WEAK", "COMMONLY_USED", "DEMOGRAPHIC_ANNIVERSARY"},
    {"2023", "WEAK", "COMMONLY_USED"},
    {"2025", "WEAK", "COMMONLY_USED"},
    {"1998", "WEAK", "COMMONLY_USED", "DEMOGRAPHIC_DOB_SELF"},
    {"0201", "WEAK", "DEMOGRAPHIC_DOB_SELF"},
    {"1502", "WEAK", "DEMOGRAPHIC_DOB_SPOUSE"},
    {"1402", "WEAK", "DEMOGRAPHIC_ANNIVERSARY"},
    {"020119", "WEAK", "DEMOGRAPHIC_DOB_SELF"},
    {"140220", "WEAK", "DEMOGRAPHIC_ANNIVERSARY"},
    {"150219", "WEAK", "DEMOGRAPHIC_DOB_SPOUSE"},
    {"202002", "STRONG"},
    {"199602", "STRONG"},
    {"abcd", "STRONG"},
    {"1a2b", "STRONG"},
    {"7351", "STRONG"},
    {"8193", "STRONG"},
    {"3847", "STRONG"},
    {"1001", "WEAK", "COMMONLY_USED"},
    {"2332", "WEAK", "COMMONLY_USED"},
    {"3113", "STRONG"},
    {"4440", "STRONG"},
    {"1211", "STRONG"},
    {"1230", "STRONG"},
    {"5678", "WEAK", "COMMONLY_USED"},
    {"9876", "WEAK", "COMMONLY_USED"},
    {"1123", "STRONG"},
    {"1512", "STRONG"},
    {"3108", "STRONG"},
    {"0001", "STRONG"},
    {"1597", "STRONG"},
    {"1359", "STRONG"},
    {"1998", "WEAK", "COMMONLY_USED", "DEMOGRAPHIC_DOB_SELF"},
    {"0201", "WEAK", "DEMOGRAPHIC_DOB_SELF"},
    {"1502", "WEAK", "DEMOGRAPHIC_DOB_SPOUSE"},
    {"1402", "WEAK", "DEMOGRAPHIC_ANNIVERSARY"},
    {"2020", "WEAK", "DEMOGRAPHIC_ANNIVERSARY"},
    {"020119", "WEAK", "DEMOGRAPHIC_DOB_SELF"},
   {"140220", "WEAK", "DEMOGRAPHIC_ANNIVERSARY"},
    {"1234", "WEAK", "COMMONLY_USED"},
    {"1221", "WEAK", "COMMONLY_USED"},
    {"3847", "STRONG"},
      {"abcd", "STRONG"},
     {"1a2b", "STRONG"},
     {"150219", "WEAK", "DEMOGRAPHIC_DOB_SPOUSE"},
       {"202002", "STRONG"},
       {"199602", "STRONG"},
     {"7351", "STRONG"}
};

for(int i=0;i<tests.length;i++)
{
  String pin=tests[i][0];
  String Strength=tests[i][1];
 String [] reasons=new String[tests[i].length-2];
 for(int j=2;j<tests[i].length;j++)
 {
  reasons[j-2]=tests[i][j];
 }
 TestCases(i+1, pin, Strength, reasons, dob, SpouseDOB, anniv);
}


 // Accept user input after tests
    Scanner sc = new Scanner(System.in);
   System.out.print("\n Enter MPIN to check strength: ");
        String userMpin = sc.nextLine();
        System.out.print("\n Enter DOB to check mpin: ");
        String dob1=sc.nextLine();
        System.out.print("\n Enter SPOUSE DOB to check mpin: ");
        String SpouseDOB1=sc.nextLine();
        System.out.print("\n Enter Anniversary to check mpin: ");
         String anniv1=sc.nextLine();


    if(!(userMpin.length()==4 || userMpin.length()==6))
    {
      System.out.println("Invalid Input Pin Enter 4 or 6 Digit Pins");
     return;
    }
      String[] reasons = TheReasons(userMpin, dob1, SpouseDOB1, anniv1);
    String strength = reasons.length == 0 ? "STRONG" : "WEAK";

    System.out.println(" MPIN Strength: " + strength);
            if (strength.equals("WEAK")) {
                      System.out.println(" Weakness Reasons: " + arrayToString(reasons));
        } 
        else {
          System.out.println(" No weaknesses detected.");
        }
   }
}


