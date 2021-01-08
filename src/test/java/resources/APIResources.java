package resources;

public enum APIResources {
    addPostAPI("/posts"),
    getPostAPI("/posts"),
    deletePostAPI("/posts/1");
    private String resource;

    APIResources(String resource) {
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
