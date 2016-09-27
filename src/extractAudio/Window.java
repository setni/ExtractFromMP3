package extractAudio;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;

public class Window extends JWindow implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
	{
		Window window = new Window();
	}
	
	private JPanel 				container 	= new JPanel();
	private Dimension 			dimButton 	= new Dimension(100, 30);
	
	static String[] 			tab_string	= { "Choisir", "Lecture", "Valider", "stop", "exit" };
	static JButton[] 			tab_button	= new JButton[tab_string.length];
	static JLabel 				morceau 	= new JLabel("Ici le nom du morceau");
	static JLabel 				countSecond;
	static JSlider 				rule 		= new JSlider(0, 0, 0, 0);
	static JFormattedTextField 	inputTime;
	
	int 						musicLength;
	Thread 						playerThread;
	JLabel 						musicName 	= new JLabel("Choisir un MP3");
	
	public Window()
	{
		setSize(400, 300);
		setLocationRelativeTo(null);
		initComposant();
		tab_button[0].addActionListener(this);
		tab_button[1].addActionListener(this);
		tab_button[2].addActionListener(this);
		tab_button[3].addActionListener(this);
		tab_button[4].addActionListener(this);
		inputTime.addActionListener(this);
		rule.addChangeListener(new ChangeListener()
		{
		
			public void stateChanged(ChangeEvent event)
			{
				Window.inputTime.setValue(Integer.valueOf(
						(
								(JSlider)event.getSource()
						).getValue()
				));
				Methods.getSecond();
			}
		});
		setContentPane(this.container);
		setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == tab_button[0]) {
			try
			{
				Methods.JChooser();
			}
			catch (IOException|InvalidAudioFrameException e)
			{
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == tab_button[1])
		{
			try
			{
				Methods.copy();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			try
			{
				/**
				 *   @deprecated 
				 */
				this.playerThread.stop();
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
			}
			this.playerThread = new Thread(new MediaPlayer());
			playerThread.start();
		}
		if (arg0.getSource() == tab_button[3])
		{
			/**
			 *   @deprecated 
			 */
			this.playerThread.stop();
			this.playerThread = null;
		}
		if (arg0.getSource() == tab_button[2])
		{
			try
			{
				Methods.copy();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.exit(1);
		}
		if (arg0.getSource() == tab_button[4]) {
			System.exit(1);
		}
	}
	
	private void initComposant()
	{
		Font police = new Font("Arial", 1, 20);
		JPanel header = new JPanel();
		
		header.setPreferredSize(new Dimension(getWidth(), 220));
		this.container.add(header, "North");
		
		tab_button[4] = new JButton(tab_string[4]);
		tab_button[4].setHorizontalAlignment(4);
		tab_button[4].setHorizontalAlignment(0);
		tab_button[4].setPreferredSize(new Dimension(50, 50));
		header.add(tab_button[4]);
		
		this.musicName.setFont(police);
		this.musicName.setHorizontalAlignment(2);
		this.musicName.setPreferredSize(new Dimension(200, 50));
		header.add(this.musicName);
		
		tab_button[0] = new JButton(tab_string[0]);
		tab_button[0].setHorizontalAlignment(4);
		tab_button[0].setHorizontalAlignment(0);
		tab_button[0].setPreferredSize(this.dimButton);
		header.add(tab_button[0]);
		
		morceau.setFont(police);
		morceau.setHorizontalAlignment(0);
		morceau.setPreferredSize(new Dimension(400, 50));
		header.add(morceau);
		
		rule.setPaintTicks(true);
		rule.setPaintLabels(true);
		rule.setMajorTickSpacing(30);
		rule.setMinorTickSpacing(10);
		rule.setBounds(20, 95, 290, 40);
		rule.setPreferredSize(new Dimension(320, 60));
		header.add(rule);
		
		inputTime = new JFormattedTextField(Integer.valueOf(0));
		inputTime.setPreferredSize(new Dimension(60, 40));
		header.add(inputTime);
		
		countSecond = new JLabel("0 s");
		countSecond.setHorizontalAlignment(2);
		countSecond.setPreferredSize(new Dimension(60, 40));
		header.add(countSecond);
		
		tab_button[1] = new JButton(tab_string[1]);
		tab_button[1].setHorizontalAlignment(2);
		tab_button[1].setHorizontalAlignment(0);
		tab_button[1].setPreferredSize(this.dimButton);
		header.add(tab_button[1]);
		
		tab_button[3] = new JButton(tab_string[3]);
		tab_button[3].setHorizontalAlignment(2);
		tab_button[3].setHorizontalAlignment(0);
		tab_button[3].setPreferredSize(this.dimButton);
		tab_button[3].setEnabled(false);
		header.add(tab_button[3]);
		
		tab_button[2] = new JButton(tab_string[2]);
		tab_button[2].setHorizontalAlignment(0);
		tab_button[2].setHorizontalAlignment(0);
		tab_button[2].setPreferredSize(new Dimension(400, 45));
		this.container.add("South", tab_button[2]);
	}
}
