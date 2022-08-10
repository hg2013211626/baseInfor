package com.magnificent.tool;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author : Huang Gen
 * @Since : Created in 2021/11/13 23:19
 * @Description : classPath扫描类
 */
public class ClassPathScanner {

    private static Pattern JSON_FILE_PATTERN = Pattern.compile(".*\\.json");

    public static InputStream getFileStreamFromPath(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static String getFileStringFromPath(String path) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (null != inputStream) {
            final String content = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining());
            return content;
        }
        return null;
    }

    public static List<String> getSubFilePathsFromPath(String path, String suffix) {
        List<String> result = new ArrayList<>();
        ScanResult scanResult = new ClassGraph().whitelistPaths(path).scan();
        scanResult.getResourcesMatchingPattern(Pattern.compile(".*" + suffix)).forEach(
                res -> {
                    result.add(res.getPath());
                }
        );
        return result;
    }

    public static List<String> getJSONStringsFromPath(String path) {
        List<String> result = new ArrayList<>();
        ScanResult scanResult = new ClassGraph().whitelistPaths(path).scan();
        scanResult.getResourcesMatchingPattern(JSON_FILE_PATTERN).forEachByteArray((Resource res, byte[] content) -> {
            String fileContent = new String(content);
            result.add(fileContent);
        });
        return result;
    }

    public static HashMap<String, String> getJsonStringsMapFromPath(String path) {
        HashMap<String, String> result = new HashMap<>();
        ScanResult scanResult = new ClassGraph().whitelistPaths(path).scan();
        scanResult.getResourcesMatchingPattern(JSON_FILE_PATTERN).forEachByteArray(
                (Resource res, byte[] content) -> {
                    String filePath = res.getPath();
                    String fileName = filePath.substring(filePath.indexOf("/") + 1, filePath.lastIndexOf("."));
                    String fileContent = new String(content);
                    result.put(fileName, fileContent);
                });
        return result;
    }


}
