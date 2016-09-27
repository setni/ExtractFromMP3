package extractAudio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;

public class Methods
{
	static JFileChooser 		MP3Choose = new JFileChooser();
	static String 				MP3Name;
	static String 				path;
	static BufferedInputStream 	fis;
	static BufferedOutputStream fos;
	static int 					musicLength;
	static MP3AudioHeader 		selection;
	static File 				MP3file;
	static long 				MP3Length;
	static int 					key;
	static int 					instanciation;
	static int 					second;
  
	public static String JChooser() throws IOException, InvalidAudioFrameException
	{
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"mp3", new String[] { "mp3" }
		);
		MP3Choose.setFileFilter(filter);
		int returnVal = MP3Choose.showOpenDialog(null);
		if (returnVal == 0)
		{
			MP3Name = MP3Choose.getSelectedFile().getName();
			path 	= MP3Choose.getSelectedFile().getPath();
		}
		Window.morceau.setText(MP3Name);
		MP3file 	= new File(path);
		selection 	= new MP3AudioHeader(MP3file);
		musicLength = selection.getTrackLength();
		Window.rule.setMaximum(musicLength);
		return path;
	}
  
	public static int getmusicLength()
	{
		return musicLength;
	}
  
	public void setmusicLength(int musicLength)
	{
		Methods.musicLength = musicLength;
	}
  
	public static int getSecond()
	{
		second = Integer.parseInt(
				Window.inputTime.getText()
		);
		int minute = second / 60;
		Window.countSecond.setText(minute + "m " + (second - minute * 60) + "s");
		return second;
	}
  
	public static void copy() throws IOException
	{
		MP3Length = MP3file.length();
		fis = new BufferedInputStream(
				new FileInputStream(
						new File(path)
				)
		);
		fos = new BufferedOutputStream(
				new FileOutputStream(
						new File("extract.mp3")
				)
		);
		key = (int)MP3Length / musicLength;
		second = Integer.parseInt(
				Window.inputTime.getText()
		);
    
		instanciation = second;
    
		byte[] buf = new byte[key];
		for (int n = 0; n <= musicLength; n++)
		{
			fis.read(buf);
			if ((n > instanciation) && (n < instanciation + 30)) {
				fos.write(buf);
			}
		}
	}
}
