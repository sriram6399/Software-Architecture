package com.saproject.rest_server.REPO;

import com.saproject.rest_server.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Items, Long> {

}
