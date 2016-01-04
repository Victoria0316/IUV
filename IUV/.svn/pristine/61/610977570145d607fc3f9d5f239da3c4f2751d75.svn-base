package bluemobi.iuv.base.crop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;

import bluemobi.iuv.R;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.view.LoadingPage;

/**
 * Created by wangzhijun on 2015/7/2.
 */
public class TestCropActivity extends BaseActivity {
    private ImageView resultView;
    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcrop);
        resultView = (ImageView) findViewById(R.id.result_image);
    }

    @Override
    protected void initBase() {

    }

    @Override
    protected void successViewCompleted(View successView) {

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        } else if (requestCode == Crop.REQUEST_CAMERA && resultCode == RESULT_OK) {
            beginCrop(Crop.outputFileUri);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            resultView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
