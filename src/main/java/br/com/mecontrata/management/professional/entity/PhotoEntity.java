package br.com.mecontrata.management.professional.entity;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

@Data
public class PhotoEntity {

    @Id
    private Long id;
    private String photoTitle;
    private Binary photo;

}
