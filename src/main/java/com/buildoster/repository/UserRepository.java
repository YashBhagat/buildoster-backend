package com.buildoster.repository;

import com.buildoster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    //boolean existsByUser_Phone_number(String phone_number);
    Optional<User> findByEmail(String email);
    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    boolean updatePassword(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Query("update User u set u.f_name = :f_name,u.l_name = :l_name,u.email = :email,u.phone_number = :phone_number where u.id = :id")
    int updateUserProfile(@Param("f_name") String f_name,@Param("l_name") String l_name,@Param("email") String email,@Param("phone_number") String phone_number, @Param("id") Long id);
}
