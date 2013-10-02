package gravity.ui;

import gravity.model.Particle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

public class DisplayComponent extends JComponent {
	private List<Particle> particles = new LinkedList<Particle>();
	public DisplayComponent() {
	}
	
	protected void paintComponent(Graphics g) {
		BufferedImage bi = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(Color.BLACK);
		for(Particle p : particles) {
			Ellipse2D.Double point = new Ellipse2D.Double(p.getPos_x(), p.getPos_y(), p.getRadius(), p.getRadius());
			g2d.fill(point);
			g2d.draw(point);
			
		}
		
		g2d.dispose();
		g.drawImage(bi, 0, 0, null);
	}

	public void setParticles(LinkedList<Particle> particles) {
		this.particles = particles;
		
	}
}
