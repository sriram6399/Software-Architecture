package com.saproject.rest_server.REPO;

import com.saproject.rest_server.Model.Item_List_Pair;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepo extends JpaRepository<Item_List_Pair, Long> {
}
