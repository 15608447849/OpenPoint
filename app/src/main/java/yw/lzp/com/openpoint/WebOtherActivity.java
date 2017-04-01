package yw.lzp.com.openpoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WebOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_other);
    }

    public void backOnActivity(View view){
        setResult(RESULT_OK,null);
        finish();
    }
}
