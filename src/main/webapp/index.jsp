<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>北京各区县二手房价分析图</title>
  <style>
    #main{
      width: 1200px;
      height: 600px;
      margin: 50px auto;
    }
  </style>
  <script src="echarts.js"></script>
</head>
<body>
<div id="main"></div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title:{
            text:'北京各区县二手房价分析图',
            subtext:'基于链家网2017年5月8日数据'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['最高总价', '平均总价', '最低总价', '最高单价', '平均单价', '最低单价']
        },
        grid: {
            top:'15%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : [
                    '东城',
                    '西城',
                    '朝阳',
                    '海淀',
                    '丰台',
                    '石景山',
                    '通州',
                    '昌平',
                    '大兴',
                    '亦庄开发区',
                    '顺义',
                    '房山',
                    '门头沟',
                    '平谷',
//            '怀柔',
                    '密云',
                    '延庆',
                    '燕郊'
                ]
            }
        ],
        yAxis : [
            {
                type : 'value',
                name:'总价（万元）'
            }
        ],
        series: [
            {
                name: '平均总价',
                type: 'bar',
                data: [957, 982, 822, 914, 624, 553, 569, 564, 545, 686, 536, 391, 460, 507, 890, 382, 287]
            },
            {
                name: '最高总价',
                type: 'bar',
                data: [5600, 5600, 8000, 7000, 3500, 7300, 3100, 8000, 3780, 2200, 4200, 950, 3200, 1500, 1500, 510, 3660]
            },
            {
                name: '最低总价',
                type: 'bar',
                data: [330, 415, 200, 300, 33, 40, 230, 26, 35, 127, 190, 205, 30, 239, 310, 255, 89]
            },
            {
                name: '最高单价',
                type: 'bar',
                data: [149972, 149985, 149882, 149939, 128361, 132626, 117112, 126350, 105367, 79230, 87291, 62963, 84131, 53055, 41149, 30539, 72339]
            },
            {
                name: '平均单价',
                type: 'bar',
                data: [112597, 117415, 79878, 97775, 66534, 64020, 55860, 49532, 53130, 53722, 45325, 38841, 47563, 35023, 27604, 29828, 30398]
            },
            {
                name: '最低单价',
                type: 'bar',
                data: [60815, 47994, 34123, 39303, 7047, 11511, 26801, 6747, 11921, 33292, 25013, 21953, 10153, 24257, 15607, 29118, 16478]
            }
        ]

    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>