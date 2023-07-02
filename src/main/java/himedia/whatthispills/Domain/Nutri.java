package himedia.whatthispills.Domain;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutri {
	private Long idx;
	private String name;
	private String category;
	private String company;
	private String shape;
	private String base;
	private String taking;
	private String effect;
	private String caution;
	private String type;
	private String image;
	private Map<String, String> review;
	
	public Nutri() {
		
	}
	
	public Nutri(Long idx, String name, String category, String company, String shape, String base, String taking,
			String effect, String caution, String type) {
		this.idx = idx;
		this.name = name;
		this.category = category;
		this.company = company;
		this.shape = shape;
		this.base = base;
		this.taking = taking;
		this.effect = effect;
		this.caution = caution;
		this.type = type;
	}

	public Nutri(Long idx, String name, String category, String company, String shape, String base, String taking,
			String effect, String caution, String type, String image) {
		this.idx = idx;
		this.name = name;
		this.category = category;
		this.company = company;
		this.shape = shape;
		this.base = base;
		this.taking = taking;
		this.effect = effect;
		this.caution = caution;
		this.type = type;
		this.image = image;
	}
}
