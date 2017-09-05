import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ESV extends Books{
	
	
	private JSONArray books;
	private String apiKey;
	
	public ESV(){
		super("ESV");
		apiKey="73432f68bc9b5626";
		books=super.getJSONBooks();
		
	}
	
	
	public void getText(String b,int chap){
		int index=getIndex(b);
		JSONObject book=(JSONObject)books.get(index);
		JSONArray chapters=(JSONArray)book.get("chapters");
		if(chap>0 && chap<=chapters.size()){
			try{
				//System.out.println(getPassage(book.get("abbr").toString(),chap));
				String textHTML=getPassage(book.get("abbr").toString(),chap);
				Document doc=Jsoup.parse(textHTML);
				Elements allHeaders=doc.select("div.esv-text > h3");
				
				
				
				System.out.println(book.get("name").toString()+"  "+chap);
				
				
				  for (Element element :allHeaders) {
				     StringBuilder sb = new StringBuilder(element.text()+"\n");

				     Element next = element.nextElementSibling();
				     while (next != null && !next.tagName().startsWith("h")) {
				        sb.append(next.text()).append("\n");
				        next = next.nextElementSibling();
				     }
				     System.out.println(sb);

				  }
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public String getPassage(String t,int c) throws Exception {
	    StringBuilder urlStringBuilder = new StringBuilder();
	    
	    urlStringBuilder.append("http://www.esvapi.org/v2/rest/passageQuery?key=");
	    urlStringBuilder.append(apiKey);
	    urlStringBuilder.append("&passage="
	            + URLEncoder.encode(t + " " + c, "ISO-8859-1"));

	    URL esvURL = new URL(urlStringBuilder.toString());
	    InputStream esvStream = esvURL.openStream();

	    StringBuilder outStringBuilder = new StringBuilder();
	    int nextChar;

	    while ((nextChar = esvStream.read()) != -1) {
	      outStringBuilder.append((char) nextChar);
	    }

	    esvStream.close();

	    return outStringBuilder.toString();
	  }

	
	public void listAllBooks(){
		
		super.listAllBooks();
		
	}
	public ArrayList<String> getBookList(){
		return super.getBookList();
	}
	public int getIndex(String title){
		return super.getIndex(title);
		
	}
	public JSONArray getJSONBooks(){
		return super.getJSONBooks();
	}
	public String getVersion(){
		return super.getVersion();
	}


	
	
	
	
	
}
