package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NutriDomain {
	private Long nutri_idx;
	private String nutri_name;
	private String nutri_category;
	private String nutri_company;
	private String nutri_base;
	private String nutri_effect;
	private String nutri_taking;
	private String nutri_warning;
	private String image;
	private String etc;
	
}
