package resources;

import pojo.Post;

public class TestDataBuilder {
    Post post;
    public Post addPost(String title, String body){
        post=new Post();
        post.setUserId(1);
        post.setId(17);
        post.setTitle(title);
        post.setBody(body);
        return post;
    }
}
