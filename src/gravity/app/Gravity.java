package gravity.app;

import gravity.kernel.Processor;
import gravity.kernel.Util;
import gravity.ui.DisplayComponent;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Gravity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
        {
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName () );
        }
        catch ( Throwable e )
        {
            //
        }

        final JFrame f = new JFrame ();
        f.setSize(500, 400);
        Color bg = new Color ( 245, 246, 247 );

        f.getRootPane ().setOpaque ( true );
        f.getRootPane ().setBackground ( bg );
        f.getRootPane ().setBorder ( BorderFactory.createEmptyBorder ( 10, 10, 10, 10 ) );
        
        f.getContentPane ().setBackground ( bg );
        f.getContentPane ().setLayout ( new BorderLayout ( 5, 5 ) );
        
        DisplayComponent component = new DisplayComponent();
        
        f.add(component);
        f.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        //f.pack ();
        f.setLocationRelativeTo ( null );
        f.setVisible ( true );
        
        Processor.getInstance().setDisplay(component);
        Processor.getInstance().addParticles(Util.generate_proto(100, 100));
        Processor.getInstance().addParticles(Util.generate_proto(f.getWidth() / 2, f.getHeight() / 2));
        
        Processor.getInstance().start();

	}

}
