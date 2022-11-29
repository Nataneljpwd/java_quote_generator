import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
public class test {
    static String getUrl="https://type.fit/api/quotes";
    public static void main(String[] args) throws IOException {
        String st=sendGet();
        saveToFile(st);
         try {
                JSONArray text=new JSONArray(sendGet());
                int random= (int) Math.floor(Math.random()*text.length());
                String author=text.getJSONObject(random).getString("author");
                String quote=text.getJSONObject(random).getString("text");
                if(author.equals("null"))
                {
                    author="Author Unknown";
                }
                //do something whith the generated quote
                //author is the author of the quote
                //quote is the quote
           }
            catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        //st=st.substring(1, st.length()-2);
    }
    public static String sendGet() throws IOException {
        URL obj=new URL(getUrl);
        HttpURLConnection con=(HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader r=new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder bldr=new StringBuilder();
            String inputLine;
            while((inputLine=r.readLine())!=null){
                bldr.append(inputLine);
                bldr.append("\n");
            }
            r.close();
            return bldr.toString();
        }
        return "Error";
    }

public static void saveToFile(String write) throws IOException{
        File f=new File("output.txt");
        f.createNewFile();
        FileWriter we=new FileWriter("output.txt");
        we.write(write);
        we.close();
    }
   
}
