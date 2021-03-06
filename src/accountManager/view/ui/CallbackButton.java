package accountManager.view.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A button which simply performs an action on any event
 */
@SuppressWarnings ("serial")
public abstract class CallbackButton extends JButton implements ActionListener
{
	public CallbackButton (String text)
	{
		super (text);
		setActionCommand ("onClicked");
		addActionListener (this);
	}

	public abstract void onClicked ();

	@Override
	public void actionPerformed (ActionEvent e)
	{
		onClicked ();
	}
}
