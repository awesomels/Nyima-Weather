package com.nyimaweather.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * http������ http����ʹ��HttpURLConnection��HttpClient
 * 
 * @author Administrator
 * @date 2015.03.10
 * @version V1.0
 */
public class HttpUtils {

	/**
	 * ��ȡ����ͼƬ
	 * 
	 * @param urlString
	 *            �磺http://f.hiphotos.baidu.com/image/w%3D2048/sign=3
	 *            b06d28fc91349547e1eef6462769358
	 *            /d000baa1cd11728b22c9e62ccafcc3cec2fd2cd3.jpg
	 * @return
	 * @date 2015.03.10
	 */
	public static Bitmap getNetWorkBitmap(String urlString) {
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(urlString);
			// ʹ��HttpURLConnection������
			HttpURLConnection urlConn = (HttpURLConnection) imgUrl
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.connect();
			// ���õ�������ת����InputStream
			InputStream is = urlConn.getInputStream();
			// ��InputStreamת����Bitmap
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("[getNetWorkBitmap->]MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[getNetWorkBitmap->]IOException");
			e.printStackTrace();
		}
		return bitmap;
	}
}
