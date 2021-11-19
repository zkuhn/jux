package com.fusionent.assistant;


import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;


public class WebcamGrabber implements Runnable {
    //final int INTERVAL=1000;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    public WebcamGrabber() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }
    @Override
    public void run() {

        //FrameGrabber grabber = new VideoInputFrameGrabber(0); 
        //
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        int i=0;
        try {
            grabber.start();
            Mat img;
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
            System.out.println("Caught Exception:");
        	System.out.println(e);
        }
    }
}
