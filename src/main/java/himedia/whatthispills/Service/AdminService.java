package himedia.whatthispills.Service;


import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.NutriDomain;
import himedia.whatthispills.Repository.JDBCAdminRepository;

@Service
public class AdminService {
	
	private final JDBCAdminRepository adminRepository;
	
	public AdminService(JDBCAdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public NutriDomain edit() {
		return adminRepository.editNutri(null, null);
	}
}
