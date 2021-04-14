package io.chege.praco_api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("Select u FROM User u WHERE u.email = ?1")
    Optional<User> getUserByEmail(String email);

    @Query(value="Select id from users where email = ?1 and id <> ?2", nativeQuery = true)
    Optional<User> getUserByEmailExcept(String email, String userId);

    String starCountQuery = "" +
            "select round(avg(date_part('hour', end_time - start_time) + date_part('minute', end_time - start_time)/60))\n" +
            "from practice_logs\n" +
            "where user_id = ?1\n" +
            "and start_time >= now() - interval '7 days';";
    @Query(value = starCountQuery, nativeQuery = true)
    Integer getStarCount(String userId);
}
