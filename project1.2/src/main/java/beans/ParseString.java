package beans;

public class ParseString {
	public static void main(String[] args) {
	Author a=new Author(1,100,"sexybitch","Oscar","Wilde",103,"wildeOscar@gmail.com","supersecret");
	System.out.println(a.toString());
	int Authorlen="Author".length();
	String authStr=a.toString().substring(Authorlen)
			.replace('[', ' ').replace(']',' ').replace(',',' ');
	System.out.println(authStr);
	String[] authArr=authStr.split(" ");
	for(int i=0;i<authArr.length;i++) {
		System.out.println(authArr[i]);
	}
	}

}
