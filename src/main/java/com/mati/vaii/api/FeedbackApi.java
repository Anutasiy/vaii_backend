package com.mati.vaii.api;
import com.mati.vaii.model.Feedbacks;
import com.mati.vaii.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class FeedbackApi {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping(value = "/feedbacks")
    public List<Feedbacks> getAllFeedbacks(){
        return feedbackService.getFeedbacks();
    }

    @GetMapping(value = "/feedbacks/{id}")
    public ResponseEntity<Feedbacks> getFeedbacksById (@PathVariable(value = "id") Long feedbackId)
    {
        Feedbacks feedback = feedbackService.findFeedbackById(feedbackId);
        return ResponseEntity.ok().body(feedback);
    }

    @PostMapping("/feedbacks")
    public Feedbacks createFeedback(@Valid @RequestBody Feedbacks feedback) {
        return feedbackService.postFeedback(feedback);
    }

    @PutMapping("/feedbacks")
    public ResponseEntity<Feedbacks> updateFeedback(@Valid @RequestBody Feedbacks feedbackDetails)
    {
        final Feedbacks updatedFeedback = feedbackService.updateFeedback(feedbackDetails);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/feedbacks/{id}")
    public Map<String, Boolean> deleteFeedback(@PathVariable(value = "id") Long id)
    {
        feedbackService.deleteFeedback(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
