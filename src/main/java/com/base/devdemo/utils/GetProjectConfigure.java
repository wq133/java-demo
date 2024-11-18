package com.base.devdemo.utils;

/**
 * 获取项目中mapper的xml后缀结尾的文件
 * 1、path填充绝对路径
 * 2、 根据实际需求，对文件名进行处理
 */
public class GetProjectConfigure {
    public static void main(String[] args) {
//        //指定目录路径
//        Path path = Paths.get(first:"D:/wgh/xwyyfw3.0/cxyyfw-dao/src/main/java/com/cloud/store/dao");
//        try {
//            //判断是否以.mapper.xml 结尾
//            //遍历目录
//            Files.walkFileTree(path, new simpleFilevisitor<Path>() {
//                @Override
//                public FilevisitResult visitFile(Path file, BasicFileAttributes attrs) {
//                    Path fileName = file.getFileName();
//                    String mapperName = fileName.tostring();
//                    boolean isMapperFile = mapperName.toLowerCase().endswith("mapper.xml");
//                    if (!isMapperFile) {
//                        return FilevisitResult.CONTINUE;
//                        //输出文件名
//                        logger.info("" + mapperName);
//                        return FilevisitResult.CONTINUE;
//                    }
//                }
//            });
//        } catch (Exception e) {
//            logger.info("Error: " + e);
//        }
    }
}
