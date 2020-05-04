package BN;

import arc.util.Log;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class byteCode {
    public static String noColors(String string){
        String finalString = string;
        finalString = finalString.replaceAll("\\[clear\\]","");
        finalString = finalString.replaceAll("\\[black\\]","");
        finalString = finalString.replaceAll("\\[white\\]","");
        finalString = finalString.replaceAll("\\[lightgray\\]","");
        finalString = finalString.replaceAll("\\[gray\\]","");
        finalString = finalString.replaceAll("\\[darkgray\\]","");
        finalString = finalString.replaceAll("\\[blue\\]","");
        finalString = finalString.replaceAll("\\[navy\\]","");
        finalString = finalString.replaceAll("\\[royal\\]","");
        finalString = finalString.replaceAll("\\[slate\\]","");
        finalString = finalString.replaceAll("\\[sky\\]","");
        finalString = finalString.replaceAll("\\[cyan\\]","");
        finalString = finalString.replaceAll("\\[teal\\]","");
        finalString = finalString.replaceAll("\\[green\\]","");
        finalString = finalString.replaceAll("\\[acid\\]","");
        finalString = finalString.replaceAll("\\[lime\\]","");
        finalString = finalString.replaceAll("\\[forest\\]","");
        finalString = finalString.replaceAll("\\[olive\\]","");
        finalString = finalString.replaceAll("\\[yellow\\]","");
        finalString = finalString.replaceAll("\\[gold\\]","");
        finalString = finalString.replaceAll("\\[goldenrod\\]","");
        finalString = finalString.replaceAll("\\[orange\\]","");
        finalString = finalString.replaceAll("\\[brown\\]","");
        finalString = finalString.replaceAll("\\[tan\\]","");
        finalString = finalString.replaceAll("\\[brick\\]","");
        finalString = finalString.replaceAll("\\[red\\]","");
        finalString = finalString.replaceAll("\\[scarlet\\]","");
        finalString = finalString.replaceAll("\\[coral\\]","");
        finalString = finalString.replaceAll("\\[salmon\\]","");
        finalString = finalString.replaceAll("\\[pink\\]","");
        finalString = finalString.replaceAll("\\[magenta\\]","");
        finalString = finalString.replaceAll("\\[purple\\]","");
        finalString = finalString.replaceAll("\\[violet\\]","");
        finalString = finalString.replaceAll("\\[maroon\\]","");
        finalString = finalString.replaceAll("\\[#(.*)\\]","");
        finalString = finalString.replace("[]","");
        return  finalString;}
    public static String make(String fileName, JSONObject object) {
        try {
            String userHomePath = System.getProperty("user.home");
            File file = new File(userHomePath+"/mind_db/"+fileName+".cn");
            File path = new File(userHomePath+"/mind_db/");
            if (!path.isDirectory()) {
                Log.err("404 - could not find directory "+userHomePath+"/mind_db/");
                return null;
            }
            if (!file.exists()) file.createNewFile();
            FileWriter out = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(out);
            pw.println(object.toString());
            out.close();
            return "Done";
        } catch (IOException i) {
            i.printStackTrace();
            return "error: \n```"+i.getMessage().toString()+"\n```";
        }
    }
    public static boolean mkdir(String dirName) {
        String userHomePath = System.getProperty("user.home");
        File path = new File(userHomePath+"/"+dirName);
        if (!path.isDirectory()) {
            if (path.mkdir()) return true;
            return false;
        }
        return true;
    }
    public static JSONObject get(String fileName) {
        try {
            String userHomePath = System.getProperty("user.home");
            File file = new File(userHomePath+"/mind_db/"+fileName+".cn");
            File path = new File(userHomePath+"/mind_db/");
            if (!path.isDirectory()) {
                Log.err("404 - could not find directory "+userHomePath+"/mind_db/");
                return null;
            }
            if (!file.exists()) {
                Log.err("404 - "+userHomePath+"/mind_db/"+fileName+".cn"+" not found");
                return null;
            }
            FileReader fr = new FileReader(file);
            StringBuilder builder = new StringBuilder();
            int i;
            while((i=fr.read())!=-1) {
                builder.append((char)i);
            }
            //return null;
            return new JSONObject(new JSONTokener(builder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String putInt(String fileName, String key, float valueNumber) {
        try {
            JSONObject data = get(fileName);
            if (data == null) return null;
            data.put(key, valueNumber);

            return save(fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String putStr(String fileName, String key, String value) {
        try {
            JSONObject data = get(fileName);
            if (data == null) return null;
            data.put(key, value);

            return save(fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String remove(String fileName, String key) {
        try {
            JSONObject data = get(fileName);
            if (data == null) return null;
            data.remove(key);

            return save(fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String save(String fileName, JSONObject object) {
        String userHomePath = System.getProperty("user.home");
        File file = new File(userHomePath+"/mind_db/"+fileName+".cn");
        File path = new File(userHomePath+"/mind_db/");
        if (!path.isDirectory()) {
            Log.err("404 - could not find directory "+userHomePath+"/mind_db/");
            return null;
        }
        if (!file.exists()) {
            Log.err("404 - "+userHomePath+"/mind_db/"+fileName+".cn"+" not found");
            return null;
        }
        try {
            FileWriter out = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(out);
            pw.println(object.toString(4));
            out.close();
            return "Done";
        } catch (IOException it) {
            it.printStackTrace();
            return "error: \n```"+it.getMessage().toString()+"\n```";
        }
    }
    public static Boolean has(String fileName) {
        String userHomePath = System.getProperty("user.home");
        File file = new File(userHomePath+"/mind_db/"+fileName+".cn");
        File path = new File(userHomePath+"/mind_db/");
        if (!path.isDirectory()) {
            Log.err("404 - could not find directory "+userHomePath+"/mind_db/");
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return false;
    }
    public static Boolean hasDir(String dirName) {
        String userHomePath = System.getProperty("user.home");
        File path = new File(userHomePath+"/"+dirName+"/");
        if (path.isDirectory()) return true;
        return false;
    }
}
