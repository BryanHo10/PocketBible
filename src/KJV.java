import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KJV extends Books{
	
	
	private JSONArray books=super.getJSONBooks();
	public KJV(){
		super("KJV");
	}

	
	
	public void getText(String b,int chap){
		int index=getIndex(b);
		JSONObject book=(JSONObject)books.get(index);
		JSONArray chapters=(JSONArray)book.get("chapters");
		if(chap>0 && chap<=chapters.size()){
			
			
			
		}
		
	}
	public String getPassage(String t,int c) throws Exception {
	    StringBuilder urlStringBuilder = new StringBuilder();
	    
	    urlStringBuilder.append("https://getbible.net/json");
	    urlStringBuilder.append("?passage="+t+c);

	    URL kjvURL = new URL(urlStringBuilder.toString());
	    InputStream kjvStream = kjvURL.openStream();

	    StringBuilder outStringBuilder = new StringBuilder();
	    int nextChar;

	    while ((nextChar = kjvStream.read()) != -1) {
	      outStringBuilder.append((char) nextChar);
	    }

	    kjvStream.close();

	    return outStringBuilder.toString();
	  }
}
