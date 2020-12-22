package com.examcreator.finalproject.entities.classEntities.OtherObjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "text_question")
public class TextQuestion extends Question{
}
