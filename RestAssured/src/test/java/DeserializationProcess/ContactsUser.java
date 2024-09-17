package DeserializationProcess;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ContactsUser {

	private String _id;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String email;
	private Integer phone;
	private String street1;
	private String street2;
	private String city;
	private String stateProvince;
	private Integer postalCode;
	private String country;
	private String owner;
	private String __v;
	
}
