package com.npci.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npci.entity.LoginLogs;

public interface LoginLogsDao extends JpaRepository<LoginLogs, Integer>{

}
