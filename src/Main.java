
public class Main {
	public static void main(String []args){

		ESV esvBible=null;
		KJV kjvBible=null;
		NIV nivBible=null;
		
		Books bible=new Books("ESV");
		
		if(bible.getVersion().compareTo("ESV")==0)
		{
			esvBible=new ESV();
		}
		else if(bible.getVersion().compareTo("KJV")==0){
			kjvBible=new KJV();
		}
		else if(bible.getVersion().compareTo("NIV")==0){
			nivBible=new NIV();
		}
		//esvBible.listAllBooks();
		//System.out.println(esvBible.getIndex("rev"));
		esvBible.getText("Gen",1);
	
	}
}
