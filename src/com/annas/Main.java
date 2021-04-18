package com.annas;

import org.newdawn.slick.*;

import java.util.Random;

public class Main extends BasicGame
{

    static Planet[] planets = new Planet[10];
    static float gravitationalConstant = 0.0000674f;

    public static void main(String[] arguments)
    {

        try
        {
            AppGameContainer app = new AppGameContainer(new Main());
            app.setDisplayMode(1024, 600, false);
            app.setShowFPS(false);
            app.start();
        }
        catch (SlickException slickException)
        {
            slickException.printStackTrace();
        }
    }

    public Main()
    {
        super("Planet");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException
    {
        Random random = new Random();
        for (int i = 0; i < planets.length; i++)
        {
            planets[i] = new Planet(random.nextFloat() * 1024, random.nextFloat() * 600, 20, 0.00001f, 0, 0);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException
    {
        for (Planet planet : planets)
        {
            for (Planet otherPlanet : planets)
            {
                if (!planet.equals(otherPlanet))
                {
                    planet.update(otherPlanet);
                }
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException
    {
        for (Planet planet : planets)
        {
            graphics.fill(planet);
        }
    }
}