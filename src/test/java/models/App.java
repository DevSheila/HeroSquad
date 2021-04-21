package models;
import models.Hero;
import models.Squad;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class App {
    //-----------HEROKU START-----

    //-----------HEROKU END-----

    public static void main(String[] args) {
        staticFileLocation("/public");
        Hero newHero1 = new Hero("PaperCut", 19,"can use papers to make any weapon","water",20,40);
        Hero newHero2 =new Hero("Shadow San",30,"all martial arts","low temparatures",20,60);
        Hero newHero3 =new Hero("Mime Bomb",30,"magic sneak attacks","cats allergy",20,60);
        Hero newHero4 =new Hero("Shifty",30,"shape shifting ","Temparature above 40 degrees",60,50);
        Hero newHero5 =new Hero("Elon Mask",30,"Vast knowledge in powerfultech ","Emotional",20,60);
        Hero newHero6 =new Hero("Carmen Sandiego",19,"Pick Pocketing","flu",78,90);

        get("/",(request, response) ->{
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("username", request.session().attribute("username"));

            return new ModelAndView(model, "index.hbs");

        },new HandlebarsTemplateEngine());



        post("/home",(request,response)->{
            Map<String,Object> model = new HashMap<String,Object>();

            //taking username entered on form
            String inputUsername = request.queryParams("username");

            //saving username passed on form to our session data
            request.session().attribute("username",inputUsername);

            //passing session username to model
            model.put("username", inputUsername);
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());




    }
}
