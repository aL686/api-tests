package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostDto {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
