package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutri {
	private Long idx;
	private String name;
	private String category;
	private String company;
	private String base;
	private String effect;
	private String taking;
	private String warning;
	private String etc;
	private String image;


	public Nutri(String name, String category, String company, String base, String effect,
			String taking, String warning, String etc, String image) {
		this.name = name;
		this.category = category;
		this.company = company;
		this.base = base;
		this.effect = effect;
		this.taking = taking;
		this.warning = warning;
		this.etc = etc;
		this.image = image;
	}
}
