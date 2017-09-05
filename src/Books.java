
import java.io.FileReader;
import java.util.ArrayList;


import org.json.simple.*;
import org.json.simple.parser.*;



public class Books {
//	private ESV esvBible=null;
//	private KJV kjvBible=null;
//	private NIV nivBible=null;
	
	private JSONArray books;
	private String version;
	public Books(String v){
		//System.out.println("hello");
		version=v.toUpperCase();
		JSONParser parser= new JSONParser();
		try{
			Object obj = parser.parse(new FileReader("/Users/Bryan/Desktop/BibleApp/jsonData/books.txt"));
			JSONObject jsonObject = (JSONObject)obj;
			JSONObject tmpObj =(JSONObject)jsonObject.get("response");
			books=(JSONArray)tmpObj.get("books");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	public void listAllBooks(){
		
		for(int i=0;i<books.size();i++){
			JSONObject book=(JSONObject)books.get(i);
			
			System.out.println(book.get("name")+" ("+book.get("abbr")+") ");
		}
		
	}
	public ArrayList<String> getBookList(){
		ArrayList<String> bibleBooks=new ArrayList<String>();
		for(int i=0;i<books.size();i++){
			bibleBooks.add(((JSONObject)books.get(i)).get("name").toString());
		}
		return bibleBooks;
	}
	public int getIndex(String title){
		title=title.toLowerCase();
		for(int i=0;i<books.size();i++){
			JSONObject book=(JSONObject)books.get(i);
			String bookTitle=book.get("name").toString().toLowerCase();
			String bookAbbr=book.get("abbr").toString().toLowerCase();
			//System.out.println(bookTitle+"      "+title);
			if(title.compareTo(bookTitle)==0||title.compareTo(bookAbbr)==0)
			{
				return Integer.parseInt(book.get("ord").toString())-1;
			}
			//System.out.println(book.get("name")+" ("+book.get("abbr")+") ");
		}
		return -1;
		
	}
	public JSONArray getJSONBooks(){
		return books;
	}
	public String getVersion(){
		return version;
	}

	
	
	
	
	
	
	
	
	
//	public void getText(String b,int chap){
//		int index=getIndex(b);
//		JSONObject book=(JSONObject)books.get(index);
//		JSONArray chapters=(JSONArray)book.get("chapters");
//		if(chap>0 && chap<=chapters.size()){
//			try{
//				//System.out.println(getPassage(book.get("abbr").toString(),chap));
//				String textHTML=getPassage(book.get("abbr").toString(),chap);
//				Document doc=Jsoup.parse(textHTML);
//				Elements allHeaders=doc.select("div.esv-text > h3");
//				
//				
//				
//				System.out.println(book.get("name").toString()+"  "+chap);
//				
//				
//				  for (Element element :allHeaders) {
//				     StringBuilder sb = new StringBuilder(element.text()+"\n");
//
//				     Element next = element.nextElementSibling();
//				     while (next != null && !next.tagName().startsWith("h")) {
//				        sb.append(next.text()).append("\n");
//				        next = next.nextElementSibling();
//				     }
//				     System.out.println(sb);
//
//				  }
//				
//				
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
//	public String getPassage(String t,int c) throws Exception {
//	    StringBuilder urlStringBuilder = new StringBuilder();
//	    String apiKey="73432f68bc9b5626";
//	    urlStringBuilder.append("http://www.esvapi.org/v2/rest/passageQuery?key=");
//	    urlStringBuilder.append(apiKey);
//	    urlStringBuilder.append("&passage="
//	            + URLEncoder.encode(t + " " + c, "ISO-8859-1"));
//
//	    URL esvURL = new URL(urlStringBuilder.toString());
//	    InputStream esvStream = esvURL.openStream();
//
//	    StringBuilder outStringBuilder = new StringBuilder();
//	    int nextChar;
//
//	    while ((nextChar = esvStream.read()) != -1) {
//	      outStringBuilder.append((char) nextChar);
//	    }
//
//	    esvStream.close();
//
//	    return outStringBuilder.toString();
//	  }
	
	
//	public void listenToPassage(String t,int c) throws Exception{
//		StringBuilder urlStringBuilder = new StringBuilder();
//	    String apiKey="73432f68bc9b5626";
//	    urlStringBuilder.append("http://www.esvapi.org/v2/rest/passageQuery?key=");
//	    urlStringBuilder.append(apiKey);
//	    urlStringBuilder.append("&passage="
//	            + URLEncoder.encode(t + " " + c, "ISO-8859-1"));
//	    urlStringBuilder.append("&output-format=mp3");
//
//	    URL esvURL = new URL(urlStringBuilder.toString());
//	    esvURL.openConnection();
//	    
//	    //esvURL.close();
//	}
}
