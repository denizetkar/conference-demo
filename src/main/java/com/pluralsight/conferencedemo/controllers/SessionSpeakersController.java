package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import com.pluralsight.conferencedemo.models.SessionSpeaker;
import com.pluralsight.conferencedemo.models.SessionSpeakerKey;
import com.pluralsight.conferencedemo.repositories.SessionSpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/session_speakers")
public class SessionSpeakersController {
    private final SessionSpeakerRepository sessionSpeakerRepository;

    @Autowired
    public SessionSpeakersController(SessionSpeakerRepository sessionSpeakerRepository) {
        this.sessionSpeakerRepository = sessionSpeakerRepository;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<SessionSpeaker> list() {
        return sessionSpeakerRepository.findAll();
    }

    @GetMapping(path = "{session_id}/{speaker_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public SessionSpeaker get(@PathVariable(name = "session_id") Long sessionId, @PathVariable(name = "speaker_id") Long speakerId) {
        SessionSpeakerKey sessionSpeakerKey = new SessionSpeakerKey();
        sessionSpeakerKey.setSessionId(sessionId);
        sessionSpeakerKey.setSpeakerId(speakerId);
        return sessionSpeakerRepository.findById(sessionSpeakerKey).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SessionSpeaker create(@RequestBody final SessionSpeaker sessionSpeaker) {
        return sessionSpeakerRepository.saveAndFlush(sessionSpeaker);
    }

    @DeleteMapping(path = "{session_id}/{speaker_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable(name = "session_id") Long sessionId, @PathVariable(name = "speaker_id") Long speakerId) {
        SessionSpeakerKey sessionSpeakerKey = new SessionSpeakerKey();
        sessionSpeakerKey.setSessionId(sessionId);
        sessionSpeakerKey.setSpeakerId(speakerId);
        sessionSpeakerRepository.deleteById(sessionSpeakerKey);
    }

    @GetMapping(path = "/by/session_id/{session_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionSpeaker> getBySessionId(@PathVariable(name = "session_id") Long sessionId) {
        return sessionSpeakerRepository.findBySessionSpeakerKeySessionId(sessionId);
    }

    @GetMapping(path = "/by/speaker_id/{speaker_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionSpeaker> getBySpeakerId(@PathVariable(name = "speaker_id") Long speakerId) {
        return sessionSpeakerRepository.findBySessionSpeakerKeySpeakerId(speakerId);
    }
}
