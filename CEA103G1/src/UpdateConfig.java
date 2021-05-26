import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.campsite.model.CampService;
import com.campsite.model.CampVO;

public class UpdateConfig {

	public static void main(String[] args) throws IOException {
		String Path = "C:\\Users\\Tibame\\Documents\\CampionWorkspace\\CEA103G1\\WebContent\\images\\config";
		File file = new File(Path);
		String[] config = file.list();
		CampService campSvc = new CampService();
		List<CampVO> camplist = campSvc.getAll();
		System.out.println(camplist);
		for (int i = 0; i < config.length; i++) {
			for(CampVO campVO : camplist) {
				if(campVO.getCamp_name().equals(config[i].substring(0, config[i].length() - 4))) {
					byte[] is = getPictureByteArray(Path + "\\" + config[i]);
					campSvc.updateConfig(is, campVO.getCamp_no());
					break;
				}
			}
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
