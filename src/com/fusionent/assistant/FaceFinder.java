package com.fusionent.assistant;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import org.bytedeco.opencv.opencv_core.Mat;

import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;

import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.opencv.core.Core;
import org.opencv.core.MatOfRect;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

//import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

public class FaceFinder {

    public void fetchFile() {
        //String path = "\\\\KUHN-NAS\\home_files\\pics_and_vids\\2012\\September 2012";
        //File f = new File (path);
/*
        File f = this.chooseFile();
        System.out.println("You chose to open this file: " +
            f.getName());
        Mat image = imread(f.getAbsolutePath());
*/        
        String path = "C:\\Users\\zakuhn\\Documents\\IMG_0013.JPG";
        String path2 = "C:\\Users\\zakuhn\\Documents\\IMG_0014.JPG";
        String path3 = "C:\\Users\\zakuhn\\Documents\\IMG_0015.JPG";
     
        faceImage(path);
        faceImage(path2);
        faceImage(path3);
        
    }
        
    public void faceImage(String path){
        
    
        Mat image = imread(path);
        
        
        
        String resolution = image.cols() + " "+ image.rows();
        
        //Image showImage = this.Mat2BufferedImage(image);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        
       
        
        RectVector faceDetections  = this.findFace(image);
        
        org.opencv.core.Mat img =  new org.opencv.core.Mat(image.address());
        for(Rect re : faceDetections.get()){
            System.out.println("Found face at:" + re.x() + "," + re.y() + " w:" + re.width() + ", h:" + re.height());
            Imgproc.rectangle(
                    img, 
                    new Point(re.x(), re.y()), new Point(re.x() + re.width(), re.y() + re.height()),
                    new Scalar(0, 0, 255), 2);
        }
        //note the pointer indirection between img and image
        Frame showImage = converter.convert(image);
        
        //CanvasFrame frame = new CanvasFrame("Some Title", CanvasFrame.getDefaultGamma()/grabber.getGamma());
        CanvasFrame frame = new CanvasFrame("Some Title:" + resolution);
       
        frame.showImage(showImage);
        

    }
    
    public RectVector findFace(Mat image) {
        //note... unpacked jar file from maven, added it to target/data, and added path to system PATH variable
        String javaLibPath = System.getProperty("java.library.path");
        System.out.println("java lib path:" + javaLibPath);
        System.out.println("loading library" + Core.NATIVE_LIBRARY_NAME);
        try{
            //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.loadLibrary("opencv_java");
        }catch (Exception e) {
            System.out.println("failure with default system path");
        }catch(java.lang.UnsatisfiedLinkError le){
            System.out.println("failure catching lib error");
            throw le;
        }
        /*
        String path="C:\\Users\\zakuhn\\workspace\\jux\\target\\native\\org\\bytedeco\\opencv\\windows-x86_64\\";
        try{
           
            System.load(path + "opencv_java.dll");// Core.NATIVE_LIBRARY_NAME + ".dll");
        }catch (Exception e) {
            System.out.println("failure with path" + path);
            throw e;
        }*/
        System.out.println("getting resource");
        //Create object
        String classifierFile = "haarcascade_frontalface_default.xml";
        //
        //String x = FaceFinder.class.getResource("haarcascade_frontalface_default.xml").getPath();
        //System.out.println(x);
        //note: downloaded this to pathe here from internet
        String classifierPath = "C:\\Users\\zakuhn\\workspace\\jux\\data\\";
        CascadeClassifier faceDetector = new CascadeClassifier(classifierPath + classifierFile );
        
        RectVector faceDetections = new RectVector();
        //Result list
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println("faces detected");
        return faceDetections;
       
    }
    
    /*
    public BufferedImage Mat2BufferedImage(Mat m) {
        // Fastest code
        // output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;
    }
    */
    public File chooseFile(){
        JFileChooser chooser = new JFileChooser();
        
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        }
        return chooser.getSelectedFile();
    }
}
