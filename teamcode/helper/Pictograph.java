package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class Pictograph {

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    private RelicRecoveryVuMark mark;

    public Pictograph(HardwareMap hardwareMap){
        int cameraId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName());

        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraId);
        params.vuforiaLicenseKey = Constant.key;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(params);

        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        mark = RelicRecoveryVuMark.UNKNOWN;
    }

    public void activate(){
        relicTrackables.activate();
    }

    public void deactivate(){
        relicTrackables.deactivate();
    }

    public void scan(){
        this.mark = RelicRecoveryVuMark.from(relicTemplate);
    }

    public RelicRecoveryVuMark getMark(){
        return mark;
    }
}
