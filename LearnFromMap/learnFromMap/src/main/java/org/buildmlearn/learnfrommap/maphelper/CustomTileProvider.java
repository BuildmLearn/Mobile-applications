package org.buildmlearn.learnfrommap.maphelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

public class CustomTileProvider implements TileProvider {

	private static final int TILE_WIDTH = 256;
	private static final int TILE_HEIGHT = 256;
	private static final int BUFFER_SIZE = 16 * 1024;

	private AssetManager mAssets;
	private GoogleMap map;

	public CustomTileProvider(AssetManager assets, GoogleMap map) {
		mAssets = assets;
		this.map = map;
	}

	@Override
	public Tile getTile(int x, int y, int zoom) {
		if(zoom>5)
		{

			map.animateCamera(CameraUpdateFactory.zoomTo(4.0f));
			return null;
		}
		else
		{

			byte[] image = readTileImage(x, y, zoom);
			return image == null ? null : new Tile(TILE_WIDTH, TILE_HEIGHT, image);
		}
	}

	private byte[] readTileImage(int x, int y, int zoom) {
		InputStream in = null;
		ByteArrayOutputStream buffer = null;

		try {
			in = mAssets.open(getTileFilename(x, y, zoom));
			buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[BUFFER_SIZE];

			while ((nRead = in.read(data, 0, BUFFER_SIZE)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();

			return buffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) try { in.close(); } catch (Exception ignored) {}
			if (buffer != null) try { buffer.close(); } catch (Exception ignored) {}
		}
	}

	private String getTileFilename(int x, int y, int zoom) {
//		if(zoom ==2)
//			return "map/" + zoom + '/' + x + '/' + y + ".jpg";
		return "map/" + zoom + '/' + x + '/' + y + ".jpg";
	}
}
