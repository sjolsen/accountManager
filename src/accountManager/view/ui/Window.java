package accountManager.view.ui;

import javax.swing.JFrame;

import accountManager.view.View;

@SuppressWarnings ("serial")
public abstract class Window extends JFrame
{
	protected View view;

	public Window (String title, View view)
	{
		super (title);
		this.view = view;
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
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
