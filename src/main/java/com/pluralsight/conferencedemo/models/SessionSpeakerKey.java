package com.pluralsight.conferencedemo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SessionSpeakerKey implements Serializable {
    @Column(name = "session_id")
    public Long sessionId;
    @Column(name = "speaker_id")
    public Long speakerId;
}
