package utils;

public class Parser {
	//input1 is a raw toString output
	//input2 is name of class which is prepended to toString method.
	//output an array containing strings such as: "prop1=val1";
	public static String[] parseToString(String obj,String objName) {
		String objStr=obj.substring(objName.length())
				.replace('[', ' ').replace(']',' ').replace(',',' ');

		String[] objArr=objStr.split(" ");
		return objArr;
	}
}
