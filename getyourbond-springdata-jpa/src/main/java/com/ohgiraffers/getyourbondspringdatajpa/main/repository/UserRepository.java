package com.ohgiraffers.getyourbondspringdatajpa.main.repository;

import com.ohgiraffers.getyourbondspringdatajpa.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Since the user and the like entity are mapped by one-to-many(one: user, many: like),
    // I will use 'left join'.
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.likes")
    List<User> findAllByLikes();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.likes l LEFT JOIN FETCH l.bond WHERE u.userId = ?1")
    User findByUserIdWithLike(Integer userId);
}
