import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.net.HttpURLConnection;
public class  Main{
    public static void main(String[] args){
        String url="https://www.zhihu.com";
        String result=SendGet(url);
        requestChaptcha();
        System.out.println(result);
    }

   public static String SendGet(String url){
        String result="";
        try{
            URL relUrl = new URL(url);
            URLConnection urlCon = relUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line=null;
            while((line=br.readLine())!=null){
                result+=line;
                result+="\n";
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String SendPost(String url){
        String result = SendGet(url);
        String _xsrf=null;
        String password="zhihu141592";
        String email="273041937@qq.com";
        Pattern p = Pattern.compile("name=\"_xsrf\".*?value=\"(.*?)\"/>");
        Matcher m = p.matcher(result);
        if(m.find()){
            _xsrf=m.group(1);
        }
        System.out.println(_xsrf);

        try {
            URL relUrl = new URL(url);
            HttpURLConnection httpUrlCon=(HttpURLConnection)relUrl.openConnection();
            httpUrlCon.setDoInput(true);
            httpUrlCon.setDoOutput(true);
            httpUrlCon.setRequestMethod("POST");
            //post不能使用缓存
            httpUrlCon.setUseCaches(false);
            //请求报头
            httpUrlCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0");
            httpUrlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpUrlCon.connect();
            //构造表单
            String captcha="1234";
            String param="_xsrf="+_xsrf
                        +"&passwrod="+password
                        +"&captcha="+captcha
                        +"&email="+email;
            OutputStreamWriter os = new OutputStreamWriter(httpUrlCon.getOutputStream());
            os.write(param);
            os.flush();
            os.close();
            //调用getInputStream将表单数据发送到服务器
            BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlCon.getInputStream()));
            String line=null;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void requestChaptcha(){
        try{
            String addr="https://www.zhihu.com/captcha.gif?";
            URL url = new URL(addr);
            URLConnection urlCon= url.openConnection();
            InputStream is = urlCon.getInputStream();        
            byte[] getData = readInputStream(is);     //获得图片的二进制数据   
            File imageFile = new File("captcha.gif");   
            FileOutputStream fos = new FileOutputStream(imageFile);    
            fos.write(getData); 
            fos.close(); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static  byte[] readInputStream(InputStream in){ 
        byte[] buffer = new byte[1024]; 
        int len = 0; 
        ByteArrayOutputStream bos=null;
        try{ 
            bos = new ByteArrayOutputStream(); 
            while((len = in.read(buffer)) != -1) { 
                bos.write(buffer, 0, len); 
            } 
        }catch(IOException e){
            e.printStackTrace();
        }
         return bos.toByteArray(); 
    } 
}