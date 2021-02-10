package com.medsmart.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsmart.entity.AdminDetail;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetail,Long>{

	AdminDetail findAdminByAdminUser(String adminUser);

	void deleteByAdminUser(String adminUser);

	AdminDetail findAdminByAdminUserAndAdminPass(String adminUser,String adminPass);

	Optional<AdminDetail> findByAdminPass(String adminPass);
	
	

}
