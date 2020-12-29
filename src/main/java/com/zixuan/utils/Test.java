import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        JSONArray instInforms = new JSONArray();
        JSONArray instGroupInfos = new JSONArray();
        JSONArray instInfoConfigs = new JSONArray();
        JSONArray instPrScores = new JSONArray();
        Integer loanPeriod = -999;
        String cols = "";
        //String jsonString = "";
        //JSONObject jsonObject = new JSONObject(jsonString);
        //System.out.println(jsonObject.has("test1"));
        String insertTime = "2020-12-27 20:41:15.058";
        System.out.println(insertTime.substring(0, 10).compareTo("2020-01-15"));

        File file = new File("C:\\Users\\wuzixuan\\Desktop\\test.json");

        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(fis);
        StringBuilder sb = new StringBuilder() ;
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            sb.append(s);
        }
        //System.out.println(sb.toString());

        JSONArray inputArray = new JSONArray(sb.toString());
        for(int i = 0; i < inputArray.length(); i ++){

            if(inputArray.get(i).toString().contains("com.myspace.assetinstmatch.RequestDto")){
                JSONObject requestDto = inputArray.getJSONObject(i).getJSONObject("insert").getJSONObject("object").getJSONObject("com.myspace.assetinstmatch.RequestDto");

                String drools2InstidList = requestDto.get("drools2InstidList").toString();


                System.out.println("drools2InstidList= "+ drools2InstidList);


                //issue_id=155618：如果入参有instInform, instGroupInfo, instConfigV2，那还是用这几个字段解析；
                //如果这几个字段不存在，那么改成解析instFullInform, instFullGroupInfo, InstFullFee。
                HashSet<Integer> instInformIds = new HashSet<Integer>();
                HashSet<Integer> instFullFeeIds = new HashSet<Integer>();
                HashSet<Integer> instFullFeePeriods = new HashSet<Integer>();

                instInforms = requestDto.getJSONArray("instFullInform");
                instInfoConfigs = requestDto.getJSONArray("instFullFee");
                System.out.println("instInforms: "+instInforms.toString());
                System.out.println("loanPeriod: "+ requestDto.getInt("loanPeriod"));
                //输出instInforms的所有id
                for (int j = 0; j<instInforms.length();j++){
                    JSONObject instInform = Utils.lowerCaseKey(instInforms.getJSONObject(j));
                    instInformIds.add(instInform.getInt("instid"));

                    //System.out.println(instInform.get("instid"));
                }
                System.out.println("instInform的ID："+instInformIds.toString());



                //输出所有instFullFee的instid
                System.out.println("instInfoConfigs: "+instInfoConfigs.toString());
                System.out.println("config长度： "+instInfoConfigs.length());
                for (int j = 0; j<instInfoConfigs.length();j++){
                    JSONObject instInfoConfig = Utils.lowerCaseKey(instInfoConfigs.getJSONObject(j));
                    //System.out.println("第"+j+"个");
                    //System.out.println(instInfoConfig.getInt("instid"));
                    //System.out.println(instInfoConfig.getInt("period"));
                    instFullFeeIds.add(instInfoConfig.getInt("instid"));
                    instFullFeePeriods.add(instInfoConfig.getInt("period"));
                }
                System.out.println("instFullFee的ID： "+instFullFeeIds.toString());
                System.out.println("instFullFee的Perios： "+instFullFeePeriods.toString());



            }
        }
    }
}
