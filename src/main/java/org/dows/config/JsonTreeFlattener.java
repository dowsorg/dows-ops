//package org.dows.cloud;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.xmind.core.IWorkbook;
//
//import org.xmind.core.*;
//import org.xmind.core.util.DOMUtils;
//import java.io.*;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//public class JsonTreeFlattener {
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static List<JsonNode> flattenJsonTree(JsonNode jsonNode) {
//        List<JsonNode> flattenedNodes = new ArrayList<>();
//
//        if (jsonNode.isObject()) {
//            flattenObject(jsonNode, flattenedNodes, "");
//        } else if (jsonNode.isArray()) {
//            flattenArray(jsonNode, flattenedNodes, "");
//        }
//
//        return flattenedNodes;
//    }
//
//    private static void flattenObject(JsonNode jsonNode, List<JsonNode> flattenedNodes, String prefix) {
//        jsonNode.fields().forEachRemaining(entry -> {
//            String key = entry.getKey();
//            JsonNode value = entry.getValue();
//
//            if (value.isObject()) {
//                flattenObject(value, flattenedNodes, prefix + key + ".");
//            } else if (value.isArray()) {
//                flattenArray(value, flattenedNodes, prefix + key + ".");
//            } else {
//                flattenedNodes.add(objectMapper.createObjectNode().put("key", prefix + key).set("value", value));
//            }
//        });
//    }
//
//    private static void flattenArray(JsonNode jsonNode, List<JsonNode> flattenedNodes, String prefix) {
//        for (int i = 0; i < jsonNode.size(); i++) {
//            JsonNode value = jsonNode.get(i);
//
//            if (value.isObject()) {
//                flattenObject(value, flattenedNodes, prefix + i + ".");
//            } else if (value.isArray()) {
//                flattenArray(value, flattenedNodes, prefix + i + ".");
//            } else {
//                flattenedNodes.add(objectMapper.createObjectNode()
//                        .put("key", prefix + i)
//                        .set("value", value));
//            }
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new FileInputStream("E:\\data\\test.xmind");
//        //IWorkbook workbook = .openWorkbook();
//
///*        dxz p0  4个
//        // 状态  自动触发计算
//        //
//        // 1.
//
//        cgp 任务/自定义任务
//        // 任务，突发时间 ，计时，议事，
//
//        heropro 郭请假 目前一个问题*/
//
//
//        InputStream stream = null;
//        //获取文件输入流
//        //FileInputStream input = new FileInputStream(file);
//        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang.IllegalArgumentException: MALFORMED)
//        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream), Charset.forName("UTF-8"));
//        //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
//        ZipEntry ze;
//        //循环遍历
//        while ((ze = zipInputStream.getNextEntry()) != null) {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            if (!ze.isDirectory() && ze.getName().equals("content.json")) {
//                //读取
//                byte[] buffer = new byte[1024];
//                int len;
//                while ((len = zipInputStream.read(buffer)) > -1) {
//                    baos.write(buffer, 0, len);
//                }
//                baos.flush();
//                stream = new ByteArrayInputStream(baos.toByteArray());
//                break;
//            }
//        }
//        //一定记得关闭流
//        zipInputStream.closeEntry();
//        inputStream.close();
//
//
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = objectMapper.readTree(stream);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        List<JsonNode> flattenedNodes = JsonTreeFlattener.flattenJsonTree(jsonNode);
//
//// 打印平铺后的结果
//        for (JsonNode node : flattenedNodes) {
//            System.out.println(node.get("key").asText() + ": " + node.get("value"));
//        }
//    }
//}