/*
 * Takes an image from a URL resource and stores it as a file
 * For this example we store it as four different filetypes
 */
package MediumStorageRetrievalWithSQL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Kingsley Oteng-Amoako
 * @date Feburary 8th, 2018
 */
public class SaveURLToFile {
    
        


    public static void main( String[] args )
    {
    	BufferedImage image = null;
        
        // try-throw-catch
        try {

            // Create the URL resource locator
            URL url 
                    = new URL(" http://vision.csd.uwo.ca/wiki/vision/upload/thumb/9/97/Gallery_2007_00.jpg/800px-Gallery_2007_00.jpg");
            
            // Capture the image
            image 
                    = ImageIO.read(url);

            // Store a representation of the image as a Joint Photographic Exports Group Interchange format file
            ImageIO.write(image, 
                    "jpg",
                    new File("C:/temp/out.jpg"));
            
            // Store a represntation of the iamge as a Graphics Interchange format file
            ImageIO.write(image, 
                    "gif",
                    new File("C:/temp/out.gif"));
            
            // Store a representatoin of the image as a Portable NetWork Graphics format file
            ImageIO.write(image, 
                    "png",
                    new File("C:/temp/out.png"));
            
            // Store a representation of the image as a  Windows Bitmap format file
            ImageIO.write(image, 
                    "bmp",
                    new File("C:/temp/out.bmp"));

        } 
            catch (IOException e) 
        {
            e.printStackTrace();
        }
            System.out.println("File stored to local drive.");
    }
};

