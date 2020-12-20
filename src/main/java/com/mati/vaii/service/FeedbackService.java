package com.mati.vaii.service;

import com.mati.vaii.model.Feedbacks;
import com.mati.vaii.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedbacks> getFeedbacks(){

        List<Feedbacks> feedbacks = feedbackRepository.findAll();
        return feedbacks;
    }

    public Feedbacks postFeedback(Feedbacks feedback) {
        Feedbacks feedbacks = feedbackRepository.save(feedback);
        return feedbacks;
    }

    public Feedbacks findFeedbackById(Long feedbackId){
        Feedbacks feedback = feedbackRepository.findById(feedbackId).orElse(null);
        return feedback;
    }

    public Feedbacks updateFeedback(Feedbacks feedback){
        Feedbacks feedbackExists = feedbackRepository.findById(feedback.getId()).orElse(null);
        Feedbacks feedbacks = null;
        if (feedbackExists != null) {
            feedbacks = feedbackRepository.save(feedback);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return feedbacks;
    }

    public Feedbacks deleteFeedback(Long id){
        Feedbacks feedbackExists = feedbackRepository.findById(id).orElse(null);
        Feedbacks feedbacks = null;
        if (feedbackExists != null) {
            feedbackRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return feedbacks;
    }
}
