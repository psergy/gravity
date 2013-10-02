package gravity.kernel;

import gravity.model.Particle;
import gravity.ui.DisplayComponent;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Processor extends TimerTask {
	private final int THREADS_NUM = 5;

	private static Processor instance = new Processor();

	private List<Particle> particles = new LinkedList<Particle>();

	private int num_particles;

	private DisplayComponent display;

	private Processor() {
	}

	public List<Particle> getParticles() {
		LinkedList<Particle> list = new LinkedList<Particle>(particles);
		return list;
	}

	public void setDisplay(DisplayComponent display) {
		this.display = display;
	}

	public static Processor getInstance() {
		return instance;
	}

	public void addParticle(Particle particle) {
		particles.add(particle);
	}

	public void addParticles(List<Particle> particles) {
		this.particles.addAll(particles);
	}

	public void start() {
		Timer timer = new Timer();
		timer.schedule(this, 0, 50);
	}

	private List<Particle> calculate(int start, int end) {
		List<Particle> new_particles = new LinkedList<Particle>();
		for (Particle particle : particles) {
			double acceleration_x_sum = 0;
			double acceleration_y_sum = 0;

			for (Particle other_particle : particles) {
				if (!(particle == other_particle) && !particle.isCollided()
						&& !other_particle.isCollided()) {
					double x_diff = other_particle.getPos_x()
							- particle.getPos_x();
					double y_diff = other_particle.getPos_y()
							- particle.getPos_y();
					double displacement_magnitude = Math.sqrt(x_diff * x_diff
							+ y_diff * y_diff);

					if (displacement_magnitude < particle.getRadius() / 1.5
							+ other_particle.getRadius() / 1.5) {
						particle.setCollided(true);
						other_particle.setCollided(true);

						double sum_mass = particle.getMass()
								+ other_particle.getMass();
						Particle new_particle = new Particle(
								particle.getMass() + other_particle.getMass(),
								(particle.getVel_x() * particle.getMass() + other_particle
										.getVel_x() * other_particle.getMass())
										/ sum_mass,
								(particle.getVel_y() * particle.getMass() + other_particle
										.getVel_y() * other_particle.getMass())
										/ sum_mass,
								(particle.getPos_x() * particle.getMass() + other_particle
										.getPos_x() * other_particle.getMass())
										/ (int) sum_mass,
								(particle.getPos_y() * particle.getMass() + other_particle
										.getPos_y() * other_particle.getMass())
										/ (int) sum_mass);
						new_particles.add(new_particle);
					}

					double acceleration = other_particle.getMass()
							/ (displacement_magnitude * displacement_magnitude);
					acceleration_x_sum += acceleration
							* (x_diff / displacement_magnitude);
					acceleration_y_sum += acceleration
							* (y_diff / displacement_magnitude);
				}
			}
			particle.setAcc_x(acceleration_x_sum);
			particle.setAcc_y(acceleration_y_sum);
		}
		List<Particle> collided = new LinkedList<Particle>();

		for (Particle particle : particles) {
			particle.incVel_x();
			particle.incVel_y();
			particle.incPos_x();
			particle.incPos_y();
			if (particle.isCollided()) {
				collided.add(particle);
			}
		}

		particles.removeAll(collided);
		particles.addAll(new_particles);

		return particles;
	}

	@Override
	public void run() {
		calculate(0, 0);
		display.setParticles(new LinkedList<Particle>(particles));
		display.repaint();

	}

}
