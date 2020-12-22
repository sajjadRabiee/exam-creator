package com.examcreator.finalproject.entities.classEntities;

import com.examcreator.finalproject.entities.enumEntities.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Admin() {
        setRole(Role.ROLE_ADMIN);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
