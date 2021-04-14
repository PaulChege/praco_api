package io.chege.praco_api.practice_log;

import io.chege.praco_api.instrument.InstrumentRepository;
import io.chege.praco_api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticeLogService {

    @Autowired
    private final PracticeLogRepository practiceLogRepository;
    private final UserRepository userRepository;
    private final InstrumentRepository instrumentRepository;

    public PracticeLogService(PracticeLogRepository practiceLogRepository, UserRepository userRepository, InstrumentRepository instrumentRepository) {
        this.practiceLogRepository = practiceLogRepository;
        this.userRepository = userRepository;
        this.instrumentRepository = instrumentRepository;
    }

    public PracticeLog createPracticeLog(PracticeLog practiceLog){
        practiceLog.setUser(userRepository.findById(practiceLog.getUserId()).get());
        practiceLog.setInstrument(instrumentRepository.findById((practiceLog.getInstrumentId())).get());
        return practiceLogRepository.save(practiceLog);
    }

    public List<PracticeLog> getPracticeLogsByUser(String userId){
        return practiceLogRepository.getPracticeLogsByUser(userId);
    }

    public PracticeLog getPracticeLog(String practiceLogId){
        return practiceLogRepository.findById(practiceLogId).get();
    }

    public PracticeLog updatePracticeLog(PracticeLog logUpdate){
        PracticeLog practiceLog = practiceLogRepository.findById(logUpdate.getId()).get();

        if(logUpdate.getInstrument() != null){
            practiceLog.setInstrument(instrumentRepository.findById(logUpdate.getInstrumentId()).get());
        }
        if(logUpdate.getNotes() != null){
            practiceLog.setNotes(logUpdate.getNotes());
        }
        if(logUpdate.getStatus() != null){
            practiceLog.setStatus(logUpdate.getStatus());
        }
        if(logUpdate.getStartTime() != null){
            practiceLog.setStartTime(logUpdate.getStartTime());
        }
        if(logUpdate.getEndTime() != null){
            practiceLog.setEndTime(logUpdate.getEndTime());
        }
        practiceLogRepository.save(practiceLog);
        return practiceLog;
    }
}
