package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepo extends JpaRepository<Network, Long> { }
