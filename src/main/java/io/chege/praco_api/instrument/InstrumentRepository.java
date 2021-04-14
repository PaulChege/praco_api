package io.chege.praco_api.instrument;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, String> {

    Optional<Instrument> findByName(String name);
}
