import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
public class Net{
    public static void main(String[] args){
        String url="https://www.zhihu.com";
        simpleSendGet(url);
    }
    public static String simpleSendGet(String url){
        String result="";
        try{
            URL relUrl = new URL(url);
            URLConnection urlCon= relUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line=null;
            while((line=br.readLine())!=null){
                result+=line;
            }
        }catch( IOException e ){
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
    public static String sendGet(String url){
        String result="";
        try {
            URL relUrl = new URL(url);
            URLConnection urlCon= relUrl.openConnection();
            HttpURLConnection httpUrlCon=(HttpURLConnection)urlCon;
            httpUrlCon.setDoInput(true);
            httpUrlCon.setRequestMethod("GET");
            httpUrlCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0");
            httpUrlCon.connect(); //  发起tcp三次握手
            BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlCon.getInputStream()));//发起http链接请求在这里
            String line=null;
            while((line=br.readLine())!=null){
                result+=line;
                System.out.println(line);
            } 
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }finally{
            //close resource
        }
        return result;
    }
}