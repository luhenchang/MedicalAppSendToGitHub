package com.example.ls.shoppingmall.user.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils
{
	/**
	 * 存放拍摄图片的文件夹
	 */
	private static final String FILES_NAME = "/MyPhoto";
	/**
	 * 获取的时间格式
	 */
	public static final String TIME_STYLE = "yyyyMMddHHmmss";
	/**
	 * 图片种类
	 */
	public static final String IMAGE_TYPE = ".png";

	// 防止实例化
	public static final String ROOT_DIR		= "Android/data/"
													+ UIUtils.getPackageName();
	public static final String DOWNLOAD_DIR	= "download";
	public static final String CACHE_DIR		= "cache";
	public static final String ICON_DIR		= "icon";

	public static final String APP_STORAGE_ROOT = "AndroidNAdaption";

	/** 判断SD卡是否挂载 */
	public static boolean isSDCardAvailable()
	{
		if (Environment.MEDIA_MOUNTED.equals(Environment
														.getExternalStorageState()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/** 获取下载目录 */
	public static String getDownloadDir()
	{
		return getDir(DOWNLOAD_DIR);
	}

	/** 获取缓存目录 */
	public static String getCacheDir()
	{
		return getDir(CACHE_DIR);
	}

	/** 获取icon目录 */
	public static String getIconDir()
	{
		return getDir(ICON_DIR);
	}

	/** 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录 */
	public static String getDir(String name)
	{
		StringBuilder sb = new StringBuilder();
		if (isSDCardAvailable())
		{
			sb.append(getAppExternalStoragePath());
		}
		else
		{
			sb.append(getCachePath());
		}
		sb.append(name);
		sb.append(File.separator);
		String path = sb.toString();
		if (createDirs(path))
		{
			return path;
		}
		else
		{
			return null;
		}
	}

	/** 获取SD下的应用目录 */
	public static String getExternalStoragePath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		sb.append(File.separator);
		sb.append(ROOT_DIR);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取SD下当前APP的目录 */
	public static String getAppExternalStoragePath()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		sb.append(File.separator);
		sb.append(APP_STORAGE_ROOT);
		sb.append(File.separator);
		return sb.toString();
	}

	/** 获取应用的cache目录 */
	public static String getCachePath()
	{
		File f = UIUtils.getContext().getCacheDir();
		if (null == f)
		{
			return null;
		}
		else
		{
			return f.getAbsolutePath() + "/";
		}
	}

	/** 创建文件夹 */
	public static boolean createDirs(String dirPath)
	{
		File file = new File(dirPath);
		if (!file.exists() || !file.isDirectory()) { return  file.mkdirs(); }
		return true;
	}

	/**产生图片的路径，这里是在缓存目录下*/
	public static String generateImgePathInStoragePath(){
		return getDir(ICON_DIR) + String.valueOf(System.currentTimeMillis()) + ".jpg";
	}
	/**
	 * 读取照片旋转角度
	 *
	 * @param path 照片路径
	 * @return 角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	/**
	 * 把原图按1/10的比例压缩
	 *
	 * @param path 原图的路径
	 * @return 压缩后的图片
	 */
	public static Bitmap getCompressPhoto(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = 10;  // 图片的大小设置为原来的十分之一
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		options = null;
		return bmp;
	}
	/**
	 * 处理旋转后的图片
	 * @param originpath 原图路径
	 * @param context 上下文
	 * @return 返回修复完毕后的图片路径
	 */
	public static String amendRotatePhoto(String originpath, Context context) {

		// 取得图片旋转角度
		int angle = readPictureDegree(originpath);

		// 把原图压缩后得到Bitmap对象
		Bitmap bmp = getCompressPhoto(originpath);;

		// 修复图片被旋转的角度
		Bitmap bitmap = rotaingImageView(angle, bmp);

		// 保存修复后的图片并返回保存后的图片路径
		return savePhotoToSD(bitmap, context);
	}
	/**
	 * 保存Bitmap图片在SD卡中
	 * 如果没有SD卡则存在手机中
	 *
	 * @param mbitmap 需要保存的Bitmap图片
	 * @return 保存成功时返回图片的路径，失败时返回null
	 */
	public static String savePhotoToSD(Bitmap mbitmap, Context context) {
		FileOutputStream outStream = null;
		String fileName = getPhotoFileName(context);
		try {
			outStream = new FileOutputStream(fileName);
			// 把数据写入文件，100表示不压缩
			mbitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (outStream != null) {
					// 记得要关闭流！
					outStream.close();
				}
				if (mbitmap != null) {
					mbitmap.recycle();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取手机可存储路径
	 *
	 * @param context 上下文
	 * @return 手机可存储路径
	 */
	private static String getPhoneRootPath(Context context) {
		// 是否有SD卡
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
				|| !Environment.isExternalStorageRemovable()) {
			// 获取SD卡根目录
			return context.getExternalCacheDir().getPath();
		} else {
			// 获取apk包下的缓存路径
			return context.getCacheDir().getPath();
		}
	}
	/**
	 * 使用当前系统时间作为上传图片的名称
	 *
	 * @return 存储的根路径+图片名称
	 */
	public static String getPhotoFileName(Context context) {
		File file = new File(getPhoneRootPath(context) + FILES_NAME);
		// 判断文件是否已经存在，不存在则创建
		if (!file.exists()) {
			file.mkdirs();
		}
		// 设置图片文件名称
		SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE, Locale.getDefault());
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		String photoName = "/" + time + IMAGE_TYPE;
		return file + photoName;
	}

	/**
	 * 发起剪裁图片的请求
	 * @param activity 上下文
	 * @param srcFile 原文件的File
	 * @param output 输出文件的File
	 * @param requestCode 请求码
     */
	public static void startPhotoZoom(Activity activity, File srcFile, File output, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(getImageContentUri(activity,srcFile), "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 380);
		// intent.putExtra("return-data", false);

		//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
		//                Uri.fromFile(new File(FileUtils.picPath)));

		intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		// intent.putExtra("noFaceDetection", true); // no face detection

		activity.startActivityForResult(intent, requestCode);
	}

	/**安卓7.0裁剪根据文件路径获取uri*/
	public static Uri getImageContentUri(Context context, File imageFile) {
		String filePath = imageFile.getAbsolutePath();
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.Media._ID },
				MediaStore.Images.Media.DATA + "=? ",
				new String[] { filePath }, null);

		if (cursor != null && cursor.moveToFirst()) {
			int id = cursor.getInt(cursor
					.getColumnIndex(MediaStore.MediaColumns._ID));
			Uri baseUri = Uri.parse("content://media/external/images/media");
			return Uri.withAppendedPath(baseUri, "" + id);
		} else {
			if (imageFile.exists()) {
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.DATA, filePath);
				return context.getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			} else {
				return null;
			}
		}
	}

	/**
	 * 复制bm
	 * @param bm
	 * @return
     */
	public static String saveBitmap(Bitmap bm) {
		String croppath="";
		try {
			File f = new File(FileUtils.generateImgePathInStoragePath());
			//得到相机图片存到本地的图片
			croppath=f.getPath();
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return croppath;
	}

	/**
	 * 按质量压缩bm
	 * @param bm
	 * @param quality 压缩率
	 * @return
	 */
	public static String saveBitmapByQuality(Bitmap bm, int quality) {
		String croppath="";
		String hah="";
		Bitmap bm1 = null;
		try {
			File f = new File(FileUtils.generateImgePathInStoragePath());
			//得到相机图片存到本地的图片
			croppath=f.getPath();
			hah=f.getPath();
			Log.e("lll",croppath.toString());
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG,quality, out);
			out.flush();
			out.close();
			int degree=getBitmapDegree(croppath);
			bm1=rotaingImageView(degree,bm);
			File f1 = new File(FileUtils.generateImgePathInStoragePath());
			//得到相机图片存到本地的图片
			croppath=f1.getPath();
			Log.e("ll2",croppath.toString());

			if (f1.exists()) {
				f1.delete();
			}
			FileOutputStream out1 = new FileOutputStream(f);
			bm1.compress(Bitmap.CompressFormat.JPEG,quality, out1);
			out1.flush();
			out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hah;
	}
	/**
	 * 旋转图片
	 *
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/**
	 * 获取原始图片的角度（解决三星手机拍照后图片是横着的问题）
	 * @param path 图片的绝对路径
	 * @return 原始图片的角度
	 */
	public static int getBitmapDegree(String path) {
		int degree = 0;
		try {
			// 从指定路径下读取图片，并获取其EXIF信息
			ExifInterface exifInterface = new ExifInterface(path);
			// 获取图片的旋转信息
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			Log.e("jxf", "orientation" + orientation);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
}
