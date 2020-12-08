package solution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
	public static final int BITMAP_HEADER_SIZE = 14;
	public static final int BYTES_OF_INT = 4;
	public static final int BYTES_OF_SHORT = 2;
	public static final int BITS_OF_BYTE = 8;

	public static void main(String args[]) {
		try {
			solution("32bit.bmp", "out.bmp");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	public static void solution(String originalImgPath, String convertedImgPath) throws IOException {
		FileInputStream fis = new FileInputStream(originalImgPath);
		FileOutputStream fos = new FileOutputStream(convertedImgPath);

		// Đọc ghi bitmap header
		byte[] temp = new byte[BITMAP_HEADER_SIZE];
		fis.read(temp);
		fos.write(temp);

		int readBitCountOfInfoHeader = 0;
		int infoHeaderSize = readAndWriteInt(fis, fos);
		readBitCountOfInfoHeader += BYTES_OF_INT;

		int widthOfOriginalImgInPixel = readAndWriteInt(fis, fos);
		readBitCountOfInfoHeader += BYTES_OF_INT;

		int heightOfOriginalImgInPixel = readAndWriteInt(fis, fos);
		readBitCountOfInfoHeader += BYTES_OF_INT;

		short planeCount = readAndWriteShort(fis, fos);
		readBitCountOfInfoHeader += BYTES_OF_SHORT;

		short bitCountPerPixel = readAndWriteShort(fis, fos);
		readBitCountOfInfoHeader += BYTES_OF_SHORT;
		
		// Đọc và ghi các byte còn lại của InfoHeader
		byte[] restByteOfInfoHeader = new byte[infoHeaderSize - readBitCountOfInfoHeader];
		fis.read(restByteOfInfoHeader);
		fos.write(restByteOfInfoHeader);

		switch (bitCountPerPixel) {
			case 32:
				//Vì số bit mỗi pixel > 8 nên sẽ không có Color Table nên sẽ xử lý luôn Pixel Data
				for (int i = 0; i < widthOfOriginalImgInPixel * heightOfOriginalImgInPixel; i++) {
					//Số bit mỗi pixel = 32 bit -> BGRA mỗi màu 8 bit
					int b = fis.read();
					int g = fis.read();
					int r = fis.read();
					int a = fis.read();
	
					if (r > g && r > b) {
						int tempColor = r;
						r = (g + b) / 2;
						b = tempColor;
					}
	
					fos.write(b);
					fos.write(g);
					fos.write(r);
					fos.write(a);
				}
				break;
	
			case 24:
				int padding = (4 - ((widthOfOriginalImgInPixel * bitCountPerPixel / BITS_OF_BYTE) % 4)) % 4;
				for (int i = 0; i < heightOfOriginalImgInPixel; i++) {
					for (int j = 0; j < widthOfOriginalImgInPixel; j++) {
						//Số bit mỗi pixel = 24 bit -> B G R(thứ tự màu trong ảnh bitmap 
						//khác với thứ tự màu trên màn hình) mỗi màu 8 bit
						int b = fis.read();
						int g = fis.read();
						int r = fis.read();
		
						if (r > g && r > b) {
							int tempColor = r;
							r = (g + b) / 2;
							b = tempColor;
						}
		
						fos.write(b);
						fos.write(g);
						fos.write(r);
					}
					
					for (int k = 0; k < padding; k++) {
						fis.read();
					}
				}
				break;
	
			case 16:
				break;
	
			case 8:
				break;
	
			case 4:
				break;
	
			case 1:
				break;
		}

		fos.close();
		fis.close();
	}

	public static int readAndWriteInt(FileInputStream fis, FileOutputStream fos) throws IOException {
		int b1 = fis.read();
		fos.write(b1);
		
		int b2 = fis.read();
		fos.write(b2);
		
		int b3 = fis.read();
		fos.write(b3);
		
		int b4 = fis.read();
		fos.write(b4);

		return (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
	}

	public static short readAndWriteShort(FileInputStream fis, FileOutputStream fos) throws IOException {
		int b1 = fis.read();
		fos.write(b1);
		
		int b2 = fis.read();
		fos.write(b2);
		
		return (short) ((b2 << 8) | b1);
	}
}