package io.chege.praco_api.instrument;

import io.chege.praco_api.practice_log.PracticeLog;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "instruments")
public class Instrument {
    @Id
    private String id;
    private String name;
    private Boolean status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "instrument")
    private Set<PracticeLog> practiceLogs;

    public Instrument() {
    }

    public Instrument(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    @PrePersist
    protected void onCreate(){
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
