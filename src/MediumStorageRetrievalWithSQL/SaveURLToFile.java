/*
 * Takes an image from a URL resource and stores it as a file
 * For this example we store it as four different filetypes
 */
package MediumStorageRetrievalWithSQL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Kingsley Oteng-Amoako
 * @date Feburary 9th, 2018
 */
public class SaveURLToFile {
    
    private                         String fileAddress;
    private                         URL resourceNetworkAddressName;
    private                         BufferedImage bufferedImage;
    
    
    //constructor
    SaveURLToFile()
        {
            this.bufferedImage = null;
            this.fileAddress = null;
            this.bufferedImage = null;
        };
    
    //constructor
    SaveURLToFile(String _resAddress) throws MalformedURLException
        {
            this.setResourceAddress(_resAddress);
        };
    
    
    // ** 
    // Media Handling
    // **
    private  void setMedia(String _mediaAddress) {
        this.fileAddress = _mediaAddress;
    };
    
    /**
     *
     * @return String::fileAddress
     */
    private String getMedia(){
        return this.fileAddress;
    };
    
    // ** 
    // Resource Address Handling
    // **
    private  void setResourceAddress(String _resAddress) throws MalformedURLException 
        {
            this.resourceNetworkAddressName = new URL(_resAddress);
        };
    
    /**
     *
     * @return String::resourceNetworkAddressName
     */
    private URL getResourceAddress()
        {
            return this.resourceNetworkAddressName;
        };
    
    // ** 
    // Media Handling
    // **
    private  void setBufferedImage(BufferedImage _mediaAddress) 
        {
            this.bufferedImage = _mediaAddress;
        };
    
    /**
     *
     * @return String::fileAddress
     */
    private BufferedImage getBufferedImage()
        {
            return this.bufferedImage;
        };
    
    public static void main( String[] args )
    {
        SaveURLToFile RSU = new SaveURLToFile();
        
        // try-throw-catch
        try {

            // Create the URL resource locator
            RSU.setResourceAddress(" http://vision.csd.uwo.ca/wiki/vision/upload/thumb/9/97/Gallery_2007_00.jpg/800px-Gallery_2007_00.jpg");
   
            // Capture the image
            RSU.setBufferedImage(ImageIO.read(RSU.getResourceAddress()));
            
            // Set the media handle
            RSU.setMedia("C:/temp/out_place_file");

            // Store a representation of the image as a Joint Photographic Exports Group Interchange format file
            ImageIO.write(RSU.getBufferedImage(), 
                    "jpg",
                    new File(RSU.getMedia()+".jpg"));
            
            // Store a represntation of the iamge as a Graphics Interchange format file
            ImageIO.write(RSU.getBufferedImage(), 
                    "gif",
                    new File(RSU.getMedia()+".gif"));
            
            // Store a representatoin of the image as a Portable NetWork Graphics format file
            ImageIO.write(RSU.getBufferedImage(), 
                    "png",
                    new File(RSU.getMedia()+".png"));
            
            // Store a representation of the image as a  Windows Bitmap format file
            ImageIO.write(RSU.getBufferedImage(), 
                    "bmp",
                    new File(RSU.getMedia()+".bmp"));

        } 
            catch (IOException e) 
        {
            e.printStackTrace();
        }
            System.out.println("File stored to local drive.");
    }
};

