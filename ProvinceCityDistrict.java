import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class ProvinceCityDistrict {

  public static void main(String[] args) throws IOException {
    String path = "";
    String content = FileUtils.readFileToString(FileUtils.getFile(path), StandardCharsets.UTF_8);
    JSONObject json = JSON.parseObject(content);
    List<Map<String, String>> list = Lists.newArrayList();
    json.getJSONArray("districts")
        .getJSONObject(0)
        .getJSONArray("districts")
        .toList(JSONObject.class)
        .forEach(
            province -> {
              String provinceName = province.getString("name");
              province
                  .getJSONArray("districts")
                  .toList(JSONObject.class)
                  .forEach(
                      city -> {
                        String cityName = city.getString("name");
                        city.getJSONArray("districts")
                            .toList(JSONObject.class)
                            .forEach(
                                district -> {
                                  String districtName = district.getString("name");
                                  list.add(
                                      ImmutableMap.of(
                                          "provinceName",
                                          provinceName,
                                          "cityName",
                                          cityName,
                                          "districtName",
                                          districtName));
                                });
                      });
            });
    System.out.println(JSON.toJSONString(list));
  }
}
