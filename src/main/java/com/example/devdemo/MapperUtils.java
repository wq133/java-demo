package com.example.devdemo;

public class MapperUtils {
//
//    public void testJsonCount() throws JobExecutionException {
//
//        List<YmdTaskHis> ymdTaskHis = ymdTaskHisMapper.selectPage(new Page<>(1, 1), new EntityWrapper<>());
//
//        String jsonString = JSON.toJSONString(ymdTaskHis);
//        System.out.println("listSize = "+ymdTaskHis.size());

//        // 1MB limit
//        BigDecimal bodyLimitation = BigDecimal.valueOf(1024 * 1024);
//        int byteSize = jsonString.getBytes(StandardCharsets.UTF_8).length;

//        BigDecimal jsonByteSize = BigDecimal.valueOf(byteSize);
//        BigDecimal oneDateSize = jsonByteSize.divide(BigDecimal.valueOf(1024),3, RoundingMode.HALF_UP);
//        System.out.println("每条数据大小 = " + oneDateSize+"kb");

//        BigDecimal totoalStorgeSize = BigDecimal.valueOf(1024).divide(oneDateSize,2,RoundingMode.HALF_UP);
//        System.out.println("1MB 能存储 " + totoalStorgeSize+"条数据");
//
//        // 直接比较大小，判断是否小于1MB
////        boolean result = jsonByteSize.compareTo(bodyLimitation) <= 0;
////        System.out.println("result = " + result);
//    }
//
//
//    private void publicDetailMethod(BaseMapper mapper){
//
//        // 1.获取基准表的entity和一条数据的大小
//        List<Object> oneEntityList = mapper.selectPage(new Page<>(1, 1), new EntityWrapper<>());
//        System.out.println("oneEntityListLength = "+oneEntityList.size());
//        String jsonString = JSON.toJSONString(oneEntityList);
//
//        // 2.用1MB计算当前entity数据能查询几条
//        // 1MB limit
//        BigDecimal bodyLimitation = BigDecimal.valueOf(1024 * 1024);
//
//
//        int byteSize = jsonString.getBytes(StandardCharsets.UTF_8).length;
//
//        BigDecimal jsonByteSize = BigDecimal.valueOf(byteSize);
//        BigDecimal oneDateSize = jsonByteSize.divide(BigDecimal.valueOf(1024),3, RoundingMode.HALF_UP);
//        System.out.println("每条数据大小 = " + oneDateSize+"kb");
//
//        BigDecimal totoalStorgeSize = BigDecimal.valueOf(1024).divide(oneDateSize,2,RoundingMode.HALF_UP);
//        System.out.println("1MB 能存储 " + totoalStorgeSize+"条数据");
//
//        // 3.查询115%的1MB的数量
//        BigDecimal moreDateList = totoalStorgeSize.multiply(BigDecimal.valueOf(1.15));
//        List<Object> twiceSelectList = mapper.selectPage(new Page<>(1, moreDateList.intValue()), new EntityWrapper<>());
//
//        // 4.取合适条数
//
//        // 4.1先查询1MB数量的数据大小 大于1MB就移除一条 小于就新增一条 判断条件：就是实际数据量Byte<1Mb
//        //int byteSize = jsonString.getBytes(StandardCharsets.UTF_8).length;
//
//        // 5. 输出2 预计entity条数 输出4 实际可携带json条数
//
//    }
}
