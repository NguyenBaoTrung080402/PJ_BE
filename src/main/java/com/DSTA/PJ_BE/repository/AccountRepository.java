package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT a FROM Account a WHERE a.email = :email")
    Account getAccountUserName(@Param("email") String email);

    @Query(value = "SELECT a FROM Account a WHERE a.id = :id")
    Account getAccountId(@Param("id") Long id);
    @Query(value = "SELECT a FROM Account a")
    Page<Account> getUser(Pageable pageable);
}
