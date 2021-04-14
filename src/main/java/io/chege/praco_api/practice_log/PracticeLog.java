package io.chege.praco_api.practice_log;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.chege.praco_api.instrument.Instrument;
import io.chege.praco_api.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "practice_logs")
public class PracticeLog {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String instrumentId;

    private String notes;
    private boolean status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PracticeLog() {
    }

    public PracticeLog(String notes, boolean status, LocalDateTime startTime, LocalDateTime endTime) {
        this.notes = notes;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @PrePersist
    protected void onCreate(){
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }


}
