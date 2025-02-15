package model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Album {

    private Integer userId;
    private Integer id;
    private String title;
}
