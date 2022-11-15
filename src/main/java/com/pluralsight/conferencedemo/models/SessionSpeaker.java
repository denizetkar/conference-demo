package com.pluralsight.conferencedemo.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "session_speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionSpeaker {
    @EmbeddedId
    @Getter // To include in the JSON format
    @Setter // To include in the JSON format
    private SessionSpeakerKey sessionSpeakerKey;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    @JsonIgnore
    private Session session;
    @ManyToOne
    @MapsId("speakerId")
    @JoinColumn(name = "speaker_id")
    @JsonIgnore
    private Speaker speaker;
}
