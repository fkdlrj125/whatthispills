package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NutriRec {
	String gender;
	String age;
	Long recommend11;
	Long recommend12;
	Long recommend13;
	
	
	public NutriRec(String gender, String age, Long recommend11, Long recommend12, Long recommend13) {
		this.gender = gender;
		this.age = age;
		this.recommend11 = recommend11;
		this.recommend12 = recommend12;
		this.recommend13 = recommend13;
	}
}
