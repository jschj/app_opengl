package FileTest;

import java.io.*;
import android.graphics.*;
import android.util.Log;



public class FileTest
{
    public static void openFile(String path)
    {
        File f;
        Bitmap bmp;

        f = new File(path);
        bmp = BitmapFactory.decodeFile(path);

        Log.w("File", bmp.getWidth() + "   " + bmp.getHeight());
    }
}
