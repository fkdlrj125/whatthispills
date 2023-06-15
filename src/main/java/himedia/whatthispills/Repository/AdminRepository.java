package himedia.whatthispills.Repository;

import java.util.Optional;

import himedia.whatthispills.Domain.Admin;

public interface AdminRepository {
	
	public Optional<Admin> findByEmail(String email);
	
}
