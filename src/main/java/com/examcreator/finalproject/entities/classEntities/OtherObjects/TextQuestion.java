package com.examcreator.finalproject.entities.classEntities.OtherObjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "text_question")
public class TextQuestion extends Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
