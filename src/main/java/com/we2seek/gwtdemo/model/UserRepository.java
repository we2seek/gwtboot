package com.we2seek.gwtdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByNameIgnoreCase(String name);
}