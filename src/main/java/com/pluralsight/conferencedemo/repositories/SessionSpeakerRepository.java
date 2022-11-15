package com.pluralsight.conferencedemo.repositories;

import java.util.List;

import com.pluralsight.conferencedemo.models.SessionSpeaker;
import com.pluralsight.conferencedemo.models.SessionSpeakerKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionSpeakerRepository extends JpaRepository<SessionSpeaker, SessionSpeakerKey> {

    List<SessionSpeaker> findBySessionSpeakerKeySessionId(Long sessionId);

    List<SessionSpeaker> findBySessionSpeakerKeySpeakerId(Long speakerId);

}
