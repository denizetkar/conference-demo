package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Session get(@PathVariable Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(session, existingSession, "session_id");
        sessionRepository.saveAndFlush(existingSession);
    }
}
