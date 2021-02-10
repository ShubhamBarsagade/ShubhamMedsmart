package com.medsmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsmart.dao.AdminRepository;
import com.medsmart.entity.AdminDetail;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository repository;
	
	public 	List<AdminDetail> getAllAdminDetail(){
		
		List<AdminDetail> listOfAdminDetail= repository.findAll();
		
		if(listOfAdminDetail.size()>0) {
			return listOfAdminDetail;
		}else {
			return new 	ArrayList<AdminDetail>();
		}
	}
	
	public AdminDetail getAdminByUser(String adminUser)  {
		System.out.println("inside getAdminByuser");
		AdminDetail admin = repository.findAdminByAdminUser(adminUser);
		System.out.println("details inside getAdminBy user "+admin);
		return admin;
	}
	public boolean getAdminByPassword(String adminPass){
		Optional<AdminDetail> admin = repository.findByAdminPass(adminPass);
		
		if(admin.isPresent()) {
			return true;
		}else {
			return false;
			//throw new RecordNotFondException("No admin record exist for given User",adminPass);
		}
	}
	
	public AdminDetail getAdminByUserAndPassword(String adminUser,String adminPass)  {
		System.out.println("inside getAdminByUserAndPassword "+adminUser +" "+adminPass);
		AdminDetail admin = repository.findAdminByAdminUserAndAdminPass(adminUser,adminPass);
		System.out.println(admin);
		
		return admin;
	}
	
	
	public void saveAdminDetail(AdminDetail adminDetail) {
		System.out.println("inside create admin block");
		repository.save(adminDetail);
	}
	
	public AdminDetail updateAdminDetail(AdminDetail detail) {
		
		if(detail.getAdminUser()!=null) {
			AdminDetail admin = repository.findAdminByAdminUser(detail.getAdminUser());
			
			if(admin!=null) {
				admin.setAdminPass(detail.getAdminPass());
				admin.setAdminUser(detail.getAdminUser());
				admin.setId(detail.getId());
				admin= repository.save(admin);
				return admin;
			}else {
				detail= repository.save(detail);
				return detail;
			}
		}else {
			detail= repository.save(detail);
			return detail;
		}
	}
	public void deleteAdminDetailById(String adminUser)
    {
        AdminDetail admin = repository.findAdminByAdminUser(adminUser);
         
        if(admin!= null)
        {
            repository.deleteByAdminUser(adminUser);
        } else {
           
        }
    }

	public boolean isAdminDetailAlreadyPresent(@Valid AdminDetail adminDetail) {
		return false;
	}

	
}
