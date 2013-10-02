package gravity.model;

public class Particle {
	private final double h = 1/200.0;
	
	private double pos_x;
	private double pos_y;
	private double prior_pos_x;
	private double prior_pos_y;
	private double vel_x;
	private double vel_y;
	private double acc_x;
	private double acc_y;
	private int mass;
	private int color;
	private boolean collided;
	private double radius;
	
	public Particle(int mass, double vel_x, double vel_y, double pos_x, double pos_y)	{
		this.mass = mass;
		this.vel_x = vel_x;
		this.vel_y = vel_y;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.radius = Math.log(Math.E + mass/1000.0);
	}
	
	public double getPos_x() {
		return pos_x;
	}
	public void setPos_x(double pos_x) {
		this.pos_x = pos_x;
	}
	public double getPos_y() {
		return pos_y;
	}
	public void setPos_y(double pos_y) {
		this.pos_y = pos_y;
	}
	public double getPrior_pos_x() {
		return prior_pos_x;
	}
	public void setPrior_pos_x(double prior_pos_x) {
		this.prior_pos_x = prior_pos_x;
	}
	public double getPrior_pos_y() {
		return prior_pos_y;
	}
	public void setPrior_pos_y(int prior_pos_y) {
		this.prior_pos_y = prior_pos_y;
	}
	public double getVel_x() {
		return vel_x;
	}
	public void setVel_x(double vel_x) {
		this.vel_x = vel_x;
	}
	public double getVel_y() {
		return vel_y;
	}
	public void setVel_y(double vel_y) {
		this.vel_y = vel_y;
	}
	public double getAcc_x() {
		return acc_x;
	}
	public void setAcc_x(double acc_x) {
		this.acc_x = acc_x;
	}
	public double getAcc_y() {
		return acc_y;
	}
	public void setAcc_y(double acc_y) {
		this.acc_y = acc_y;
	}
	public int getMass() {
		return mass;
	}
	public void setMass(int mass) {
		this.mass = mass;
		this.radius = Math.log(Math.E+mass/1000);
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isCollided() {
		return collided;
	}
	public void setCollided(boolean collided) {
		this.collided = collided;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	
	public double incVel_x() {
		vel_x += acc_x * h;
		return vel_x;
	}
	
	public double incVel_y() {
		vel_y += acc_y * h;
		return vel_y;
	}
	
	public double incPos_x() {
		pos_x += vel_x * h;
		return pos_x;
	}
	
	public double incPos_y() {
		pos_y += vel_y * h;
		return pos_y;
	}

}
