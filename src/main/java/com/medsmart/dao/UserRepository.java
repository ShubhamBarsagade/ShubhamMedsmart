package com.medsmart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsmart.entity.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Long>{


	UserDetail findUserByUserUsername(String userUsername);

	UserDetail findUserDetailByUserUsernameAndUserPass(String userUsername, String userPass);

	UserDetail findUserDetailByUserEmail(String email);

}
