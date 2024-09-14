package LambokWithJsonArray;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalUser {
	
	private int id;
	private String name;
	private String status;
	private Category category;
	private List<String> photourls;
	private List<Tag> tags;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category{
		private int id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag{
		private int id;
		private String name;
	}
	

}
