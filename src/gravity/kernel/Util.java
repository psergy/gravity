package gravity.kernel;

import java.util.LinkedList;
import java.util.List;

import gravity.model.Particle;

public class Util {

	public static List generate_proto(int center_x, int center_y) {
		List<Particle> particles = new LinkedList<Particle>();
	
		
		for (int i = 0; i < 1000; i++)
		{
			double rand = Math.random()*2*Math.PI;
			double rand2 = Math.random();
			double x = (100*rand2)*Math.cos(rand);
			double y = (100*rand2)*Math.sin(rand);
			double mag = Math.sqrt(x*x+y*y);
			Particle particle = new Particle(1000, y*(mag/70), -x*(mag/70), (int)(center_x+x), (int)(center_y+y));

			particles.add(particle);
		}
		return particles;
	}
}
