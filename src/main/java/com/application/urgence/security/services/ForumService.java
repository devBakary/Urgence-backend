package com.application.urgence.security.services;

import com.application.urgence.models.Forum;

import java.util.List;

public interface ForumService {

    Forum addForum(Forum forum);

    Forum updateForum(Forum forum, Long id);

    List<Forum> list();

    String delete(Long id);
}
