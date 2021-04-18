package com.annas;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Planet extends Circle
{

    private static final long serialVersionUID = 1L;
    Vector2f velocity;
    float mass;

    public Planet(float x, float y, float radius, float gravity, float velX, float velY)
    {
        super(x, y, radius);
        mass = gravity * radius * radius / Main.gravitationalConstant;
        velocity = new Vector2f(velX, velY);
    }

    public void update(Planet otherPlanet)
    {
        if (getLocation().x > 1024 + radius)
        {
            setLocation(-radius, getY());
        }
        else if (getLocation().x < -radius)
        {
            setLocation(1024 + radius, getY());
        }

        if (getLocation().y > 600 + radius)
        {
            setLocation(getX(), 0 - radius);
        }
        else if (getLocation().y < -radius)
        {
            setLocation(getX(), 600 + radius);
        }
        float squareDst = getLocation().distanceSquared(otherPlanet.getLocation());
        float distance = (float) Math.sqrt(squareDst);

        Vector2f forceDir = (otherPlanet.getLocation()
                                        .sub(getLocation())).normalise();
        Vector2f force = forceDir.scale(Main.gravitationalConstant * mass * otherPlanet.mass / squareDst);
        Vector2f acceleration = force.scale(mass);

        if (distance > radius + otherPlanet.radius)
        {
            velocity.add(acceleration);
        }
        if (distance - radius - otherPlanet.radius < 0)
        {
            float collisonDepth = Math.abs(distance - radius - otherPlanet.radius);
            Vector2f v = new Vector2f(velocity.getX(), velocity.getY());
            Vector2f v1 = new Vector2f(otherPlanet.velocity.getX(), otherPlanet.velocity.getY());
            setLocation(getLocation().sub(v.normalise()
                                           .scale(collisonDepth * 0.5f)));
            otherPlanet.setLocation(otherPlanet.getLocation()
                                               .sub(v1.normalise()
                                                      .scale(collisonDepth * 0.5f)));
            velocity = new Vector2f();
        }
        setLocation(getLocation().add(velocity));
    }

}
