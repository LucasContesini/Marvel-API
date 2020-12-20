package com.contesini.marvel.model.character;

import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Column(name = "resource_URI")
    private String resourceURI;
    private Date modified;
    private Date start;
    private Date end;
    @JsonIgnore
    @ManyToOne
    private Event previous;
    @JsonIgnore
    @ManyToOne
    private Event next;

    @OneToMany(mappedBy = "previous")
    private List<Event> previousEvents;
    @OneToMany(mappedBy = "next")
    private List<Event> nextEvents;

    @OneToMany(mappedBy = "event")
    private List<Url> urls;
    @OneToOne(mappedBy = "event")
    private Thumbnail thumbnail;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "events")
    Set<Character> characters;

}
