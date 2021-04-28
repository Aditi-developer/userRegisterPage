package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.project.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	@Query("Select u from UserModel u where u.username=?1 and u.password=?2")
	UserModel findByNameAndPwd(String username, String password);
}
