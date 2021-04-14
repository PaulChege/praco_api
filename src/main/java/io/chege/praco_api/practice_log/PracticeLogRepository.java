package io.chege.praco_api.practice_log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeLogRepository extends JpaRepository<PracticeLog, String> {

    @Query(value="select * from practice_logs where user_id = ?1 and status = true", nativeQuery = true)
    List<PracticeLog> getPracticeLogsByUser(String userId);
}
