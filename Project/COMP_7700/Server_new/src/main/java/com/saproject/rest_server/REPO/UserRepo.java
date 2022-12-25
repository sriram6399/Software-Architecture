package com.saproject.rest_server.REPO;

import com.saproject.rest_server.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}
