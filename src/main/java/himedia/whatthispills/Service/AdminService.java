package himedia.whatthispills.Service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Repository.JDBCAdminRepository;

@Service
public class AdminService {
	
	private final JDBCAdminRepository adminRepository;
	
	public AdminService(JDBCAdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	public Optional<Admin> findEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	

}
