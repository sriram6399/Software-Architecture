package com.saproject.rest_server.REPO;

import com.saproject.rest_server.Model.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepo extends JpaRepository<Lists, Long> {

}
