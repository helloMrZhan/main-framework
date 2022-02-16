package com.zjq.dao;

import com.zjq.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户dao
 * @author zjq
 */
public interface UserDao extends JpaRepository<User,Long> ,JpaSpecificationExecutor<User> {
}
