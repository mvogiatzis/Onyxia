package onyxia.game;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 * Class SoundFilePlayer - A sound class in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * This class uses a SourceDataLine for streaming data, such as a 
 * long sound file that won't all fit in memory at once. Each sound is a seperated
 * running thread.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 * 
 */
public class SoundFilePlayer extends Thread {
    
    /**
     * The URL of the file
     */
    private URL url;
    
    /*
     * The AudioInputStream taken from the url
     */
    private AudioInputStream in;
    
    /**
     * SourceDataLine object line that contains the data
     */
    private SourceDataLine line;
    
    /**
     * The size of the frame
     */
    private int frameSize;
    
    /**
     * The buffer to store the data that line has read
     */
    private byte[] buffer = new byte [32 * 1024]; // 32k is arbitrary
    
    /**
     * boolean to indicate if the sound is playing or not
     */
    private boolean playing;
    
    /**
     * boolean that indicates if "playing" has reached the end of file
     */
    private boolean notYetEOF;
    
    /**
     * Used to show the point of reading at an exact moment
     */
    private int readPoint;
    
    /**
     * Integer to show how many bytes have been read
     */
    private int bytesRead;
    
    /**
     * Integer to show how many frames we get
     */
    private int frames;
    
    /**
    * Integer that shows how many bytes are left over to read 
    */
    private int leftover;

    public SoundFilePlayer (URL urlOfFile)
            throws IOException,
                    UnsupportedAudioFileException,
                    LineUnavailableException {
            url = urlOfFile;
            in = AudioSystem.getAudioInputStream(url);
            AudioFormat format = in.getFormat();
            AudioFormat.Encoding formatEncoding = format.getEncoding();
            //If the format encoding is not proper then audio file is unsupported
            //and Exception is thrown
            if (! (formatEncoding.equals (AudioFormat.Encoding.PCM_SIGNED) ||
                       formatEncoding.equals (AudioFormat.Encoding.PCM_UNSIGNED))) 
               throw new UnsupportedAudioFileException (
                          " Not PCM audio");
       
       frameSize = format.getFrameSize(); 
       DataLine.Info info =
               new DataLine.Info (SourceDataLine.class, format); 

       line = (SourceDataLine) AudioSystem.getLine(info); 
     
       line.open(); 


       playing = false;     //starts as not playing
       notYetEOF = true;        //hasnt reached EOF yet

    }
    @Override
    public void run() {
        readPoint = 0;
        bytesRead = 0;



        while (notYetEOF) {
            if (playing)
            {
                try {
                    bytesRead = in.read (buffer, readPoint, buffer.length - readPoint);
                } catch (IOException ex) {
                    Logger.getLogger(SoundFilePlayer.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (bytesRead == -1) {
                    notYetEOF = false;
                    //System.out.println ("reached eof");
                    break;
                    }
                    // how many frames did we get,
                    frames = bytesRead / frameSize;
                    leftover = bytesRead % frameSize;
                    // send to line
                    line.write (buffer, readPoint, bytesRead-leftover);
                    // save the leftover bytes
                    System.arraycopy (buffer, bytesRead,
                                      buffer, 0, 
                                      leftover); 
                    readPoint = leftover;
            }
            else
            {
                //if not playing, sleep
            try { Thread.sleep (5);} 
                    catch (InterruptedException ie) {}
                    }



        } // while notYetEOF

            line.drain();
            line.stop();
            line.close();
    }


    /**
     * Starts playing the current Sound thread. If the sound is
     * already playing do nothing.
     */
    public void startPlaying() {
        if(playing)
            return;

        playing=true;
        if (!isAlive() && this.getState()==Thread.State.NEW)
            start();
        line.start();
        if (this.getState()==Thread.State.TERMINATED)
            {}//loop if you want

    }
    
    /**
     * Stops the playing of the sound
     */
    public void stopPlaying() {
            playing = false;
            line.stop();
    }


    /**
     * Get the SourceDataLine of the Sound.
     * Usually used to add a listener to the line
     * @return SourceDataLine The line of the sound
     */
    public SourceDataLine getLine() {
            return line;
    }
    
    /**
     * Get the URL of the sound
     * @return URL The URL object of the sound
     */
    public URL getURL() {		
            return url; 
    }
    
    /**
     * Get if the "playing" has reached End of File or not.
     * @return boolean True if it has reached EOF, false otherwise.
     */
    public boolean reachedEOF()
    {
        return (!notYetEOF);
    }


}

