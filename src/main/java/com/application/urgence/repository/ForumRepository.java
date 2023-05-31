package com.application.urgence.repository;

import com.application.urgence.models.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    List<Forum> findForumByUserId(Long id);
}
