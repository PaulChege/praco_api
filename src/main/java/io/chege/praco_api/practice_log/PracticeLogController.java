package io.chege.praco_api.practice_log;

import io.chege.praco_api.CustomError;
import io.chege.praco_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/practice_logs")
public class PracticeLogController {

    @Autowired
    private final PracticeLogService practiceLogService;
    private final UserService userService;

    public PracticeLogController(PracticeLogService practiceLogService, UserService userService) {
        this.practiceLogService = practiceLogService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getPracticeLogs(@RequestParam(name = "userId") String userId) {
        try {
            userService.confirmUser(userId);
            List<PracticeLog> logs = practiceLogService.getPracticeLogsByUser(userId);
            return ResponseEntity.ok().body(logs);
        } catch (Exception e) {
            CustomError customError = new CustomError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
            return new ResponseEntity(customError, customError.getStatus());
        }
    }

    @GetMapping(path = "{practiceLogId}")
    public ResponseEntity getPracticeLog(
            @PathVariable(name = "practiceLogId") String practiceLogId,
            @RequestParam(name = "userId") String userId) {
        try {
            userService.confirmUser(userId);
            PracticeLog log = practiceLogService.getPracticeLog(practiceLogId);
            return ResponseEntity.ok().body(log);
        } catch (Exception e) {
            CustomError customError = new CustomError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
            return new ResponseEntity(customError, customError.getStatus());
        }
    }

    @PostMapping
    public ResponseEntity createPracticeLog(@RequestBody PracticeLog practiceLog) {
        try {
            userService.confirmUser(practiceLog.getUserId());
            PracticeLog createdPracticeLog = practiceLogService.createPracticeLog(practiceLog);

            if (createdPracticeLog == null) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok().body(createdPracticeLog);
            }
        } catch (Exception e) {
            CustomError customError = new CustomError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
            return new ResponseEntity(customError, customError.getStatus());
        }
    }

    @PatchMapping("{practiceLogId}")
    public ResponseEntity updatePracticeLog(
            @PathVariable(name="practiceLogId") String practiceLogId,
            @RequestParam(name="userId") String userId,
            @RequestBody PracticeLog practiceLog){
        try{
            userService.confirmUser(userId);
            practiceLog.setId(practiceLogId);
            PracticeLog updatedLog = practiceLogService.updatePracticeLog(practiceLog);
            return ResponseEntity.ok().body(updatedLog);
        }
        catch (Exception e){
            CustomError customError = new CustomError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
            return new ResponseEntity(customError, customError.getStatus());
        }
    }
}
