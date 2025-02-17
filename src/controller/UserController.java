package controller;

import dao.CityDAO;
import dao.PlaneDAO;
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
        User user = UserDAO.login(name, password);
        if (user != null) {
            mysession.add("actif", user);
            mysession.add("roles", user.getRole());
            mysession.add("user", user);
            mv.add("cities", CityDAO.findAll());
            mv.add("planes", PlaneDAO.findAll());
            mv.setUrl("dashboard.jsp");
        } else {
            mv.add("name", name);
            mv.add("password", password);
            mv.add("error", "Invalid name or password");
            mv.setUrl("login.jsp");
        }
        return mv;
    }

    @Get
    @Url("/logout")
    public Modelview logout(Mysession mysession) {
        mysession.delete("user");
        mysession.delete("roles");
        Modelview mv = new Modelview();
        mv.setUrl("login.jsp");
        return mv;
    }
}
