package com.application.urgence.repository;

import com.application.urgence.models.Commentaire;
import com.application.urgence.models.Contact;
import com.application.urgence.models.Likees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likees, Long> {

    /** Liste de like par commentaire **/
    @Query(value ="SELECT a FROM Likees a WHERE a.isLike = true AND a.commentaire.id = :id")
    List<Likees> listeLike(Long id);

    /** Liste de dislike par commentaire **/
    @Query(value ="SELECT a FROM Likees a WHERE a.isDislike = true AND a.commentaire.id = :id")
    List<Likees> listeDislike(Long id);

    List<Likees> findLikeesByCommentaireId(Long id);
}
