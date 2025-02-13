package controller;

import dao.UserDAO;
import mg.noobframework.annotation.Controller;
import mg.noobframework.annotation.Get;
import mg.noobframework.annotation.Post;
import mg.noobframework.annotation.RequestParam;
import mg.noobframework.annotation.Url;
import mg.noobframework.modelview.Modelview;
import mg.noobframework.session.Mysession;
import model.User;

@Controller
public class UserController {

    @Get
    @Url("/showlogin")
    public Modelview showlogin() {
        Modelview mv = new Modelview();
        mv.setUrl("login.jsp");
        return mv;
    }

    @Post
    @Url("/login")
    public Modelview login(@RequestParam("name") String name, @RequestParam("password") String password,
            Mysession mysession) throws Exception {
        Modelview mv = new Modelview();
        mv.setUrl("home.jsp");
        User user = UserDAO.login(name, password);
        if (user != null) {
            mysession.add("actif", user);
            mysession.add("roles", user.getRole());
            mysession.add("user", user);
        } else {
            mv.add("url", "/showlogin");
        }
        return mv;
    }
}
