package com.capgemini.capfoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class GroupeEquipeId implements Serializable {
    private Long groupeId;
    private Long equipeId;
}
