package m08.uf3.drops.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class JoyStick {

    Circle circle;
    Circle circle2;

    public JoyStick(float x, float y, float radius){
        //Circulo
        circle = new Circle(x, y, radius);

        circle2 = new Circle(x, y, radius / 5);

    }

    public void update(float x, float y){

        if (circle2.contains(x, y)){
            circle2.setPosition(x, y);

            if (circle2.x > circle.x + circle.radius){
                circle2.x = circle.x + circle.radius;
            }
            if (circle2.y > circle.y + circle.radius){
                circle2.y = circle.y + circle.radius;
            }
            if (circle2.x < circle.x - circle.radius){
                circle2.x = circle.x - circle.radius;
            }
            if (circle2.y < circle.y - circle.radius){
                circle2.y = circle.y - circle.radius;
            }
        }
    }

    public void render(ShapeRenderer renderer){
        //Color del Joystick
        renderer.setColor(Color.SKY);

        //Circulo de fuera
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.circle(circle.x, circle.y, circle.radius);
        renderer.end();

        //Circulo de dentro
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(circle2.x, circle2.y, circle2.radius);
        renderer.end();
    }
}
