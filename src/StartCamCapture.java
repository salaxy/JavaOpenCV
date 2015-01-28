import javax.swing.JFrame;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public class StartCamCapture {
	public static void main(String arg[]) {
		// Load the native library.
		System.loadLibrary("opencv_java2410");
		String window_name = "Capture - Face detection";
		JFrame frame = new JFrame(window_name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		processor my_processor = new processor();
		MyPanel my_panel = new MyPanel();
		frame.setContentPane(my_panel);
		frame.setVisible(true);
		// -- 2. Read the video stream
		Mat webcam_image = new Mat();
		VideoCapture capture = new VideoCapture(0);
		if (capture.isOpened()) {
			while (true) {
				capture.read(webcam_image);
				if (!webcam_image.empty()) {
					frame.setSize(webcam_image.width() + 40, webcam_image.height() + 60);
					// -- 3. Apply the classifier to the captured image
					webcam_image = my_processor.detect(webcam_image);
					
					
					// -- 4. Display the image
					my_panel.MatToBufferedImage(webcam_image); // We could look
					// at the
					// error...
					my_panel.repaint();
				} else {
					System.out.println(" --(!) No captured frame -- Break!");
					break;
				}
			}
		}
		return;
	}
}