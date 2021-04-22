
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
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    //-----------HEROKU END-----

    public static void main(String[] args) {

        //heroku port
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Hero newHero1 = new Hero("PaperCut", 19,"can use papers to make any weapon","water",20,40);
        Hero newHero2 =new Hero("Shadow San",30,"all martial arts","low temparatures",20,60);
        Hero newHero3 =new Hero("Mime Bomb",30,"magic sneak attacks","cats allergy",20,60);
        Hero newHero4 =new Hero("Shifty",30,"shape shifting ","Temparature above 40 degrees",60,50);
        Hero newHero5 =new Hero("Elon Mask",30,"Vast knowledge in powerfultech ","Emotional",20,60);
        Hero newHero6 =new Hero("Carmen Sandiego",19,"Pick Pocketing","flu",78,90);

        ArrayList<Hero> squad1Heroes=new ArrayList<Hero>();
        newHero1.updateHero(true);
        newHero6.updateHero(true);

        squad1Heroes.add(newHero1);
        squad1Heroes.add(newHero6);
        Squad squad1 = new Squad(10,"Myth Busters","justice and equality",squad1Heroes);




        //HOME VIEW
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


        //-------------------------------1.CREATE-----------------------
        //A.)----------------------------CREATE HEROES------------------
        get("/heroes/form",(request, response) -> {
            Map<String,Object> model = new HashMap<String,Object>();
            return new ModelAndView(model,"hero-form.hbs");

        }, new HandlebarsTemplateEngine());

        post("/heroes/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();

            String name = request.queryParams("name");
            int age=Integer.parseInt(request.queryParams("age"));
            String powers=request.queryParams("powers");
            String weakness=request.queryParams("weakness");
            int defence=Integer.parseInt(request.queryParams("defence"));
            int attack = Integer.parseInt(request.queryParams("attack"));
            Hero newHero = new Hero(name,age,powers,weakness,defence,attack);

            model.put("heroes", newHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //B.)----------------------------CREATE SQUAD-----------------
        get("/squads/form",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes=Hero.getHeroes();
            ArrayList<Hero> heroList=new ArrayList<>();
            for (int i=0;i<heroes.size();i++){
                if(heroes.get(i).isOccupied()==false){
                    heroList.add(heroes.get(i));
                }
            }

            model.put("heroes",Hero.getHeroes());
            return new ModelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine());


        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String name = request.queryParams("name");
            int maxSize=Integer.parseInt(request.queryParams("size"));
            String cause=request.queryParams("cause");
            ArrayList<Hero> heroes=new ArrayList<>();
            if(request.queryParamsValues("heroes")!=null){
                String[] heroesList=request.queryParamsValues("heroes");

                for(int i=0;i<heroesList.length;i++){
                    Hero addHero=Hero.findById(Integer.parseInt(heroesList[i]));
                    if(heroes.size()<maxSize){
                        addHero.updateHero(true);
                        heroes.add(addHero);
                    }

                }
            }
            Squad newSquad= new Squad(maxSize,name,cause,heroes);

            model.put("heroes",Hero.getHeroes());

            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());


        //------------------------2.READ---------------
        //(ALL HEROES)
        get("/heroes",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            //get all heroes
            ArrayList<Hero> heroes = Hero.getHeroes();
            //pass all heroes to the model
            model.put("heroes", heroes);

            return new ModelAndView(model,"heroes-view.hbs");
        }, new HandlebarsTemplateEngine());

        //(SPECIFIC HERO)
        get("/heroes/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<>();

            int heroId = Integer.parseInt(request.params(":id"));
            Hero foundHero = Hero.findById(heroId);
            model.put("hero",foundHero);

            ArrayList<Hero> heroes = Hero.getHeroes();
            model.put("heroes", heroes);

            return new ModelAndView(model, "hero-view.hbs");
        }, new HandlebarsTemplateEngine());

        //(ALL SQUADS)
        get("/squads",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squads",Squad.getSquads());
            return new ModelAndView(model,"squads-view.hbs");

        },new HandlebarsTemplateEngine());

        //(SPECIFIC SQUAD)
        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind=Integer.parseInt(request.params(":id"));
            Squad foundSquad=Squad.findById(idOfSquadToFind);
            model.put("squad",foundSquad);
            ArrayList<Squad> squads=Squad.getSquads();
            model.put("squads",squads);
            return new ModelAndView(model,"squad-view.hbs");
        },new HandlebarsTemplateEngine());




        //-----------------------4.DELETE( ALL HEROES)------------------
        //delete all heroes
        get("/Heroes/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero.clearAll();
            model.put("heroes",Hero.getHeroes());
            return new ModelAndView(model,"heroes-view.hbs");

        },new HandlebarsTemplateEngine());
        get("/hero/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete=Integer.parseInt(request.params(":id"));
            Hero foundHero=Hero.findById(idOfHeroToDelete);
            for (int i=idOfHeroToDelete;i<Hero.getHeroes().size();i++){
                Hero.getHeroes().get(i).setId(Hero.getHeroes().get(i).getId()-1);
            }
            foundHero.deleteHero();
            ArrayList<Hero> heroes = Hero.getHeroes();
            model.put("heroes", heroes);
            return new ModelAndView(model,"heroes-view.hbs");

        },new HandlebarsTemplateEngine());

        get("/squad/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete=Integer.parseInt(request.params(":id"));
            Squad foundSquad=Squad.findById(idOfSquadToDelete);
            ArrayList<Hero> heroes=foundSquad.getHeroes();

            for(int i=0;i<heroes.size();i++){
                heroes.get(i).updateHero(false);
            }
            for (int i=idOfSquadToDelete;i<Squad.getSquads().size();i++){
                Squad.getSquads().get(i).setId(Squad.getSquads().get(i).getId()-1);
            }
            foundSquad.deleteSquad();

            ArrayList<Squad> squads = Squad.getSquads();
            model.put("squads", squads);
            return new ModelAndView(model,"squad-view.hbs");

        },new HandlebarsTemplateEngine());

        //delete all squads
        get("/squadz/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad.clearAll();
            ArrayList<Hero> heroes=Hero.getHeroes();
            for (int i=0;i<heroes.size();i++){
                heroes.get(i).updateHero(false);
            }
            model.put("squads",Squad.getSquads());
            return new ModelAndView(model,"squads-view.hbs");

        },new HandlebarsTemplateEngine());;


    }

}
