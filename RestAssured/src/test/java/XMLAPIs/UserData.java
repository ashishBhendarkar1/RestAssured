package XMLAPIs;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement
public class UserData {
	
	@JacksonXmlProperty(localName = "objects")
	private String Objects;
	
	@JacksonXmlProperty(isAttribute = true , localName = "type")
	private String Type;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "object")
	private List<object> Object;



@Data
public static class object{
	
	@JacksonXmlProperty(localName = "id")
	private int Id;
	
	@JacksonXmlProperty(isAttribute = true , localName = "type")
	private String Type;
	
	@JacksonXmlProperty(localName = "name")
	private String Name;
	
	@JacksonXmlProperty(localName = "email")
	private String Email;
	
	@JacksonXmlProperty(localName = "gender")
	private String Gender;
	
	@JacksonXmlProperty(localName = "status")
	private String Status;
	
	
}
 

}