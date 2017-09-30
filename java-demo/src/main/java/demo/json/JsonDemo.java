package demo.json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings(value = { "unchecked" })
public class JsonDemo
{
    @NotNull
    static TypeReference<HashMap<String, Object>> mapTypeRef = new TypeReference<HashMap<String, Object>>()
    {
    };
    @NotNull
    static TypeReference<ArrayList<HashMap<String, Object>>> listTypeRef = new TypeReference<ArrayList<HashMap<String, Object>>>()
    {
    };
    @NotNull
    static TypeReference<HashSet<String>> setTypeRef = new TypeReference<HashSet<String>>()
    {
    };

    static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    final static String FILE_NAME_PINYIN = "cityListByPinyin.json";
    final static String FILE_NAME_JUHE = "cityListByJuhe.json";
    @NotNull
    static Set<String> ziwaixianSet = new HashSet<String>();

    public static void main(String[] args) throws Exception
    {
        filterByXian();

        filterByDi();

        filterByDistrict();

        testWeather();

        compare();

        extractCitySet();

        handlePinyin();
    }

    private static void filterByXian() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream jsonDistrict = loadFile("districtList.json");
        InputStream jsonXian = loadFile("xianList.json");

        List<Map<String, Object>> districtList = objectMapper.readValue(jsonDistrict, listTypeRef);
        Set<String> xianSet = objectMapper.readValue(jsonXian, setTypeRef);

        List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
        Map<String, Object> cityMap;

        // 加入县级市
        String theXian;
        Set<String> filterXianSet = new HashSet<String>();
        for (Map<String, Object> juhe : districtList)
        {
            theXian = (String) juhe.get("district");

            if (xianSet.contains(theXian))
            {
                filterXianSet.add(theXian);
                cityMap = new HashMap<String, Object>();
                cityMap.put("city", theXian);
                cityMap.put("province", (String) juhe.get("province"));
                cityMap.put("pinyin", translate2Pinyin(theXian));
                cityMap.put("di", (String) juhe.get("city"));

                cityList.add(cityMap);
            }
        }

        System.out.println(xianSet.size());
        System.out.println(cityList.size());

        SetView<String> diffSet = Sets.difference(xianSet, filterXianSet);
        System.out.println(objectMapper.writeValueAsString(diffSet));

        // 取出地级市
        Set<String> citySet = new HashSet<String>();
        String theCity;
        Set<String> failedCitySet = new HashSet<String>();
        for (Map<String, Object> juhe : districtList)
        {
            theCity = (String) juhe.get("city");
            citySet.add(theCity);
        }

        for (String cityName : citySet)
        {
            boolean isOk = getRequest(cityName);

            if (isOk)
            {
                cityMap = new HashMap<String, Object>();
                cityMap.put("city", cityName);
                cityMap.put("province", findProvince(cityName, districtList));
                cityMap.put("pinyin", translate2Pinyin(cityName));

                cityList.add(cityMap);
            }
            else
            {
                failedCitySet.add(cityName);
            }

        }

        System.out.println("免费天气接口不支持: " + objectMapper.writeValueAsString(failedCitySet));
        System.out.println(objectMapper.writeValueAsString(cityList));
    }

    private static void filterByDi() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream jsonDistrict = loadFile("districtList.json");
        List<Map<String, Object>> districtList = objectMapper.readValue(jsonDistrict, listTypeRef);

        // 取出地级市
        Set<String> citySet = new HashSet<String>();
        String theCity;

        for (Map<String, Object> juhe : districtList)
        {
            theCity = (String) juhe.get("city");
            citySet.add(theCity);
        }

        List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
        Map<String, Object> cityMap;

        for (String cityName : citySet)
        {
            boolean isOk = getRequest(cityName);

            if (isOk)
            {
                cityMap = new HashMap<String, Object>();
                cityMap.put("city", cityName);
                cityMap.put("province", findProvince(cityName, districtList));
                cityMap.put("pinyin", translate2Pinyin(cityName));

                cityList.add(cityMap);
            }

        }

        System.out.println(objectMapper.writeValueAsString(cityList));
    }

    private static void testWeather()
    {
        getRequest("台中");
    }

    private static void compare() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream jsonJuhe = loadFile(FILE_NAME_JUHE);
        InputStream jsonDistrict = loadFile("districtList.json");

        List<Map<String, Object>> cityListByJuhe = objectMapper.readValue(jsonJuhe, listTypeRef);
        List<Map<String, Object>> districtList = objectMapper.readValue(jsonDistrict, listTypeRef);

        String id;
        String theId = "";
        boolean tag;
        for (Map<String, Object> juhe : cityListByJuhe)
        {
            id = (String) juhe.get("id");
            tag = false;

            for (Map<String, Object> district : districtList)
            {
                theId = (String) district.get("id");
                if (id.equals(theId))
                {
                    tag = true;
                    break;
                }
            }

            if (!tag)
            {
                System.out.println(objectMapper.writeValueAsString(juhe));
            }

        }
    }

    private static void filterByDistrict() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream jsonJuhe = loadFile(FILE_NAME_JUHE);

        List<Map<String, Object>> cityListByJuhe = objectMapper.readValue(jsonJuhe, listTypeRef);
        Map<String, Object> juhe;
        String district;

        List<Map<String, Object>> districtList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < cityListByJuhe.size(); i++)
        {
            juhe = cityListByJuhe.get(i);
            district = (String) juhe.get("district");

            boolean isOk = getRequest(district);

            if (isOk)
            {
                districtList.add(juhe);
            }
        }

        System.out.println(objectMapper.writeValueAsString(districtList));
        System.out.println(objectMapper.writeValueAsString(ziwaixianSet));
    }

    /**
     * 提取城市列表
     * 
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    private static void extractCitySet() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream jsonJuhe = loadFile(FILE_NAME_JUHE);

        List<Map<String, Object>> cityListByJuhe = objectMapper.readValue(jsonJuhe, listTypeRef);

        Set<String> citySet = new HashSet<String>();
        String theCity;

        for (Map<String, Object> juhe : cityListByJuhe)
        {
            theCity = (String) juhe.get("city");
            citySet.add(theCity);
        }
    }

    private static String translate2Pinyin(@NotNull String cityName)
    {
        return PinyinHelper.convertToPinyinString(cityName, "", PinyinFormat.WITHOUT_TONE);
    }

    private static String findProvince(String cityName, @NotNull List<Map<String, Object>> cityList)
    {
        String theCity;
        for (Map<String, Object> juhe : cityList)
        {
            theCity = (String) juhe.get("city");

            if (theCity.equals(cityName))
            {
                return (String) juhe.get("province");
            }

        }

        return null;
    }

    private static void handlePinyin()
            throws IOException, JsonParseException, JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        InputStream jsonPinyin = loadFile(FILE_NAME_PINYIN);

        TypeReference<ArrayList<HashMap<String, Object>>> typeRef = new TypeReference<ArrayList<HashMap<String, Object>>>()
        {
        };

        List<Map<String, Object>> cityListByPinyin = mapper.readValue(jsonPinyin, typeRef);

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        String cityName, cityPinyin;
        char initialLetter;
        char word = 'A';
        int count = 0;
        // 最多不超过26个英文字母
        for (int i = 0; i < 26; i++)
        {
            // 按拼音字母分组
            List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
            Map<String, Object> groupMap = new HashMap<String, Object>();
            groupMap.put("charType", word);

            Map<String, Object> cityMap;

            for (Map<String, Object> city : cityListByPinyin)
            {
                cityPinyin = StringUtils.capitalize((String) city.get("pinyin"));
                initialLetter = cityPinyin.charAt(0);

                if (initialLetter == word)
                {
                    cityName = (String) city.get("name");
                    cityMap = new HashMap<String, Object>();
                    cityMap.put("city", cityName);

                    cityList.add(cityMap);
                    count++;
                }
                else
                {
                    continue;
                }

            }

            groupMap.put("citylist", cityList);
            result.add(i, groupMap);
            word++;
        }

        System.out.println(mapper.writeValueAsString(result));
        System.out.println("total group: " + result.size());
        System.out.println("total citys: " + count);
    }

    private static InputStream loadFile(String fileName)
    {
        InputStream jsonInput = JsonDemo.class.getResourceAsStream(fileName);
        if (jsonInput == null)
        {
            throw new NullPointerException("can't find " + fileName);
        }

        return jsonInput;
    }

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    @NotNull
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    // 配置您申请的KEY
    public static final String APPKEY = "*************************";

    // 根据城市查询天气
    public static boolean getRequest(String cityName)
    {
        String result = null;
        String url = "http://op.juhe.cn/onebox/weather/query";// 请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();// 请求参数
        params.put("cityname", cityName);// 要查询的城市，如：温州、上海、北京
        params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
        params.put("dtype", "");// 返回数据的格式,xml或json，默认json

        try
        {
            result = net(url, params, "GET");
            Map<String, Object> object = objectMapper.readValue(result, mapTypeRef);

            int errorCode = (int) object.get("error_code");

            if (errorCode == 0)
            {
                Map<String, Object> theResult = (Map<String, Object>) object.get("result");
                Map<String, Object> data = (Map<String, Object>) theResult.get("data");
                Map<String, Object> life = (Map<String, Object>) data.get("life");
                Map<String, Object> info = (Map<String, Object>) life.get("info");
                Map<String, Object> realtime = (Map<String, Object>) data.get("realtime");
                Map<String, Object> weather = (Map<String, Object>) realtime.get("weather");

                String humid = (String) weather.get("humidity");
                String temp = (String) weather.get("temperature");
                List<String> ziwaixian = (List<String>) info.get("ziwaixian");

                if (StringUtils.isNoneBlank(humid) && StringUtils.isNoneBlank(temp)
                        && StringUtils.isNoneBlank(ziwaixian.get(0)))
                {
                    ziwaixianSet.add(ziwaixian.get(0));

                    // System.out.println(object.get("result"));
                    return true;
                }

            }
            else
            {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Nullable
    public static String net(String strUrl, @Nullable Map<String, Object> params, @Nullable String method) throws Exception
    {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try
        {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET"))
            {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET"))
            {
                conn.setRequestMethod("GET");
            }
            else
            {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST"))
            {
                try (DataOutputStream out = new DataOutputStream(conn.getOutputStream()))
                {
                    out.writeBytes(urlencode(params));
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null)
            {
                sb.append(strRead);
            }
            rs = sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (conn != null)
            {
                conn.disconnect();
            }
        }
        return rs;
    }

    // 将map型转为请求参数型
    public static String urlencode(@NotNull Map<String, ?> data)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> i : data.entrySet())
        {
            try
            {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
