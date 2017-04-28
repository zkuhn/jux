package com.fusionent.assistant;


import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;


public class WebcamGrabber implements Runnable {
    //final int INTERVAL=1000;///you may use interval
    IplImage image;
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    public WebcamGrabber() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void run() {
        FrameGrabber grabber = new VideoInputFrameGrabber(0); 
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        int i=0;
        try {
            grabber.start();
            IplImage img;
            Frame frame;
            while (true) {
                frame = grabber.grab();
                img = converter.convert(frame);
                if (img != null) {
                	//don't save a bunch of video just yet.. too big!! and unwieldy!
                    //cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                    //cvSaveImage((i++)+"-capture.jpg", img);
                    // show image on window
                    canvas.showImage(frame);
                }
                 //Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
}
