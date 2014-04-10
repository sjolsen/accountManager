package accountManager.view.ui;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import accountManager.view.View;

/**
 * A basic window element with the functionality specific to all windows of the program
 */
@SuppressWarnings ("serial")
public abstract class Window extends JFrame
{
	protected static final int BORDER = 3;

	protected View view;

	public Window (String title, View view)
	{
		super (title);
		this.view = view;
		setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener (new NullWindowListener () {
			@Override
			public void windowClosing (WindowEvent e)
			{
				close ();
			}
		});
	}

	public void close ()
	{
		view.close (this);
	}

	public void cleanup ()
	{
		setVisible (false);
		dispose ();
	}
}
