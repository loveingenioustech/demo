package demo.facepp;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class FacePPDemo
{

    public static void main(String[] args)
    {
        HttpRequests httpRequests = new HttpRequests(ApiKeyConfigure.getKey(), ApiKeyConfigure.getSecret(), true, true);

        // demoDetect(httpRequests);

        // demoPerson(httpRequests);

        // demoFaceset(httpRequests);

        demoGroup(httpRequests);
    }

    /**
     * 检测
     * 
     * @param httpRequests
     */
    public static void demoDetect(HttpRequests httpRequests)
    {
        JSONObject result = null;
        try
        {
            System.out.println(Charset.forName("UTF-8").name());

            System.out.println("FacePlusPlus API Test:");

            // detection/detect
            result = httpRequests.detectionDetect(new PostParameters()
                    .setUrl("http://cn.faceplusplus.com/wp-content/themes/faceplusplus/assets/img/demo/9.jpg"));
            System.out.println(result);

            System.out.println(
                    result.getJSONArray("face").getJSONObject(0).getJSONObject("position").getJSONObject("center"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            clean(httpRequests, result);
        }
    }

    /**
     * Person 指同一个人的Face集合。Person中的多个Face可能来源于多个Image，但必须是同一个人的多张Face。
     * 一个Face可以属于多个不同的Person。Person被用在人脸验证（verify）和人脸识别（identify）中。
     * 
     * @param httpRequests
     */
    public static void demoPerson(HttpRequests httpRequests)
    {
        JSONObject result = null;
        try
        {
            result = httpRequests.detectionDetect(new PostParameters()
                    .setUrl("http://cn.faceplusplus.com/wp-content/themes/faceplusplus/assets/img/demo/9.jpg"));

            // person/create
            System.out.println("\nperson/create");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.personCreate(new PostParameters().setPersonName("person_" + i)));
            }

            new PostParameters().setPersonName("person_" + 0)
                    .setFaceId(result.getJSONArray("face").getJSONObject(0).getString("face_id")).getMultiPart()
                    .writeTo(System.out);

            // person/add_face
            System.out.println("\nperson/add_face");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName("person_" + i)
                        .setFaceId(result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
            }

            // person/set_info
            System.out.println("\nperson/set_info");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                new PostParameters().setPersonName("person_" + i).setTag("中文 tag_" + i).getMultiPart()
                        .writeTo(System.out);
                System.out.println(httpRequests
                        .personSetInfo(new PostParameters().setPersonName("person_" + i).setTag("中文 tag_" + i)));
            }

            // person/get_info
            System.out.println("\nperson/get_info");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.personGetInfo(new PostParameters().setPersonName("person_" + i)));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            clean(httpRequests, result);
        }
    }

    /**
     * Faceset 指一个或多个Face的集合。Faceset和Person一样，都是Face的集合，但Faceset并不要求Face来源于同一个人。
     * 一个Face可以属于多个不同的Person和Faceset。Faceset被用在人脸搜索（search）中。
     * 
     * @param httpRequests
     */
    public static void demoFaceset(HttpRequests httpRequests)
    {
        JSONObject result = null;
        try
        {
            result = httpRequests.detectionDetect(new PostParameters()
                    .setUrl("http://cn.faceplusplus.com/wp-content/themes/faceplusplus/assets/img/demo/9.jpg"));

            // faceset/create
            System.out.println("\nfaceset/create");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.facesetCreate(new PostParameters().setFacesetName("faceset_" + i)));
            }

            // faceset/add_face
            System.out.println("\nfaceset/add_face");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.facesetAddFace(new PostParameters().setFacesetName("faceset_" + i)
                        .setFaceId(result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
            }

            // faceset/set_info
            System.out.println("\nfaceset/set_info");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                new PostParameters().setFacesetName("faceset_" + i).setTag("中文 tag_" + i).getMultiPart()
                        .writeTo(System.out);
                System.out.println(httpRequests
                        .facesetSetInfo(new PostParameters().setFacesetName("faceset_" + i).setTag("中文 tag_" + i)));
            }

            // faceset/get_info
            System.out.println("\nfaceset/get_info");
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                System.out.println(httpRequests.facesetGetInfo(new PostParameters().setFacesetName("faceset_" + i)));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            clean(httpRequests, result);
        }
    }

    /**
     * Group 指多个Person的集合。在多数Face++人脸识别场景中，用户需指定一个Group来限定在此集合中进行识别。
     * 
     * @param httpRequests
     */
    public static void demoGroup(HttpRequests httpRequests)
    {
        JSONObject result = null;
        try
        {
            result = httpRequests.detectionDetect(new PostParameters()
                    .setUrl("http://cn.faceplusplus.com/wp-content/themes/faceplusplus/assets/img/demo/9.jpg"));

            // group/create
            System.out.println("\ngroup/create");
            System.out.println(httpRequests.groupCreate(new PostParameters().setGroupName("group_0")));

            // group/add_person
            System.out.println("\ngroup/add_person");
            ArrayList<String> personList = new ArrayList<String>();
            for (int i = 0; i < result.getJSONArray("face").length(); ++i)
            {
                personList.add("person_" + i);
            }

            new PostParameters().setGroupName("group_0").setPersonName(personList).getMultiPart().writeTo(System.out);
            System.out.println(httpRequests
                    .groupAddPerson(new PostParameters().setGroupName("group_0").setPersonName(personList)));

            // group/set_info
            System.out.println("\ngroup/set_info");
            System.out.println(
                    httpRequests.groupSetInfo(new PostParameters().setGroupName("group_0").setTag("group tag")));

            // group/get_info
            System.out.println("\ngroup/get_info");
            System.out.println(httpRequests.groupGetInfo(new PostParameters().setGroupName("group_0")));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            clean(httpRequests, result);
        }
    }

    /**
     * 调用完就清理
     * 
     * @param httpRequests
     * @param result
     */
    private static void clean(HttpRequests httpRequests, JSONObject result)
    {
        try
        {
            for (int i = 1; i < result.getJSONArray("face").length(); ++i)
            {
                httpRequests.personDelete(new PostParameters().setPersonName("person_" + i));
                httpRequests.facesetDelete(new PostParameters().setFacesetName("faceset_" + i));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        catch (FaceppParseException e)
        {
            e.printStackTrace();
        }
    }

}
