package com.application.urgence.security.services;

import com.application.urgence.models.Forum;
import com.application.urgence.models.User;

import java.util.List;

public interface ForumService {

    Forum addForum(Forum forum, User user);

    Forum updateForum(Forum forum, Long id);

    List<Forum> list();

    List<Forum> listForumByIdUser(Long id);

    String delete(Long id);
}
