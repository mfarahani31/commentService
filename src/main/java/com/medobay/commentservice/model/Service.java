package com.medobay.commentservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CS_SERVICE")
public class Service extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "TITLE", unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "service")
    private List<Comment> comments;

}
