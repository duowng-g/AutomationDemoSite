package Common;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordVideo {
    //---Record with ATU library---
    public static ATUTestRecorder recorder;
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    static Date date = new Date();
    public static void startRecord(String videoName) throws ATUTestRecorderException {
        recorder = new ATUTestRecorder("./test-recordings/",videoName + "_" + dateFormat.format(date), false);
        recorder.start();
    }
    public static void stopRecord() throws ATUTestRecorderException {
        recorder.stop();
    }
}
