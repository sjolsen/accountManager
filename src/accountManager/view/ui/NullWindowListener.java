package accountManager.view.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * A WindowListener which does nothing, making it easier to create anonymous listener types
 */
public abstract class NullWindowListener implements WindowListener
{
	@Override
	public void windowOpened (WindowEvent e)
	{
	}

	@Override
	public void windowClosing (WindowEvent e)
	{
	}

	@Override
	public void windowClosed (WindowEvent e)
	{
	}

	@Override
	public void windowIconified (WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified (WindowEvent e)
	{
	}

	@Override
	public void windowActivated (WindowEvent e)
	{
	}

	@Override
	public void windowDeactivated (WindowEvent e)
	{
	}
}
