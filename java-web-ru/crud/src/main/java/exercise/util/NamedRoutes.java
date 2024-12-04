package exercise.util;

public class NamedRoutes {

    public static String rootPath() { return "/"; }
    public static String postsPath() { return "/posts"; }
    public static String postsPath(int page) { return "/posts?page=" + String.valueOf(page); }
    public static String postsPath(String page) { return "/posts?page=" + page; }
    public static String postPath(Long id) { return "/posts/" + String.valueOf(id); }
    public static String postPath(String id) { return "/posts/" + id; }
    // END
}
