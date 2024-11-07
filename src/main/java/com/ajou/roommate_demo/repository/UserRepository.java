package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: UserRepository
 * @USER: BTS&ARMY
 * @Date 2024/11/5
 * @Version 1.0
 */

public interface UserRepository extends JpaRepository<User,Integer> {
    // 根据用户名查找用户
    User findByUsername(String username);

    // 根据学号查找用户
    User findByStudentId(String studentId);


    /**
     * @param studentId
     * @return
     */
    boolean existsByStudentId(String studentId);
    /**
     * @Author: Tyz
     * @Description: 这是注册需要的
     * @Date: 2024/11/7 8:33
     * @Param:
* @param username
     * @return: boolean
     **/
    boolean existsByUsername(String username);
    //可以安全的返回值Optional可为null
    /**
     * @Author: Tyz 
     * @Description: 这是登录模块
     * @Date: 2024/11/7 8:31
     * @Param: 
* @param studentId
* @param username
     * @return: Optional<User>
     **/
    Optional<User> findByStudentIdOrUsername(String studentId, String username);
}
