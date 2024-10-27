package com.sampleproj.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sampleproj.project.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
