package extractAudio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class MediaPlayer implements Runnable
  	{
	static AdvancedPlayer player;
  
	public static AdvancedPlayer Player() throws FileNotFoundException, JavaLayerException
	{
		InputStream in = new BufferedInputStream(
				new FileInputStream("extract.mp3")
		);
		player = new AdvancedPlayer(in);
		return player;
	}

	public void setPlayer(AdvancedPlayer player)
	{
		MediaPlayer.player = player;
	}
  
	public synchronized void run()
	{
		Window.tab_button[3].setEnabled(true);
		try
		{
			Player();
			player.play();
		}
		catch (JavaLayerException e1)
		{
			e1.printStackTrace();
		}
		catch (FileNotFoundException e2)
		{
			e2.printStackTrace();
		}
	}
}
