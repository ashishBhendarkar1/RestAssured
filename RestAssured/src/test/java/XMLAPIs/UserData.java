package XMLAPIs;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "objects")
public class UserData {
		
	@JacksonXmlProperty(isAttribute = true , localName = "type")
	private String Type;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "object")
	private List<object> Object;



@Data
public static class object{
	
	@JacksonXmlProperty(localName = "id")
	private idwrapper Id;
	
	@JacksonXmlProperty(localName = "name")
	private String Name;
	
	@JacksonXmlProperty(localName = "email")
	private String Email;
	
	@JacksonXmlProperty(localName = "gender")
	private String Gender;
	
	@JacksonXmlProperty(localName = "status")
	private String Status;
	
	
}

@Data
public static class idwrapper{
	
	@JacksonXmlText
	private int typevalue;  //this hold the integer value of the id
	
	@JacksonXmlProperty(isAttribute = true , localName = "type")
	private String Type; //this holds the type attribute of id
	
	
}
 

}