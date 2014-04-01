package accountManager.view.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import accountManager.view.View;

@SuppressWarnings ("serial")
public class MainWindow extends Window
{
	private static final int BORDER = 3;
	
	private JPanel panel;
	private AccountSelector selector;

	private JPanel control_area;
	private JPanel button_panel;	
	private JButton save_button;
	private JButton exit_button;

	public MainWindow (View view, String pathname)
	{
		super (String.format ("Account Manager - %s", pathname), view);
		
		panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
		panel.setBorder (BorderFactory.createEmptyBorder (BORDER, BORDER, BORDER, BORDER));
		getContentPane ().add (panel);

		selector = new AccountSelector (view.getAccounts ());
		panel.add (selector.getJComponent ());

		panel.add (Box.createRigidArea (new Dimension (BORDER, 0)));
		
		control_area = new JPanel ();
		control_area.setLayout (new BoxLayout (control_area, BoxLayout.Y_AXIS));
		control_area.add (Box.createVerticalGlue ());
		panel.add (control_area);

		button_panel = new JPanel ();
		button_panel.setLayout (new BoxLayout (button_panel, BoxLayout.X_AXIS));

		save_button = new CallbackButton ("Save") {
			@Override
			public void onClicked ()
			{
				MainWindow.this.view.save ();
			}
		};
		getRootPane ().setDefaultButton (save_button);
		exit_button = new CallbackButton ("Exit") {
			@Override
			public void onClicked ()
			{
				MainWindow.this.view.exit ();
			}
		};
		
		button_panel.add (Box.createHorizontalGlue ());
		button_panel.add (exit_button, BorderLayout.EAST);
		button_panel.add (Box.createRigidArea (new Dimension(BORDER, 0)));
		button_panel.add (save_button, BorderLayout.WEST);
		control_area.add (button_panel, BorderLayout.EAST);

		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener (new NullWindowListener () {
			@Override
			public void windowClosing (WindowEvent e)
			{
				MainWindow.this.view.exit ();
			}
		});
		
		pack ();
		setVisible (true);
	}
}
