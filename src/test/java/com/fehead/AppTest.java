package com.fehead;

import static org.junit.Assert.assertTrue;

import com.fehead.compoment.NoClassGenerator;
import com.fehead.controller.vo.NoCourse4MutUsers;
import com.fehead.dao.CourseMapper;
import com.fehead.dao.UserMapper;
import com.fehead.dao.entity.Course;
import com.fehead.dao.entity.NoCourse;
import com.fehead.dao.entity.NoCourse4Group;
import com.fehead.dao.entity.NoCoursePack;
import com.fehead.service.impl.CourseServiceImpl;
import com.fehead.utils.RandomUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest 
{

    @Autowired
    UserMapper userMapper;

    @Test
    public void whenSelectUserNotExit(){
        if(userMapper.selectById(1)==null){
            System.out.println(1);
        }else{
            System.out.println(2);
        }
    }
    @Test
    public void randomUtilTest(){
        System.out.println(RandomUtil.getStringRandom(10));
    }

    @Autowired
    NoClassGenerator noClassGenerator;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseServiceImpl courseService;

    @Test
    public void generateWeeks(){
//        Set<String> courseStrId = noClassGenerator.getCourseStrId();
        List<Course> courses = courseMapper.selectByUserId(3);
        Set<String> strings = noClassGenerator.transNum(noClassGenerator.splitWeeksCourse(courses));
//        Course target = noClassGenerator.transToObject("0811");



        if(strings.contains("0811")){
            System.out.println("有课");
        }else {
            System.out.println("没有课");
        }
    }

    @Test
    public void splitWeeks(){
//        Set<String> courseStrId = noClassGenerator.getCourseStrId();
        List<Course> courses = courseMapper.selectByUserId(3);
        System.out.println(courses.get(0).getWeeks());
        System.out.println("=====");
        List<String> strings = noClassGenerator.splitWeeks(courses.get(0).getWeeks());
//        Course target = noClassGenerator.transToObject("0811");
        strings.forEach(k->{
            System.out.println(k);
        });
    }
    @Test
    public void noClassSelect(){
        courseService.getUserNoClassPack(1).forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });

    }
    @Test
    public void getUserNoClassPack(){
        courseService.getUserNoClassPack(3).forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });

    }

    @Test
    public void getGroupNoClassPack(){
//        courseService.getGroupNoClassPack(1);

    }
    @Test
    public void savePackNoClass(){
        List<NoCoursePack> userNoClassPack = (List<NoCoursePack>) courseService.getUserNoClassPack(3);
        userNoClassPack.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
//        courseService.savePackNoClass(userNoClassPack);

    }

    @Test
    public void getPackNoClass4MutUsers(){
        Collection<NoCourse4Group> userNoClassPack = (Collection<NoCourse4Group>) courseService.getGroupNoClassPackOrderByWeeks(1,1);
        userNoClassPack.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
        Collection<NoCourse4MutUsers> noCourse4MutUsers = courseService.packNoClass4Group(userNoClassPack);
        noCourse4MutUsers.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });

    }


}
