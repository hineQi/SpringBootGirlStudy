package com.hine.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 齐海阳
 * Date: 2017/7/26
 * Time: 17:57
 */
@Aspect
@Component
public class TestAspect {

    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(* com.hine.controller.GirlsController.*(..))")
    public void point(){}

    @Before("point()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("host={}",request.getRemoteHost());
        logger.info("Test={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    }


    /**
     * 当天加入到所选和要背两张表
     * 要背列表显示：要背表中未完成的以及当天完成时间处于需要记忆阶段的（存在30分钟以内需要记忆的都还算未完成，30min以后就需要12h后记忆）
     * 记忆顺序：
     *      1.完成并且完成时间过了12h（连续三次完成）
     *      2.完成并且完成时间过了30min（连续三次完成）
     *      3.完成并且完成时间过了5min（连续三次完成）
     *      4.模糊并且模糊时间过了40s
     *      5.完成并且完成时间过了1min（一次完成）
     *      6.完成并且完成时间过了1min30(二次完成)
     *      7.完成并且完成时间过了2min(三次完成)
     *      8.未曾记忆过的
     *      9.所有模糊的
     *      10.所有未连续完成三次的（按次数正序排列）
     * 凌晨定时任务:
     *      1.根据要背表数据更新所选表数据（然后清空要背表）
     *      2.根据所选表中的当前记忆次数和创建时间计算，将需要记忆的数据添加到要背表中
     */
    @After("point()")
    public void Ebbinghaus() {
        int l=12, b=7, n,u=0 ,m,p,et,v,i,c;
        // l为List的数量
        // b为复习的次数，示例如下
        // b=4 , Between[] = { 0 , 1 , 2 , 4 , 7 }
        // b=5 , Between[] = { 0 , 1 , 2 , 4 , 7 , 15 }
        //定义艾宾浩斯复习间隔天数的数组
        int Between[][];
        Between = new int[l+1][b+1];
        for (n=0;n<l+1;n++)
        {
            Between[n][0] = 0;
            Between[n][1] = 1;
            Between[n][2] = 2;
            Between[n][3] = 4;
            Between[n][4] = 7;
            Between[n][5] = 14;
            Between[n][6] = 28;
            Between[n][7] = 60;
        }
        /*if ( b==5 )
            for (n=0;n<l+1;n++)
            {
                Between[n][5] = 15;
            }*/
        // 测试Between赋值是否正确
        //			for (n=0;n<l+1;n++)
        //			{
        //			 for (v=0;v<b+1;v++)
        //			  {
        //			   System.out.print("Between"+n+v+"="+Between[n][v]+" ");
        //			  }
        //			  System.out.println();
        //			}
        //定义List数组
        int List[];
        List = new int[l+1];
        for(n = 1; n < l+1; n++)//对List数组循环赋值
        {
            List[n] = n;
            //		 if (n<10) System.out.println("List"+u+n+"="+u+List[n]);//测试List赋值是否正确
            //		 else System.out.println("List"+n+"="+List[n]);//测试List赋值是否正确
        };
        //定义背单词计划的天数
        int Day[];
        int sum=0;
        for(n=1;n<b+1;n++) {sum += Between[1][n];}


        Day = new int[l+sum+1];
        for(n = 1; n < l+sum+1; n++)
        {
            Day[n] = n;
//					 if (n<10) System.out.println("Day"+u+n+"="+u+Day[n]);//测试Day赋值是否正确
//					 else System.out.println("Day"+n+"="+Day[n]);//测试Day赋值是否正确
        };
        //开始生成艾宾浩斯背单词计划表
        //循环天数
        for (n=1;n<l+sum+1;n++)
        {
            if (n<10) System.out.print("Day"+u+n+" ");//打印天数
            else System.out.print("Day"+n+" ");//打印天数
            if (n<l+1)
            {
                if (n<10) System.out.print("List"+u+n+" * ");//打印天数
                else System.out.print("List"+n+" * ");//打印天数
            }
            else {System.out.print("       * ");}
            //循环复习间隔
            for (p=0;p<b+1;p++)
            {
                //循环List
                for(m = 1; m < l+1; m++)
                {

                    if (Day[n]-List[m]==Between[m][0])//Day[n]-List[m]相隔天数 day07-list05=2  相隔天数==该复习间隔天数
                    {
                        if (Between[m][0]<Between[m][1])  //Between[m][0] 记录的是应该轮到的间隔天数1 2 4 7 14 28 60
                        {
                            Between[m][0]=Between[m][1];
                            List[m]=Day[n];}    //更新记录当前list值为当天天数  list[2]=第五天
                        else if (Between[m][0]<Between[m][2])
                        {
                            Between[m][0]=Between[m][2];
                            List[m]=Day[n];}
                        else if (Between[m][0]<Between[m][3])
                        {
                            Between[m][0]=Between[m][3];
                            List[m]=Day[n];}
                        else if (Between[m][0]<Between[m][4])
                        {
                            Between[m][0]=Between[m][4];
                            List[m]=Day[n];}
                        else if (Between[m][0]<Between[m][5])
                        {
                            Between[m][0]=Between[m][5];
                            List[m]=Day[n];}
                        else if (Between[m][0]<Between[m][6])
                        {
                            Between[m][0]=Between[m][6];
                            List[m]=Day[n];}
                        else if (Between[m][0]<Between[m][7])
                        {
                            Between[m][0]=Between[m][7];
                            List[m]=Day[n];}
                        else List[m]=0;//终止List


                        if (m<10) System.out.print("List"+u+m+" ");//打印List数
                        else System.out.print("List"+m+" ");//打印List数
                    }
                }
            }
            System.out.println(" ");
        }
    }


    @AfterReturning(pointcut = "point()", returning = "object")
    public void doAfterReturning(Object object){
        logger.info("object={}",object.toString());
    }
}
