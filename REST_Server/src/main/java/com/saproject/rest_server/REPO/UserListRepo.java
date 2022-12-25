package com.saproject.rest_server.REPO;

import com.saproject.rest_server.Model.User_List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserListRepo extends JpaRepository<User_List, Long> {
}
