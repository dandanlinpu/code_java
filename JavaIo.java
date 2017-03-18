import java.io.DataInputStream;
import java.util.Locale.FilteringMode;
import java.io.*;
public class JavaIo{
    
    public static void main(String args[]){
        try{
            //读取字符-char接收
            BufferedReader r = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("1.txt"),"utf-8"
                )
            );  
            char []buf=new char[4];
            int length=r.read(buf);
            System.out.println(length);
            for(int i=0;i<length;i++){
                System.out.println("char--"+buf[i]);
            }
            String line;//读取整行
            while( (line=r.readLine())!=null){
                System.out.println("line--"+line);
            };
            //写入字符-char数组或者string
            BufferedWriter w = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("2.txt"),"utf-8"
                    )
                    );
            String str="中国人123abc";//javac -encoding UTF-8 JavaIo.java
            w.write(str);
            w.flush(); 
            //读取2进制-byte接收
            BufferedInputStream r1=new BufferedInputStream(
                new FileInputStream("1.txt"));
            byte []b=new byte[10];
            int length1=r1.read(b,0,b.length);
            for(int i=0;i<length1;i++){
                System.out.println("byte--"+b[i]);
            }
            String s=new String(b,0,length1,"utf-8");//从字节构造string
            System.out.println(s);
            //写2进制字节-byte数组
            BufferedOutputStream w1=new BufferedOutputStream(
                new FileOutputStream("3.txt")
                );
            byte[] b1=new byte[10];
            for(int i=0;i<b1.length;i++) b1[i]=(byte)i;
            w1.write(b1,0,b1.length);
            w1.flush();
            //使用PrintWriter可以方便写入各类型的数据，使用DataInputStream可以方便读入各类数据
            PrintWriter p=new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("4.txt")
                    ))
            );
            p.print(1234);
            p.print("abc");
            p.print(0xf2cd);
            p.print(123.4);
            p.flush();
            DataInputStream d=new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream("4.txt")
                )
            );
            int j=d.readInt();
            System.out.println("int"+j);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}