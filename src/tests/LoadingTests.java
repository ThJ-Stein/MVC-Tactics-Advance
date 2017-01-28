package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import model.Map;

public class LoadingTests {
	@Test
	public void testMapLoad() {
		String path = "resources/testmap";
		assert(new File(path)).isFile();
		
		Map map = null;
		
		try {
			map = Map.loadMap(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(map.getTile(2, 2).toString());
	}
}