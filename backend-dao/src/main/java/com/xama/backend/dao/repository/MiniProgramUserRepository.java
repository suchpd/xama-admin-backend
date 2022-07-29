package com.xama.backend.dao.repository;

import com.xama.backend.domain.entity.MiniProgramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniProgramUserRepository extends JpaRepository<MiniProgramUser,Integer> {

}
