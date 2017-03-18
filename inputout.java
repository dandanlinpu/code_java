import java.io.*;
public class inputout{
    public static void main(String[] args){
        try{
        InputStream in = new BufferedInputStream(
                new FileInputStream("a.txt")
            );
        }catch(IoException e){

        }

    }
}