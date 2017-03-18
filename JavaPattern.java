import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaPattern{
    public static void main(String []  args){
        String str="abc123abc543";
        Pattern p = Pattern.compile("c([0-9]+)");
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group());
        }
    }
}