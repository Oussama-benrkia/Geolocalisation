package org.app.backend.rep;

import org.app.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InRepToken extends JpaRepository<Token,Long> {
    @Query("""
            select t from Token t inner join User u on t.user.id=u.id
            where u.id=:userid and (t.expired=false  or t.revoked=false )
            """)
    List<Token> findAllValidTokens(Long userid);

    Optional<Token> findByToken(String token);
}
