package provider;

import model.PostDto;

public class PostProvider {

    public PostDto buildPost (){
        return new PostDto()
                .setTitle("Title")
                .setUserId(3)
                .setBody("Body");
    }
}
