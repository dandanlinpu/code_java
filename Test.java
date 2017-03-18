import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Test{
    public static void main(String[] args){
        String s="name=\"_xsrf\" value=\"123abc\" />";
        Pattern p=Pattern.compile("name=\"_xsrf\".*?value=\"(.*?)\" />");
        Matcher m=p.matcher(s);
        while(m.find()){
            String s1=m.group(1);
            System.out.println(s1);
        }
    }
}